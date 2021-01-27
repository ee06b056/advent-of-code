package day17;

import java.io.File;
import java.util.Scanner;

public class ConwayCubes {

    static int activeOrNot (int[][][] dimension, int i, int j, int k) {
        int sum = 0;
        int[] d = new int[]{-1, 0, 1};
        int l = dimension.length, m = dimension[0].length, n = dimension[0][0].length;
        for (int di : d) {
            for (int dj: d) {
                for (int dk : d) {
                    int ni = i + di, nj = j + dj, nk = k + dk;
                    if (ni >= 0 && ni < l && nj >= 0 && nj < m && nk >= 0 && nk < n) {
                        sum += dimension[ni][nj][nk];
                    }
                }
            }
        }
        int x = 0;
        if (i >= 0 && i < l && j >= 0 && j < m && k >= 0 && k < n) {
            x = dimension[i][j][k];
        }
        sum -= x;
        if (x == 1 && (sum == 2 || sum == 3)) {
            return 1;
        } else if (x == 0 && sum ==3) {
            return 1;
        } else {
            return 0;
        }
    }

    static int[][][] enlargeD (int[][][] dimension) {
        int d = dimension.length, h = dimension[0].length, w = dimension[0][0].length;
        int[][][] newDimension = new int[d + 2][h + 2][w + 2];
        for (int i = 0; i < d + 2; i++) {
            for (int j = 0; j < h + 2; j++) {
                for (int k = 0; k < w + 2; k++) {
                    int io = i - 1, jo = j - 1, ko = k - 1;
                    newDimension[i][j][k] = activeOrNot(dimension, io, jo, ko);
                }
            }
        }
        return newDimension;
    }

    static int solution1 (int[][][] dimension) {
        for (int loop = 0; loop < 6; loop++) {
            dimension = enlargeD(dimension);
        }
        int res = 0;
        for (int[][] e1 : dimension) {
            for (int[] e2 : e1) {
                for (int e3 : e2) {
                    res += e3;
                }
            }
        }
        return res;
    }

    static int activeOrNot4D (int[][][][] dimension, int i, int j, int k, int a) {
        int sum = 0;
        int[] d = new int[]{-1, 0, 1};
        int l = dimension.length, m = dimension[0].length, n = dimension[0][0].length, o = dimension[0][0][0].length;
        for (int di : d) {
            for (int dj: d) {
                for (int dk : d) {
                    for (int da : d) {
                        int ni = i + di, nj = j + dj, nk = k + dk, na = a + da;
                        if (ni >= 0 && ni < l && nj >= 0 && nj < m && nk >= 0 && nk < n && na >= 0 && na < o) {
                            sum += dimension[ni][nj][nk][na];
                        }
                    }
                }
            }
        }
        int x = 0;
        if (i >= 0 && i < l && j >= 0 && j < m && k >= 0 && k < n && a >= 0 && a < o) {
            x = dimension[i][j][k][a];
        }
        sum -= x;
        if (x == 1 && (sum == 2 || sum == 3)) {
            return 1;
        } else if (x == 0 && sum ==3) {
            return 1;
        } else {
            return 0;
        }
    }

    static int[][][][] enlarge4D (int[][][][] dimension) {
        int d = dimension.length, h = dimension[0].length, w = dimension[0][0].length, a = dimension[0][0][0].length;
        int[][][][] newDimension = new int[d + 2][h + 2][w + 2][a + 2];
        for (int i = 0; i < d + 2; i++) {
            for (int j = 0; j < h + 2; j++) {
                for (int k = 0; k < w + 2; k++) {
                    for (int l = 0; l < a + 2; l++) {
                        int io = i - 1, jo = j - 1, ko = k - 1, lo = l -1;
                        newDimension[i][j][k][l] = activeOrNot4D(dimension, io, jo, ko, lo); 
                    }
                    
                }
            }
        }
        return newDimension;
    }

    static int solution2 (int[][][][] dimension) {
        int res = 0;
        for (int loop = 0; loop < 6; loop++) {
            dimension = enlarge4D(dimension);
        }
        for (int[][][] e1 : dimension) {
            for (int[][] e2 : e1) {
                for (int[] e3 : e2) {
                    for (int e4 : e3) {
                        res += e4;
                    }
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[][][] initD = new int[1][8][8];
        int[][][][] init4D = new int[1][1][8][8];
        try {
            File f = new File("day17/input.data");
            Scanner scanner = new Scanner(f);
            int r = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '#') {
                        initD[0][r][i] = 1;
                        init4D[0][0][r][i] = 1;
                    }
                }
                r++;
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(solution1(initD));
        System.out.println(solution2(init4D));
    }
}
