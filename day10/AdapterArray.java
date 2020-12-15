package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class AdapterArray {
    static int solution1 (List<Integer> adapters) {
        adapters.sort((a, b) -> a.compareTo(b));
        int oneDiff = 0, thressDiff = 1;
        for (int i = 1; i < adapters.size(); i++) {
            int a = adapters.get(i - 1), b = adapters.get(i);
            if (b - a == 1) oneDiff++;
            if (b - a == 3) thressDiff++;
        }
        if (adapters.get(0) - 0 == 1) oneDiff++;
        if (adapters.get(0) - 0 == 3) thressDiff++; 
        return oneDiff * thressDiff;
    }
    static long solution2 (List<Integer> adapters) {
        Map<Integer, Long> ways = new HashMap<>();
        ways.put(0, 1L);
        adapters.sort((a, b) -> a.compareTo(b));
        long res = 0L;
        for (int a : adapters) {
            res = ways.getOrDefault(a - 3, 0L) + ways.getOrDefault(a - 2, 0L) + ways.getOrDefault(a - 1, 0L);
            ways.put(a, res);
        }
        return res;
    }
    public static void main(String[] args) {
        List<Integer> adapters = new ArrayList<>();
        try {
            File f = new File("day10/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                adapters.add(Integer.valueOf(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(adapters));
        System.out.println(solution2(adapters));
    }
}
