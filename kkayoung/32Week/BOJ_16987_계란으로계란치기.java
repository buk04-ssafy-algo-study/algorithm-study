// https://www.acmicpc.net/problem/16987
import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] S,W;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 계란 개수

        S = new int[N]; // 내구도
        W = new int[N]; // 무게
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
        }

        breakEgg(0,  0);

        System.out.println(answer);
    }
    
    static void breakEgg(int idx, int brokeCnt) {
        if(idx==N) { // 가장 오른쪽 계란에 도달
            answer = Math.max(answer, brokeCnt);
            return;
        }
        if(S[idx]<=0 || brokeCnt==N-1) { // 손에 든 계란이 깨졌거나 더 깰 계란이 없음
            breakEgg(idx+1, brokeCnt);
            return;
        }

        for(int i=0;i<N;i++) {
            if(S[i] <=0) continue; // 이미 깨져있는 계란
            if(i==idx) continue; // 손에 든 계란으로 손에 든 계란 깰 수 없음
            
            // 손에 든 계란 깨기
            S[idx] = S[idx]-W[i];
            // 다음 계란 깨기
            S[i] = S[i]-W[idx];

            int brokeCntTmp = 0;
            if(S[idx]<=0) {
                brokeCntTmp++;
            }
            if(S[i]<=0) {
                brokeCntTmp++;
            }
            breakEgg(idx+1, brokeCnt+brokeCntTmp);
            S[idx] += W[i];
            S[i] += W[idx];
        }    
    }
}
