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
@Table(name = "ASS_COLAB_CARGO", schema = "BET_PLR")
public class ColaboradorCargoModel {
	
	@EmbeddedId
	private ColaboradorCargoModelPK pk;

	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	public ColaboradorCargoModel() {
		
	}
	
	public ColaboradorCargoModel(ColaboradorModel colaborador, CargoModel cargo) {
		this.pk = new ColaboradorCargoModelPK(colaborador, cargo);
	}
	
	@Embeddable
	public static class ColaboradorCargoModelPK implements Serializable {
		
		private static final long serialVersionUID = 1831415924647090004L;

		@ManyToOne
		@JoinColumn(name = "CD_MATRICULA")
		private ColaboradorModel colaborador;
		
		@ManyToOne
		@JoinColumn(name = "CD_CARGO")
		private CargoModel cargo;
		
		public ColaboradorCargoModelPK() {}
		
		public ColaboradorCargoModelPK(ColaboradorModel colaborador, CargoModel cargo) {
			this.colaborador = colaborador;
			this.cargo = cargo;
		}

		public ColaboradorModel getColaborador() {
			return colaborador;
		}

		public void setColaborador(ColaboradorModel colaborador) {
			this.colaborador = colaborador;
		}

		public CargoModel getCargo() {
			return cargo;
		}

		public void setCargo(CargoModel cargo) {
			this.cargo = cargo;
		}
	}

	public ColaboradorCargoModelPK getPk() {
		return pk;
	}

	public void setPk(ColaboradorCargoModelPK pk) {
		this.pk = pk;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}
}
