package br.com.zipext.plr.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumSituacao {
	ATIVO('A',"Ativo"),
	INATIVO('I', "Inativo"),
	NA('N',"Não aplicável");
	
	private Character codigo;
	private String descricao;
	
	private EnumSituacao(Character codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Character getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@JsonCreator
	public static EnumSituacao forValue(String value) {
		if (value == null) return NA;
		
		for (EnumSituacao situacao : EnumSituacao.values()) {
			if (situacao.getCodigo().toString().equalsIgnoreCase(value) || situacao.getDescricao().equalsIgnoreCase(value)) {
				return situacao;
			}
		}
		
		return
				NA;
	}
	
	@JsonValue
    public Character toValue() {
		return this.getCodigo();
    }
}
