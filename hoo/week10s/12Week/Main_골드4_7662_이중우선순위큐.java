package study.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_���4_7662_���߿켱����ť {
	
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int test=0; test<t; test++) {
			int input = Integer.parseInt(br.readLine());
			
			Queue<Integer> min = new PriorityQueue<>();	// �ּ� ���� Ʈ��
			Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); // �ִ� ���� Ʈ�� 
			map = new HashMap<>();	// ���� ���� ���θ� �����ϱ� ���� map(���� : ��(����)) 
			for(int i=0; i<input; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op = st.nextToken();
				if(op.equals("I")) {	// ������ ��
					int num = Integer.parseInt(st.nextToken());
					max.offer(num);
					min.offer(num);
					map.put(num, map.getOrDefault(num, 0)+1);	// �̹� map�� ����� ���ڶ�� �� ���� + 1���ְ�, ���� �ȵ� ���ڶ�� �� 1�� ��������
				} else {	// ������ ��
					int type = Integer.parseInt(st.nextToken());
					
					if(map.size() == 0) continue;	// �ʿ� ����� ���� ���� = ť�� ����ִ� -> �ǳ� ��
					if(type == 1) { //�ִ� ����
						delete(max);
					}else { // �ּڰ� ����
						delete(min);
					}
				}
			}
			
			if (map.size() == 0) {
	            sb.append("EMPTY\n");
	        } else {
	        	int res = delete(max);
	        	sb.append(res+" "); // �ִ� 
	        	if(map.size()>0) res = delete(min);
	        	sb.append(res+"\n"); // �ּڰ�
	        }
		}
		System.out.println(sb.toString());
	}
	
	static int delete(Queue<Integer> q) {	// ���� ������ ���ִ� �Լ�
		int res = 0;
		while(true) {
			res = q.poll();	// �̹� �ۿ��� isEmpty üũ�� ���̹Ƿ� ���� isEmpty üũ ����.
			
			int cnt = map.getOrDefault(res, 0);  
			if(cnt == 0) continue;	// map�� ���� �����̸� skip(min ���忡���� max�� ���� ���� ���� ����̰�, max ���忡���� �� �ݴ�)
			
			if(cnt == 1) map.remove(res);	// ���� ���� ���� 1�̸� map���� ����
			else map.put(res, cnt-1);	// 2�̻��̸� -1����
			break;	// cnt�� 0�� �ƴϱ⸸ �ϸ� res�� ��ȿ�� �����̹Ƿ� �ٷ� break
		}
		
		return res;
	}
	
}
