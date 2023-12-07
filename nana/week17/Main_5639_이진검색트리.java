import java.io.*;

public class Main_5639_이진검색트리 {

    private static Node root;
    private static StringBuilder sb;

    private static void makeTree(Node node, int value) {
        // 현재 노드와 삽입해야 하는 값을 매개변수로 받아온다.
        if (node.value > value) {   // 현재 노드의 값보다 삽입하는 수가 작은 경우
            if (node.left != null)  // 왼쪽 노드가 비어있지 않다면
                makeTree(node.left, value); // 재귀를 통해 비어있는 곳 찾기
            else    // 비어있다면, 왼쪽 노드에 새로운 노드 생성
                node.left = new Node(value, null, null);

        } else {    // 현재 노드의 값보다 삽입하는 수가 큰 경우
            if (node.right != null) // 오른쪽 자식에 삽입하기 위해 자리 찾기
                makeTree(node.right, value);
            else node.right = new Node(value, null, null);
        }
    }

    private static void postOrder(Node node) {
        if (node == null) return;
        // 노드가 비어있다면 트리의 끝부분이므로 return
        // 후위우선순회 : 왼쪽자식 -> 오른쪽자식 -> 부모
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        root = new Node(Integer.parseInt(br.readLine()), null, null);

        while (true) {
            String line = br.readLine();
            if (line == null || line.isEmpty()) break;  // 입력이 더이상 없으면 멈춤

            int val = Integer.parseInt(line);

            makeTree(root, val);    // root에서 시작해서 val의 자리를 찾아간다.
        }

        postOrder(root);    // root부터 후위우선순회

        System.out.println(sb);
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}