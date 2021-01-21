package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.repository.FilialRepository;

@Service
public class FilialValidator implements Validator<FilialModel> {
	
	@Autowired
	private FilialRepository repository;
	
	@Autowired
	ValidarNomeValidator nomeValidator;
	
	public boolean validar(FilialModel filial) throws Exception {
		boolean isPresent = repository.findByNome(filial.getNome()).isPresent();

		nomeValidator.validar(isPresent, filial.getId());

		return true;
	}

}
