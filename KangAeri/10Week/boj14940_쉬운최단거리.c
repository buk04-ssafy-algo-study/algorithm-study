#include <stdio.h>
#include <stdlib.h>
int map[1001][1001];
int que[1001*1001];
int mov[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};

int main(){
	int i=0,j=0,k=0,m=0, n=0;
	int front=-1, rear=-1,cur=0, ni=0,nj=0;
	scanf("%d %d", &n, &m);

	for(i=0; i<n;i++){
		for(j=0; j<m;j++){
			scanf("%d", &map[i][j]);
			if(map[i][j]==1){
				map[i][j]=-1;
			} else if(map[i][j]==2){
				que[rear++]=i*m+j;
				map[i][j]=0;
			} 
		}
	}
	
	while(front<rear){ //bfs
		cur = que[front++];
		for(k=0; k<4; k++){	
			ni = cur/m + mov[k][0];
			nj = cur%m + mov[k][1];
			if(ni>=0 && nj>=0 && ni<n && nj<m &&map[ni][nj]==-1){
				que[rear++]=ni*m+nj;
				map[ni][nj]=map[cur/m][cur%m]+1;
			}
		}
	}
	
	for(i=0; i<n;i++){
		for(j=0; j<m;j++){
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
	
	return 0;
}
