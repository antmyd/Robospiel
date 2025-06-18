package model;

public class SoulAngler extends Enemy {
   public SoulAngler(String name, int lifePoints) {
        super(name, lifePoints);
    }

    @Override
    public void fight(Robot robot) {
    if (robot == null || !robot.isOperational()) {
        return; // Ignoriere inaktive Roboter
    }
    System.out.println(getName() + " greift " + robot.getName() + " an!");

    // Basis-Schaden
    int damage = 15; 
    robot.takeDamage(damage); // Schaden anwenden
}

    @Override
    public void takeDamage(int amount) {
        setLifePoints(getLifePoints() - amount); // Leben reduzieren
        if (getLifePoints() <= 0) isDefeated();
    }

    @Override
    public void isDefeated() {
        System.out.println(getName() + " wurde besiegt!");
    } 
}
