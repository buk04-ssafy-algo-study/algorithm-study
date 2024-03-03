package _14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	
	public static void main(String[] args) {
		String today = "2022.05.19";
		String[] terms = new String[] {"A 6", "B 12", "C 3"};
		String[] privacies = new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
		int [] a  = solution(today, terms, privacies);
		System.out.println(Arrays.toString(a));
	}
	
    static public int[] solution(String today, String[] terms, String[] privacies) {
    	List<Integer> list = new ArrayList<>();
        String[] tmp = today.split("\\.");
        int start = Integer.parseInt(tmp[0]) * 28 * 12 + Integer.parseInt(tmp[1]) * 28 + Integer.parseInt(tmp[2]);
        Map<String,Integer> map = new HashMap<>();
        for (String s : terms) {
        	tmp = s.split(" ");
			map.put(tmp[0], Integer.parseInt(tmp[1]));
		}
        
        for (int i = 0; i < privacies.length; i++) {
			tmp = privacies[i].split(" ");
			String[] date = tmp[0].split("\\.");
			int cal = Integer.parseInt(date[0]) * 28 * 12 + Integer.parseInt(date[1]) * 28 + Integer.parseInt(date[2]);
			int pr = map.get(tmp[1]);
			if((cal + pr * 28) <= start) {
				list.add(i + 1);
			}
		}
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
        
        return answer;
    }
}
