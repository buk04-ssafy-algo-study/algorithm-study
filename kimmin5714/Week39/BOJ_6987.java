import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987 {
    static boolean isEnd = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int[][] match = new int[15][2];
        int idx = 0;
        for(int i=0;i<5;i++){
            for(int j=i+1;j<6;j++){
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }


        for(int i=0;i<4;i++){
            st = new StringTokenizer(br.readLine());
            int[][] result = new int[6][3];
            boolean possible = true;

            for(int j=0;j<6;j++){
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                result[j][0] = win;
                result[j][1] = draw;
                result[j][2] = lose;

                if(win+draw+lose != 5) {
                    possible = false;
                    break;
                }
            }

            if(possible) {
                isPossible(result, match, 0, 15);
                if(isEnd)
                    sb.append(1);
                else
                    sb.append(0);
            }
            else {
                sb.append(0);
            }
            sb.append(" ");
            isEnd = false;
        }
        System.out.println(sb);
    }

    private static void isPossible(int[][] result, int[][] match, int cur, int totalSize) {
        if(isEnd) return;

        if(cur == totalSize){
            isEnd = true;
            return;
        }

        int teamA = match[cur][0];
        int teamB = match[cur][1];

        if(result[teamA][0]>0 && result[teamB][2]>0) {
            //A승, B패
            result[teamA][0]--;
            result[teamB][2]--;
            isPossible(result, match, cur+1, totalSize);
            result[teamA][0]++;
            result[teamB][2]++;
        }
        if(result[teamA][1]>0 && result[teamB][1]>0) {
            //A무, B무
            result[teamA][1]--;
            result[teamB][1]--;
            isPossible(result, match, cur+1, totalSize);
            result[teamA][1]++;
            result[teamB][1]++;
        }
        if(result[teamA][2]>0 && result[teamB][0]>0) {
            //A패, B승
            result[teamA][2]--;
            result[teamB][0]--;
            isPossible(result, match, cur+1, totalSize);
            result[teamA][2]++;
            result[teamB][0]++;
        }
    }
}
