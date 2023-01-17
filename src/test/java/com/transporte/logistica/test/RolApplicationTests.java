package com.transporte.logistica.test;

import com.transporte.logistica.model.entities.Rol;
import com.transporte.logistica.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class RolApplicationTests {
  
  @Autowired
  private RolRepository repository;

  /**
   * Prueba unitaria para varificar que haya datos
   */
  @Test
  void testDataUser() {
    Rol rol = repository.findById(Short.valueOf("2")).get();
    Assertions.assertThat(rol.getNombre()).isEqualTo("ROLE_USER");
  }
  
}
