package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BinaryBoarding {
    static int solution1 (List<String> seats) {
        int res = -1;
        for (String seat : seats) {
            String rowS = seat.substring(0, 7).replace('B', '1').replace('F', '0');
            String columnS = seat.substring(7).replace('L', '0').replace('R', '1');
            int row = Integer.parseInt(rowS, 2);
            int column = Integer.parseInt(columnS, 2);
            int seatID = row * 8 + column;
            if (res < seatID) {
                res = seatID;
            }
        }
        return res;
    }
    static int solution2 (List<String> seats) {
        List<Integer> resList = new ArrayList<>();
        HashSet<Integer> idSet = new HashSet<>();
        for (String seat : seats) {
            String rowS = seat.substring(0, 7).replace('B', '1').replace('F', '0');
            String columnS = seat.substring(7).replace('L', '0').replace('R', '1');
            int row = Integer.parseInt(rowS, 2);
            int column = Integer.parseInt(columnS, 2);
            int seatID = row * 8 + column;
            idSet.add(seatID);
        }
        for (int e : idSet) {
            if (!idSet.contains(e - 1)) {
                resList.add(e - 1);
            }
            if (! idSet.contains(e + 1)) {
                resList.add(e + 1);
            }
        }
        resList.sort((Integer a, Integer b) -> a.compareTo(b));
        return resList.get(1);
    }
    public static void main(String[] args) {
        List<String> seats = new ArrayList<>();
        try {
            File f = new File("day05/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                seats.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(seats));
        System.out.println(solution2(seats));
    }
}
