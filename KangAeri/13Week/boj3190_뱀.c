#include <stdio.h>
int mov[4][2]={{0,1}, {1,0},{0,-1},{-1,0}};
int board[100][100];
int convertDir(char direction){
	int re=0;
	switch (direction){
		case 'D':
			re=1;
			break;
		case 'L':
			re=-1;
			break;		
	}
	return re;
		
}
int main(){
	int i=0,j=0, r=0,c=0,sec=0,n=0,k=0,dnum=0;
	int front=-1,rear=0,ni=0,nj=0,head=0,dir=0,changeIdx=0;
	char tmp;
	int que[10001];
	int chgInfo[10001][2];
	
	que[++front]=0;
	
	scanf("%d",&n);//r,c
	scanf("%d",&k);//apple#
	for(i=0;i<n;i++){
		for(j=0;j<n;j++){
			board[i][j]=0;
		}
	}//board init
	board[0][0]=-1;///-1:snake, 1:apple, 0:blank
	for(i=0;i<k;i++){
		scanf("%d %d",&r, &c);		
		board[r-1][c-1]=1;
	}//apple
	
	scanf("%d",&dnum);//# of direction-change
	
	for(changeIdx=0; changeIdx<dnum; changeIdx++){
		scanf("%d %c", &chgInfo[changeIdx][0], &tmp); //changing time , rotating direction
		chgInfo[changeIdx][1]=convertDir(tmp);
	}
	
	changeIdx=0;
	sec=1;
	while(1){
		ni=que[front]/n+mov[dir][0];
		nj=que[front]%n+mov[dir][1];
		if(ni>=0 && ni<n && nj>=0 && nj<n && board[ni][nj]!=-1){
			head=ni*n+nj;
			que[++front]= head;
			//printf("[%d %d %d]",dir, que[front], head);
			if(board[head/n][head%n]==0){
				board[que[rear]/n][que[rear]%n]=0;
				rear++;
			} 
			board[head/n][head%n]=-1;
		} else{
			//printf("break:%d,%d:%d",head/n,head%n,board[head/n][head%n]);
			break;
		}
		
		while( (changeIdx<dnum) && (sec==chgInfo[changeIdx][0])){
			dir=(dir+chgInfo[changeIdx++][1]+4)%4;
			//printf("(%d~%d)(head %d %d) ",sec, dir, head/n, head%n);
		}
		sec++;
	}
	
	printf("%d",sec);
	return 0;
}
