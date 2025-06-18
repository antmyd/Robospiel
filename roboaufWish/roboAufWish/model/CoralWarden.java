package model;

import java.util.Random;

public class CoralWarden extends Enemy {
    public CoralWarden(String name, int lifePoints) {
        super(name, lifePoints);
    }


@Override
public void fight(Robot robot) {
   if (robot == null || !robot.isOperational()) {
        return; // Ignoriere inaktive Roboter
    }
    Random rand = new Random();
    System.out.println(getName() + " umhüllt " + robot.getName() + " mit giftigen Korallenfilamenten!");

    // Basis-Energieraub
    int energyDrain = 20; 

    // Zufallsentscheidung mit int (0-99)
    int chance = rand.nextInt(100); // 0 bis 99

    if (chance < 10) {  // 10% Chance (0-9)
        // Kritischer Treffer
        energyDrain *= 2;
        System.out.println("KRITISCH! Das Gift lähmt " + robot.getName() + " vollständig!");
    } else if (chance < 20) {  // 10% Chance (10-19)
        // Schwacher Treffer
        energyDrain /= 2;
        System.out.println("Schwache Wirkung! " + robot.getName() + " widersteht teilweise dem Gift.");
    }
    // 80% Chance (20-99): Normaler Treffer

    robot.drainEnergy(energyDrain); // Energie reduzieren
}

    @Override
    public void takeDamage(int amount) {
    int newLifePoints = getLifePoints() - amount;
    if (newLifePoints < 0) {
        newLifePoints = 0;  // Lebenspunkte nie negativ
    }
    
    // Ausgabe des Schadens und der restlichen Lebenspunkte (neu)
    System.out.println("Der Gegner erleidet " + amount + " Schaden.");
    System.out.println("Verbleibende Lebenspunkte: " + newLifePoints);
    
    setLifePoints(newLifePoints);  // Original-Logik (nur mit gesichertem Wert)
    if (newLifePoints <= 0) {
        isDefeated();
    }
}

    @Override
    public void isDefeated() {
        System.out.println(getName() + " wurde besiegt!");
    }
    
}
