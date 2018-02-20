package org.sb.to.learn.pattern_matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    referenceWork();
  }

  static void referenceWork() {

    // character classes

    // . Dot, any character (may or may not match line terminators, read on)
    // \d A digit: [0-9]
    // \D A non-digit: [^0-9]
    // \s A whitespace character: [ \t\n\x0B\f\r]
    // \S A non-whitespace character: [^\s]
    // \w A word character: [a-zA-Z_0-9]
    // \W A non-word character: [^\w]

    // quantifier

    // * Match 0 or more times
    // + Match 1 or more times
    // ? Match 1 or 0 times
    // {n} Match exactly n times
    // {n,} Match at least n times
    // {n,m} Match at least n but not more than m times

    // meta character

    // \ Escape the next meta-character (it becomes a normal/literal character)
    // ^ Match the beginning of the line
    // . Match any character (except newline)
    // $ Match the end of the line (or before newline at the end)
    // | Alternation (‘or’ statement)
    // () Grouping
    // [] Custom character class

    List<String> input = new ArrayList<String>();
    input.add("123.568.989.55");
    input.add("185.66698.555.44");
    input.add("455889.555.66.55.66");
    input.add("the.tiger[blooper].got.lunch[chinese].done");

    // find the string that has 3 dots
    String pattern1 = "^(\\d+).(\\d+).(\\d+).(\\d+)";

    input.listIterator().forEachRemaining(s -> {
      if (s.matches(pattern1))
        System.out.println(s);
    });

    // explanation
    /*
     * ^ - signifies start of a string () - signifies groups \\d - signifies numeric values + - signifies any number of occurrences for numeric value
     */

    // find the string that has 5 numbers in second segment
    String pattern2 = "^(\\d+).(\\d{5}).(\\d+).(\\d+)";

    input.listIterator().forEachRemaining(s -> {
      if (s.matches(pattern2))
        System.out.println(s);
    });

    // explanation
    /*
     * same as before, in addition (\\d{5}) - second group has {5} which signifies that there should be 5 numbers in the group
     */

    // find the string that has two occurrences of []
    String pattern3 = "^.";

    input.listIterator().forEachRemaining(s -> {
      if (s.matches(pattern3))
        System.out.println(s);
    });

    String introspected = "compute-node[abc].host.agent-credential[efg].key-pair.id";
    String expected = "compute-node[].host.agent-credential[].key-pair.id";

    // split
    String[] arr = introspected.split("\\.");
    List<String> result = new ArrayList<>();

    Arrays.asList(introspected.split("\\.")).iterator().forEachRemaining(s -> {
      if (s.indexOf("[") != -1 && s.indexOf("]") != -1) {
        result.add(s.substring(0, s.indexOf("[") + 1) + "]");
      } else {
        result.add(s);
      }
    });

    // join the elements of the list by dots
    String outcome = result.stream().map(n -> n.toString()).collect(Collectors.joining("."));
    if (outcome.equals(expected)){
      System.out.println("they are same");
    }
    

  }

}
