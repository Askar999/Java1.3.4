package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {300, 260, 250, 270, 235, 400, 200, 280};
    public static int[] heroesDamage = {30, 20, 15, 10, 0, 3, 25, 40};
    public static String[] heroesAttackType = {"Thor", "Physical",
            "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk"};
    public static int roundNumber;
    public static Random random = new Random();


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        if (randomIndex == 4) {
            chooseBossDefence();
        } else {
            bossDefenceType = heroesAttackType[randomIndex];
            System.out.println("Boss chose: " + bossDefenceType);
        }
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        workOfMedic();
        golem();
        berserk();
        heroesHit();
        printStatistics();
    }

    public static void workOfMedic() {
        int randomIndex = random.nextInt(heroesHealth.length); // 0,1,2
        int hp = random.nextInt(30) + 20;

        if (randomIndex == 4) {
            workOfMedic();
        } else {
            if (heroesHealth[4] > 0) {
                if (heroesHealth[randomIndex] < 100 && heroesHealth[randomIndex] > 0) {
                    heroesHealth[randomIndex] += hp;
                    System.out.println("\t\t" + heroesAttackType[randomIndex] + " healed for: " + hp);
                }
            }
        }
    }
    public static void Lusky(){
        if (heroesHealth[6]>0){
            Boolean luck = true;
            Boolean unluck = false;
            random = new Random(luck || unluck);
            Boolean luck= random.nextBoolean();
            if (luck)
        }
    }
    public static void golem() {
        if (heroesHealth[5] > 0) {
            int takeDamage = bossDamage / 5;
            int aliveHeroes = 0;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 5) continue;
                else if (heroesHealth[i] >= 0) {
                    heroesHealth[i] += takeDamage;      // 100 - 50 + 10
                    aliveHeroes++;
                }
            }
            heroesHealth[5] -= (aliveHeroes * takeDamage);
            System.out.println("\t\tGolem took " + (aliveHeroes * takeDamage));
        }
    }

    public static void berserk() {
        int block = random.nextInt(15) + 10;
        if (heroesHealth[heroesHealth.length - 1] > 0) {
            heroesHealth[heroesDamage.length - 1] += block;
            bossHealth -= block;
            System.out.println("\t\tBerserk kicked for: " + block);
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
//                    System.out.println("Critical damage: " + ((heroesDamage[i] * coeff) - heroesDamage[i]));
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i]; //bossHealth -= heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("\n" + roundNumber + " ROUND -------------");
        System.out.println("\nBoss health: " + bossHealth + " (" + bossDamage + ")\n");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }
}
/*
● Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
● Добавить n-го игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд,вследствие чего босс
пропускает 1 раунд и не наносит урон героям. //random.nextBoolean(); - true, false
*/
