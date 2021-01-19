package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.EquivalenciaDTO;
import br.com.zipext.plr.service.EquivalenciaService;

@Controller
@RequestMapping("/equivalencias")
public class EquivalenciaController {

	@Autowired
	private EquivalenciaService service;
	
	@GetMapping
	public ResponseEntity<List<EquivalenciaDTO>> findAll() {
		return new ResponseEntity<>
			(this.service.findAllByOrderByDescricaoAsc().stream().map(EquivalenciaDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
	
//	@GetMapping("/filter")
//	public ResponseEntity<List<EquivalenciaDTO>> findByFilter(
//			@RequestParam(name = "codigo", required = false) Long id,
//			@RequestParam(name = "nome", required = false) String nome,
//			@RequestParam(name = "equivalencia", required = false) Long equivalencia) {
//		
//		List<CargoDTO> dtos = this.service.findByFilter(
//				id,
//				StringUtils.isNotBlank(nome) ? nome.toUpperCase() : null,
//				equivalencia)
//					.stream()
//					.map(CargoDTO::new)
//					.collect(Collectors.toList());
//		
//		return new ResponseEntity<>(dtos, HttpStatus.OK);
//	}
	
//	@GetMapping("/export")
//	public ResponseEntity<InputStreamResource> exportFolha(@RequestParam("matricula") String matricula, @RequestParam Long idFolhaMeta) throws IOException {
//		ColaboradorModel model = this.colaboradorService.findByMatricula(matricula);
//
//		HttpHeaders headers = new HttpHeaders();
//		String fileName = model.getMatricula() + "_" + model.getNome() + "_" + PLRUtils.today() + ".xlsx";
//		
//		headers.add("Content-Disposition", "attachment; filename=" + fileName);
//		
//		return new ResponseEntity<>(new InputStreamResource(this.service.export(idFolhaMeta)), headers, HttpStatus.OK);
//	}
	
//	@PostMapping	
//	public ResponseEntity<FolhaMetaDTO> save(@RequestBody FolhaMetaDTO dto) {
//		if (dto.getId() != null) {
//			this.folhaMetaItemService.deleteByIdFolhaMeta(dto.getId());
//		}
//	
//		FolhaMetaModel model = this.service.save(dto.obterModel());
//		model.setFolhaMetaItems(this.folhaMetaItemService.saveAll(dto.obterFolhaMetaItems(model)));
//		model.getFolhaMetaItems().forEach(item -> item.setMeta(this.metasService.findById(item.getMeta().getId())));
//		
//		return new ResponseEntity<>(new FolhaMetaDTO(model), HttpStatus.OK);
//	}
//	
//	@PutMapping("/aprovacao/{id}")
//	public ResponseEntity<Void> aprovarFolhaMeta(@PathVariable Long id) {
//		this.service.updateSituacaoById(id, EnumSituacao.ATIVO.getCodigo().toString());
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteFolhaMeta(@PathVariable Long id) {
//		this.service.deleteById(id);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}
