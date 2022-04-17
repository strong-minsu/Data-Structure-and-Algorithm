//Exchange sort
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define DATA_SIZE 100
//#define DATA_SIZE 500
//#define DATA_SIZE 1000
//#define DATA_SIZE 5000
//#define DATA_SIZE 10000

int main(void) {
	double a[5];
	double Sum = 0;
	double average;

	for (int v = 0; v < 5; v++) {
		int m = v + 1; // ���° ���� ������� �Ǵ��ϴ� ����
		int n = DATA_SIZE;

		int* data = malloc(n * sizeof(int));
		clock_t start_t = clock(); // argorithm ����ð� ������ ���� clock�Լ�
		srand(time(NULL)); // ���� �ʱ�ȭ (������ ������ ������ ������ �����ϱ� ����)

		//data insert
		for (int i = 0; i < DATA_SIZE; i++) {
			int random_num = (rand() % DATA_SIZE) + 1; //0~DATA_SIZE ������ ������ ���� 
			data[i] = random_num;
			//printf("%d ", data[i]);
		}
		printf("\n");

		//exchange_sort
		int temp = 0;

		for (int i = 0; i < DATA_SIZE; i++) {
			for (int j = i + 1; j < DATA_SIZE; j++) {

				if (data[i] >= data[j]) { //���� �������� ũ�� �ڸ� ��ȯ
					temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
				else {
					continue;
				}
			}
		}

		//���� ����� Ȯ���ϰ� ���� �ÿ� �Ʒ� �ּ����� ���� �����Ѵ�.

		/*printf(" EXCHANGE_SORT = ");
		for (int i = 0; i < DATA_SIZE; i++) {
			printf("%d ", data[i]);
		}*/

		printf("\n");
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