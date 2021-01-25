package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.repository.EquivalenciaRepository;

@Service
public class EquivalenciaValidator implements Validator<EquivalenciaModel> {
	
	@Autowired
	private EquivalenciaRepository repository;
	
	public boolean validar(EquivalenciaModel equivalencia) throws Exception {
		if (equivalencia.getMultiplicador().compareTo(equivalencia.getLimiteMultiplicador()) > -1) {
			throw new Exception("O Limite do multiplicador deve ser maior que o multiplicador.");
		}
		if (repository.findByDescricao(equivalencia.getDescricao()).isPresent() && equivalencia.getId() == null) {
			throw new Exception("Foi encontrada uma equivalência com a mesma descrição, por favor tente outra.");
		};
		return true;
	}

}
