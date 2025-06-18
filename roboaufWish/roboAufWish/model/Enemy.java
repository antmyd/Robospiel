package model;
/**
 * Repräsentiert ein feindliches Wesen, welches dem Roboter angreifen, Schaden zufügen oder Energie entziehen kann
 * 
 * @author Paul Schottstädt
 */
public abstract class Enemy {

    private String name;
    private int lifePoints;

    public Enemy(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
    }

    public abstract void fight(Robot robot);

    public abstract void takeDamage(int amount);

    public abstract void isDefeated();

    //getter
     public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }
    
    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
    
    


}