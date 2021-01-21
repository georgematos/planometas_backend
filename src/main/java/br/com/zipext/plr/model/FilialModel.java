package br.com.zipext.plr.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "CORPORATIVO", name = "CAD_FILIAL")
public class FilialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadFilialSeq")
	@SequenceGenerator(schema = "CORPORATIVO", name = "cadFilialSeq", sequenceName = "cad_filial_seq", allocationSize = 1)
	@Column(name = "CD_FILIAL")
	private Long id;

	@Column(name = "NM_FILIAL")
	private String nome;

	@Column(name = "CD_LOGIN_INC")
	private String responsavelInclusao;

	@Column(name = "CD_LOGIN_ALT")
	private String responsavelAlteracao;

	@Column(name = "DT_INC")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime inclusao;

	@Column(name = "DT_ALT")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime alteracao;

	public FilialModel() {
	}

	public FilialModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavelInclusao() {
		return responsavelInclusao;
	}

	public void setResponsavelInclusao(String responsavelInclusao) {
		this.responsavelInclusao = responsavelInclusao;
	}

	public String getResponsavelAlteracao() {
		return responsavelAlteracao;
	}

	public void setResponsavelAlteracao(String responsavelAlteracao) {
		this.responsavelAlteracao = responsavelAlteracao;
	}

	public LocalDateTime getInclusao() {
		return inclusao;
	}

	public void setInclusao(LocalDateTime inclusao) {
		this.inclusao = inclusao;
	}

	public LocalDateTime getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(LocalDateTime alteracao) {
		this.alteracao = alteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		FilialModel other = (FilialModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
