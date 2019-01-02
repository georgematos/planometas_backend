package br.com.zipext.plr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zipext.plr.enums.EnumSituacao;

@Entity
@Table(name = "ASS_COLAB_META_ESPECIF", schema = "BET_PLR")
public class ColaboradorMetaEspecificaModel {
	
	@EmbeddedId
	private ColaboradorMetaEspecificaModelPK pk;
	
	@Column(name = "IN_SITUACAO")
	private EnumSituacao situacao;
	
	public ColaboradorMetaEspecificaModel() {}
		
	public ColaboradorMetaEspecificaModel(ColaboradorCargoModel colaboradorCargo, MetaEspecificaModel metaEspecifica) {
		this.pk = new ColaboradorMetaEspecificaModelPK(colaboradorCargo, metaEspecifica);
	}

	@Embeddable
	public static class ColaboradorMetaEspecificaModelPK implements Serializable {

		private static final long serialVersionUID = -4605842892837620440L;
		
		@ManyToOne
		@JoinColumns({@JoinColumn(name="CD_CARGO", referencedColumnName="CD_CARGO", nullable = false),
			@JoinColumn(name="CD_MATRICULA", referencedColumnName="CD_MATRICULA", nullable = false)})
		private ColaboradorCargoModel colaboradorCargo;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		private MetaEspecificaModel metaEspecifica;
		
		public ColaboradorMetaEspecificaModelPK() {}
		
		public ColaboradorMetaEspecificaModelPK(ColaboradorCargoModel colaboradorCargo, MetaEspecificaModel metaEspecifica) {
			this.colaboradorCargo = colaboradorCargo;
			this.metaEspecifica = metaEspecifica;
		}

		public ColaboradorCargoModel getColaboradorCargo() {
			return colaboradorCargo;
		}

		public void setColaboradorCargo(ColaboradorCargoModel colaboradorCargo) {
			this.colaboradorCargo = colaboradorCargo;
		}

		public MetaEspecificaModel getMetaEspecifica() {
			return metaEspecifica;
		}

		public void setMetaEspecifica(MetaEspecificaModel metaEspecifica) {
			this.metaEspecifica = metaEspecifica;
		}
	}

	public ColaboradorMetaEspecificaModelPK getPk() {
		return pk;
	}

	public void setPk(ColaboradorMetaEspecificaModelPK pk) {
		this.pk = pk;
	}

	public EnumSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}
}
