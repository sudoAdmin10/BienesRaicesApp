package com.um.controller;

import java.util.Collection;

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


@Controller
public class LoginController {
	
	@Autowired
	private UsuarioDao usuarioDao;


	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login( ){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value={"/logout"}, method = RequestMethod.GET)
	public ModelAndView logout(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> grantedAuthorities = auth.getAuthorities();
		
		Usuario usuario = usuarioDao.buscaUsuarioPorEmail(auth.getName());
		String role 	= "";
		
		for (GrantedAuthority grantedAuthority : grantedAuthorities){
			role = role +"-"+grantedAuthority.getAuthority();
		}
		
		modelAndView.addObject("role", role); 
		modelAndView.addObject("usuario", "Bienvenido " + usuario.getNombre() + " (" + usuario.getEmail() + ")");
		modelAndView.setViewName("inicio");
		return modelAndView;
	}
	

}
