package br.com.zipext.plr.controller.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.RelMetaAvaliacaoProjetoDTO;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel;
import br.com.zipext.plr.service.RelAvaliacaoProjetoService;
import br.com.zipext.plr.service.RelMetaAvaliacaoProjetoService;

@Controller
@RequestMapping("/metaaval")	
public class RelMetaAvaliacaoProjetoController {

	@Autowired
	private RelMetaAvaliacaoProjetoService service;
	
	@Autowired
	private RelAvaliacaoProjetoService serviceAval;
	
	@DeleteMapping("/meta/{idMeta}/periodo/{periodoPLR}")
	public ResponseEntity<Void> delete(@PathVariable("idMeta") Long idMeta, @PathVariable("periodoPLR") String periodoPLR) {
		this.service.deleteByMetaAndPeriodo(new MetasModel(idMeta), Integer.valueOf(periodoPLR));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RelMetaAvaliacaoProjetoDTO> save(@RequestBody RelMetaAvaliacaoProjetoDTO dto){
		
		// baseado no dto que chega, buscar o valor do escalonamento atraves do prazo, qualitativo e orcamento
		RelAvaliacaoProjetoPK pk = serviceAval.getPKByDTO(dto);
		
		RelAvaliacaoProjetoModel findedModel = serviceAval.findById(pk);
		
		dto.setValEscalonamento(findedModel.getValEscalonamento());
		
		RelMetaAvaliacaoProjetoModel result = this.service.save(dto.obterModel());
		
		return new ResponseEntity<>(new RelMetaAvaliacaoProjetoDTO(result), HttpStatus.OK);
	}
}
