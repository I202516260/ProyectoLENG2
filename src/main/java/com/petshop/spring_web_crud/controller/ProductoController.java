package com.petshop.spring_web_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petshop.spring_web_crud.dto.ProductoFilter;
import com.petshop.spring_web_crud.model.Producto;
import com.petshop.spring_web_crud.service.CategoriaService;
import com.petshop.spring_web_crud.service.ProductoService;
import com.petshop.spring_web_crud.util.Alert;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("producto")
@RequiredArgsConstructor
public class ProductoController {
    
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    
    @GetMapping("listado")
    public String listado(@ModelAttribute ProductoFilter filter, Model model) {
        model.addAttribute("lstProductos", productoService.search(filter));
        model.addAttribute("lstCategorias", categoriaService.getAll());
        model.addAttribute("filter", filter);
        return "producto/listado";
    }
    
    @GetMapping("nuevo")
    public String nuevo(Model model) {
        model.addAttribute("lstCategorias", categoriaService.getAll());
        model.addAttribute("producto", new Producto());
        return "producto/nuevo";
    }
    
    @PostMapping("registrar")
    public String registrar(@ModelAttribute Producto producto, RedirectAttributes flash) {
        var response = productoService.create(producto);
        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje(), "success", 5000));
        return "redirect:/producto/listado";
    }
    
    @GetMapping("edicion/{id}")
    public String edicion(@PathVariable Integer id, Model model) {
        model.addAttribute("lstCategorias", categoriaService.getAll());
        model.addAttribute("producto", productoService.getOne(id));
        return "producto/edicion";
    }
    
    @PostMapping("guardar")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes flash) {
        var response = productoService.update(producto);
        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje(), "success", 5000));
        return "redirect:/producto/listado";
    }

    @PostMapping("cambiar-activo")
    public String cambiarEstado(@RequestParam Integer id, RedirectAttributes flash) {
        var response = productoService.changeActive(id);
        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje(), "success", 5000));
        return "redirect:/producto/listado";
    }
}