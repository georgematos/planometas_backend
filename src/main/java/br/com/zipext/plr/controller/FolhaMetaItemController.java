package br.com.zipext.plr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.FolhaMetaItemDTO;
import br.com.zipext.plr.model.FolhaMetaAnualModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.PerfilPermissaoModel;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.FolhaMetaAnualService;
import br.com.zipext.plr.service.FolhaMetaItemService;
import br.com.zipext.plr.service.FolhaMetaMensalService;
import br.com.zipext.plr.service.FolhaMetaService;
import br.com.zipext.plr.service.PerfilPermissaoService;
import br.com.zipext.plr.service.PerfilUsuarioService;

@Controller
@RequestMapping("/metasitem")
public class FolhaMetaItemController {

	@Autowired
	private FolhaMetaItemService service;
	
	@Autowired
	private FolhaMetaService folhaMetaService;
	
	@Autowired
	private FolhaMetaMensalService folhaMetaMensalService;
	
	@Autowired
	private FolhaMetaAnualService folhaMetaAnualService;
	
	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@Autowired
	private PerfilPermissaoService perfilPermissaoService;
	
	@GetMapping("/{idFolhaMeta}")
	public ResponseEntity<List<FolhaMetaItemDTO>> findByFolhaMeta(@PathVariable("idFolhaMeta") Long idFolhaMeta, @RequestParam String login) {
		Optional<FolhaMetaModel> folhaMeta = this.folhaMetaService.findById(idFolhaMeta);
		PerfilUsuarioModel puModel = this.perfilUsuarioService.findByUsuario(new UsuarioModel(login));
		PerfilPermissaoModel perfilPermissao = this.perfilPermissaoService.findByPerfil(puModel.getPk().getPerfil());
		
		List<FolhaMetaItemDTO> dtos = new ArrayList<>();
		List<FolhaMetaItemModel> models = new ArrayList<>();
		
		if (folhaMeta.isPresent()) {
			models = this.service.findByFolhaMeta(folhaMeta.get());
			
			if (models != null && !models.isEmpty()) {
				models.forEach(model -> {
					FolhaMetaAnualModel folhaMetaAnual = this.folhaMetaAnualService.applyMetaAnualForFolhaMetaItem(model);
					List<FolhaMetaMensalModel> folhaMetasMensais = this.folhaMetaMensalService.findByFolhaMetaItem(model);
					dtos.add(new FolhaMetaItemDTO(model, folhaMetaAnual, folhaMetasMensais, perfilPermissao.isPerfilReadOnly()));
				});
			}
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
