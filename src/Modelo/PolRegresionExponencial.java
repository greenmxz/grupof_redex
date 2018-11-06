/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nowa
 */
public class PolRegresionExponencial {
    
    
    private double[] x;  //datos
    private double[] y;
    private int nDatos;
    private  double[] a; //respuesta
    
    public PolRegresionExponencial(double[] x, double[] y){
        this.x = x;
        this.y = y;
        this.nDatos = x.length;
        this.a = new double[2];
    }

    public void calculaPolinomio(){
        try{
		
		// N = size of data set
		int N = this.nDatos;
		
		// constant e:
		Double e = Math.E;
		
		Double sumX = 0.00;
		Double sumX2 = 0.00;
		Double sumY = 0.00;
		Double sumY2 = 0.00;
		Double sumXY = 0.00;
		
		for(int i=0;i<N;i++)
		{
                        
			sumX = sumX + x[i];
			sumX2 = sumX2 + Math.pow(x[i], 2);
			
			// exponential
			sumY = sumY + Math.log(y[i]);
			sumY2 = sumY2 + Math.pow(Math.log(y[i]), 2);
			sumXY = sumXY + (x[i]*(Math.log(y[i])));
                        
                        /*
                        System.out.println("sumX " + sumX);
                        System.out.println("sumX2 " + sumX2);
                        System.out.println("sumY " + sumY);
                        System.out.println("sumY2 " + sumY2);
                        System.out.println("sumXY " + sumXY);
                        */
			
		}
                
                
		
		/* Calculate regression coefficient 'b' */
		// exponential
		Double b = 0.00;
		b = ((N*sumXY) - (sumX*sumY))/(N*sumX2 - (sumX*sumX));
		
                System.out.println("b " + b);
                
		/* Calculate regression coefficient 'a' */
		// exponential
		Double a = 0.00;
		a = Math.pow(e, (sumY - (b*sumX))/N);
		
                System.out.println("a " + a);
		/* Calculate coefficient of determination (R^2) */
		// exponential
		Double c = 0.00;	// numerator
		Double d = 0.00;	// denominator
		Double r = 0.00;	// coefficient of determination
		c = (b)*(sumXY - sumX*sumY/N);
		d = sumY2 - (sumY*sumY)/N;
		r = c/d;

		
		// Calculate coefficient of correlation
		// exponential
		Double p = 0.00;
		if(r > 0){
			p = Math.sqrt(r);
		} else {
			p = 0.00;
		}
		

		/* Calculate standard error
		 * equals (total variance - y variance)/(n-2)
		 */
		// exponential
		Double std_err = 0.00;
		std_err = Math.sqrt((d-c)/(N-2));
		

		
		/* print the model */
		// exponential
		System.out.println("Exponential regression model for n equals " + N + ":");
		System.out.println("y = " + a + "*(e^(" + b + "*x))");
		System.out.println("R-square value equals " + r);
		System.out.println("Correlation equals " + p);
		System.out.println("Standard Error equals " + std_err);
		System.out.println("");
		
                this.a[0] = a;
                this.a[1] = b;
                
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR calculaPolinomio" + e.getMessage());
        }
    }

    public double[] getA() {
        return a;
    }

    public void setA(double[] a) {
        this.a = a;
    }
    
    
}
