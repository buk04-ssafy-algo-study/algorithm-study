import java.util.*;

public class PGS_석유시추 {
    static int[][] oil;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int m, n;

    public int solution(int[][] land) {
        oil = land;
        m = land.length;
        n = land[0].length;
        visited = new boolean[m][n];

        Map<Integer, Integer> groupToOil = new HashMap<>(); // 그룹 번호에 따른 석유량
        List<Set<Integer>> colToGroupSet = new ArrayList<>(); // 열에 포함된 그룹 번호

        for (int j = 0; j < n; j++) {
            colToGroupSet.add(new HashSet<>());
        }

        int groupId = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (oil[i][j] == 1 && !visited[i][j]) {
                    int count = bfs(i, j, groupId, colToGroupSet); // bfs로 석유량 확인
                    groupToOil.put(groupId, count); // 그룹 아이디에 따른 석유량
                    groupId++;
                }
            }
        }

        int max = 0;
        for (int j = 0; j < n; j++) {
            int totalOil = 0;
            for (int id : colToGroupSet.get(j)) { // 그룹 아이디 가지고 석유량 총합 구하기
                totalOil += groupToOil.get(id);
            }
            max = Math.max(max, totalOil);
        }

        return max;
    }

    private int bfs(int x, int y, int groupId, List<Set<Integer>> colToGroupSet) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int count = 1; // 석유량
        colToGroupSet.get(y).add(groupId); // 열에 그룹 아이디 추가

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (oil[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        count++;
                        colToGroupSet.get(ny).add(groupId); // 열에서 새로운 그룹 아이디면 추가
                    }
                }
            }
        }

        return count;
    }
}
