package com.dzg.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatch {
    public static void main(String[] args) {

        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        /**
         * Returns whether all elements of this stream match the provided predicate.
         *      * May not evaluate the predicate on all elements if not necessary for
         *      * determining the result.  If the stream is empty then {@code true} is
         *      * returned and the predicate is not evaluated.
         */
        boolean allMatch = stream.allMatch(integer -> integer > 10);
        System.out.println(allMatch);//false

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean anyMatch = stream.anyMatch(integer -> integer % 2 == 0);
        System.out.println(anyMatch);//true

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean noneMatch = stream.noneMatch(integer -> integer < 0);
        System.out.println(noneMatch);//true
    }
}
