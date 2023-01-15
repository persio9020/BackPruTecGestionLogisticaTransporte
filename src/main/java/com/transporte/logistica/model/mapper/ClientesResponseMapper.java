package com.transporte.logistica.model.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.transporte.logistica.model.dto.ClienteResponse;
import com.transporte.logistica.model.entities.Cliente;
/**
 *
 * @author Hector Leon
 */
@Mapper(componentModel = "spring")
public interface ClientesResponseMapper {

  @Mappings({
    @Mapping(source = "cliente.id", target = "id"),
    @Mapping(source = "cliente.nombres", target = "nombres"),
    @Mapping(source = "cliente.apellidos", target = "apellidos"),
    @Mapping(source = "cliente.identificacion", target = "identificacion"),
    @Mapping(source = "cliente.sexoId.nombre", target = "sexo"),
    @Mapping(source = "cliente.telefono", target = "telefono"),
    @Mapping(source = "cliente.celular", target = "celular"),
    @Mapping(source = "cliente.direccion", target = "direccion"),
    @Mapping(source = "cliente.correo", target = "correo"),
    @Mapping(source = "cliente.fechaNacimiento", target = "fechaNacimiento")
  })
  ClienteResponse clienteToClienteResponse(Cliente cliente);

  List<ClienteResponse> clienteToClientesResponseList(List<Cliente> carritos);
}
