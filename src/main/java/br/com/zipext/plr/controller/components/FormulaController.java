package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.FormulaDTO;
import br.com.zipext.plr.service.FormulaService;

@Controller
@RequestMapping("/formulas")
public class FormulaController {

	@Autowired
	private FormulaService service;

	@GetMapping
	public ResponseEntity<List<FormulaDTO>> findAll() {
		return new ResponseEntity<>
			(this.service.findAllByOrderByNomeAsc().stream().map(FormulaDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
