#include <stdio.h>
//���� �׷������� ������ strar_node�������� ��� ����� ���� �ִܰ�θ� ���Ͻÿ�
// �ּҺ���� ���� Ž������ ã���� ��

//��� �� (test-case 3���� ���)
//#define num_case 4
#define num_case 5
//#define num_case 6

//���Ѵ븦 ��Ÿ���� ��
int INF = 10000000;

//��ü �׷��� �ʱ�ȭ (3���� ���)
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
//�湮 ��� üũ true, false �� �� �ϳ��� ����
bool visit[num_case];
//�Ÿ�: �Ÿ� ���ÿ� Ȱ��
int distance[num_case];

//���� �ּ� �Ÿ��� ������ ��� ��ȯ.
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

//dijkstra �˰��� ����
void dijkstra_algorithm(int start_node) {
	for (int i = 0; i < num_case; i++) {

		//���� ��忡�� �ٸ� ������ �Ÿ� ����
		distance[i] = graph[start_node][i];
	}
	// ������ ��带 �湮������ ����
	visit[start_node] = true;

	for (int i = 0; i < num_case - 2; i++) {

		//���� ��� ����
		int current = getSmallnode();
		//���� ��� �湮������ ����
		visit[current] = true;

		for (int j = 0; j < num_case; j++) {
			//��尡 �湮���� ���� ���� �Ÿ� ���
			if (!visit[j]) {
				//���� ���� ���̺��� �ٸ� ��带 ��ģ ���� �Ÿ��� �� ���� ��
				if (distance[current] + graph[current][j] < distance[j]) {
					distance[j] = distance[current] + graph[current][j];
				}
			}
		}
	}
}

void main(void) {
	//0��° �迭 ���, �׷��� ���� �����ϸ� 1�� ��带 ���� ������
	dijkstra_algorithm(0);
	//���� ��� ���� �Ÿ� ���
	for (int i = 1; i < num_case; i++) {
		printf("%d �� ��� �湮�� �ּ� �Ÿ�  = %d\n", i + 1, distance[i]);
	}
}
