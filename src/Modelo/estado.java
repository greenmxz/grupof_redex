/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Nowa
 */
public class estado {
    
    private String codigo; //ejemplo "estado_pedido"
    private String valor; //ejemplo "Enviado"
    private int id_tabla_general;
    private int id_tabla_general_detalle;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getId_tabla_general() {
        return id_tabla_general;
    }

    public void setId_tabla_general(int id_tabla_general) {
        this.id_tabla_general = id_tabla_general;
    }

    public int getId_tabla_general_detalle() {
        return id_tabla_general_detalle;
    }

    public void setId_tabla_general_detalle(int id_tabla_general_detalle) {
        this.id_tabla_general_detalle = id_tabla_general_detalle;
    }
    
    
    
}
