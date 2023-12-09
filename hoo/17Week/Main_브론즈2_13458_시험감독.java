package SSAFY.study.algo.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_브론즈2_13458_시험감독 {

    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] students = br.readLine().split(" ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(students[i]);
            if (n - B <= 0) {
                answer += 1;
                continue;
            }
            long bu = (n - B) / C;
            if ((n-B) % C != 0) bu += 1;
            answer += (1 + bu);
        }
        System.out.println(answer);
    }

}
