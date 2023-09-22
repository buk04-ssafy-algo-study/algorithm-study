#include <stdio.h>
#include <stdlib.h>

typedef struct counsel{
	int t;
	int p;
}counsel;

int main(){
	int t,p,n=0;
	int i,j,max=0;
	int* dp;
	struct counsel* arr; 
	
	scanf("%d", &n);
	arr = (struct counsel*)malloc(sizeof(struct counsel)*n);
	dp = (int*)malloc(sizeof(int)*n);
	
	for(i=0;i<n;i++){
		scanf("%d %d", &t, &p);
		arr[i].t=t;
		arr[i].p=p;
		dp[i] = p;
	}
	
	for(i=1;i<n;i++){
		for(j=0;j<i;j++){
			if(i-j>=arr[j].t){
				if(dp[i]<arr[i].p+dp[j]){
					dp[i] = arr[i].p+dp[j];
				}
			}
		}
	}
	
	max = 0;
	for(i=0;i<n;i++){
		if(arr[i].t<=n-i){
			if(max<dp[i]){
				max = dp[i];
			}
		}
	}
	printf("%d",max);
	
	
	free(dp);
	free(arr);
	return 0;
}
