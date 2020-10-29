package br.com.zipext.plr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "CORPORATIVO", name = "CAD_AFASTAMENTO_COLAB")
public class AfastamentoModel {
	
	@EmbeddedId
	private AfastamentoPK pk;
	
	@Column(name = "DS_MOTIVO_AFASTAMENTO")
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name = "SKY_FIM_AFASTAMENTO")
	private TempoModel fimAfastamento;
	
	public AfastamentoModel() {}
	
	public AfastamentoModel(ColaboradorModel colaborador, TempoModel inicioAfastamento) {
		this.pk = new AfastamentoPK(colaborador, inicioAfastamento);
	}
	
	public AfastamentoPK getPk() {
		return pk;
	}

	public void setPk(AfastamentoPK pk) {
		this.pk = pk;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public TempoModel getFimAfastamento() {
		return fimAfastamento;
	}

	public void setFimAfastamento(TempoModel fimAfastamento) {
		this.fimAfastamento = fimAfastamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AfastamentoModel other = (AfastamentoModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}



	@Embeddable
	public static class AfastamentoPK implements Serializable {

		private static final long serialVersionUID = -8692357056819008244L;
		
		@ManyToOne
		@JoinColumn(name = "CD_MATRICULA")
		private ColaboradorModel colaborador;
		
		@ManyToOne
		@JoinColumn(name = "SKY_INI_AFASTAMENTO")
		private TempoModel inicioAfastamento;
		
		public AfastamentoPK() {}
		
		public AfastamentoPK(ColaboradorModel colaborador, TempoModel inicioAfastamento) {
			this.colaborador = colaborador;
			this.inicioAfastamento = inicioAfastamento;
		}

		public ColaboradorModel getColaborador() {
			return colaborador;
		}

		public void setColaborador(ColaboradorModel colaborador) {
			this.colaborador = colaborador;
		}

		public TempoModel getInicioAfastamento() {
			return inicioAfastamento;
		}

		public void setInicioAfastamento(TempoModel inicioAfastamento) {
			this.inicioAfastamento = inicioAfastamento;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((colaborador == null) ? 0 : colaborador.hashCode());
			result = prime * result + ((inicioAfastamento == null) ? 0 : inicioAfastamento.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AfastamentoPK other = (AfastamentoPK) obj;
			if (colaborador == null) {
				if (other.colaborador != null)
					return false;
			} else if (!colaborador.equals(other.colaborador))
				return false;
			if (inicioAfastamento == null) {
				if (other.inicioAfastamento != null)
					return false;
			} else if (!inicioAfastamento.equals(other.inicioAfastamento))
				return false;
			return true;
		}
	}
}
