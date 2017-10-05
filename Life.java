
/*******************************************************************************
 * 
 * Life Matrix
 * 
 * Purpose: Input row and column parameters, and the seed to use for random number.
 *     
 * Version 2.0 now prints 6 life cycles, which evolve depending on the conditions.
 * @author Matt Fossett
 *
 * @version 2.0 (9/20/2017)   
 * 
 ******************************************************************************/

import java.util.Random;
import java.util.Scanner;

public class Life {

	int row;
	int column;
	long seed;
	int birthLow;
	int birthHigh;
	int liveLow;
	int liveHigh;
	boolean[][] displayMatrix;

	/**
	 * Constructs object that can be used for life game
	 * @param seed
	 * @param row
	 * @param column
	 * @param birthLow
	 * @param birthHigh
	 * @param liveLow
	 * @param liveHigh
	 */
	public Life(long seed, int row, int column, int birthLow, int birthHigh, int liveLow, int liveHigh) {
		this.seed = seed;
		this.row = row;
		this.column = column;
		this.birthLow = birthLow;
		this.birthHigh = birthHigh;
		this.liveLow = liveLow;
		this.liveHigh = liveHigh;
		if(birthLow<1){
			throw new IllegalArgumentException("Birthlow must be a positive number, not " + birthLow );
		} else if(birthHigh > 9){
			throw new IllegalArgumentException("Birthhigh must be a less than 10 number, not " + birthHigh );
		} else if(liveLow < 1){
			throw new IllegalArgumentException("Livelow must be a positive number, not " + liveLow );
		} else if(liveHigh > 9){
			throw new IllegalArgumentException("Livehigh must be a positive number, not " + liveHigh );
		} else if(row < 1){
			throw new IllegalArgumentException("Row must be a positive number, not " + row );
		} else if(column < 1){
			throw new IllegalArgumentException("Column must be a positive number, not " + column );
		} else if(birthHigh < birthLow){
			throw new IllegalArgumentException("Birthhigh should not be greater than Birthlow " + birthHigh + " > " + birthLow );
		} else if(liveHigh < liveLow){
			throw new IllegalArgumentException("Livehigh should not be greater than Livelow " + liveHigh + " > " + liveLow );
		}
		this.displayMatrix = new boolean[row][column];
		
		Random generator = new Random(seed);
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < column - 1; j++) {
				displayMatrix[i][j] = generator.nextBoolean();
			}
		}
	}
	/**
	 * Accesses current matrix 
	 * @return Copy of current matrix
	 */
	public boolean[][] world() {

		return cloneMatrix(displayMatrix);

	}
	/**
	 * 
	 * @param displayMatrix to be copied
	 * @return copy of displayMatrix at new memory address
	 */
	public boolean[][] cloneMatrix(boolean[][] displayMatrix) {
		boolean[][] myNewMatrix = displayMatrix.clone();
		System.out.println(displayMatrix.clone().toString());
		for (int row = 0; row < displayMatrix.length; ++row) {
			myNewMatrix[row] = displayMatrix[row].clone();
		}
		return myNewMatrix;
	}
	/**
	 * updates matrix to the next iteration of life game
	 */
	public void update() {
		boolean[][] constantMatrix = cloneMatrix(displayMatrix);
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < column - 1; j++) {
				int truths = countTruths(constantMatrix, i, j);
				if (constantMatrix[i][j]) {
					displayMatrix[i][j] = (truths >= liveLow && truths <= liveHigh);
				} else {
					displayMatrix[i][j] = (truths >= birthLow && truths <= birthHigh);
				}
			}
		}
	}
	/**
	 * Counts positive values in a single points neighborhood
	 * @param matrix 
	 * @param row	single point in matrix row value
	 * @param column 	single point in matrix column
	 * @return positive values count in neighborhood
	 */
	public static int countTruths(boolean[][] matrix, int row, int column) {
		int trueCount = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (matrix[row + i][column + j]) {
					trueCount++;
				}
			}
		}
		return trueCount;
	}
}
