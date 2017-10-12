package com.anp.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anastasia Pauliuchuk on 10/6/2017.
 */


/* Class com.anp.calc.Calc works with plain two-operand calculations.
Operands format : #.##, values from -10 to 10, not including.
*/

public class Calc {


    private static final String EXPRESSION_REG_EXP = "^(\\-?\\d\\.\\d{1,2})([\\+\\-\\*\\/\\:])(\\-?\\d\\.\\d{1,2})";
    private static CalcData data;
    private Double value1;
    private Double value2;
    private String calcOperator;

    public Calc() {
    }

    private void parse(String expression) throws NumberFormatException, ExpressionFormatException {

        Pattern p = Pattern.compile(EXPRESSION_REG_EXP);
        Matcher m = p.matcher(expression);

        if (m.find()) {
            data = new CalcData(Double.parseDouble(m.group(1)), Double.parseDouble(m.group(3)), m.group(2));
        } else throw new ExpressionFormatException();
    }

    private Double roundTo3Digits(Double x) {
        return Double.valueOf(Math.round(x * 1000) / 1000.00);
    }

    private Double addCalc() {
        return data.getValue1() + data.getValue2();
    }

    private Double subCalc() {
        return data.getValue1() - data.getValue2();
    }

    private Double multCalc() {
        return roundTo3Digits(data.getValue1() * data.getValue2());
    }

    private Double divCalc() throws ArithmeticException {

        if (data.getValue2() == 0) throw new ArithmeticException();
        return roundTo3Digits(data.getValue1() / data.getValue2());
    }


    public Double count(String expression) throws ExpressionFormatException {
        parse(expression);
        Double res = 0.00;
        switch (data.getCalcOperator()) {
            case "+":
                res = addCalc();
                break;
            case "-":
                res = subCalc();
                break;
            case "*":
                res = multCalc();
                break;
            case "/":
            case ":":
                res = divCalc();
                break;

        }
        return res;
    }
}