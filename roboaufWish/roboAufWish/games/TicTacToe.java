package games;

public class TicTacToe implements Game {
    private char[][] board = new char[3][3];
    private int round = 1;
    private boolean finished = false;
    private boolean won = false;
    private boolean lost = false;

    private char playerSymbol = 'X'; // Roboter
    private char enemySymbol = 'O';  // Gegner

    public TicTacToe() {
        // Spielfeld leeren
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void playNextRound() {
        if (finished) return;

        // Roboter-Zug (einfacher erster freier Platz)
        makePlayerMove();

        if (checkWinner(playerSymbol)) {
            finished = true;
            won = true;
            return;
        }

        if (isBoardFull()) {
            finished = true;
            return;
        }

        // Gegner-Zug (ebenfalls zufällig)
        makeEnemyMove();

        if (checkWinner(enemySymbol)) {
            finished = true;
            lost = true;
            return;
        }

        if (isBoardFull()) {
            finished = true;
        }

        round++;
    }

    private void makePlayerMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = playerSymbol;
                    return;
                }
            }
        }
    }

    private void makeEnemyMove() {
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                if (board[i][j] == ' ') {
                    board[i][j] = enemySymbol;
                    return;
                }
            }
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    private boolean checkWinner(char symbol) {
        // Reihen und Spalten
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }

        // Diagonalen
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;

        return false;
    }

    @Override
    public int getCurrentRound() {
        return round;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean isWon() {
        return won;
    }

    @Override
    public boolean isLost() {
        return lost;
    }

    @Override
    public boolean isTie() {
        return finished && !won && !lost;
    }

    // Optional: Spielfeld anzeigen (z. B. für Debug oder späteres UI)
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}
