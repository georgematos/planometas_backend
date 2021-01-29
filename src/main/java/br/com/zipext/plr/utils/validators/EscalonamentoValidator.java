package br.com.zipext.plr.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.repository.EscalonamentoRepository;

@Service
public class EscalonamentoValidator implements Validator<EscalonamentoModel> {

	@Autowired
	private EscalonamentoRepository repository;

	public boolean validar(EscalonamentoModel formula) throws Exception {
//		if (formula.getId() == null) { // Salvando
//
//			if (repository.findByNome(formula.getNome()).isPresent()) {
//				throw new Exception("Foi encontrada um Tipo de Meta com a mesma descrição, por favor tente outra.");
//			}
//
//		} else { // Editando
//			Optional<EscalonamentoModel> formulaComMesmoNome = repository.findByNome(formula.getNome());
//
//			if (formulaComMesmoNome.isPresent() && formulaComMesmoNome.get().getId() != formula.getId()) {
//				throw new Exception("Foi encontrada um Tipo de Meta com a mesma descrição, por favor tente outra.");
//			}
//		}

		return true;
	}

}
