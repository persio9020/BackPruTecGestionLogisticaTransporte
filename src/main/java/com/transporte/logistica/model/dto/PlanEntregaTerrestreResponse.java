package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class PlanEntregaTerrestreResponse {

  private Integer id;
  @Schema(example = "Mesa", description = "Nombre del producto")
  private String nombreTipoProducto;
  @Schema(example = "5", description = "Cantidad del producto")
  private Integer cantidad;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de registro")
  private Date fechaRegistro;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de entrega")
  private Date fechaEntrega;
  @Schema(example = "Bodega 1", description = "Bodega en la que debe entregarse")
  private String bodega;
  @Schema(example = "10000", description = "Precio del envio")
  private BigDecimal precioEnvio;
  @Schema(example = "HEL795", description = "Placa del vehiculo")
  private String placaVehiculo;
  @Schema(example = "4AD8FACS95", description = "Numero de Guia")
  private String numeroGuia;
  @Schema(example = "Jose", description = "Nombre del cliente")
  private String nombreCompletoCliente;
}
