#include <stdio.h>
#include <stdlib.h> 

int map[101][101];
int mov[4][2]={{0,-1},{0,1},{-1,0},{1,0}};

int mincomp(int a, int b){
	if(a<b){
		return a;
	} else{
		return b;
	}
}

int main(){
	int i=0, j=0, t=0, n=0, m=0, sword=0, min=0,flag=0;
	int cur=0, ni=0,nj=0,front=0, rear=0;
	int* que;
	scanf("%d %d %d", &n, &m, &t);
	for(i=0; i<n; i++){
		for(j=0; j<m; j++){
			scanf("%d", &map[i][j]);
			if(map[i][j]==2){
				sword=i*m+j;
				map[i][j]=0;
			} else if(map[i][j]==1){
				map[i][j]=-1;
			}
		}
	}
	min=n*m;
	
	//bfs
	que=(int*)malloc(sizeof(int)*m*n);
	que[front++]=0;
	map[0][0]=1;
	while(rear<front){
		cur=que[rear++];
		if(cur==(n-1)*j+m-1){
			flag=1;
			min=mincomp(min, map[cur/m][cur%m]-1);
			break;
		} else if(cur==sword){
			flag=1;
			min=map[cur/m][cur%m]+n-sword/m+m-sword%m-3;
		}
		for(i=0; i<4;i++){
			ni=cur/m+mov[i][0];
			nj=cur%m+mov[i][1];
			if(ni>=0 && ni<n && nj>=0 && nj<m && map[ni][nj]==0){
				que[front++]=ni*m+nj;
				map[ni][nj]=map[cur/m][cur%m]+1;
			}
		}
	}
	
	////result
	if(min<=t&&flag==1){
		printf("%d",min);
	} else{
		printf("Fail");
	}
	
	free(que);


	return 0;	
}
