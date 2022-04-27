package br.com.unip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DocumentacaoController {

	@GetMapping("documentacao")
	public ModelAndView documentacaoSwagger() {
		return new ModelAndView("redirect:/swagger-ui.html");
	}
}
