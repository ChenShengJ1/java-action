package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AppleTest {

    public static List<Apple> Inventory;

    @BeforeAll
    public static void setUp() {
        Inventory = Arrays.asList(new Apple("green", 80),
                new Apple("red", 80),
                new Apple("green", 160),
                new Apple("red", 160));
    }

    @Test
    void example1() {
        Inventory.sort(comparing(Apple::getWeight));
        Inventory.forEach(System.out::println);
    }

    @Test
    void example2() {
        Inventory.sort(comparing(Apple::getWeight).reversed());
        Inventory.forEach(System.out::println);
    }

    @Test
    void example3() {
        Inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor));
        Inventory.forEach(System.out::println);
    }

    @Test
    void example4() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));
    }

    @Test
    void example5() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g);
        System.out.println(h.apply(1));
    }

}