package br.com.zipext.plr.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.MetasPeriodoDTO;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.MetasPeriodoModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.service.MetasPeriodoService;

@Controller
@RequestMapping("/metasperiodo")
public class MetasPeriodoController {

	@Autowired
	private MetasPeriodoService service;

	@GetMapping("/{periodoplr}")
	public ResponseEntity<List<MetasPeriodoDTO>> findAllForPeriodo(@PathVariable("periodoplr") Long periodoPLR, @RequestParam(name = "page", required = false) Integer page) {
		return new ResponseEntity<>
			(this.service.findMetasByPeriodoAndSituacao(periodoPLR, null, page)
					.stream().map(MetasPeriodoDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MetasPeriodoDTO> save(@RequestBody MetasPeriodoDTO dto) {
		MetasPeriodoModel model = this.service.save(dto.obterModel());
		return
				new ResponseEntity<>(new MetasPeriodoDTO(model), HttpStatus.OK);
	}
	
	@DeleteMapping("/meta/{idMeta}/periodoplr/{periodoPLR}")
	public ResponseEntity<Void> delete(@PathVariable("idMeta") Long idMeta, @PathVariable("periodoPLR") Long periodoPLR) {
		this.service.delete(new MetasPeriodoModel(new MetasModel(idMeta), new TempoModel(periodoPLR)));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
