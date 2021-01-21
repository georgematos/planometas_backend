package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.repository.DiretoriaRepository;

@Service
public class DiretoriaValidator implements Validator<DiretoriaModel> {

	@Autowired
	private DiretoriaRepository repository;

	@Autowired
	ValidarNomeValidator nomeValidator;

	public boolean validar(DiretoriaModel diretoria) throws Exception {
		boolean isPresent = repository.findByNome(diretoria.getNome()).isPresent();

		nomeValidator.validar(isPresent, diretoria.getId());

		return true;
	}

}
