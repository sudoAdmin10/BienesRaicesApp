package com.um.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.um.dao.UsuarioDao;
import com.um.model.Usuario;

import com.um.dao.PropiedadDao;
import com.um.model.Propiedad;

@Controller
public class LoginController {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private PropiedadDao propiedadDao;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("inicio");
		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		modelAndView.addObject("lista_propiedades", propiedadDao.listaPropiedades());
		modelAndView.addObject("lista_propiedades_destacadas", propiedadDao.listaPropiedadesDestacadas());
		modelAndView.setViewName("inicio");
		return modelAndView;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("index");
		return modelAndView;
	}
}
