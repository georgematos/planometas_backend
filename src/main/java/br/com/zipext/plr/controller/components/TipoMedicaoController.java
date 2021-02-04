package br.com.zipext.plr.controller.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.TipoMedicaoDTO;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.service.TipoMedicaoService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/tiposmedicao")
public class TipoMedicaoController {

	@Autowired
	private TipoMedicaoService service;

	@GetMapping
	public ResponseEntity<List<TipoMedicaoDTO>> findAll() {
		return new ResponseEntity<>(this.service.findAllByOrderByDescricaoAsc().stream().map(TipoMedicaoDTO::new)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<TipoMedicaoDTO>> findByFilter(@RequestParam Long id, @RequestParam String descricao) {
		List<TipoMedicaoModel> tipoMedicoes = service.findByFilter(id, descricao);

		List<TipoMedicaoDTO> dtos = tipoMedicoes.stream().map(TipoMedicaoDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtos);
	}
	
	@PostMapping
	public ResponseEntity<TipoMedicaoDTO> save(@RequestBody TipoMedicaoDTO dto) throws Exception {
		if (dto.getId() != null) {
			this.service.findById(dto.getId());
		}

		TipoMedicaoModel model = this.service.save(dto.obterModel());

		return ResponseEntity.status(HttpStatus.CREATED).body(new TipoMedicaoDTO(model));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoMedicaoDTO> atualizarCargo(@PathVariable Long id, @RequestBody TipoMedicaoDTO dto)
			throws Exception {
		TipoMedicaoModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new TipoMedicaoDTO(entity));
	}
	
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportTiposMedicao() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "TIPOS_MEDICAO" + "_" + PLRUtils.today() + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		ByteArrayInputStream exported = service.export();
		
		return new ResponseEntity<>(new InputStreamResource(exported), headers, HttpStatus.OK);
	}
}
