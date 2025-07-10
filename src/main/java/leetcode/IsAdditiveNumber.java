package leetcode;

import java.util.ArrayList;

/**
 *  https://leetcode.com/problems/additive-number/
 *  16/12/2023
 *
 *
 *
 * */
public class IsAdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        } else if (num.length() == 3) {
            return num.charAt(0) + num.charAt(1) - 48 == num.charAt(2);
        }

        int firstNumber = getDigitInIndex(num, 0);
        int secondNumber = getDigitInIndex(num, 1);
        int middleIndex = 1;
        int fullCurrentNumber = 0;
        int reserveIndex = -1;

        for (int i = 2; i < num.length(); i++) {
            if (num.charAt(i) == 48) {
                if (reserveIndex == -1) {
                    secondNumber *= 10;
                    continue;
                } else {
                    fullCurrentNumber *= 10;
                }
            } else {
                fullCurrentNumber = (fullCurrentNumber * 10) + getDigitInIndex(num, i);
            }

            if (firstNumber + secondNumber == fullCurrentNumber) {
                while (firstNumber > 0) {
                    reserveIndex++;
                    firstNumber /= 10;
                }
                firstNumber = secondNumber;
                secondNumber = fullCurrentNumber;

            } else if (firstNumber + secondNumber < fullCurrentNumber) {
                if (reserveIndex > 0) {
                    firstNumber += (int)Math.pow(10, middleIndex - reserveIndex) * (num.charAt(reserveIndex) - 48);
                    reserveIndex--;
                } else {

                }
            }
        }

        return false;
    }

    private int compareStrings(StringBuilder firstNumber, StringBuilder secondNumber, StringBuilder finalNumber) {
        return 0;
    }

    private int addDigitToStartOfNumber(char c, int firstNumber) {
        return Integer.valueOf(c + "" + firstNumber);
    }

    private int getDigitInIndex(String num, int index) {
        return num.charAt(index) - 48;
    }


//
//        if (num.length() < 3) {
//            return false;
//        } else if (num.length() == 3) {
//            return getDigitInIndex(num, 0) + getDigitInIndex(num, 1) == getDigitInIndex(num, 2);
//        }
//
//        int secondPreviousNumber = getDigitInIndex(num, 0);
//        int previousNumber = getDigitInIndex(num, 1);
//        int fullNumber = getDigitInIndex(num, 2);
//        int spSize = 0;
//        int pSize = 0;
//        int leadingIndex = 0;
//
//        while (leadingIndex < num.length()) {
//            if (previousNumber + secondPreviousNumber == fullNumber) {
//                secondPreviousNumber = previousNumber;
//                previousNumber = fullNumber;
//                fullNumber = 0;
//            } else if (previousNumber + secondPreviousNumber < fullNumber) {
//                if (lastSolvedIndex >= 0) {
//                    secondPreviousNumber = addDigitToBegin(num.charAt(lastSolvedIndex), secondPreviousNumber);
//                } else {
//                    int movingDigit =
//                }
//            }
//        }
//
//        return false;
//    }
//
//    public boolean isAdditiveNumber1(String num) {
//        if (num.length() < 3) {
//            return false;
//        } else if (num.length() == 3) {
//            return getDigitInIndex(num, 0) + getDigitInIndex(num, 1) == getDigitInIndex(num, 2);
//        }
//
//        int secondPreviousNumber = getDigitInIndex(num, 0);
//        int previousNumber = getDigitInIndex(num, 1);
//        int fullCurrentNumber = 0;
//        int lastClearedIndex = -1;
//
//        for (int i = 3; i < num.length(); i++) {
//            if (secondPreviousNumber + previousNumber < fullCurrentNumber) {
//                if (lastClearedIndex == -1) {
//
//                }
//            }
//
//            if (secondPreviousNumber + previousNumber == fullCurrentNumber) {
//                secondPreviousNumber = previousNumber;
//                previousNumber = fullCurrentNumber;
//                fullCurrentNumber = 0;
//            }
//
//            fullCurrentNumber = (10 * fullCurrentNumber) + getDigitInIndex(num, i);
//        }
//
//        return secondPreviousNumber + previousNumber == fullCurrentNumber;
//    }
//
//    private int getDigitInIndex(String num, int index) {
//        return num.charAt(index) - 48;
//    }
}
