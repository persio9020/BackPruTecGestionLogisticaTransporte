package com.transporte.logistica.service.impl;

import com.transporte.logistica.model.dto.PaisResponse;
import com.transporte.logistica.model.mapper.PaisResponseMapper;
import com.transporte.logistica.repository.PaisRepository;
import com.transporte.logistica.service.PaisService;
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
public class PaisServiceImpl implements PaisService {

  private PaisRepository paisRepository;
  private PaisResponseMapper paisResponseMapper;
  Logger LOGGER = LoggerFactory.getLogger(PaisServiceImpl.class);

  @Autowired
  public PaisServiceImpl(PaisRepository paisRepository, PaisResponseMapper paisResponseMapper) {
    this.paisRepository = paisRepository;
    this.paisResponseMapper = paisResponseMapper;
  }

  @Override
  public Mono<ResponseEntity<List<PaisResponse>>> listarTodos() {
    try {
      List<PaisResponse> p = this.paisResponseMapper.paisToPaisesResponseList(paisRepository.findAll());
      return Mono.just(ResponseEntity.ok(p));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<PaisResponse>()));
    }
  }

}
