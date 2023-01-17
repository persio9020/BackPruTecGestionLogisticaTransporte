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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "bodega")
public class Bodega implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "direccion")
  private String direccion;
  @JoinColumn(name = "pais_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Pais paisId;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaEntregaId")
  private List<PlanEntrega> planEntregaList;

  public Bodega() {
  }

  public Bodega(Integer id) {
    this.id = id;
  }

}
