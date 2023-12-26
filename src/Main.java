import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();
        int result = calculate(expression);
        System.out.println("Результат: " + result);
    }

    public static int calculate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

        int operand1;
        int operand2;

        try {
            operand1 = Integer.parseInt(tokens[0]);
            operand2 = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            operand1 = convertRomanToArabic(tokens[0]);
            operand2 = convertRomanToArabic(tokens[2]);
        }

        String operator = tokens[1];

        int result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }

        return result;
    }

    public static int convertRomanToArabic(String romanNumber) {
        Map<Character, Integer> romanToArabicMap = new HashMap<>();
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('C', 100);
        romanToArabicMap.put('D', 500);
        romanToArabicMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            int value = romanToArabicMap.get(romanNumber.charAt(i));

            if (value >= prevValue) {
                result += value;
            } else {
                result -= value;
            }

            prevValue = value;
        }

        return result;
    }
}

