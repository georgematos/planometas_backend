package br.com.zipext.plr.utils.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.repository.FormulaRepository;

@Service
public class FormulaValidator implements Validator<FormulaModel> {

	@Autowired
	private FormulaRepository repository;

	public boolean validar(FormulaModel formula) throws Exception {
		if (formula.getId() == null) { // Salvando

			if (repository.findByNome(formula.getNome()).isPresent()) {
				throw new Exception("Foi encontrada um Tipo de Meta com a mesma descrição, por favor tente outra.");
			}

		} else { // Editando
			Optional<FormulaModel> formulaComMesmoNome = repository.findByNome(formula.getNome());

			if (formulaComMesmoNome.isPresent() && formulaComMesmoNome.get().getId() != formula.getId()) {
				throw new Exception("Foi encontrada um Tipo de Meta com a mesma descrição, por favor tente outra.");
			}
		}

		return true;
	}

}
