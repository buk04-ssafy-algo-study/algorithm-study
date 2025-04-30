import java.util.*;
import java.io.*;

class Main {

    private static class Problem implements Comparable<Problem> {
        int num, level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return this.num - o.num;  // 문제 번호 오름차순
            }
            return this.level - o.level; // 난이도 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        TreeSet<Problem> list = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        // solved 명령어는 문제 번호만 나오기 때문에 난이도를 가져와야함

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            list.add(new Problem(P, L));
            map.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order) {
                case "recommend":
                    int n = Integer.parseInt(st.nextToken());

                    if (n == 1) {
                        sb.append(list.last().num).append("\n");
                    } else {
                        sb.append(list.first().num).append("\n");
                    }
                    break;
                case "add": {
                    int P = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());

                    list.add(new Problem(P, L));
                    map.put(P, L);
                    break;
                }
                case "solved": {
                    int P = Integer.parseInt(st.nextToken());
                    int L = map.get(P); // 레벨은 주어지지 않기 때문에 맵을 이용해서 찾음

                    list.remove(new Problem(P, L));
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}