package org.example;

public class Character {
    private String name;
    private String gameClass;
    private int level;
    private int attack;
    private int armor;
    private int money;
    public Character() {
    }

    public Character(String name, String gameClass, int level, int attack, int armor, int money) {
        this.name = name;
        this.gameClass = gameClass;
        this.level = level;
        this.attack = attack;
        this.armor = armor;
        this.money = money;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameClass() {
        return gameClass;
    }

    public void setGameClass(String gameClass) {
        this.gameClass = gameClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
