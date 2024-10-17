import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Score implements Comparable<Score> {
    int korean;
    int english;
    int math;
    String name;

    public Score(int korean, int english, int math, String name) {
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.name = name;
    }

    @Override
    public int compareTo(Score o) {
        if (o.korean == this.korean) { // 국어 점수가 같은 경우
            if (o.english == this.english) { // 영어 점수가 같은 경우
                if (o.math == this.math) { // 수학 점수가 같은 경우
                    return this.name.compareTo(o.name); // 이름 오름차순
                }
                return o.math - this.math; // 내림차순
            }
            return this.english - o.english; // 오름차순
        }
        return o.korean - this.korean; // 내림차순
    }
}

public class BOJ_10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        // 자동 정렬되는 우선순위 큐
        PriorityQueue<Score> pq = new PriorityQueue<Score>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            Score score = new Score(korean, english, math, name);
            pq.offer(score);
        }

        // 순서대로 출력
        for (int i = 0; i < n; i++)
            sb.append(pq.poll().name + "\n");

        System.out.print(sb);
    }
}