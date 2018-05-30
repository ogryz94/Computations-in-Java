package com.Olga.ComputationsInJava;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//System.out.println("For solving linear equations with random matrices with the same size: ");
        //LinearEquations.apacheCommonsMath();
        //LinearEquations.eJML();
        //LinearEquations.lA4J();
        
        System.out.println("For computing matrix eigenvalues: ");
        //Be careful! Very time-consuming:
        MatrixEigenDecomposition.apacheCommonsMath();
        MatrixEigenDecomposition.eJML();
        MatrixEigenDecomposition.lA4J();
}
}