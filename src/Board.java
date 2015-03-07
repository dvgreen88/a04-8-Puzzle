public class Board {
   public Board(int[][] tiles) {       
       // construct a board from an N-by-N array of tiles
   }
   
   public int hamming() {
       // return number of blocks out of place
    }
    
   public int manhattan()   {
       // return sum of Manhattan distances between blocks and goal
   }
   public boolean equals(Object y) {    
   // does this board equal y 
   }
  
   public Iterable<Board> neighbors() {
   // return an Iterable of all neighboring board positions
   }
   
   public String toString() {
   // return a string representation of the board 
   }

   //  test client
   public static void main(String[] args) {
	   System.out.println("practicing to push");
   }
}
