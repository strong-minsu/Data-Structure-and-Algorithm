//radix sort
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
//각 데이터 사이즈가 갖는 자리수를 잘 설정하여 컴파일 한다.(DATA_SIZE 1000, DIGITS 2 <- 이렇게 수행할 경우 잘 못된 결과)

#define DATA_SIZE 100
//#define DATA_SIZE 500
#define DIGITS  2          //2자리수

//#define DATA_SIZE 1000
//#define DATA_SIZE 5000
//#define DIGITS  3         //3자리수

//#define DATA_SIZE 10000
//#define DIGITS  4         //4자리수

#define MAX_QUEUE_SIZE DATA_SIZE+1 // 큐의 크기
#define BUCKETS 10

typedef struct {
    int data;
    struct ListNode* link;
}ListNode;



void init(ListNode* ln)
{
    ln->data = 0;
    ln->link = NULL;
}
// 공백 상태 검출 함수
int is_empty(ListNode* ln)
{
    return (ln->link == NULL);
}

// 삽입 함수
void enqueue(ListNode* ln, int item)
{
    ListNode* newNode;
    ListNode* p;
    newNode = (ListNode*)malloc(sizeof(ListNode));
    newNode->data = item;
    newNode->link = NULL; //새로운 노드생성
    if (ln->link == NULL)
    {
        ln->link = newNode;
        return;
    }
    p = ln;
    while (p->link != NULL) //p가 마지막까지가서 마지막노드의 link값이 null 일경우 그곳에 마지막노드 추가.
    {
        p = p->link;
    }
    p->link = newNode;

}
// 삭제 함수
int dequeue(ListNode* ln)
{
    ListNode* p, * q;
    int item;
    p = ln;
    q = ln->link;
    if (is_empty(p))
        printf("Queue is empty");
    item = q->data;
    p->link = q->link;

    free(q);
    return item;
}


void radix_sort(int list[], int n)
{
    int i;
    int k, l = 1;
    int factor = 1;

    ListNode ln[BUCKETS];

    for (k = 0; k < BUCKETS; k++) init(&ln[k]);  // 큐들의 초기화

    for (l = 0; l < DIGITS; l++) { 
        for (i = 0; i < n; i++) // 데이터들을 자리수에 따라 큐에 삽입 n=10
            enqueue(&ln[(list[i] / factor % 10)], list[i]);

        for (k = i = 0; k < BUCKETS; k++)  // 버킷에서 꺼내어 list로 합친다.
            while (!is_empty(&ln[k]))
                list[i++] = dequeue(&ln[k]);
        factor *= 10; // 그 다음 자리수로 간다.
    }
}
//
void main(){

    double a[5];
    double S = 0;
    double average;

    for (int v = 0; v < 5; v++) {
        int m = v + 1; // 몇번째 실행 결과인지 판단하는 변수
        int i;
        int n = DATA_SIZE;
        int list[DATA_SIZE];

        clock_t start_t = clock(); // argorithm 수행시간 측정을 위한 clock함수
        srand(time(NULL));// 난수 초기화 (실행할 때마다 랜덤한 변수를 생성하기 위함) //time(NULL)
        for (i = 0; i < n; i++)       
            list[i] = rand() % DATA_SIZE;

        //for (i = 0; i < n; i++)
            //printf("%4d", list[i]);
        printf("\n");

        radix_sort(list, n); // 선택정렬 호출 

        //정렬 결과를 확인하고 싶을 시에 아래 주석줄을 같이 실행한다.
        //for (i = 0; i < n; i++)
            //printf("%4d", list[i]);

        clock_t end_t = clock();
        double algorithm_duration = (double)(end_t - start_t);

        a[v] = algorithm_duration;
        printf("%dth running time : %lf\n", m, algorithm_duration);
    }
    for (int c = 0; c < 5; c++) {
        S = S + a[c];
    }
    printf("\n");
    average = S / 5;
    printf("running time average = %lf\n", average);
}