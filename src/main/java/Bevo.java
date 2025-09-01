import java.util.Scanner;

public class Bevo {
    public static void main(String[] args) {
        String logo = "|||||   -----   \\     /   -----\n"
                + "|    |  |        \\   /   |     |\n"
                + "|||||   ----      \\ /    |     |\n"
                + "|    |  |          |     |     |\n"
                + "|||||   -----      |      ----- \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t_____________________________________________________");
        System.out.println("\t  Hello! I'm Bevo.");
        System.out.println("\t  What can I do for you?");
        System.out.println("\t_____________________________________________________\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("\t_____________________________________________________");
                System.out.println("\t  Bye. Hope to see you again soon!");
                System.out.println("\t_____________________________________________________");
                break;
            }

            System.out.println("\t_____________________________________________________");
            System.out.println("\t  " + input);
            System.out.println("\t_____________________________________________________\n");
        }

        scanner.close();
    }
}
