// https://www.acmicpc.net/problem/2457
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        int[][] flowers = new int[N][2];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());
            flowers[i][0] = calDays(m1, d1);
            flowers[i][1] = calDays(m2, d2);
        }
        
        Arrays.sort(flowers, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(b[1], a[1]);
            }
        });
        
        int idx = 0;
        int sday = 60; //calDays(3, 1);
        int eday = 0;
        int LASTDAY = calDays(11, 30);
        
        while(sday<=LASTDAY) {
            boolean find = false;
            for(int i=idx;i<N;i++) {
                int s = flowers[i][0];
                int e = flowers[i][1];

                if(s>sday) break;
                if(eday<e) {
                    eday = e;
                    idx = i+1;
                    find = true;
                }
            }
            
            if(!find) break;
            sday = eday;
            answer++;
        }

        if(eday<=LASTDAY) answer = 0;
        
        System.out.println(answer);
    }

    static int calDays(int month, int date) {
        int days = 0;
        for(int m=1;m<month;m++) {
            switch(m) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days+=31;
                break;
                case 4: case 6: case 9: case 11:
                days+=30;
                break;
                default:
                days+=28;
                break;
            }
        }
        days+=date;
        return days;
    }

}
