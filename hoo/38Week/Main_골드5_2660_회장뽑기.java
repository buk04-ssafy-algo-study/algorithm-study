package SSAFY.study.algo.week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_2660_회장뽑기 {

    static class Finder implements Comparable<Finder> {   // 회원 별 bfs를 하기 위한 객체, 근데 depth 기준으로 정렬하게 Comparable로
        int from;
        int to;
        int depth;

        public Finder(int from, int to, int depth) {
            this.from = from;
            this.to = to;
            this.depth = depth;
        }

        @Override
        public int compareTo(Finder f) {
            return this.depth - f.depth;
        }
    }

    static class Member {
        int from;
        int to;

        public Member(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int n;   // 회원 수
    static int[] memberScore;   // 회원들의 점수 저장용 배열
    static List<List<Member>> memberList;   // 회원 인접 리스트

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i < n+1; i++) bfs(i);
        printAnswer();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        memberScore = new int[n+1];
        memberList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) memberList.add(new ArrayList<>());

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (from == -1 && to == -1) break;
            memberList.get(from).add(new Member(from, to)); // 무향 그래프이므로 출발, 도착 양쪽 다 저장해줌
            memberList.get(to).add(new Member(to, from));
        }
    }

    static void bfs(int memberNo) {
        int maxScoreOfMember = 0;  // 현재 회원의 최대 점수 저장용 변수
        boolean[] isChecked = new boolean[n+1];
        isChecked[memberNo] = true;
        PriorityQueue<Finder> pq = new PriorityQueue<>();
        List<Member> initMemberList = memberList.get(memberNo); // 출발 회원과 친구인 회원 리스트
        for (int i = 0; i < initMemberList.size(); i++) {
            Member offerInitMember = initMemberList.get(i);
            isChecked[offerInitMember.to] = true;
            pq.offer(new Finder(offerInitMember.from, offerInitMember.to, 1));  // depth가 1인 Finder로 넣어줌
        }

        while(!pq.isEmpty()) {
            Finder now = pq.poll();
            maxScoreOfMember = Math.max(maxScoreOfMember, now.depth);   // 매번 최대 깊이 갱신
            List<Member> nextMemberList = memberList.get(now.to);
            for (int i = 0; i < nextMemberList.size(); i++) {
                Member next = nextMemberList.get(i);
                if (isChecked[next.to]) continue;   // 이미 체크한 멤버는 패스
                isChecked[next.to] = true;
                pq.offer(new Finder(next.from, next.to, now.depth+1));
            }
        }

        memberScore[memberNo] = maxScoreOfMember;   // 현재 회원의 최대 점수 저장
    }

    static void printAnswer() {
        int minimumScore = Integer.MAX_VALUE;
        List<Integer> candidateList = new ArrayList<>();
        for (int i = 1; i < memberScore.length; i++) {
            if (memberScore[i] < minimumScore) {    // 최소 점수가 갱신되면 점수 갱신 및 그간 후보 리스트 싹 다 지운 후 그 시점의 후보부터 다시 넣어줌
                minimumScore = memberScore[i];
                candidateList = new ArrayList<>();
                candidateList.add(i);
            } else if (memberScore[i] == minimumScore) {    // 최소 점수랑 같으면 후보 리스트에 추가
                candidateList.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(minimumScore).append(" ").append(candidateList.size()).append("\n");
        for (int i = 0; i < candidateList.size(); i++) sb.append(candidateList.get(i)).append(" ");
        System.out.println(sb);
    }

}
