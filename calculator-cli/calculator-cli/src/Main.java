import java.util.Scanner;

public class Main {

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to BaseLAB CLI.\nType 'lab --help' for help\nType 'exit' to exit.");

        while (true) {
            System.out.print("base-lab> ");
            String command = input.nextLine().strip();

            switch (command) {
                case "exit" -> {
                    System.out.print("Until next time");
                    delay(1000);
                    System.out.print('.');
                    delay(1000);
                    System.out.print('.');
                    delay(1000);
                    System.out.print('.');
                    delay(1000);
                    System.exit(0);
                }
                case "" -> {
                    continue;
                }
                case "lab --help" -> {
                    System.out.println("The LAB use the format 'given-base' 'required-base' 'argument'");
                    System.out.println("The usual arguments are: ");
                    System.out.println("bin              binary");
                    System.out.println("dec              decimal");
                    System.out.println("oct              octal");
                    System.out.println("hex              hexadecimal");

                    System.out.println("For custom base, use 'custom('base in integer')'");
                    System.out.println("For example, 'dec cus(3) 128.12'");
                }
            }

            try {
                String result = calculatedResult(command);
                System.out.println("Converted number in base " + result);
            } catch (java.lang.Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            input.close();
        }
    }

    public static int customBase(String base) {
        try {
            return  Integer.parseInt(base.substring(4, base.length() - 1));
        }
        catch(NumberFormatException e) {
            return 0;
        }
    }

    public static int baseConvert(String input) {
        return switch (input) {
            case "bin" -> 2;
            case "dec" -> 10;
            case "oct" -> 8;
            case "hex" -> 16;
            case String s when s.startsWith("cus(") && s.endsWith(")") -> customBase(s);
            default -> 0;
        };
    }
    public static String calculatedResult(String command) {

        String[]  args = command.split(" ");
        int inputBase = baseConvert(args[0]);
        int outputBase = baseConvert(args[1]);
        try {
            int input = Integer.parseInt(args[2]);
        }
        catch(NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }



        return "result_here";
    }
}

