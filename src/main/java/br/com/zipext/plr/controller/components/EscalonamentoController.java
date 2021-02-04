package br.com.zipext.plr.controller.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

import br.com.zipext.plr.dto.EscalonamentoDTO;
import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.service.EscalonamentoService;
import br.com.zipext.plr.service.TipoMedicaoService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/escalonamentos")
public class EscalonamentoController {

	@Autowired
	private EscalonamentoService service;
	
	@Autowired
	private TipoMedicaoService tipoMedicaoService;

	@GetMapping
	public ResponseEntity<List<EscalonamentoDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAll().stream().map(EscalonamentoDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<EscalonamentoDTO>> findByFilter(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "tipoMedicaoId", required = false) Long tipoMedicaoId,
			@RequestParam(name = "faixa", required = false) BigDecimal faixa,
			@RequestParam(name = "desempenho", required = false) BigDecimal desempenho) {

		List<EscalonamentoDTO> dtos = this.service.findByFilter(id, tipoMedicaoId, faixa, desempenho).stream()
				.map(EscalonamentoDTO::new).collect(Collectors.toList());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EscalonamentoDTO> save(@RequestBody EscalonamentoDTO dto) throws Exception {

		dto.setTipoMedicao(tipoMedicaoService.findById(dto.getTipoMedicaoId()));
		
		EscalonamentoModel resultModel = service.save(dto.obterModel());

		return ResponseEntity.ok().body(new EscalonamentoDTO(resultModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EscalonamentoDTO> atualizarCargo(@PathVariable Long id, @RequestBody EscalonamentoDTO dto) throws Exception {
		EscalonamentoModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new EscalonamentoDTO(entity));
	}
	
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportEscalonamentos() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "ESCALONAMENTOS" + "_" + PLRUtils.today() + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		ByteArrayInputStream exported = service.export();
		
		return new ResponseEntity<>(new InputStreamResource(exported), headers, HttpStatus.OK);
	}

}
