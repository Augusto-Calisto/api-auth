package br.com.unip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class DocumentacaoController {

	@GetMapping("docs")
	public ModelAndView documentacaoSwagger() {
		return new ModelAndView("redirect:/swagger-ui.html");
	}
}