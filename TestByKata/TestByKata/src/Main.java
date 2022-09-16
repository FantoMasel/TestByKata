import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
    static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static Character[] operations= {'+', '-', '*', '/'};
    static String post1= "Введите математическую операцию из 2х арабских или римских чисел c пробелами,\n"
                          +"от 1 до 10 включительно. Примеры: 1 + 4, I + IV";



    public static void main(String[] args) throws Exception {
        System.out.println(post1);
        String answerForOut = calc(scanner.nextLine());
        System.out.println(answerForOut);

    }



    public static String calc(String input) throws Exception {
        String[] strings = input.split(" ");

        if(strings.length!=3){
            throw new Exception("т.к. строка не является математической операцией или " +
                    "т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

       char[] chars = strings[1].toCharArray();

        String number1 = strings[0];
        char operation = chars[0];
        String number2 = strings[2];
        List<String> romanNumbers = Arrays.asList(roman);
        List<Character> operationsList = Arrays.asList(operations);
        List<String> number = Arrays.asList(numbers);
        if(!number.contains(number1) && !romanNumbers.contains(number1)){
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *) или числа выходят за границы от 1 до 10");
        }
        if(!number.contains(number2) && !romanNumbers.contains(number2)){
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *) или числа выходят за границы от 1 до 10");
        }

        if(chars.length!=1 || !(operationsList.contains(operation))){
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)");
        }




        if(romanNumbers.contains(number1) && romanNumbers.contains(number2)
                && operationsList.contains(operation)){
            return calcForRoman(number1, number2, operation);

        }


        if(number.contains(number1) && number.contains(number2)
                && operationsList.contains(operation)){
            return calcForNumber(number1, number2, operation);
        }
        if((number.contains(number1) && romanNumbers.contains(number2)) ||
                (romanNumbers.contains(number1) && number.contains(number2))){
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }


        return "Hop-Hey-Lalaley";
    }




    static String calcForNumber(String s1, String s2, char operation) throws Exception {
        int n1 = Integer.parseInt(s1);
        int n2 = Integer.parseInt(s2);
        int result;

        switch (operation){
            case '+':
                result = n1+n2;
                return String.valueOf(result);
            case '-':
                result = n1-n2;
                return String.valueOf(result);
            case '*':
                result = n1*n2;
                return String.valueOf(result);
            case '/':
                result = n1/n2;
                return String.valueOf(result);
        }
        return null;
    }



    static String calcForRoman(String s1, String s2, char operation) throws Exception {
        int number1 = -1;
        int number2 = -1;
        int result;
        for(int i = 1; i<11 ; i++) {
            if (roman[i].equals(s1)) {
                number1 = i;
            }
            if (roman[i].equals(s2)) {
                number2 = i;
            }
        }

        if(number1==-1 || number2 ==-1){
            throw new Exception("ВВедены числа не из промежутка от 1 до 10");
        }

        switch (operation){
            case '+':
                result = number1+number2;
                return roman[result];
            case '-':
                result = number1-number2;
                if(result<1){
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }
                return roman[result];
            case '*':
                result = number1*number2;
                return roman[result];
            case '/':
                result = number1/number2;
                if(result<1){
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }
                return roman[result];
        }
        return null;
    }
}
