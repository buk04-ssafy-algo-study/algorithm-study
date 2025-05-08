import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem implements Comparable<Problem> {
    int p, l;

    public Problem(int p, int l) {
        this.p = p;
        this.l = l;
    }

    @Override
    public int compareTo(Problem o) { // 난이도 오름차순, 문제 번호 오름차순
        if (this.l == o.l) return this.p - o.p;
        return this.l - o.l;
    }
}

public class BOJ_21939 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> problemSet = new TreeSet<>(); // 정렬된 트리
        Map<Integer, Problem> problemMap = new HashMap<>(); // solved를 위한 맵

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            Problem prob = new Problem(p, l);
            problemSet.add(prob);
            problemMap.put(p, prob);
        }



        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                Problem prob = new Problem(p, l);
                problemSet.add(prob);
                problemMap.put(p, prob);
            }

            else if (cmd.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                Problem toRemove = problemMap.get(p);
                problemSet.remove(toRemove);
                problemMap.remove(p);
            }

            else if (cmd.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    System.out.println(problemSet.last().p);  // 난이도 가장 높고, 번호 가장 큰
                } else {
                    System.out.println(problemSet.first().p); // 난이도 가장 낮고, 번호 가장 작은
                }
            }
        }
    }
}