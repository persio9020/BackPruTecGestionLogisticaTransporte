package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.PaisResponse;
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
    @Mapping(source = "pais.id", target = "id"),
    @Mapping(source = "pais.nombre", target = "nombre")
  })
  PaisResponse paisToPaisResponse(Pais pais);

  List<PaisResponse> paisToPaisesResponseList(List<Pais> paises);
}
