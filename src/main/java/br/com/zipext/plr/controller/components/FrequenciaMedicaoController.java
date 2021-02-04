package br.com.zipext.plr.controller.components;

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

import br.com.zipext.plr.dto.FrequenciaMedicaoDTO;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.service.FrequenciaMedicaoService;

@Controller
@RequestMapping("/frequenciasmedicao")
public class FrequenciaMedicaoController {

	@Autowired
	private FrequenciaMedicaoService service;

	@GetMapping
	public ResponseEntity<List<FrequenciaMedicaoDTO>> findAll() {
		return new ResponseEntity<>(this.service.findAllByOrderByDescricaoAsc().stream().map(FrequenciaMedicaoDTO::new)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<FrequenciaMedicaoDTO>> findByFilter(@RequestParam Long id, @RequestParam String descricao) {
		List<FrequenciaMedicaoModel> tipoMedicoes = service.findByFilter(id, descricao);

		List<FrequenciaMedicaoDTO> dtos = tipoMedicoes.stream().map(FrequenciaMedicaoDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtos);
	}

	@PostMapping
	public ResponseEntity<FrequenciaMedicaoDTO> save(@RequestBody FrequenciaMedicaoDTO dto) throws Exception {
		if (dto.getId() != null) {
			this.service.findById(dto.getId());
		}

		FrequenciaMedicaoModel model = this.service.save(dto.obterModel());

		return ResponseEntity.status(HttpStatus.CREATED).body(new FrequenciaMedicaoDTO(model));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FrequenciaMedicaoDTO> atualizarCargo(@PathVariable Long id, @RequestBody FrequenciaMedicaoDTO dto)
			throws Exception {
		FrequenciaMedicaoModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new FrequenciaMedicaoDTO(entity));
	}
}
