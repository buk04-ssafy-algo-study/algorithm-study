import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
public class Main_9935_문자열폭발 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String target = br.readLine();

        Stack<Character> stack = new Stack<>();
        int len = target.length();

        for (int s = 0; s < str.length(); s++) {
            stack.push(str.charAt(s));
            boolean same = false; // 문자열이 다르다로 초기화

            // target 문장의 길이보다 크다면 검사
            if (stack.size() >= len) {

                // 스택의 가장 위 글자와 target문장의 끝 글자가 같다면
                if (stack.peek() == target.charAt(len - 1)) {
                    int count = 0;

                    // target 문장과 스택 윗부분에 같은 단어가 있는지 검사
                    for (int j = 0; j < len; j++)
                        if (stack.get(stack.size() - len + j) == target.charAt(j))
                            count++;

                    // 같은 글자의 수가 target 길이와 같으면 같은 문장이기 때문에 true
                    if (count == len)
                        same = true;
                }

                // 내용이 같다면 문자열 폭발 -> stack.pop();
                if (same)
                    for (int i = 0; i < len; i++)
                        stack.pop();
            }
        }

        // 스택이 비어있다면? 문자열 모두 폭발 -> FRULA
        if (stack.isEmpty()) {
            sb.append("FRULA");
        } else {
            for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
            }
        }
        System.out.println(sb);
    }
}