package br.com.zipext.plr.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.FolhaMetaDTO;
import br.com.zipext.plr.enums.EnumPerfil;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.ColaboradorService;
import br.com.zipext.plr.service.FolhaMetaItemService;
import br.com.zipext.plr.service.FolhaMetaService;
import br.com.zipext.plr.service.MetasService;
import br.com.zipext.plr.service.PerfilUsuarioService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/folhametas")
public class FolhaMetaController {

	@Autowired
	private FolhaMetaService service;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private FolhaMetaItemService folhaMetaItemService;
	
	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@Autowired
	private MetasService metasService;
	
	@GetMapping("/filter")
	public ResponseEntity<List<FolhaMetaDTO>> findByFilter(
			@RequestParam(name = "matricula", required = false) String matricula,
			@RequestParam(name = "cargo", required = false) String cargo,
			@RequestParam(name = "inicioVigencia", required = false) String inicioVigencia, 
			@RequestParam(name = "fimVigencia", required = false) String fimVigencia, 
			@RequestParam(name = "colaborador", required = false) String colaborador, 
			@RequestParam(name = "responsavel", required = false) String responsavel, 
			@RequestParam(name = "situacao", required = false) String situacao) {
		
		List<FolhaMetaDTO> dtos = this.service.findByFilter(
				StringUtils.isNotBlank(matricula) ? matricula.toUpperCase() : null, 
				StringUtils.isNotBlank(cargo) ? cargo.toUpperCase() : null, 
				StringUtils.isNotBlank(inicioVigencia) ? PLRUtils.getSkyTempoFromStringDate(inicioVigencia) : null,
				StringUtils.isNotBlank(fimVigencia) ? PLRUtils.getSkyTempoFromStringDate(fimVigencia) : null, 
				StringUtils.isNotBlank(colaborador) ? colaborador.toUpperCase() : null, 
				StringUtils.isNotBlank(responsavel) ? responsavel.toUpperCase() : null, 
				StringUtils.isNotBlank(situacao) ? situacao : null)
					.stream()
					.map(FolhaMetaDTO::new)
					.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/colaborador/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<List<FolhaMetaDTO>> findByColaborador(@PathVariable("matricula") String matricula, @PathVariable("periodoPLR") Long periodoPLR) {
		List<FolhaMetaDTO> dtos = this.service.findByColaboradorAndVigencia(new ColaboradorModel(matricula),  PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()), 
				PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()))
				.stream()
				.map(FolhaMetaDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/responsavel/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<List<FolhaMetaDTO>> findByResponsavel(@PathVariable("matricula") String matricula, @PathVariable("periodoPLR") Long periodoPLR) {
		PerfilUsuarioModel perfilUsuario = this.perfilUsuarioService.findByUsuario(new UsuarioModel(matricula));
		ColaboradorModel filtroColaborador = new ColaboradorModel(matricula); 
		if (perfilUsuario.getPk().getPerfil().getId().equals(EnumPerfil.ADMIN.getId())) {
			filtroColaborador = null;
		}
		List<FolhaMetaDTO> dtos = this.service.findByResponsavelAndVigencia(filtroColaborador, 
				PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()), 
				PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()))
					.stream()
					.map(FolhaMetaDTO::new)
					.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/pendentes/colaborador/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<List<FolhaMetaDTO>> findPendentes(@PathVariable("matricula") String matricula, @PathVariable("periodoPLR") Long periodoPLR) {
		PerfilUsuarioModel perfilUsuario = this.perfilUsuarioService.findByUsuario(new UsuarioModel(matricula));
		List<FolhaMetaDTO> dtos;
		if (perfilUsuario.getPk().getPerfil().getId().equals(EnumPerfil.ADMIN.getId())) {
			dtos = this.service.findAllPendentesByVigencia(PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()), 
					PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()))
						.stream()
						.map(FolhaMetaDTO::new)
						.collect(Collectors.toList());
		} else {
			dtos = this.service.findPendentesByColaboradorAndVigencia(new ColaboradorModel(matricula), 
					PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()), 
					PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()))
						.stream()
						.map(FolhaMetaDTO::new)
						.collect(Collectors.toList());			
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportFolha(@RequestParam("matricula") String matricula, @RequestParam Long idFolhaMeta) throws IOException {
		ColaboradorModel model = this.colaboradorService.findByMatricula(matricula);

		HttpHeaders headers = new HttpHeaders();
		String fileName = model.getMatricula() + "_" + model.getNome() + "_" + PLRUtils.today() + ".xlsx";
		
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		
		return new ResponseEntity<>(new InputStreamResource(this.service.export(idFolhaMeta)), headers, HttpStatus.OK);
	}
	
	@PostMapping	
	public ResponseEntity<FolhaMetaDTO> save(@RequestBody FolhaMetaDTO dto) {
		if (dto.getId() != null) {
			this.folhaMetaItemService.deleteByIdFolhaMeta(dto.getId());
		}
	
		FolhaMetaModel model = this.service.save(dto.obterModel());
		model.setFolhaMetaItems(this.folhaMetaItemService.saveAll(dto.obterFolhaMetaItems(model)));
		model.getFolhaMetaItems().forEach(item -> item.setMeta(this.metasService.findById(item.getMeta().getId())));
		
		return new ResponseEntity<>(new FolhaMetaDTO(model), HttpStatus.OK);
	}
	
	@PutMapping("/aprovacao/{id}")
	public ResponseEntity<Void> aprovarFolhaMeta(@PathVariable Long id) {
		this.service.updateSituacaoById(id, EnumSituacao.ATIVO.getCodigo().toString());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFolhaMeta(@PathVariable Long id) {
		this.service.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
