/**
 * Main class. Game engine.
 * @author leonardosilva
 */
package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.ErrorMessage.*;

public class Main {
    private static final String[] CHARACTER_NAME = {"Mario", "Peach", "Yoshi", "Bowser", "Luigi", "Donkey Kong"};
    private static final int[] CHARACTER_SPEED = {4, 3, 2, 5, 3, 2};
    private static final int[] CHARACTER_MANEUVERABILITY = {3, 4, 4, 2, 4, 2};
    private static final int[] CHARACTER_POWER = {3, 2, 3, 5, 4, 5};
    private static final int EXIT_CODE = CHARACTER_NAME.length+1;

    private static Character player1;
    private static Character player2;
    public static void main(String[] args) {
        try {
            selectPlayers();
        } catch (Exception e) {
            System.out.println(UNEXPECTED_EXCEPTION);
        }
    }

    public static void selectPlayers() {
        try (Scanner sc = new Scanner (System.in)) {
            int idPlayer1 = readPlayer(sc, "Selecione o jogador 1: ", 0);
            if (idPlayer1 != EXIT_CODE) {
                int idPlayer2 = readPlayer(sc, "Selecione o jogador 2: ", idPlayer1);

                if (idPlayer2 != EXIT_CODE) {
                    player1 = createCharacterById(idPlayer1-1);
                    player2 = createCharacterById(idPlayer2-1);

                    showSelectedCharacters();
                }
            }
        }

    }

    private static void showSelectedCharacters() {
        // Show selected players
        System.out.println("Jogador 1 selecionado foi: ");
        System.out.println(player1);
        System.out.println("Jogador 2 selecionado foi: ");
        System.out.println(player2);
    }

    private static Character createCharacterById(int id) {
        return new Character(CHARACTER_NAME[id], CHARACTER_SPEED[id],
            CHARACTER_MANEUVERABILITY[id], CHARACTER_POWER[id]);
    }

    private static void menuHeader() {
        System.out.println("<<<< MARIO KART SIMULATOR >>>");
        for (int i = 0; i < CHARACTER_NAME.length; i++)
            System.out.println((i + 1) + " - " + CHARACTER_NAME[i]);
        System.out.println(EXIT_CODE + " - Cancelar e SAIR");
    }

    private static int readPlayer(Scanner sc, String selectMessage, int alreadyUsedId) {
        int idPlayer=0;
        do {
            try {
                menuHeader();
                System.out.println(selectMessage);
                idPlayer = sc.nextInt();
                if ((idPlayer < 1) || (idPlayer > EXIT_CODE))
                    throw new InputMismatchException(INVALID_CHARACTER_CODE);
                if (idPlayer == alreadyUsedId)
                    throw new InputMismatchException(SAME_SELECTED_CHARACTER);
            } catch (InputMismatchException ime) {
                String message = ime.getMessage();
                System.out.println(message == null ? INVALID_CHARACTER_CODE : message);
                idPlayer=0;
                sc.nextLine();
            }
        } while (idPlayer == 0);

        return idPlayer;
    }
}