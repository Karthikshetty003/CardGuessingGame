package cardguessinggame;

import java.util.Random;
import java.util.Scanner;

public class CardGuessingGame {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // Card ranks
    static String[] ranks = {
        "Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"
    };

    // Card suits
    static String[] suits = {
        "Hearts", "Diamonds", "Clubs", "Spades"
    };

    public static void main(String[] args) {

        boolean playAgain = true;

        showWelcome();

        while (playAgain) {

            playGame();

            System.out.println();
            System.out.println("========================================");
            System.out.println("          PLAY AGAIN?");
            System.out.println("========================================");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = getChoice(1, 2);

            if (choice == 2) {
                playAgain = false;
            }
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("       THANK YOU FOR PLAYING! 🃏");
        System.out.println("========================================");
        System.out.println("Keep practicing and become a CARD MASTER!");
        System.out.println("========================================");

        scanner.close();
    }

    // ==============================
    // WELCOME SCREEN
    // ==============================

    static void showWelcome() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║                                      ║");
        System.out.println("║       🃏 CARD GUESSING GAME 🃏       ║");
        System.out.println("║                                      ║");
        System.out.println("║          ULTIMATE CHALLENGE          ║");
        System.out.println("║                                      ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.println();
        System.out.println("🎯 Guess the cards");
        System.out.println("❤️  Protect your lives");
        System.out.println("🔥 Build your streak");
        System.out.println("⭐ Earn maximum points");
        System.out.println();
    }

    // ==============================
    // MAIN GAME
    // ==============================

    static void playGame() {

        int score = 0;
        int lives = 3;
        int streak = 0;
        int round = 1;

        System.out.println();
        System.out.println("========================================");
        System.out.println("           GAME STARTED! 🎮");
        System.out.println("========================================");

        while (lives > 0) {

            // Generate random card
            int rankIndex = random.nextInt(13);
            int suitIndex = random.nextInt(4);

            String cardRank = ranks[rankIndex];
            String cardSuit = suits[suitIndex];

            // Select random challenge
            int challenge = random.nextInt(4) + 1;

            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║              ROUND " + round + "                ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.println();
            System.out.println("❤️ Lives  : " + getLives(lives));
            System.out.println("⭐ Score  : " + score);
            System.out.println("🔥 Streak : " + streak);

            System.out.println();

            boolean correct = false;

            // ==============================
            // CHALLENGE 1 - EXACT CARD
            // ==============================

            if (challenge == 1) {

                System.out.println("🎯 CHALLENGE: GUESS THE CARD");
                System.out.println();

                int rankChoice = showRankMenu();

                int suitChoice = showSuitMenu();

                if (rankChoice == rankIndex + 1 &&
                    suitChoice == suitIndex + 1) {

                    correct = true;
                }
            }

            // ==============================
            // CHALLENGE 2 - GUESS THE SUIT
            // ==============================

            else if (challenge == 2) {

                System.out.println("🎯 CHALLENGE: GUESS THE SUIT");
                System.out.println();

                System.out.println("Which suit is the hidden card?");
                System.out.println();

                System.out.println("1. ♥ Hearts");
                System.out.println("2. ♦ Diamonds");
                System.out.println("3. ♣ Clubs");
                System.out.println("4. ♠ Spades");

                System.out.print("\nYour answer: ");

                int answer = getChoice(1, 4);

                if (answer == suitIndex + 1) {
                    correct = true;
                }
            }

            // ==============================
            // CHALLENGE 3 - RED OR BLACK
            // ==============================

            else if (challenge == 3) {

                System.out.println("🎯 CHALLENGE: RED OR BLACK");
                System.out.println();

                System.out.println("Is the hidden card RED or BLACK?");
                System.out.println();

                System.out.println("1. 🔴 RED");
                System.out.println("2. ⚫ BLACK");

                System.out.print("\nYour answer: ");

                int answer = getChoice(1, 2);

                boolean isRed =
                    cardSuit.equals("Hearts") ||
                    cardSuit.equals("Diamonds");

                if ((answer == 1 && isRed) ||
                    (answer == 2 && !isRed)) {

                    correct = true;
                }
            }

            // ==============================
            // CHALLENGE 4 - HIGH OR LOW
            // ==============================

            else {

                System.out.println("🎯 CHALLENGE: HIGH OR LOW");
                System.out.println();

                System.out.println("Is the card HIGH or LOW?");
                System.out.println();
                System.out.println("LOW  = Ace to 6");
                System.out.println("HIGH = 7 to King");

                System.out.println();
                System.out.println("1. LOW");
                System.out.println("2. HIGH");

                System.out.print("\nYour answer: ");

                int answer = getChoice(1, 2);

                boolean isHigh = rankIndex >= 6;

                if ((answer == 2 && isHigh) ||
                    (answer == 1 && !isHigh)) {

                    correct = true;
                }
            }

            // ==============================
            // CHECK ANSWER
            // ==============================

            System.out.println();
            System.out.println("----------------------------------------");

            if (correct) {

                streak++;

                int points = 100;

                // Streak bonus
                if (streak >= 3) {
                    points += 50;
                }

                if (streak >= 5) {
                    points += 100;
                }

                score += points;

                System.out.println("🎉 CORRECT!");
                System.out.println("🃏 Hidden Card: " + cardRank + " of " + cardSuit);
                System.out.println("⭐ +" + points + " points!");
                System.out.println("🔥 Current Streak: " + streak);

                if (streak >= 3) {
                    System.out.println("🔥 STREAK BONUS ACTIVATED!");
                }

            } else {

                lives--;
                streak = 0;

                System.out.println("❌ WRONG ANSWER!");
                System.out.println("🃏 The card was: " + cardRank + " of " + cardSuit);
                System.out.println("❤️ You lost one life.");
                System.out.println("❤️ Lives remaining: " + lives);
                System.out.println("🔥 Streak reset!");
            }

            System.out.println("----------------------------------------");

            round++;
        }

        // ==============================
        // GAME OVER
        // ==============================

        showGameOver(score);
    }

    // ==============================
    // RANK MENU
    // ==============================

    static int showRankMenu() {

        System.out.println("Choose the card rank:");
        System.out.println();

        for (int i = 0; i < ranks.length; i++) {

            System.out.println((i + 1) + ". " + ranks[i]);
        }

        System.out.print("\nYour rank: ");

        return getChoice(1, 13);
    }

    // ==============================
    // SUIT MENU
    // ==============================

    static int showSuitMenu() {

        System.out.println();
        System.out.println("Choose the card suit:");
        System.out.println();

        System.out.println("1. ♥ Hearts");
        System.out.println("2. ♦ Diamonds");
        System.out.println("3. ♣ Clubs");
        System.out.println("4. ♠ Spades");

        System.out.print("\nYour suit: ");

        return getChoice(1, 4);
    }

    // ==============================
    // INPUT VALIDATION
    // ==============================

    static int getChoice(int min, int max) {

        while (true) {

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();

                if (choice >= min && choice <= max) {
                    return choice;
                }
            } else {
                scanner.next();
            }

            System.out.print(
                "⚠️ Invalid choice! Enter a number between "
                + min + " and " + max + ": "
            );
        }
    }

    // ==============================
    // DISPLAY LIVES
    // ==============================

    static String getLives(int lives) {

        String result = "";

        for (int i = 0; i < lives; i++) {
            result += "❤️ ";
        }

        return result;
    }

    // ==============================
    // GAME OVER SCREEN
    // ==============================

    static void showGameOver(int score) {

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║              GAME OVER               ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.println();
        System.out.println("⭐ FINAL SCORE: " + score);

        System.out.println();

        if (score >= 1500) {

            System.out.println("🏆 RANK: CARD MASTER!");
            System.out.println("🔥 Incredible! You are a card genius!");

        } else if (score >= 1000) {

            System.out.println("🥇 RANK: CARD EXPERT!");
            System.out.println("🔥 Excellent performance!");

        } else if (score >= 500) {

            System.out.println("🥈 RANK: CARD PLAYER!");
            System.out.println("👍 Good job!");

        } else if (score >= 200) {

            System.out.println("🥉 RANK: BEGINNER!");
            System.out.println("🙂 Keep practicing!");

        } else {

            System.out.println("🎯 RANK: ROOKIE!");
            System.out.println("💪 Don't give up!");
        }

        System.out.println();
        System.out.println("========================================");
    }
}