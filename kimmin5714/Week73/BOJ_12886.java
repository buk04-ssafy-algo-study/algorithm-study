import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Set<Integer> visited = new HashSet<>(); // 방문체크를 위한 Set

        List<int[]> pair = new ArrayList<>(); // 두 수 뽑는 경우의 수
        pair.add(new int[]{0, 1, 2});
        pair.add(new int[]{1, 2, 0});
        pair.add(new int[]{0, 2, 1});

        Queue<int[]> q = new ArrayDeque<>();
        int[] arr = new int[]{A, B, C};
        q.offer(arr);

        visited.add(1000 * A + 100 * B + C);

        int res = 0;
        if (A == B && B == C) {
            System.out.print(1);
            return;
        }

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 3; i++) { // 두 수 뽑는 경우 모두 수행
                int[] newInt = new int[3];
                int[] pairInt = pair.get(i);

                int first = pairInt[0];
                int second = pairInt[1];
                int third = pairInt[2];

                if (cur[first] == cur[second]) continue; // 두 수가 같으면 처리x
                else {
                    int X = Math.min(cur[first], cur[second]);  // 더 작은 수
                    int Y = Math.max(cur[first], cur[second]) - X; // 더 큰 수
                    newInt[first] = X + X;
                    newInt[second] = Y;
                }
                newInt[third] = cur[third]; // 두 수 제외한 나머지 입력

                if (newInt[0] == newInt[1] && newInt[1] == newInt[2]) { // 세 수가 모두 같아지면 종료
                    res = 1;
                    break;
                }

                int newValue = newInt[0] * 1000 + newInt[1] * 100 + newInt[2]; // 방문체크
                if (visited.contains(newValue))
                    continue;

                visited.add(newValue);
                q.offer(newInt);
            }
            if (res == 1) break;
        }
        System.out.print(res);
    }
}
