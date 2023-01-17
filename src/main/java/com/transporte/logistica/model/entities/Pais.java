package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author persi
 */
@Data
@Entity
@Table(name = "pais")
public class Pais implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 2)
  @Column(name = "iso")
  private String iso;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 80)
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisId")
  private List<Puerto> puertoList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisId")
  private List<Bodega> bodegaList;

  public Pais() {
  }

  public Pais(Integer id) {
    this.id = id;
  }
}
