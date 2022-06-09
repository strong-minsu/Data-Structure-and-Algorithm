package btree;

//메인으로 작동함 (앞에서 구현한 B_tree(함수구현)class, BTree_Node(필요한 자료구조, 함수 구현)class) import하기
import btree.BTree_Node;
import btree.B_tree;
import java.util.*;

public class BTree {
	public static void main(String[] args) {
	
		int degree5 = 5;
		int degree7 = 7;
		int degree9 = 9;
		
		int [] rand_array = new int[1000];
		//랜덤 숫자 생성 -seed값 고정 실행시간 분석을 위해
		Random rand = new Random(10);
		
		int insert_number = 0;
		int count_i = 0;
		int remove_index = 0;
		int count_r = 0;
		
		B_tree t5 = new B_tree(degree5);
		B_tree t7 = new B_tree(degree7);
		B_tree t9 = new B_tree(degree9);// B-Tree with minimum degree 5, 7, 9 위 주석부분에서 선택 (입력받기 귀찮....
        System.out.println(String.format("B-Tree minimum degree is %d", degree5));
        
        //과제 조건: 시간 측정
      	long startTime5 = System.nanoTime();
		//과제 조건: 0~9999까지의 수중 랜덤으로 배열에 넣기 -> remove시 사용 
		//입력자료는 중복될 수 없다.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//배열에 들어가는 값을 트리의 insert값으로 넣기
				t5.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        //System.out.println();

		//System.out.println("Traversal of tree after insert");
        //t.traverse();
        //System.out.println();
		
		//과제 조건: 500개 항목 지우기
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500개의 항목를 삭제해야하므로, 겹치는 랜덤 수가 없도록 조건문 실행
			if(rand_array[remove_index] != -1) {
				t5.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
		//System.out.println();

		//System.out.println("Traversal of tree after remove");
        //t.traverse();
        //System.out.println();
        
        long endTime5 = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime5-startTime5));
        
        
        System.out.println();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //degree 7인 경우
        insert_number = 0;
		count_i = 0;
		remove_index = 0;
		count_r = 0;
        System.out.println(String.format("B-Tree minimum degree is %d", degree7));
        
        //과제 조건: 시간 측정
      	long startTime7 = System.nanoTime();
		//과제 조건: 0~9999까지의 수중 랜덤으로 배열에 넣기 -> remove시 사용 
		//입력자료는 중복될 수 없다.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//배열에 들어가는 값을 트리의 insert값으로 넣기
				t7.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        //System.out.println();

		//System.out.println("Traversal of tree after insert");
        //t.traverse();
        //System.out.println();
		
		//과제 조건: 500개 항목 지우기
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500개의 항목를 삭제해야하므로, 겹치는 랜덤 수가 없도록 조건문 실행
			if(rand_array[remove_index] != -1) {
				t7.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
		//System.out.println();

		//System.out.println("Traversal of tree after remove");
        //t.traverse();
        //System.out.println();
        
        long endTime7 = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime7-startTime7));
        
        
        System.out.println();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //degree 9인 경우
        insert_number = 0;
		count_i = 0;
		remove_index = 0;
		count_r = 0;
        System.out.println(String.format("B-Tree minimum degree is %d", degree9));
        
        //과제 조건: 시간 측정
      	long startTime9 = System.nanoTime();
		//과제 조건: 0~9999까지의 수중 랜덤으로 배열에 넣기 -> remove시 사용 
		//입력자료는 중복될 수 없다.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//배열에 들어가는 값을 트리의 insert값으로 넣기
				t9.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        //System.out.println();

		//System.out.println("Traversal of tree after insert");
        //t.traverse();
        //System.out.println();
		
		//과제 조건: 500개 항목 지우기
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500개의 항목를 삭제해야하므로, 겹치는 랜덤 수가 없도록 조건문 실행
			if(rand_array[remove_index] != -1) {
				t9.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
		//System.out.println();

		//System.out.println("Traversal of tree after remove");
        //t.traverse();
        //System.out.println();
        
        long endTime9 = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime9-startTime9));

	}

}
