package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.TipoProductoRequest;
import com.transporte.logistica.model.dto.TipoProductoResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.service.TipoProductoService;
import com.transporte.logistica.repository.TipoProductoRepository;
import com.transporte.logistica.model.entities.TipoProducto;
import com.transporte.logistica.model.mapper.TipoProductoResponseMapper;
import com.transporte.logistica.model.mapper.TipoProductoResponseOptionsMapper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

  private TipoProductoRepository tipoProductoRepositoy;
  private TipoProductoResponseMapper tipoProductoResponseMapper;
  private TipoProductoResponseOptionsMapper tipoProductoResponseOptionsMapper;
  
  Logger LOGGER = LoggerFactory.getLogger(TipoProductoServiceImpl.class);
  @Autowired
  public TipoProductoServiceImpl(TipoProductoRepository tipoProductoRepositoy, TipoProductoResponseMapper tipoProductoResponseMapper, TipoProductoResponseOptionsMapper tipoProductoResponseOptionsMapper) {
    this.tipoProductoRepositoy = tipoProductoRepositoy;
    this.tipoProductoResponseMapper = tipoProductoResponseMapper;
    this.tipoProductoResponseOptionsMapper = tipoProductoResponseOptionsMapper;
  }

  @Override
  public Mono<ResponseEntity<String>> agregarTipoProducto(Mono<TipoProductoRequest> tipoProductoRequest) {
    return tipoProductoRequest
            .flatMap(this::validarTipoProducto)
            .flatMap(this::guardarTipoProducto)
            .onErrorResume(BusinessRuleException.class, error -> {
		    LOGGER.info(error.getMessage());
		    return Mono.just(ResponseEntity.status(error.getHttpStatus()).body(error.getMessage()));
		});
  }

  @Override
  public Mono<TipoProductoRequest> validarTipoProducto(TipoProductoRequest tipoProductoRequest) {
    return Mono.just(tipoProductoRequest);
  }

  @Override
  public Mono<ResponseEntity<String>> guardarTipoProducto(TipoProductoRequest tipoProductoRequest) {
    TipoProducto tipoProducto = new TipoProducto();
    tipoProducto.setNombre(tipoProductoRequest.getNombre());
    this.tipoProductoRepositoy.save(tipoProducto);
    return Mono.just(ResponseEntity.ok("Tipo de producto guardado correctamente"));
  }

  @Override
  public Mono<ResponseEntity<List<TipoProductoResponse>>> listarTodos() {
    try {
      List<TipoProductoResponse> l = this.tipoProductoResponseMapper.TiposProductosToTiposProductosResponseList(tipoProductoRepositoy.findAll());
      return Mono.just(ResponseEntity.ok(l));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<TipoProductoResponse>()));
    }
  }

  @Override
  public Mono<ResponseEntity<List<OptionResponse>>> listarTodosSelect() {
    try {
      List<OptionResponse> l = this.tipoProductoResponseOptionsMapper.TiposProductosToTiposProductosResponseList(tipoProductoRepositoy.findAll());
      return Mono.just(ResponseEntity.ok(l));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<OptionResponse>()));
    }
  }

}
