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

import br.com.zipext.plr.dto.FolhaMetaDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.service.FolhaMetaItemService;
import br.com.zipext.plr.service.FolhaMetaService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/folhametas")
public class FolhaMetaController {

	@Autowired
	private FolhaMetaService service;
	
	@Autowired
	private FolhaMetaItemService folhaMetaItemService;
	
	@GetMapping("/filter")
	public ResponseEntity<List<FolhaMetaDTO>> findByFilter(
			@RequestParam(name = "matricula", required = false) String matricula,
			@RequestParam(name = "inicioVigencia", required = false) String inicioVigencia, 
			@RequestParam(name = "fimVigencia", required = false) String fimVigencia, 
			@RequestParam(name = "colaborador", required = false) String colaborador, 
			@RequestParam(name = "responsavel", required = false) String responsavel, 
			@RequestParam(name = "situacao", required = false) String situacao) {
		
		List<FolhaMetaDTO> dtos = this.service.findByFilter(
				StringUtils.isNotBlank(matricula) ? matricula.toUpperCase() : null, 
				StringUtils.isNotBlank(inicioVigencia) ? PLRUtils.getSkyTempoFromStringDate(inicioVigencia) : null,
				StringUtils.isNotBlank(fimVigencia) ? PLRUtils.getSkyTempoFromStringDate(fimVigencia) : null, 
				StringUtils.isNotBlank(colaborador) ? colaborador.toUpperCase() : null, 
				StringUtils.isNotBlank(responsavel) ? responsavel.toUpperCase() : null, situacao)
					.stream()
					.map(FolhaMetaDTO::new)
					.collect(Collectors.toList());
		
		return new ResponseEntity<List<FolhaMetaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/colaborador/{matricula}")
	public ResponseEntity<List<FolhaMetaDTO>> findByColaborador(@PathVariable("matricula") String matricula) {
		List<FolhaMetaDTO> dtos = this.service.findByColaborador(new ColaboradorModel(matricula)).stream()
				.map(FolhaMetaDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<FolhaMetaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/responsavel/{matricula}")
	public ResponseEntity<List<FolhaMetaDTO>> findByResponsavel(@PathVariable("matricula") String matricula) {
		List<FolhaMetaDTO> dtos = this.service.findByResponsavel(new ColaboradorModel(matricula)).stream()
				.map(FolhaMetaDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<FolhaMetaDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<FolhaMetaDTO> save(@RequestBody FolhaMetaDTO dto) {
		if (dto.getId() != null) {
			this.folhaMetaItemService.deleteByIdFolhaMeta(dto.getId());
		}
	
		FolhaMetaModel model = this.service.save(dto.obterModel());
		model.setFolhaMetaItems(this.folhaMetaItemService.saveAll(dto.obterFolhaMetaItems(model)));

		return new ResponseEntity<FolhaMetaDTO>(new FolhaMetaDTO(model), HttpStatus.OK);
	}
}
