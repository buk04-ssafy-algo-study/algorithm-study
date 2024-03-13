import java.util.Scanner;

class BOJ_20546 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] stock = new int[14];
		int junCnt=0;
		int sungCnt=0;
		int junCash=N;
		int sungCash=N;
		int junTotal=0;
		int sungTotal=0;
		
		for(int i=0;i<14;i++) 
			stock[i]=sc.nextInt();
		
		for(int t : stock) {
			if(junCash >= t) {
				while(true) {
					junCash-=t;
					if(junCash<=0) {
						junCash+=t;
						break;
					}
					junCnt++;
				}
				break;
			}
		}
		junTotal += (junCash+ junCnt*stock[13]); 
		
		int increase=0;
		int decrease=0;
		
		for(int i=1;i<14;i++) {
			if(stock[i]>stock[i-1]) {
				increase++;
			}
			if(stock[i]<stock[i-1]) {
				decrease++;
			}
			if((increase==3) && (sungCash>=stock[i])) {
				while(true) {
					sungCash-=stock[i];
					if(sungCash<=0) {
						sungCash+=stock[i];
						break;
					}
					sungCnt++;
				}
			}	
			
			if(decrease==3) {
				sungCash+= (stock[i]*sungCnt);
				sungCnt=0;
			}
		}
		sungTotal += (sungCash + sungCnt*stock[13]);	
		
		if(junTotal>sungTotal) System.out.println("BNP");
		else if(junTotal <sungTotal) System.out.println("TIMING");
		else System.out.println("SAMESAME");
	}
}
