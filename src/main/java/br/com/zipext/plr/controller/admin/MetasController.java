package br.com.zipext.plr.controller.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.service.MetasPeriodoService;
import br.com.zipext.plr.service.MetasService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/metas")
public class MetasController {

	@Autowired
	private MetasService service;
	
	@Autowired
	private MetasPeriodoService metasPeriodoService;
	
	@GetMapping("/export/{periodoPLR}")
	public ResponseEntity<InputStreamResource> exportIndicadores(@PathVariable("periodoPLR") String periodoPLR) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "INDICADORES" + "_" + periodoPLR + ".xlsx";
		
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		
		return new ResponseEntity<>(new InputStreamResource(this.service.export(
				new TempoModel(PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR)))), headers, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<MetasDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAllAtivasByOrderByDescricaoAsc().stream().map(MetasDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/{periodoPLR}")
	public ResponseEntity<List<MetasDTO>> findAllByPeriodo(@PathVariable("periodoPLR") String periodoPLR, @RequestParam(name = "page", required = false) Integer page) {
		List<MetasDTO> dtos = this.metasPeriodoService.findAllByPeriodo(PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR), page)
				.stream()
				.map(mp -> new MetasDTO(mp.getPk().getMetas()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/qualitativas")
	public ResponseEntity<List<MetasDTO>> findMetasQualitativas() {
		return 
				new ResponseEntity<>(this.service.findQualitativas().stream().map(m -> new MetasDTO(m)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/quantitativas")
	public ResponseEntity<List<MetasDTO>> findMetasQuantitativas() {
		return 
				new ResponseEntity<>(this.service.findQuantitativas().stream().map(m -> new MetasDTO(m)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/quantitativas/{periodoPLR}")
	public ResponseEntity<List<MetasDTO>> findMetasQuantitativasByPeriodo(@PathVariable("periodoPLR") String periodoPLR,
			@RequestParam(name = "page", required = false) Integer page) {
		List<MetasDTO> dtos = this.metasPeriodoService.findMetasQuantitativasByPeriodoAndSituacao(PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR),
						EnumSituacao.ATIVO.getCodigo().toString(), page)
				.stream().map(mp -> new MetasDTO(mp.getPk().getMetas())).collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/filter/{periodoPLR}")
	public ResponseEntity<List<MetasDTO>> findByFilter(
			@PathVariable("periodoPLR") Integer periodoPLR,
			@RequestParam(name = "idMeta", required = false) Long idMeta,
			@RequestParam(name = "codigoLegado", required = false) String codigoLegado,
			@RequestParam(name = "meta", required = false) String meta,
			@RequestParam(name = "situacao", required = false) String situacao,
			@RequestParam(name = "tipoMedicao", required = false) Long tipoMedicao,
			@RequestParam(name = "tipoMeta", required = false) Long tipoMeta,
			@RequestParam(name = "formula", required = false) Long formula,
			@RequestParam(name = "frequenciaMedicao", required = false) Long frequenciaMedicao) {

		List<MetasModel> models = this.service.findByFilter(idMeta != null ? new MetasModel(idMeta) : null, 
				StringUtils.isNotBlank(codigoLegado) ? codigoLegado.toUpperCase() : null,
				StringUtils.isNotBlank(meta) ? meta.toUpperCase() : null,
				StringUtils.isNotBlank(situacao) ? situacao.toUpperCase() : null,
				tipoMedicao != null ? new TipoMedicaoModel(tipoMedicao) : null,
				tipoMeta != null ? new TipoMetaModel(tipoMeta) : null,
				formula != null ? new FormulaModel(formula) : null, 
				frequenciaMedicao != null ? new FrequenciaMedicaoModel(frequenciaMedicao) : null);
		return new ResponseEntity<>(models.stream().map(model -> new MetasDTO(model, periodoPLR)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/projetos/{periodoPLR}")
	public ResponseEntity<List<MetasDTO>> findMetasProjetoVencidasByResponsavel(@PathVariable("periodoPLR") Integer periodoPLR, 
			@RequestParam(name = "aprovador", required = false) String aprovador) {
		Long skyDataLimite = PLRUtils.getSkyTempoFromStringDate(
				LocalDate.now().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)));
		List<MetasModel> models = this.service.findProjetosVencidosByResponsavel(periodoPLR, skyDataLimite, 
				StringUtils.isNotBlank(aprovador) ? new ColaboradorModel(aprovador) : null);
		return new ResponseEntity<>(models.stream().map(model -> new MetasDTO(model, periodoPLR)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MetasDTO> save(@RequestBody MetasDTO dto) throws Exception {
		MetasModel meta = this.service.save(dto.obterModel());
		return new ResponseEntity<MetasDTO>(new MetasDTO(meta), HttpStatus.OK);
	}
}
