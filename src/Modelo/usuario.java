/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Moises
 */
public class usuario {
    private int id;
    private String codigo;
    private String password;
    private String rol;
    private persona persona;
    private int numeroIntentos;
    private boolean baneado;
    private int tiempoRestanteBaneado;
    private boolean encontrado;
    
    public usuario(){
        persona = new persona();
        encontrado=false;
        baneado=false;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the persona
     */
    public persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(persona persona) {
        this.persona = persona;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the numeroIntentos
     */
    public int getNumeroIntentos() {
        return numeroIntentos;
    }

    /**
     * @param numeroIntentos the numeroIntentos to set
     */
    public void setNumeroIntentos(int numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    /**
     * @return the baneado
     */
    public boolean isBaneado() {
        return baneado;
    }

    /**
     * @param baneado the baneado to set
     */
    public void setBaneado(boolean baneado) {
        this.baneado = baneado;
    }

    /**
     * @return the tiempoRestanteBaneado
     */
    public int getTiempoRestanteBaneado() {
        return tiempoRestanteBaneado;
    }

    /**
     * @param tiempoRestanteBaneado the tiempoRestanteBaneado to set
     */
    public void setTiempoRestanteBaneado(int tiempoRestanteBaneado) {
        this.tiempoRestanteBaneado = tiempoRestanteBaneado;
    }

    /**
     * @return the encontrado
     */
    public boolean isEncontrado() {
        return encontrado;
    }

    /**
     * @param encontrado the encontrado to set
     */
    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }


    

}
