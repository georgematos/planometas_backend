package br.com.zipext.plr.dto;

import java.time.format.DateTimeFormatter;

import br.com.zipext.plr.model.CargoModel;
import br.com.zipext.plr.model.ColaboradorCargoModel;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.utils.PLRUtils;

public class MetasResumoDTO {

	private String dataInclusao;
	private String matricula;
	private String nomeColaborador;
	private String cargo;
	private String diretoria;
	
	public MetasResumoDTO() {
	}
	
	public MetasResumoDTO(ColaboradorMetaEspecificaModel model) {
		if (model != null) {
			ColaboradorModel colaborador = model.getPk().getColaborador();		
			ColaboradorCargoModel colabCargo = (ColaboradorCargoModel) colaborador.getColaboradoresCargos().toArray()[0];
			CargoModel carg = colabCargo.getPk().getCargo();
			
			if (model.getDataInclusao() != null) {
				this.dataInclusao = model.getDataInclusao().toLocalDate().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS));
			}
			
			this.matricula = colaborador.getMatricula();
			this.nomeColaborador = colaborador.getNome();
			this.cargo = carg.getNome();
			this.diretoria = carg.getDiretoria().getNome();
		}
	}

	public String getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(String diretoria) {
		this.diretoria = diretoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		MetasResumoDTO other = (MetasResumoDTO) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
