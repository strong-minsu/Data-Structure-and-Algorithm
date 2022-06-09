package btree;
import btree.BTree_Node;
import btree.B_tree;
import java.util.*;

public class BTree {
	public static void main(String[] args) {
		//과제 조건: 시간 측정
		long startTime = System.nanoTime();
		int degree = 5;
		//int degree = 7;
		//int degree = 9;
		
		B_tree t = new B_tree(degree); // B-Tree with minimum degree 5, 7, 9 위 주석부분에서 선택 (입력받기 귀찮....
        System.out.println(String.format("B-Tree minimum degree is %d", degree));


		int [] rand_array = new int[1000];
		//랜덤 숫자 생성
		Random rand = new Random();
		int insert_number;
		int count_i = 0;
		
		//과제 조건: 0~9999까지의 수중 랜덤으로 배열에 넣기 -> remove시 사용 
		//입력자료는 중복될 수 없다.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//배열에 들어가는 값을 트리의 insert값으로 넣기
				t.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        System.out.println();

		System.out.println("Traversal of tree after insert");
        t.traverse();
        System.out.println();
        
		int remove_index = 0;
		int count_r = 0;
		
		//과제 조건: 500개 항목 지우기
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500개의 항목를 삭제해야하므로, 겹치는 랜덤 수가 없도록 조건문 실행
			if(rand_array[remove_index] != -1) {
				t.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
        System.out.println();

		System.out.println("Traversal of tree after remove");
        t.traverse();
        System.out.println();
        
        System.out.println();
        long endTime = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime-startTime));

	}

}
