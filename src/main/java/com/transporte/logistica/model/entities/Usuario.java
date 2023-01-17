/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author persi
 */
@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Basic(optional = false)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @Column(name = "contrasenia")
  private String contrasenia;
  @Basic(optional = false)
  @Column(name = "correo")
  private String correo;
  @ManyToMany(fetch = FetchType.EAGER,
   mappedBy = "usuarioList")
  private List<Rol> rolList;

  public Usuario() {
  }

  public Usuario(Long id) {
    this.id = id;
  }

  public Usuario(Long id, String nombre, String contrasenia, String correo) {
    this.id = id;
    this.nombre = nombre;
    this.contrasenia = contrasenia;
    this.correo = correo;
  }

}
