//radix sort
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
//�� ������ ����� ���� �ڸ����� �� �����Ͽ� ������ �Ѵ�.(DATA_SIZE 1000, DIGITS 2 <- �̷��� ������ ��� �� ���� ���)

#define DATA_SIZE 100
//#define DATA_SIZE 500
#define DIGITS  2          //2�ڸ���

//#define DATA_SIZE 1000
//#define DATA_SIZE 5000
//#define DIGITS  3         //3�ڸ���

//#define DATA_SIZE 10000
//#define DIGITS  4         //4�ڸ���

#define MAX_QUEUE_SIZE DATA_SIZE+1 // ť�� ũ��
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
// ���� ���� ���� �Լ�
int is_empty(ListNode* ln)
{
    return (ln->link == NULL);
}

// ���� �Լ�
void enqueue(ListNode* ln, int item)
{
    ListNode* newNode;
    ListNode* p;
    newNode = (ListNode*)malloc(sizeof(ListNode));
    newNode->data = item;
    newNode->link = NULL; //���ο� ������
    if (ln->link == NULL)
    {
        ln->link = newNode;
        return;
    }
    p = ln;
    while (p->link != NULL) //p�� �������������� ����������� link���� null �ϰ�� �װ��� ��������� �߰�.
    {
        p = p->link;
    }
    p->link = newNode;

}
// ���� �Լ�
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

    for (k = 0; k < BUCKETS; k++) init(&ln[k]);  // ť���� �ʱ�ȭ

    for (l = 0; l < DIGITS; l++) { 
        for (i = 0; i < n; i++) // �����͵��� �ڸ����� ���� ť�� ���� n=10
            enqueue(&ln[(list[i] / factor % 10)], list[i]);

        for (k = i = 0; k < BUCKETS; k++)  // ��Ŷ���� ������ list�� ��ģ��.
            while (!is_empty(&ln[k]))
                list[i++] = dequeue(&ln[k]);
        factor *= 10; // �� ���� �ڸ����� ����.
    }
}
//
void main(){

    double a[5];
    double S = 0;
    double average;

    for (int v = 0; v < 5; v++) {
        int m = v + 1; // ���° ���� ������� �Ǵ��ϴ� ����
        int i;
        int n = DATA_SIZE;
        int list[DATA_SIZE];

        clock_t start_t = clock(); // argorithm ����ð� ������ ���� clock�Լ�
        srand(time(NULL));// ���� �ʱ�ȭ (������ ������ ������ ������ �����ϱ� ����) //time(NULL)
        for (i = 0; i < n; i++)       
            list[i] = rand() % DATA_SIZE;

        //for (i = 0; i < n; i++)
            //printf("%4d", list[i]);
        printf("\n");

        radix_sort(list, n); // �������� ȣ�� 

        //���� ����� Ȯ���ϰ� ���� �ÿ� �Ʒ� �ּ����� ���� �����Ѵ�.
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