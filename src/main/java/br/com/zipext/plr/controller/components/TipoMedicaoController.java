package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.TipoMedicaoDTO;
import br.com.zipext.plr.service.TipoMedicaoService;

@Controller
@RequestMapping("/tiposmedicao")
public class TipoMedicaoController {

	@Autowired
	private TipoMedicaoService service;
	
	@GetMapping
	public ResponseEntity<List<TipoMedicaoDTO>> findAll() {
		return new ResponseEntity<>
			(this.service.findAllByOrderByDescricaoAsc().stream().map(TipoMedicaoDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
