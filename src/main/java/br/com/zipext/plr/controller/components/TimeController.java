package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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

import br.com.zipext.plr.dto.TimeDTO;
import br.com.zipext.plr.model.TimeModel;
import br.com.zipext.plr.service.TimeService;

@Controller
@RequestMapping("/times")
public class TimeController {

	@Autowired
	private TimeService service;

	@GetMapping
	public ResponseEntity<List<TimeDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAllByOrderByNomeAsc().stream().map(TimeDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<TimeDTO>> findByFilter(@RequestParam String codigo, @RequestParam String nome) {
		if (StringUtils.isBlank(codigo)) {
			codigo = null;
		} else {
			codigo = codigo.toUpperCase();
		}
		List<TimeModel> times = service.findByFilter(codigo, nome);
		return ResponseEntity.ok().body(times.stream().map(TimeDTO::new).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<TimeDTO> save(@RequestBody TimeDTO dto) throws Exception {
		TimeModel model = this.service.save(dto.obterModel());
		return ResponseEntity.status(HttpStatus.CREATED).body(new TimeDTO(model));

	}

	@PutMapping("/{codigo}")
	public ResponseEntity<TimeDTO> update(@PathVariable String codigo, @RequestBody TimeDTO dto) throws Exception {
		dto.setCodigo(dto.getCodigo().toUpperCase());
		TimeModel entity = this.service.update(codigo, dto);
		return ResponseEntity.ok().body(new TimeDTO(entity));
	}
}
