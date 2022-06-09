package btree;

public class BTree_Node {
	int[] keys; // 노드의 keys
    int MinDeg; // B-tree의 Minimum degree
    BTree_Node[] children; // 자식 노드
    int num; // 노드 키의 개수
    boolean isLeaf; // leaf노드일 때 true

    // Constructor
    public BTree_Node(int deg, boolean isLeaf){

        this.MinDeg = deg;
        this.isLeaf = isLeaf;
        this.keys = new int[2*this.MinDeg-1]; // 노드에는 최대 2*MinDeg-1키 있음
        this.children = new BTree_Node[2*this.MinDeg];
        this.num = 0;
    }

    // 키와 같거나 키보다 큰 첫 번째 위치 인덱스 찾기
    public int findKey(int key){

        int idx = 0;
        // 루프 종료 조건 
        //1.idx == num, 모든 항목을 한 번 스캔 하는 경우
        //2. IDX < num, 키가 발견되었거나 키보다 클 경우
        while (idx < num && keys[idx] < key)
            ++idx;
        return idx;
    }

    //remove
    public void remove(int key){

        int idx = findKey(key);
        if (idx < num && keys[idx] == key){ // Find key
            if (isLeaf) // 키가 leaf node에 있는 경우
                removeFromLeaf(idx);
            else // 키가 leaf node에 없는 경우
                removeFromNonLeaf(idx);
        }
        else{
            if (isLeaf){ // 노드가 leaf node인 경우 찾는 node가 B-tree에 없음 
            	//찾는 키가 없다는 메세지 출력
                System.out.printf("The key %d is does not exist in the tree\n",key);
                return;
            }

            // Otherwise, 삭제할 키가 노드를 루트로 하는 sub_tree에 있다

            //  flag는 루트가 노드의 마지막 자식인 sub_tree에 키가 있는지 여부를 나타냄
            // idx == num, 전체 노드가 비교되고 플래그가 true로
            boolean flag = idx == num; 
            
            // 노드의 하위 노드가 가득 차지 않으면 먼저 노드를 채운다
            if (children[idx].num < MinDeg) 
                fill(idx);

            //마지막 하위 노드가 병합된 경우 이전 하위 노드와 병합되어야 하므로 (idx-1) 하위 노드에서 다시 발생
            //아니면, 최소 차수의 키가 있는 (idx) 자식 노드로 돌아감
            if (flag && idx > num)
                children[idx-1].remove(key);
            else
                children[idx].remove(key);
        }
    }

    public void removeFromLeaf(int idx){

        // idx 이동
        for (int i = idx +1;i < num;++i)
            keys[i-1] = keys[i];
        num --;
    }

    public void removeFromNonLeaf(int idx){

        int key = keys[idx];

        // 키 앞의 하위 트리(children[idx])에 적어도 t개의 키가 있는 경우
        // 그런 다음 하위 트리에서 하위 [idx]를 루트로 하는 키의 '사전'을 찾음
        // 키를 'pred'로 대체하고 하위 항목에서 pred를 재귀적으로 삭제
        if (children[idx].num >= MinDeg){
            int pred = getPred(idx);
            keys[idx] = pred;
            children[idx].remove(pred);
        }
        // 하위 [idx]의 키가 MinDeg보다 적은 경우 하위 [idx+1]를 확인
        // 하위 트리(루트가 하위인 [idx+1]에 최소 MinDeg 키가 있는 경우
        // 키의 후속 'succ'를 찾아 suckin children[idx+1]을(를) 반복적으로 삭제
        else if (children[idx+1].num >= MinDeg){
            int succ = getSucc(idx);
            keys[idx] = succ;
            children[idx+1].remove(succ);
        }
        else{
            // 하위 [idx] 및 하위 [idx+1]의 키 수가 MinDeg보다 작은 경우
            // 그런 다음 키와 하위 [idx+1]가 하위 [idx]로 결합
            // 하위 [idx]에 2t-1 키 포함
            // 하위 항목[idx+1]을 해제하고 하위 항목[idx]의 키를 반복적으로 삭제
            merge(idx);
            children[idx].remove(key);
        }
    }

    // 이전 노드는 항상 왼쪽 sub_tree에서 가장 오른쪽 노드를 찾는 노드
    public int getPred(int idx){ 

        // 리프 노드에 도달할 때까지 맨 오른쪽 노드로 이동
    	BTree_Node cur = children[idx];
        while (!cur.isLeaf)
            cur = cur.children[cur.num];
        return cur.keys[cur.num-1];
    }

    // 오른쪽 하위 트리에서 왼쪽 끝까지 후속 노드가 있음
    public int getSucc(int idx){ 

        // 리프 노드에 도달할 때까지 하위 노드[idx+1]에서 가장 왼쪽 노드를 계속 이동
    	BTree_Node cur = children[idx+1];
        while (!cur.isLeaf)
            cur = cur.children[0];
        return cur.keys[0];
    }

    // MinDeg 키보다 작은 하위 [idx]로 채우기
    public void fill(int idx){

        // 이전 하위 노드에 여러 개의 MinDeg-1 키가 있는 경우 해당 키에서 차용
        if (idx != 0 && children[idx-1].num >= MinDeg)
            borrowFromPrev(idx);
        // 후자의 하위 노드는 여러 개의 MinDeg-1 키를 가지고 있으며, 여기서 차용할 수 있음
        else if (idx != num && children[idx+1].num >= MinDeg)
            borrowFromNext(idx);
        else{
            // 하위 노드[idx]가 마지막 하위 노드인 경우  하위 항목[idx] 및 해당 형제 병합
            // 그다음 이전 하위 노드와 병합하거나 다음 형제 노드와 병합
            if (idx != num)
                merge(idx);
            else
                merge(idx-1);
        }
    }

    // 하위 항목[idx-1]에서 키를 빌려 하위 항목[idx]에 삽입
    public void borrowFromPrev(int idx){

    	BTree_Node child = children[idx];
    	BTree_Node sibling = children[idx-1];

        // 하위 [idx-1]의 마지막 키가 상위 노드로 오버플로함
        // 상위 노드의 [idx-1] 언더플로 키가 하위 [idx]에 첫 번째 키로 삽입
        // 따라서 형제자매는 1명씩 감소하고 자녀는 1명씩 증가
        for (int i = child.num-1; i >= 0; --i) // 하위 [idx-1] 앞으로 
            child.keys[i+1] = child.keys[i];

        if (!child.isLeaf){ // children[idx] leaf아닐때 앞으로
            for (int i = child.num; i >= 0; --i)
                child.children[i+1] = child.children[i];
        }

        // 하위 노드의 첫 번째 키를 현재 노드 [idx-1]의 키로 설정
        child.keys[0] = keys[idx-1];
        if (!child.isLeaf) // Take the last child of sibling as the first child of children[idx]
            child.children[0] = sibling.children[sibling.num];

        // 형제자매의 마지막 아이를 첫 번째 자식[idx]으로 가져옴
        keys[idx-1] = sibling.keys[sibling.num-1];
        child.num += 1;
        sibling.num -= 1;
    }

    // borrowFromPrev함수와 대칭을 이룸
    public void borrowFromNext(int idx){

    	BTree_Node child = children[idx];
    	BTree_Node sibling = children[idx+1];

        child.keys[child.num] = keys[idx];

        if (!child.isLeaf)
            child.children[child.num+1] = sibling.children[0];

        keys[idx] = sibling.keys[0];

        for (int i = 1; i < sibling.num; ++i)
            sibling.keys[i-1] = sibling.keys[i];

        if (!sibling.isLeaf){
            for (int i= 1; i <= sibling.num;++i)
                sibling.children[i-1] = sibling.children[i];
        }
        child.num += 1;
        sibling.num -= 1;
    }

    // 하위 항목[idx+1]을(를) 하위 항목[idx]에 병합
    public void merge(int idx){

    	BTree_Node child = children[idx];
    	BTree_Node sibling = children[idx+1];

        // 현재 노드의 마지막 키를 하위 노드의 MinDeg-1 위치에 삽입
        child.keys[MinDeg-1] = keys[idx];

        // 키: children[idx]에 [idx+1] copy
        for (int i =0 ; i< sibling.num; ++i)
            child.keys[i+MinDeg] = sibling.keys[i];

        // 자식 노드: children[idx]에 [idx+1] copy
        if (!child.isLeaf){
            for (int i = 0;i <= sibling.num; ++i)
                child.children[i+MinDeg] = sibling.children[i];
        }

        // 키[idx]를 하위 [idx]로 이동하여 발생하는 간격이 아닌 키 앞으로 이동
        for (int i = idx+1; i<num; ++i)
            keys[i-1] = keys[i];
        // 해당 하위 노드를 앞으로 이동
        for (int i = idx+2;i<=num;++i)
            children[i-1] = children[i];

        child.num += sibling.num + 1;
        num--;
    }


    public void insertNotFull(int key){

        int i = num -1; // i를 맨 오른쪽 인덱스로 초기화

        if (isLeaf){ // 리프 노드인 경우
            //새 키를 삽입할 위치 찾기
            while (i >= 0 && keys[i] > key){
            	//키 뒤로
                keys[i+1] = keys[i]; 
                i--;
            }
            keys[i+1] = key;
            num = num +1;
        }
        else{
            // 삽입해야 하는 하위 노드 위치 찾기
            while (i >= 0 && keys[i] > key)
                i--;
            //하위 노드가 꽉 찼을 때
            if (children[i+1].num == 2*MinDeg - 1){ 
                splitChild(i+1,children[i+1]);
                // 분할 후 하위 노드 중간에 있는 키가 위로 이동하고 하위 노드가 두 개로 분할
                if (keys[i+1] < key)
                    i++;
            }
            children[i+1].insertNotFull(key);
        }
    }


    public void splitChild(int i ,BTree_Node y){

        // 먼저 Y의 MinDeg-1 키를 보유할 노드를 생성
    	BTree_Node z = new BTree_Node(y.MinDeg,y.isLeaf);
        z.num = MinDeg - 1;

        // y의 속성을 z에 전달합니다.
        for (int j = 0; j < MinDeg-1; j++)
            z.keys[j] = y.keys[j+MinDeg];
        if (!y.isLeaf){
            for (int j = 0; j < MinDeg; j++)
                z.children[j] = y.children[j+MinDeg];
        }
        y.num = MinDeg-1;

        // 하위 항목에 새 하위 항목 삽입
        for (int j = num; j >= i+1; j--)
            children[j+1] = children[j];
        children[i+1] = z;

        // y의 키를 이동
        for (int j = num-1;j >= i;j--)
            keys[j+1] = keys[j];
        keys[i] = y.keys[MinDeg-1];

        num = num + 1;
    }

    //트리 출력 travers 함수
    public void traverse(){
        int i;
        for (i = 0; i< num; i++){
            if (!isLeaf)
                children[i].traverse();
            System.out.printf(" %d",keys[i]);
        }
        
        if (!isLeaf){
            children[i].traverse();
        }

    }

    //트리 검색
    public BTree_Node search(int key){
        int i = 0;
        while (i < num && key > keys[i])
            i++;

        if (keys[i] == key)
            return this;
        if (isLeaf)
            return null;
        return children[i].search(key);
    }
}

