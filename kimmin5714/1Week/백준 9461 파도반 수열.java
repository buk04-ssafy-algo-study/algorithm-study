import java.util.Scanner;
//long 타입
//0이상,1이상일 때 초기화
public class BOJ_9461 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int t=0;t<T;t++) {
			int N= sc.nextInt();
			long[] p = new long[N+1];
			p[0]=0; 
			
			if(N>0)
				p[1]=1; 
			
			if(N>1)
				p[2]=1;
			
			if(N>2) {
				for(int i=3;i<N+1;i++) {
					p[i]=p[i-2]+p[i-3];
				}
			}

			System.out.println(p[N]);
		}

	}
}
