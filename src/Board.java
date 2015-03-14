/*
 * Your implementation should support all Board methods in time proportional to 
 * N² (or better) in the worst case, with the exception that isSolvable() may 
 * take up to N4 in the worst case. 
 */

package a04_8_Puzzle;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;
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
	private final int N;
	private final int[][] tiles;
	private final int[][] goal;

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
		N = tiles.length;
		this.tiles = new int[N][N];
		goal = makeAGoalArray(N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.tiles[i][j] = tiles[i][j];
			}
		}
	}

	/**
	 * board size N
	 * 
	 * @return N
	 */
	public int size() {
		return N;
	}

	/**
	 * creates a board based on what the end board will look like
	 * 
	 * @return the actual goal board
	 */
	private static int[][] makeAGoalArray(int n) {
		int[][] goal = new int[n][n];
		int count = 1;
		// int[][] goal = {
		// {1, 2, 3},
		// {4, 5, 6},
		// {7, 8, 0}
		// };
		/*
		 * for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++) { if (i ==
		 * n - 1 && j == n - 1) { goal[n - 1][n - 1] = 0; } else { goal[i][j] =
		 * count++; } } }
		 */
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (count == n * n)
					count = 0;
				goal[i][j] = count++;

			}

		}
		return goal;
	}

	private int[][] getGoal() {
		return goal;
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
		return numOutOfOrder;
	}

	/**
	 * return sum of Manhattan distances between blocks and goal
	 * 
	 * @return the manhattan distance between where you are to the goal
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
		return distanceSum;
	}

	/**
	 * is this board the goal board?
	 * 
	 * @return true if matches, otherwise false
	 */
	public boolean isGoal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tiles[i][j] != goal[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * swaps the index for the new board
	 * 
	 * @param i
	 * @param j
	 * @param it
	 * @param jt
	 * @return
	 */
	private boolean indexSwap(int i, int j, int it, int jt) {
		if (it < 0 || it >= N || jt < 0 || jt >= N) {
			return false;
		}
		int temp = tiles[i][j];
		tiles[i][j] = tiles[it][jt];
		tiles[it][jt] = temp;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (N != other.N)
			return false;
		if (!Arrays.deepEquals(tiles, other.tiles))
			return false;
		return true;
	}

	/**
	 * return an Iterable of all neighboring board positions
	 * 
	 * @return an Iterable of all neighboring board positions
	 */
	public Iterable<Board> neighbors() {
		return null;
	}

	/*
	 * string representation of the board (non-Javadoc)
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
	 * is the initial board solvable?
	 * 
	 * @return true if solvable, otherwise false
	 */
	public boolean isSolvable() {
		int value;
		int invariants = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				value = tiles[i][j];
				for (int k = i; k < N; k++) {
					for (int m = j; m < N; m++) {
						if (value > tiles[k][m]) {
							invariants++;
						}
					}
				}
			}
		}

		if (invariants % 2 == 0 && N % 2 != 0) {
			return true;
		} else {
			return false;
		}
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
		int[][] unsolvable = { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } };

		ArrayList<int[][]> arrays = new ArrayList<>();
		arrays.add(values1);
		arrays.add(values2);
		arrays.add(values3);
		arrays.add(difValues);
		arrays.add(unsolvable);

		for (int[][] i : arrays) {
			Board myBoard = new Board(i);
			StdOut.println("----------");
			StdOut.println("Board: ");
			StdOut.println(myBoard);
			StdOut.println("Goal Board: ");
			StdOut.println(new Board(myBoard.getGoal()));
			StdOut.println("Manhattan Value: " + myBoard.manhattan());
			StdOut.println("Hamming Value: " + myBoard.hamming());
			StdOut.println();
		}
	}
}
