#include <stdio.h>

int graph[101][101];
int visit[101];
int n;
int lev=0;

void bfs(int start){
	int i=0,tmp=0, rear=0, front=0,cur=0;
	int que[101];
	int con[101];
	que[rear++]=start;
	lev=0;
	con[start]=0;
	while(front<rear){
		cur=que[front++];
		visit[cur]=1;	
		tmp=con[cur]+1;
		for(i=1;i<=n;i++){
			if(visit[i]==0 && graph[cur][i]==1){
				que[rear++]=i;
				visit[i]=1;
				con[i]=tmp;
				lev+=tmp;
			}
		}
	}
}

int main(){
	int m=0, p1=0, p2=0;
	int i=0,j=0,minp=0,min=2147483647;
	scanf("%d %d", &n, &m);
	for(i=0;i<=n;i++){ //init graph
		for(j=0;j<=n;j++){
			graph[i][j]=0;
		}
	}
	for(i=0;i<m;i++){
		scanf("%d %d", &p1, &p2);
		graph[p1][p2]=1;
		graph[p2][p1]=1;
	}
	
	for(i=1;i<=n;i++){
		for(j=0;j<=n;j++){
			visit[j]=0;
		}
		bfs(i);
		//printf("(%d)", lev);
		if(lev<min){
			min = lev;
			minp=i;
		}
	}

	printf("%d",minp);
	return 0;
}
