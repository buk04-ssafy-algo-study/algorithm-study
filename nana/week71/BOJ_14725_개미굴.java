import java.util.*;
import java.io.*;

class Main {

    private static class Node {
        // TreeMap은 정렬을 보장한다
        TreeMap<String, Node> child;

        public Node() {
            this.child = new TreeMap<>();
        }

        public void print(int count) {
            for (String s : child.keySet()) {
                for (int i = 0; i < count; i++) {
                    System.out.print("--");
                }
                System.out.println(s);
                child.get(s).print(count + 1);
            }
        }
    }

    private static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }
    }

    private static int N;
    private static Trie trie;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        trie = new Trie();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Node node = trie.root;  // 시작 노드 = 트라이의 루트 노드

            for (int j = 0; j < K; j++) {
                String now = st.nextToken();

                node.child.putIfAbsent(now, new Node());    // 단어가 없으면 노드 생성
                node = node.child.get(now); // 가리키는 노드 위치 자식노드로 바꿔주기
            }
        }

        // 결과 출력
        trie.root.print(0);
    }
}
