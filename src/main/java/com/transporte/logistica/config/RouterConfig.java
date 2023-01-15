package com.transporte.logistica.config;

import com.transporte.logistica.handler.ClienteHandler;
import com.transporte.logistica.service.impl.ClienteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
@Configuration
public class RouterConfig {
  @Bean
  public RouterFunction<?> rutas(ClienteHandler clienteHandler){
    return route().GET("/api/listarClientes", clienteHandler::listar, ops -> ops
        .operationId("listarClientes")
        .response(responseBuilder().responseCode("200").description("La solicitud ha tenido éxito."))
        .response(responseBuilder().responseCode("404").description("El servidor no pudo encontrar el contenido solicitado."))
    ).build()
    .and(route().POST("/api/agregarCliente", clienteHandler::agregarCliente,
        ops -> ops.beanClass(ClienteServiceImpl.class).beanMethod("agregarProductoCarritoCompras")).build())
    .and(route().GET("/api/listarClientes", clienteHandler::listar, ops -> ops
        .operationId("listarClientes")).build());
  }
}
