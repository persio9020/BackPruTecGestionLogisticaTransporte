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
public class PlanEntregaMaritimoRequest {
  @Schema(example = "5", description = "Cantidad del producto")
  private Integer cantidad;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de registro")
  private Date fechaRegistro;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de entrega")
  private Date fechaEntrega;
  @Schema(example = "1", description = "id del puerto en el que debe entregarse")
  private Integer idPuertoEntrega;
  @Schema(example = "10000", description = "Precio del envio")
  private BigDecimal precioEnvio;
  @Schema(example = "HEL7958C", description = "Número del flota del barco")
  private String numeroFlota;
  @Schema(example = "4AD8FACS95", description = "Numero de Guia")
  private String numeroGuia;
  @Schema(example = "1", description = "Id del cliente")
  private Long idCliente;
  @Schema(example = "1", description = "Id del producto")
  private Integer idTipoProducto;
}
