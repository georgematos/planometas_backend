package br.com.zipext.plr.controller.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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

import br.com.zipext.plr.dto.CargoDTO;
import br.com.zipext.plr.dto.CargoNewDTO;
import br.com.zipext.plr.model.CargoModel;
import br.com.zipext.plr.service.CargoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService service;

	@GetMapping
	public ResponseEntity<List<CargoDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findAllByOrderByNomeAsc().stream().map(CargoDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<CargoDTO>> findByFilter(@RequestParam(name = "codigo", required = false) Long id,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "equivalencia", required = false) Long equivalencia,
			@RequestParam(name = "situacao", required = false) String situacao) {

		List<CargoDTO> dtos = this.service
				.findByFilter(id, StringUtils.isNotBlank(nome) ? nome.toUpperCase() : null, equivalencia,
						StringUtils.isNotBlank(situacao) ? situacao.toUpperCase() : null)
				.stream().map(CargoDTO::new).collect(Collectors.toList());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CargoNewDTO> save(@RequestBody CargoNewDTO dto) throws Exception {
		CargoModel model = this.service.findByNome(dto.getNome());
		if (model != null && dto.getIsNewCargo()) {
			throw new Exception("Já existe um cargo cadastrado com o mesmo nome! ");
		}

		CargoModel resultModel = this.service.save(dto.obterModel());

		return ResponseEntity.ok().body(new CargoNewDTO(resultModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CargoNewDTO> atualizarCargo(@PathVariable Long id, @RequestBody CargoNewDTO dto) {
		CargoModel entity = this.service.update(id, dto);

		return ResponseEntity.ok().body(new CargoNewDTO(entity));
	}

	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportIndicadores() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String fileName = "CARGOS" + "_" + ".xlsx";

		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		ByteArrayInputStream exported = service.export();
		
		return new ResponseEntity<>(new InputStreamResource(exported), headers, HttpStatus.OK);
	}

}
