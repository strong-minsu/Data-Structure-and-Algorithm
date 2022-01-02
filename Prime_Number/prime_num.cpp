//�Է¹��� ������ �Ҽ����� �Ǻ��ϴ� ���α׷�
using namespace std;
#include <stdio.h>
#include <math.h> //sqrt �Լ��� ����ϱ� ���� �ʿ�
#include <iostream>

int is_prime1(int n) // 2 ~ N-1 ������ ���� ���� ������ �Ҽ����� �Ǻ��ϴ� �Լ�
{
	int i;
	for (i = 2; i < n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

int is_prime2(int n) // 2~ sqrt(N)������ ���� ������ �Ҽ����� �Ǻ��ϴ� �Լ�
{
	int i, sqrn;
	sqrn = (int)sqrt(n);
	for (i = 2; i <= sqrn; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

void main(void)
{
	int n, result;
	cout << "�Ҽ����� �Ǻ��� ������ �Է��Ͻÿ�: ";
	cin >> n;
	printf("\n");
	result = is_prime1(n);
	//result = is_prime2(n);
	
	//��ȯ ���� ����� �Ǵ� True or False
	if (result == 1) {
		printf("%d�� �Ҽ��Դϴ�.\n", n);
	}
	else
		printf("%d�� �Ҽ��� �ƴմϴ�.\n", n);
}