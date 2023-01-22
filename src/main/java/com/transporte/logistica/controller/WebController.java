package com.transporte.logistica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/index")
  public String index(final Model model) {
    return "index";
  }
}
