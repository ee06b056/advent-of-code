package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockingData {
    public static void main(String[] args) {
        List<String> commondList = new ArrayList<>();
        try {
            File f = new File("day14/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                commondList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Pattern p = Pattern.compile("mask = (.+)");
        String s = "mask = 100X000010011011110001X111X0X11111X0";
        Matcher matcher = p.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.print(matcher.group(1));
        }
        Pattern p2 = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
        String s2 = "mem[21984] = 924398411";
        Matcher matcher2 = p2.matcher(s2);
        if (matcher2.find()) {
            System.out.println(matcher2.groupCount());
            System.out.println(matcher2.group(1));
            System.out.println(matcher2.group(2));
        }
    }
}
