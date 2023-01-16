package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreRequest;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreResponse;
import com.transporte.logistica.model.entities.Bodega;
import com.transporte.logistica.model.entities.Cliente;
import com.transporte.logistica.model.entities.PlanEntrega;
import com.transporte.logistica.model.entities.TipoProducto;
import com.transporte.logistica.model.mapper.PlanEntregaTerrestreResponseMapper;
import com.transporte.logistica.repository.PlanEntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.transporte.logistica.service.PlanEntregaTerrestreService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Hector Leon
 */
@Service
public class PlanEntregaTerrestreServiceImpl implements PlanEntregaTerrestreService {

  private PlanEntregaRepository planEntregaRepository;
  private PlanEntregaTerrestreResponseMapper planEntregaTerrestreResponseMapper;
  Logger LOGGER = LoggerFactory.getLogger(PlanEntregaTerrestreServiceImpl.class);

  public PlanEntregaTerrestreServiceImpl(PlanEntregaRepository planEntregaRepository, PlanEntregaTerrestreResponseMapper planEntregaTerrestreResponseMapper) {
    this.planEntregaRepository = planEntregaRepository;
    this.planEntregaTerrestreResponseMapper = planEntregaTerrestreResponseMapper;
  }

  @Override
  public Mono<ResponseEntity<String>> agregarPlanEntrega(Mono<PlanEntregaTerrestreRequest> planEntregaRequest) {
    return planEntregaRequest
            .flatMap(this::validarPlanEntrega)
            .flatMap(this::guardarPlanEntrega)
            .onErrorResume(BusinessRuleException.class, error -> {
              LOGGER.info(error.getMessage());
              return Mono.just(ResponseEntity.status(error.getHttpStatus()).body(error.getMessage()));
            });
  }

  @Override
  public Mono<PlanEntregaTerrestreRequest> validarPlanEntrega(PlanEntregaTerrestreRequest planEntregaRequest) {
    return Mono.just(planEntregaRequest);
  }

  @Override
  public Mono<ResponseEntity<String>> guardarPlanEntrega(PlanEntregaTerrestreRequest planEntregaRequest) {
    try {
      PlanEntrega planEntrega = new PlanEntrega();
      planEntrega.setCantidad(planEntregaRequest.getCantidad());
      planEntrega.setFechaEntrega(planEntregaRequest.getFechaEntrega());
      planEntrega.setBodegaEntregaId(new Bodega(planEntregaRequest.getIdBodegaEntrega()));
      planEntrega.setPrecioEnvio(planEntregaRequest.getPrecioEnvio());
      planEntrega.setPlacaVehiculo(planEntregaRequest.getPlacaVehiculo());
      planEntrega.setNumeroGuia(planEntregaRequest.getNumeroGuia());
      planEntrega.setClienteId(new Cliente(planEntregaRequest.getIdCliente()));
      planEntrega.setTipoProductoId(new TipoProducto(planEntregaRequest.getIdTipoProducto()));
      this.planEntregaRepository.save(planEntrega);
      return Mono.just(ResponseEntity.ok("Plan de entrega terrestre guardada correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<PlanEntregaTerrestreResponse>>> listarTodos() {
    try {
      List<PlanEntregaTerrestreResponse> pe = this.planEntregaTerrestreResponseMapper.planEntregaToPlanesEntregaResponseList(planEntregaRepository.findAll());
      return Mono.just(ResponseEntity.ok(pe));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<PlanEntregaTerrestreResponse>()));
    }
  }

}
