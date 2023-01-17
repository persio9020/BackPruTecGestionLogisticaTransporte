package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.ClienteResponse;
import com.transporte.logistica.model.dto.TipoProductoResponse;
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
public interface TipoProductoResponseMapper {
  @Mappings({
    @Mapping(source = "tipoProducto.id", target = "id"),
    @Mapping(source = "tipoProducto.nombre", target = "nombre"),
  })
  TipoProductoResponse tipoProductoToTipoProductoResponse(TipoProducto tipoProducto);

  List<TipoProductoResponse> TiposProductosToTiposProductosResponseList(List<TipoProducto> tipoProducto);
}
