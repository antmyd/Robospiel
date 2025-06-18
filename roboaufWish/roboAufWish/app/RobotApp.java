package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
/**
 * * Klasse für den Hauptprogrammablauf des Spiels.
 * @author Paul Schottstädt
 * @author Anton Mydlach
 */
public class RobotApp {

    public static final String SAVE_FILE_NAME = "save";
    private RobotGame game;
    private boolean gameRunning = true;

    public static void main(String[] args) {
        System.out.println("Welcome to the robot adventure");
        System.out.println("========================================\n");

        RobotApp app = new RobotApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }
    /**
     * Zeigt das Hauptmenü im Terminal an.
     */
   /**
 * Zeigt das Hauptmenü im Terminal an – mit dynamischen Optionen je nach Spielstatus.
 */
private void showMainMenu() {
    System.out.println("You're in the main menu");
    System.out.println("What do you want to do next?");
    System.out.println("(1) Start new game");

    if (isGameRunning()) {
        System.out.println("(2) Continue game");
        System.out.println("(4) Save game");
    }

    if (hasSavedGame()) {
        System.out.println("(3) Load game");
        System.out.println("(5) Delete saved game");
    }

    System.out.println("(6) Quit");
    System.out.println();
    System.out.println("Please choose a number between 1 and 6: ");
}

    /**
     * Liest die Eingabe des Benutzers aus der Konsole.
     * 
     * @return die eingegebene Zeichenkette
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }
     /**
     * Verarbeitet die vom Benutzer eingegebene Auswahl aus dem Hauptmenü.
     * 
     * @param input die Eingabe des Benutzers als String
     */
    /**
 * Verarbeitet die vom Benutzer eingegebene Auswahl aus dem Hauptmenü.
 *
 * @param input die Eingabe des Benutzers als String
 */
private void handleUserInput(String input) {
    switch (input) {
        case "1":
            startNewGame();
            break;
        case "2":
            if (isGameRunning()) {
                continueGame();
            } else {
                System.out.println("No game is currently running.");
            }
            break;
        case "3":
            if (hasSavedGame()) {
                loadGame();
            } else {
                System.out.println("No saved game found.");
            }
            break;
        case "4":
            if (isGameRunning()) {
                saveGame();
            } else {
                System.out.println("No game to save.");
            }
            break;
        case "5":
            if (hasSavedGame()) {
                deleteGame();
            } else {
                System.out.println("No saved game to delete.");
            }
            break;
        case "6":
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid input. Please choose a correct number between 1 and 6.");
            break;
    }
}

     /**
     * Startet ein neues Spiel.
     */
    private void startNewGame() {
        this.game = new RobotGame();
        continueGame();
    }
     /**
     * Führt das aktuell gestartete Spiel aus.
     */
    private void continueGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }
    
    /**
     * Löscht den gespeicherten Spielstand, falls vorhanden.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }
    /**
     * Speichert das aktuelle Spielobjekt in einer Datei.
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }
    /**
     * Lädt ein zuvor gespeichertes Spiel aus der Datei.
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (RobotGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }
    /**
     * Prüft, ob ein Spiel aktuell läuft.
     * 
     * 
     */
    private boolean isGameRunning() {
        return game != null;
    }
    /**
     * Prüft, ob das aktuelle Spiel als abgeschlossen markiert ist.
     * 
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }
    /**
     * Prüft, ob ein gespeicherter Spielstand existiert.
     * 
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
