package Week81;

import java.io.*;
import java.util.*;

public class 백준_21939_문제추천시스템Version1 {
	
	 static class Problem implements Comparable<Problem> {
	        int num;
	        int level;
	        int order; // 1: maxHeap 용, -1: minHeap 용

	        public Problem(int num, int level, int order) {
	            this.num = num;
	            this.level = level;
	            this.order = order;
	        }

	        @Override
	        public int compareTo(Problem o) {
	            if (this.order == 1) {
	                // 내림차순 정렬 (난이도 기준, 번호 기준)
	                if (this.level == o.level) return o.num - this.num;
	                return o.level - this.level;
	            } else {
	                // 오름차순 정렬
	                if (this.level == o.level) return this.num - o.num;
	                return this.level - o.level;
	            }
	        }
	    }

	    static int N, M;
	    static PriorityQueue<Problem> maxHeap = new PriorityQueue<>();
	    static PriorityQueue<Problem> minHeap = new PriorityQueue<>();
	    static Map<Integer, Integer> problemMap = new HashMap<>();

	    public static void main(String[] args) throws IOException {
	        init();
	    }

	    private static void init() throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringBuilder sb = new StringBuilder();

	        N = Integer.parseInt(br.readLine());

	        for (int i = 0; i < N; i++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            int P = Integer.parseInt(st.nextToken());
	            int L = Integer.parseInt(st.nextToken());
	            addProblem(P, L);
	        }

	        M = Integer.parseInt(br.readLine());

	        for (int i = 0; i < M; i++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            String command = st.nextToken();

	            if (command.equals("add")) {
	                int P = Integer.parseInt(st.nextToken());
	                int L = Integer.parseInt(st.nextToken());
	                addProblem(P, L);
	            } 
	            else if (command.equals("solved")) {
	                int P = Integer.parseInt(st.nextToken());
	                problemMap.remove(P);
	            } 
	            else if (command.equals("recommend")) {
	                
	                int x = Integer.parseInt(st.nextToken());
	                if (x == 1) {
	                    sb.append(getValidProblem(maxHeap)).append("\n");
	                } else {
	                    sb.append(getValidProblem(minHeap)).append("\n");
	                }
	            }
	        }

	        System.out.println(sb);
	    }

	    private static void addProblem(int P, int L) {
	        problemMap.put(P, L);
	        maxHeap.offer(new Problem(P, L, 1));  // 큰 문제 추천용 (난이도 높은 순)
	        minHeap.offer(new Problem(P, L, -1)); // 작은 문제 추천용 (난이도 낮은 순)
	    }

	    private static int getValidProblem(PriorityQueue<Problem> pq) {
	        
	        while (!pq.isEmpty()) {
	            Problem p = pq.peek();
	            Integer currentLevel = problemMap.get(p.num);
	            if (currentLevel != null && currentLevel == p.level) {
	                return p.num;
	            }
	            pq.poll(); // Lazy deletion
	        }
	        return -1; 
	    }
	}
