package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.model.entities.Pais;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface PaisResponseMapper {

  @Mappings({
    @Mapping(source = "pais.id", target = "value"),
    @Mapping(source = "pais.nombre", target = "label")
  })
  OptionResponse paisToPaisResponse(Pais pais);

  List<OptionResponse> paisToPaisesResponseList(List<Pais> paises);
}
