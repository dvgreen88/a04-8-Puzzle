/*
 * To implement the A* algorithm, you must use the MinPQ data type from algs4.jar
 * for the priority queue.
 */

package a04_8_Puzzle;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Solver {
	Board initial;

	/**
	 * find a solution to the initial board
	 * 
	 * The constructor should throw a java.lang.IllegalArgumentException if the
	 * initial board is not solvable and a java.lang.NullPointerException if the
	 * initial board is null.
	 * 
	 * @param initial
	 */
	public Solver(Board initial) {
		if (initial == null) {
			throw new java.lang.NullPointerException();
		}
		
		if (!initial.isSolvable()) {
			throw new java.lang.IllegalArgumentException();
		}
		this.initial = initial;
	}

	/**
	 * is the initial board solvable?
	 * 
	 * @return true if solvable, otherwise false
	 */
	public boolean isSolvable() {
		return false;
	}

	/**
	 * return min number of moves to solve the initial board, -1 if no such
	 * solution
	 * 
	 * @return min number of moves to solve the board, -1 if no solution
	 */
	public int moves() {
		return 0;
	}

	/**
	 * read puzzle instance from stdin and print solution to stdout (in format
	 * above)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// header
		StdOut.printf("%-25s %7s %8s\n", "filename", "moves", "time");
		StdOut.println("------------------------------------------");

		// for each command-line argument
		for (String filename : args) {
			// read in the board specified in the filename
			In in = new In(filename);
			int N = in.readInt();
			int[][] blocks = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					blocks[i][j] = in.readInt();
			Board initial = new Board(blocks);

			// check if puzzle is solvable; if so, solve it print out number of
			// moves
			if (initial.isSolvable()) {
				Stopwatch timer = new Stopwatch();
				Solver solver = new Solver(initial);
				int moves = solver.moves();
				double time = timer.elapsedTime();
				StdOut.printf("%-25s %7d %8.2f\n", filename, moves, time);
			}

			// if not, print that it is unsolvable
			else {
				StdOut.printf("%-25s   unsolvable\n", filename);
			}
		}
	}
}
