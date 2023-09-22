import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_1080_병합정렬1  {
		static int[] arr;
		static int[] tmp;
		static int cnt = 0;
		static int k = 0;
		static StringBuilder sb = new StringBuilder();
		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String[] split = br.readLine().split(" ");
			int n = Integer.parseInt(split[0]);
			k = Integer.parseInt(split[1]);
			
			arr = new int[n];
			tmp = new int[n];
			split = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(split[i]);
			}
			int left = 0;
			int right = n-1;
			int mid = (left+right) / 2;
			
			mergeSort(left, right);
//			mergeSort(mid+1,right);	
			if(cnt < k) {
				System.out.println(-1);
			}
		}
		
		static void mergeSort(int left, int right) {
			if(left < right) {
				int mid = (left + right) / 2;
				
				mergeSort(left, mid);
				mergeSort(mid+1, right);
				merge(left, mid, right);
			}
		}
		static void merge(int left, int mid, int right) {
			int i = left;
			int j = mid+1; //오른쪽 배열의 left임
			int a = left; // 새로운 배열에 들어갈 인덱스 값
			
			
			while(i <= mid && j <= right) {
				tmp[a++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
			}
			
			while(i <= mid) {
				tmp[a++] = arr[i++];
			}
			while(j <= right) {
				tmp[a++] = arr[j++];
			}
			
			for(int l = left; l <= right; l++) {
	            arr[l] = tmp[l];
	            cnt++;
	            if(cnt == k) {
	            	System.out.println(tmp[l]);
	            	sb.append(tmp[l]);
	            }
	        }

		}
	}
	/*
	5 7
	4 5 1 3 2
	*/
