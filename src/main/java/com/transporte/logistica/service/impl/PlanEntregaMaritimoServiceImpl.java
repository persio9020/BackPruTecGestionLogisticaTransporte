package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.PlanEntregaMaritimoRequest;
import com.transporte.logistica.model.dto.PlanEntregaMaritimoResponse;
import com.transporte.logistica.model.entities.Cliente;
import com.transporte.logistica.model.entities.PlanEntrega;
import com.transporte.logistica.model.entities.Puerto;
import com.transporte.logistica.model.entities.TipoProducto;
import com.transporte.logistica.model.mapper.PlanEntregaMaritimoResponseMapper;
import com.transporte.logistica.repository.PlanEntregaRepository;
import com.transporte.logistica.service.PlanEntregaMaritimoService;
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
public class PlanEntregaMaritimoServiceImpl implements PlanEntregaMaritimoService {

  private PlanEntregaRepository planEntregaRepository;
  private PlanEntregaMaritimoResponseMapper planEntregaMaritimoResponseMapper;
  Logger LOGGER = LoggerFactory.getLogger(PlanEntregaMaritimoServiceImpl.class);

  public PlanEntregaMaritimoServiceImpl(PlanEntregaRepository planEntregaRepository, PlanEntregaMaritimoResponseMapper planEntregaMaritimoResponseMapper) {
    this.planEntregaRepository = planEntregaRepository;
    this.planEntregaMaritimoResponseMapper = planEntregaMaritimoResponseMapper;
  }

  @Override
  public Mono<ResponseEntity<String>> agregarPlanEntrega(Mono<PlanEntregaMaritimoRequest> planEntregaRequest) {
    return planEntregaRequest
            .flatMap(this::validarPlanEntrega)
            .flatMap(this::guardarPlanEntrega)
            .onErrorResume(BusinessRuleException.class, error -> {
              LOGGER.info(error.getMessage());
              return Mono.just(ResponseEntity.status(error.getHttpStatus()).body(error.getMessage()));
            });
  }

  @Override
  public Mono<PlanEntregaMaritimoRequest> validarPlanEntrega(PlanEntregaMaritimoRequest planEntregaRequest) {
    return Mono.just(planEntregaRequest);
  }

  @Override
  public Mono<ResponseEntity<String>> guardarPlanEntrega(PlanEntregaMaritimoRequest planEntregaRequest) {
    try {
      PlanEntrega planEntrega = new PlanEntrega();
      planEntrega.setCantidad(planEntregaRequest.getCantidad());
      planEntrega.setFechaEntrega(planEntregaRequest.getFechaEntrega());
      planEntrega.setPuertoEntregaId(new Puerto(planEntregaRequest.getIdPuertoEntrega()));
      planEntrega.setPrecioEnvio(planEntregaRequest.getPrecioEnvio());
      planEntrega.setNumeroFlota(planEntregaRequest.getNumeroFlota());
      planEntrega.setNumeroGuia(planEntregaRequest.getNumeroGuia());
      planEntrega.setClienteId(new Cliente(planEntregaRequest.getIdCliente()));
      planEntrega.setTipoProductoId(new TipoProducto(planEntregaRequest.getIdTipoProducto()));
      this.planEntregaRepository.save(planEntrega);
      return Mono.just(ResponseEntity.ok("Plan de entrega guardado correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<PlanEntregaMaritimoResponse>>> listarTodos() {
    try {
    List<PlanEntregaMaritimoResponse> pe = this.planEntregaMaritimoResponseMapper.planEntregaToPlanesEntregaResponseList(planEntregaRepository.findAll());
      return Mono.just(ResponseEntity.ok(pe));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<PlanEntregaMaritimoResponse>()));
    }
  }

}
