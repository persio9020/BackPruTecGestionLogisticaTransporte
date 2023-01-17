package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.PaisResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.service.PaisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
@RestController
@RequestMapping("/pais")
public class PaisController {

  @Autowired
  private PaisService paisService;

  @GetMapping("/listar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<OptionResponse>>> listar() {
    return paisService.listarTodos();
  }
}
