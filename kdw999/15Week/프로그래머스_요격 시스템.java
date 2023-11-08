public static int solution(int[][] targets) {
	 
		 int answer=0;
		 
		 Arrays.sort(targets, (o1, o2) ->{
			 return o1[1] - o2[1]; // 2차원 배열에서 1번째 인덱스 기준으로 오름차순 정렬
		 });
		
		 
		 int last = -1; // 처음은 무조건 실행시키기 위함
		 
		 for(int[] target : targets) {
			 if(last == -1) { // 처음 한 번은 무조건 실행
				 answer++; // 좌표 하나 꽂기
				 last = target[1] - 1; // 좌표 꽂은 지점은 현재 폭격 미사일 좌표의 끝 지점 앞 [끝 지점이랑 안겹치게]
			 }
			 if(target[0] <= last && last < target[1]) continue; // 꽂은 지점이 폭격 미사일 범위에 포함되있다면 새 요격지점을 추가할 필요없음
			 
			 // 미사일 범위를 벗어나면 새 요격지점 추가
			 answer++;
			 last = target[1] -1; // 기존 요격지점을 벗어나게한 폭격 미사일 범위의 끝지점으로 이후 미사일들을 포함할 수 있는지 판단
		 }
		 System.out.println(answer);
		 return answer;
	 }
