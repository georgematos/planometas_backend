package br.com.zipext.plr.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.ColaboradorDTO;
import br.com.zipext.plr.dto.ColaboradorResumoDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.service.ColaboradorService;
import br.com.zipext.plr.service.PerfilUsuarioService;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.PaginationJsGridObject;

@Controller
@RequestMapping("/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService service;

	@Autowired
	private PerfilUsuarioService perfilUsuarioService;

	@GetMapping
	public ResponseEntity<List<ColaboradorResumoDTO>> findAll() {
		List<ColaboradorModel> colaboradores = service.findAll();
		List<ColaboradorResumoDTO> colaboradoresDTOs = colaboradores.stream().map(x -> new ColaboradorResumoDTO(x))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(colaboradoresDTOs);
	}

	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportColaboradores() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "COLABORADORES" + "_" + PLRUtils.today() + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		return new ResponseEntity<>(new InputStreamResource(this.service.export()), headers, HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<PaginationJsGridObject<ColaboradorDTO>> findByFilter(
			@RequestParam(required = false, name = "matricula") String matricula,
			@RequestParam(required = false, name = "cpf") String cpf,
			@RequestParam(required = false, name = "nome") String nome,
			@RequestParam(required = false, name = "situacao") String situacao,
			@RequestParam(required = false, name = "cargo") String cargo,
			@RequestParam(required = false, name = "diretoria") String diretoria,
			@RequestParam(required = false, name = "time") String time,
			@RequestParam(required = false, name = "superiorImediato") String superiorImediato,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		Page<ColaboradorDTO> dtos = this.service.findByFilter(StringUtils.isNotBlank(matricula) ? matricula : null,
				StringUtils.isNotBlank(cpf) ? cpf.toUpperCase() : null,
				StringUtils.isNotBlank(nome) ? nome.toUpperCase() : null,
				StringUtils.isNotBlank(situacao) ? situacao : null,
				StringUtils.isNotBlank(cargo) ? cargo.toUpperCase() : null,
				StringUtils.isNotBlank(diretoria) ? diretoria.toUpperCase() : null,
				StringUtils.isNotBlank(time) ? time.toUpperCase() : null,
				StringUtils.isNoneBlank(superiorImediato) ? superiorImediato : null,
				pageIndex,
				pageSize);
		dtos.getContent().stream().forEach(x -> {
			if (StringUtils.isNotBlank(x.getSuperiorImediato().getMatricula())) {
				x.getSuperiorImediato()
						.setNome(service.findByMatricula(x.getSuperiorImediato().getMatricula()).getNome());
			}
		});
		
		PaginationJsGridObject<ColaboradorDTO> po = new PaginationJsGridObject<>(dtos.getContent(), dtos.getTotalElements());

		return ResponseEntity.ok().body(po);
	}

	@GetMapping("/{matricula}")
	public ResponseEntity<ColaboradorDTO> findByMatricula(@PathVariable("matricula") String matricula) {
		return new ResponseEntity<>(new ColaboradorDTO(this.service.findByMatricula(matricula)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ColaboradorDTO> save(@RequestBody ColaboradorDTO dto) throws Exception {
		ColaboradorModel model = this.service.findByMatricula(dto.getMatricula());
		if (model != null && dto.isNewColaborador()) {
			throw new Exception("Já existe um colaborador cadastrado para a mesma matrícula! ");
		}

		ColaboradorModel newModel = dto.obterModel();
		newModel.setSuperiorImediato(service.findByMatricula(dto.getSuperiorImediato().getMatricula()));
		ColaboradorModel resultModel = this.service.save(newModel);

		if (dto.isNewColaborador()) {
			this.perfilUsuarioService.associaUsuarioGenerico(dto.getMatricula());
		}

		return new ResponseEntity<>(new ColaboradorDTO(resultModel), HttpStatus.OK);
	}
}
