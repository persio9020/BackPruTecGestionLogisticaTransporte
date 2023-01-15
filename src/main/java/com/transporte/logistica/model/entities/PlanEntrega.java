package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "plan_entrega")
public class PlanEntrega implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "cantidad")
  private int cantidad;
  @Basic(optional = false)
  @Column(name = "fecha_registro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  @Basic(optional = false)
  @Column(name = "fecha_entrega")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaEntrega;
  @Basic(optional = false)
  @Column(name = "bodega_entrega_id")
  private int bodegaEntregaId;
  @Basic(optional = false)
  @Column(name = "puerto_entrega_id")
  private int puertoEntregaId;
  @Basic(optional = false)
  @Column(name = "precio_envio")
  private long precioEnvio;
  @Basic(optional = false)
  @Column(name = "placa_vehiculo")
  private String placaVehiculo;
  @JoinColumn(name = "cliente_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Cliente clienteId;
  @JoinColumn(name = "tipo_producto_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private TipoProducto tipoProductoId;

  public PlanEntrega() {
  }

  public PlanEntrega(Integer id) {
    this.id = id;
  }
  
}
