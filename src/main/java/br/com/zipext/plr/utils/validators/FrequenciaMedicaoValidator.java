package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.repository.FrequenciaMedicaoRepository;

@Service
public class FrequenciaMedicaoValidator implements Validator<FrequenciaMedicaoModel> {

	@Autowired
	private FrequenciaMedicaoRepository repository;

	@Autowired
	ValidarNomeValidator<Long> nomeValidator;

	public boolean validar(FrequenciaMedicaoModel frequenciaMedicao) throws Exception {
		boolean isPresent = repository.findByDescricao(frequenciaMedicao.getDescricao()).isPresent();

		nomeValidator.validar(isPresent, frequenciaMedicao.getId());

		return true;
	}

}
