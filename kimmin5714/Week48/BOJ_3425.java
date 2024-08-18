import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Operator { // 연산자 클래스
    String operator;
    long num;

    public Operator(String operator, long num) {
        this.operator = operator;
        this.num = num;
    }
}
public class BOJ_3425 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while(true){
            st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 0) continue; // 빈 줄 나오는 경우 (프로그램 구분)

            String str = st.nextToken();
            if(str.equals("QUIT")) break;

            Queue<Operator> operator = new ArrayDeque<>();
            int num = 0;
            if(str.equals("NUM"))
                num = Integer.parseInt(st.nextToken());

            operator.offer(new Operator(str,num));

            // 연산자 입력 받는 반복문
            while(true){
                if(operator.peek().operator.equals("END")) // 연산자가 END만 있는 경우
                    break;

                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();

                if(op.equals("END")) break;
                int num2 = 0;
                if(op.equals("NUM"))
                    num2 = Integer.parseInt(st.nextToken());

                operator.offer(new Operator(op, num2)); // 연산자 저장
            }

            int N = Integer.parseInt(br.readLine());
            Queue<Operator> copyOperator = new ArrayDeque<>();

            for(int i=0;i<N;i++){
                copyOperator.addAll(operator); // 연산자 재사용
                Stack<Long> result = new Stack<>(); // 프로그램 한 번 실행 결과

                long init = Long.parseLong(br.readLine());
                if(init>1000000000 || init<0) {
                    sb.append("ERROR\n");
                    continue;
                }
                result.push(init);
                sb.append(program(result, copyOperator)+"\n"); // 프로그램 실행
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static String program(Stack<Long> result, Queue<Operator> operator) {
        while(!operator.isEmpty()){
            Operator op = operator.poll();
            long first = -1;
            long second = -1;
            long cal = -1;
            boolean error = false;

            switch (op.operator){
                case "NUM":
                    result.push(op.num);
                    break;
                case "POP":
                    if(result.size()<1) {
                        error=true;
                        break;
                    }
                    result.pop();
                    break;
                case "INV":
                    if(result.size()<1) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    result.push(first*(-1));
                    break;
                case "DUP":
                    if(result.size()<1) {
                        error=true;
                        break;
                    }
                    result.push(result.peek());
                    break;
                case "SWP":
                    if(result.size()<2) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    second = result.pop();

                    result.push(first);
                    result.push(second);
                    break;
                case "ADD":
                    if(result.size()<2) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    second = result.pop();
                    cal = first+second;
                    if(Math.abs(cal)>1000000000){
                        error = true;
                        break;
                    }
                    result.push(cal);
                    break;
                case "SUB":
                    if(result.size()<2) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    second = result.pop();
                    cal = second-first;
                    if(Math.abs(cal)>1000000000){
                        error = true;
                        break;
                    }
                    result.push(cal);
                    break;
                case "MUL":
                    if(result.size()<2) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    second = result.pop();
                    cal = second*first;
                    if(Math.abs(cal)>1000000000){
                        error = true;
                        break;
                    }
                    result.push(cal);
                    break;
                case "DIV":
                    if(result.size()<2) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    second = result.pop();

                    int minusCnt = 0;
                    if(first == 0){
                        error = true;
                        break;
                    }
                    if(first<0) {
                        first *= (-1);
                        minusCnt++;
                    }
                    if(second<0) minusCnt++;

                    cal = Math.abs(second)/Math.abs(first);
                    if(Math.abs(cal)>1000000000){
                        error = true;
                        break;
                    }
                    if(minusCnt==1)
                        cal *= (-1);

                    result.push(cal);
                    break;
                case "MOD":
                    if(result.size()<2) {
                        error=true;
                        break;
                    }
                    first = result.pop();
                    second = result.pop();

                    if(first == 0){
                        error = true;
                        break;
                    }
                    if(first<0)
                        first *= (-1);
                    cal = Math.abs(second)%Math.abs(first);
                    if(Math.abs(cal)>1000000000){
                        error = true;
                        break;
                    }

                    if(second<0)
                        cal *= (-1);

                    result.push(cal);
                    break;
            }
            if(error)
                return "ERROR";
        }
        if(result.size() != 1)
            return "ERROR";
        return result.pop().toString();
    }
}
