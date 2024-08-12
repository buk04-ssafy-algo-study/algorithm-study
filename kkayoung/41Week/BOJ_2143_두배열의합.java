// https://www.acmicpc.net/problem/2143
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] A = new int[n];
		for(int i=0;i<n;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		List<Long> subA = cal(A);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] B = new int[m];
		for(int i=0;i<m;i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		List<Long> subB = cal(B);

		Collections.sort(subA);
		Collections.sort(subB, Comparator.reverseOrder());

		System.out.println(binarySearch(subA, subB, T));
	}

	public static List<Long> cal(int[] list) {
		List<Long> result = new ArrayList<>();
		int size = list.length;

		for(int i=0;i<size;i++) {
			long sum = 0;
			for(int j=i;j<size;j++) {
				sum += list[j];
				result.add(sum);
			}
		}
		return result;
	}

	public static long binarySearch(List<Long> A, List<Long> B, int T) {
		long answer = 0;
		
		int aIdx = 0;
		int bIdx = 0;

		while(aIdx<A.size() && bIdx<B.size()) {
			long sum = A.get(aIdx)+B.get(bIdx);
			
			if(sum<T) {
				aIdx++;
			} else if(sum>T) {
				bIdx++;
			} else {
				long aCnt = 0;
				long aValue = A.get(aIdx);
				long bCnt = 0;
				long bValue = B.get(bIdx);
				
				while(aIdx<A.size() && A.get(aIdx)==aValue) {
					aCnt++;
					aIdx++;
				}

				while(bIdx<B.size() && B.get(bIdx)==bValue) {
					bCnt++;
					bIdx++;
				}

				answer += (aCnt*bCnt);
			}

		}

		return answer;
	}

	
}
