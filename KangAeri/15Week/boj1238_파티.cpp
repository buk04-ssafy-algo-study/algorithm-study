#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include<iostream>
#include<queue>
#include<vector>
using namespace std;

// typedef struct vertex{
// 	int weight;
// 	int link;
// 	struct vertex* next;
// }vertex;
vector<pair<int, int> > arr[20001];
//struct vertex arr[20001];
int INF = 4000000;
int v;
int e;

int main(){
	int i=0,j=0,k=0, p=0,q=0,w=0,start=0,cur=0,d=0;
	int max = 0;
	int* time;
	int* dist;
	priority_queue<pair<int,int> > pq;
  
	//////init//////////
	scanf("%d %d %d", &v,&e,&start);
	time = (int*)malloc(sizeof(int)*(v+1));
	dist = (int*)malloc(sizeof(int)*(v+1));
	for(i=1; i<=v; i++){
		dist[i]=INF;
	}
  for(i=0;i<e; i++){
    scanf("%d %d %d", &p,&q,&w);
    arr[p].push_back(make_pair(q, w));
  }
	//////initend /////////


//////////dijkstra////////start->ith//////
  pq.push(make_pair(0,start));
  while(pq.empty()==0){
    w=-1*(pq.top().first);//start->cur
    cur=pq.top().second;
    pq.pop();
    for(i=0; i<arr[cur].size();i++){ 
      j=arr[cur][i].first;
      d=arr[cur][i].second;
      if(dist[j] > w+d){
        dist[j] =w+d;
        pq.push(make_pair(-1*dist[j],j));
      }
    }
  }
    
  for(i=1;i<=v; i++){
    time[i]=dist[i];
  }
/////////////////////////


  ///////////return back//////////////
  for(k=1; k<=v; k++){
    pq = priority_queue<pair<int,int>>();
    if(k==start){
      continue;
    }
    for(i=1; i<=v; i++){
      dist[i]=INF;
    }
    pq.push(make_pair(0,k));
    while(pq.empty()==0){
      w=-1*(pq.top().first);//start->cur
      cur=pq.top().second;
      if(cur==start){
        time[k]+=dist[cur];
        break;
      }
      pq.pop();
      for(i=0; i<arr[cur].size();i++){
        j=arr[cur][i].first;
        d=arr[cur][i].second;
        if(dist[j] > w+d){
          dist[j] =w+d;
          pq.push(make_pair(-1*dist[j],j));
        }
      }
    }
  }

  ///result
  for(i=1;i<=v; i++){
    //printf("%d ",time[i]);
    if(time[i]>max){
      max=time[i];
    }
  }
  printf("%d",max);
  
  free(time);
	free(dist);
  return 0;
}