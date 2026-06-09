public class NumberConverter{

    private final int fracPrecision;

    public NumberConverter(){
        this.fracPrecision = 6;
    }

    public String convert(String number, int fromBase, int toBase){
        String[] parts = splitNumber(number.toUpperCase());
        String whole = parts[0];
        String fraction = parts[1];

        long wholeDecimal = wholeToDecimal(whole, fromBase);
        double fractionDecimal = fractionToDecimal(fraction, fromBase);

        String baseWhole = wholeToBase(wholeDecimal, toBase);
        String baseFraction = fractionToBase(fractionDecimal, toBase);

        return output(baseWhole, baseFraction);
    }

    private String[] splitNumber(String number){
        String[] parts = number.split("\\.");

        if      (parts.length == 1)   return new String[]{number, ""};
        else if (parts.length == 2)   return new String[]{parts[0], parts[1]};
        else throw new IllegalArgumentException("Invalid number: " + number);
    }

    private long wholeToDecimal(String wholeNumber, int base){

        long out = 0;
        int power = wholeNumber.length() - 1;

        for(int i = 0; i < wholeNumber.length(); i++){
            int digit = Character.getNumericValue(wholeNumber.charAt(i));
            out += digit * (long) Math.pow(base, power);
            power--;
        }

        return out;
    }

    private double fractionToDecimal(String fractionNumber, int base){

        if(fractionNumber.isEmpty()) return 0.0;

        double out = 0;
        for(int i = 0; i < fractionNumber.length(); i++){
            int digit = Character.getNumericValue(fractionNumber.charAt(i));
            out += digit * Math.pow(base, -(i + 1));
        }

        return out;
    }

    private String wholeToBase(long wholeNumber, int base){

        if (wholeNumber == 0) return "0";

        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        while(wholeNumber > 0){
            int remainder = (int) (wholeNumber % base);
            sb.insert(0, digits.charAt(remainder));
            wholeNumber /= base;
        }

        return sb.toString();
    }

    private String fractionToBase(double fractionNumber, int base){

        if (fractionNumber == 0) return "";

        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        int steps = 0;

        while(fractionNumber != 0 && steps < fracPrecision){
            fractionNumber *= base;
            int intPart = (int) fractionNumber;
            sb.append(digits.charAt(intPart));
            fractionNumber -= intPart;
            steps++;
        }

        return sb.toString();
    }

    private String output(String whole, String fraction){
        if (fraction.isEmpty()) return whole;
        return whole + "." + fraction;
    }
}