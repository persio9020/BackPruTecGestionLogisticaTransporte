package com.transporte.logistica.service.impl;

import com.transporte.logistica.exception.BusinessRuleException;
import com.transporte.logistica.model.dto.ClienteRequest;
import com.transporte.logistica.model.dto.ClienteResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.model.entities.Cliente;
import com.transporte.logistica.model.entities.Sexo;
import com.transporte.logistica.model.entities.TipoIdentificacion;
import com.transporte.logistica.model.mapper.ClientesResponseMapper;
import com.transporte.logistica.model.mapper.ClientesResponseOptionsMapper;
import com.transporte.logistica.repository.ClienteRepository;
import com.transporte.logistica.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
public class ClienteServiceImpl implements ClienteService {

  private ClienteRepository clienteRepository;
  private ClientesResponseMapper clientesResponseMapper;
  ClientesResponseOptionsMapper clientesResponseOptionsMapper;
  Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

  @Autowired
  public ClienteServiceImpl(ClienteRepository clienteRepository, ClientesResponseMapper clientesResponseMapper, ClientesResponseOptionsMapper clientesResponseOptionsMapper) {
    this.clienteRepository = clienteRepository;
    this.clientesResponseMapper = clientesResponseMapper;
    this.clientesResponseOptionsMapper = clientesResponseOptionsMapper;
  }

  @Override
  public Mono<ResponseEntity<String>> agregarCliente(Mono<ClienteRequest> clienteRequest) {
    return clienteRequest
            .flatMap(this::validarCliente)
            .flatMap(this::guardarCliente)
            .onErrorResume(BusinessRuleException.class, error -> {
              LOGGER.info(error.getMessage());
              return Mono.just(ResponseEntity.status(error.getHttpStatus()).body(error.getMessage()));
            });
  }

  @Override
  public Mono<ClienteRequest> validarCliente(ClienteRequest clienteRequest) {
    return Mono.just(clienteRequest);
  }

  @Override
  public Mono<ResponseEntity<String>> guardarCliente(ClienteRequest clienteRequest) {
    try {
      Cliente cliente = new Cliente();
      cliente.setNombres(clienteRequest.getNombres());
      cliente.setApellidos(clienteRequest.getApellidos());
      cliente.setIdentificacion(clienteRequest.getIdentificacion());
      cliente.setSexoId(new Sexo(clienteRequest.getIdSexo()));
      cliente.setTelefono(clienteRequest.getTelefono());
      cliente.setCelular(clienteRequest.getCelular());
      cliente.setDireccion(clienteRequest.getDireccion());
      cliente.setCorreo(clienteRequest.getCorreo());
      cliente.setFechaNacimiento(clienteRequest.getFechaNacimiento());
      cliente.setTipoIdentificacionId(new TipoIdentificacion(clienteRequest.getIdTipoIdentificacion()));
      this.clienteRepository.save(cliente);
      return Mono.just(ResponseEntity.ok("Cliente guardado correctamente"));
    } catch (Exception ex) {
      throw new BusinessRuleException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno porfavor intentelo mas tarde.");
    }
  }

  @Override
  public Mono<ResponseEntity<List<ClienteResponse>>> listarTodos() {
    try {
      List<ClienteResponse> l = this.clientesResponseMapper.clienteToClientesResponseList(clienteRepository.findAll());
      return Mono.just(ResponseEntity.ok(l));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<ClienteResponse>()));
    }

  }

  @Override
  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions() {
    try {
      List<OptionResponse> l = this.clientesResponseOptionsMapper.clienteToClientesResponseList(clienteRepository.findAll());
      return Mono.just(ResponseEntity.ok(l));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<OptionResponse>()));
    }
  }

}
