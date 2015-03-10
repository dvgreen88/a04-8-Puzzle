/*
 * Your implementation should support all Board methods in time proportional to 
 * N² (or better) in the worst case, with the exception that isSolvable() may 
 * take up to N4 in the worst case. 
 */

package a04_8_Puzzle;

import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.StdOut;

/**
 * 
 * You may assume that the constructor receives an N-by-N array containing the
 * N2 integers between 0 and N2 − 1, where 0 represents the blank square.
 * 
 * @author Don Vance Green and Greg Hiatt
 *
 */
public class Board {
	private int N;
	private int[][] tiles;
	private int[][] goal;

	/**
	 * construct a board from an N-by-N array of tiles
	 * 
	 * The constructor should throw a java.lang.IllegalArgumentException if the
	 * initial board is not solvable and a java.lang.NullPointerException if the
	 * initial board is null.
	 * 
	 * @param tiles
	 */
	public Board(int[][] tiles) {
		if (tiles == null) {
			throw new java.lang.NullPointerException();
		}

		// this is needed, commented out until isSolvable is written
		// if (!isSolvable()) {
		// throw new java.lang.IllegalArgumentException();
		// }

		this.N = tiles[0].length;
		this.tiles = tiles;
		goal = makeAGoalArray(N);
	}

	/**
	 * return number of blocks out of place
	 * 
	 * @return number of blocks out of place
	 */
	public int hamming() {
		int numOutOfOrder = 0;
		int value;

		for (int i = 1; i < N * N; i++) {
			int row = i / N;
			int col = i % N;
			value = tiles[row][col];
			if (value != i + 1) {
				numOutOfOrder++;
			}
		}
		StdOut.println(numOutOfOrder);
		return numOutOfOrder;
	}

	/**
	 * return sum of Manhattan distances between blocks and goal
	 * 
	 * @return
	 */
	public int manhattan() {
		int distanceSum = 0;
		int value;
		int row;
		int goalRow = 0;
		int goalCol = 0;
		int col;

		for (int i = 0; i < N * N; ++i) {
			row = i / N;
			col = i % N;
			value = tiles[row][col];

			if (value == 0) {
				continue;
			}

			for (int j = 0; j < N * N; j++) {
				if (goal[j / N][j % N] == value) {
					goalRow = j / N;
					goalCol = j % N;
				}
			}

			distanceSum += (Math.abs(goalRow - row) + Math.abs(goalCol - col));
		}
		StdOut.println(distanceSum);
		return distanceSum;
	}

	private static int[][] makeAGoalArray(int n) {
		int[][] goal = new int[n][n];
		int count = 1;
		// int[][] goal = {
		// {1, 2, 3},
		// {4, 5, 6},
		// {7, 8, 0}
		// };
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == n - 1 && j == n - 1) {
					goal[n - 1][n - 1] = 0;
				} else {
					goal[i][j] = count++;
				}
			}
		}
		return goal;
	}

	/**
	 * is the initial board solvable?
	 * 
	 * @return true if solvable, false otherwise
	 */
	protected boolean isSolvable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * does this board equal y Given by Sedgewick (similar to DATA)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object y) {
		if (y == this) {
			return true;
		} else if (y == null) {
			return false;
		} else if (y.getClass() != this.getClass()) {
			return false;
		}

		Board other = (Board) y;

		if (other.N != this.N) {
			return false;
		}

		for (int i = 0; i < N; i++) {
			if (!Arrays.equals(other.tiles[i], this.tiles[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * return an Iterable of all neighboring board positions
	 * 
	 * @return an Iterable of all neighboring board positions
	 */
	public Iterable<Board> neighbors() {
		Queue result = new Queue<Board>();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * return a string representation of the board Given BY Sedgewick in
	 * Checklist
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	/**
	 * test client
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] values1 = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } }; // 4
		int[][] values2 = { { 1, 2, 3 }, { 0, 7, 6 }, { 5, 4, 8 } }; // 7
		int[][] values3 = { { 2, 3, 5 }, { 1, 0, 4 }, { 7, 8, 6 } }; // 8
		int[][] difValues = { { 0, 15, 14, 13 }, { 12, 11, 10, 9 },
				{ 8, 7, 6, 5 }, { 4, 3, 2, 1 } };
		Board myBoard = new Board(values3);
		Board difBoard = new Board(difValues);
		StdOut.println(myBoard);
		myBoard.manhattan();
		StdOut.println("-------------");
		StdOut.println(difBoard);
		difBoard.manhattan();
		StdOut.println();
		// myBoard.hamming();

		// int[][] goal = makeAGoalArray(4);
		// Board goalBoard = new Board(goal);
		// StdOut.println(goalBoard);
		// goalBoard.manhattan();
	}

}
