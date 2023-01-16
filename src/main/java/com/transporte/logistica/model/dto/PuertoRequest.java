package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class PuertoRequest {
  @Schema(example = "Pueto 1", description = "Nombre de la bodega")
  private String nombre;
  @Schema(example = "Cartagena", description = "Nombre de la bodega")
  private String ubicacion;
  @Schema(example = "1", description = "id del pais")
  private Integer idPais;
}
