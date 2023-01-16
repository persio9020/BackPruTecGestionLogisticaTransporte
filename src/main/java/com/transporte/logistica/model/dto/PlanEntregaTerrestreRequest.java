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
public class PlanEntregaTerrestreRequest {
  @Schema(example = "5", description = "Cantidad del producto")
  private Integer cantidad;
  @Schema(example = "2023-01-15T10:28:39.816Z", description = "Fecha de entrega")
  private Date fechaEntrega;
  @Schema(example = "1", description = "id de la Bodega en la que debe entregarse")
  private Integer idBodegaEntrega;
  @Schema(example = "10000", description = "Precio del envio")
  private BigDecimal precioEnvio;
  @Schema(example = "HEL795", description = "Placa que lleva el producto debe corresponder a 3 letras iniciales y 3 números finales")
  private String placaVehiculo;
  @Schema(example = "4AD8FACS95", description = "Numero de Guia")
  private String numeroGuia;
  @Schema(example = "1", description = "Id del cliente")
  private Long idCliente;
  @Schema(example = "1", description = "Id del producto")
  private Integer idTipoProducto;
}
