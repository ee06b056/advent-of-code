package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PasswordPhilosophy {
    static boolean passwordValid1 (String password, int leastTime, int mostTime, char letter) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter) {
                count++;
            }
        }
        return leastTime <= count && count <= mostTime;
    }

    static int solution1 (String[] passwords) {
        int leastTime = 0, mostTime = 0;
        char letter = '\u0000';
        String pw = null;
        int res = 0;
        for (String p : passwords) {
            String[] tmp = p.split("[-: ]+");
            leastTime = Integer.parseInt(tmp[0]);
            mostTime = Integer.parseInt(tmp[1]);
            letter = tmp[2].charAt(0);
            pw = tmp[3];
            if (passwordValid1(pw, leastTime, mostTime, letter)) {
                res++;
            }
        }
        return res;
    }
    static boolean passwordValid2 (String password, int firstPosition, int secondPosition, char letter) {
        int count = 0;
        if (password.charAt(firstPosition) == letter) {
            count++;
        }
        if (password.charAt(secondPosition) == letter) {
            count++;
        }
        return count == 1;
    }
    static int solution2 (String[] passwords) {
        int firstPosition = 0, secondPosition = 0;
        char letter = '\u0000';
        String pw = null;
        int res = 0;
        for (String p : passwords) {
            String[] tmp = p.split("[-: ]+");
            firstPosition = Integer.parseInt(tmp[0]) - 1;
            secondPosition = Integer.parseInt(tmp[1]) - 1;
            letter = tmp[2].charAt(0);
            pw = tmp[3];
            if (passwordValid2(pw, firstPosition, secondPosition, letter)) {
                res++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String[] passwords = new String[1000];
        try {
            File f = new File("day02/input.data");
            f.exists();
            System.out.println(f.getPath());
            Scanner scanner = new Scanner(f);
            int i = 0;
            while (scanner.hasNext()) {
                passwords[i++] = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(solution1(passwords));
        System.out.println(solution2(passwords));
    }
}