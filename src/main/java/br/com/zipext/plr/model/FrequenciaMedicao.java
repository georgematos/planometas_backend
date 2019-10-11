package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "METAS", name = "CAD_FREQ_MEDICAO")
public class FrequenciaMedicao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadFreqMedicaoSeq")
	@SequenceGenerator(schema = "METAS", name = "cadFreqMedicaoSeq", sequenceName = "cad_freq_medicao_seq", allocationSize = 1)
	@Column(name = "CD_FREQ_MEDICAO")
	private Long id;
	
	@Column(name = "DS_FREQUENCIA_MEDICAO")
	private String descricao;

	public FrequenciaMedicao() {}
	
	public FrequenciaMedicao(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		FrequenciaMedicao other = (FrequenciaMedicao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
