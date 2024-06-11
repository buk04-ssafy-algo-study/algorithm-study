package SSAFY.study.algo.week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_1005_ACMCraft {

    static StringBuilder sb;

    static class BuildingRule {

        int now;
        int next;   // 이번 건물을 지음으로써 지을 수 있는 다음 건물

        public BuildingRule(int now, int next) {
            this.now = now;
            this.next = next;
        }

        @Override
        public String toString() { return this.now + " " + this.next; }
    }

    static int N, K, W; // N: 건물 개수, K: 건물 규칙 개수, W: 마지막에 지어야 하는 건물
    static int[] buildingTime;  // 각 건물을 짓는 데 필요한 시간
    static List<List<BuildingRule>> buildingRuleList;
    static int[] needCount; // 빌딩을 짓기 위해 전제되는 조건들 개수 저장하는 배열

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
            doACMCraft();
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        buildingTime = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) buildingTime[n] = Integer.parseInt(st.nextToken());
        buildingRuleList = new ArrayList<>();
        needCount = new int[N+1];
        for (int i = 0; i < N+1; i++) buildingRuleList.add(new ArrayList<>());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            buildingRuleList.get(now).add(new BuildingRule(now, next));    // 그 빌딩을 지은 후 다음에 지을 수 있는 빌딩 저장
            needCount[next] += 1;   // 다음 빌딩에 필요한 전제 조건 카운트 + 1
        }
        W = Integer.parseInt(br.readLine());
    }

    static void doACMCraft() {
        int[] cost = new int[N+1];
        Queue<BuildingRule> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if (needCount[i] == 0) {
                cost[i] = buildingTime[i]; // 전제 조건이 필요 없는 빌딩의 비용은 그냥 자기 자신을 짓는 것으로 대체
                List<BuildingRule> noNeedCountBuildingRuleList = buildingRuleList.get(i);
                for (int j = 0; j < noNeedCountBuildingRuleList.size(); j++) queue.offer(noNeedCountBuildingRuleList.get(j));
            }
        }

        while (!queue.isEmpty()) {
            BuildingRule nowBuilding = queue.poll();
            int now = nowBuilding.now;
            int next = nowBuilding.next;
            cost[next] = Math.max(cost[now] + buildingTime[next], cost[next]);  // 다음 빌딩을 짓는 데 필요한 시간의 최댓값(전제 조건들을 모두 만족해야 하므로) 갱신
            needCount[next] -= 1;
            if (needCount[next] == 0) { // 다음 빌딩을 짓는 데 필요한 전제 조건들을 모두 처리했으면 다음 빌딩도 큐에 삽입
                List<BuildingRule> noNeedBuildingRuileList = buildingRuleList.get(next);
                for (int i = 0; i < noNeedBuildingRuileList.size(); i++) queue.offer(noNeedBuildingRuileList.get(i));
            }
        }
        sb.append(cost[W]).append("\n");
    }

}
