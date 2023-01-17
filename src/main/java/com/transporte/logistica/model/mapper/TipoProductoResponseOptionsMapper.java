package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.model.entities.TipoProducto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface TipoProductoResponseOptionsMapper {
  @Mappings({
    @Mapping(source = "tipoProducto.id", target = "value"),
    @Mapping(source = "tipoProducto.nombre", target = "label"),
  })
  OptionResponse tipoProductoToTipoProductoResponse(TipoProducto tipoProducto);

  List<OptionResponse> TiposProductosToTiposProductosResponseList(List<TipoProducto> tipoProducto);
}
