package br.com.zipext.plr.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "CONFIG", name = "CAD_TEMPLATE_CAMPO")
public class TemplateCampoModel {

	@Id
	@Column(name = "CD_TEMPLATE_CAMPO")
	private Long id;
	
	@Column(name = "DS_CAMPO")
	private  String nome;
	
	@Column(name = "DS_TIPO_CAMPO")
	private String tipoCampo;
	
	@Column(name = "DS_AREA")
	private String area;

	@Column(name = "NU_ABA")
	public Integer indiceAba;
	
	@Column(name = "NU_COL")
	public Integer coluna;
	
	@Column(name = "NU_ROW")
	public Integer linha;
	
	@Column(name = "DS_METODO_VALOR")
	public String metodo;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
    
    @ManyToOne
    @JoinColumn(name = "CD_TEMPLATE")
    private TemplateModel template;
    
    public TemplateCampoModel() {}

	public TemplateCampoModel(Long id) {
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

	public String getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public Integer getIndiceAba() {
		return indiceAba;
	}

	public void setIndiceAba(Integer indiceAba) {
		this.indiceAba = indiceAba;
	}

	public Integer getColuna() {
		return coluna;
	}

	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
	
	public TemplateModel getTemplate() {
		return template;
	}

	public void setTemplate(TemplateModel template) {
		this.template = template;
	}
	
	public void incremetaIndiceLinha() {
		this.linha++;
	}
}
