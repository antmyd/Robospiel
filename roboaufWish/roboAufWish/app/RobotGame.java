package app;

import games.Nim;
import games.TicTacToe;
import java.util.Random;
import java.util.Scanner;
import model.CoralWarden;
import model.Enemy;
import model.Robot;
import model.Room;
import model.Shuttle;
import model.SoulAngler;



public class RobotGame {
    private Robot robot;
    private final Room[] rooms = new Room[3];
    private final Shuttle shuttle;
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    public RobotGame() {
        this.shuttle = new Shuttle("name");
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        if (robot == null) {
    System.out.print("Bitte gib deinem Roboter einen Namen: ");
    String name = scanner.nextLine();
    this.robot = new Robot(name);
    System.out.println("Willkommen, " + name + "!");
}


        while (gameRunning && robot.isOperational()) {
            System.out.println("\n=== SPIELMENÜ ===");
            System.out.println("1) Forschungsstation erkunden");
            System.out.println("2) Status anzeigen");
            System.out.println("3) Energie aufladen");
            System.out.println("4) Reparieren");
            System.out.println("5) Spiel verlassen");
            System.out.print("Wähle eine Option (1-5): ");

            String input = scanner.nextLine();
            handleGameMenu(input, scanner);
        }

        if (!robot.isOperational()) {
            System.out.println("Dein Roboter ist nicht mehr betriebsbereit. Spiel vorbei!");
        }
    }

   
    private void handleGameMenu(String input, Scanner scanner) {
        switch (input) {
            case "1":
                exploreStation();
                break;
            case "2":
                showStatus();
                break;
            case "3":
    int maxPossibleRecharge = 100 - robot.getEnergy();  // Was noch fehlt bis 100
    int rechargeAmount = Math.min(10, maxPossibleRecharge);  // Maximal 10%, aber nicht über das Limit
    
    if (rechargeAmount > 0) {
        robot.recharge(rechargeAmount);
        System.out.println("Energie um " + rechargeAmount + "% aufgeladen.");
        System.out.println("Neuer Stand: " + robot.getEnergy());
    } else {
        System.out.println("Energie bereits voll!");
    }
    break;
            case "4":
                reparieren();
                break;
            case "5":
                gameRunning = false;
                System.out.println("Spiel wird beendet...");
                break;
            default:
                System.out.println("Ungültige Eingabe.");
        }
    }

    private void showStatus() {
        System.out.println("=== ROBOTERSTATUS ===");
        System.out.println("Name: " + robot.getName());
        System.out.println("Energie: " + robot.getEnergy());
        System.out.println("Schaden: " + robot.getDamage());
        System.out.println("Erfahrung: " + robot.getExperiencePoints());
        System.out.println("Betriebsbereit: " + (robot.isOperational() ? "Ja" : "Nein"));
        System.out.println("Anzahl an gefundenen Ersatzteilen: "+this.shuttle.countArtifacts());
        System.out.println("Fehlende Artifakte: "+this.shuttle.nameMissingArtifacts());
        //System.out.println("Bereits entdeckte Räume: ");
       
    }

  private void exploreStation() {
    System.out.println("\n" + robot.getName() + " erkundet die Forschungsstation...");
    robot.drainEnergy(10); // Fixed 10% energy drain

    switch (new Random().nextInt(4)) { // Cleaner 50/25/25 distribution
        case 0, 1 -> { // 50% nothing
            System.out.println("Nichts passiert. Der Roboter setzt die Suche fort.");
            robot.addExperiencePoints(5);
        }
        case 2 -> { // 25% enemy
            Enemy enemy = generateRandomEnemy();
            System.out.println("Ein " + enemy.getName() + " blockiert den Weg!");
            handleEnemyEncounter(enemy);
        }
         
        case 3 -> { // 25%
            System.out.println("Verschlossener Raum entdeckt!");
        }
        
        
    }
}

private void handleEnemyEncounter(Enemy enemy) {
    Scanner scanner = new Scanner(System.in);
    boolean resolved = false;

    while (!resolved && robot.isOperational() && enemy.getLifePoints() > 0) {
        System.out.println("\n--- Begegnung mit " + enemy.getName() + " ---");
        System.out.println("1) Kämpfen");
        System.out.println("2) Spiel");
        System.out.println("3) Fliehen");
        System.out.print("Wahl: ");

        switch (scanner.nextLine()) {
            case "1": { // Fight
                if (directCombat(enemy)) {
                    robot.addExperiencePoints(5); // Win
                } else {
                    robot.addExperiencePoints(1); // Lose
                }
                resolved = true;
            }
            case "2": // Spiel
    if (enemy.getName().equalsIgnoreCase("Korallenwächter")) {
        TicTacToe game = new TicTacToe();
        while (!game.isFinished()) {
            game.playNextRound();
            game.printBoard(); // Optional, falls vorhanden
        }
        if (game.isWon()) {
            System.out.println("Du hast das Spiel gewonnen und den Gegner besiegt!");
            robot.addExperiencePoints(5);
        } else if (game.isLost()) {
            System.out.println("Du hast das Spiel verloren.");
            robot.addExperiencePoints(1);
        } else if (game.isTie()) {
            System.out.println("Das Spiel endete unentschieden.");
        }
        resolved = true;
    } else if (enemy.getName().equalsIgnoreCase("Seelenangler")) {
        Nim game = new Nim();
        while (!game.isFinished()) {
            game.playNextRound();
            game.printBoard(); // Falls du diese Methode implementierst
        }
        if (game.isWon()) {
            System.out.println("Du hast das Spiel gewonnen und den Gegner besiegt!");
            robot.addExperiencePoints(5);
        } else if (game.isLost()) {
            System.out.println("Du hast das Spiel verloren.");
            robot.addExperiencePoints(1);
        } else if (game.isTie()) {
            System.out.println("Das Spiel endete unentschieden.");
        }
        resolved = true;
    } else {
        System.out.println("Kein Spiel verfügbar für diesen Gegner.");
    }
    break;


            case "3": { // Flee
                if (new Random().nextInt(10) == 0) { // 10% chance
                    System.out.println("Flucht gelungen!");
                    resolved = true;
                } else {
                    System.out.println("Flucht fehlgeschlagen!");
                    // Auto-roll combat
                }
            }
            default: System.out.println("Ungültig!");
        }
    }
}

private boolean directCombat(Enemy enemy) {
    while (robot.isOperational() && enemy.getLifePoints() > 0) {
        // Player turn
        enemy.takeDamage(robot.getDamage());
        System.out.println(robot.getName() + " greift an (" + robot.getDamage() + " Schaden)");
        
        // Enemy turn if alive
        if (enemy.getLifePoints() > 0) {
            enemy.fight(robot); // Uses enemy-specific behavior
        }
    }
    return robot.isOperational(); // true if player won
}

private Enemy generateRandomEnemy() {
    Random random = new Random();
    if (random.nextBoolean()) {
        return new CoralWarden("Korallenwächter", 30); // Name und Lebenspunkte anpassbar
    } else {
        return new SoulAngler("Seelenfänger", 25);
    }
}

private void reparieren() {

Scanner scanner = new Scanner(System.in);
Random rand = new Random();
boolean inRepairMenu = true;

while (robot.isOperational() && inRepairMenu) {
   
    System.out.println(" === Willkommen im Wartungsmenü für Reparatur! ===");
    System.out.println(" ===           Was willst du tun?              ===");
    System.out.println();
    System.out.println("1) Selbstreparatur");
    System.out.println("2) Installation gesammelter Artefakte und Ersatzteile");
    System.out.println("3) Zum Spielmenü");

    switch (scanner.nextLine()) {
            case "1" -> {
                System.out.println("Beginne mit Selbstreparatur...");
                System.out.println();
                int heilung = rand.nextInt(50);

                robot.repairDamage(heilung);

                System.out.println("aktualiserter Schaden: "+ robot.getDamage());

                robot.recharge(heilung);

                System.out.println("aktualisertes Energielevel: "+ robot.getEnergy());
                
                System.out.println("Selbstreparatur abgeschlossen!");

                

            }
            case "2" -> {
                System.out.println(".");
            }
            case "3" -> { 
                inRepairMenu = false;
                return;
            }
            default -> System.out.println("Ungültig!");
        } 
}



}

    public Robot getRobot() {
        return robot;
    }
}

    


