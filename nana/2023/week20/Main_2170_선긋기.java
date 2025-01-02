package study.week20;

import java.util.*;
import java.io.*;

public class Main_2170_선긋기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Line[] line = new Line[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            line[i] = new Line(start, end);
        }

        Arrays.sort(line);

        int start = line[0].start;
        int end = line[0].end;
        int ans = end - start;

        for (int i = 1; i < N; i++) {
            Line now = line[i];

            if (now.start <= end) {
                if (now.end <= end) continue;

                ans += now.end - end;
                end = now.end;
            } else {
                ans += now.end - now.start;
                end = now.end;
            }
        }

        System.out.println(ans);
    }

    static class Line implements Comparable<Line> {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line other) {
            if (this.start < other.start) {
                return -1;
            } else if (this.start == other.start) {
                if (this.end < other.end) {
                    return -1;
                }
            } else {
                return 1;
            }

            return 1;
        }
    }
}
