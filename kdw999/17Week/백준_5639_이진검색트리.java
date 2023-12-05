package Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_5639_이진검색트리 {
	
	static int[] tree = new int[10001];
	
	public static void main(String[] args) throws IOException  {
		Scanner sc = new Scanner(System.in);
		
		int idx=0;
		while(sc.hasNext()) tree[idx++] = sc.nextInt();
	
		postOrder(0, idx-1);
		System.out.println(tree);
	}
	
	public static void postOrder(int n, int end) {
		
		if(n > end) return; // 좌측 노드가 마지막 노드보다 크다면
		
		int mid = n+1; // 중간을 1씩 증가시키면서 루트 노드가 뭔지 찾기 이진트리는 루트 왼쪽은 작고 오른쪽은 크니까
		while(mid <= end && tree[mid] < tree[n]) mid++;
		
		postOrder(n+1, mid-1); // 왼쪽 서브트리
		postOrder(mid, end); // 오른쪽 서브트리
		System.out.println(tree[n]);
	}
}
