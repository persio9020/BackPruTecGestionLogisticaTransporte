package com.transporte.logistica.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
public class ClienteResponse {
  private Long id;
  @Schema(description = "Nombre completo del cliente")
  private String nombres;
  @Schema(example = "45", description = "Apellidos del cliente")
  private String apellidos;
  @Schema(description = "Numero de identificación del cliente")
  private String identificacion;
  @Schema(description = "sexo cliente")
  private String sexo;
  @Schema(description = "Número de telefono del cliente")
  private String telefono;
  @Schema(description = "Número de celular del cliente")
  private String celular;
  @Schema(description = "Direccion de residencia del cliente")
  private String direccion;
  @Schema(description = "Correo electrónico del cliente")
  private String correo;
  @Schema(description = "Fecha de nacimiento de cliente")
  private Date fechaNacimiento;
  @Schema(description = "id tipo de identificación de cliente")
  private String tipoIdentificacion;
}
