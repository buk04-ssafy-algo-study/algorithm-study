#include <iostream>
#include <string>
#include <map>
using namespace std;

map<string, int> set;

int main(int argc, char * argv[]) {
	ios_base::sync_with_stdio(0); cin.tie(0);
	int n=0, m=0, i=0,j=0;
    int ans = 0;
	string target;
    string cmp;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> target;
		set[target] = 1;
	}
	
	for (int i = 0; i < m; i++) {
		cin >> cmp;
		if (set[cmp] ==1)
			ans++;
	}
	
	cout << ans;//<< endl;

	return 0;
}
