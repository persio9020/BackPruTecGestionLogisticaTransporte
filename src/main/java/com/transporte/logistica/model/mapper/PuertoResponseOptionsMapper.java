package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.PuertoResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
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
public interface PuertoResponseOptionsMapper {
  @Mappings({
    @Mapping(source = "puerto.id", target = "value"),
    @Mapping(source = "puerto.nombre", target = "label")
  })
  OptionResponse puertoToPuertoResponse(Puerto puerto);

  List<OptionResponse> puertosToPuertosResponseList(List<Puerto> puerto);
}
