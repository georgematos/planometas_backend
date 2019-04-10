package br.com.zipext.plr.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.HistoricoDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.service.ColaboradorService;
import br.com.zipext.plr.service.HistoricoService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoService service;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping("/{matricula}/export")
	public ResponseEntity<InputStreamResource> exportColaborador(@PathVariable("matricula") String matricula, @RequestParam Long version) throws IOException {
		ColaboradorModel model = this.colaboradorService.findByMatricula(matricula);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + model.getMatricula() + "_" + model.getNome() + "_" + PLRUtils.today() + ".xlsx");
		
		return new ResponseEntity<>(new InputStreamResource(this.service.export(model, version)), headers, HttpStatus.OK);
	}
	
	@GetMapping("/colaborador")
	public ResponseEntity<List<HistoricoDTO>> getHistoricoForColaborador(@RequestParam String matricula) {
		return new ResponseEntity<>(this.service.findHistoricoByMatricula(matricula).stream().map(HistoricoDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/responsavel")
	public ResponseEntity<List<HistoricoDTO>> getHistoricoForResponsavel(@RequestParam String matriculaResponsavel) {
		return new ResponseEntity<>(this.service.findByResponsavel(matriculaResponsavel).stream().map(HistoricoDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<HistoricoDTO> geraHistorico(@RequestBody HistoricoDTO dto) {
		return new ResponseEntity<>(new HistoricoDTO(this.service.geraHistorico(dto.obterModelFromDTO())), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Void> atualizaHistorico(@RequestBody HistoricoDTO dto) {
		this.service.update(dto.obterModelFromDTOSimples());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
