package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShuttleSearch {
    static int solution1 (int t, String[] buses) {
        int timeDiff = t;
        int targetBus = -1;
        for (String busS : buses) {
            int busN = Integer.valueOf(busS);
            if ((t/busN + 1) * busN - t < timeDiff) {
                targetBus = busN;
                timeDiff = (t/busN + 1) * busN - t;
            }
        }
        return ((t/targetBus + 1) * targetBus - t) * targetBus;
    }
    static long find (long originalN, int divisor, int offset) {
        for (int i = 1; i <= divisor; i++) {
            if ((originalN * i) % divisor == offset) {
                System.out.println("i: " + i);
                return originalN * i;
            }
        }
        return 0L;
    }
    static long solution2 (String[] buses) {
        List<int[]> busList = new ArrayList<>();
        for (int i = 0; i < buses.length; i++) {
            if (!buses[i].equals("x")) {
                int busNumber = Integer.valueOf(buses[i]);
                busList.add(new int[]{busNumber, (((busNumber - i) % busNumber) + busNumber) % busNumber});
            }
        }
        long totoalM = 1L;
        for (int[] b : busList) {
            totoalM *= b[0];
        }
        long res = 0L;
        for (int[] b : busList) {
            long resTmp = find(totoalM / b[0], b[0], b[1]);
            res += resTmp;
        }
        while (res > 0) {
            res -= totoalM;
        }
        return res + totoalM;
    }
    public static void main(String[] args) {
        int t = 0;
        String busLine = null;
        try {
            File f = new File("day13/input.data");
            Scanner scanner = new Scanner(f);
            t = Integer.valueOf(scanner.nextLine());
            busLine = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] buses = busLine.split(",(x,)*,*");
        System.out.println(solution1(t, buses));
        buses = busLine.split(",");
        System.out.println(solution2(buses));
    }
}
