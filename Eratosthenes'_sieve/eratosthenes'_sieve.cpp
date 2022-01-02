//입력받은 정수까지의 수 중에서 소수인 수를 출력하는 프로그램
// Eratosthenes' sieve
using namespace std;
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	int n;
	//수를 입력받음
	cout << "정수를 입력하시오: ";
	cin >> n;
	cout << "\n";

	//입력받은 수는 1보다 큰 정수여야 한다.
	if (n < 2) {
		printf("Error: 입력한 정수는 1보다 커야합니다.\n");
		return 0;
	}
	int* parray;

	//메모리 할당
	parray = new int[n + 1];	// [n]으로 설정시 array[0] ~ array[29]로 설정되기 때문에 n+1로 설정
	if (parray == 0) {
		printf("Error : 메모리 할당에 실패하였습니다.\n");
		return 0;
	}

	//메모리를 0으로 초기화 (소수가 아닌 수를 1로 변경)
	memset(parray, 0, sizeof(int) * (n + 1));

	int i, j;
	//소수 구하기 루프
	for (i = 2; i <= n; i++) {
		if (parray[i] == 1) // 이미 지워진 수 즉 소수가 아니면 다음 수로 넘어감
			continue;
		j = i;				// i를 기점으로
		while ((j += i) <= n) {
			parray[j] = 1;	//소수가 아닌 수로 처리하는 과정
		}
	}

	//소수의 출력
	for (i = 2; i <= n; i++) {
		if (parray[i] == 0)
			printf("%d ", i);
	}
	printf("\n");

	//할당된 메모리 해제
	delete[] parray;
	return 0;

}