package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.TipoMetaDTO;
import br.com.zipext.plr.service.TipoMetaService;

@Controller
@RequestMapping("/tiposmeta")
public class TipoMetaController {

	@Autowired
	private TipoMetaService service;

	@GetMapping
	public ResponseEntity<List<TipoMetaDTO>> findAll() {
		return new ResponseEntity<List<TipoMetaDTO>>
				(this.service.findAllByOrderByDescricaoAsc().stream().map(TipoMetaDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
