package SSAFY.study.algo.week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_골드4_1863_스카이라인쉬운거 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        Stack<Integer> skyLine = new Stack<>();
        StringTokenizer st;
        int x, y;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (skyLine.isEmpty()) {
                if (y != 0) skyLine.push(y);
            } else {
                while (!skyLine.isEmpty() && skyLine.peek() > y) {
                    skyLine.pop();
                    answer += 1;
                }
                if (y != 0 && (skyLine.isEmpty() || skyLine.peek() < y)) skyLine.push(y);
            }
        }
        while (!skyLine.isEmpty()) {
            skyLine.pop();
            answer += 1;
        }
        System.out.println(answer);
    }

}
