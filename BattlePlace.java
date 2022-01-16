

public class BattlePlace {

    public void fight(GameCharacter hero, GameCharacter monster, FightCallback fightCallback){
        Runnable runnable = () -> {
        int turn = 1;
        boolean isFightEnded = false;
        while (!isFightEnded) {
            System.out.println("Ход: " + turn + "");
            if (turn++ % 2 != 0) {
                isFightEnded = makeHit(monster, hero, fightCallback);
            } else {
                isFightEnded = makeHit(hero, monster, fightCallback);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Thread thread = new Thread(runnable);
       thread.start();
}

    private Boolean makeHit(GameCharacter defender, GameCharacter attacker, FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья.", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Персонаж погиб!");
            fightCallback.fightLost();
            return true;

        } else if(defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }
    }
}
