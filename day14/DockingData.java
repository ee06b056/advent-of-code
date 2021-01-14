package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockingData {

    static long[] generateMasks (String originalMask) {
        StringBuilder overwrite1SB = new StringBuilder();
        StringBuilder overwrite0SB = new StringBuilder();
        for (char c : originalMask.toCharArray()) {
            switch (c) {
                case '1':
                    overwrite1SB.append('1');
                    overwrite0SB.append('1');
                    break;
                case '0':
                    overwrite0SB.append('0');
                    overwrite1SB.append('0');
                    break;
                case 'X':
                    overwrite0SB.append('1');
                    overwrite1SB.append('0');
                    break;
                default:
                    break;
            }
        }
        return new long[]{Long.parseLong(overwrite0SB.toString(), 2), Long.parseLong(overwrite1SB.toString(), 2)};
    }

    static void writeToMap (Map<Long, Long> memMap, long memIndex, long originalValue, long[] masks) {
        long res = originalValue & masks[0];
        res |= masks[1];
        memMap.put(memIndex, res);
    }

    static long solution1 (List<String> commondList) {
        Pattern maskPattern = Pattern.compile("mask = (.+)");
        Pattern memPattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
        Map<Long, Long> memMap = new HashMap<>();
        long[] masks = null;
        for (String commond : commondList) {
            Matcher maskMatcher = maskPattern.matcher(commond);
            Matcher memMatcher = memPattern.matcher(commond);
            if (maskMatcher.find()) {
                masks = generateMasks(maskMatcher.group(1));
            } else if (memMatcher.find()) {
                writeToMap(memMap, Long.valueOf(memMatcher.group(1)), Long.valueOf(memMatcher.group(2)), masks);
            }
        }
        long res = 0L;
        for (long l : memMap.values()) {
            res += l;
        }
        return res;
    }

    static class Memory {
        private List<Character> mems;
        
        public Memory () {
            this.mems = new ArrayList<>();
        }

        public void add (Character c) {
            this.mems.add(c);
        }

        public List<Long> getMems () {
            List<Long> memList = new ArrayList<>();
            List<StringBuilder> sbList = new ArrayList<>();
            sbList.add(new StringBuilder());
            for (Character c : this.mems) {
                if (c.equals('X')) {
                    int currentL = sbList.size();
                    for (int i = 0; i < currentL; i++) {
                        StringBuilder newSB = new StringBuilder(sbList.get(i));
                        sbList.get(i).append('0');
                        newSB.append('1');
                        sbList.add(newSB);
                    }
                } else {
                    for (StringBuilder sb : sbList) {
                        sb.append(c);
                    }
                }
            }
            for (StringBuilder sb : sbList) {
                memList.add(Long.parseLong(sb.toString(), 2));
            }
            return memList;
        } 
    }

    static List<Long> generateMems (String mask, long initMem) {
        String initMemS = Long.toBinaryString(initMem);
        int memL = initMemS.length();
        int maskL = mask.length();
        Memory m = new Memory();
        for (int i = 0; i < maskL; i++) {
            if (i < maskL - memL) {
                m.add(mask.charAt(i));
            } else {
                int j = i - maskL + memL;
                if (mask.charAt(i) == '0') {
                    m.add(initMemS.charAt(j));
                } else {
                    m.add(mask.charAt(i));
                }
            }
        }
        return m.getMems();
    }

    static long solution2 (List<String> commondList) {
        long res = 0L;
        Pattern maskPattern = Pattern.compile("mask = (.+)");
        Pattern memPattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
        Map<Long, Long> memMap = new HashMap<>();
        String mask = null;
        for (String commond : commondList) {
            Matcher maskMatcher = maskPattern.matcher(commond);
            Matcher memMatcher = memPattern.matcher(commond);
            if (maskMatcher.find()) {
                mask = maskMatcher.group(1);
            } else if (memMatcher.find()) {
                List<Long> mems = generateMems(mask, Long.valueOf(memMatcher.group(1)));
                for (long mem : mems) {
                    memMap.put(mem, Long.valueOf(memMatcher.group(2)));
                }
            }
        }
        for (long l : memMap.values()) {
            res += l;
        }
        return res;
    }

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
        System.out.println(solution1(commondList));
        System.out.println(solution2(commondList));
    }
}
