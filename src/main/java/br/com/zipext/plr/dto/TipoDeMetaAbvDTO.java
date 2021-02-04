package br.com.zipext.plr.dto;

public class TipoDeMetaAbvDTO {

	public int id;
	public Character abv;

	public TipoDeMetaAbvDTO() {
	}

	public TipoDeMetaAbvDTO(int id, Character abv) {
		super();
		this.id = id;
		this.abv = abv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Character getAbv() {
		return abv;
	}

	public void setAbv(Character abv) {
		this.abv = abv;
	}

}
