import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        int n = 3;
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic Tac Toe !\n");
        System.out.print(" your name:? ");
        String playerName = scanner.nextLine();

        System.out.println("Choose a mode:\n1. Two Players\n2. Single Player");
        int mode = scanner.nextInt();

        if (mode == 1) {
            playTwoPlayers(board, playerName, scanner);
        } else if (mode == 2) {
            playSinglePlayer(board, playerName, scanner);
        } else {
            System.out.println("Invalid mode selection. Exiting...");
        }

        scanner.close();
    }

    private static void playTwoPlayers(char[][] board, String playerName, Scanner scanner) {
        String player1 = playerName;
        String player2 = "Player 2";

        boolean player1Turn = true;
        boolean gameEnded = false;

        while (!gameEnded) {
            drawBoard(board);

            String currentPlayer = player1Turn ? player1 : player2;
            char playerSymbol = player1Turn ? 'x' : 'o';

            System.out.println(currentPlayer + "'s Turn (" + playerSymbol + "):");

            int row = getValidInput("Enter a row number: ", scanner, board.length);
            int col = getValidInput("Enter a column number: ", scanner, board[0].length);

            if (board[row][col] == '-') {
                board[row][col] = playerSymbol;
                player1Turn = !player1Turn;
            } else {
                System.out.println("already made a move at this position! Try again.");
            }

            char winner = playerHasWon(board);
            if (winner != ' ') {
                System.out.println(currentPlayer + " has won!");
                gameEnded = true;
            } else if (boardIsFull(board)) {
                System.out.println("It's a tie!");
                gameEnded = true;
            }
        }

        drawBoard(board);
    }

    private static void playSinglePlayer(char[][] board, String playerName, Scanner scanner) {
        String player = playerName;
        String computer = "AI";

        boolean playerTurn = true;
        boolean gameEnded = false;

        while (!gameEnded) {
            drawBoard(board);

            String currentPlayer = playerTurn ? player : computer;
            char playerSymbol = playerTurn ? 'x' : 'o';

            System.out.println(currentPlayer + "'s Turn (" + playerSymbol + "):");

            int row, col;

            if (playerTurn) {
                row = getValidInput("Enter a row number: ", scanner, board.length);
                col = getValidInput("Enter a column number: ", scanner, board[0].length);
            } else {
                // Computer's move (randomly selecting an empty spot)
                int[] computerMove = getRandomEmptySpot(board);
                row = computerMove[0];
                col = computerMove[1];
                System.out.println("Computer chose: Row " + row + ", Column " + col);
            }

            if (board[row][col] == '-') {
                board[row][col] = playerSymbol;
                playerTurn = !playerTurn;
            } else {
                System.out.println("already made a move at this position! Try again.");
            }

            char winner = playerHasWon(board);
            if (winner != ' ') {
                System.out.println(currentPlayer + " has won!");
                gameEnded = true;
            } else if (boardIsFull(board)) {
                System.out.println("It's a tie!");
                gameEnded = true;
            }
        }

        drawBoard(board);
    }

    private static int getValidInput(String message, Scanner scanner, int max) {
        int input;
        do {
            System.out.print(message);
            input = scanner.nextInt();
        } while (input < 0 || input >= max);
        return input;
    }

    private static int[] getRandomEmptySpot(char[][] board) {
        int n = board.length;
        int[] move = new int[2];
        do {
            move[0] = (int) (Math.random() * n);
            move[1] = (int) (Math.random() * n);
        } while (board[move[0]][move[1]] != '-');
        return move;
    }

    private static void drawBoard(char[][] board) {
        System.out.println("Board:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private static char playerHasWon(char[][] board) {
        // ... (unchanged)for (int i = 0; i < board.length; i++) {
            boolean inARow = true;
            char value = board[i][0];

            if (value == '-') {
                inARow = false;
            } else {
                for (int j = 1; j < board[i].length; j++) {
                    if (board[i][j] != value) {
                        inARow = false;
                        break;
                    }
                }
            }

            if (inARow) {
                return value;
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            boolean inACol = true;
            char value = board[0][j];

            if (value == '-') {
                inACol = false;
            } else {
                for (int i = 1; i < board.length; i++) {
                    if (board[i][j] != value) {
                        inACol = false;
                        break;
                    }
                }
            }

            if (inACol) {
                return value;
            }
        }

        boolean inADiag1 = true;
        char value1 = board[0][0];
        if (value1 == '-') {
            inADiag1 = false;
        } else {
            for (int i = 1; i < board.length; i++) {
                if (board[i][i] != value1) {
                    inADiag1 = false;
                    break;
                }
            }
        }

        if (inADiag1) {
            return value1;
        }

        boolean inADiag2 = true;
        char value2 = board[0][board.length - 1];

        if (value2 == '-') {
            inADiag2 = false;
        } else {
            for (int i = 1; i < board.length; i++) {
                if (board[i][board.length - 1 - i] != value2) {
                    inADiag2 = false;
                    break;
                }
            }
        }

        if (inADiag2) {
            return value2;
        }

        return ' ';
    }

    

    private static boolean boardIsFull(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    
    }

