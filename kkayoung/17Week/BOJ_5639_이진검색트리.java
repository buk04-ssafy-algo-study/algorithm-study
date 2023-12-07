// https://www.acmicpc.net/problem/5639
import java.io.*;
import java.util.*;

public class practicejava {
	
	static class Node {
		int num;
		Node left, right;
		Node(int num){
			this.num =num;
		}
	}

	static Node root;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// input
		root = null;
		while(true){
			String line = br.readLine();
			if(line==null || line.isEmpty()) break;
			int n = Integer.parseInt(line);
			
			Node node = new Node(n);
			if(root==null) {
				root = node;
				continue;
			}
			find(node); // make binary search tree
		}
		// post order
		postorder(root);

		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void postorder(Node node){
		if(node.left!=null) postorder(node.left); // left
		if(node.right!=null) postorder(node.right); // right
		sb.append(node.num+"\n"); // node
	}

	static void find(Node node){
		int n = node.num;
		Node now = root;
		while(now!=null) {
			if(n < now.num){ // move left
				if(now.left==null) {
					now.left = node;
					return;
				} else {
					now = now.left;
				}
			} else { // move right
				if(now.right==null) {
					now.right = node;
					return;
				} else {
					now = now.right;
				}
			}
		}
	}
}
