package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String str = getStr();
        int result = calc(str);
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

    public static int calc(String str){
        int result;

        int index1 = str.indexOf('+'); // операция сложения
        int index2 = str.indexOf('-'); // операция вычитания
        int index3 = str.indexOf('*'); // операция умножения
        int index4 = str.indexOf('/'); // операция деления

        char operation = index1 > 0 ? '+' : (index2 > 0 ? '-' : (index3 > 0 ? '*' : (index4 > 0 ? '/' : 'x') ) );
        int i = index1 > 0 ? index1 : (index2 > 0 ? index2 : (index3 > 0 ? index3 : (index4 > 0 ? index4 : -1) ) );

        String strnum1 = str.substring(0, i).trim();
        String strnum2 = str.substring(i + 1).trim();

        String[] aNumbers = new String[]{"1","2","3","4","5","6","7","8","9","10",};
        String[] rNumbers = new String[]{"I","II","III","IV","V","VI","VII","VIII","IX","X",};

        boolean num1isArabic = Arrays.asList(aNumbers).contains(strnum1);
        boolean num2isArabic = Arrays.asList(aNumbers).contains(strnum2);
        boolean num1isRoman = Arrays.asList(rNumbers).contains(strnum1);
        boolean num2isRoman = Arrays.asList(rNumbers).contains(strnum1);

        int num1 = 0, num2 = 0;
        if ((num1isArabic & num2isArabic) | (num1isRoman & num2isRoman)) {
            if (num1isArabic & num2isArabic) {
                num1 = Integer.parseInt(strnum1);
                num2 = Integer.parseInt(strnum2);
            }
            if (num1isRoman & num2isRoman) {
                num1 = Arrays.asList(rNumbers).indexOf(strnum1) + 1;
                num2 = Arrays.asList(rNumbers).indexOf(strnum2) + 1;
            }
        } else {
            System.out.println("Допустимы арабские или римские числа от 1 до 10. Повторите ввод");
            result = calc(getStr());
            return result;
        }

        switch (operation){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = calc(getStr());
        }

        return result;
    }
}


