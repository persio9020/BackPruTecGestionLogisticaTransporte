package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
@Entity
@Table(name = "tipo_identificacion")
public class TipoIdentificacion implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "id")
  private Short id;
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(mappedBy = "tipoIdentificacionId")
  private List<Cliente> clienteList;

  public TipoIdentificacion() {
  }

  public TipoIdentificacion(Short id) {
    this.id = id;
  }
}
