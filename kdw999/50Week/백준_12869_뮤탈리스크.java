package Week50;

import java.io.*;
import java.util.*;

public class 백준_12869_뮤탈리스크 {

	static int[] scv;
    static boolean[][][] visited = new boolean[61][61][61];
    static int result;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());        
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        result = Integer.MAX_VALUE;
        
        scv = new int[3];
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        
        // scv 개수가 3보다 작으면 나머지는 0으로 채워주기
        for (int i = N; i < 3; i++) {
            scv[i] = 0;
        }
 
        findMin(scv[0], scv[1], scv[2], 0);
        System.out.println(result);
 
    }
 
    public static void findMin(int a, int b, int c, int count) {
        
    	// 정렬 및 체크했던 경우의 수를 위해 음수는 0으로 수정
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);
 
        // 모든 scv가 죽었을 경우
        if(a==0 & b==0 && c==0){
            result = Math.min(result, count);
            return;
        }
 
        // a,b,c 순서로 큰 수대로 입력
        int[] sorting = {a,b,c};
        Arrays.sort(sorting);
        a = sorting[2];
        b = sorting[1];
        c = sorting[0];
 
        // SCV의 3마리의 피가 이미 한 번 방문했던 경우라면 재귀 return
        if(visited[a][b][c]) return;
        else visited[a][b][c] = true;
 
        // 최소를 찾아야 하기에 현재 계산 중인 count가 result보다 크다면 더 계산할 필요없다
        if(result<count) return;
 
        // SCV 3마리에 대한 피를 깎는 경우의 수 6가지 
        findMin(a-9, b-3, c-1, count+1);
        findMin(a-9, b-1, c-3, count+1);
        findMin(a-3, b-9, c-1, count+1);
        findMin(a-3, b-1, c-9, count+1);
        findMin(a-1, b-3, c-9, count+1);
        findMin(a-1, b-9, c-3, count+1);
 
        return;
    }
			
		
	}
