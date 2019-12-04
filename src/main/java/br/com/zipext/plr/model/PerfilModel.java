package br.com.zipext.plr.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "AUTH", name = "CAD_PERFIL")
public class PerfilModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadPerfilSeq")
	@SequenceGenerator(schema = "AUTH", name = "cadPerfilSeq", sequenceName = "cad_perfil_seq", allocationSize = 1)
	@Column(name = "CD_PERFIL")
	private Long id;
	
	@Column(name = "NM_PERFIL")
	private String nome;
	
	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	@Column(name = "DT_INC")
	private LocalDateTime dataInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_LOGIN_INC")
	private UsuarioModel responsavel;
	
	@OneToMany(mappedBy = "pk.perfil")
	private List<PerfilInfoAcessoModel> perfisAcesso;

	public PerfilModel() {}
	
	public PerfilModel(Long id) {
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

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public UsuarioModel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(UsuarioModel responsavel) {
		this.responsavel = responsavel;
	}
	
	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public List<PerfilInfoAcessoModel> getPerfisAcesso() {
		return perfisAcesso;
	}

	public void setPerfisAcesso(List<PerfilInfoAcessoModel> perfisAcesso) {
		this.perfisAcesso = perfisAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PerfilModel other = (PerfilModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
