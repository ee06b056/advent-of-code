package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EncodingError {
    static boolean isSumOfTwo (long target, Set<Long> keySet) {
        for (long a : keySet) {
            if (target - a != a && keySet.contains(target - a)) {
                return true;
            }
        }
        return false;
    }

    static long solution1 (List<Long> numbers) {
        DequeMap dm = new DequeMap(25);
        int i = 0;
        for (;i < 25; i++) {
            dm.addNumber(numbers.get(i));
        }
        for (; i < numbers.size(); i++) {
            long nextNumber = numbers.get(i);
            if (!isSumOfTwo(nextNumber, dm.map.keySet())) {
                return nextNumber;
            } else {
                dm.addNumber(nextNumber);
            }
        }
        return -1L;
    }
    static long solution2 (List<Long> numbers, Long target) {
        int i = 0, j = 0;
        long sum = numbers.get(i);
        while (j < numbers.size()) {
            if (sum == target) {
                System.out.println("i: " + i +", j: " + j);
                long min = numbers.get(i);
                long max = numbers.get(i);
                for (int k = i + 1; k <= j; k++) {
                    min = numbers.get(k) < min ? numbers.get(k) : min;
                    max = numbers.get(k) > max ? numbers.get(k) : max;
                }
                return min + max;
            } else if (sum < target && j < numbers.size() - 1) {
                sum += numbers.get(++j);
            } else if (sum > target) {
                sum -= numbers.get(i++);
            } else {
                j++;
            }
        }
        return -1L;
    }
    public static void main(String[] args) {
        List<Long> numbers = new ArrayList<>();
        try {
            File f = new File("day09/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                numbers.add(Long.valueOf(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(numbers));
        System.out.println(solution2(numbers, 776203571L));
    }
}

class DequeMap {
    int capatity = 0, size = 0;
    Map<Long, Integer> map;
    Deque<Long> longList;
    public DequeMap (int capacity) {
        this.capatity = capacity;
        this.size = 0;
        this.map = new HashMap<>();
        this.longList = new LinkedList<>();
    }

    public void addNumber (long number) {
        this.longList.addFirst(number);
        this.map.put(number, this.map.getOrDefault(number, 0) + 1);
        this.size++;
        if (this.size > this.capatity) {
            removeLastNumber();
        }
    }

    private long removeLastNumber () {
        long lastNumber = this.longList.removeLast();
        this.size--;
        if (this.map.get(lastNumber) > 1) {
            this.map.put(lastNumber, this.map.get(lastNumber) - 1);
        } else {
            this.map.remove(lastNumber);
        }
        return 0L;
    }
}