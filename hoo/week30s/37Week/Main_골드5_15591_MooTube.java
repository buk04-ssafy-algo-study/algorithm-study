package SSAFY.study.algo.week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_15591_MooTube {

    static class Link {
        int from;
        int to;
        int usado;

        public Link(int from, int to, int usado) {
            this.from = from;
            this.to = to;
            this.usado = usado;
        }

        @Override
        public String toString() { return this.from + " " + this.to + " " + this.usado; }
    }

    static int N, Q;
    static List<List<Link>> adjustList; // 링크로 이루어진 인접 리스트
    static List<int[]> questionList;    // 질문 리스트, 0: 기준 유사도, 1: 연결된 동영상 몇 개인지 카운트 해야 하는 동영상(bfs 출발지)


    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        adjustList = new ArrayList<>();
        questionList = new ArrayList<>();
        for (int i = 0; i < N+1; i++) adjustList.add(new ArrayList<>());
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int usado = Integer.parseInt(st.nextToken());
            adjustList.get(from).add(new Link(from, to, usado));    // 무향 그래프이므로 출발지, 도착지 모두 더해줌
            adjustList.get(to).add(new Link(to, from, usado));
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            questionList.add(new int[] {K, V});
        }
    }

    static void bfs() {
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {   // 질문들에 대해서 bfs, 정답 저장
            int startVideo = questionList.get(q)[1];
            int minimumK = questionList.get(q)[0];
            List<Link> startVideoList = adjustList.get(startVideo);  // 질문한 비디오와 연결된 비디오들
            boolean[] isVisited = new boolean[N+1];
            isVisited[startVideo] = true;   // 질문 시작한 비디오는 방문처리
            Queue<Link> queue = new ArrayDeque<>();
            for (int i = 0; i < startVideoList.size(); i++) {
                Link nextStartVideo = startVideoList.get(i);
                if (nextStartVideo.usado < minimumK) continue;    // 유사도가 기준 미만이면 건너뜀
                isVisited[nextStartVideo.to] = true;
                queue.offer(nextStartVideo);
            }

            while (!queue.isEmpty()) {
                Link now = queue.poll();
                List<Link> nextVideoList = adjustList.get(now.to);
                for (int i = 0; i < nextVideoList.size(); i++) {
                    Link next = nextVideoList.get(i);
                    int nextTo = next.to;
                    int nextUsado = next.usado;
                    if (isVisited[nextTo] || nextUsado < minimumK) continue;    // 이미 방문했거나 기준 충족 못하면 패스
                    isVisited[nextTo] = true;
                    queue.offer(next);
                }
            }

            int count = 0;
            for (int i = 0; i < N+1; i++) {
                if (i != startVideo && isVisited[i]) count++;
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

}
