/**
 * Class that represents a Mario Kart Character
 * @author leonardosilva
 */
package org.example;

public class Character {
    private String name;
    private int speed;
    private int maneuverability;
    private int power;

    public Character(String name, int speed, int maneuverability, int power) {
        this.name = name;
        this.speed = speed;
        this.maneuverability = maneuverability;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getManeuverability() {
        return maneuverability;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Character{" +
            "name='" + name + '\'' +
            ", speed=" + speed +
            ", maneuverability=" + maneuverability +
            ", power=" + power +
            '}';
    }
}
