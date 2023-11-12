#include <stdio.h>

int mov[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
int field[13][7];
int visited[13][7];
int row=12;
int col=6;

int bfs(){
	int cnt=0, key=0;
	int i=0,j=0, k=0,cur=0, ni=0, nj=0, front=0, rear=0, mrear=0;
	int que[80];//que for bfs
	int mem[80];//que for memorizin targets(#<4:ignore)
	
	for(i=0;i<row;i++){
		for(j=0; j<col; j++){
			visited[i][j]=0;//init
		}
	}
	///////////////////
	for(i=0;i<row;i++){
		for(j=0; j<col; j++){
			if(field[i][j]<=0 || visited[i][j]==1){
				continue;
			}
			key=field[i][j];
			mrear=0;
			//bfs
			que[rear++]=i*col+j;
			visited[i][j]=1;
			while(front<rear){
				cur=que[front++];
				mem[mrear++]=cur;
				
				for(k=0;k<4;k++){
					ni=cur/col+mov[k][0];
					nj=cur%col+mov[k][1];
					if(ni<row && ni>=0 && nj>=0 && nj<col &&field[ni][nj]==key && visited[ni][nj]==0){
						que[rear++]=ni*col+nj;
						visited[ni][nj]=1;
					}
				}
			}
			if(mrear>=4){
				cnt+=mrear;
				while(mrear>0){
					cur=mem[--mrear];
					field[cur/col][cur%col]=0;
				}
			}
		}//for:j
	}//for:i::field end
	return cnt;
}


int main(){
	int i=0,j=0, k=0, cnt=0,ans=0;
	char in[col+1];
	int fall[row];
	
	////get chars//
	for(i=0; i<row; i++){		
		scanf("%s",in);
		for(j=0; j<col; j++){
			switch (in[j]){
				case '.':
					field[i][j]=0;
					break;
				case 'R' : 
					field[i][j]=1;
					break;
				case 'G' : 
					field[i][j]=2;
					break;
				case 'B' : 
					field[i][j]=3;
					break;
				case 'P' : 
					field[i][j]=4;
					break;
				case 'Y' : 
					field[i][j]=5;
					break;	
			}
		}
	} //input end
    
	//compute
	while(ans<row*col){
		cnt=bfs();
		if(cnt==0){
			break;
		}
		//fall
		for(i=0;i<col;i++){
			k=0;
			for(j=row-1; j>=0; j--){
				if(field[j][i]>0){
					fall[k++]=field[j][i];
					field[j][i]=0;
				}
			}
			for(j=0; j<k;j++){
				field[row-1-j][i]=fall[j];
			}
		}
		ans++;		
	}//end while:computed
	
	printf("%d",ans);
	return 0;
}
