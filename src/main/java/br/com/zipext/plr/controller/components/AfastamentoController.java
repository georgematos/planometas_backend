package br.com.zipext.plr.controller.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.AfastamentoDTO;
import br.com.zipext.plr.model.AfastamentoModel;
import br.com.zipext.plr.service.AfastamentoService;

@Controller
@RequestMapping("/afastamento")
public class AfastamentoController {
	
	@Autowired
	private AfastamentoService service;
	
	@PostMapping
	public ResponseEntity<AfastamentoDTO> save(@RequestBody AfastamentoDTO dto) {
		AfastamentoModel model = dto.obterModel();
		AfastamentoDTO resultDTO = new AfastamentoDTO(this.service.save(model));
		
		return
				new ResponseEntity<>(resultDTO, HttpStatus.OK);
	}
}
