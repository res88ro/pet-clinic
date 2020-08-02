package ro.sda.PetClinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "despre-noi";
    }

    @GetMapping("/despre-noi")
    public String despreNoi() {
        return "despre-noi";
    }

    @GetMapping("/anestezie")
    public String anestezie() {
        return "anestezie";
    }

    @GetMapping("/ingrijire")
    public String ingrijire() {
        return "ingrijire";
    }


    @GetMapping("/stomatologie")
    public String stomatologie() {
        return "stomatologie";
    }
}
