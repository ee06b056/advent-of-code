package day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandyHaversacks {

    static int solution1 (Map<String, BagRelation> bagGraph, String color) {
        Set<String> visitedColors = new HashSet<>();
        Deque<String> dq = new LinkedList<>();
        dq.push(color);
        visitedColors.add(color);
        int res = 0;
        while (dq.size() > 0) {
            String colorTmp = dq.pop();
            BagRelation br = bagGraph.get(colorTmp);
            if (br == null) {
                continue;
            }
            for (String colorCanContain : br.canBeContainedBy) {
                if (!visitedColors.contains(colorCanContain)) {
                    dq.push(colorCanContain);
                    visitedColors.add(colorCanContain);
                }
            }
            res++;
        }
        return res - 1;
    }

    static int solution2 (Map<String, BagRelation> bagGraph, String color) {
        int res = 1;
        BagRelation br = bagGraph.get(color);
        if (br.canContain.isEmpty()) {
            return res;
        }
        for (Map.Entry<String, Integer> entry : br.canContain.entrySet()) {
            int n = entry.getValue();
            String colorC = entry.getKey();
            res += n * solution2(bagGraph, colorC);
        }
        return res;
    }
    public static void main(String[] args) {
        Map<String, BagRelation> bagGraph = new HashMap<>();
        try {
            File f = new File("day07/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineTmp = line.replaceAll("[,\\.]", "").split("\\s*bags contain\\s*");
                String color = lineTmp[0];
                if (!bagGraph.containsKey(color)) {
                    bagGraph.put(color, new BagRelation(color));
                }
                BagRelation br = bagGraph.get(color);
                Pattern p = Pattern.compile("(\\d+) ([a-z]+ [a-z]+) bag(s)?");
                Matcher m = p.matcher(lineTmp[1]);
                while (m.find()) {
                    int n = Integer.valueOf(m.group(1));
                    String colorContain = m.group(2);
                    br.addCanContain(colorContain, n);
                    if (!bagGraph.containsKey(colorContain)) {
                        bagGraph.put(colorContain, new BagRelation(colorContain));
                    }
                    BagRelation brr = bagGraph.get(colorContain);
                    brr.addIsContainedBy(color);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(bagGraph, "shiny gold"));
        System.out.println(solution2(bagGraph, "shiny gold"));
    }
}

class BagRelation {
    String color;
    Map<String, Integer> canContain;
    Set<String> canBeContainedBy;
    public BagRelation (String color) {
        this.color = color;
        this.canContain = new HashMap<>();
        this.canBeContainedBy = new HashSet<>();
    }
    public void addCanContain (String color, int n) {
        this.canContain.put(color, n);
    }
    public void addIsContainedBy (String color) {
        this.canBeContainedBy.add(color);
    }
}
