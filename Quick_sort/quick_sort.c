//Quick sort
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define DATA_SIZE 100
//#define DATA_SIZE 500
//#define DATA_SIZE 1000
//#define DATA_SIZE 5000
//#define DATA_SIZE 10000

int partition(int low, int high, int *S) {
	int i, j;
	int pivot_item;
	int temp = 0;
	int pivotpoint = low;

	pivot_item = S[low];
	j = low;

	for (i = low + 1; i < high+1; i++) {
		if (S[i] < pivot_item) {
			j++;
			temp = S[i];
			S[i] = S[j];
			S[j] = temp;
		}
	}
	pivotpoint = j;
	temp = S[low];
	S[low] = S[pivotpoint];
	S[pivotpoint] = temp;
	
	return pivotpoint;
	
}

void quicksort(int low, int high, int* S) {
	if (high > 0) {

		if (high > low) {
			int pp = partition(low, high, S);
			quicksort(low, pp - 1, S);
			quicksort(pp + 1, high, S);
		}
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
		srand(time(NULL)); // ���� �ʱ�ȭ (������ ������ ������ ������ �����ϱ� ����) //time(NULL)

		//data insert
		for (int i = 0; i < DATA_SIZE; i++) {
			int random_num = rand() % DATA_SIZE + 1; //0~DATA_SIZE ������ ������ ���� 
			data[i] = random_num;
			//printf("%d ", data[i]);
		}
		printf("\n");

		//quick sort
		quicksort(0, DATA_SIZE - 1, data);

		//���� ����� Ȯ���ϰ� ���� �ÿ� �Ʒ� �ּ����� ���� �����Ѵ�.

		/*printf(" QUICK_SORT = ");
		for (int i = 0; i < DATA_SIZE; i++) {
			printf("%d ", data[i]);
		}*/
		//printf("\n");

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