package com.transporte.logistica.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
  @NotNull
  @Column(name = "cantidad")
  private int cantidad;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha_registro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha_entrega")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaEntrega;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "precio_envio")
  private BigDecimal precioEnvio;
  @Size(max = 6)
  @Column(name = "placa_vehiculo")
  private String placaVehiculo;
  @Size(max = 8)
  @Column(name = "numero_flota")
  private String numeroFlota;
  @Basic(optional = false)
  @NotNull
  @Size(min = 10, max = 10)
  @Column(name = "numero_guia")
  private String numeroGuia;
  @JoinColumn(name = "bodega_entrega_id", referencedColumnName = "id")
  @ManyToOne
  private Bodega bodegaEntregaId;
  @JoinColumn(name = "cliente_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Cliente clienteId;
  @JoinColumn(name = "puerto_entrega_id", referencedColumnName = "id")
  @ManyToOne
  private Puerto puertoEntregaId;
  @JoinColumn(name = "tipo_logistica_transporte_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private TipoLogisticaTransporte tipoLogisticaTransporteId;
  @JoinColumn(name = "tipo_producto_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private TipoProducto tipoProductoId;

  public PlanEntrega() {
  }

  public PlanEntrega(Integer id) {
    this.id = id;
  }

}
