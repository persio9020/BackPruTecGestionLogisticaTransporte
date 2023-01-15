package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Short id;
  @Basic(optional = false)
  @Column(name = "nombre")
  private String nombre;
  @JoinTable(name = "usuario_rol", joinColumns = {
    @JoinColumn(name = "rol_id", referencedColumnName = "id")}, inverseJoinColumns = {
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")})
  @ManyToMany
  private List<Usuario> usuarioList;

  public Rol() {
  }

  public Rol(Short id) {
    this.id = id;
  }
  
}
