package com.um.controller;

import java.util.Collection;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.um.model.Usuario;

@Controller
public class NosotrosController {
	
	@RequestMapping(value={"/nosotros"}, method = RequestMethod.GET)
	public ModelAndView conocenos(){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("nosotros");
		return modelAndView;
	}
	
	@RequestMapping(value = "/nosotros", method = RequestMethod.POST)
	public ModelAndView nosotros(HttpServletRequest request) {
		ModelAndView modelAndView 	= new ModelAndView();
		
		
		modelAndView = new ModelAndView("redirect:/nosotros");
		
		return modelAndView;
	}
	
}
