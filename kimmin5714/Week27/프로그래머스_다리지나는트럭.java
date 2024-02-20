package Week27;
import java.util.*;
	class Solution {
		public int solution(int bridge_length, int weight, int[] truck_weights) {
			int answer = 0;
			int sum = 0;
			Queue<Integer> bridge = new ArrayDeque<>();

			for(int i : truck_weights){
				while(true){
					if(bridge.isEmpty()){
						bridge.offer(i);
						sum+=i;
						answer++;
						break;
					}
					else if(bridge.size() == bridge_length){
						sum-=bridge.poll();
					}
					else{
						if(sum+i>weight){
							bridge.offer(0);
							answer++;
						}
						else if(sum+i<=weight){
							bridge.offer(i);
							sum+=i;
							answer++;
							break;
						}
					}
				}
			}
			answer+=bridge_length;
			return answer;
		}
	}

