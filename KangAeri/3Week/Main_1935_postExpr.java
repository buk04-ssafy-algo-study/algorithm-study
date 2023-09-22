package boj_study.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1935_postExpr {
	
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int n=0,i=0;
		double opd1=0,opd2=0;
		char op;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		int[] refers = new int[n];
		String postfix = in.readLine();

		Stack<Double> stk = new Stack<>();
		
		for(i=0;i<n;i++) {
			refers[i] = Integer.parseInt(in.readLine());
		}//usageEx:char-'A'
		
		for(i=0;i<postfix.length();i++) {
			op = postfix.charAt(i);
			if(op =='*' || op =='+' || op =='-' || op =='/') {
	            switch (op){
	                case '*':
	                    stk.push(stk.pop()*stk.pop());
	                    break;
	                case '+':
	                	stk.push(stk.pop()+stk.pop());
	                    break;
	                case '-':
	                	opd2 = stk.pop();
	                	opd1 = stk.pop();
	                	stk.push(opd1-opd2);
	                    break;
	                case '/':
	                	opd2 = stk.pop();
	                	opd1 = stk.pop();
	                	stk.push(opd1/opd2);
	                    break;
	            }
	        } else{
	        	stk.push((double)refers[op-'A']);
	        }
		}
		
		if(stk.size()==1) {
			System.out.printf("%.2f",stk.pop());
		} else {
			System.out.println("computation error.RETURN");
		}

	}
}
