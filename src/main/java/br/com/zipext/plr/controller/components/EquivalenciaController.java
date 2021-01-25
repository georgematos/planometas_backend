package br.com.zipext.plr.controller.components;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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

import br.com.zipext.plr.dto.EquivalenciaDTO;
import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.service.EquivalenciaService;

@Controller
@RequestMapping("/equivalencias")
public class EquivalenciaController {

	@Autowired
	private EquivalenciaService service;

	@GetMapping
	public ResponseEntity<List<EquivalenciaDTO>> findAll() {
		return new ResponseEntity<>(this.service.findAllByOrderByDescricaoAsc().stream().map(EquivalenciaDTO::new)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<EquivalenciaDTO>> findByFilter(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "descricao", required = false) String descricao,
			@RequestParam(name = "multiplicador", required = false) BigDecimal multiplicador,
			@RequestParam(name = "limitemultiplicador", required = false) BigDecimal limiteMultiplicador,
			@RequestParam(name = "limitesomametas", required = false) BigDecimal limiteSomaMetas)
			throws EntityNotFoundException {

		List<EquivalenciaDTO> dtos = new ArrayList<>();

		try {
			List<EquivalenciaModel> models = this.service.findByFilter(id,
					StringUtils.isNotBlank(descricao) ? descricao.toUpperCase() : null, multiplicador,
					limiteMultiplicador, limiteSomaMetas);

			dtos = models.stream().map(EquivalenciaDTO::new).collect(Collectors.toList());

			return ResponseEntity.ok().body(dtos);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<EquivalenciaDTO> save(@RequestBody EquivalenciaDTO dto) throws Exception {
		if (dto.getId() != null) {
			this.service.findById(dto.getId());
		}

		EquivalenciaModel model;

		model = this.service.save(dto.obterModel());
		return ResponseEntity.status(HttpStatus.CREATED).body(new EquivalenciaDTO(model));

	}

	@PutMapping("/{id}")
	public ResponseEntity<EquivalenciaDTO> atualizarCargo(@PathVariable Long id, @RequestBody EquivalenciaDTO dto)
			throws Exception {
		EquivalenciaModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new EquivalenciaDTO(entity));
	}

}
