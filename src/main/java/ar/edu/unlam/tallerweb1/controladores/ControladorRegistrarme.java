package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistrarme {
    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorRegistrarme(ServicioLogin servicioLogin){
        this.servicioLogin=servicioLogin;
    }

    public ModelAndView registrarUsuario(DatosRegistro datos) {
        ModelMap model=new ModelMap();
        String view;
        String msg;
        if(validarEmail(datos.getEmail())){
            servicioLogin.registrar(datos.getEmail(),datos.getClave());
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

    public ModelAndView irARegistrarme(){
        return new ModelAndView("registro-usuario");
    }
}
