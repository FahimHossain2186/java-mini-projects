import java.util.Scanner;

public class Main {

    public static String morse(char flag){
        return switch (flag) {
            case 'A' -> ".-";   case 'B' -> "-..."; case 'C' -> "-.-."; case 'D' -> "-..";
            case 'E' -> ".";    case 'F' -> "..-."; case 'G' -> "--.";  case 'H' -> "....";
            case 'I' -> "..";   case 'J' -> ".---"; case 'K' -> "-.-";  case 'L' -> ".-..";
            case 'M' -> "--";   case 'N' -> "-.";   case 'O' -> "---";  case 'P' -> ".--.";
            case 'Q' -> "--.-"; case 'R' -> ".-.";  case 'S' -> "...";  case 'T' -> "-";
            case 'U' -> "..-";  case 'V' -> "...-"; case 'W' -> ".--";  case 'X' -> "-..-";
            case 'Y' -> "-.--"; case 'Z' -> "--..";
            case '1' -> ".----"; case '2' -> "..---"; case '3' -> "...--"; case '4' -> "....-";
            case '5' -> "....."; case '6' -> "-...."; case '7' -> "--..."; case '8' -> "---..";
            case '9' -> "----."; case '0' -> "-----";
            default -> "";
        };
    }

    public static char text(String morse) {
        return switch (morse) {
            case ".-" -> 'A';   case "-..." -> 'B'; case "-.-." -> 'C'; case "-.." -> 'D';
            case "." -> 'E';    case "..-." -> 'F'; case "--." -> 'G';  case "...." -> 'H';
            case ".." -> 'I';   case ".---" -> 'J'; case "-.-" -> 'K';  case ".-.." -> 'L';
            case "--" -> 'M';   case "-." -> 'N';   case "---" -> 'O';  case ".--." -> 'P';
            case "--.-" -> 'Q'; case ".-." -> 'R';  case "..." -> 'S';  case "-" -> 'T';
            case "..-" -> 'U';  case "...-" -> 'V'; case ".--" -> 'W';  case "-..-" -> 'X';
            case "-.--" -> 'Y'; case "--.." -> 'Z';
            case ".----" -> '1'; case "..---" -> '2'; case "...--" -> '3'; case "....-" -> '4';
            case "....." -> '5'; case "-...." -> '6'; case "--..." -> '7'; case "---.." -> '8';
            case "----." -> '9'; case "-----" -> '0';
            default -> '?';
        };
    }

    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int option;
        String c;

        System.out.println("Morse Code Converter");
        System.out.println("1. Text to Morse Encoder");
        System.out.println("2. Morse to Text Decoder");

        while (flag) {
            try{
                System.out.print("Enter your choice: ");
                option = scanner.nextInt();
                scanner.nextLine();
                flag = false;

                if(option == 1){
                    String morseCode = "";
                    System.out.print("Enter Text to Morse Encoder: ");
                    String text = scanner.nextLine();
                    for(int i = 0; i < text.length(); i++){
                        char currentChar = text.toUpperCase().charAt(i);
                        if (currentChar == ' ') {
                            morseCode += "/ ";
                        } else {
                            c = morse(currentChar);
                            if (!c.isEmpty()) {
                                morseCode += c + " ";
                            }
                        }
                    }
                    System.out.println("MorseCode: " + morseCode.trim());
                }
                else if(option == 2){
                    String text = "";
                    System.out.print("Enter Morse to Text Decoder: ");
                    String morseCode = scanner.nextLine();
                    String[] tokens = morseCode.split(" ");
                    char m;

                    for(String token : tokens){
                        if(token.equals("/")){
                            text += " ";
                        }else if(!token.isEmpty()){
                            m = text(token);
                            text += m;
                        }
                    }
                    System.out.println("Text: " + text);
                }
                else{
                    throw new Exception("Invalid Option");
                }
            }
            catch(Exception e){
                System.out.println("Invalid Input");
                scanner.nextLine();
                flag = true;
            }
        }
        scanner.close();
    }
}