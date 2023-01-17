package com.transporte.logistica.controller;

import com.transporte.logistica.service.RegistreseService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author persi
 */
@RestController
@RequestMapping("/registrese")
public class RegistreseController {

  @Autowired
  private RegistreseService registreseService;

  @RequestMapping(value = "/guardar", method = RequestMethod.POST)
  public void existeNombreUsuario(@RequestBody Map<String, Object> datos) {
    registreseService.guardar(datos);
  }
}
