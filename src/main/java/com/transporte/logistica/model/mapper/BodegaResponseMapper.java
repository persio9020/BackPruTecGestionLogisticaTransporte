package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.BodegaResponse;
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
public interface BodegaResponseMapper {
  @Mappings({
    @Mapping(source = "bodega.id", target = "id"),
    @Mapping(source = "bodega.nombre", target = "nombre")
  })
  BodegaResponse bodegaToBodegaResponse(Bodega bodega);

  List<BodegaResponse> bodegaToBodegasResponseList(List<Bodega> bodegas);
}
