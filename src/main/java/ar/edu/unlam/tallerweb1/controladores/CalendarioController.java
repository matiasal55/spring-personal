package ar.edu.unlam.tallerweb1.controladores;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Locale;

@Controller
public class CalendarioController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/home")
    public ModelAndView irAHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/calendarios", method = RequestMethod.GET)
    public ModelAndView verTodosLosCalendarios() {
        ModelMap model=new ModelMap();
        String titulo="Todos";
        ArrayList<String> profesiones=new ArrayList<>();
        profesiones.add("Cardiologia");
        profesiones.add("Odontologia");
        model.put("profesiones",profesiones);
        model.put("titulo", titulo);
        return new ModelAndView("calendarios", model);
    }

    @RequestMapping(path = "/calendarios", method = RequestMethod.POST)
    public ModelAndView recibirUnaProfesion(@RequestParam(required = false) String profesion) {
        ModelMap model=new ModelMap();
        String titulo = profesion;
        ArrayList<String> profesiones = new ArrayList<>();
        profesiones.add("Cardiologia");
        profesiones.add("Odontologia");
        model.put("profesiones", profesiones);
        model.put("titulo", titulo);
        return new ModelAndView("calendarios", model);
    }
}
