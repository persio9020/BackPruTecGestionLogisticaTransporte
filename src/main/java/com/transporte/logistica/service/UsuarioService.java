package com.transporte.logistica.service;

import com.transporte.logistica.config.User;
import reactor.core.publisher.Mono;

/**
 *
 * @author persi
 */
public interface UsuarioService {

  public Mono<User> findByUsername(String username);
}
