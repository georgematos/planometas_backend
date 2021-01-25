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

import br.com.zipext.plr.dto.DiretoriaDTO;
import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.service.DiretoriaService;

@Controller
@RequestMapping("/diretorias")
public class DiretoriaController {

	@Autowired
	private DiretoriaService service;

	@GetMapping
	public ResponseEntity<List<DiretoriaDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAllByOrderByNomeAsc().stream().map(DiretoriaDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<DiretoriaDTO>> findByFilter(@RequestParam Long id, @RequestParam String nome) {
		List<DiretoriaModel> diretorias = service.findByFilter(id, nome);
		return ResponseEntity.ok().body(diretorias.stream().map(DiretoriaDTO::new).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<DiretoriaDTO> save(@RequestBody DiretoriaDTO dto) throws Exception {
		if (dto.getId() != null) {
			this.service.findById(dto.getId());
		}

		DiretoriaModel model;

		model = this.service.save(dto.obterModel());
		return ResponseEntity.status(HttpStatus.CREATED).body(new DiretoriaDTO(model));

	}

	@PutMapping("/{id}")
	public ResponseEntity<DiretoriaDTO> atualizarCargo(@PathVariable Long id, @RequestBody DiretoriaDTO dto)
			throws Exception {
		DiretoriaModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new DiretoriaDTO(entity));
	}
	
}
