package ec.edu.espe.zone_core.controller;

import ec.edu.espe.zone_core.dto.SpacesRequestDto;
import ec.edu.espe.zone_core.service.SpacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spaces")
public class SpacesViewController {

    @Autowired
    private SpacesService spacesService;

    @GetMapping
    public String spaces(Model model) {
        model.addAttribute("title", "Espacios");
        model.addAttribute("view", "spaces/index");
        model.addAttribute("script", "/js/spaces.js");
        model.addAttribute("spaces", spacesService.getAllSpaces());
        return "layouts/main";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("space", new SpacesRequestDto());
        model.addAttribute("title", "Nuevo Espacio");
        model.addAttribute("view", "spaces/create");
        model.addAttribute("script", "/js/spaces.js");
        return "layouts/main";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "OK MVC";
    }


}
