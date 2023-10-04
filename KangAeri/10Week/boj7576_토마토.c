#include <stdio.h>
#include <stdlib.h>

int mov[4][2] = {{0,-1},{0,1},{-1,0},{1,0}};
int arr[1001][1001];

int que[1001*1001];

int main(){
	int n=0, m=0, i=0, j=0,k=0, cnt=0, day=0;
	int front=-1,rear=-1;//for que
	int ni=0, nj=0,cur=0; //for bfs
	
	scanf("%d %d", &m, &n);
	for(i=0; i<n;i++){
		for(j=0; j<m;j++){
		}
	}
	
	for(i=0; i<n;i++){
		for(j=0; j<m;j++){
			scanf("%d", &arr[i][j]);	
			if(arr[i][j]==0){
				cnt++;
			} else if(arr[i][j]==1){
				que[rear++] = i*m+j;
			}
		}
	}
	
	while(front<rear){//bfs
		cur = que[front++];
		for(k=0; k<4; k++){
			ni = cur/m+mov[k][0];
			nj = cur%m+mov[k][1];
			if(ni>=0 && nj>=0 && ni<n && nj<m && (arr[ni][nj]==0 || arr[ni][nj]>arr[cur/m][cur%m]+1)){
				if(arr[ni][nj]==0){
						cnt--;
				}	
				arr[ni][nj]=arr[cur/m][cur%m]+1;
				que[rear++]=ni*m+nj;
				
			}
		}
	}
			
	if(cnt>0){
		printf("-1");
	} else{
		day=0;
		for(i=0; i<n;i++){
			for(j=0; j<m;j++){
				if(arr[i][j]>day){
					day=arr[i][j];
				}
			}
		}
		printf("%d",day-1);
	}

	
	return 0;
}
