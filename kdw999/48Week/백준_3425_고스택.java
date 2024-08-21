package Week48;

import java.io.*;
import java.util.*;

public class 백준_3425_고스택 {

	static Stack<Integer> stack;
	static List<String> command;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true) {
		
			stack = new Stack<>();
			command = new ArrayList<>();
			
		   while(true) {
			
			String line = br.readLine();
			command.add(line);
			
			if(line.equals("END")) break;
			
			if(line.equals("QUIT")) {
				System.out.println(sb);
				return;
			}
		   }
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			
			int num = Integer.parseInt(br.readLine());
			
			stack.add(num);
			
			for(int j=0; j<command.size(); j++) {
				
				// 스택 위에 숫자 저장, NUM
				if(command.get(i).charAt(0) == 'N') {
					
					String[] split = command.get(i).split(" ");
					stack.add(Integer.parseInt(split[1]));
				}
				
				// 스택 가장 위 숫자 제거
				else if(command.get(i).equals("POP")) {
					
					stack.pop();
				}
				
				// 첫 번째 수 부호 변경
                else if(command.get(i).equals("INV")) {
					
                	int stNum = stack.pop();
                	stack.add(-stNum);
				}
				
				// 첫 번째 숫자를 하나 더 스택 가장 위에 저장
                else if(command.get(i).equals("DUP")) {
                	
                	int stNum = stack.peek();
                	stack.add(stNum);
                }
				
				// 첫 숫자와 두번 째 위치 변경
                else if(command.get(i).equals("SWP")) {
                	
                	int stNum1 = stack.pop();
                	int stNum2 = stack.pop();
                	
                	stack.add(stNum1);
                	stack.add(stNum2);
                }
				
				// 1, 2 숫자 더하기
                else if(command.get(i).equals("ADD")) {
	
                	int stNum1 = stack.pop();
                	int stNum2 = stack.pop();
                	
                	stack.add(stNum1 + stNum2);
                 }
				
				// 2에서 1 빼기
                else if(command.get(i).equals("SUB")) {
	
                	int stNum1 = stack.pop();
                	int stNum2 = stack.pop();
                	
                	stack.add(stNum2 - stNum1);
                }
				
				// 1 * 2
                else if(command.get(i).equals("MUL")) {
	
                	int stNum1 = stack.pop();
                	int stNum2 = stack.pop();
                	
                	stack.add(stNum1 * stNum2);
                 }
				
				// 2 / 1, 피연산자가 하나라도 음수면 몫도 음수
                else if(command.get(i).equals("DIV")) {
	
                	int stNum1 = stack.pop();
                	int stNum2 = stack.pop();
                	
                	int resultNum = Math.abs(stNum2) / Math.abs(stNum1);
                	
                	if(stNum1 < 0 || stNum2 < 0) resultNum = -resultNum;
                	
                }
				
				// 2 % 1, 나눠지는 수가 음수면 나머지도 음수
                else if(command.get(i).equals("MOD")) {
	
                	int stNum1 = stack.pop();
                	int stNum2 = stack.pop();
                	
                	int resultNum = Math.abs(stNum2) % Math.abs(stNum1);
                	
                	if(stNum2 < 0) resultNum = -resultNum;
                	
                }
				
				// END
                else {
                	
                	if(!stack.isEmpty()) {
                		
                	int result = stack.pop();
                	System.out.println(result);
                	sb.append(result+"\n");
                	}
                	
                	else {
                		
                	sb.append("ERROR"+"\n");
                	}
                }
			}
		}
		sb.append("\n");
		
		}// 가장 외부 while
	}
}
