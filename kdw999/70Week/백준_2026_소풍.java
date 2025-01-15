package Week70;

import java.io.*;
import java.util.*;

public class 백준_2026_소풍 {

	    static int K, N, F;
	    static List<List<Integer>> friends; // 친구 관계 인접 리스트
	    static List<Integer> result; // 완성된 친구 그룹

	    public static void main(String[] args) throws IOException {
	        init();
	         
	        for (int i = 1; i <= N; i++) {
	            List<Integer> group = new ArrayList<>(); // 현재 학생의 친구 그룹
	            group.add(i);
	            
	            // 1번 학생부터 그룹 생성여부 확인
	            backtrack(i, group);
	            if (!result.isEmpty()) break; // 이미 답을 찾았으면 종료
	        }
	        printResult();
	    }

	    private static void backtrack(int current, List<Integer> group) {
	        if (result.size() > 0) return; 
	        if (group.size() == K) { // 필요 인원이 충족됐다면
	            result = new ArrayList<>(group); 
	            return;
	        }
	        
	        // 다음 학생이 현재 학생과 친구인지 
	        for (int next = current + 1; next <= N; next++) {
	            if (canAddToGroup(group, next)) { // 그룹 추가 여부
	                group.add(next); // 친구면 그룹에 추가
	                backtrack(next, group); // 기존 학생과 추가된 학생 친구 찾기
	                group.remove(group.size() - 1); // 백트래킹
	            }
	        }
	    }

	    private static boolean canAddToGroup(List<Integer> group, int student) {
	        for (int member : group) {
	        	
	        	// 인접 리스트에서 해당 학생과 친구인지 확인
	            if (!friends.get(member).contains(student)) return false;
	        }
	        return true;
	    }
	    private static void init() throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        K = Integer.parseInt(st.nextToken());
	        N = Integer.parseInt(st.nextToken());
	        F = Integer.parseInt(st.nextToken());

	        friends = new ArrayList<>();
	        result = new ArrayList<>();
	        
	        for (int i = 0; i <= N; i++) friends.add(new ArrayList<>());
	        
	        for (int i = 0; i < F; i++) {
	            st = new StringTokenizer(br.readLine());
	            int a = Integer.parseInt(st.nextToken());
	            int b = Integer.parseInt(st.nextToken());
	            friends.get(a).add(b);
	            friends.get(b).add(a);
	        }
	    }

	    private static void printResult() {
	        if (result.isEmpty()) {
	            System.out.println(-1);
	        } else {
	            Collections.sort(result);
	            for (int student : result) {
	                System.out.println(student);
	            }
	        }
	    }
	}