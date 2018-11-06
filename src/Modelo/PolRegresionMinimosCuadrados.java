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
public class PolRegresionMinimosCuadrados {
    
    
  private double[] x;  //datos
  private double[] y;
  private int nDatos;
  private double[][] m;    //matriz de los coeficientes
  private double[] t;      //términos independientes
  private  double[] a; //polinomio   a[0]+a[1]·x+a[2]·x2+...
  private int grado; //grado del polinomio

  public PolRegresionMinimosCuadrados(double[] x, double[] y, int grado) {
    this.x=x;
    this.y=y;
    nDatos=x.length;
    this.grado=grado;
    t=new double[grado+1];
    m=new double[grado+1][grado+1];
    a=new double[grado+1];
  }

  private void coeficientes(){
    double[] s=new double[2*grado+1];
    double suma;
    for(int k=0; k<=2*grado; k++){
      suma=0.0;
      for(int i=0; i<nDatos; i++){
        suma+=potencia(x[i], k);
      }
      s[k]=suma;
    }
    for(int k=0; k<=grado; k++){
      suma=0.0;
      for(int i=0; i<nDatos; i++){
        suma+=potencia(x[i], k)*y[i];
      }
      t[k]=suma;
    }
    for(int i=0; i<=grado; i++){
      for(int j=0; j<=grado; j++){
        m[i][j]=s[i+j];
      }
    }
  }

  private double potencia(double base, int exp){
    double producto=1.0;
    for(int i=0; i<exp; i++){
      producto*=base;
    }
    return producto;
  }

//procedimiento de Seidel
  public void calculaPolinomio(){
    coeficientes();
//matriz
    double aux;
    for(int i=0; i<=grado; i++){
        aux=m[i][i];
      for(int j=0; j<=grado; j++){
        m[i][j]=-m[i][j]/aux;
      }
      t[i]=t[i]/aux;
      m[i][i]=0.0;
  }
// aproximación inicial
    double[] p=new double[grado+1];
    p[0]=t[0];
    for(int i=1; i<=grado; i++){
      p[i]=0.0;
    }
//aproximaciones  sucesivas
    double error=0.0, maximo=0.0;
    do{
      maximo=0.0;
      for(int i=0; i<=grado; i++){
        a[i]=t[i];
        for(int j=0; j<i; j++){
          a[i]+=m[i][j]*a[j];
        }
        for(int j=i+1; j<=grado; j++){
          a[i]+=m[i][j]*p[j];
        }
        error=Math.abs((a[i]-p[i])/a[i]);
        if (error>maximo)   maximo=error;
      }
      for(int i=0; i<=grado; i++){
          p[i]=a[i];
      }
    }while(maximo>0.001);

  }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public int getnDatos() {
        return nDatos;
    }

    public void setnDatos(int nDatos) {
        this.nDatos = nDatos;
    }

    public double[][] getM() {
        return m;
    }

    public void setM(double[][] m) {
        this.m = m;
    }

    public double[] getT() {
        return t;
    }

    public void setT(double[] t) {
        this.t = t;
    }

    public double[] getA() {
        return a;
    }

    public void setA(double[] a) {
        this.a = a;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }
    
}
