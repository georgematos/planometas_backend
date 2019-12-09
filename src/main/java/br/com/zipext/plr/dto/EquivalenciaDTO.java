package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.enums.EnumSimNao;
import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.model.MetaEquivalenciaPeriodoModel;

public class EquivalenciaDTO {
	
	private Long id;
	private String descricao;
	private BigDecimal multiplicador;
	private BigDecimal limiteMultiplicador;
	private List<FolhaMetaItemDTO> metasEquivalentes;
	
	public EquivalenciaDTO() {}
	
	public EquivalenciaDTO(EquivalenciaModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			List<MetaEquivalenciaPeriodoModel> metasEquivalentesModel = model.getMetasEquivalencia();
			this.metasEquivalentes = new ArrayList<>();
			if (metasEquivalentesModel != null && !metasEquivalentesModel.isEmpty()) {
				IntStream.range(0, metasEquivalentesModel.size()).forEach(index -> {
					int sequence = index + 1;
					
					FolhaMetaItemDTO item = new FolhaMetaItemDTO();
					MetaEquivalenciaPeriodoModel me = metasEquivalentesModel.get(index);
					
					item.setMeta(new MetasDTO(me.getPk().getMeta()));
					item.setSequencia(sequence);
					item.setPeso(me.getPeso());
					item.setReadOnly(me.isReadOnly());
					item.setTipoSugerida(EnumSimNao.SIM.getCodigoToString());
					item.setIdTempo(me.getPk().getTempo().getId());
					
					this.metasEquivalentes.add(item);
				});
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(BigDecimal multiplicador) {
		this.multiplicador = multiplicador;
	}

	public BigDecimal getLimiteMultiplicador() {
		return limiteMultiplicador;
	}

	public void setLimiteMultiplicador(BigDecimal limiteMultiplicador) {
		this.limiteMultiplicador = limiteMultiplicador;
	}

	public List<FolhaMetaItemDTO> getMetasEquivalentes() {
		return metasEquivalentes;
	}

	public void setMetasEquivalentes(List<FolhaMetaItemDTO> metasEquivalentes) {
		this.metasEquivalentes = metasEquivalentes;
	}
}
