package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class BodegaResponse {

  private Integer id;
  @Schema(example = "Bodega 1", description = "Nombre de la bodega")
  private String nombre;
  @Schema(example = "cr 1", description = "Dirección de la bodega")
  private String direccion;
  @Schema(example = "Colombia", description = "Dirección de la bodega")
  private String pais;
}
