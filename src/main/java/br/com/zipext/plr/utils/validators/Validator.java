package br.com.zipext.plr.utils.validators;

public interface Validator<T> {
	public boolean validar(T tipo) throws Exception;
}
