package SSAFY.study.algo.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_골드5_5639_이진검색트리 {

    static List<Integer> preOrder;
    static class Node { // 트리의 노드
        private int v;
        Node left, right;   // 좌, 우 노드

        public Node(int v) {
            this.v = v;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return this.v + " " + this.left + " " + this.right;
        }
    }

    static class BST {
        int idx;    // 이진 검색 트리를 만들 때 사용 되는, 전위 순회 결과를 담은 리스트의 인덱스

        Node bstFromPre(List<Integer> preOrder, int smaller, int bigger) { // 전위순회 결과로부터 이진 검색 트리를 만드는 함수
            if (smaller > bigger) return null;  // 이진 검색 트리 조건에 부합하지 않는 노드 => null 반환

            Node root = new Node(preOrder.get(idx++));
            if (smaller == bigger) return root; // 서브트리 구성 못하는 노드면 현재 노드 바로 반환 후 종료

            int firstBigger = 0;
            for (int i = smaller; i < bigger+1; i++) {
                if (preOrder.get(i) > root.v) {
                    firstBigger = i;
                    break;
                }
            }

            root.left = bstFromPre(preOrder, idx, firstBigger - 1);
            root.right = bstFromPre(preOrder, idx, bigger);

            return root;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        BST bst = new BST();
        Node node = bst.bstFromPre(preOrder, 0, preOrder.size()-1);
        printPost(node);
    }

    static void init() throws IOException {
        preOrder = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while(true) {
            input = br.readLine();
            if (input == null || input.equals("")) break;
            int inputNum = Integer.parseInt(input);
            preOrder.add(inputNum);
        }
    }

    static void printPost(Node node) {
        if (node == null) return;
        printPost(node.left);
        printPost(node.right);
        System.out.println(node.v);
    }

}
