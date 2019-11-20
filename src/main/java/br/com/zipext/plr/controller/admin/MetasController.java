package br.com.zipext.plr.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.service.MetasService;

@Controller
@RequestMapping("/metas")
public class MetasController {

	@Autowired
	private MetasService service;

	@GetMapping("/filter")
	public ResponseEntity<List<MetasDTO>> findByFilter(@RequestParam(name = "meta", required = false) String meta,
			@RequestParam(name = "situacao", required = false) String situacao,
			@RequestParam(name = "tipoMedicao", required = false) String tipoMedicao,
			@RequestParam(name = "tipoMeta", required = false) String tipoMeta,
			@RequestParam(name = "formula", required = false) String formula,
			@RequestParam(name = "frequenciaMedicao", required = false) String frequenciaMedicao) {

		List<MetasModel> models = this.service.findByFilter(StringUtils.isNotBlank(meta) ? meta.toUpperCase() : null,
				StringUtils.isNotBlank(situacao) ? situacao.toUpperCase() : null,
				StringUtils.isNotBlank(tipoMedicao) ? tipoMedicao.toUpperCase() : null,
				StringUtils.isNotBlank(tipoMeta) ? tipoMeta.toUpperCase() : null,
				StringUtils.isNoneBlank(formula) ? formula.toUpperCase() : null, 
				StringUtils.isNoneBlank(frequenciaMedicao) ? frequenciaMedicao.toUpperCase() : null);
		return new ResponseEntity<>(models.stream().map(MetasDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
