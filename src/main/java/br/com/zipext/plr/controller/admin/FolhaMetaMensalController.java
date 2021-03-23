package br.com.zipext.plr.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.FolhaMetaMensalDTO;
import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK;
import br.com.zipext.plr.service.FolhaMetaMensalService;
import br.com.zipext.plr.service.MetasService;
import br.com.zipext.plr.service.RelAvaliacaoProjetoService;
import br.com.zipext.plr.utils.PLRUtils;

@Controller
@RequestMapping("/folhasmensais")
public class FolhaMetaMensalController {

	@Autowired
	private FolhaMetaMensalService service;
	
	@Autowired
	private MetasService metasService;
	
	@Autowired
	private RelAvaliacaoProjetoService serviceAval;
	
	class ResponseMetasMensaisDTO {
		
		private List<FolhaMetaMensalDTO> dtosIndicador = new ArrayList<>();
		private List<FolhaMetaMensalDTO> dtosPlanejados = new ArrayList<>();
		private List<FolhaMetaMensalDTO> dtosRealizados = new ArrayList<>();
		private Long MetaNumeradorId;
		private String metaNumeradorDescricao;
		private Long MetaDenominadorId;
		private String metaDenominadorDescricao;
		private MetasDTO indicador;
		public List<FolhaMetaMensalDTO> getDtosIndicador() {
			return dtosIndicador;
		}
		public void setDtosIndicador(List<FolhaMetaMensalDTO> dtosIndicador) {
			this.dtosIndicador = dtosIndicador;
		}
		public List<FolhaMetaMensalDTO> getDtosPlanejados() {
			return dtosPlanejados;
		}
		public void setDtosPlanejados(List<FolhaMetaMensalDTO> dtosPlanejados) {
			this.dtosPlanejados = dtosPlanejados;
		}
		public List<FolhaMetaMensalDTO> getDtosRealizados() {
			return dtosRealizados;
		}
		public void setDtosRealizados(List<FolhaMetaMensalDTO> dtosRealizados) {
			this.dtosRealizados = dtosRealizados;
		}
		public String getMetaNumeradorDescricao() {
			return metaNumeradorDescricao;
		}
		public void setMetaNumeradorDescricao(String metaNumeradorDescricao) {
			this.metaNumeradorDescricao = metaNumeradorDescricao;
		}
		public String getMetaDenominadorDescricao() {
			return metaDenominadorDescricao;
		}
		public void setMetaDenominadorDescricao(String metaDenominadorDescricao) {
			this.metaDenominadorDescricao = metaDenominadorDescricao;
		}
		public Long getMetaNumeradorId() {
			return MetaNumeradorId;
		}
		public void setMetaNumeradorId(Long metaNumeradorId) {
			MetaNumeradorId = metaNumeradorId;
		}
		public Long getMetaDenominadorId() {
			return MetaDenominadorId;
		}
		public void setMetaDenominadorId(Long metaDenominadorId) {
			MetaDenominadorId = metaDenominadorId;
		}
		public MetasDTO getIndicador() {
			return indicador;
		}
		public void setIndicador(MetasDTO indicador) {
			this.indicador = indicador;
		}

	}
	
	class ResponseMetaSalvaDTO {
		
		private MetasDTO meta;
		private List<FolhaMetaMensalDTO> results = new ArrayList<>();

		public List<FolhaMetaMensalDTO> getResults() {
			return results;
		}

		public void setResults(List<FolhaMetaMensalDTO> results) {
			this.results = results;
		}

		public MetasDTO getMeta() {
			return meta;
		}

		public void setMeta(MetasDTO meta) {
			this.meta = meta;
		}

	}
	
	@GetMapping("/meta/{idMeta}/periodoPLR/{periodoPLR}/colaborador/{matricula}")
	public ResponseEntity<ResponseMetasMensaisDTO> findByMeta(
			@PathVariable("idMeta") Long idMeta, 
			@PathVariable("periodoPLR") Integer periodoPLR,
			@PathVariable("matricula") String matricula) {
		
		ResponseMetasMensaisDTO responseMetasMensaisDTO = new ResponseMetasMensaisDTO();
		List<FolhaMetaMensalModel> metasMensaisDoIndicador = this.service.findByMetaColaboradorAndAno(new MetasModel(idMeta), new ColaboradorModel(matricula), periodoPLR);
		List<FolhaMetaMensalDTO> dtosIndicador = new ArrayList<>();
		
		if (metasMensaisDoIndicador != null && !metasMensaisDoIndicador.isEmpty()) {
			dtosIndicador.add(new FolhaMetaMensalDTO("META", null, null, metasMensaisDoIndicador, false));
			dtosIndicador.add(new FolhaMetaMensalDTO("REAL", null, null, metasMensaisDoIndicador, false));
		}
		
		MetasModel indicador = metasService.findById(idMeta);

		if (indicador.getFormula().getId() == 2 && indicador.getMetaNumerador() != null && indicador.getMetaDenominador() != null) {
			
			responseMetasMensaisDTO.setMetaNumeradorId(indicador.getMetaNumerador().getId());
			responseMetasMensaisDTO.setMetaNumeradorDescricao(indicador.getMetaNumerador().getDescricao());
			responseMetasMensaisDTO.setMetaDenominadorId(indicador.getMetaDenominador().getId());
			responseMetasMensaisDTO.setMetaDenominadorDescricao(indicador.getMetaDenominador().getDescricao());
			
			List<FolhaMetaMensalModel> metasMensaisNumerador = service.findByMetaColaboradorAndAno(indicador.getMetaNumerador(), new ColaboradorModel(matricula), periodoPLR);
			List<FolhaMetaMensalModel> metasMensaisDenominador = service.findByMetaColaboradorAndAno(indicador.getMetaDenominador(), new ColaboradorModel(matricula), periodoPLR);

			List<FolhaMetaMensalDTO> dtosPlanejados = new ArrayList<>();
			List<FolhaMetaMensalDTO> dtosRealizados = new ArrayList<>();

			if (metasMensaisNumerador != null && !metasMensaisNumerador.isEmpty()) {
				dtosPlanejados.add(new FolhaMetaMensalDTO("META", null, null, metasMensaisNumerador, false));
				dtosPlanejados.add(new FolhaMetaMensalDTO("META", null, null, metasMensaisDenominador, false));

				FolhaMetaMensalDTO metaMensalPlanejadaNumeradorDto = dtosPlanejados.get(0);
				FolhaMetaMensalDTO metaMensalPlanejadaDenominadorDto = dtosPlanejados.get(1);

				BigDecimal planJan = metaMensalPlanejadaNumeradorDto.getValJan() != null && metaMensalPlanejadaDenominadorDto.getValJan() != null  && metaMensalPlanejadaDenominadorDto.getValJan() != null? metaMensalPlanejadaNumeradorDto.getValJan().divide(metaMensalPlanejadaDenominadorDto.getValJan(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planFev = metaMensalPlanejadaNumeradorDto.getValFev() != null && metaMensalPlanejadaDenominadorDto.getValFev() != null ? metaMensalPlanejadaNumeradorDto.getValFev().divide(metaMensalPlanejadaDenominadorDto.getValFev(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planMar = metaMensalPlanejadaNumeradorDto.getValMar() != null && metaMensalPlanejadaDenominadorDto.getValMar() != null ? metaMensalPlanejadaNumeradorDto.getValMar().divide(metaMensalPlanejadaDenominadorDto.getValMar(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planAbr = metaMensalPlanejadaNumeradorDto.getValAbr() != null && metaMensalPlanejadaDenominadorDto.getValAbr() != null ? metaMensalPlanejadaNumeradorDto.getValAbr().divide(metaMensalPlanejadaDenominadorDto.getValAbr(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planMai = metaMensalPlanejadaNumeradorDto.getValMai() != null && metaMensalPlanejadaDenominadorDto.getValMai() != null ? metaMensalPlanejadaNumeradorDto.getValMai().divide(metaMensalPlanejadaDenominadorDto.getValMai(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planJun = metaMensalPlanejadaNumeradorDto.getValJun() != null && metaMensalPlanejadaDenominadorDto.getValJun() != null ? metaMensalPlanejadaNumeradorDto.getValJun().divide(metaMensalPlanejadaDenominadorDto.getValJun(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planJul = metaMensalPlanejadaNumeradorDto.getValJul() != null && metaMensalPlanejadaDenominadorDto.getValJul() != null ? metaMensalPlanejadaNumeradorDto.getValJul().divide(metaMensalPlanejadaDenominadorDto.getValJul(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planAgo = metaMensalPlanejadaNumeradorDto.getValAgo() != null && metaMensalPlanejadaDenominadorDto.getValAgo() != null ? metaMensalPlanejadaNumeradorDto.getValAgo().divide(metaMensalPlanejadaDenominadorDto.getValAgo(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planSet = metaMensalPlanejadaNumeradorDto.getValSet() != null && metaMensalPlanejadaDenominadorDto.getValSet() != null ? metaMensalPlanejadaNumeradorDto.getValSet().divide(metaMensalPlanejadaDenominadorDto.getValSet(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planOut = metaMensalPlanejadaNumeradorDto.getValOut() != null && metaMensalPlanejadaDenominadorDto.getValOut() != null ? metaMensalPlanejadaNumeradorDto.getValOut().divide(metaMensalPlanejadaDenominadorDto.getValOut(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planNov = metaMensalPlanejadaNumeradorDto.getValNov() != null && metaMensalPlanejadaDenominadorDto.getValNov() != null ? metaMensalPlanejadaNumeradorDto.getValNov().divide(metaMensalPlanejadaDenominadorDto.getValNov(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal planDez = metaMensalPlanejadaNumeradorDto.getValDez() != null && metaMensalPlanejadaDenominadorDto.getValDez() != null ? metaMensalPlanejadaNumeradorDto.getValDez().divide(metaMensalPlanejadaDenominadorDto.getValDez(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				
				dtosIndicador.get(0).setValJan(planJan);
				dtosIndicador.get(0).setValFev(planFev);
				dtosIndicador.get(0).setValMar(planMar);
				dtosIndicador.get(0).setValAbr(planAbr);
				dtosIndicador.get(0).setValMai(planMai);
				dtosIndicador.get(0).setValJun(planJun);
				dtosIndicador.get(0).setValJul(planJul);
				dtosIndicador.get(0).setValAgo(planAgo);
				dtosIndicador.get(0).setValSet(planSet);
				dtosIndicador.get(0).setValOut(planOut);
				dtosIndicador.get(0).setValNov(planNov);
				dtosIndicador.get(0).setValDez(planDez);
			}

			if (metasMensaisDenominador != null && !metasMensaisDenominador.isEmpty()) {
				dtosRealizados.add(new FolhaMetaMensalDTO("REAL", null, null, metasMensaisNumerador, false));
				dtosRealizados.add(new FolhaMetaMensalDTO("REAL", null, null, metasMensaisDenominador, false));

				FolhaMetaMensalDTO metaMensalRealizadaNumeradorDto = dtosRealizados.get(0);
				FolhaMetaMensalDTO metaMensalRealizadaDenominadorDto = dtosRealizados.get(1);

				BigDecimal realJan = metaMensalRealizadaNumeradorDto.getValJan() != null ? metaMensalRealizadaNumeradorDto.getValJan().divide(metaMensalRealizadaDenominadorDto.getValJan(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realFev = metaMensalRealizadaNumeradorDto.getValFev() != null ? metaMensalRealizadaNumeradorDto.getValFev().divide(metaMensalRealizadaDenominadorDto.getValFev(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realMar = metaMensalRealizadaNumeradorDto.getValMar() != null ? metaMensalRealizadaNumeradorDto.getValMar().divide(metaMensalRealizadaDenominadorDto.getValMar(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realAbr = metaMensalRealizadaNumeradorDto.getValAbr() != null ? metaMensalRealizadaNumeradorDto.getValAbr().divide(metaMensalRealizadaDenominadorDto.getValAbr(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realMai = metaMensalRealizadaNumeradorDto.getValMai() != null ? metaMensalRealizadaNumeradorDto.getValMai().divide(metaMensalRealizadaDenominadorDto.getValMai(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realJun = metaMensalRealizadaNumeradorDto.getValJun() != null ? metaMensalRealizadaNumeradorDto.getValJun().divide(metaMensalRealizadaDenominadorDto.getValJun(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realJul = metaMensalRealizadaNumeradorDto.getValJul() != null ? metaMensalRealizadaNumeradorDto.getValJul().divide(metaMensalRealizadaDenominadorDto.getValJul(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realAgo = metaMensalRealizadaNumeradorDto.getValAgo() != null ? metaMensalRealizadaNumeradorDto.getValAgo().divide(metaMensalRealizadaDenominadorDto.getValAgo(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realSet = metaMensalRealizadaNumeradorDto.getValSet() != null ? metaMensalRealizadaNumeradorDto.getValSet().divide(metaMensalRealizadaDenominadorDto.getValSet(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realOut = metaMensalRealizadaNumeradorDto.getValOut() != null ? metaMensalRealizadaNumeradorDto.getValOut().divide(metaMensalRealizadaDenominadorDto.getValOut(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realNov = metaMensalRealizadaNumeradorDto.getValNov() != null ? metaMensalRealizadaNumeradorDto.getValNov().divide(metaMensalRealizadaDenominadorDto.getValNov(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				BigDecimal realDez = metaMensalRealizadaNumeradorDto.getValDez() != null ? metaMensalRealizadaNumeradorDto.getValDez().divide(metaMensalRealizadaDenominadorDto.getValDez(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) : new BigDecimal("0");
				
				dtosIndicador.get(1).setValJan(realJan);
				dtosIndicador.get(1).setValFev(realFev);
				dtosIndicador.get(1).setValMar(realMar);
				dtosIndicador.get(1).setValAbr(realAbr);
				dtosIndicador.get(1).setValMai(realMai);
				dtosIndicador.get(1).setValJun(realJun);
				dtosIndicador.get(1).setValJul(realJul);
				dtosIndicador.get(1).setValAgo(realAgo);
				dtosIndicador.get(1).setValSet(realSet);
				dtosIndicador.get(1).setValOut(realOut);
				dtosIndicador.get(1).setValNov(realNov);
				dtosIndicador.get(1).setValDez(realDez);
			}

			responseMetasMensaisDTO.setDtosPlanejados(dtosPlanejados);
			responseMetasMensaisDTO.setDtosRealizados(dtosRealizados);
			responseMetasMensaisDTO.setDtosIndicador(dtosIndicador);
			
			return ResponseEntity.ok().body(responseMetasMensaisDTO);
		}

		responseMetasMensaisDTO.setDtosIndicador(dtosIndicador);

		return ResponseEntity.ok().body(responseMetasMensaisDTO);
	}
	
	@PostMapping("/meta/{idMeta}/periodoPLR/{periodoPLR}/colaborador/{matricula}")
	public ResponseEntity<ResponseMetaSalvaDTO> save(@RequestBody List<FolhaMetaMensalDTO> dtos,
			@PathVariable("idMeta") Long idMeta, @PathVariable("periodoPLR") Integer periodoPLR,
			@PathVariable("matricula") String matricula) {
		List<FolhaMetaMensalModel> modelsToSave = dtos.stream().map(dto -> dto.obterModel()).collect(Collectors.toList());
		this.service.deleteByMetaColaboradorAndAno(new MetasModel(idMeta), new ColaboradorModel(matricula), periodoPLR);
		
		if(dtos.size() == 1) { // Tipo ENTREGA ou PROJETO quando a origem é avaliação.
			FolhaMetaMensalDTO dto = dtos.get(0);

			if (metasService.findById(dto.getIdMeta()).getTipoMeta().getDescricao().equals("ENTREGA")
				|| metasService.findById(dto.getIdMeta()).getTipoMeta().getDescricao().equals("PROJETO")
			) {
				RelAvaliacaoProjetoPK pk = serviceAval.getPKByDTO(dto.getAvaliacaoProjeto());
				RelAvaliacaoProjetoModel avalProjModel = serviceAval.findById(pk);
				
				modelsToSave.get(0).setValorReal(avalProjModel.getValEscalonamento());
			};
		}
		
		List<FolhaMetaMensalModel> models = this.service.saveAll(modelsToSave);
		List<FolhaMetaMensalDTO> results = new ArrayList<>();
		if (models != null && !models.isEmpty()) {
			models.forEach(m -> results.add(new FolhaMetaMensalDTO(m)));
		}
		
		ResponseMetaSalvaDTO metaSalvaDTO = new ResponseMetaSalvaDTO(); 
		MetasDTO meta = new MetasDTO(metasService.findById(idMeta), periodoPLR);
		
		metaSalvaDTO.setResults(results);
		metaSalvaDTO.setMeta(meta);
		
		return ResponseEntity.ok().body(metaSalvaDTO);
	}
	
	@PostMapping("/meta/mediaponderada/{idMeta}/periodoPLR/{periodoPLR}/colaborador/{matricula}")
	public ResponseEntity<ResponseMetasMensaisDTO> saveWithMediaPonderada(@RequestBody List<List<FolhaMetaMensalDTO>> dtos,
			@PathVariable("idMeta") Long idMeta, @PathVariable("periodoPLR") Integer periodoPLR,
			@PathVariable("matricula") String matricula) {
		
		ResponseMetasMensaisDTO responseMetasMensaisDTO = new ResponseMetasMensaisDTO();
		
		MetasModel indicador = metasService.findById(idMeta);
		MetasModel numerador = metasService.findById(indicador.getMetaNumerador().getId());
		MetasModel denominador = metasService.findById(indicador.getMetaDenominador().getId());
		
		responseMetasMensaisDTO.setMetaNumeradorId(indicador.getMetaNumerador().getId());
		responseMetasMensaisDTO.setMetaNumeradorDescricao(indicador.getMetaNumerador().getDescricao());
		responseMetasMensaisDTO.setMetaDenominadorId(indicador.getMetaDenominador().getId());
		responseMetasMensaisDTO.setMetaDenominadorDescricao(indicador.getMetaDenominador().getDescricao());
		
		List<FolhaMetaMensalModel> modelsIndicador = dtos.get(0).stream().map(dto -> dto.obterModel()).collect(Collectors.toList());
		List<FolhaMetaMensalModel> modelsNumerador = dtos.get(1).stream().map(dto -> dto.obterModel()).collect(Collectors.toList());
		List<FolhaMetaMensalModel> modelsDenominador = dtos.get(2).stream().map(dto -> dto.obterModel()).collect(Collectors.toList());
		
		rearranjarValoresDasMetas(numerador, denominador, modelsNumerador, modelsDenominador);

		this.service.deleteByMetaColaboradorAndAno(new MetasModel(idMeta), new ColaboradorModel(matricula), periodoPLR);
		this.service.deleteByMetaColaboradorAndAno(new MetasModel(numerador.getId()), new ColaboradorModel(matricula), periodoPLR);
		this.service.deleteByMetaColaboradorAndAno(new MetasModel(denominador.getId()), new ColaboradorModel(matricula), periodoPLR);
		
		// atualizar indicadores
		
		for (int i = 0; i < modelsIndicador.size(); i++) {
			FolhaMetaMensalModel model = modelsIndicador.get(i);
			FolhaMetaMensalModel numeradorModel = modelsNumerador.get(i);
			FolhaMetaMensalModel denominadorModel = modelsDenominador.get(i);
			if (numeradorModel.getValorMeta() != null && denominadorModel.getValorMeta() != null) {
				if (numeradorModel.getValorMeta() != new BigDecimal("0") && denominadorModel.getValorMeta() != new BigDecimal("0")) {				
					model.setValorMeta(numeradorModel.getValorMeta().divide(denominadorModel.getValorMeta(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
				}
			}
			if (numeradorModel.getValorReal() != null && denominadorModel.getValorReal() != null) {
				if (numeradorModel.getValorReal() != new BigDecimal("0") && denominadorModel.getValorReal() != new BigDecimal("0")) {
					model.setValorReal(numeradorModel.getValorReal().divide(denominadorModel.getValorReal(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
				}
			}
		}

		List<FolhaMetaMensalModel> modelsIndicadorSalvos = this.service.saveAll(modelsIndicador);
		List<FolhaMetaMensalModel> modelsNumeradorSalvos = this.service.saveAll(modelsNumerador);
		List<FolhaMetaMensalModel> modelsDenominadorSalvos = this.service.saveAll(modelsDenominador);

		List<FolhaMetaMensalDTO> results = new ArrayList<>();
		List<FolhaMetaMensalDTO> resultsPlanejados = new ArrayList<>();
		List<FolhaMetaMensalDTO> resultsRealizados = new ArrayList<>();

		if (modelsIndicadorSalvos != null && !modelsIndicadorSalvos.isEmpty()) {
			modelsIndicadorSalvos.forEach(m -> results.add(new FolhaMetaMensalDTO(m)));
		}		
		
		if (modelsNumeradorSalvos != null && !modelsNumeradorSalvos.isEmpty()) {
			modelsNumeradorSalvos.forEach(m -> resultsPlanejados.add(new FolhaMetaMensalDTO(m)));
		}
		
		if (modelsDenominadorSalvos != null && !modelsDenominadorSalvos.isEmpty()) {
			modelsDenominadorSalvos.forEach(m -> resultsRealizados.add(new FolhaMetaMensalDTO(m)));
		}
		
		responseMetasMensaisDTO.setIndicador(new MetasDTO(indicador));
		responseMetasMensaisDTO.setDtosIndicador(results);
		responseMetasMensaisDTO.setDtosPlanejados(resultsPlanejados);
		responseMetasMensaisDTO.setDtosRealizados(resultsRealizados);
		
		return ResponseEntity.ok().body(responseMetasMensaisDTO);
	}

	private void rearranjarValoresDasMetas(MetasModel numerador, MetasModel denominador,
			List<FolhaMetaMensalModel> modelsNumerador, List<FolhaMetaMensalModel> modelsDenominador) {

		modelsNumerador.stream().forEach(x -> x.getMeta().setId(numerador.getId()));
		modelsDenominador.stream().forEach(x -> x.getMeta().setId(denominador.getId()));
		
		List<BigDecimal> valoresMetaRealizado = new ArrayList<>();
		modelsNumerador.stream().forEach(x -> valoresMetaRealizado.add(x.getValorMeta()));
		
		List<BigDecimal> valoresMetaPlanejado = new ArrayList<>();
		modelsNumerador.stream().forEach(x -> valoresMetaPlanejado.add(x.getValorReal()));
		
		List<BigDecimal> valoresRealRealizado = new ArrayList<>();
		modelsDenominador.stream().forEach(x -> valoresRealRealizado.add(x.getValorMeta()));
	
		List<BigDecimal> valoresRealPlanejado = new ArrayList<>();
		modelsDenominador.stream().forEach(x -> valoresRealPlanejado.add(x.getValorReal()));
		
		for (int i = 0; i < 12; i++) {
			modelsNumerador.get(i).setValorMeta(valoresMetaRealizado.get(i));
			modelsNumerador.get(i).setValorReal(valoresRealRealizado.get(i));
		}
		
		for (int i = 0; i < 12; i++) {
			modelsDenominador.get(i).setValorMeta(valoresMetaPlanejado.get(i));
			modelsDenominador.get(i).setValorReal(valoresRealPlanejado.get(i));
		}
	}
	
	@GetMapping("/export/{periodoPLR}")
	public ResponseEntity<InputStreamResource> exportIndicadores(@PathVariable Integer periodoPLR) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "FOLHAS_METAS_MENSAIS" + "_" + PLRUtils.today() + ".xlsx";
		
		Long inicioVigencia = PLRUtils.getSkyTempoFromStringDate("01/01/" + periodoPLR.toString());
		Long fimVigencia = PLRUtils.getSkyTempoFromStringDate("31/12/" + periodoPLR.toString());

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		return new ResponseEntity<>(new InputStreamResource(this.service.export(inicioVigencia, fimVigencia)), headers, HttpStatus.OK);
	}
	
}
