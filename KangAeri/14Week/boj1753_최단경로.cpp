#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include<queue>
#include<vector>
using namespace std;

vector<pair<int, int> > arr[20001];

int INF = 4000000;
int v;
int e;

int main(){
	int i=0,j=0,p=0,q=0,w=0,start=0,cur=0,d=0;
	int chk=0;
	int cnt = 0;
	int* visited;
	int* dist;
	struct vertex* tmp;
	struct vertex* node;
	priority_queue<pair<int,int> > pq;
	//////init//////////
	scanf("%d %d", &v,&e);
	scanf("%d", &start);
	visited = (int*)malloc(sizeof(int)*(v+1));
	dist = (int*)malloc(sizeof(int)*(v+1));
	for(i=1; i<=v; i++){
		dist[i]=INF;
	}
	//////initend /////////

//info get
	for(i=0;i<e; i++){
	  scanf("%d %d %d", &p,&q,&w);
	  arr[p].push_back(make_pair(q, w));
  }
  //////////////////

  //dijkstra(start);
  pq.push(make_pair(0,start));
  dist[start]=0;
	
  while(pq.empty()==0){
  	
	  	w=-1*(pq.top().first);//start->cur
	  	cur=pq.top().second;
	  	pq.pop();

	    for(i=0; i<arr[cur].size();i++){///while(tmp->next){
			  j=arr[cur][i].first;//tmp->link;
	    	d=arr[cur][i].second;
	    	if(dist[j] > w+d){
		    	dist[j] =w+d;
		    	pq.push(make_pair(-1*dist[j],j));
        }
      }
  }
   
  ///result
  for(i=1;i<=v; i++){
    if(dist[i]==INF){
      printf("INF\n");
    } else{
      printf("%d\n",dist[i]);
    }
  }
  
  free(visited);
	free(dist);
  return 0;
}