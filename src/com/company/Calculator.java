package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calculator {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String str = getStr();
        String result = calc(str);
        System.out.println("Результат операции: " + result);
    }

    public static String getStr(){
        System.out.println("Введите строку для вычисления:");
        String str;
        if(scanner.hasNext()){
            str = scanner.nextLine();
        } else {
            System.out.println("Вы допустили ошибку при вводе. Попробуйте еще раз.");
            scanner.nextLine();
            str = getStr();
        }
        return str;
    }

    public static String calc(String str){
        String result;

        int index1 = str.indexOf('+'); // операция сложения
        int index2 = str.indexOf('-'); // операция вычитания
        int index3 = str.indexOf('*'); // операция умножения
        int index4 = str.indexOf('/'); // операция деления

        char operation = index1 > 0 ? '+' : (index2 > 0 ? '-' : (index3 > 0 ? '*' : (index4 > 0 ? '/' : 'x') ) );
        int i = index1 > 0 ? index1 : (index2 > 0 ? index2 : (index3 > 0 ? index3 : (index4 > 0 ? index4 : -1) ) );

        String strnum1 = str.substring(0, i).trim();
        String strnum2 = str.substring(i + 1).trim();

        String[] aNumbers = new String[] {"1","2","3","4","5","6","7","8","9","10"};
        String[] rNumbers = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};

        boolean num1isArabic = Arrays.asList(aNumbers).contains(strnum1);
        boolean num2isArabic = Arrays.asList(aNumbers).contains(strnum2);
        boolean num1isRoman = Arrays.asList(rNumbers).contains(strnum1);
        boolean num2isRoman = Arrays.asList(rNumbers).contains(strnum2);

        boolean isArabic = num1isArabic && num2isArabic;
        boolean isRoman = num1isRoman && num2isRoman;

        int num1 = 0, num2 = 0;
        if (isArabic || isRoman) {
            if (isArabic) {
                num1 = Integer.parseInt(strnum1);
                num2 = Integer.parseInt(strnum2);
            }
            if (isRoman) {
                num1 = Arrays.asList(rNumbers).indexOf(strnum1) + 1;
                num2 = Arrays.asList(rNumbers).indexOf(strnum2) + 1;
            }
        } else {
            System.out.println("Допустимы арабские или римские числа от 1 до 10. Повторите ввод");
            result = calc(getStr());
            return result;
        }

        int res = 0;
        switch (operation){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = calc(getStr());
        }

        result = ((Integer)res).toString();
        if (isRoman && res > 0) {
            result = NumeralsConversion.arabicToRoman(res);
        }

        return result;
    }
}

class NumeralsConversion {
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " не входит в диапазон чисел от 1 до 100");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}

