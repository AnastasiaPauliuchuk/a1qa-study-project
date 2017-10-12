package com.anp.calc;

/**
 * Created by KAPPA on 10/12/2017.
 */
public class CalcData {

    private Double value1;
    private Double value2;
    private String calcOperator;

    public CalcData(Double value1, Double value2, String calcOperator) {
        this.value1 = value1;
        this.value2 = value2;
        this.calcOperator = calcOperator;
    }

    public Double getValue1() {
        return value1;
    }

    public Double getValue2() {
        return value2;
    }

    public String getCalcOperator() {
        return calcOperator;
    }
}
