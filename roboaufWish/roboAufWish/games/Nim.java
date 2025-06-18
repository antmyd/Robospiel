package games;

import java.util.Random;

/**
 * Implements the Nim game where the player (Robot) and opponent
 * take turns removing stones from rows. The player who takes the last stone wins.
 */
public class Nim implements Game {

    private int[] rows;        // Anzahl der Steine in jeder Reihe
    private int currentRound;
    private boolean finished;
    private boolean won;       // true wenn Roboter gewinnt
    private boolean lost;      // true wenn Gegner gewinnt
    private boolean playerTurn; // true = Roboter am Zug, false = Gegner am Zug

    private Random random = new Random();

    /**
     * Initialisiert Nim mit Standardreihen (z.B. 3 Reihen mit 3, 5, 7 Steinen).
     * Der Roboter beginnt.
     */
    public Nim() {
        this.rows = new int[] {3, 5, 7};
        this.currentRound = 1;
        this.finished = false;
        this.won = false;
        this.lost = false;
        this.playerTurn = true; // Roboter beginnt
    }

    @Override
    public void playNextRound() {
        if (finished) return;

        if (playerTurn) {
            playerMove();
        } else {
            enemyMove();
        }

        // Prüfen ob das Spiel vorbei ist
        if (isGameOver()) {
            finished = true;
            if (playerTurn) {
                // Wenn Spieler gerade gezogen hat und Spiel vorbei ist, gewinnt er
                won = true;
            } else {
                lost = true;
            }
            return;
        }

        // Zug wechseln
        playerTurn = !playerTurn;
        currentRound++;
    }

    private void playerMove() {
        // Einfachste KI für Roboter: Nim-Summen-Strategie verwenden
        int nimSum = 0;
        for (int stones : rows) {
            nimSum ^= stones;
        }

        if (nimSum == 0) {
            // Kein Gewinnzug möglich, nehme 1 Stein von der ersten nicht-leeren Reihe
            for (int i = 0; i < rows.length; i++) {
                if (rows[i] > 0) {
                    rows[i] -= 1;
                    break;
                }
            }
        } else {
            // Nim-Summe ungleich 0: Optimale Strategie
            for (int i = 0; i < rows.length; i++) {
                int target = rows[i] ^ nimSum;
                if (target < rows[i]) {
                    int stonesToRemove = rows[i] - target;
                    rows[i] = target;
                    break;
                }
            }
        }
    }

    private void enemyMove() {
        // Gegnerzug: zufällig einen Zug ausführen
        // Wähle eine zufällige nicht-leere Reihe
        int row;
        do {
            row = random.nextInt(rows.length);
        } while (rows[row] == 0);

        // Entferne zufällig 1 bis alle Steine aus der Reihe
        int stonesToRemove = 1 + random.nextInt(rows[row]);
        rows[row] -= stonesToRemove;
    }

    private boolean isGameOver() {
        for (int stones : rows) {
            if (stones > 0) return false;
        }
        return true;
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
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
        // Nim kann nicht unentschieden enden
        return false;
    }

    // Optional: Spielfeld anzeigen (z.B. für Debug)
    public void printBoard() {
        System.out.println("Aktuelles Nim-Spielfeld:");
        for (int i = 0; i < rows.length; i++) {
            System.out.print("Reihe " + (i + 1) + ": ");
            for (int j = 0; j < rows[i]; j++) {
                System.out.print("O ");
            }
            System.out.println("(" + rows[i] + " Steine)");
        }
        System.out.println();
    }
}
