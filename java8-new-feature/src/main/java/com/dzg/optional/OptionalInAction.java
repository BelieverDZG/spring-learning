package com.dzg.optional;

import java.util.Optional;

public class OptionalInAction {

    public static void main(String[] args) {

        /*String insuranceNameByOptional = getInsuranceNameByOptional(new Person());
        System.out.println(insuranceNameByOptional);*/

        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }

    public static String getInsuranceNameByOptional(Person person){

//        Optional<Optional<Car>> optionalOptionalCar = Optional.ofNullable(person).map(Person::getCar);
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Empty Value");
    }
}
