package br.com.zipext.plr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.MetaEspecificaMensalDTO;
import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.dto.MetasResumoDTO;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.MetaEspecificaMensalModel;
import br.com.zipext.plr.service.ColaboradorMetaEspecificaService;
import br.com.zipext.plr.service.HistoricoMetaMensalService;
import br.com.zipext.plr.service.MetaEspecificaMensalService;

@RestController
@RequestMapping("/metaEspecifica")
public class MetaEspecificaController {

	@Autowired
	private ColaboradorMetaEspecificaService service;
	
	@Autowired
	private MetaEspecificaMensalService metaMensalService;
	
	@Autowired
	private HistoricoMetaMensalService historicoMetaMensalService;
	
	@GetMapping("/colaborador/{matricula}")
	public ResponseEntity<List<MetasResumoDTO>> findMetasCadastradasBy(@PathVariable("matricula") String matricula) {
		Set<MetasResumoDTO> results = this.service.findByResponsavel(matricula).stream()
				.map(MetasResumoDTO::new)
				.collect(Collectors.toSet());
		return new ResponseEntity<>(results.stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/colaborador/{matricula}/mensal")
	public ResponseEntity<List<MetaEspecificaMensalDTO>> 
		findMetasMensais(@PathVariable("matricula") String matricula, @RequestParam(name = "idMeta", required = true) Long idMeta, 
			@RequestParam(name = "sequencia", required = true) Integer sequencia, @RequestParam(name = "filterVersion", required = false) Long filterVersion) {
		List<MetaEspecificaMensalDTO> dtos = new ArrayList<>();
		if (filterVersion != null) {
			dtos = this.historicoMetaMensalService.findByFilter(idMeta, matricula, sequencia, filterVersion).stream().map(MetaEspecificaMensalDTO::new).collect(Collectors.toList());
		} else {
			dtos = this.metaMensalService.findByFilter(idMeta, matricula, sequencia).stream().map(MetaEspecificaMensalDTO::new).collect(Collectors.toList());
		}
		return new ResponseEntity<>(dtos.stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> save(@RequestBody MetasDTO dto, @PathVariable("matricula") String matricula) {
		ColaboradorMetaEspecificaModel model = dto.getMetasForColaborador(matricula);
		
		this.service.save(model);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/colaborador/{matricula}/mensal")
	public ResponseEntity<Void> 
		saveMetasMensais(@RequestBody List<MetaEspecificaMensalDTO> dtos) {
		
		List<MetaEspecificaMensalModel> models = new ArrayList<>();
		dtos.forEach(dto -> models.add(dto.obterModelFromDTO()));
		
		this.metaMensalService.saveAll(models);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> delete(@RequestBody MetasDTO dto, @PathVariable("matricula") String matricula) {
		ColaboradorMetaEspecificaModel model = dto.getMetasForColaborador(matricula);
		this.service.delete(model);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> update(@RequestBody MetasDTO dto, @PathVariable("matricula") String matricula) {
		ColaboradorMetaEspecificaModel model = dto.getMetasForColaborador(matricula);
		this.service.update(model);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
