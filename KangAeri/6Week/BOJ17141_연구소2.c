#include <stdio.h>

int mov[4][2] = {{1,0}, {-1,0},{0,1},{0,-1}};

int lab[51][51];
int visited[51][51];

int virus[11];
int actives[11];
int que[2500];
int front, rear;

int min,m,n,t,v;


int bfs(){
	int cur=0,i=0,j=0, ni=0, nj=0;
	while(front<rear){
		cur = que[front++];
		for(i=0; i<4; i++){
			ni = cur/n + mov[i][0];
			nj = cur%n + mov[i][1];
			if(ni>=0&& ni<n&& nj>=0 && nj<n && lab[ni][nj]!=1 && visited[ni][nj]==-1){
				que[rear++] = ni*n+nj;
				visited[ni][nj]=visited[cur/n][cur%n]+1;
			}			
		}	
	}
	t=0;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++){
			if(lab[i][j]==0 || (lab[i][j]==2)){			
				if(visited[i][j]==-1){
					return 0;
				} else{
					if(t<visited[i][j]){		
						t = visited[i][j];
					} 
				}
			}
		}
	}
	return 1;
}


void comb(int cnt, int start){
	int i=0,j=0;
	
	if(cnt==m){
		front=0;
		rear=0;
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				visited[i][j] = -1;			
			}	
		}
		for(i=0; i<m; i++){
			que[rear++] = actives[i];
			visited[actives[i]/n][actives[i]%n]=0;
		}	
		if(bfs()==1){
			if(t<min){
				min=t;
			}
		}
		return;
	}
	
	for(i=start; i<v; i++){
	    lab[virus[i]/n][virus[i]%n]=3;
		actives[cnt] = virus[i];
		comb(cnt+1, i+1);
		lab[virus[i]/n][virus[i]%n]=2;
	}
	
}


int main(){
	int i=0,j=0;
	min = 10000;
	t=10000;
	v=0;
	scanf("%d %d", &n, &m);

	for(i=0;i<n;i++){
		for(j=0;j<n;j++){
			scanf("%d", &lab[i][j]);
			if(lab[i][j]==2){
				virus[v++] = i*n+j;
			}
		}
	}
	comb(0,0);
	
	if(min==10000){
		printf("-1");
	} else{
		printf("%d", min);
	}

	return 0;
}
