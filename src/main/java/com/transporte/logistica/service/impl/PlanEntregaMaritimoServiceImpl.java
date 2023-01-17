package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.PlanEntregaMaritimoRequest;
import com.transporte.logistica.model.dto.PlanEntregaMaritimoResponse;
import com.transporte.logistica.model.entities.Cliente;
import com.transporte.logistica.model.entities.PlanEntrega;
import com.transporte.logistica.model.entities.Puerto;
import com.transporte.logistica.model.entities.TipoLogisticaTransporte;
import com.transporte.logistica.model.entities.TipoProducto;
import com.transporte.logistica.model.mapper.PlanEntregaMaritimoResponseMapper;
import com.transporte.logistica.repository.PlanEntregaRepository;
import com.transporte.logistica.service.PlanEntregaMaritimoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    Exception ex;
    if (planEntregaRepository.countByNumeroGuia(planEntregaRequest.getNumeroGuia())>0) {
      ex = new BusinessRuleException(HttpStatus.BAD_REQUEST, "El numero de guía ya existe.");//400
      return Mono.error(ex);
    }
    String regex = "[a-zA-Z]{3}[0-9]{4}[a-zA-Z]{1}";
    Pattern pat = Pattern.compile(regex);
    Matcher mat = pat.matcher(planEntregaRequest.getNumeroFlota());
    if (planEntregaRequest.getNumeroFlota().length() != 8 || !mat.find()) {//422
      ex = new BusinessRuleException(HttpStatus.UNPROCESSABLE_ENTITY, "El formato debe corresponder a 3 letras iniciales, seguidas de 4 números y finalizando con una letra.");
      return Mono.error(ex);
    }
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
      planEntrega.setTipoLogisticaTransporteId(new TipoLogisticaTransporte(Short.valueOf("2")));
      planEntrega.setFechaRegistro(new Date());
      //Aplicacion del descuento del 3% Si la cantidad de productos es superior a 10 unidades
      if (planEntrega.getCantidad() > 10) {
        planEntrega.setDescuento(planEntrega.getPrecioEnvio().multiply(BigDecimal.valueOf(0.03)));
      } else {
        planEntrega.setDescuento(BigDecimal.ZERO);
      }
      this.planEntregaRepository.save(planEntrega);
      return Mono.just(ResponseEntity.ok("Plan de entrega guardado correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<PlanEntregaMaritimoResponse>>> listarTodos() {
    try {
      TipoLogisticaTransporte tipoLogisticaTransporteId = new TipoLogisticaTransporte(Short.valueOf("2"));
      List<PlanEntregaMaritimoResponse> pe = this.planEntregaMaritimoResponseMapper.planEntregaToPlanesEntregaResponseList(planEntregaRepository.findPlanEntregaBytipoLogisticaTransporteId(tipoLogisticaTransporteId));
      return Mono.just(ResponseEntity.ok(pe));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>()));
    }
  }

}
