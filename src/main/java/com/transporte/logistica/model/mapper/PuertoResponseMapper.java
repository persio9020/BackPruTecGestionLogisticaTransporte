package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.PuertoResponse;
import com.transporte.logistica.model.entities.Puerto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface PuertoResponseMapper {
  @Mappings({
    @Mapping(source = "puerto.id", target = "id"),
    @Mapping(source = "puerto.nombre", target = "nombre"),
    @Mapping(source = "puerto.ubicacion", target = "ubicacion"),
    @Mapping(source = "puerto.paisId.nombre", target = "pais")
  })
  PuertoResponse puertoToPuertoResponse(Puerto puerto);

  List<PuertoResponse> puertosToPuertosResponseList(List<Puerto> puerto);
}
