package br.com.zipext.plr.controller.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.RelAvaliacaoProjetoDTO;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.service.RelAvaliacaoProjetoService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/avaliacao")
public class RelAvaliacaoProjetoController {

	@Autowired
	private RelAvaliacaoProjetoService service;

	@GetMapping
	public ResponseEntity<List<RelAvaliacaoProjetoDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAll().stream().map(m -> new RelAvaliacaoProjetoDTO(m)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<RelAvaliacaoProjetoDTO> save(@RequestBody RelAvaliacaoProjetoDTO esc) {
		RelAvaliacaoProjetoModel model = esc.obterModel();

		RelAvaliacaoProjetoModel savedModel = service.save(model);
		
		return ResponseEntity.ok(new RelAvaliacaoProjetoDTO(savedModel));
	}
	
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportRelAvaliacaoProjetos() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "AVALIACAO_PROJETOS" + "_" + PLRUtils.today() + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		ByteArrayInputStream exported = service.export();
		
		return new ResponseEntity<>(new InputStreamResource(exported), headers, HttpStatus.OK);
	}
}
