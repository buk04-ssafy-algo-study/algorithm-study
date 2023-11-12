#include <stdio.h>
#include <string.h>
#include <string>
#include <map>
using namespace std;

map <string, int> m;

int main(){
	int ans=0,start=0, comment=0, meetEnd=0, streamEnd=0;
	int hh=0,mm=0;
	char name[21];
	
	scanf("%d:%d",&hh, &mm);
	start=60*hh+mm;
	scanf("%d:%d",&hh, &mm);
	meetEnd=60*hh+mm;
	scanf("%d:%d",&hh, &mm);
	streamEnd=60*hh+mm;
	
	while(1){
		if (scanf("%d:%d%s", &hh, &mm, name) == EOF){
			break;
		}		
		comment=hh*60+mm;
		if(comment<=start){
			m.insert({name,1}); 
		} else if(meetEnd<=comment &&comment<=streamEnd){
			if(m.count(name)>0&&m[name]==1){	
				m[name]=2;
				ans++;
			} 
		}
	}

//result
	printf("%d",ans);
	return 0;
}
