import java.util.Scanner;
import java.util.Random;
class Food {
    int x, y;
    boolean eaten = false;

    Food(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class MainGame {

    static int size = 10;
    static char[][] board = new char[size][size];

    static int pacmanA = 0, pacmanB = 0;
    static int ghostA = 9, ghostB = 9;

    static Food[] foods = new Food[5];
    static int score = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeFood();
        while (true) {
            initializeboard();
            printboard();
            System.out.print("Move (W=up, Z=down, A=left, S=right): ");
            char move = sc.next().charAt(0);
            movePacman(move);
            checkFood();
            moveGhost();
            if (check_collision()) {
                printboard();
                System.out.println("OOPSIE !! GAME OVER!! Ghost caught Pacman");
                System.out.println("Final Score: " + score);
                break;
            }
        }
    }
    static void initializeboard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
        for (int i = 0; i < foods.length; i++) {
            if (!foods[i].eaten) {
                board[foods[i].x][foods[i].y] = 'F';
            }
        }
        board[pacmanA][pacmanB] = 'P';
        board[ghostA][ghostB] = 'G';
    }
    static void printboard() {

        System.out.println("\nScore: " + score);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void movePacman(char move) {
        move = Character.toUpperCase(move);
        switch (move) {
            case 'W':
                if (pacmanA > 0) pacmanA--;
                break;

            case 'Z':
                if (pacmanA < size - 1) pacmanA++;
                break;

            case 'A':
                if (pacmanB > 0) pacmanB--;
                break;

            case 'S':
                if (pacmanB < size - 1) pacmanB++;
                break;
        }
    }
    static void moveGhost() {

        Random rand = new Random();
        int direction = rand.nextInt(4);

        switch (direction) {
            case 0:
                if (ghostA > 0) ghostA--;
                break;
            case 1:
                if (ghostA < size - 1) ghostA++;
                break;
            case 2:
                if (ghostB > 0) ghostB--;
                break;
            case 3:
                if (ghostB < size - 1) ghostB++;
                break;
        }
    }

    static boolean check_collision() {
        return pacmanA == ghostA && pacmanB == ghostB;
    }

    static void initializeFood() {
        foods[0] = new Food(5, 1);
        foods[1] = new Food(1, 9);
        foods[2] = new Food(4, 2);
        foods[3] = new Food(3, 5);
        foods[4] = new Food(1, 3);
        foods[5] = new Food(6, 7);
        foods[6] = new Food(8, 8);
    }

    static void checkFood() {

        for (int i = 0; i < foods.length; i++) {

            if (!foods[i].eaten &&
                    pacmanA == foods[i].x &&
                    pacmanB == foods[i].y) {

                foods[i].eaten = true;
                score++;

                System.out.println("Yummy 😋!! Food eaten. Score: " + score);
            }
        }
    }
}