package com.petshop.spring_web_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petshop.spring_web_crud.dto.AutenticacionFilter;
import com.petshop.spring_web_crud.service.AutenticacionService;
import com.petshop.spring_web_crud.util.Alert;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AutenticacionService autenticacionService;

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("filter", new AutenticacionFilter(null, null));
        return "login";
    }

    @GetMapping("iniciar-sesion")
    public String iniciarSesion(@ModelAttribute AutenticacionFilter filter, RedirectAttributes flash,
            HttpSession session) {

        var usuario = autenticacionService.autenticathe(filter);

        if (usuario == null) {
            var alert = Alert.sweetAlertError("Email y/o contraseña inválidos");
            flash.addFlashAttribute("alert", alert);
            return "redirect:/";
        }

        if (!usuario.getActivo()) {
            var alert = Alert.sweetAlertError("Usuario inactivado, comunicarse con soporte");
            flash.addFlashAttribute("alert", alert);
            return "redirect:/";
        }

        session.setAttribute("fullName", usuario.getFullName());
        session.setAttribute("rol", usuario.getRol());

        String routeImage = "/imagenes/welcome-pet.gif";
        String alert = Alert.sweetImageUrl("Bienvenido a PetShop", usuario.getFullName(), routeImage);
        flash.addFlashAttribute("alert", alert);
        return "redirect:/home/dashboard";
    }

    @GetMapping("cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}