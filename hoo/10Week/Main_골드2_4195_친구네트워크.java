package SSAFY.study.algo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드2_4195_친구네트워크 {

    static int F;
    // 친구 관계 담는 집합 자료형 생각하기(몇 개의 집합이 생길 지 모르니까)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
        }
    }
    
    static void union() {
    	
    }
    
    static void find() {
    	
    }
    
    static void friendNetwork() {
    	
    }

    static void init(BufferedReader br) throws IOException {
        F = Integer.parseInt(br.readLine());
        for (int i = 0; i < F; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String firstName = st.nextToken();
            String secondName = st.nextToken();
            friendNetwork();
        }
    }
	
}
