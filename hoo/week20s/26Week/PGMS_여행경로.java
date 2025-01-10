package SSAFY.study.algo.week20s.week26;

import java.util.*;

class PGMS_여행경로 {

    boolean isFinished = false; // 모든 공항에 방문했는지 체크
    String[] answer;

    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length+1];
        Arrays.sort(tickets, new Comparator<String[]>() {   // 가장 먼저 만들어진 것을 답으로 바로 쓰기 위해 알파벳 순 정렬
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].toString().contentEquals(o2[0].toString()))
                    return o1[1].toString().compareTo(o2[1].toString());
                else
                    return o1[0].toString().compareTo(o2[0].toString());
            }
        });
        for (int i = 0; i < tickets.length; i++) {
            if (isFinished) break;
            if (!tickets[i][0].equals("ICN")) continue; // 무조건 인천에서 출발하므로 아닌 경우 패스
            String[] route = new String[tickets.length + 1];    // 방문하는 경로 저장할 배열
            for (int j = 0; j < 2; j++) route[j] = tickets[i][j];   // 가지고 있는 i 번째 티켓의 출발, 도착지를 경로에 저장
            boolean[] isVisited = new boolean[tickets.length];
            isVisited[i] = true;
            dfs(tickets, isVisited, route, 2);
        }


        return answer;
    }

    public void dfs(String[][] tickets, boolean[] isVisited, String[] route, int cnt) {    // route: 방문한 공항(경로 순), cnt: 방문한 공항 수
        if (isFinished) return;
        if (cnt == tickets.length + 1) {   // 모든 공항을 다 들렸을 경우
            for (int i = 0; i < cnt; i++) answer[i] = route[i];
            isFinished = true;
            return;
        }

        String start = route[cnt-1];    // 마지막 공항을 출발지로 하는 티켓을 찾기 위해 마지막 공항 값 가져옴
        for (int i = 0; i < tickets.length; i++) {
            if (isVisited[i]) continue; // 사용한 항공권이면 건너뜀
            if (tickets[i][0].equals(start)) {  // 마지막 공항을 출발지로 하는 티켓이면
                route[cnt] = tickets[i][1];
                isVisited[i] = true;
                dfs(tickets, isVisited, route, cnt+1);
                route[cnt] = null;
                isVisited[i] = false;
            }
        }
    }
}