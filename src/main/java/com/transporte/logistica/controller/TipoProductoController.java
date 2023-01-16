package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.TipoProductoRequest;
import com.transporte.logistica.model.dto.TipoProductoResponse;
import com.transporte.logistica.service.TipoProductoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
@RestController
@RequestMapping("/tipo-producto")
public class TipoProductoController {

  @Autowired
  private TipoProductoService tipoProductoService;

  @PostMapping("/agregar")
  @PreAuthorize("hasRole('ADMIN')")
  public Mono<ResponseEntity<String>> agregarTipoProducto(TipoProductoRequest request) {
    Mono<TipoProductoRequest> tipoProducto = Mono.just(request);
    return tipoProductoService.agregarTipoProducto(tipoProducto);
  }

  @GetMapping("/listar")
  @PreAuthorize("hasRole('ADMIN')")
  @ApiResponses(value = {
    @ApiResponse(code = 401, message = "Solicitud no autorizada al recurso."),
    @ApiResponse(code = 422, message = "Debido a errores semánticos no puede ser procesada."),
    @ApiResponse(code = 400, message = "Bad Request El servidor no puede o no procesará la petición debido a algo que es percibido como un error del cliente."),
    @ApiResponse(code = 500, message = "El servidor encontró una condición inesperada que le impide completar la petición.")
  })
  public Mono<ResponseEntity<List<TipoProductoResponse>>> listar() {
    return tipoProductoService.listarTodos();
  }
}
