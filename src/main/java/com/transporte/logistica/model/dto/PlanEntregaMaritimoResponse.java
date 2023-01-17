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
public class PlanEntregaMaritimoResponse {

  private Integer id;
  @Schema(example = "Mesa", description = "Nombre del producto")
  private String nombreTipoProducto;
  @Schema(example = "5", description = "Cantidad del producto")
  private Integer cantidad;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de registro")
  private Date fechaRegistro;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de entrega")
  private Date fechaEntrega;
  @Schema(example = "Puerto 1", description = "Puerto en el que debe entregarse")
  private String puerto;
  @Schema(example = "10000", description = "Precio del envio")
  private BigDecimal precioEnvio;
  @Schema(example = "10000", description = "Precio del envio con descuento")
  private BigDecimal descuento;
  @Schema(example = "HEL7958C", description = "Número del flota del barco")
  private String numeroFlota;
  @Schema(example = "4AD8FACS95", description = "Numero de Guia")
  private String numeroGuia;
  @Schema(example = "Jose", description = "Nombre del cliente")
  private String nombreCompletoCliente;
}
