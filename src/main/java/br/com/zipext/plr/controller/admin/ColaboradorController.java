package br.com.zipext.plr.controller.admin;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.ColaboradorDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.service.ColaboradorService;

@Controller
@RequestMapping("/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService service;
	
	@GetMapping("/filter")
	public ResponseEntity<List<ColaboradorDTO>> findByFilter(
			@RequestParam(required = false, name = "matricula") String matricula,
			@RequestParam(required = false, name = "nome") String nome,
			@RequestParam(required = false, name = "situacao") String situacao,
			@RequestParam(required = false, name = "cargo") String cargo,
			@RequestParam(required = false, name = "diretoria") String diretoria,
			@RequestParam(required = false, name = "time") String time) {
		List<ColaboradorDTO> dtos = this.service.findByFilter(StringUtils.isNotBlank(matricula) ? matricula : null,
				StringUtils.isNotBlank(nome) ? nome.toUpperCase() : null, StringUtils.isNotBlank(situacao) ? situacao : null,
				StringUtils.isNotBlank(cargo) ? cargo.toUpperCase() : null, StringUtils.isNotBlank(diretoria) ? diretoria.toUpperCase() : null,
				StringUtils.isNotBlank(time) ? time.toUpperCase() : null).stream().map(ColaboradorDTO::new)
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<ColaboradorDTO> findByMatricula(@PathVariable("matricula") String matricula) {
		return new ResponseEntity<ColaboradorDTO>(new ColaboradorDTO(this.service.findByMatricula(matricula)), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ColaboradorDTO> save(@RequestBody ColaboradorDTO dto) throws Exception {
		ColaboradorModel model = this.service.findByMatricula(dto.getMatricula());
		if (model != null && dto.isNewColaborador()) {
			throw new Exception("Já existe um colaborador cadastrado para a mesma matrícula! ");
		}
		
		ColaboradorModel resultModel = this.service.save(dto.obterModel());
		return new ResponseEntity<>(new ColaboradorDTO(resultModel), HttpStatus.OK);
	}
}
