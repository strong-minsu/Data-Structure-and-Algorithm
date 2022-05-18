#include <stdio.h>
//방향 그래프에서 시작점 strar_node에서부터 모든 마디로 가는 최단경로를 구하시오
// 최소비용을 선형 탐색으로 찾도록 함

//노드 수 (test-case 3가지 경우)
//#define num_case 4
#define num_case 5
//#define num_case 6

//무한대를 나타내는 값
int INF = 10000000;

//전체 그래프 초기화 (3가지 경우)
/*int graph[num_case][num_case] = {
	{0, 3, 6, 7},
	{INF, 0, 1, INF},
	{INF, INF, 0, 1},
	{INF, INF, INF, 0}
};
*/

int graph[num_case][num_case] = {
	{0, 7, 4, 6, 1},
	{INF, 0, INF, INF, INF},
	{INF, 2, 0, 5, INF},
	{INF, 3, INF, 0, INF},
	{INF, INF, INF, 1, 0}
};

/*
int graph[num_case][num_case] = {
	{0, 1, 4, 5, 2, INF},
	{INF, 0, 1, 1, INF, INF},
	{INF, INF, 0, INF, 2, INF},
	{INF, INF, INF, 0,INF, 4},
	{INF, INF, INF, 3, 0, 1},
	{INF, INF, 3, INF, INF, 0}
};
*/
//방문 노드 체크 true, false 둘 중 하나로 설정
bool visit[num_case];
//거리: 거리 계산시에 활용
int distance[num_case];

//가장 최소 거리를 가지는 노드 반환.
int getSmallnode() {
	int min = INF;
	int node = 0;
	for (int i = 0; i < num_case; i++) {
		if (distance[i] < min && !visit[i]) {
			min = distance[i];
			node = i;
		}
	}
	return node;
}

//dijkstra 알고리즘 구현
void dijkstra_algorithm(int start_node) {
	for (int i = 0; i < num_case; i++) {

		//시작 노드에서 다른 노드까지 거리 정의
		distance[i] = graph[start_node][i];
	}
	// 시작점 노드를 방문함으로 설정
	visit[start_node] = true;

	for (int i = 0; i < num_case - 2; i++) {

		//현재 노드 설정
		int current = getSmallnode();
		//현재 노드 방문함으로 설정
		visit[current] = true;

		for (int j = 0; j < num_case; j++) {
			//노드가 방문하지 않은 노드면 거리 계산
			if (!visit[j]) {
				//직접 가는 길이보다 다른 노드를 거친 것이 거리가 더 작을 때
				if (distance[current] + graph[current][j] < distance[j]) {
					distance[j] = distance[current] + graph[current][j];
				}
			}
		}
	}
}

void main(void) {
	//0번째 배열 요소, 그래프 노드로 생각하면 1번 노드를 시작 점으로
	dijkstra_algorithm(0);
	//시작 노드 제외 거리 출력
	for (int i = 1; i < num_case; i++) {
		printf("%d 번 노드 방문시 최소 거리  = %d\n", i + 1, distance[i]);
	}
}
