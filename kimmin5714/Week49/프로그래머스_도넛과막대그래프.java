import java.util.*;
 public class 프로그래머스_도넛과막대그래프 {
    public int[] solution(int[][] edges) {
        int[] answer = {};

        // 정점마다 진출 차수, 진입 차수 저장
        Map<Integer, int[]> nodes = new HashMap<>();
        for(int i=0;i<edges.length;i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            // 최초 저장 시
            if(!nodes.containsKey(from))
                nodes.put(from, new int[]{0,0});
            if(!nodes.containsKey(to))
                nodes.put(to, new int[]{0,0});

            nodes.get(from)[0]++;
            nodes.get(to)[1]++;
        }

        int newNode = -1;
        int doughnut = 0;
        int stick = 0;
        int eight = 0;
        // 노드마다 그래프 판단
        for(int key : nodes.keySet()){
            int[] count = nodes.get(key);
            int out = count[0];
            int in = count[1];

            // 새로운 노드는 진출 차수 2 이상, 진입 차수 0
            if(in==0 && out>=2) newNode = key;
            // 막대 그래프의 마지막 정점은 진출 차수 0, 진입 차수 0 이상
            else if(out==0 && in>=0) stick++;
            // 8자 그래프의 가운데 정점은 진출 차수 2 이상, 진입 차수 2 이상
            else if(out>=2 && in>=2) eight++;
        }

        // 전체 그래프 수 = 새로운 노드의 진출 차수
        int graphCnt = nodes.get(newNode)[0];
        // 도넛 그래프는 전체 그래프 수에서 막대,8자 그래프 뺀 것
        doughnut = graphCnt-stick-eight;
        answer = new int[]{newNode, doughnut, stick, eight};

        return answer;
    }
}