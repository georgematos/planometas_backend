package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.TimeModel;
import br.com.zipext.plr.repository.TimeRepository;

@Service
public class TimeValidator implements Validator<TimeModel> {
	
	@Autowired
	private TimeRepository repository;
	
	@Autowired
	ValidarNomeValidator<String> nomeValidator;

	public boolean validar(TimeModel filial) throws Exception {
		boolean isPresent = repository.findByNome(filial.getNome()).isPresent();

		nomeValidator.validar(isPresent, filial.getCodigo());

		return true;
	}

}
