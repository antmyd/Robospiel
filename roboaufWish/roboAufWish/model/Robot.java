package model;

import java.io.Serializable;
import java.util.Random;

/**
 * Ein einfacher Roboter, der Energie und Schaden hat und Aufgaben erfüllen kann.
 * Er kann Erfahrung sammeln, repariert werden und außer Betrieb gehen.
 * 
 * @author Anton Mydlach
 */
public class Robot implements Serializable {

    private static final long serialVersionUID = -5081867320134061285L;

    private String name;
    private int energy;
    private int damage;
    private int experiencePoints;
    private boolean operational;

    private Random random = new Random();

    /**
     * Erstellt einen neuen Roboter mit dem gegebenen Namen.
     * Energie = 50, Schaden = 0, Erfahrung = 0, betriebsbereit = true
     */
    public Robot(String name) {
        this.name = name;
        this.energy = 50;
        this.damage = 0;
        this.experiencePoints = 0;
        this.operational = true;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getDamage() {
        return damage;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public boolean isOperational() {
        return operational;
    }

    /**
     * Lädt Energie auf, aber nur bis maximal 100.
     */
    public void recharge(int energyAmount) {
        if (!operational) return;

        energy += energyAmount;
        if (energy > 100) {
            energy = 100;
        }
    }

    /**
     * Verbraucht Energie mit zufälliger Abweichung (kritisch oder schwach).
     * Wenn Energie <= 0, ist der Roboter nicht mehr betriebsbereit.
     */
    public void drainEnergy(int amount) {
        if (!operational) return;

        int zufall = random.nextInt(100);

        if (zufall < 10) {
            amount *= 2; // kritischer Treffer
        } else if (zufall < 20) {
            amount /= 2; // schwacher Treffer
        }

        energy -= amount;

        if (energy <= 0) {
            energy = 0;
            operational = false;
        }
    }

    /**
     * Fügt dem Roboter Schaden zu. Ab 100 Schaden ist er kaputt.
     */
    public void takeDamage(int amount) {
        if (!operational) return;

        int zufall = random.nextInt(100);

        if (zufall < 10) {
            amount *= 2;
        } else if (zufall < 20) {
            amount /= 2;
        }

        damage += amount;

        if (damage >= 100) {
            damage = 100;
            operational = false;
        }
    }

    /**
     * Repariert Schaden, aber maximal bis 0 runter.
     */
    public void repairDamage(int amount) {
        damage -= amount;
        if (damage < 0) {
            damage = 0;
        }
    }

    /**
     * Erhöht die Erfahrungspunkte des Roboters.
     */
    public void addExperiencePoints(int points) {
        experiencePoints += points;
    }
}
