package com.myflights.utils;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
public class Permutation {
  private List<String> citiesCodeForPermutations;

  private long factorial(int n) {
    if (n > 20 || n < 0) throw new IllegalArgumentException(n + " is out of range");
    return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
  }

  private <T> List<T> permutation(long no, List<T> items) {
    return permutationHelper(no,
        new LinkedList<>(Objects.requireNonNull(items)),
        new ArrayList<>());
  }

  private <T> List<T> permutationHelper(long no, LinkedList<T> in, List<T> out) {
    if (in.isEmpty()) return out;
    long subFactorial = factorial(in.size() - 1);
    out.add(in.remove((int) (no / subFactorial)));
    return permutationHelper((int) (no % subFactorial), in, out);
  }

  public ArrayList<ArrayList<String>> getPermutationsList() {
    long permutationsSize = factorial(citiesCodeForPermutations.size());
    System.out.println(citiesCodeForPermutations + " can be combined in " + permutationsSize + " different ways:");
    ArrayList<ArrayList<String>> permutations = new ArrayList<>();
    LongStream.range(0, permutationsSize).forEachOrdered(i -> {
      permutations.add((ArrayList<String>) permutation(i, citiesCodeForPermutations));
    });
    return permutations;
  }

  public ArrayList<ArrayList<String>> getPermutationWithFirstCity(String firstCityName) {
    ArrayList<ArrayList<String>> result = getPermutationsList().stream()
        .filter(line -> line.get(0).equals(firstCityName))
        .collect(Collectors.toCollection(ArrayList::new));
    System.out.println("Size with first city: " + result.size());
    result.forEach(System.out::println);
    return result;
  }

  public ArrayList<ArrayList<String>> getPermutationWithLastCity(String lastCityName) {
    ArrayList<ArrayList<String>> result = getPermutationsList().stream()
        .filter(line -> line.get(line.size() - 1).equals(lastCityName))
        .collect(Collectors.toCollection(ArrayList::new));
    System.out.println("Size with last city: " + result.size());
    result.forEach(System.out::println);
    return result;
  }

  public ArrayList<ArrayList<String>> getPermutationWithFirstAndLastCity(String firstCityName, String lastCityName) {
    ArrayList<ArrayList<String>> result = getPermutationsList().stream()
        .filter(line -> line.get(0).equals(firstCityName) && line.get(line.size() - 1).equals(lastCityName))
        .distinct()
        .collect(Collectors.toCollection(ArrayList::new));
    System.out.println("Size with first and last cities: " + result.size());
    result.forEach(System.out::println);
    return result;
  }

  public void setCitiesCodeForPermutations(List<String> citiesCodeForPermutations) {
    this.citiesCodeForPermutations = citiesCodeForPermutations;
  }
}
