package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RainRisk {
    static int solution1 (List<String> instructions) {
        int[][] moves = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        int dirPos = 0;
        int[] coord = {0, 0};
        for (String inst : instructions) {
            char op1 = inst.charAt(0);
            int op2 = Integer.valueOf(inst.substring(1));
            switch (op1) {
                case 'F':
                    coord[0] += op2 * moves[dirPos][0];
                    coord[1] += op2 * moves[dirPos][1];
                    break;
                case 'L':
                    dirPos = (4 + (dirPos - op2 / 90) % 4) % 4;
                    break;
                case 'R':
                    dirPos = (4 + (dirPos + op2 / 90) % 4) % 4;
                    break;
                case 'E':
                    coord[0] += op2;
                    break;
                case 'S':
                    coord[1] -= op2;
                    break;
                case 'W':
                    coord[0] -= op2;
                    break;
                case 'N':
                    coord[1] += op2;
                    break;
            }
        }
        return Math.abs(coord[0]) + Math.abs(coord[1]);
    }
    static int[] leftTurn (int[] wayPoint, int degree) {
        switch ((degree / 90) % 4) {
            case 0:
                return wayPoint;
            case 1:
                return new int[]{-1 * wayPoint[1], wayPoint[0]};
            case 2:
                return new int[]{-1 * wayPoint[0], -1 * wayPoint[1]};
            case 3:
                return new int[]{wayPoint[1], -1 * wayPoint[0]};
        }
        return wayPoint;
    }
    static int[] rightTurn (int[] wayPoint, int degree) {
        switch ((degree / 90) % 4) {
            case 0:
                return wayPoint;
            case 1:
                return new int[]{wayPoint[1], -1 * wayPoint[0]};
            case 2:
                return new int[]{-1 * wayPoint[0], -1 * wayPoint[1]};
            case 3:
                return new int[]{-1 * wayPoint[1], wayPoint[0]};
        }
        return wayPoint;
    }
    static int solution2 (List<String> instructions) {
        int[] position = {0, 0};
        int[] wayPoint = {10, 1};
        for (String inst : instructions) {
            int op2 = Integer.valueOf(inst.substring(1));
            switch (inst.charAt(0)) {
                case 'F':
                    position[0] += op2 * wayPoint[0];
                    position[1] += op2 * wayPoint[1];
                    break;
                case 'L':
                    wayPoint = leftTurn(wayPoint, op2);
                    break;
                case 'R':
                    wayPoint = rightTurn(wayPoint, op2);
                    break;
                case 'E':
                    wayPoint[0] += op2;
                    break;
                case 'S':
                    wayPoint[1] -= op2;
                    break;
                case 'W':
                    wayPoint[0] -= op2;
                    break;
                case 'N':
                    wayPoint[1] += op2;
                    break;
            }
        }
        return Math.abs(position[0]) + Math.abs(position[1]);
    }
    public static void main(String[] args) {
        List<String> instructions = new ArrayList<>();
        try {
            File f = new File("day12/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                instructions.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(instructions));
        System.out.println(solution2(instructions));
    }
}
