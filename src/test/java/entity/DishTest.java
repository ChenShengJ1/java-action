package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class DishTest {

    public static List<Dish> menu;

    @BeforeAll
    public static void setUp() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
    }

    @Test
    void example0() {
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    void example1() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    void example2() {
        List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    void matchTest() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("The menu is low calories");
        }

        if (!menu.stream().noneMatch(d -> d.getCalories() < 1000)) {
            System.out.println("The menu is low calories");
        }
    }

    @Test
    void findTest() {
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
    }

    @Test
    void reduceTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        sum = numbers.stream()
                        .reduce(0, Integer::sum);
        Optional<Integer> max = numbers.stream()
                        .reduce(Integer::max);
        System.out.println(sum);
    }

}