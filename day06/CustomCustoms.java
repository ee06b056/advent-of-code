package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomCustoms {
    static int solution1 (List<HashSet<Character>> customs) {
        int res = 0;
        for (HashSet custom : customs) {
            res += custom.size();
        }
        return res;
    }

    static int solution2 (List<List<Set<Character>>> customs) {
        int res = 0;
        for (List<Set<Character>> custom : customs) {
            Set<Character> first = custom.get(0);
            for (int i = 1; i < custom.size(); i++) {
                first.retainAll(custom.get(i));
            }
            res += first.size();
        }
        return res;
    }
    public static void main(String[] args) {
        List<HashSet<Character>> customs = new ArrayList<>();
        try {
            HashSet<Character> custom = new HashSet<>();
            File f = new File("day06/input.data");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line == "") {
                    customs.add(custom);
                    custom = new HashSet<>();
                } else {
                    for (char c : line.toCharArray()) {
                        custom.add(c);
                    }   
                }
            }
            customs.add(custom);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(customs));
        
        List<List<Set<Character>>> customs2 = new ArrayList<>();
        try {
            List<Set<Character>> custom = new ArrayList<>();
            File f = new File("day06/input.data");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line == "") {
                    customs2.add(custom);
                    custom = new ArrayList<>();
                } else {
                    custom.add(line.chars().mapToObj(e -> (char) e).collect(Collectors.toSet()));
                }
            }
            customs2.add(custom);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution2(customs2));
    }
}
