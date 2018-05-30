package com.Olga.ComputationsInJava;

import java.util.Random;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.ejml.data.DenseMatrix64F;
import org.ejml.data.Matrix64F;
import org.ejml.factory.LinearSolverFactory;
import org.ejml.interfaces.linsol.LinearSolver;
import org.ejml.ops.RandomMatrices;
import org.ejml.simple.SimpleEVD;
import org.ejml.simple.SimpleMatrix;
import org.la4j.LinearAlgebra;
import org.la4j.Matrix;
import org.la4j.decomposition.EigenDecompositor;
import org.la4j.linear.LinearSystemSolver;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;

public class MatrixEigenDecomposition {
	


	public static void apacheCommonsMath() {

		double[][] mac = new double[1000][1000];
		
		Random rand = new Random();
		
		for(int i=0; i<1000; i++)
			for(int j=0; j<1000; j++)
				mac[i][j] = rand.nextDouble()*200-100;
		
		
		RealMatrix matrix = new Array2DRowRealMatrix(mac);
		
		long startTime = System.nanoTime();
		
		EigenDecomposition decomp = new EigenDecomposition(matrix);
	
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime);
		
		System.out.println("With Apache Commons it takes: " + duration/1000000 + "miliseconds");
		
	}
	
	public static void eJML() {

		double[][] mac = new double[1000][1000];
		
		Random rand = new Random();
		
		for(int i=0; i<1000; i++)
			for(int j=0; j<1000; j++)
				mac[i][j] = rand.nextDouble();
		
		DenseMatrix64F a = new DenseMatrix64F(mac);
		
		long startTime = System.nanoTime();
		
		SimpleEVD<SimpleMatrix> b = new SimpleEVD(a);
		
		EigenDecomposition decomp = (EigenDecomposition) b.getEVD();
		
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime);
		
		System.out.println("With EJML it takes: " + duration/1000000 + "miliseconds");
		
	}
	
	public static void lA4J() {

		double[][] mac = new double[1000][1000];
		
		Random rand = new Random();
		
		for(int i=0; i<1000; i++)
			for(int j=0; j<1000; j++)
				mac[i][j] = rand.nextDouble();
		
		Matrix a = new Basic2DMatrix(mac);
		
		long startTime = System.nanoTime();
		
		EigenDecompositor a2 = new EigenDecompositor( a );
		
		Matrix eigen[] = a2.decompose();
		
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime);
		
		System.out.println("With LA4J it takes: " + duration/1000000 + "miliseconds");
		
		Matrix v = eigen[0]; //vectors
		Matrix d = eigen[1]; //values
		
	}

}
