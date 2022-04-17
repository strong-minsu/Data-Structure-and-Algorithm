//Merge sort
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define DATA_SIZE 100
//#define DATA_SIZE 500
//#define DATA_SIZE 1000
//#define DATA_SIZE 5000
//#define DATA_SIZE 10000

void merge(int h, int m, int *U, int *V, int* S) {
	int i = 0, j = 0, k = 0;
	while (i < h && j < m) {
		if (U[i] <= V[j]) {
			S[k] = U[i];
			i++;
		}
		else {
			S[k] = V[j];
			j++;
		}
		k++;
	}
	
	//���� �迭 ������ ������� ���� �־��ִ� �κ�
	if (i >= h) {
		for (j ; j < m; j++) {
			S[k] = V[j];
			k++;
		}
	}
	else {
		for (i; i < h; i++) {
			S[k] = U[i];
			k++;
		}
	}
	
}
void mergesort(int n, int S[]) {
	if (n > 1) {
		int h = n / 2;
		int m = n - h;

		int* U = malloc(h * sizeof(int));
		int* V = malloc(m*sizeof(int));

		for (int i = 0; i < h; i++) {
			U[i] = S[i];
		}
		for (int i = 0; i < m; i++) {
			V[i] = S[h+i];
		}
		/*for (int i = 0; i < m; i++) {
			printf("%d ", V[i]);
		}*/
		
		mergesort(m, V);
		mergesort(h, U);
		
		merge(h, m, U, V, S);
	}
}

int main(void) {

	double a[5];
	double Sum = 0;
	double average;

	for (int v = 0; v < 5; v++) {
		int m = v + 1; // ���° ���� ������� �Ǵ��ϴ� ����
		int n = DATA_SIZE;
		int* data = malloc(n * sizeof(int));
		clock_t start_t = clock(); // argorithm ����ð� ������ ���� clock�Լ�
		srand(NULL); // ���� �ʱ�ȭ (������ ������ ������ ������ �����ϱ� ����) //time(NULL)

		//data insert
		for (int i = 0; i < DATA_SIZE; i++) {
			int random_num = rand() % DATA_SIZE + 1; //0~DATA_SIZE ������ ������ ���� 
			data[i] = random_num;
			//printf("%d ", data[i]);
		}
		printf("\n");

		//merge sort
		mergesort(DATA_SIZE, data);

		//���� ����� Ȯ���ϰ� ���� �ÿ� �Ʒ� �ּ����� ���� �����Ѵ�.

		/*printf(" MERGE_SORT = ");
		for (int i = 0; i < DATA_SIZE; i++) {
			printf("%d ", data[i]);
		}
		printf("\n");*/

		clock_t end_t = clock();
		double algorithm_duration = (double)(end_t - start_t);

		a[v] = algorithm_duration;
		printf("%dth running time : %lf\n", m, algorithm_duration);
	}
	printf("\n");
	for (int c = 0; c < 5; c++) {
		Sum = Sum + a[c];
	}
	average = Sum / 5;
	printf("running time average = %lf\n", average);
}