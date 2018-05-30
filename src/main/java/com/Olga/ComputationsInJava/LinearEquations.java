package com.Olga.ComputationsInJava;

import java.util.Random;
import java.util.Vector;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.ejml.data.DenseMatrix64F;
import org.ejml.data.Matrix64F;
import org.ejml.factory.LinearSolverFactory;
import org.ejml.interfaces.linsol.LinearSolver;
import org.ejml.ops.RandomMatrices;
import org.la4j.LinearAlgebra;
import org.la4j.Matrix;
import org.la4j.linear.LinearSystemSolver;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;



public class LinearEquations {
	
	
	public static void apacheCommonsMath() {

		double[][] mac = new double[1000][1000];
		double[] mac2 = new double[1000];
		
		Random rand = new Random();
		
		for(int i=0; i<1000; i++)
			for(int j=0; j<1000; j++)
				mac[i][j] = rand.nextDouble();
		
		for(int k=0; k<1000; k++)
			mac2[k] = rand.nextDouble();
		
		RealMatrix coefficients = new Array2DRowRealMatrix(mac);
		
		long startTime = System.nanoTime();
		
		DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
		
		RealVector constants = new ArrayRealVector(mac2);
		
		RealVector solution = solver.solve(constants);
		
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime);
		
		System.out.println("With Apache Commons it takes: " + duration/1000000 + "miliseconds");
		
	}
	
	public static void eJML() {

		double[][] mac = new double[1000][1000];
		double[][] mac2 = new double[1000][1];
		
		Random rand = new Random();
		
		DenseMatrix64F matA = new DenseMatrix64F(mac);
		DenseMatrix64F matB = new DenseMatrix64F(mac2);
		
		RandomMatrices.setRandom(matA,-100,100,rand);
		RandomMatrices.setRandom(matB,-100,100,rand);
		
		DenseMatrix64F result = new DenseMatrix64F(matA.numCols,matB.numCols);
		
		long startTime = System.nanoTime();
		
		LinearSolver solver = LinearSolverFactory.linear(matA.numRows);
		
		solver.setA((Matrix64F) matA);
		
		solver.solve((Matrix64F)matB,(Matrix64F)result);
		
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime);
		
		System.out.println("With EJML it takes: " + duration/1000000 + "miliseconds");
		
	}
	
	public static void lA4J() {

		double[][] mac = new double[1000][1000];
		double[] mac2 = new double[1000];
		
		Random rand = new Random();
		
		for(int i=0; i<1000; i++)
			for(int j=0; j<1000; j++)
				mac[i][j] = rand.nextDouble()*200-100;
		
		for(int k=0; k<1000; k++)
			mac2[k] = rand.nextDouble()*200-100;
		
		Matrix a = new Basic2DMatrix(mac);
		BasicVector b = new BasicVector(mac2);
		
		long startTime = System.nanoTime();
		
		LinearSystemSolver solver = a.withSolver(LinearAlgebra.FORWARD_BACK_SUBSTITUTION);
		
		org.la4j.Vector x = solver.solve(b);
		
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime);
		
		System.out.println("With LA4J it takes: " + duration/1000000 + "miliseconds");
		
	}

}
