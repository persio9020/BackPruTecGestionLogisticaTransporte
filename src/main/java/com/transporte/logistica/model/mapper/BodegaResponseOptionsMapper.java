package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.model.entities.Bodega;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface BodegaResponseOptionsMapper {
  @Mappings({
    @Mapping(source = "bodega.id", target = "value"),
    @Mapping(source = "bodega.nombre", target = "label")
  })
  OptionResponse bodegaToBodegaResponse(Bodega bodega);

  List<OptionResponse> bodegaToBodegasResponseList(List<Bodega> bodegas);
}
