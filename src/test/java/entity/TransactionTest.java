package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

class TransactionTest {

    public static List<Transaction> transactions;

    @BeforeAll
    static void setUp() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    void test1() {
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);
    }

    @Test
    void test2() {
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    void test3() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);
    }

    @Test
    void test4() {
        transactions.stream()
                .map(Transaction::getTrader)
                .sorted(comparing(Trader::getName))
                .map(Trader::getName)
                .distinct()
                .forEach(System.out::println);

    }

    @Test
    void test5() {
        boolean bool = transactions.stream()
                .filter(t -> "Milan".equals(t.getTrader().getCity()))
                .count() > 0;
        System.out.println(bool);
    }

    @Test
    void test6() {
        System.out.println(transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum));
    }

    @Test
    void test7() {
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    @Test
    void test8() {
        transactions.stream()
                .sorted(comparing(Transaction::getValue))
                .limit(1)
                .forEach(System.out::println);
    }
}