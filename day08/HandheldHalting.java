package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class HandheldHalting {

    static int solution1 (List<Op> codes) {
        int res = 0, i = 0;
        Set<Integer> visitedNumber = new HashSet<>();
        while (!visitedNumber.contains(i)) {
            visitedNumber.add(i);
            Op currentOp = codes.get(i);
            switch (currentOp.oprater) {
                case "acc":
                    res += currentOp.step;
                    i++;
                    break;
                case "jmp":
                    i += currentOp.step;
                    break;
                case "nop":
                    i++;
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return res;
    }

    static int solution2 (List<Op> codes) {
        for (int i = 0; i < codes.size(); i++) {
            int res = 0;
            Op cop = codes.get(i);
            if (cop.oprater.equals("jmp") || cop.oprater.equals("nop")) {
                // System.out.println("before: " + codes.get(i).oprater);
                cop.oprater = cop.oprater.equals("jmp") ? "nop" : "jmp";
                // System.out.println("after: " + codes.get(i).oprater);
                int lineNumber = 0;
                Set<Integer> visitedNumbers = new HashSet<>();
                while (!visitedNumbers.contains(lineNumber)) {
                    if (lineNumber >= codes.size()) {
                        System.out.println("i: " + i);
                        System.out.println("lineNumber: " + lineNumber);
                        return res;
                    }
                    visitedNumbers.add(lineNumber);
                    cop = codes.get(lineNumber);
                    switch (cop.oprater) {
                        case "acc":
                            res += cop.step;
                            lineNumber++;
                            break;
                        case "jmp":
                            lineNumber += cop.step;
                            break;
                        case "nop":
                            lineNumber++;
                            break;
                    }
                }
                cop = codes.get(i);
                cop.oprater = cop.oprater.equals("jmp") ? "nop" : "jmp";
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        List<Op> codes = new ArrayList<>();
        try {
            File f = new File("day08/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                codes.add(new Op(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(codes));
        System.out.println(solution2(codes));
    }
}

class Op {
    String oprater;
    int step;
    public Op (String line) {
        String[] lineTmp = line.split(" ");
        this.oprater = lineTmp[0];
        this.step = Integer.valueOf(lineTmp[1]);
    }
}