package com.transporte.logistica.model.mapper;

import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.model.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;
/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface ClientesResponseOptionsMapper {

  @Mappings({
    @Mapping(source = "cliente.id", target = "value"),
    @Mapping(source = "cliente.label", target = "label"),
  })
  OptionResponse clienteToClienteResponse(Cliente cliente);

  List<OptionResponse> clienteToClientesResponseList(List<Cliente> carritos);
}
