package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreRequest;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreResponse;
import com.transporte.logistica.model.entities.Bodega;
import com.transporte.logistica.model.entities.Cliente;
import com.transporte.logistica.model.entities.PlanEntrega;
import com.transporte.logistica.model.entities.TipoLogisticaTransporte;
import com.transporte.logistica.model.entities.TipoProducto;
import com.transporte.logistica.model.mapper.PlanEntregaTerrestreResponseMapper;
import com.transporte.logistica.repository.PlanEntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.transporte.logistica.service.PlanEntregaTerrestreService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    if (planEntregaRepository.countByNumeroGuia(planEntregaRequest.getNumeroGuia())>0) {
      Exception ex = new BusinessRuleException(HttpStatus.BAD_REQUEST, "El numero de guía ya existe.");//400
      return Mono.error(ex);
    }
    String regex = "[a-zA-Z]{3}[0-9]{3}";
    Pattern pat = Pattern.compile(regex);
    Matcher mat = pat.matcher(planEntregaRequest.getPlacaVehiculo());
    if (planEntregaRequest.getPlacaVehiculo().length() != 6 || !mat.find()) {//422
      Exception ex = new BusinessRuleException(HttpStatus.UNPROCESSABLE_ENTITY, "El formato debe corresponder a 3 letras iniciales y 3 números finales.");
      return Mono.error(ex);
    }
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
      planEntrega.setTipoLogisticaTransporteId(new TipoLogisticaTransporte(Short.valueOf("1")));
      planEntrega.setFechaRegistro(new Date());
      //Aplicacion del descuento del 5% Si la cantidad de productos es superior a 10 unidades
      if(planEntrega.getCantidad()>10){
        BigDecimal descuento = planEntrega.getPrecioEnvio().multiply(BigDecimal.valueOf(0.05));
        planEntrega.setDescuento(descuento);
      }else{
        planEntrega.setDescuento(BigDecimal.ZERO);
      }
      this.planEntregaRepository.save(planEntrega);
      return Mono.just(ResponseEntity.ok("Plan de entrega terrestre guardada correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<PlanEntregaTerrestreResponse>>> listarTodos() {
    try {
      TipoLogisticaTransporte tipoLogisticaTransporteId = new TipoLogisticaTransporte(Short.valueOf("1"));
      List<PlanEntregaTerrestreResponse> pe = this.planEntregaTerrestreResponseMapper.planEntregaToPlanesEntregaResponseList(planEntregaRepository.findPlanEntregaBytipoLogisticaTransporteId(tipoLogisticaTransporteId));
      return Mono.just(ResponseEntity.ok(pe));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>()));
    }
  }

}
