package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.PuertoRequest;
import com.transporte.logistica.model.dto.PuertoResponse;
import com.transporte.logistica.model.entities.Pais;
import com.transporte.logistica.model.entities.Puerto;
import com.transporte.logistica.model.mapper.PuertoResponseMapper;
import com.transporte.logistica.repository.PuertoRepository;
import com.transporte.logistica.service.PuertoService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
@Service
public class PuertoServiceImpl implements PuertoService {

  private PuertoRepository puertoRepository;
  private PuertoResponseMapper puertoResponseMapper;
  Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

  public PuertoServiceImpl(PuertoRepository puertoRepository, PuertoResponseMapper puertoResponseMapper) {
    this.puertoRepository = puertoRepository;
    this.puertoResponseMapper = puertoResponseMapper;
  }

  @Override
  public Mono<ResponseEntity<String>> agregarPuerto(Mono<PuertoRequest> puertoRequest) {
    return puertoRequest
            .flatMap(this::validarPuerto)
            .flatMap(this::guardarPuerto)
            .onErrorResume(BusinessRuleException.class, error -> {
              LOGGER.info(error.getMessage());
              return Mono.just(ResponseEntity.status(error.getHttpStatus()).body(error.getMessage()));
            });
  }

  @Override
  public Mono<PuertoRequest> validarPuerto(PuertoRequest puertoRequest) {
    return Mono.just(puertoRequest);
  }

  @Override
  public Mono<ResponseEntity<String>> guardarPuerto(PuertoRequest puertoRequest) {
    try {
      Puerto puerto = new Puerto();
      puerto.setNombre(puertoRequest.getNombre());
      puerto.setUbicacion(puertoRequest.getUbicacion());
      puerto.setPaisId(new Pais(puertoRequest.getIdPais()));
      this.puertoRepository.save(puerto);
      return Mono.just(ResponseEntity.ok("Puerto guardado correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<PuertoResponse>>> listarTodos() {
    try {
      List<PuertoResponse> p = this.puertoResponseMapper.puertosToPuertosResponseList(puertoRepository.findAll());
      return Mono.just(ResponseEntity.ok(p));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<PuertoResponse>()));
    }
  }

}
