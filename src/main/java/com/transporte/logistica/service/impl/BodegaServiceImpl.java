package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.BodegaRequest;
import com.transporte.logistica.model.dto.BodegaResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.model.entities.Bodega;
import com.transporte.logistica.model.entities.Pais;
import com.transporte.logistica.model.mapper.BodegaResponseMapper;
import com.transporte.logistica.model.mapper.BodegaResponseOptionsMapper;
import com.transporte.logistica.repository.BodegaRepository;
import com.transporte.logistica.service.BodegaService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
@Service
public class BodegaServiceImpl implements BodegaService {

  private BodegaRepository bodegaRepository;
  private BodegaResponseMapper bodegaResponseMapper;
  private BodegaResponseOptionsMapper bodegaResponseOptionsMapper;
  Logger LOGGER = LoggerFactory.getLogger(BodegaServiceImpl.class);

  @Autowired
  public BodegaServiceImpl(BodegaRepository bodegaRepository, BodegaResponseMapper bodegaResponseMapper, BodegaResponseOptionsMapper bodegaResponseOptionsMapper) {
    this.bodegaRepository = bodegaRepository;
    this.bodegaResponseMapper = bodegaResponseMapper;
    this.bodegaResponseOptionsMapper = bodegaResponseOptionsMapper;
  }

  @Override
  public Mono<ResponseEntity<String>> agregarBodega(Mono<BodegaRequest> bodegaRequest) {
    return bodegaRequest
            .flatMap(this::validarBodega)
            .flatMap(this::guardarBodega)
            .onErrorResume(BusinessRuleException.class, error -> {
              LOGGER.info(error.getMessage());
              return Mono.just(ResponseEntity.status(error.getHttpStatus()).body(error.getMessage()));
            });
  }

  @Override
  public Mono<BodegaRequest> validarBodega(BodegaRequest bodegaRequest) {
    return Mono.just(bodegaRequest);
  }

  @Override
  public Mono<ResponseEntity<String>> guardarBodega(BodegaRequest bodegaRequest) {
    try {
      Bodega bodega = new Bodega();
      bodega.setNombre(bodegaRequest.getNombre());
      bodega.setDireccion(bodegaRequest.getDireccion());
      bodega.setPaisId(new Pais(bodegaRequest.getIdPais()));
      this.bodegaRepository.save(bodega);
      return Mono.just(ResponseEntity.ok("Bodega guardada correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<BodegaResponse>>> listarTodos() {
    try {
      List<BodegaResponse> b = this.bodegaResponseMapper.bodegaToBodegasResponseList(bodegaRepository.findAll());
      return Mono.just(ResponseEntity.ok(b));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<BodegaResponse>()));
    }
  }

  @Override
  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions() {
    try {
      List<OptionResponse> b = this.bodegaResponseOptionsMapper.bodegaToBodegasResponseList(bodegaRepository.findAll());
      return Mono.just(ResponseEntity.ok(b));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<OptionResponse>()));
    }
  }

}
