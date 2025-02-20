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

        Set<Integer> visited = new HashSet<>();

        List<int[]> pair = new ArrayList<>();
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

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 3; i++) {
                int[] newInt = new int[3];
                int[] pairInt = pair.get(i);

                int first = pairInt[0];
                int second = pairInt[1];
                int third = pairInt[2];

                int X = 0, Y = 0;
                if (cur[first] - cur[second] < 0) {
                    X = cur[first];
                    Y = cur[second];
                    Y -= X;
                    X += X;
                    newInt[first] = X;
                    newInt[second] = Y;
                } else if (cur[first] - cur[second] > 0) {
                    X = cur[second];
                    Y = cur[first];
                    Y -= X;
                    X += X;
                    newInt[first] = X;
                    newInt[second] = Y;
                } else {
                    continue;
                }
                newInt[third] = cur[third];

                if (newInt[0] == newInt[1] && newInt[1] == newInt[2]) {
                    res = 1;
                    break;
                }

                int newValue = newInt[0] * 1000 + newInt[1] * 100 + newInt[2];
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
