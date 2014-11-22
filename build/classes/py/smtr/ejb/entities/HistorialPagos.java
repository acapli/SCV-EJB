/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.smtr.ejb.entities;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IDTK
 */
@Entity
@Table(name = "historial_pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialPagos.findAll", query = "SELECT h FROM HistorialPagos h"),
    @NamedQuery(name = "HistorialPagos.findById", query = "SELECT h FROM HistorialPagos h WHERE h.id = :id"),
    @NamedQuery(name = "HistorialPagos.findBySaldoParcial", query = "SELECT h FROM HistorialPagos h WHERE h.saldoParcial = :saldoParcial"),
    
    @NamedQuery(name = "HistorialPagos.findByCerrado", query = "SELECT h FROM HistorialPagos h WHERE h.cerrado = :cerrado")})
  
public class HistorialPagos implements Serializable {
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne
    private Clientes idCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo_anterior")
    private Double saldoAnterior;
    @Column(name = "saldo_actual")
    private Double saldoActual;
    @Column(name = "cerrado")
    private Boolean cerrado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_parcial")
    private double saldoParcial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;
    @JoinColumn(name = "id_pagos", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pagos idPagos;
    @JoinColumn(name = "id_factura", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Facturas idFactura;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cajas idUsuario;

    public HistorialPagos() {
    }

    public HistorialPagos(Integer id) {
        this.id = id;
    }

    public HistorialPagos(Integer id, double saldoParcial, Date changed) {
        this.id = id;
        this.saldoParcial = saldoParcial;
        this.changed = changed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSaldoParcial() {
        return saldoParcial;
    }

    public void setSaldoParcial(double saldoParcial) {
        this.saldoParcial = saldoParcial;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public Pagos getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(Pagos idPagos) {
        this.idPagos = idPagos;
    }

    public Facturas getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Facturas idFactura) {
        this.idFactura = idFactura;
    }

    public Cajas getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Cajas idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialPagos)) {
            return false;
        }
        HistorialPagos other = (HistorialPagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.HistorialPagos[ id=" + id + " ]";
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(Double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }
    
}
