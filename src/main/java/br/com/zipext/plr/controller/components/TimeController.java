package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.TimeDTO;
import br.com.zipext.plr.service.TimeService;

@Controller
@RequestMapping("/times")
public class TimeController {
	
	@Autowired
	private TimeService service;

	@GetMapping
	public ResponseEntity<List<TimeDTO>> findAll() {
		return new ResponseEntity<>
			(this.service.findAllByOrderByNomeAsc().stream().map(TimeDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
