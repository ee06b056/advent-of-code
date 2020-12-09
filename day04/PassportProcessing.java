package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class PassportProcessing {

    static class Passport {
        public int byr, iyr, eyr;
        public String hgt, hcl, ecl, pid, cid;
        public Passport () {
            this.byr = this.iyr = this.eyr = -1;
            this.hgt = this.hcl = this.ecl = this.pid = this.cid = null;
        }
        public void addProperty (String pair) {
            String[] keyValue = pair.split(":");
            switch (keyValue[0]) {
                case "byr":
                    this.byr = Integer.valueOf(keyValue[1]);
                    break;
                case "iyr":
                    this.iyr = Integer.valueOf(keyValue[1]);
                    break;
                case "eyr":
                    this.eyr = Integer.valueOf(keyValue[1]);
                    break;
                case "hgt":
                    this.hgt = keyValue[1];
                    break;
                case "hcl":
                    this.hcl = keyValue[1];
                    break;
                case "ecl":
                    this.ecl = keyValue[1];
                    break;
                case "pid":
                    this.pid = keyValue[1];
                    break;
                case "cid":
                    this.cid = keyValue[1];
                    break;
                default:
                    break;
            }
        }
    }

    static int solution1 (List<Passport> pl) {
        int res = 0;
        for (Passport p : pl) {
            if (p.byr != -1 &&
                p.eyr != -1 &&
                p.iyr != -1 &&
                p.ecl != null &&
                p.hcl != null &&
                p.hgt != null &&
                p.pid != null
                ) {
                    res++;
                }
        }
        return res;
    }

    static boolean validHgt (String hgt) {
        if (hgt == null) {
            return false;
        }
        String[] hgtArray = hgt.split("(?<=\\d)(?=\\D)");
        if (hgtArray.length != 2) {
            return false;
        }
        int h = Integer.valueOf(hgtArray[0]);
        switch (hgtArray[1]) {
            case "cm":
                return h >= 150 && h <= 193;
            case "in":
                return h >= 59 && h <= 76;
            default:
                return false;
        }
    }

    static boolean validHcl (String hcl) {
        return hcl != null && hcl.matches("#[0-9a-z]{6}");
    }

    static boolean validEcl (String ecl) {
        String[] c = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        HashSet<String> colors = new HashSet<>(Arrays.asList(c));
        return ecl != null && colors.contains(ecl);
    }

    static boolean validPid (String pid) {
        return pid != null && pid.matches("\\d{9}");
    }

    static int solution2 (List<Passport> pl) {
        int res = 0;
        for (Passport p : pl) {
            if (p.byr >= 1920 &&
                p.byr <= 2002 &&
                p.eyr >= 2020 &&
                p.eyr <= 2030 &&
                p.iyr >= 2010 &&
                p.iyr <= 2020 &&
                // p.cid != null &&
                validEcl(p.ecl) &&
                validHcl(p.hcl) &&
                validHgt(p.hgt) &&
                validPid(p.pid)
                ) {
                    res++;
                }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Passport> pl = new ArrayList<>();
        Passport currentPassport = new Passport();
        try {
            File f = new File("day04/input.data");
            
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    pl.add(currentPassport);
                    currentPassport = new Passport();
                } else {
                    String[] pairs = line.split("\s+");
                    for (String p : pairs) {
                        currentPassport.addProperty(p);
                    }
                }
            }
            pl.add(currentPassport);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(pl));
        System.out.println(solution2(pl));
    }
}
