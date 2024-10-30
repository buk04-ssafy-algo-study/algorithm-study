package SSAFY.study.algo.week50s.week59;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14247_나무자르기 {

    static class Tree implements Comparable<Tree> {
        int initHeight;
        int growth;

        public Tree(int initHeight, int growth) {
            this.initHeight = initHeight;
            this.growth = growth;
        }

        @Override
        public int compareTo(Tree t) {  // 자라는 길이 기준 오름차순 정렬
            return Integer.compare(this.growth, t.growth);
        }
    }

    static int n;

    public static void main(String[] args) throws IOException {
        PriorityQueue<Tree> pq = init();
        calcMaxGain(pq);
    }

    static PriorityQueue<Tree> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());   // 초반 나무 길이
        StringTokenizer st2 = new StringTokenizer(br.readLine());   // 성장 길이
        PriorityQueue<Tree> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) pq.offer(new Tree(Integer.parseInt(st1.nextToken()), Integer.parseInt(st2.nextToken())));

        return pq;
    }

    static void calcMaxGain(PriorityQueue<Tree> pq) {
        long gain = 0;  // 최대 10만 + 11만 + 12만 + ... + 10만 + 1만*10만 만큼이 될 수 있으므로 long으로 타입 지정
        Tree now;
        for (long i = 0; i < n; i++) {    // 첫날부터 성장세가 작은 순으로 나무를 밴다
            now = pq.poll();
            gain += (long) now.initHeight + (long) now.growth * i;  // 나무의 필드들이 int라 long으로 타입캐스팅
        }
        System.out.println(gain);
    }

}
