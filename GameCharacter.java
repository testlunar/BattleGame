public abstract class GameCharacter implements Fighting {

    private String name;
    private int healthPoints;
    private int strength;
    private int skill;
    private int experience;
    private int gold;

    public GameCharacter(String name, int healthPoints, int strength, int skill, int experience, int gold) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.skill = skill;
        this.experience = experience;
        this.gold = gold;
    }

    public int attack() {
        if (skill * 3 > getRandomValue()) return strength;
        if (skill * 3 >80) return strength*2; //критический удар х2
        else return  0; //промах
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("Здоровье персонажа %s: %d", name, healthPoints);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getSkill() {
        return skill;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }
}
