package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TobogganTrajectory {

    static int solution1 (List<String> map) {
        int horizontalStep = 3;
        int verticalStep = 1;
        int horizontalCoor = 0;
        int repeat = map.get(0).length();
        int res = 0;
        for (int i = 0; i < map.size(); i += verticalStep) {
            String s = map.get(i);
            if (s.charAt(horizontalCoor) == '#') {
                res++;
            }
            horizontalCoor = (horizontalCoor + horizontalStep) % repeat;
        }
        return res;
    }

    static int solution2 (List<String> map, int horizontalStep, int verticalStep) {
        int horizontalCoor = 0;
        int repeat = map.get(0).length();
        int res = 0;
        for (int i = 0; i < map.size(); i += verticalStep) {
            String s = map.get(i);
            if (s.charAt(horizontalCoor) == '#') {
                res++;
            }
            horizontalCoor = (horizontalCoor + horizontalStep) % repeat;
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> map = new ArrayList<>();
        try {
            File f = new File("day03/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                map.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println(solution1(map));
        System.out.println(solution2(map, 1, 1) * solution2(map, 3, 1) * solution2(map, 5, 1) * solution2(map, 7, 1) * solution2(map, 1, 2));
    }
}
