#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int no;
	struct node* nb;	
}node;

int n;

void bfs(struct node* link){
	int i=0,cur=0;
	int rear=0, front=0;
	struct node* tmp;
	int que[n+1];
	int visit[n+1];
	int par[n+1];
    for(i=2;i<=n;i++){
    	visit[i]=0;
	}
	que[rear++]=1;	
	while(front<rear){
		cur=que[front++];
		visit[cur]=1;
		tmp = link[cur].nb;
		while(tmp){
			if(visit[tmp->no]==0){
				que[rear++]=tmp->no;
				par[tmp->no]=cur;
			}
			tmp=tmp->nb;
		}
	}
	for(i=2;i<=n;i++){
		printf("%d\n",par[i]);
	}

}

int main(){
	int n1=0, n2=0;
	int i=0,j=0;
	struct node* tmp;
	struct node* nd;
	struct node* tree;
	scanf("%d", &n);
	tree = (struct node*)malloc(sizeof(struct node)*(n+1));
	for(i=0;i<=n;i++){			
		tree[i].no = i;
		tree[i].nb = NULL;
	}
	
	for(i=1;i<n;i++){
		scanf("%d %d", &n1, &n2);
		
		tmp = &tree[n1];
		nd = (struct node*)malloc(sizeof(struct node));
		nd->no = n2;
		nd->nb = NULL;
		while(tmp->nb){ //find latest-added neighbor from n1
			tmp = tmp->nb;
		}
		tmp->nb = nd; 
		
		tmp = &tree[n2];
		nd = (struct node*)malloc(sizeof(struct node));
		nd->no = n1;
		nd->nb = NULL;
		while(tmp->nb){ //find latest-added neighbor from n2
			tmp = tmp->nb;
		}
		tmp->nb = nd; 
	}

	bfs(tree);
	
	free(tree);
	return 0;
}
