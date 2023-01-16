package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.TipoProductoRequest;
import com.transporte.logistica.model.dto.TipoProductoResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 *
 * @author persi
 */
public interface TipoProductoService {

  public Mono<ResponseEntity<String>> agregarTipoProducto(Mono<TipoProductoRequest> tipoProductoRequest);

  public Mono<TipoProductoRequest> validarTipoProducto(TipoProductoRequest tipoProductoRequest);

  public Mono<ResponseEntity<String>> guardarTipoProducto(TipoProductoRequest tipoProductoRequest);

  public Mono<ResponseEntity<List<TipoProductoResponse>>> listarTodos();
}
