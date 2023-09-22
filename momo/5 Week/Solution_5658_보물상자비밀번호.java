package _824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution_5658_보물상자비밀번호 {
	static int N; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			String[] split = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			int K = Integer.parseInt(split[1]);
			
			char[] arr = new char[N];
			Set<String> set = new HashSet<String>();
			String str = br.readLine();
			for (int i = 0; i < N; i++) {
				arr[i] = str.charAt(i);
			}
			addSet(set, arr);
			
			for (int i = 0; i < N/4; i++) {
				rotate(arr);
				addSet(set, arr);
			}
			
			String[] list = new String[set.size()];
			Iterator<String> it = set.iterator();
			int idx = 0;
			while(it.hasNext()) {
				list[idx] = it.next();
				idx++;
			}
			Arrays.sort(list, Collections.reverseOrder());
			sb.append(Integer.parseInt(list[K-1], 16) + "\n");
		}
		System.out.println(sb);
	}
	
	static void rotate(char[] arr) {
		char tmp = arr[arr.length-1];
		for (int i = arr.length-1; i > 0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = tmp; 
	}
	
	static void addSet(Set<String> set, char[] arr) {
		for (int i = 0; i < arr.length; i = i+N/4) {
			char[] newChar = Arrays.copyOfRange(arr, i, i+N/4);
			set.add(String.copyValueOf(newChar));
		}
	}
}
