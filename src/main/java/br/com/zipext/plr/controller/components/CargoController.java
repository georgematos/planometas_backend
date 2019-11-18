package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.CargoDTO;
import br.com.zipext.plr.service.CargoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService service;
	
	@GetMapping
	public ResponseEntity<List<CargoDTO>> finAll() {
		return new ResponseEntity<>
			(this.service.findAllByOrderByNomeAsc().stream().map(CargoDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
