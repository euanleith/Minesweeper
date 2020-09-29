import java.util.Scanner;

public class Main {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean playing = true;

        while (playing) {

            int proportion = getDifficulty(scan);

            Board board = new Board(WIDTH, HEIGHT, proportion);
            boolean cont = true;
            int x = -1;
            int y = -1;

            while (cont) {
                board.draw();

                boolean m = false;
                String s;
                do {
                    System.out.print("move/mark/unmark: ");
                    s = scan.next();
                    System.out.print("position: ");
                    x = scan.nextInt() - 1;
                    y = scan.nextInt() - 1;
                    switch (s) {
                        case "move":
                            m = board.move(x, y);
                            break;
                        case "mark":
                            m = board.mark(x, y);
                            break;
                        case "unmark":
                            m = board.unmark(x, y);
                            break;
                    }

                    System.out.println();
                } while (!m);

                cont = !(board.isFull() || (s.equals("move") && board.isMine(x, y)));
            }

            board.draw();

            if (board.isMine(x, y)) {
                System.out.println("You lost! :(");
            } else {
                System.out.println("You won!");
            }

            System.out.print("again? ");
            playing = scan.next().equals("y");
        }
    }

    private static int getDifficulty (Scanner scan) {
        System.out.println("Difficulty:\n" +
                "[1] Easy\n" +
                "[2] Medium\n" +
                "[3] Hard\n" +
                "[4] InSaNe");

        switch (scan.nextInt()) {
            case 1:
                return 7;
            case 2:
                return 5;
            case 3:
                return 2;
            case 4:
                return 1;
        }
        return -1;
    }
}
