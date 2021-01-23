package day16;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketTranslation {
    static class Constraint {
        private String name;
        public int a, b, c, d;
        public Constraint (String rawS) {
            Pattern p = Pattern.compile("([A-Za-z\\s]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
            Matcher m = p.matcher(rawS);
            if (m.find()) {
                this.name = m.group(1);
                this.a = Integer.parseInt(m.group(2));
                this.b = Integer.parseInt(m.group(3));
                this.c = Integer.parseInt(m.group(4));
                this.d = Integer.parseInt(m.group(5));
            } else {
                throw new RuntimeException();
            }
        }

        public boolean isValid (int i) {
            return (i >= a && i <= b) || (i >= c && i <= d);
        }
    }

    static class Ticket {
        private List<Integer> fields;
        public Ticket (String fieldsString) {
            this.fields = new ArrayList<>();
            String[] fieldsArray = fieldsString.split(",");
            for (String fs : fieldsArray) {
                this.fields.add(Integer.parseInt(fs));
            }
        }

        public List<Integer> getFields () {
            return this.fields;
        }
    }

    static long solution1 (List<Ticket> tList, List<Constraint> cList) {
        long res = 0L;
        int l = tList.size();
        for (int i = 0; i < l; i++) {
            Ticket t = tList.remove(0);
            boolean validT = true;
            for (int v : t.getFields()) {
                boolean valid = false;
                for (Constraint c : cList) {
                    if (c.isValid(v)) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    res += v;
                    validT = false;
                }
            }
            if (validT) {
                tList.add(t);
            }
        }
        return res;
    }

    static long solution2 (List<Ticket> tList, List<Constraint> cList) {
        long res = 1L;
        List<Set<Integer>> validPosList = new ArrayList<>();
        for (Constraint c : cList) {
            Set<Integer> perCValidPosSet = new HashSet<>();
            for (int i = 0; i < 20; i++) {
                perCValidPosSet.add(i);
            }
            for (int i = 0; i < 20; i++) {
                for (Ticket t : tList) {
                    int v = t.getFields().get(i);
                    if (!c.isValid(v)) {
                        perCValidPosSet.remove(i);
                        break;
                    }
                }
            }
            validPosList.add(perCValidPosSet);
        }
        Map<Integer, Integer> constraintToFieldpos = new HashMap<>();
        while (constraintToFieldpos.size() != 20) {
            for (int i = 0; i < 20; i++) {
                Set<Integer> set = validPosList.get(i);
                if (set.size() == 1 && !constraintToFieldpos.containsKey(i)) {
                    int v = set.iterator().next();
                    constraintToFieldpos.put(i, v);
                    for (int j = 0; j < 20; j++) {
                        if (j != i && validPosList.get(j).contains(v)) {
                            validPosList.get(j).remove(v);
                        }
                    }
                }
            }
        }
        Ticket myTicket = new Ticket("163,151,149,67,71,79,109,61,83,137,89,59,53,179,73,157,139,173,131,167");
        for (int i = 0; i < 6; i++) {
            res *= myTicket.getFields().get(constraintToFieldpos.get(i));
        }
        return res;
    }
    public static void main(String[] args) {
        List<Constraint> constraintList = new ArrayList<>();
        List<Ticket> ticketList = new ArrayList<>();
        try {
            File f1 = new File("day16/constraints.data");
            File f2 = new File("day16/nearby-ticket.data");
            Scanner scanner1 = new Scanner(f1);
            Scanner scanner2 = new Scanner(f2);
            while (scanner1.hasNextLine()) {
                constraintList.add(new Constraint(scanner1.nextLine()));
            }
            while (scanner2.hasNext()) {
                ticketList.add(new Ticket(scanner2.nextLine()));
            }
            scanner1.close();
            scanner2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(solution1(ticketList, constraintList));
        System.out.println(solution2(ticketList, constraintList));
    }
}
