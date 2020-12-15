package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SeatingSystem {
    static boolean canOccupy (List<List<Character>> seats, int i, int j) {
        int[][] ads = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] m : ads) {
            int newI = i + m[0];
            int newJ = j + m[1];
            if (newI >= 0 && newI < seats.size() && newJ >= 0 && newJ < seats.get(0).size() 
                && seats.get(newI).get(newJ).equals('#')) {
                return false;
            }
        }
        return true;
    }
    static boolean needVacate (List<List<Character>> seats, int i, int j) {
        int occupiedSeats = 0;
        int[][] ads = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] m : ads) {
            int newI = i + m[0];
            int newJ = j + m[1];
            if (newI >= 0 && newI < seats.size() && newJ >= 0 && newJ < seats.get(0).size() 
                && seats.get(newI).get(newJ).equals('#')) {
                occupiedSeats++;
            }
        }
        return occupiedSeats >= 4;
    }
    static boolean canOccupy2 (List<List<Character>> seats, int i, int j) {
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int rowN = seats.size(), columnN = seats.get(0).size();
        for (int[] d : directions) {
            int newI = i + d[0], newJ = j + d[1];
            while (newI >= 0 && newI < rowN && newJ >= 0 && newJ < columnN) {
                if (seats.get(newI).get(newJ).equals('L')) {
                    break;
                } else if (seats.get(newI).get(newJ).equals('#')) {
                    return false;
                } else {
                    newI += d[0];
                    newJ += d[1];
                }
            }
        }
        return true;
    }
    static boolean needVacate2 (List<List<Character>> seats, int i, int j) {
        int occupiedSeats = 0;
        int rowN = seats.size(), columnN = seats.get(0).size();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] d : directions) {
            int newI = i + d[0];
            int newJ = j + d[1];
            while (newI >= 0 && newI < rowN && newJ >= 0 && newJ < columnN) {
                if (seats.get(newI).get(newJ).equals('L')) {
                    break;
                } else if (seats.get(newI).get(newJ).equals('#')) {
                    occupiedSeats++;
                    break;
                } else {
                    newI += d[0];
                    newJ += d[1];
                }
            }
        }
        return occupiedSeats >= 5;
    }
    static int solution1 (List<List<Character>> seats) {
        boolean changed = false;
        int row = seats.size(), column = seats.get(0).size();
        List<List<Character>> newSeats;
        int res = 0;
        do {
            changed = false;
            newSeats = new ArrayList<>(row);
            for (int i = 0; i < row; i++) {
                newSeats.add(new ArrayList<>(column));
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (seats.get(i).get(j).equals('L') && canOccupy(seats, i, j)) {
                        newSeats.get(i).add('#');
                        changed = true;
                    } else {
                        newSeats.get(i).add(seats.get(i).get(j));
                    }
                }
            }
            seats = newSeats;
            newSeats = new ArrayList<>(row);
            for (int i = 0; i < row; i++) {
                newSeats.add(new ArrayList<>(column));
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (seats.get(i).get(j).equals('#') && needVacate(seats, i, j)) {
                        newSeats.get(i).add('L');
                        changed = true;
                    } else {
                        newSeats.get(i).add(seats.get(i).get(j));
                    }
                }
            }
            seats = newSeats;
        } while (changed);
        for (List<Character> line : seats) {
            for (Character c : line) {
                if (c.equals('#')) {
                    res++;
                }
            }
        }
        return res;
    }
    static int solution2(List<List<Character>> seats) {
        boolean changed = false;
        int row = seats.size(), column = seats.get(0).size();
        List<List<Character>> newSeats;
        int res = 0;
        do {
            changed = false;
            newSeats = new ArrayList<>(row);
            for (int i = 0; i < row; i++) {
                newSeats.add(new ArrayList<>(column));
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (seats.get(i).get(j).equals('L') && canOccupy2(seats, i, j)) {
                        newSeats.get(i).add('#');
                        changed = true;
                    } else {
                        newSeats.get(i).add(seats.get(i).get(j));
                    }
                }
            }
            seats = newSeats;
            newSeats = new ArrayList<>(row);
            for (int i = 0; i < row; i++) {
                newSeats.add(new ArrayList<>(column));
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (seats.get(i).get(j).equals('#') && needVacate2(seats, i, j)) {
                        newSeats.get(i).add('L');
                        changed = true;
                    } else {
                        newSeats.get(i).add(seats.get(i).get(j));
                    }
                }
            }
            seats = newSeats;
        } while (changed);
        for (List<Character> line : seats) {
            for (Character c : line) {
                if (c.equals('#')) {
                    res++;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        List<List<Character>> seats = new ArrayList<>();
        try {
            File f = new File("day11/input.data");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                seats.add(scanner.nextLine().chars().mapToObj(i -> (char) i).collect(Collectors.toList()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<List<Character>> seats2 = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            seats2.add(new ArrayList<>());
            for (int j = 0; j < seats.get(0).size(); j++) {
                seats2.get(i).add(seats.get(i).get(j));
            }
        }
        System.out.println(solution1(seats));
        System.out.println(solution2(seats2));
    }
}
