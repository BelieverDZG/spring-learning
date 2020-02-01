package com.dzg.optional;

import java.util.Objects;
import java.util.Optional;

/**
 * Optional的构造以及常用方法演示
 */
public class OptionalUsage {

    public static void main(String[] args) {
        Optional<Insurance> optionalInsurance = Optional.<Insurance>empty();
//        Insurance insurance = optionalInsurance.get();
//        System.out.println(insurance);
        Optional<Insurance> insuranceOptional = Optional.of(new Insurance());
        /*insuranceOptional.get();

        Optional<Insurance> objectOptional = Optional.ofNullable(null);
        objectOptional.orElseGet(Insurance::new);
        objectOptional.orElse(new Insurance());
        objectOptional.orElseThrow(RuntimeException::new);

        objectOptional.orElseThrow(()->new RuntimeException("not have reference"));*/

//        Insurance insurance = insuranceOptional.filter(a -> a.getName() != null).get();
//        System.out.println(insurance);

        Optional<String> names = insuranceOptional.map(i -> i.getName());
        System.out.println(names.orElse("Empty Value"));

        boolean namesPresent = names.isPresent();
        System.out.println(namesPresent);//false
        names.ifPresent(System.out::println);//没有值，什么也不输出

        String insuranceName = getInsuranceName(null);
        System.out.println(insuranceName);

        String insuranceNameByOptional = getInsuranceNameByOptional(new Insurance());
        System.out.println(insuranceNameByOptional);
    }

    public static String getInsuranceName(Insurance insurance) {
        if (insurance == null)
            return "Empty value";
        return insurance.getName();
    }

    public static String getInsuranceNameByOptional(Insurance insurance){
        return Optional.ofNullable(insurance).map(Insurance::getName).orElseThrow(()->new RuntimeException("Empty Value"));
    }
}
