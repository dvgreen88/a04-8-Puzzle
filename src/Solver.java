/*
 * To implement the A* algorithm, you must use the MinPQ data type from algs4.jar
 * for the priority queue.
 */

package a04_8_Puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * find a solution to the initial board
 * 
 * The constructor should throw a java.lang.IllegalArgumentException if the
 * initial board is not solvable and a java.lang.NullPointerException if the
 * initial board is null.
 * 
 * @param initial
 */
public class Solver {
	private final Stack<Board> boards;
	private int moves;
	private static boolean isSolvable;

	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int moves;
		private SearchNode previous;
		private int priority = -1;

		SearchNode(Board board, int moves, SearchNode previous) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
		}

		private int priority() {
			if (priority == -1) {
				priority = moves + board.manhattan();
			}
			return priority;
		}

		@Override
		public int compareTo(SearchNode that) {
			if (this.priority() < that.priority()) {
				return -1;
			}
			if (this.priority() > that.priority()) {
				return +1;
			}
			return 0;
		}
	}

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
		} else {
			isSolvable = true;
		}
		
		boards = new Stack<Board>();
		if (initial.isGoal()) {
			this.boards.push(initial);
			return;
		} else if (initial.copyWithSwitch().isGoal()) {
			return;
		}
	}

	/**
	 * return min number of moves to solve the initial board, -1 if no such
	 * solution
	 * 
	 * @return min number of moves to solve the board, -1 if no solution
	 */
	public int moves() {
		if (isSolvable) {
			return boards.size() - 1;
		} else {
			return -1;
		}
	}

	public Iterable<Board> solution() {
		if (isSolvable) {
			return boards;
		} else {
			return null;
		}
	}

	/**
	 * read puzzle instance from stdin and print solution to stdout (in format
	 * above)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// check if puzzle is solvable; if so, solve it and output solution
		if (initial.isSolvable()) {
			Solver solver = new Solver(initial);
			StdOut.println("Minimum number of moves = " + solver.moves());

			for (Board board : solver.solution()) {
				StdOut.println(board);
			}
		}

		// if not, report unsolvable
		else {
			StdOut.println("Unsolvable puzzle");
		}
	}
}
