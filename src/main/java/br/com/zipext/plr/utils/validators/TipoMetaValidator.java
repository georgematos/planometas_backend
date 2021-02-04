package br.com.zipext.plr.utils.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.repository.TipoMetaRepository;

@Service
public class TipoMetaValidator implements Validator<TipoMetaModel> {

	@Autowired
	private TipoMetaRepository repository;

	@Autowired
	ValidarNomeValidator<Long> nomeValidator;

	public boolean validar(TipoMetaModel tipoMeta) throws Exception {

		if (tipoMeta.getId() == null) { // Salvando

			if (repository.findByDescricao(tipoMeta.getDescricao()).isPresent()) {
				throw new Exception("Foi encontrada um Tipo de Meta com a mesma descrição, por favor tente outra.");
			}

			if (repository.findByAbreviacao(tipoMeta.getAbreviacao()).isPresent()) {
				throw new Exception("Foi encontrado Tipo de Meta com a mesma abreviação, por favor tente outra.");
			}
		} else { // Editando
			Optional<TipoMetaModel> tipoMetaComDescricao = repository.findByDescricao(tipoMeta.getDescricao());
			Optional<TipoMetaModel> tipoMetaComAbreviacao = repository.findByAbreviacao(tipoMeta.getAbreviacao());
			if (tipoMetaComDescricao.isPresent() && tipoMetaComDescricao.get().getId() != tipoMeta.getId()) {
				throw new Exception("Foi encontrada um Tipo de Meta com a mesma descrição, por favor tente outra.");
			}
			
			if (tipoMetaComAbreviacao.isPresent() && tipoMetaComAbreviacao.get().getId() != tipoMeta.getId()) {
				throw new Exception("Foi encontrado Tipo de Meta com a mesma abreviação, por favor tente outra.");
			}
		}

		return true;
	}

}
