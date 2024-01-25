package SSAFY.study.algo.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_골드5_3967_골드스타 {

    static char[][] magicStar;
    static List<int[]> blankAxis;   // 알파벳 채워야하는 곳
    static boolean[] usedAlphabet;  // 1부터 12까지 쓴 알파벳 체크
    static boolean answerFinded;    // 가장 빨리 찾아낸 게 답이므로 true가 되면 dfs 안들어감

    public static void main(String[] args) throws IOException {
        init();
        dfs(0);
    }

    static void init() throws IOException { // . 과 x를 제외한 알파벳들은 우선 숫자로 저장
        magicStar = new char[5][9];
        blankAxis = new ArrayList<>();
        usedAlphabet = new boolean[12];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); j++) {
                if (String.valueOf(row.charAt(j)).equals("x")) blankAxis.add(new int[] {i, j});
                else if (!String.valueOf(row.charAt(j)).equals(".")) usedAlphabet[row.charAt(j) - 65] = true;
                magicStar[i][j] = row.charAt(j);
            }
        }
        answerFinded = false;
    }

    static boolean isTwentySix() {    // 모든 줄의 합이 26인지 체크
        if ((magicStar[0][4]-64) + (magicStar[1][3]-64) + (magicStar[2][2]-64) + (magicStar[3][1]-64) != 26) return false;
        if ((magicStar[0][4]-64) + (magicStar[1][5]-64) + (magicStar[2][6]-64) + (magicStar[3][7]-64) != 26) return false;
        if ((magicStar[3][1]-64) + (magicStar[3][3]-64) + (magicStar[3][5]-64) + (magicStar[3][7]-64) != 26) return false;
        if ((magicStar[4][4]-64) + (magicStar[3][3]-64) + (magicStar[2][2]-64) + (magicStar[1][1]-64) != 26) return false;
        if ((magicStar[4][4]-64) + (magicStar[3][5]-64) + (magicStar[2][6]-64) + (magicStar[1][7]-64) != 26) return false;

        return (magicStar[1][1]-64) + (magicStar[1][3]-64) + (magicStar[1][5]-64) + (magicStar[1][7]-64) == 26;
    }

    static void dfs(int count) {    // count: 몇 개의 알파벳을 넣었는지, drawingStar: 그려나가고 있는 매직스타
        if (answerFinded) return;
        if (count == blankAxis.size()) {
            if (isTwentySix()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) sb.append(magicStar[i][j]).append("");
                    sb.append("\n");
                }
                System.out.println(sb);
                answerFinded = true;
            }

            return;
        }

        for (int i = 0; i < usedAlphabet.length; i++) {
            if (usedAlphabet[i]) continue;  // 사용한 알파벳이면 패스
            int[] blank = blankAxis.get(count);
            magicStar[blank[0]][blank[1]] = (char) (i+65);
            usedAlphabet[i] = true;
            dfs(count + 1);
            magicStar[blank[0]][blank[1]] = '.';
            usedAlphabet[i] = false;
        }
    }

}
