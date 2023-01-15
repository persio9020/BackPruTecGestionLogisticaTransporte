package com.transporte.logistica.service.impl;

import com.transporte.logistica.model.dto.ClienteRequest;
import com.transporte.logistica.model.entities.Cliente;
import com.transporte.logistica.model.entities.Sexo;
import com.transporte.logistica.model.entities.TipoIdentificacion;
import com.transporte.logistica.repository.ClienteRepository;
import com.transporte.logistica.service.ClienteService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 *
 * @author persi
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
	this.clienteRepository = clienteRepository;
    }

    @Override
    public Mono<ServerResponse> agregarCliente(Mono<ClienteRequest> clienteRequest) {
	return clienteRequest
		.flatMap(this::validarCliente)
		.flatMap(this::guardarCliente)
		.onErrorResume(ResponseStatusException.class, error -> {
		    LOGGER.info(error.getMessage());
		    return ServerResponse.status(error.getStatus()).bodyValue(error.getMessage());
		});
    }

    @Override
    public Mono<ClienteRequest> validarCliente(ClienteRequest clienteRequest) {
	return Mono.just(clienteRequest);
    }

    @Override
    public Mono<ServerResponse> guardarCliente(ClienteRequest cr) {
	Cliente cliente = new Cliente();
	cliente.setNombres(cr.getNombres());
	cliente.setApellidos(cr.getApellidos());
	cliente.setIdentificacion(cr.getIdentificacion());
	cliente.setSexoId(new Sexo(cr.getSexoId()));
	cliente.setTelefono(cr.getTelefono());
	cliente.setCelular(cr.getCelular());
	cliente.setDireccion(cr.getDireccion());
	cliente.setCorreo(cr.getCorreo());
	cliente.setFechaNacimiento(cr.getFechaNacimiento());
	cliente.setTipoIdentificacionId(new TipoIdentificacion(cr.getIdTipoIdentificacion()));
	this.clienteRepository.save(cliente);
	return ServerResponse.ok().body(Mono.just("Producto guardado correctamente en el carrito"), String.class);
    }

    @Override
    public Mono<ServerResponse> listarTodos() {
	return Mono.just(clienteRepository.findAll())
		.flatMap(p -> ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(p).switchIfEmpty(ServerResponse.notFound().build()));
    }

}
