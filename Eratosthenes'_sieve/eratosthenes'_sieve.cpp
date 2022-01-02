//�Է¹��� ���������� �� �߿��� �Ҽ��� ���� ����ϴ� ���α׷�
// Eratosthenes' sieve
using namespace std;
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	int n;
	//���� �Է¹���
	cout << "������ �Է��Ͻÿ�: ";
	cin >> n;
	cout << "\n";

	//�Է¹��� ���� 1���� ū �������� �Ѵ�.
	if (n < 2) {
		printf("Error: �Է��� ������ 1���� Ŀ���մϴ�.\n");
		return 0;
	}
	int* parray;

	//�޸� �Ҵ�
	parray = new int[n + 1];	// [n]���� ������ array[0] ~ array[29]�� �����Ǳ� ������ n+1�� ����
	if (parray == 0) {
		printf("Error : �޸� �Ҵ翡 �����Ͽ����ϴ�.\n");
		return 0;
	}

	//�޸𸮸� 0���� �ʱ�ȭ (�Ҽ��� �ƴ� ���� 1�� ����)
	memset(parray, 0, sizeof(int) * (n + 1));

	int i, j;
	//�Ҽ� ���ϱ� ����
	for (i = 2; i <= n; i++) {
		if (parray[i] == 1) // �̹� ������ �� �� �Ҽ��� �ƴϸ� ���� ���� �Ѿ
			continue;
		j = i;				// i�� ��������
		while ((j += i) <= n) {
			parray[j] = 1;	//�Ҽ��� �ƴ� ���� ó���ϴ� ����
		}
	}

	//�Ҽ��� ���
	for (i = 2; i <= n; i++) {
		if (parray[i] == 0)
			printf("%d ", i);
	}
	printf("\n");

	//�Ҵ�� �޸� ����
	delete[] parray;
	return 0;

}