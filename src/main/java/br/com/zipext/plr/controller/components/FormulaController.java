package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.FormulaDTO;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.service.FormulaService;

@Controller
@RequestMapping("/formulas")
public class FormulaController {

	@Autowired
	private FormulaService service;

	@GetMapping
	public ResponseEntity<List<FormulaDTO>> findAll() {
		return new ResponseEntity<>
			(this.service.findAllAtivosByOrderByNomeAsc().stream().map(FormulaDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<FormulaDTO>> findByFilter(
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "descricao", required = false) String descricao,
			@RequestParam(name = "situacao", required = false) String situacao) {

		List<FormulaDTO> dtos = this.service.findByFilter(
				id,
				StringUtils.isNotBlank(nome) ? nome.toUpperCase() : null,
				StringUtils.isNotBlank(descricao) ? descricao.toUpperCase() : null,
				StringUtils.isNotBlank(situacao) ? situacao.toUpperCase() : null)
					.stream()
					.map(FormulaDTO::new)
					.collect(Collectors.toList());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FormulaDTO> save(@RequestBody FormulaDTO dto) throws Exception {
		FormulaModel model = this.service.findByNome(dto.getNome());
		if (model != null) {
			throw new Exception("Já existe uma Formula cadastrada com o mesmo nome! ");
		}

		FormulaModel resultModel = this.service.save(dto.obterModel());

		return ResponseEntity.ok().body(new FormulaDTO(resultModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormulaDTO> atualizarCargo(@PathVariable Long id, @RequestBody FormulaDTO dto) throws Exception {
		FormulaModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new FormulaDTO(entity));
	}
}
