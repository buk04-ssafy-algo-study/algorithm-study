package SSAFY.study.algo.week28;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_게임맵최단거리 {

    public static void main(String[] args) {
        class Solution {
            class Character {
                int row;
                int col;
                int time;

                public Character(int row, int col, int time) {
                    this.row = row;
                    this.col = col;
                    this.time = time;
                }
            }

            int[] dirRow = new int[] {-1, 1, 0, 0};  // 상 하 좌 우
            int[] dirCol = new int[] {0, 0, -1, 1};
            int minTime; // 상대방 진영까지 걸리는 최소 거리

            public int solution(int[][] maps) {
                minTime = Integer.MAX_VALUE;
                bfs(maps);
                if (minTime == Integer.MAX_VALUE) minTime = -1;

                return minTime;
            }

            boolean isOuted(int row, int col, int rowSize, int colSize) {
                if ((0 <= row && row < rowSize) && (0 <= col && col < colSize)) return false;
                return true;
            }

            void bfs(int[][] maps) {
                int rowSize = maps.length;
                int colSize = maps[0].length;
                Queue<Character> queue = new ArrayDeque<>();
                boolean[][] isVisited = new boolean[rowSize][colSize];
                queue.offer(new Character(0, 0, 1));    // 출발 칸도 카운트 해야함
                isVisited[0][0] = true;

                while (!queue.isEmpty()) {
                    Character now = queue.poll();
                    if (now.row == rowSize-1 && now.col == colSize-1) {   // 상대방 진영 도착, 걸린 시간 갱신
                        if (now.time < minTime) minTime = now.time;
                        continue;
                    }

                    for (int d = 0; d < 4; d++) {
                        int nextRow = now.row + dirRow[d];
                        int nextCol = now.col + dirCol[d];
                        if (isOuted(nextRow, nextCol, rowSize, colSize) || isVisited[nextRow][nextCol] || maps[nextRow][nextCol] == 0) continue;

                        queue.offer(new Character(nextRow, nextCol, now.time+1));
                        isVisited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }

}
