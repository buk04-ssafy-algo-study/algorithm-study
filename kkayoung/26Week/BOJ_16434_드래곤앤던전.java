// https://www.acmicpc.net/problem/16434
// 용사가 얻는 피해량의 합을 계산
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // # of room
        long H_atk = Long.parseLong(st.nextToken()); // # initial H_atk

        long curHP = 0;
        long maxHP = 0;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()); // attack
            int h = Integer.parseInt(st.nextToken()); // heal

            if(t==1) { // monster
                long cnt = h/H_atk; // 몬스터에게 공격받는 횟수
                if(h%H_atk==0) cnt-=1;
                curHP += a*cnt;
                maxHP = Math.max(maxHP, curHP);

            } else { // potion
                H_atk += a; // 공격력 증가
                curHP = Math.max(curHP-h, 0);
            }
        }

        maxHP++;
        bw.write(String.valueOf(maxHP));
        bw.flush();
        bw.close();
    }
}
