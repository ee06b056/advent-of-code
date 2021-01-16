package day15;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RambunctiousRecitation {
    static void updateNumberMap (long number, long turn, Map<Long, Deque<Long>> numberMap) {
        numberMap.putIfAbsent(number, new LinkedList<>());
        Deque<Long> dq = numberMap.get(number);
        if (dq.size() < 2) {
            dq.addLast(turn);
        } else {
            dq.removeFirst();
            dq.addLast(turn);
        }
    }

    static long solution2 (int[] input) {
        long turn = 1;
        long previousN = -1;
        Map<Long, Deque<Long>> numberMap = new HashMap<>();
        while (turn <= 30000000) {
            if (turn <= input.length) {
                previousN = input[(int) turn - 1];
                updateNumberMap(previousN, turn, numberMap);
            } else if (numberMap.get(previousN).size() == 2) {
                Deque<Long> dq = numberMap.get(previousN);
                previousN = dq.peekLast() - dq.peekFirst();
                updateNumberMap(previousN, turn, numberMap);
            } else {
                previousN = 0;
                updateNumberMap(previousN, turn, numberMap);
            }
            turn++;
        }
        return previousN;
    }
    static long solution1 (int[] input) {
        long turn = 1;
        long previousN = -1;
        Map<Long, Deque<Long>> numberMap = new HashMap<>();
        while (turn <= 2020) {
            if (turn <= input.length) {
                previousN = input[(int) turn - 1];
                updateNumberMap(previousN, turn, numberMap);
            } else if (numberMap.get(previousN).size() == 2) {
                Deque<Long> dq = numberMap.get(previousN);
                previousN = dq.peekLast() - dq.peekFirst();
                updateNumberMap(previousN, turn, numberMap);
            } else {
                previousN = 0;
                updateNumberMap(previousN, turn, numberMap);
            }
            turn++;
        }
        return previousN;
    }
    public static void main(String[] args) {
        int[] input = new int[]{9, 12, 1, 4, 17, 0, 18};
        System.out.println(solution1(input));
        System.out.println(solution2(input));
    }
}
