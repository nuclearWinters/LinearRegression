/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.linearregression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.Scanner;
import java.util.Queue; 
import java.util.LinkedList;
import java.util.Map;
/**
 *
 * @author Armando
 */
public class LinearRegression {
    static Queue<Double> x = new LinkedList<>();
    static Queue<Double> y = new LinkedList<>();
    SimpleRegression sr = new SimpleRegression();
    Plot2DPanel plot = new Plot2DPanel();
    JTextArea resultados = new JTextArea();
    public LinearRegression() {
        Object[] xObject = x.toArray();
        Object[] yObject = y.toArray();
        double[] xArray = new double[xObject.length];
        double[] yArray = new double[yObject.length];
        for (int i = 0; i < xObject.length; i++) {
            xArray[i] = (double)xObject[i];
            yArray[i] = (double)yObject[i];
        }
        for(int i = 0; i < xArray.length; i++){
            sr.addData(xArray[i], yArray[i]);
        }
        double[] yc = new double[y.size()];
        for(int i = 0; i < xArray.length; i++) {
            yc[i] = sr.predict(xArray[i]);
        }
        plot.addLegend("South");
        plot.addScatterPlot("Datos", xArray, yArray);
        plot.addLinePlot("Regresión", xArray , yc);
        BaseLabel titulo = new BaseLabel("Ejemplo regresión lineal", Color.BLUE, 0.5, 1.1);
        plot.addPlotable(titulo);
        JFrame frame = new JFrame("Regresión Lineal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.add(plot, BorderLayout.CENTER);
        frame.setVisible(true);
        System.out.println("La función lineal es: y = " + sr.getSlope() + "x + " + sr.getIntercept());
    }
    public static void setX(int position, double X) {
        x.add(X);
    }
    public static void setY(int position, double Y) {
        y.add(Y);
    }
    public static void main(String[] args) {
        int position = 0;
        while(true) {
            System.out.println("Introduce un el valor de la inversión tecnologica: ");
            Scanner codigo = new Scanner(System.in);
            double codigoString = codigo.nextDouble();
            setX(position, codigoString);
            System.out.println("Introduce un el valor de ventas: ");
            Scanner codigo2 = new Scanner(System.in);
            double codigoString2 = codigo2.nextDouble();
            setY(position, codigoString2);
            position += 1;
            if (position == 6) {
                break;
            }
        }
        //System.out.prinln()
        new LinearRegression();    
    }
}
