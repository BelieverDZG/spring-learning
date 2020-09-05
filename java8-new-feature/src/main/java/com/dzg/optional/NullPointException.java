/*
package com.dzg.optional;

*/
/**
 * 传统的获取属性值得方式容易导致NPE问题
 *
 *  例如 a.b.c.getXXX(T t)
 *      要求a b c均不能为空
 *//*

public class NullPointException {
    public static void main(String[] args) {

//        getInsuranceName(new Person());NPE
        String doubts = getInsuranceNameByDeepDoubts(new Person());
        System.out.println(doubts);
    }

    private static String getInsuranceName(Person person) {
//        return person.getCar().getInsurance().getName();
    }

    private static String getInsuranceNameByMultExit(Person person)
    {
        final String defaultValue = "UNKNOWN";

        if (null == person)
            return defaultValue;
        Car car = person.getCar();
        if (null == car)
            return defaultValue;

        Insurance insurance = car.getInsurance();
        if (null == insurance)
            return defaultValue;

        return insurance.getName();
    }

    private static String getInsuranceNameByDeepDoubts(Person person)
    {
        if (null != person) {
            Car car = person.getCar();
            if (null != car) {
                Insurance insurance = car.getInsurance();
                if (null != insurance) {
                    return insurance.getName();
                }
            }
        }

        return "UNKNOWN";
    }
}
*/
