package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.repository.TipoMedicaoRepository;

@Service
public class TipoMedicaoValidator implements Validator<TipoMedicaoModel> {

	@Autowired
	private TipoMedicaoRepository repository;

	@Autowired
	ValidarNomeValidator<Long> nomeValidator;

	public boolean validar(TipoMedicaoModel tipoMedicao) throws Exception {
		boolean isPresent = repository.findByDescricao(tipoMedicao.getDescricao()).isPresent();

		nomeValidator.validar(isPresent, tipoMedicao.getId());

		return true;
	}

}
