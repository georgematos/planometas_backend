package br.com.zipext.plr.controller.admin;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
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
import br.com.zipext.plr.dto.FolhaMetaItemDTO;
import br.com.zipext.plr.enums.EnumPerfil;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.ColaboradorService;
import br.com.zipext.plr.service.FolhaMetaItemService;
import br.com.zipext.plr.service.FolhaMetaService;
import br.com.zipext.plr.service.MetasService;
import br.com.zipext.plr.service.PerfilUsuarioService;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.PaginationJsGridObject;

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
			@RequestParam(name = "situacao", required = false) String situacao,
			@RequestParam(name = "superiorImediato", required = false) String superiorImediato,
			@RequestParam(name = "filial", required = false) Long filial,
			@RequestParam(name = "time", required = false) String time,
			@RequestParam(name = "diretoria", required = false) Long diretoria) {

		List<FolhaMetaDTO> dtos = this.service
				.findByFilter(
						StringUtils.isNotBlank(matricula) ? matricula.toUpperCase() : null,
						StringUtils.isNotBlank(cargo) ? cargo.toUpperCase() : null,
						StringUtils.isNotBlank(inicioVigencia) ? PLRUtils.getSkyTempoFromStringDate(inicioVigencia) : null,
						StringUtils.isNotBlank(fimVigencia) ? PLRUtils.getSkyTempoFromStringDate(fimVigencia) : null,
						StringUtils.isNotBlank(colaborador) ? colaborador.toUpperCase() : null,
						StringUtils.isNotBlank(responsavel) ? responsavel.toUpperCase() : null,
						StringUtils.isNotBlank(situacao) ? situacao : null,
						StringUtils.isNotBlank(superiorImediato) ? superiorImediato : null, 
						filial,
						StringUtils.isNotBlank(time) ? time : null, 
						diretoria)
				.stream().map(FolhaMetaDTO::new).collect(Collectors.toList());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("/colaborador/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<List<FolhaMetaDTO>> findByColaborador(@PathVariable("matricula") String matricula,
			@PathVariable("periodoPLR") Long periodoPLR) {
		List<FolhaMetaDTO> dtos = this.service
				.findByColaboradorAndVigencia(new ColaboradorModel(matricula),
						PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()),
						PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()))
				.stream().map(FolhaMetaDTO::new).collect(Collectors.toList());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("/responsavel/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<PaginationJsGridObject<FolhaMetaDTO>> findByResponsavel(
			@PathVariable("matricula") String matricula,
			@PathVariable("periodoPLR") Long periodoPLR,
			@RequestParam(value ="folha", required = false) Long folha,
			@RequestParam(value ="colaborador", required = false) String colaborador,
			@RequestParam(value ="cargo", required = false) String cargo,
			@RequestParam(value ="situacao", required = false) String situacao,
			@RequestParam(value ="inicioVigencia", required = false) String inicioVigencia,
			@RequestParam(value ="fimVigencia", required = false) String fimVigencia,
			@RequestParam(value ="responsavel", required = false) String responsavel,
			@RequestParam(value ="pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value ="pageSize", required = false, defaultValue = "10") int pageSize
	) {
		
		PerfilUsuarioModel perfilUsuario = this.perfilUsuarioService.findByUsuario(new UsuarioModel(matricula));
		ColaboradorModel filtroColaborador = new ColaboradorModel(matricula);

		if (perfilUsuario.getPk().getPerfil().getId().equals(EnumPerfil.ADMIN.getId())) {
			filtroColaborador = null;
		}

		Page<FolhaMetaDTO> dtos = this.service.findByResponsavelAndVigencia(
			folha,
			StringUtils.isNotBlank(colaborador) ? colaborador.toUpperCase() : null,
			StringUtils.isNotBlank(cargo) ? cargo.toUpperCase() : null,
			StringUtils.isNotBlank(situacao) ? situacao : null,
			StringUtils.isNotBlank(inicioVigencia) ? PLRUtils.getSkyTempoFromStringDate(inicioVigencia) : PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()),
			StringUtils.isNotBlank(fimVigencia) ? PLRUtils.getSkyTempoFromStringDate(fimVigencia) : PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()),
			filtroColaborador,
			pageIndex - 1,
			pageSize
				);

		PaginationJsGridObject<FolhaMetaDTO> po = new PaginationJsGridObject<>(dtos.getContent(), dtos.getTotalElements());

		return ResponseEntity.ok().body(po);
	}
	
	@GetMapping("/porindicador/responsavel/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<List<FolhaMetaItemDTO>> findByMetaAndPeriodo(@PathVariable("periodoPLR") Long periodoPLR, @RequestParam Long indicadorId) {

		List<FolhaMetaItemModel> folhas = folhaMetaItemService.findByMetaAndPeriodo(
				PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()),
				PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()),
				indicadorId);
		
		List<FolhaMetaItemDTO> dtos = folhas.stream().map(FolhaMetaItemDTO::new).collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("/pendentes/colaborador/{matricula}/periodo/{periodoPLR}")
	public ResponseEntity<PaginationJsGridObject<FolhaMetaDTO>> findPendentes(
			@PathVariable("matricula") String matricula,
			@PathVariable("periodoPLR") Long periodoPLR,
			@RequestParam(name = "folha", required = false) Long folha,
			@RequestParam(name = "colaborador", required = false) String colaborador,
			@RequestParam(name = "cargo", required = false) String cargo,
			@RequestParam(name = "situacao", required = false) String situacao,
			@RequestParam(name = "inicioVigencia", required = false) String inicioVigencia,
			@RequestParam(name = "fimVigencia", required = false) String fimVigencia,
			@RequestParam(name = "responsavel", required = false) String responsavel,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

		PerfilUsuarioModel perfilUsuario = this.perfilUsuarioService.findByUsuario(new UsuarioModel(matricula));
		Page<FolhaMetaDTO> dtos;
		if (perfilUsuario.getPk().getPerfil().getId().equals(EnumPerfil.ADMIN.getId())) {

			dtos = this.service.findAllPendentesByVigencia(
					folha,
					StringUtils.isNotBlank(colaborador) ? colaborador.toUpperCase() : null,
					StringUtils.isNotBlank(cargo) ? cargo.toUpperCase() : null,
					StringUtils.isNotBlank(situacao) ? situacao : null,
					StringUtils.isNotBlank(inicioVigencia) ? PLRUtils.getSkyTempoFromStringDate(inicioVigencia) : PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()),
					StringUtils.isNotBlank(fimVigencia) ? PLRUtils.getSkyTempoFromStringDate(fimVigencia) : PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()),
					null,
					pageIndex - 1,
					pageSize);
			
			PaginationJsGridObject<FolhaMetaDTO> po = new PaginationJsGridObject<>(dtos.getContent(), dtos.getTotalElements());

			return ResponseEntity.ok().body(po);
					
		} else {
			dtos = this.service.findPendentesByColaboradorAndVigencia(new ColaboradorModel(matricula),
							PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString()),
							PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString()),
							pageIndex - 1, pageSize);
			
			PaginationJsGridObject<FolhaMetaDTO> po = new PaginationJsGridObject<>(dtos.getContent(), dtos.getTotalElements());

			return ResponseEntity.ok().body(po);
		}

//		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportFolha(@RequestParam("matricula") String matricula,
			@RequestParam Long idFolhaMeta) throws IOException {
		ColaboradorModel model = this.colaboradorService.findByMatricula(matricula);

		HttpHeaders headers = new HttpHeaders();
		String fileName = model.getMatricula() + "_" + model.getNome() + "_" + PLRUtils.today() + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		return new ResponseEntity<>(new InputStreamResource(this.service.export(idFolhaMeta)), headers, HttpStatus.OK);
	}
	
	@GetMapping("/exportconsulta")
	public ResponseEntity<InputStreamResource> exportConsultaFolha(
			@RequestParam("usuario") String usuario,
			@RequestParam("periodo") String periodo,
			@RequestParam Long indicadorId) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		Long inicioVigencia = PLRUtils.getSkyTempoFromStringDate("01/01/" + periodo.toString());
		Long fimVigencia = PLRUtils.getSkyTempoFromStringDate("31/12/" + periodo.toString());
		String fileName = "USUARIO_"+ usuario + "_" + PLRUtils.today() + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		InputStreamResource isr = new InputStreamResource(this.folhaMetaItemService.exportConsultas(inicioVigencia, fimVigencia, indicadorId));

		return new ResponseEntity<>(isr, headers, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FolhaMetaDTO> save(@RequestBody FolhaMetaDTO dto) throws Exception {
		
		if (dto.getId() != null) {
			this.folhaMetaItemService.deleteByIdFolhaMeta(dto.getId());
		}
				
		verificarIndicadoresDuplicados(dto);

		FolhaMetaModel obtainedModel = dto.obterModel();
		ColaboradorModel colaborador = colaboradorService.findByMatricula(dto.getColaborador().getMatricula());

		obtainedModel.setSuperiorImediato(colaborador.getSuperiorImediato());
		obtainedModel.setDiretoria(colaborador.getDiretoria());
		obtainedModel.setFilial(colaborador.getFilial());
		obtainedModel.setTime(colaborador.getTime());
		
		FolhaMetaModel model = this.service.save(obtainedModel);
 		model.setFolhaMetaItems(this.folhaMetaItemService.saveAll(dto.obterFolhaMetaItems(model)));
		model.getFolhaMetaItems().forEach(item -> item.setMeta(this.metasService.findById(item.getMeta().getId())));

		return new ResponseEntity<>(new FolhaMetaDTO(model), HttpStatus.OK);
	}

	private void verificarIndicadoresDuplicados(FolhaMetaDTO dto) throws Exception {
		List<Long> indicadores = dto.getFolhasMetaItem().stream().map(x -> x.getMeta().getId()).collect(Collectors.toList());
		if (dto.getId() == null) {
			List<Long> duplicates= findDuplicates(indicadores).stream().collect(Collectors.toList());
			if (duplicates.size() > 0) {
				throw new Exception("Não pode haver dois indicadores para uma mesma Folha de Metas");
			}
		}
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
	
	public static Set<Long> findDuplicates(List<Long> listContainingDuplicates) {
		 
		final Set<Long> setToReturn = new HashSet<Long>();
		final Set<Long> set = new HashSet<Long>();
 
		for (Long item : listContainingDuplicates) {
			if (!set.add(item)) {
				setToReturn.add(item);
			}
		}
		return setToReturn;
	}
	
	public static Set<Long> getNotDuplicates(List<Long> listContainingDuplicates) {
		 
		final Set<Long> setToReturn = new HashSet<Long>();
		final Set<Long> set = new HashSet<Long>();
 
		for (Long item : listContainingDuplicates) {
			if (set.add(item)) {
				setToReturn.add(item);
			}
		}
		return setToReturn;
	}

}
