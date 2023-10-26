// https://www.acmicpc.net/problem/11286
// 우선순위큐에 (절댓값, 값)을 넣고 뺀다
// 절댓값이 작은 순으로 정렬된다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Elem{
		int abs, value; // 절댓값, 값
		Elem(int abs, int value){
			this.abs = abs;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Elem> pq = new PriorityQueue<>(new Comparator<Elem>() {
			@Override
			public int compare(Elem a, Elem b){
				if(a.abs==b.abs)
					return Integer.compare(a.value, b.value);
				else
					return Integer.compare(a.abs, b.abs);
			}			
		});

		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++){
			int x = Integer.parseInt(br.readLine());
			if(x!=0){ // 추가
				pq.offer(new Elem(Math.abs(x),x));
			}
			else { // 배열에서 절댓값이 가장 작은 값 출력, 그 값을 배열에서 제거
				if(pq.isEmpty()){
					System.out.println(0);
				}else{
					System.out.println(pq.poll().value);
				}
			}
		}
	}
}
