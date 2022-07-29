package org.example.converter;


public class WordToNum {
    //int result = Integer.parseInt(number);
    public int convertToInt(String number) {
        if(containSymbols(number) || containInteger(number)) {
            throw new NumberFormatException("Bad string!");
        }

        int size = number.length();
        int iter = isNegative(number.charAt(0));
        long result = converter(number, size, iter, 0);

        if(result > Integer.MAX_VALUE) {
            throw new ArithmeticException("Overflow occurred");
        }

        return (int) (iter == 0 ? result : -result);
    }

    public double convertToDouble(String number) {
        if(containSymbols(number) || containDouble(number)) {
            throw new NumberFormatException("Bad string!");
        }

        int size = number.length();
        int iter = isNegative(number.charAt(0));
        int pointID = findPoint(number);

        long wholePart = converter(number, pointID, iter, 0);
        long fractionalPart = converter(number, size, ++pointID, pointID);
        double intSize = sizeOfInt(fractionalPart);

        return iter == 0 ? wholePart + (fractionalPart * intSize) : -(wholePart + (fractionalPart * intSize));
    }

    private int isNegative(char symbol) {
        return symbol == '-' ? 1 : 0;
    }

    private boolean containSymbols(String inputNumber) {
        return inputNumber.isEmpty() || inputNumber.isBlank();
    }

    private boolean containInteger(String inputNumber) {
        boolean err = false;
        int iter = 0;
        if(inputNumber.charAt(0) == '-') {
            iter = 1;
        }
        for(int i = iter; i < inputNumber.length(); i++) {
            char tmp = inputNumber.charAt(i);
            if(tmp < 48 || tmp > 57) {
                err = true;
                break;
            }
        }
        return err;
    }

    private boolean containDouble(String inputNumber) {
        boolean err = true;
        int iter = 0;
        if(inputNumber.charAt(0) == '-') {
            iter = 1;
        }
        for(int i = iter; i < inputNumber.length(); i++) {
            char tmp = inputNumber.charAt(i);
            if (tmp == 46) {
                err = false;
                break;
            }
            if (tmp < 48 || tmp > 57) {
                break;
            }
        }
        return err;
    }

    private long converter(String inputNumber, int size, int startIter, int point) {
        long value = 0;
        for(int i = startIter; i < size; i++) {
            value += (inputNumber.charAt(i) - '0') * Math.pow(10, (size - point) - (i - point) - 1);
        }
        return value;
    }

    private int findPoint(String inputNumber) {
        int pointID = 0;
        for(int i = 0; i < inputNumber.length(); i++) {
            if(inputNumber.charAt(i) == '.') {
                pointID = i;
                break;
            }
        }
        return pointID;
    }

    private double sizeOfInt(long fractionalPart) {
        double intSize = Math.log10(fractionalPart) - 1;
        double doubleSize = 0.1;
        for (int i = 0; i < intSize; ++i) {
            doubleSize /= 10;
        }
        return doubleSize;
    }
}
