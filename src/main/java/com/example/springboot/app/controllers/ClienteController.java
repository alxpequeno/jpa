package com.example.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springboot.app.models.dao.IClienteDao;
import com.example.springboot.app.models.entity.Cliente;
import com.example.springboot.app.models.service.IClienteService;

@Controller
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}

	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();

		model.put("cliente", cliente);
		model.put("Titulo", "Formulario de Cliente");

		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result) {

		if(result.hasErrors()) {
			return "form";
		}
		
		clienteService.save(cliente);

		return "redirect:listar";
	}
}
