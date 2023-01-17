package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.PlanEntregaTerrestreResponse;
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
public interface PlanEntregaTerrestreResponseMapper {
  @Mappings({
    @Mapping(source = "planEntrega.id", target = "id"),
    @Mapping(source = "planEntrega.tipoProductoId.nombre", target = "nombreTipoProducto"),
    @Mapping(source = "planEntrega.cantidad", target = "cantidad"),
    @Mapping(source = "planEntrega.fechaRegistro", target = "fechaRegistro"),
    @Mapping(source = "planEntrega.fechaEntrega", target = "fechaEntrega"),
    @Mapping(source = "planEntrega.bodegaEntregaId.nombre", target = "bodega"),
    @Mapping(source = "planEntrega.precioEnvio", target = "precioEnvio"),
    @Mapping(source = "planEntrega.descuento", target = "descuento"),
    @Mapping(source = "planEntrega.placaVehiculo", target = "placaVehiculo"),
    @Mapping(source = "planEntrega.numeroGuia", target = "numeroGuia"),
    @Mapping(source = "planEntrega.clienteId.nombreCompleto", target = "nombreCompletoCliente"),
  })
  PlanEntregaTerrestreResponse planEntregaToPlanEntregaResponse(PlanEntrega planEntrega);

  List<PlanEntregaTerrestreResponse> planEntregaToPlanesEntregaResponseList(List<PlanEntrega> planesEntrega);
}
