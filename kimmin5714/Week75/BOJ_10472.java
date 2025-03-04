import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Block {
    int[][] cur;
    int cnt;
    String stateKey; // 방문 처리를 위해 저장하는 값

    public Block(int[][] cur, int cnt, String stateKey) {
        this.cur = cur;
        this.cnt = cnt;
        this.stateKey = stateKey;
    }
}

public class BOJ_10472 {
    private static int[][] arr;
    private static Set<String> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int P = Integer.parseInt(br.readLine());
        for (int p = 0; p < P; p++) {
            visited = new HashSet<>();
            arr = new int[3][3];

            for (int i = 0; i < 3; i++) {
                String str = br.readLine();
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = (str.charAt(j) == '*') ? 1 : 0;
                }
            }
            if (isAllWhite(arr)) sb.append(0 + "\n"); // 초기에 모두 뒤집혀 있는 경우
            else {
                sb.append(bfs()).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int bfs() {
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        Queue<Block> q = new ArrayDeque<>();
        String initialKey = getStateKey(arr);
        q.offer(new Block(arr, 0, initialKey));
        visited.add(initialKey);

        while (!q.isEmpty()) {
            Block curBlock = q.poll();
            int[][] curArr = curBlock.cur;
            int curCnt = curBlock.cnt;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int[][] newArr = copyArray(curArr);
                    flip(newArr, i, j, delr, delc); // i,j 기준으로 4방향 뒤집기
                    String newStateKey = getStateKey(newArr); // 새로운 방문체크

                    if (visited.contains(newStateKey)) continue; // 이미 나왔던 배열은 넘어감
                    if (isAllWhite(newArr)) return curCnt + 1; // 모두 흰색이 되면 종료

                    visited.add(newStateKey);
                    q.offer(new Block(newArr, curCnt + 1, newStateKey));
                }
            }
        }
        return -1;
    }

    private static void flip(int[][] arr, int r, int c, int[] delr, int[] delc) {
        arr[r][c] ^= 1;  // 현재 위치 토글 (0이면 1, 1이면 0으로 변환)
        for (int d = 0; d < 4; d++) {
            int nr = r + delr[d];
            int nc = c + delc[d];
            if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
            arr[nr][nc] ^= 1;  // 인접 위치 토글
        }
    }

    private static boolean isAllWhite(int[][] arr) {
        for (int[] row : arr) {
            for (int col : row) {
                if (col == 1) return false;
            }
        }
        return true;
    }

    private static int[][] copyArray(int[][] src) {
        int[][] newArr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newArr[i][j] = src[i][j];
            }
        }
        return newArr;
    }

    private static String getStateKey(int[][] arr) { // 방문처리를 위한 값 생성
        StringBuilder sb = new StringBuilder();
        for (int[] row : arr) { // 배열을 연속 스트링으로 저장
            for (int cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }
}
