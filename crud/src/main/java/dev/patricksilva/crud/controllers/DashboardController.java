package dev.patricksilva.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("salesData", Map.of(
                "labels", new String[]{"January", "February", "March", "April", "May"},
                "values", new int[]{1500, 2000, 1800, 2200, 1700}
        ));
        model.addAttribute("topProducts", List.of(
                Map.of("name", "Product A", "quantitySold", 120, "totalRevenue", 2400),
                Map.of("name", "Product B", "quantitySold", 100, "totalRevenue", 2000),
                Map.of("name", "Product C", "quantitySold", 80, "totalRevenue", 1600)
        ));
        return "dashboard";
    }
}