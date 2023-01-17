package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class PaisResponse {

  private Integer id;
  @Schema(example = "Colombia", description = "Nombre del país")
  private String nombre;
}
