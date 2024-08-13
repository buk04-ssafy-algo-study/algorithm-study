package SSAFY.study.algo.week48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main_골드4_3425_고스택 {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result;
        while (true) {
            result = doMachine(br);
            if (result.equals("")) break;
            sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static String doMachine(BufferedReader br) throws IOException {   // 각 기계별 설명을 입력받고 수행하는 함수
        StringBuilder sb = new StringBuilder();
        String programCommand;
        List<String> programCommandList = new ArrayList<>();
        while (true) {
            programCommand = br.readLine();
            if (programCommand.equals("")) continue;
            if (programCommand.equals("END")) break;
            else if (programCommand.equals("QUIT")) return "";
            programCommandList.add(programCommand);
        }

        int N = Integer.parseInt(br.readLine());    // 프로그램 실행 횟수
        int result;
        for (int i = 0; i < N; i++) {   // N번 만큼 프로그램 수행
            result = doProgram(programCommandList, Integer.parseInt(br.readLine()));
            if (result == Integer.MAX_VALUE) sb.append("ERROR").append("\n");
            else sb.append(result).append("\n");
        }

        return sb.toString();    // 명령이 QUIT이 아니고 실행을 잘 마쳤다는 의미로 sb를 리턴
    }

    static int doProgram(List<String> programCommandList, int initNumber) { // 기계의 각 프로그램을 수행하는 함수
        Stack<Integer> stack = new Stack<>();
        stack.push(initNumber);
        String programCommand;
        int popNumber1, popNumber2;

        for (int i = 0; i < programCommandList.size(); i++) {
            programCommand = programCommandList.get(i);
            try {
                switch (programCommand.substring(0, 3)) {  // 간단한 것들은 그대로 작성, 나머지는 함수화
                    case "NUM":   // X를 스택에 삽입
                        stack.push(Integer.valueOf(programCommand.substring(4)));  // 숫자만 잘라서 삽입
                        break;
                    case "POP": // 스택 가장 윗수 제거
                        stack.pop();
                        break;
                    case "INV": // 스택 가장 윗 수 부호 바꿈
                        popNumber1 = stack.pop();
                        stack.push(popNumber1 * -1);
                        break;
                    case "DUP": // 스택 가장 윗 수를 하나 더 스택에 삽입
                        stack.push(stack.peek());
                        break;
                    case "SWP": // 스택 가장 윗수와 그 아랫수 위치를 바꿈
                        popNumber1 = stack.pop();
                        popNumber2 = stack.pop();
                        stack.push(popNumber1);
                        stack.push(popNumber2);
                        break;
                    case "ADD": case "SUB": case "MUL":
                        stack.push(doBinaryCalc(programCommand, stack));
                        break;
                    case "DIV": case "MOD":
                        stack.push(calcDivMod(programCommand, stack));
                        break;
                }
            } catch (Exception e) {
                return Integer.MAX_VALUE;
            }
        }

        if (stack.size() != 1) return Integer.MAX_VALUE;
        return stack.pop();
    }

    static int doBinaryCalc(String programCommand, Stack<Integer> stack) throws Exception {   // 이항연산 수행하는 함수
        int LIMIT = 1_000_000_000;
        int popNumber1, popNumber2;
        long result = 0;

        try {
            popNumber1 = stack.pop();
            popNumber2 = stack.pop();
        } catch (Exception e) {
            throw new Exception();
        }

        switch (programCommand) {
            case "ADD":  // 스택 가장 윗수와 그 아랫수 더해서 다시 삽입, 10억 넘으면 에러
                result = (long) popNumber1 + (long) popNumber2;
                break;
            case "SUB": // 스택 가장 윗수와 그 아랫수 더해서 빼서(두 번째 - 첫 번째) 삽입, -10억 넘으면 에러
                result = (long) popNumber2 - (long) popNumber1;
                break;
            case "MUL":  // 스택 가장 윗수와 그 아랫수 곱해서 다시 삽입, 10억 넘으면 에러
                result = (long) popNumber1 * (long) popNumber2;
                break;
        }
        if (Math.abs(result) > LIMIT) throw new Exception();    // 계산 결과 절댓값 10억 넘으면 예외 던짐

        return (int) result;    // 예외 발생 안하면 계산한 값 리턴
    }

    static int calcDivMod(String programCommand, Stack<Integer> stack) throws Exception {    // 나누기 연산
        // div, mod: 왼쪽 숫자(두 번째 숫자)가 피제수, 오른쪽 숫자(첫 번째 숫자)가 제수
        // 몫, 나머지 부호 -> 1. 피연산자 중 음수 하나면 몫 부호 음수 / 2. 이 경우를 제외하면 몫은 항상 양수 / 3. 나머지 부호는 피제수(왼쪽 숫자) 부호랑 같음
        int popNumber1, popNumber2;
        long result = 0;

        try {
            popNumber1 = stack.pop();
            popNumber2 = stack.pop();
        } catch (Exception e) {
            throw new Exception();
        }
        if (popNumber1 == 0) throw new Exception(); // 0 divide 에러

        switch (programCommand) {
            case "DIV":  // 스택 가장 윗수와 그 아랫수 나눈(두 번째 숫자/첫 번째 숫자) 몫 저장
                result = Math.abs(popNumber2) / Math.abs(popNumber1);
                if ((popNumber1 < 0 && popNumber2 > 0)|| (popNumber1 > 0) && (popNumber2 < 0)) result *= -1;
                break;
            case "MOD": // 스택 가장 윗수와 그 아랫수 나눈(두 번째 숫자%첫 번째 숫자) 나머지 저장
                result = Math.abs(popNumber2) % Math.abs(popNumber1);
                if (popNumber2 < 0) result *= -1;
                break;
        }

        return (int) result;    // 예외 발생 안하면 계산한 값 리턴
    }

}
