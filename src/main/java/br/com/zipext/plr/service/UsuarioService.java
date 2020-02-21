package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.UsuarioDTO;
import br.com.zipext.plr.enums.EnumSimNao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@Transactional(readOnly = true)
	public UsuarioModel findByLogin(String login) {
		return
				this.repository.findByLogin(login);
	}
	
	public UsuarioModel processLogin(UsuarioModel model) {
		UsuarioModel result = this.findByLogin(model.getLogin());
		ColaboradorModel colaborador = this.colaboradorService.findByMatricula(model.getLogin());
		if (result == null) {
			if (colaborador ==  null) {
				return null;
			} else {
				UsuarioDTO dto = new UsuarioDTO(colaborador);
				UsuarioModel newUser = dto.getModel();
				newUser.setColaborador(colaborador);
				
				this.save(newUser);
				this.perfilUsuarioService.associaUsuarioGenerico(model.getLogin());
				
				return	newUser;
			}
		}  else {
			return
					result;
		}
	}
	
	@Modifying
	@Transactional(readOnly = false)
	public void redefinePrimeiroAcesso(String login) {
		this.repository.redefinePrimeiroAcesso(login, EnumSimNao.SIM.getValue());
	}
	
	@Transactional(readOnly = false)
	public UsuarioModel save(UsuarioModel model) {
		return
				this.repository.save(model);
	}
	
	public UsuarioModel update(UsuarioModel model) {
		ColaboradorModel colaborador = this.colaboradorService.findByMatricula(model.getLogin());
		model.setColaborador(colaborador);
		model.setNome(colaborador.getNome());
		
		return
				this.save(model);
	}
}
