package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class BodegaRequest {

  @Schema(example = "Bodega 1", description = "Nombre de la bodega")
  private String nombre;
  @Schema(example = "Cr 1 #10", description = "Nombre de la bodega")
  private String direccion;
  @Schema(example = "1", description = "id del pais")
  private Integer idPais;
}
