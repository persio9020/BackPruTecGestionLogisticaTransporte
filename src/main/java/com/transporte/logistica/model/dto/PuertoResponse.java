package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class PuertoResponse {
  private Integer id;
  @Schema(example = "Pueto 1", description = "Nombre de la bodega")
  private String nombre;
  @Schema(example = "Cartagena", description = "Nombre de la bodega")
  private String ubicacion;
  @Schema(example = "Colombia", description = "pais")
  private String pais;
}
