package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistrarme {
    public ModelAndView registrarUsuario(DatosRegistro datos) {
        ModelMap model=new ModelMap();
        String view;
        String msg;
        if(validarEmail(datos.getEmail())){
            model.put("email", datos.getEmail());
            msg= "Registro exitoso";
            view="login";
        }
        else {
            msg="Registro fallido";
            view="registrarme";
        }
        model.put("msg",msg);
        return new ModelAndView(view, model);
    }

    private boolean validarEmail(String email) {
        return email.endsWith(".com") && email.contains("@");
    }
}
