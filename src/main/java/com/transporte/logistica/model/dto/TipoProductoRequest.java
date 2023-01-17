package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class TipoProductoRequest {

  @Schema(example = "Jabon", maxLength = 100, description = "Nombre del tipo de producto")
  private String nombre;
}
