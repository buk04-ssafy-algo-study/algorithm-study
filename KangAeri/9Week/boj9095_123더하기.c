#include <stdio.h>

int main(){
	int t=0, tcase=0, n=4;
	int dp[12];
	scanf("%d",&t);
	dp[1]=1;
	dp[2]=2;
	dp[3]=4;

	while(n<=11){
		dp[n]=dp[n-1]+dp[n-2]+dp[n-3];
		n++;
	}
	for(tcase=0; tcase<t; tcase++){
		scanf("%d",&n);
		printf("%d\n",dp[n]);
	}
	return 0;
}
