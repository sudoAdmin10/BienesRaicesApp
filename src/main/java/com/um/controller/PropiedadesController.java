package com.um.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.um.dao.PropiedadDao;
import com.um.model.Propiedad;

@Controller
public class PropiedadesController {

    @Autowired
    private PropiedadDao propiedadDao;

    @RequestMapping(value = { "/propiedades" }, method = RequestMethod.GET)
    public ModelAndView mostrarPropiedades(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Propiedad propiedad = new Propiedad();

        boolean showModal = false;
        if (request.getParameter("id") == null) {
            showModal = true;
        }

        boolean result = request.getParameter("borrar") == null ? false
                : Boolean.parseBoolean(request.getParameter("borrar"));

        modelAndView.addObject("Borrar esta propiedad", result);
        modelAndView.addObject("lista_propiedades", propiedadDao.listaPropiedades());
        modelAndView.addObject("showModal", showModal);
        modelAndView.addObject("propiedad", propiedad);
        modelAndView.setViewName("propiedades");
        return modelAndView;
    }

    @RequestMapping(value = "/propiedades", method = RequestMethod.POST)
    public ModelAndView registrarPropiedad(Propiedad propiedad) {

        ModelAndView modelAndView = new ModelAndView("redirect:/propiedades");
        return modelAndView;
    }

    @RequestMapping(value = { "/crear-propiedad" }, method = RequestMethod.GET)
    public ModelAndView creaPropiedad(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Propiedad propiedad = new Propiedad();

        modelAndView.addObject("propiedad", propiedad);

        modelAndView.setViewName("crear-propiedad");
        return modelAndView;
    }

    @RequestMapping(value = { "/crear-propiedad" }, method = RequestMethod.POST)
    public ModelAndView crearPropiedad(Propiedad propiedad, RedirectAttributes atribute) {
        int propiedadEstado = 1;

        int propiedadTipo = propiedad.getTipo();

        int[] propietario = new int[1];

        propietario[0] = 6;
        System.out.println("Property Saved Sucessfully!!");
        atribute.addFlashAttribute("success", "Property Saved Sucessfully!!");

        propiedadDao.grabaPropiedad(propiedad, propiedadEstado, propiedadTipo, propietario);
        ModelAndView modelAndView = new ModelAndView("redirect:/propiedades");
        return modelAndView;
    }

    @RequestMapping(value = { "/propiedades/edit" }, method = RequestMethod.GET)
    public ModelAndView editarPropiedad(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @RequestMapping(value = "/editarPropiedad", method = RequestMethod.POST)
    public ModelAndView editarPropiedad(Propiedad propiedad, RedirectAttributes atribute) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Entrando al Método POST de Editar Propiedad");
        System.out.println(
                "Propiedad " + propiedad.getId() + " " + propiedad.getPrecio() + " " + propiedad.getDimensiones()
                        + " " + propiedad.getPropietario() + " ");

        boolean result = propiedadDao.editarPropiedad(propiedad);
        modelAndView.addObject("result", result);
        System.out.println("RESULT " + result);

        atribute.addFlashAttribute("edit", "Property Edited Sucessfully!!");
        modelAndView = new ModelAndView("redirect:/propiedades/detalles?id=" + propiedad.getId() + "&editar=" + result);
        return modelAndView;
    }

    @RequestMapping(value = "/propiedades/detalles", method = RequestMethod.GET)
    public ModelAndView detalles(HttpServletRequest request, RedirectAttributes atribute) {
        ModelAndView modelAndView = new ModelAndView();

        Propiedad propiedad_detalles = new Propiedad();

        int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
        System.out.println("ID DEL GET porpiedades/detalles" + id);
        if (request.getParameter("id") != null) {
            propiedad_detalles = propiedadDao.buscaPropiedadPorId(id);
        }

        atribute.addFlashAttribute("edit", "Property Edited Sucessfully!!");
        modelAndView.addObject("mapaTipoInmueble", propiedadDao.mapaTipoInmueble());
        modelAndView.addObject("mapaEstadoInmueble", propiedadDao.mapaEstadoInmueble());
        modelAndView.addObject("detalles", propiedad_detalles);
        modelAndView.setViewName("detalles-propiedad");

        return modelAndView;
    }

    @RequestMapping(value = "/propiedaes/detalles", method = RequestMethod.POST)
    public ModelAndView detallesPropiedad(Propiedad propieddad) {
        ModelAndView modelAndView = new ModelAndView("redirect:/propiedades/detalles");

        return modelAndView;
    }

    @RequestMapping(value = "/borrarPropiedad", method = RequestMethod.GET)
    public ModelAndView borrarPropiedadGET(HttpServletRequest request, RedirectAttributes atribute) {
        ModelAndView modelAndView = new ModelAndView();

        int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
        System.out.println("ID GET " + id);

        boolean result = false;

        if (id != 0) {
            System.out.println(id);
            result = propiedadDao.borrarPropiedadPorId(id);
        }

        atribute.addFlashAttribute("delete", "Property Deleted Sucessfully!!");
        modelAndView = new ModelAndView("redirect:/propiedades?borrar=" + result);

        return modelAndView;
    }

    @RequestMapping(value = "/borrarPropiedadConfirmacion", method = RequestMethod.POST)
    public ModelAndView borrarPropiedadPOST(@ModelAttribute("propiedad") Propiedad propiedad,
            RedirectAttributes atribute) {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("BorrarPropiedadPOST ID " + propiedad.getId());
        int id = propiedad.getId() == 0 ? null : Integer.parseInt("" + propiedad.getId());

        System.out.println("Borrar usuario " + propiedad.getId());
        System.out.println("ID usuario " + id);
        boolean result = propiedadDao.borrarPropiedadPorId(id);

        atribute.addFlashAttribute("delete", "Property Deleted Sucessfully!!");
        modelAndView = new ModelAndView("redirect:/propiedades?borrar=" + result);
        return modelAndView;
    }

}
