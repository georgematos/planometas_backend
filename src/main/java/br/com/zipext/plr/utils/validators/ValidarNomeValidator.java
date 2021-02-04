package br.com.zipext.plr.utils.validators;

import org.springframework.stereotype.Service;

@Service
public class ValidarNomeValidator<T> {

	public boolean validar(boolean isPresent, T id) throws Exception {

		if ((isPresent && id == null) || (isPresent && id != null)) {
			throw new Exception("Foi encontrada uma entitade com o mesmo nome, por favor tente outro.");
		}

		return true;
	}

}
