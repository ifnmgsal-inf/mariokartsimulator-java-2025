/**
 * Main class. Game engine.
 * @author leonardosilva
 */
package org.example;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static org.example.ErrorMessage.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String[] CHARACTER_NAME = {"Mario", "Peach", "Yoshi", "Bowser", "Luigi", "Donkey Kong"};
    private static final int[] CHARACTER_SPEED = {4, 3, 2, 5, 3, 2};
    private static final int[] CHARACTER_MANEUVERABILITY = {3, 4, 4, 2, 4, 2};
    private static final int[] CHARACTER_POWER = {3, 2, 3, 5, 4, 5};
    private static final String[] TRACK_NAME = {"RETA", "CURVA", "CONFRONTO"};

    private static final int EXIT_CODE = CHARACTER_NAME.length + 1;
    private static final int NUMBER_OF_ROUNDS = 5;
    private static final int DICE_SIDES = 6;

    private static final int STRAIGHT_LINE = 0;
    private static final int TURN = 1;
    private static final int CONFRONTATION = 2;

    private static Character player1;
    private static Character player2;
    private static Random localRandom = null;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            selectPlayers(sc);
            gameEngine();
        } catch (Exception e) {
            System.out.println(UNEXPECTED_EXCEPTION);
        }
    }

    private static void gameEngine() throws InterruptedException {
        for(int i = 0; i < NUMBER_OF_ROUNDS; i++) {
            System.out.println("Rodada " + (i+1) + " 🏁");
            int trackType = randomNumber(TRACK_NAME.length);
            System.out.println("Tipo de pista = " + TRACK_NAME[trackType]);
            int dicePlayer1 = randomNumber(DICE_SIDES)+1;
            System.out.println(player1.getName() + " tirou " + dicePlayer1 + " no dado. 🎲");
            int dicePlayer2 = randomNumber(DICE_SIDES)+1;
            System.out.println(player2.getName() + " tirou " + dicePlayer2 + " no dado. 🎲");
            int sumPlayer1 = abilityNeeded(trackType, player1) + dicePlayer1;
            int sumPlayer2 = abilityNeeded(trackType, player2) + dicePlayer2;
            System.out.println("A pontuação de " + player1.getName() + " na rodada foi " + sumPlayer1);
            System.out.println("A pontuação de " + player2.getName() + " na rodada foi " + sumPlayer2);
            if (trackType == CONFRONTATION) {
                manageScoreLoss(sumPlayer1, sumPlayer2);
            } else {
                manageScoreIncrease(sumPlayer1, sumPlayer2);
            }
            System.out.println();
            Thread.sleep(3000);
        }
        finalResult();
    }

    private static void finalResult() {
        System.out.println("Pontuação para " + player1.getName() + " = " + player1.getScore());
        System.out.println("Pontuação para " + player2.getName() + " = " + player2.getScore());

        if (player1.getScore() > player2.getScore())
            System.out.println(player1.getName() + " VENCEU A CORRIDA !!! 🥁✨🥁✨" );
        else if (player1.getScore() < player2.getScore())
            System.out.println(player2.getName() + " VENCEU A CORRIDA !!! 🥁✨🥁✨" );
        else
            System.out.println("RESULTADO FINAL = EMPATE! ☺️");
    }

    private static void manageScoreIncrease(int sumPlayer1, int sumPlayer2) {
        if (sumPlayer1 > sumPlayer2) {
            System.out.println(player1.getName() + " MARCOU 1 ponto! 🥳");
            player1.setScore(player1.getScore() + 1);
        } else if (sumPlayer1 < sumPlayer2) {
            System.out.println(player2.getName() + " MARCOU 1 ponto! 🥳");
            player2.setScore(player2.getScore() + 1);
        } else {
            System.out.println("Houve empate nesta rodada. Ninguém marcou ponto!");
        }
    }

    private static void manageScoreLoss(int sumPlayer1, int sumPlayer2) {
        if (sumPlayer1 > sumPlayer2) {
            if (player2.getScore() > 0) {
                System.out.println(player2.getName() + " PERDE 1 ponto! \uD83D\uDE1E");
                player2.setScore(player2.getScore() - 1);
            } else {
                System.out.println(player2.getName() + " PERDEU este confronto! Permanece com zero pontos! \uD83D\uDE1E");
            }
        } else if (sumPlayer1 < sumPlayer2) {
            if (player1.getScore() > 0) {
                System.out.println(player1.getName() + " PERDE 1 ponto! \uD83D\uDE1E");
                player1.setScore(player1.getScore() - 1);
            } else {
                System.out.println(player1.getName() + " PERDEU este confronto! Permanece com zero pontos! \uD83D\uDE1E");
            }
        } else {
            System.out.println("Houve empate nesta rodada. Ninguém perdeu ponto!");
        }
    }

    private static int abilityNeeded(int trackType, Character player) {
        int ability;
        if (trackType == STRAIGHT_LINE)
            ability = player.getSpeed();
        else if (trackType == TURN)
            ability = player.getManeuverability();
        else
            ability = player.getPower();

        return ability;
    }

    private static int randomNumber(int length) {
        return getRandomInstance().nextInt(length);
    }

    private static Random getRandomInstance() {
        if (Main.localRandom == null) Main.localRandom = new Random();
        return Main.localRandom;
    }

    private static void selectPlayers(Scanner sc) {
        int idPlayer1, idPlayer2;

        idPlayer1 = readPlayer(sc, "Selecione o jogador 1: ", 0);
        if (idPlayer1 != EXIT_CODE) {
            idPlayer2 = readPlayer(sc, "Selecione o jogador 2: ", idPlayer1);

            if (idPlayer2 != EXIT_CODE) {
                player1 = characterById(idPlayer1);
                player2 = characterById(idPlayer2);

                showSelectedCharacters();
            }
        }
    }

    private static void showSelectedCharacters() {
        System.out.println("O jogador 1 selecionado foi: ");
        System.out.println(player1);
        System.out.println("O jogador 2 selecionado foi: ");
        System.out.println(player2);
        System.out.println();
    }

    private static Character characterById(int id) {
        return new Character(CHARACTER_NAME[id - 1], CHARACTER_SPEED[id - 1],
                CHARACTER_MANEUVERABILITY[id - 1], CHARACTER_POWER[id - 1]);
    }

    private static int readPlayer(Scanner sc, String selectMessage, int idAlreadyUsed) {
        int idPlayer;

        do {
            try {
                showMenu();
                System.out.println(selectMessage);
                idPlayer = sc.nextInt();

                if ((idPlayer < 1) || (idPlayer > EXIT_CODE))
                    throw new InputMismatchException(INVALID_CHARACTER_CODE);
                if (idPlayer == idAlreadyUsed)
                    throw new InputMismatchException(SAME_SELECTED_CHARACTER);
            } catch (InputMismatchException ime) {
                String message = ime.getMessage();
                System.out.println(message == null ? INVALID_CHARACTER_CODE : message );
                sc.nextLine();
                idPlayer=0;
            }
        } while (idPlayer == 0);

        return idPlayer;
    }

    private static void showMenu() {
        System.out.println("<<< Mario Kart Simulator >>>");
        System.out.println("Menu:");
        for(int i=0; i < CHARACTER_NAME.length; i++)
            System.out.println((i+1) + " - " + CHARACTER_NAME[i]);
        System.out.println(EXIT_CODE + " - " + "Cancelar e SAIR");
    }
}