package br.com.zipext.plr.controller.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.zipext.plr.dto.TipoDeMetaAbvDTO;
import br.com.zipext.plr.dto.TipoMetaDTO;
import br.com.zipext.plr.enums.EnumTipoMeta;
import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.service.TipoMetaService;

@Controller
@RequestMapping("/tiposmeta")
public class TipoMetaController {

	@Autowired
	private TipoMetaService service;

	@GetMapping
	public ResponseEntity<List<TipoMetaDTO>> findAll() {
		return new ResponseEntity<List<TipoMetaDTO>>(
				this.service.findAllByOrderByDescricaoAsc().stream().map(TipoMetaDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<TipoMetaDTO>> findByFilter(@RequestParam Long id, @RequestParam String descricao,
			@RequestParam String abreviacao, @RequestParam String restrita) {
		List<TipoMetaModel> tipoMetas = service.findByFilter(id, descricao, abreviacao, restrita);

		List<TipoMetaDTO> dtos = tipoMetas.stream().map(x -> {

			TipoMetaDTO dto = new TipoMetaDTO(x);
			TipoDeMetaAbvDTO abv = new TipoDeMetaAbvDTO();
			abv.setId(EnumTipoMeta.forAbv(x.getAbreviacao().charAt(0)).getId());
			abv.setAbv(EnumTipoMeta.forAbv(x.getAbreviacao().charAt(0)).getAbv());
			dto.setAbv(abv);

			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping("/abreviacoes")
	public ResponseEntity<List<TipoDeMetaAbvDTO>> getAbreviacoes() {
		List<TipoDeMetaAbvDTO> abreviacoes = new ArrayList<>();

		for (EnumTipoMeta e : EnumTipoMeta.values()) {
			abreviacoes.add(new TipoDeMetaAbvDTO(e.getId(), e.getAbv()));
		}

		return ResponseEntity.ok().body(abreviacoes);
	}

	@PostMapping
	public ResponseEntity<TipoMetaDTO> save(@RequestBody TipoMetaDTO dto) throws Exception {
		if (dto.getId() != null) {
			this.service.findById(dto.getId());
		}

		TipoMetaModel model = this.service.save(dto.obterModel());

		return ResponseEntity.status(HttpStatus.CREATED).body(new TipoMetaDTO(model));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoMetaDTO> atualizarCargo(@PathVariable Long id, @RequestBody TipoMetaDTO dto)
			throws Exception {
		TipoMetaModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new TipoMetaDTO(entity));
	}
}
