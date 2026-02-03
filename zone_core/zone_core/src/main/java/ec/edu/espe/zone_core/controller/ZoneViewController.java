package ec.edu.espe.zone_core.controller;

import ec.edu.espe.zone_core.dto.ZoneRequestDto;
import ec.edu.espe.zone_core.service.ZoneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zones")
public class ZoneViewController {
    @Autowired
    private ZoneServices zoneServices;

    @GetMapping
    public String listZones(Model model) {

        model.addAttribute("title", "Gestión de Zonas");
        model.addAttribute("content", "zones/index :: content");
        model.addAttribute("script", "/js/zones.js");
        model.addAttribute("zones", zoneServices.getAllZones());
        return "layouts/main";
    }

    @GetMapping("/create")
    public String createZoneForm(Model model) {
        model.addAttribute("zone", new ZoneRequestDto());
        model.addAttribute("view", "zones/create");
        model.addAttribute("script", "/js/zones.js");
        return "layouts/main";
    }
}