package Week71;

import java.io.*;
import java.util.*;

public class 백준_14725_개미굴 {
	
	static class Node{
		
		String name; // 노드명
		TreeMap<String, Node> child; // 현재 노드의 자식 노드를 트리 맵의 키로 저장
		
		// 객체 생성 시 노드명, 맵 초기화
		Node(String name){
			this.name = name;
			this.child = new TreeMap<>();
		}
	}

	static int N;
	
	public static void main(String[] args) throws IOException {
		init();
	}
	
	static void nodeInsert(Node root, String[] children) {
		Node curNode = root; // 현재 정점
		
		// 현재 정점에 자식 노드 추가하기 
		for(String node : children) {
			
			// 현재 노드의 자식 노드 중 해당 이름의 노드가 없다면 자식 노드로 추가
			if(!curNode.child.containsKey(node)) {
				curNode.child.put(node, new Node(node)); // 맵에 자식 노드의 이름을 key, 자식 노드의 자식 노드를 받을 맵을 value로
			}
			
			// 현재 노드를 자식 노드로 변경
			curNode = curNode.child.get(node);
		}
	}
	
	static void print(Node node, int depth) {
		
		// 해당 노드의 자식 노드 꺼내기
		for(Node child : node.child.values()) {
//			System.out.println("부모 노드: "+ node.name +"/ 자식 노드: "+ child.name + " / 깊이: "+ depth);
			for(int i=0; i<depth; i++) System.out.print("--"); // 깊이만큼 -- 붙임
			System.out.println(child.name);
			print(child, depth+1);
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Node root = new Node(""); // 개미굴 입구 삽입
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			
			String[] str = line.split(" ");
			
			int K = Integer.parseInt(str[0]);
			String[] children = Arrays.copyOfRange(str, 1, str.length); // K 이후 노드명을 배열로
			nodeInsert(root, children); // 부모 노드에 자식 노드 삽입, KIWI BANANA -> root 노드에 KIWI 노드 삽입, KIWI 노드에 BANANA 노드 삽입
		}
		
		print(root, 0);
	}
}
