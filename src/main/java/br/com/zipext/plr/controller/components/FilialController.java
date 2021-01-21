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

import br.com.zipext.plr.dto.FilialDTO;
import br.com.zipext.plr.dto.GenericDTO;
import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.service.FilialService;

@Controller
@RequestMapping("/filiais")
public class FilialController {

	@Autowired
	private FilialService service;

	@GetMapping
	public ResponseEntity<List<GenericDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAllByOrderByNomeAsc().stream().map(GenericDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<FilialDTO>> findByFilter(@RequestParam Long id, @RequestParam String nome) {
		List<FilialModel> filiais = service.findByFilter(id, nome);
		return ResponseEntity.ok().body(filiais.stream().map(FilialDTO::new).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<FilialDTO> save(@RequestBody FilialDTO dto) throws Exception {
		if (dto.getId() != null) {
			this.service.findById(dto.getId());
		}

		FilialModel model;

		model = this.service.save(dto.obterModel());
		return ResponseEntity.status(HttpStatus.CREATED).body(new FilialDTO(model));

	}

	@PutMapping("/{id}")
	public ResponseEntity<FilialDTO> update(@PathVariable Long id, @RequestBody FilialDTO dto) throws Exception {
		FilialModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new FilialDTO(entity));
	}
}
