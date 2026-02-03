package ec.edu.espe.zone_core.controller;

import ec.edu.espe.zone_core.service.SpacesService;
import ec.edu.espe.zone_core.service.ZoneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardViewController {

    @Autowired
    private SpacesService spacesService;

    @Autowired
    private ZoneServices zoneServices;

    @GetMapping("/")
    public String index(Model model) {
        var spaces = spacesService.getAllSpaces();

        model.addAttribute("title", "Dashboard Principal");
        model.addAttribute("view", "dashboard");
        model.addAttribute("script", null);

        model.addAttribute("totalSpaces", spaces.size());
        model.addAttribute("availableSpaces", spaces.stream().filter(s -> "AVAILABLE".equals(s.getStatus())).count());
        model.addAttribute("totalZones", zoneServices.getAllZones().size());

        model.addAttribute("content", "dashboard :: content");
        return "layouts/main";
    }
}