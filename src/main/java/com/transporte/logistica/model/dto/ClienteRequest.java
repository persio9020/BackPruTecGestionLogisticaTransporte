package com.transporte.logistica.model.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hector Leon
 */
@Data
public class ClienteRequest {
  @Schema(example = "45", description = "Nombre completo del cliente")
  private String nombres;
  @Schema(example = "45", description = "Apellidos del cliente")
  private String apellidos;
  @Schema(example = "12", description = "Numero de identificación del cliente")
  private String identificacion;
  @Schema(example = "2", description = "Id sexo cliente")
  private Short sexoId;
  @Schema(example = "15", description = "Número de telefono del cliente")
  private String telefono;
  @Schema(example = "15", description = "Número de celular del cliente")
  private String celular;
  @Schema(example = "255", description = "Direccion de residencia del cliente")
  private String direccion;
  @Schema(example = "60", description = "Correo electrónico del cliente")
  private String correo;
  @Schema(example = "50", description = "Fecha de nacimiento de cliente")
  private Date fechaNacimiento;
  @Schema(example = "2", description = "id tipo de identificación de cliente")
  private Short idTipoIdentificacion;
}
