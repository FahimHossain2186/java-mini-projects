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

            if (command.equals("exit") || command.isEmpty() || command.equals("lab --help")) {
                switch (command) {
                    case "exit" -> {
                        System.out.print("Until next time");
                        for(int i = 0; i < 3; i++){
                            delay(1000);  System.out.print('.');
                        }
                        delay(1000);
                        System.exit(0);
                    }
                    case "" -> { continue; }
                    case "lab --help" -> {
                        System.out.println("The LAB use the format 'given-base' 'required-base' 'argument'");
                        System.out.println("The usual arguments are: ");
                        System.out.println("bin    binary");
                        System.out.println("dec    decimal");
                        System.out.println("oct    octal");
                        System.out.println("hex    hexadecimal");
                        System.out.println("For custom base: cus(N)  e.g. dec cus(3) 128");
                    }
                }
            }

            else {
                try {
                    String result = calculatedResult(command);
                    System.out.println("= " + result);
                } catch (java.lang.Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

            }
        }
    }

    public static int customBase(String base) {
        try {
            return Integer.parseInt(base.substring(4, base.length() - 1));
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
        if(args.length != 3) throw new IllegalArgumentException("Format: <from-base> <to-base> <number>");

        int inputBase = baseConvert(args[0]);
        int outputBase = baseConvert(args[1]);
        if (inputBase == 0) throw new IllegalArgumentException("Unknown input base: " + args[0]);
        if (outputBase == 0) throw new IllegalArgumentException("Unknown output base: " + args[1]);

        NumberConverter converter = new NumberConverter();
        return converter.convert(args[2], inputBase, outputBase);
    }
}

