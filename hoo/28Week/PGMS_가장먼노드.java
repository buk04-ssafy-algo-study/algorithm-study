package SSAFY.study.algo.week28;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class PGMS_가장먼노드 {

    public static void main(String[] args) {
        class Solution {

            boolean[][] adjMatrix;   // 노드 개수 범위가 간선 개수 범위보다 작으므로 인접행렬로, int형 배열일 때 메모리 초과를 겪어서 boolean 배열로 변경
            int maxDist;

            public int solution(int n, int[][] edge) {
                adjMatrix = new boolean[n+1][n+1];  // 노드 번호가 1부터 주어지므로 크기는 n+1로
                for (int i = 0; i < edge.length; i++) {
                    adjMatrix[edge[i][0]][edge[i][1]] = true;
                    adjMatrix[edge[i][1]][edge[i][0]] = true;
                }
                maxDist = Integer.MIN_VALUE;
                int answer = bfs(n);

                return answer;
            }

            int bfs(int n) {
                int[] distArr = new int[n+1];  // 1번 노드부터 n번 노드까지의 거리(간선 개수)
                Arrays.fill(distArr, Integer.MAX_VALUE);
                distArr[1] = 0;

                Queue<int[]> queue = new ArrayDeque<>();    // 0: 노드 번호, 1: 거친 간선 개수
                queue.offer(new int[] {1, 0});

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    int nodeNum = now[0];
                    int dist = now[1];
                    for (int i = 2; i < n+1; i++) {
                        if (!adjMatrix[nodeNum][i]) continue;  // 이어져있지 않으면 건너 뜀
                        if (distArr[i] == Integer.MAX_VALUE) {  // 1부터 걸리는 거리 갱신 및 제일 긴 거리 갱신
                            int tempDist = dist+1;
                            distArr[i] = tempDist;
                            queue.offer(new int[] {i, tempDist});

                            if (distArr[i] > maxDist) maxDist = distArr[i];
                        }
                    }
                }

                int answerCount = 0;
                for (int i = 2; i < distArr.length; i++) {
                    if (distArr[i] == maxDist) answerCount += 1;
                }

                return answerCount;
            }

        }
    }

}
