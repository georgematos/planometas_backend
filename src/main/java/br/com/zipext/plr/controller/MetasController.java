package br.com.zipext.plr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("/metas")
public class MetasController {

	@GetMapping
	public ModelAndView getPage() {
		return
				new ModelAndView("metas");
	}
}
