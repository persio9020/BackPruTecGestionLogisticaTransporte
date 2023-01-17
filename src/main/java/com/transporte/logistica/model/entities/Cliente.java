package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Hector Leon
 */
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Column(name = "nombres")
  private String nombres;
  @Column(name = "apellidos")
  private String apellidos;
  @Column(name = "identificacion")
  private String identificacion;
  @JoinColumn(name = "sexo_id", referencedColumnName = "id")
  @ManyToOne
  private Sexo sexoId;
  @Column(name = "telefono")
  private String telefono;
  @Column(name = "celular")
  private String celular;
  @Column(name = "direccion")
  private String direccion;
  @Column(name = "correo")
  private String correo;
  @Column(name = "fecha_nacimiento")
  @Temporal(TemporalType.DATE)
  private Date fechaNacimiento;
  @JoinColumn(name = "tipo_identificacion_id", referencedColumnName = "id")
  @ManyToOne
  private TipoIdentificacion tipoIdentificacionId;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId")
  private List<PlanEntrega> planEntregaList;

  public Cliente() {
  }

  public Cliente(Long id) {
    this.id = id;
  }

  public String getNombreCompleto() {
    return nombres + " " + apellidos;
  }

  public String getLabel() {
    return nombres + " " + apellidos + " - " + identificacion;
  }
}
