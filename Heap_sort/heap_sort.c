//Heap sort
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//#define DATA_SIZE 100
//#define DATA_SIZE 500
//#define DATA_SIZE 1000
//#define DATA_SIZE 5000
#define DATA_SIZE 10000

typedef enum _boolean {
	FALSE,
	TRUE
} boolean;

struct heap
{
	int S[DATA_SIZE+1];
	int data_s;
};

void siftdown(struct heap H, int i) {
	int parent, largerchild;
	int siftkey;
	boolean spotfound;

	siftkey = H.S[i];
	parent = i;
	spotfound = FALSE;
	while (2 * parent <= H.data_s && !spotfound)
	{
		if (2 * parent < H.data_s && H.S[2 * parent] < H.S[2 * parent + 1])
			largerchild = 2 * parent + 1;
		else
			largerchild = 2 * parent;

		if (siftkey < H.S[largerchild]) {
			H.S[parent] = H.S[largerchild];
			parent = largerchild;

		}
		else
			spotfound = TRUE;
	}
	H.S[parent] = siftkey;
}

void makeheap(int n,struct heap H) {
	H.data_s = n-1;
	for (int i = n / 2; i >= 1; i--) { // i 인덱스 값
		siftdown(H, i);
	}
}

int root(struct heap H) {
	int keyout;
	keyout = H.S[1];
	H.S[1] = H.S[H.data_s-1];
	
	H.data_s = H.data_s - 1;
	siftdown(H, 1);
	return keyout;
}

void removekeys(int n,struct heap H, int S[]) {
	
	for (int i = n-1; i >= 1; i--) {
		S[i] = root(H);
	}
}

void heapsort(int n,struct heap H) {

	makeheap(n, H);
	removekeys(n, H, H.S);
}

int main(void) {

	double a[5];
	double S = 0;
	double average;

	for (int v = 0; v < 5; v++) {
		int m = v + 1; // 몇번째 실행 결과인지 판단하는 변수
		struct heap H;
		H.data_s = DATA_SIZE + 1;

		clock_t start_t = clock(); // argorithm 수행시간 측정을 위한 clock함수
		srand(time(NULL)); // 난수 초기화 (실행할 때마다 랜덤한 변수를 생성하기 위함) //time(NULL)

		//data insert
		for (int i = 1; i < H.data_s; i++) {
			int random_num = rand() % H.data_s; //0~DATA_SIZE 사이의 랜덤한 숫자 
			H.S[i] = random_num;
			//printf("%d ", H.S[i]);
		}
		printf("\n");

		//quick sort
		heapsort(H.data_s, H);

		//정렬 결과를 확인하고 싶을 시에 아래 주석줄을 같이 실행한다.

		/*printf(" HEAP_SORT = ");
		for (int i = 1; i < DATA_SIZE + 1; i++) {
			printf("%d ", H.S[i]);
		}*/
		//printf("\n");

		clock_t end_t = clock();
		double algorithm_duration = (double)(end_t - start_t);

		a[v] = algorithm_duration;
		printf("%dth running time : %lf\n", m, algorithm_duration);
	}
	printf("\n");
	for (int c = 0; c < 5; c++) {
		S = S + a[c];
	}
	average = S / 5;
	printf("running time average = %lf\n", average);
}