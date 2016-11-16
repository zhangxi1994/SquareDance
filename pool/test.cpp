#include <iostream>
#include <vector>
using namespace std;

// Simulation for round table strategy
// Pros: swap is only needed between two consecutive elements
// Cons: space inefficient

int n;
bool vis[1000][1000];

int main() {
	cin >> n;
	vector<int> l;
	for (int i = 1; i <= n; ++ i) l.push_back(i);

	for (int round = 1; round <= n; ++ round) {
		int start = (round & 1) == 1 ? 0 : 1;

		cout << "round " << round << ":\t";
		if (start == 1) cout << l[0] << ' ';
		for (int i = start; i < n - 1; i += 2) {
			cout << l[i] << '-' << l[i + 1]  << ' ';
			vis[l[i]][l[i+1]] = vis[l[i + 1]][l[i]] = true;
		}
		if (start == 1) cout << l[n - 1] << ' ';
		cout << endl;

		for (int i = start; i < n - 1; i += 2)
			swap(l[i], l[i + 1]);

	}
	for (int i = 1; i <= n; ++ i)
		for (int j = i + 1; j <= n; ++ j)
			if (!vis[i][j]) {
				cout << "Assessment failed!" << endl;
				return 0;
			}
	cout << "Assessment succeeded!" << endl;
	return 0;
}
