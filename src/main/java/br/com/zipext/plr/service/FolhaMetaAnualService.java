package br.com.zipext.plr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.model.FolhaMetaAnualModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.repository.FolhaMetaAnualRepository;

@Service
public class FolhaMetaAnualService {

	@Autowired
	private FolhaMetaAnualRepository repository;
	
	@Autowired
	private FolhaMetaItemService folhaMetaItemService;
	
	@Autowired
	private EscalonamentoService escalonamentoService;
	
	@Transactional(readOnly = true)
	public FolhaMetaAnualModel findByMetaAndAno(MetasModel meta, Integer ano) {
		return
				this.repository.findByMetaAndAno(meta, ano);
	}
	
	public List<FolhaMetaAnualModel> applyMetaAnualForFolhaMeta(FolhaMetaModel folhaMeta) {
		List<FolhaMetaItemModel> folhasMetaItem = this.folhaMetaItemService.findByFolhaMeta(folhaMeta);
		List<FolhaMetaAnualModel> folhasMetaAnual = new ArrayList<>();
		if (folhasMetaItem != null && !folhasMetaItem.isEmpty()) {
			folhasMetaItem.forEach(fmi -> {
				FolhaMetaAnualModel folhaMetaAnual = this.applyMetaAnualForFolhaMetaItem(fmi);
				if (folhaMetaAnual != null) {
					folhasMetaAnual.add(folhaMetaAnual);
				}
			});
		}
		
		return
				folhasMetaAnual;
	}
	
	public FolhaMetaAnualModel applyMetaAnualForFolhaMetaItem(FolhaMetaItemModel model) {
		FolhaMetaAnualModel folhaMetaAnual = this.findByMetaAndAno(model.getMeta(), model.getFolhaMeta().getInicioVigencia().getAno());
		if (folhaMetaAnual != null) {
			Optional<EscalonamentoModel> escala = this.escalonamentoService.findBestFitEscalonamentoByAtingidoAndTipoMedicao(
					folhaMetaAnual.getValorPercentAtingido(), folhaMetaAnual.getPk().getMeta().getTipoMedicao());
				
			if (escala.isPresent()) {
				folhaMetaAnual.setDesempenho(escala.get().getDesempenho());
			}
		}
		
		return
				folhaMetaAnual;
	}
}
