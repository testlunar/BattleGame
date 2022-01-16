import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private static BufferedReader br;
    private static GameCharacter player = null;
    private static BattlePlace battlePlace = null;


    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battlePlace = new BattlePlace();
        System.out.println("Введите имя персонажа:");

        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    30,
                    30,
                    0,
                    0
            );
            System.out.println(String.format("Нашего героя зовут %s!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        battlePlace.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта, поро%d золота, %d единиц здоровья.", player.getName(), player.getExperience(), player.getGold(), player.getHealthPoints()));
                System.out.println("Желаете продолжить или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static GameCharacter createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Гоблин",
                70,
                20,
                10,
                50,
                30
        );
        else return new Skeleton(
                "Скелет",
                50,
                30,
                25,
                50,
                20
        );
    }
}
