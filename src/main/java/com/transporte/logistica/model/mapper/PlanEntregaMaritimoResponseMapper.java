package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.PlanEntregaMaritimoResponse;
import com.transporte.logistica.model.entities.PlanEntrega;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface PlanEntregaMaritimoResponseMapper {
  @Mappings({
    @Mapping(source = "planEntrega.id", target = "id"),
    @Mapping(source = "planEntrega.tipoProductoId.nombre", target = "nombreTipoProducto"),
    @Mapping(source = "planEntrega.cantidad", target = "cantidad"),
    @Mapping(source = "planEntrega.fechaRegistro", target = "fechaRegistro"),
    @Mapping(source = "planEntrega.fechaEntrega", target = "fechaEntrega"),
    @Mapping(source = "planEntrega.puertoEntregaId.nombre", target = "puerto"),
    @Mapping(source = "planEntrega.precioEnvio", target = "precioEnvio"),
    @Mapping(source = "planEntrega.numeroFlota", target = "numeroFlota"),
    @Mapping(source = "planEntrega.numeroGuia", target = "numeroGuia"),
    @Mapping(source = "planEntrega.clienteId.nombreCompleto", target = "nombreCompletoCliente"),
  })
  PlanEntregaMaritimoResponse planEntregaToPlanEntregaResponse(PlanEntrega planEntrega);

  List<PlanEntregaMaritimoResponse> planEntregaToPlanesEntregaResponseList(List<PlanEntrega> planesEntrega);
}
