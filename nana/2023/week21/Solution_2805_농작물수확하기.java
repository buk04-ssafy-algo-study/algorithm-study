import java.io.*;

public class Solution_2805_농작물수확하기 {

    private static int N;
    private static int[][] field;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#" + tc + " ");

            N = Integer.parseInt(br.readLine());

            field = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    field[i][j] = str.charAt(j) - '0';
                }
            }

            int mid = N / 2;
            int profit = 0;
            int jndex = mid+1;

            for (int i = 0; i < N; i++) {
                int num = 0;

                if (i > mid) {  // 중간보다 아래인 경우
                    jndex++;    // 시작점을 한칸 뒤로 미룬다
                    num = 2 * (N - i) - 1;  // 수확할 농작물 칸의 개수
                } else {
                    jndex--;    // 처음부터 중앙까지인 경우 시작점을 한칸씩 앞으로 당김
                    num = 2 * i + 1;    // 수확할 농작물 칸의 개수
                }

                for (int j = jndex; j < jndex + num; j++) {
                    // 시작점부터 수확하는 농작물 칸 수만큼 수확한다
                    profit += field[i][j];
                }
            }

            sb.append(profit).append("\n");
        }

        System.out.println(sb);
    }
}

