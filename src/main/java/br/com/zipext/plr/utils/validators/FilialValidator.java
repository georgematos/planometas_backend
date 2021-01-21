package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.repository.FilialRepository;

@Service
public class FilialValidator implements Validator<FilialModel> {
	
	@Autowired
	private FilialRepository repository;
	
	public boolean validar(FilialModel filial) throws Exception {
		if (repository.findByNome(filial.getNome()).isPresent() && filial.getId() == null) {
			throw new Exception("Foi encontrada uma filial com o mesmo nome, por favor tente outro.");
		};
		return true;
	}

}
