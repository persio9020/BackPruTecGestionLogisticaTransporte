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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_logistica_transporte")
public class TipoLogisticaTransporte implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Short id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoLogisticaTransporteId")
  private List<PlanEntrega> planEntregaList;

  public TipoLogisticaTransporte() {
  }

  public TipoLogisticaTransporte(Short id) {
    this.id = id;
  }
}
