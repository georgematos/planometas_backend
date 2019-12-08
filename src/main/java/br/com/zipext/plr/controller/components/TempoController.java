package br.com.zipext.plr.controller.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.service.TempoService;

@Controller
@RequestMapping("/tempo")
public class TempoController {

	@Autowired
	private TempoService service;
	
	@GetMapping("/ano")
	public ResponseEntity<List<Integer>> findDistinctAno() {
		return new ResponseEntity<>(this.service.findDistinctAno(), HttpStatus.OK);
	}
}
