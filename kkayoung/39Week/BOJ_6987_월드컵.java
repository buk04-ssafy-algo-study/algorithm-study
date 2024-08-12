// https://www.acmicpc.net/problem/6987
import java.io.*;
import java.util.*;

public class Main {

    static int[][] match = {{0, 1},{0, 2},{0, 3},{0, 4},{0, 5},{1, 2},{1, 3},{1, 4},{1, 5},{2, 3},{2, 4},{2, 5},{3, 4},{3, 5},{4, 5}};
    static int[][] matchRes;
    static boolean isAvailable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        matchRes = new int[6][3];

        for(int tc=0;tc<4;tc++) {
            int totalMatchCnt=0;
            isAvailable = false;
            st = new StringTokenizer(br.readLine());
            for(int team=0;team<6;team++) {
                matchRes[team][0] = Integer.parseInt(st.nextToken()); 
                matchRes[team][1] = Integer.parseInt(st.nextToken()); 
                matchRes[team][2] = Integer.parseInt(st.nextToken());
                if(matchRes[team][0]+matchRes[team][1]+matchRes[team][2] != 5)
                    break;
                totalMatchCnt += (matchRes[team][0]+matchRes[team][1]+matchRes[team][2]);
            }

            if(totalMatchCnt == 30) {
                play(0);
            }

            if(isAvailable) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }
    
        System.out.println(sb.toString());
    }

    static void play(int cnt) {
        if(cnt==15) {
            isAvailable = true;
            return;
        }

        int teamA = match[cnt][0];
        int teamB = match[cnt][1];

        if(matchRes[teamA][0]>0 && matchRes[teamB][2]>0) {
            matchRes[teamA][0]--;
            matchRes[teamB][2]--;
            play(cnt+1);
            matchRes[teamA][0]++;
            matchRes[teamB][2]++;
        }
        if(matchRes[teamA][1]>0 && matchRes[teamB][1]>0) {
            matchRes[teamA][1]--;
            matchRes[teamB][1]--;
            play(cnt+1);
            matchRes[teamA][1]++;
            matchRes[teamB][1]++;
        }
        if(matchRes[teamA][2]>0 && matchRes[teamB][0]>0) {
            matchRes[teamA][2]--;
            matchRes[teamB][0]--;
            play(cnt+1);
            matchRes[teamA][2]++;
            matchRes[teamB][0]++;
        }   
    }
}
