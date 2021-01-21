package br.com.zipext.plr.utils.validators;

import org.springframework.stereotype.Service;

@Service
public class ValidarNomeValidator {

	public boolean validar(boolean isPresent, Long id) throws Exception {

		if ((isPresent && id == null) || (isPresent && id != null)) {
			throw new Exception("Foi encontrada uma diretoria com o mesmo nome, por favor tente outro.");
		}

		return true;
	}

}
