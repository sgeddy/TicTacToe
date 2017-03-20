// Sam Eddy ECE 309 Extra Credit Lab - TicTacToe

import java.util.Scanner;

public class TicTacToe
{
  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args)
  {
	// First, welcome message and display the board.
    System.out.println("===== WELCOME TO THE TIC-TAC-TOE GAME!! ======");
	System.out.println("=============== By Samuel Eddy ===============\n");
	boolean play = true;
	int usr1scr=0;
    int usr2scr=0;
    int ties=0;
	
	while(play) {

    final int SIZE = 6;
    char[][] board = new char[SIZE][SIZE]; // game board

    resetBoard(board); // initialize the board (with ' ' for all cells)
    showBoard(board);

    // Then ask the user which symbol (x or o) he/she wants to play.
    System.out.print("  Player 1, which symbol do you want to play with, X or O?");
    System.out.print("  \n  To answer, enter the symbol, followed by the enter key:");
    char user1Symbol = sc.next().toLowerCase().charAt(0);
    char user2Symbol = (user1Symbol == 'x') ? 'o' : 'x';

    // Also ask whether or not the user wants to go first.
    System.out.println();
    System.out.print("  Player 1, do you want to go first (y/n)? ");
    System.out.print("  \n  To answer, enter either" + " y" + " or" + " n" + ", followed by the enter key:");
    char ans = sc.next().toLowerCase().charAt(0);

    int turn;  
    int remainCount = SIZE * SIZE; // empty cell count

    // THE VERY FIRST MOVE.
    if (ans == 'y') {
      turn = 0;
      user1Play(board, user1Symbol); // user puts his/her first tic
    }
    else {
      turn = 1;
      user2Play(board, user2Symbol); 
    }
    // Show the board, and decrement the count of remaining cells.
    showBoard(board);
    remainCount--;

    // Play the game until either one wins.
    boolean done = false;
    int winner = -1;   

    while (!done && remainCount > 0) {
      // If there is a winner at this time, set the winner and the done flag to true.
      done = isGameWon(board, turn, user1Symbol, user2Symbol); // Did the turn won?

      if (done)
        winner = turn; // the one who made the last move won the game
      else {
        // No winner yet.  Find the next turn and play.
        turn = (turn + 1 ) % 2;

        if (turn == 0)
          user1Play(board, user1Symbol);
        else
        
          user2Play(board, user2Symbol);

        // Show the board after one tic, and decrement the rem count.
        showBoard(board);
        remainCount--;
      }
    }

    // Winner is found.  Declare the winner.
    if (winner == 0) {
      System.out.println("\n** PLAYER 1 WINS.  CONGRATULATIONS!! **");
      usr1scr++;
    }
    else if (winner == 1) {
      System.out.println("\n** PLAYER 2 WINS.  CONGRATULATIONS!! **");
      usr2scr++;
    }
    else {
      System.out.println("\n** IT'S A TIC TAC TIE!! **");
      ties++;
    }
    System.out.println("\n** Total Score: User 1= " + usr1scr + " User 2= " + usr2scr + " Ties= " + ties);
    System.out.println("\n** Play Again? (y/n): **");
    char pans = sc.next().toLowerCase().charAt(0);
    if (pans == 'n') play = false;
	}
  }

  public static void resetBoard(char[][] brd)
  {
    for (int i = 0; i < brd.length; i++)
      for (int j = 0; j < brd[0].length; j++)
        brd[i][j] = ' ';
  }

  public static void showBoard(char[][] brd)
  {
    int numRow = brd.length;
    int numCol = brd[0].length;

    System.out.println();

    // First write the column header
    System.out.print("    ");
    for (int i = 0; i < numCol; i++)
      System.out.print(i + "   ");
    System.out.print('\n');

    System.out.println(); // blank line after the header

    // The write the table
    for (int i = 0; i < numRow; i++) {
      System.out.print(i + "  ");
      for (int j = 0; j < numCol; j++) {
        if (j != 0)
          System.out.print("|");
        System.out.print(" " + brd[i][j] + " ");
      }

      System.out.println();

      if (i != (numRow - 1)) {
        // separator line
        System.out.print("   ");
        for (int j = 0; j < numCol; j++) {
          if (j != 0)
            System.out.print("+");
          System.out.print("---");
        }
        System.out.println();
      }
    }
    System.out.println();
  }

  public static void user1Play(char[][] brd, char usym1)
  {
    System.out.print("\n\n  Player 1, enter the row you would like to place your symbol, followed by the enter key.");
    System.out.println("\n  Then enter the column, followed by the enter key:");
    int rowIndex = sc.nextInt();
    int colIndex = sc.nextInt();

    while (brd[rowIndex][colIndex] != ' ') {
      System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
      rowIndex = sc.nextInt();
      colIndex = sc.nextInt();
    }

    brd[rowIndex][colIndex] = usym1;
  }

  public static void user2Play(char[][] brd, char usym2)
  {
	  System.out.print("\n\n  Player 2, enter the row you would like to place your symbol, followed by the enter key.");
	    System.out.println("\n  Then enter the column, followed by the enter key:");
	    int rowIndex = sc.nextInt();
	    int colIndex = sc.nextInt();

	    while (brd[rowIndex][colIndex] != ' ') {
	      System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
	      rowIndex = sc.nextInt();
	      colIndex = sc.nextInt();
	    }

	    brd[rowIndex][colIndex] = usym2;
  }

  public static boolean isGameWon(char[][] brd, int turn, char usym1, char usym2)
  {
    char sym;
    if (turn == 0)
      sym = usym1;
    else
      sym = usym2;

    int i, j;
    boolean win = false;

    // Check win by a row
    for (i = 0; i < brd.length && !win; i++) {
      for (j = 0; j < brd[0].length; j++) {
        if (brd[i][j] != sym)
          break;
      }
      if (j == brd[0].length)
        win = true;
    }

    // Check win by a column
    for (j = 0; j < brd[0].length && !win; j++) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][j] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    // Check win by a diagonal (1)
    if (!win) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][i] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    // Check win by a diagonal (2)
    if (!win) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][brd.length - 1 - i] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    // Finally return win
    return win;
  }
}