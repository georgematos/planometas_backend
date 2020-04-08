package br.com.zipext.plr.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import br.com.zipext.plr.converter.LocalDateTimeConverter;
import br.com.zipext.plr.enums.EnumSimNao;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.utils.PLRUtils;


@Entity
@Table(schema = "CORPORATIVO" ,name = "CAD_COLABORADOR")
public class ColaboradorModel {

	@Id
	@Column(name = "CD_MATRICULA")
	private String matricula; 
	
	@Column(name = "CD_CPF")
	private String cpf;
	
	@Column(name = "NM_COLABORADOR")
	private String nome;
	
	@Column(name = "DT_ADMISSAO")
	private LocalDate dataAdmissao;
	
	@Column(name = "DT_DEMISSAO")
	private LocalDate dataDemissao;
	
	@Column(name = "FL_SIT_ELEGIVEL")
	private String elegivel;
	
	@Column(name = "FL_SIT_CADASTRO")
	private String situacao;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_DIRETORIA")
	private DiretoriaModel diretoria;
	
	@ManyToOne
	@JoinColumn(name = "CD_CARGO")
	private CargoModel cargo;
	
	@ManyToOne
	@JoinColumn(name = "CD_TIME")
	private TimeModel time;
	
	@ManyToOne
	@JoinColumn(name = "CD_FILIAL")
	private FilialModel filial;
	
	@OneToMany(mappedBy = "colaborador")
	private List<UsuarioModel> usuarios;
	
	public ColaboradorModel() {}
	
	public ColaboradorModel(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getElegivel() {
		return elegivel;
	}

	public void setElegivel(String elegivel) {
		this.elegivel = elegivel;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public DiretoriaModel getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(DiretoriaModel diretoria) {
		this.diretoria = diretoria;
	}

	public CargoModel getCargo() {
		return cargo;
	}

	public void setCargo(CargoModel cargo) {
		this.cargo = cargo;
	}

	public TimeModel getTime() {
		return time;
	}

	public void setTime(TimeModel time) {
		this.time = time;
	}

	public FilialModel getFilial() {
		return filial;
	}

	public void setFilial(FilialModel filial) {
		this.filial = filial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDateTime getInclusao() {
		return inclusao;
	}

	public void setInclusao(LocalDateTime inclusao) {
		this.inclusao = inclusao;
	}

	public String getResponsavelInclusao() {
		return responsavelInclusao;
	}

	public void setResponsavelInclusao(String responsavelInclusao) {
		this.responsavelInclusao = responsavelInclusao;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/* Export */
	
	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}

	public String getAdmissaoToString() {
		if (this.dataAdmissao != null) {
			return this.dataAdmissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		
		return "";
	}
	
	public String getDemissaoToString() {
		if (this.dataDemissao != null) {
			return this.dataDemissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		
		return "";
	}
	
	public String isElegivelIndividual() {
		if (!StringUtils.isNotBlank(this.elegivel)) {
			return "";
		}
		
		return this.elegivel.equals("1") ? EnumSimNao.SIM.getDescricao() : EnumSimNao.NAO.getDescricao();
	}
	
	public String getStatusToExport() {
		return EnumSituacao.forValue(this.situacao).getDescricao();
	}
	
	public String getFormattedCPF() {
		return PLRUtils.formatCPF(this.cpf);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		ColaboradorModel other = (ColaboradorModel) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}
}
