#include <stdio.h>
#include <stdlib.h>
#define MAX(a,b) (((a)>(b))?(a):(b))

int main(){
	int n=0,i=0;
	int**dp;
	int* stairs;
	scanf("%d",&n);
	stairs = (int*)malloc(sizeof(int)*(n));
	dp = (int**)malloc(sizeof(int*)*(n));
	
	for(i=0; i<n; i++){
		scanf("%d",&stairs[i]);
		dp[i]=(int*)malloc(sizeof(int)*2);
	}
	dp[0][0]=0;
	dp[0][1]=stairs[0];
	if(n>=2){
		dp[1][0]=dp[0][1];
		dp[1][1]=dp[0][1]+stairs[1];
	}
	for(i=2; i<n; i++){
		dp[i][0]=dp[i-1][1];
		dp[i][1]=MAX(dp[i-2][0]+stairs[i-1]+stairs[i],dp[i-2][1]+stairs[i]);	
	}
	printf("%d",dp[n-1][1]);
	for(i=0; i<n; i++){
		free(dp[i]);
	}
	free(dp);
	free(stairs);
	
	return 0;
}
