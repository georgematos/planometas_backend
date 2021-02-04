package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class TipoMetaDTO {

	private Long id;

	private String descricao;

	private String abreviacao;
	
	private TipoDeMetaAbvDTO abv;

	private String isMetaRestrita;

	public TipoMetaDTO() {
	}

	public TipoMetaDTO(TipoMetaModel model) {
		BeanUtils.copyProperties(model, this);
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

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public String getIsMetaRestrita() {
		return isMetaRestrita;
	}

	public void setIsMetaRestrita(String isMetaRestrita) {
		this.isMetaRestrita = isMetaRestrita;
	}
	
	public TipoDeMetaAbvDTO getAbv() {
		return abv;
	}

	public void setAbv(TipoDeMetaAbvDTO abv) {
		this.abv = abv;
	}

	public TipoMetaModel obterModel() {
		TipoMetaModel tipoMetaModel = new TipoMetaModel();

		if (this.id != null)
			tipoMetaModel.setId(this.id);
		if (this.descricao != null)
			tipoMetaModel.setDescricao(this.getDescricao().toUpperCase());
		if (this.abreviacao != null)
			tipoMetaModel.setAbreviacao(this.getAbreviacao().toUpperCase());
		if (this.isMetaRestrita != null)
			tipoMetaModel.setIsMetaRestrita(this.isMetaRestrita);
		if (this.id == null) {
			tipoMetaModel.setInclusao(LocalDateTime.now());
			tipoMetaModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			tipoMetaModel.setAlteracao(LocalDateTime.now());
			tipoMetaModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return tipoMetaModel;

	}

}
