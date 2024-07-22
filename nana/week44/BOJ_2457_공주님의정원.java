// https://www.acmicpc.net/problem/2457
// 공주님의 정원

import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static List<Flower> flowers;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        flowers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());

            flowers.add(new Flower(start, end));
        }

        Collections.sort(flowers);

        int now = 301;
        int count = 0;
        int max = 0;
        int idx = 0;

        while (now < 1201) {
            boolean newFlower = false;

            for (int i = idx; i < N; i++) {
                Flower flower = flowers.get(i);

                if (flower.start > now) break;

                if (max < flower.end) {
                    newFlower = true;
                    max = flower.end;
                    idx = i + 1;
                }
            }

            if (newFlower) {
                now = max;
                count++;
            } else {
                break;
            }

        }


        if (max < 1201) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }
    }

    private static class Flower implements Comparable<Flower> {
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower f) {
            if (this.start == f.start) {
                return this.end - f.end;
            }

            return this.start - f.start;
        }
    }
}
