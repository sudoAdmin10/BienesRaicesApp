package com.um.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.um.dao.UsuarioDao;
import com.um.model.Usuario;
import com.um.dao.AgenciaDao;
import com.um.model.Agencia;

@Controller
public class UsuariosController {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private AgenciaDao agenciaDao;

	@RequestMapping(value = { "/usuarios" }, method = RequestMethod.GET)
	public ModelAndView usuarios(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> grantedAuthorities = auth.getAuthorities();

		String role = "";

		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			role = role + "-" + grantedAuthority.getAuthority();
		}

		int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
		String mensaje = request.getParameter("Mensaje") == null ? "1" : request.getParameter("Mensaje");
		System.out.println("idUsuario " + id);

		Usuario tmp = new Usuario();

		boolean showModal = false;
		if (request.getParameter("id") != null) {
			showModal = true;
			tmp = usuarioDao.buscaUsuarioPorId(id);
			System.out.println("0====0 " + tmp.getId());
		}

		Agencia agencias = new Agencia();

		boolean result = request.getParameter("borrar") == null ? false
				: Boolean.parseBoolean(request.getParameter("borrar"));

		modelAndView.addObject("Borrar este usuario", result);
		modelAndView.addObject("role", role);
		modelAndView.addObject("mapaAgencias", agenciaDao.mapaAgencias());
		modelAndView.addObject("lista", usuarioDao.listaUsuarios());
		modelAndView.addObject("agencias", agencias);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("showModal", showModal);
		modelAndView.addObject("tmp", tmp);
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping(value = "/editarUsuario", method = RequestMethod.POST)
	public ModelAndView editarUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		int agencia_id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));

		System.out.println("Entrando al Método POST de Editar Usuario");
		System.out.println("Usuario " + usuario.getId() + " " + usuario.getNombre() + " " + usuario.getEmail() + " "
				+ " " + usuario.getAgencia_id() + " " + agencia_id + " " + usuario.getPassword());

		boolean result = usuarioDao.editarUsuario(usuario, new int[] { 1 });

		modelAndView = new ModelAndView("redirect:/usuarios?result=" + result);

		return modelAndView;
	}

	@RequestMapping(value = "/regresar", method = RequestMethod.GET)
	public ModelAndView regresar() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("inicio");
		return modelAndView;
	}

	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrarUsuario(HttpServletRequest request, RedirectAttributes atribute) {
		ModelAndView modelAndView = new ModelAndView();
		int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));

		boolean result = false;

		if (id != 0) {
			System.out.println(id);
			result = usuarioDao.borrarUsuarioPorId(id);
		}

		modelAndView = new ModelAndView("redirect:/usuarios?borrar=" + result + "&ModalDelete=true");

		return modelAndView;
	}

}
