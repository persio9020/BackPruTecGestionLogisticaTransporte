package com.transporte.logistica.model.dto.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author persi
 */
@Data
public class OptionResponse {
  @Schema(example = "1", description = "Valor del select")
  private Integer value;
  @Schema(example = "Colombia", description = "texto del select")
  private String label;
}
