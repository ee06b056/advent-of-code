package day15;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RambunctiousRecitation {
    static int solution1 (int[] input) {
        int turn = 1;
        int previousN = -1;
        Map<Integer, Deque> numberMap = new HashMap<>();
        while (turn <= 2020) {
            if (turn <= input.length) {
                previousN = input[turn - 1];
                Deque dq = numberMap.getOrDefault(previousN, new LinkedList<>());
                if (dq.size() < 2) {
                    dq.addLast(turn);
                }
            } else {

            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] input = new int[]{9, 12, 1, 4, 17, 0, 18};
        System.out.println(solution1(input));
    }
}
