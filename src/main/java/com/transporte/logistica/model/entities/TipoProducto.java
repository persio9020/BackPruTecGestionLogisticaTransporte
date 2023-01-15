package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipo_producto")
public class TipoProducto implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Short id;
  @Basic(optional = false)
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProductoId")
  private List<PlanEntrega> planEntregaList;

  public TipoProducto() {
  }

  public TipoProducto(Short id) {
    this.id = id;
  }
}
