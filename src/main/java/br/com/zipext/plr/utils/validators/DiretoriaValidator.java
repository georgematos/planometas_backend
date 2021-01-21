package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.repository.DiretoriaRepository;

@Service
public class DiretoriaValidator implements Validator<DiretoriaModel> {
	
	@Autowired
	private DiretoriaRepository repository;
	
	public boolean validar(DiretoriaModel diretoria) throws Exception {
		if (repository.findByNome(diretoria.getNome()).isPresent() && diretoria.getId() == null) {
			throw new Exception("Foi encontrada uma diretoria com o mesmo nome, por favor tente outro.");
		};
		return true;
	}

}
