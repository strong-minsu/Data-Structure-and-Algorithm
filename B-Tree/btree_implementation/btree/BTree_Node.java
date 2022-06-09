package btree;

public class BTree_Node {
	int[] keys; // ����� keys
    int MinDeg; // B-tree�� Minimum degree
    BTree_Node[] children; // �ڽ� ���
    int num; // ��� Ű�� ����
    boolean isLeaf; // leaf����� �� true

    // Constructor
    public BTree_Node(int deg, boolean isLeaf){

        this.MinDeg = deg;
        this.isLeaf = isLeaf;
        this.keys = new int[2*this.MinDeg-1]; // ��忡�� �ִ� 2*MinDeg-1Ű ����
        this.children = new BTree_Node[2*this.MinDeg];
        this.num = 0;
    }

    // Ű�� ���ų� Ű���� ū ù ��° ��ġ �ε��� ã��
    public int findKey(int key){

        int idx = 0;
        // ���� ���� ���� 
        //1.idx == num, ��� �׸��� �� �� ��ĵ �ϴ� ���
        //2. IDX < num, Ű�� �߰ߵǾ��ų� Ű���� Ŭ ���
        while (idx < num && keys[idx] < key)
            ++idx;
        return idx;
    }

    //remove
    public void remove(int key){

        int idx = findKey(key);
        if (idx < num && keys[idx] == key){ // Find key
            if (isLeaf) // Ű�� leaf node�� �ִ� ���
                removeFromLeaf(idx);
            else // Ű�� leaf node�� ���� ���
                removeFromNonLeaf(idx);
        }
        else{
            if (isLeaf){ // ��尡 leaf node�� ��� ã�� node�� B-tree�� ���� 
            	//ã�� Ű�� ���ٴ� �޼��� ���
                System.out.printf("The key %d is does not exist in the tree\n",key);
                return;
            }

            // Otherwise, ������ Ű�� ��带 ��Ʈ�� �ϴ� sub_tree�� �ִ�

            //  flag�� ��Ʈ�� ����� ������ �ڽ��� sub_tree�� Ű�� �ִ��� ���θ� ��Ÿ��
            // idx == num, ��ü ��尡 �񱳵ǰ� �÷��װ� true��
            boolean flag = idx == num; 
            
            // ����� ���� ��尡 ���� ���� ������ ���� ��带 ä���
            if (children[idx].num < MinDeg) 
                fill(idx);

            //������ ���� ��尡 ���յ� ��� ���� ���� ���� ���յǾ�� �ϹǷ� (idx-1) ���� ��忡�� �ٽ� �߻�
            //�ƴϸ�, �ּ� ������ Ű�� �ִ� (idx) �ڽ� ���� ���ư�
            if (flag && idx > num)
                children[idx-1].remove(key);
            else
                children[idx].remove(key);
        }
    }

    public void removeFromLeaf(int idx){

        // idx �̵�
        for (int i = idx +1;i < num;++i)
            keys[i-1] = keys[i];
        num --;
    }

    public void removeFromNonLeaf(int idx){

        int key = keys[idx];

        // Ű ���� ���� Ʈ��(children[idx])�� ��� t���� Ű�� �ִ� ���
        // �׷� ���� ���� Ʈ������ ���� [idx]�� ��Ʈ�� �ϴ� Ű�� '����'�� ã��
        // Ű�� 'pred'�� ��ü�ϰ� ���� �׸񿡼� pred�� ��������� ����
        if (children[idx].num >= MinDeg){
            int pred = getPred(idx);
            keys[idx] = pred;
            children[idx].remove(pred);
        }
        // ���� [idx]�� Ű�� MinDeg���� ���� ��� ���� [idx+1]�� Ȯ��
        // ���� Ʈ��(��Ʈ�� ������ [idx+1]�� �ּ� MinDeg Ű�� �ִ� ���
        // Ű�� �ļ� 'succ'�� ã�� suckin children[idx+1]��(��) �ݺ������� ����
        else if (children[idx+1].num >= MinDeg){
            int succ = getSucc(idx);
            keys[idx] = succ;
            children[idx+1].remove(succ);
        }
        else{
            // ���� [idx] �� ���� [idx+1]�� Ű ���� MinDeg���� ���� ���
            // �׷� ���� Ű�� ���� [idx+1]�� ���� [idx]�� ����
            // ���� [idx]�� 2t-1 Ű ����
            // ���� �׸�[idx+1]�� �����ϰ� ���� �׸�[idx]�� Ű�� �ݺ������� ����
            merge(idx);
            children[idx].remove(key);
        }
    }

    // ���� ���� �׻� ���� sub_tree���� ���� ������ ��带 ã�� ���
    public int getPred(int idx){ 

        // ���� ��忡 ������ ������ �� ������ ���� �̵�
    	BTree_Node cur = children[idx];
        while (!cur.isLeaf)
            cur = cur.children[cur.num];
        return cur.keys[cur.num-1];
    }

    // ������ ���� Ʈ������ ���� ������ �ļ� ��尡 ����
    public int getSucc(int idx){ 

        // ���� ��忡 ������ ������ ���� ���[idx+1]���� ���� ���� ��带 ��� �̵�
    	BTree_Node cur = children[idx+1];
        while (!cur.isLeaf)
            cur = cur.children[0];
        return cur.keys[0];
    }

    // MinDeg Ű���� ���� ���� [idx]�� ä���
    public void fill(int idx){

        // ���� ���� ��忡 ���� ���� MinDeg-1 Ű�� �ִ� ��� �ش� Ű���� ����
        if (idx != 0 && children[idx-1].num >= MinDeg)
            borrowFromPrev(idx);
        // ������ ���� ���� ���� ���� MinDeg-1 Ű�� ������ ������, ���⼭ ������ �� ����
        else if (idx != num && children[idx+1].num >= MinDeg)
            borrowFromNext(idx);
        else{
            // ���� ���[idx]�� ������ ���� ����� ���  ���� �׸�[idx] �� �ش� ���� ����
            // �״��� ���� ���� ���� �����ϰų� ���� ���� ���� ����
            if (idx != num)
                merge(idx);
            else
                merge(idx-1);
        }
    }

    // ���� �׸�[idx-1]���� Ű�� ���� ���� �׸�[idx]�� ����
    public void borrowFromPrev(int idx){

    	BTree_Node child = children[idx];
    	BTree_Node sibling = children[idx-1];

        // ���� [idx-1]�� ������ Ű�� ���� ���� �����÷���
        // ���� ����� [idx-1] ����÷� Ű�� ���� [idx]�� ù ��° Ű�� ����
        // ���� �����ڸŴ� 1�� �����ϰ� �ڳ�� 1�� ����
        for (int i = child.num-1; i >= 0; --i) // ���� [idx-1] ������ 
            child.keys[i+1] = child.keys[i];

        if (!child.isLeaf){ // children[idx] leaf�ƴҶ� ������
            for (int i = child.num; i >= 0; --i)
                child.children[i+1] = child.children[i];
        }

        // ���� ����� ù ��° Ű�� ���� ��� [idx-1]�� Ű�� ����
        child.keys[0] = keys[idx-1];
        if (!child.isLeaf) // Take the last child of sibling as the first child of children[idx]
            child.children[0] = sibling.children[sibling.num];

        // �����ڸ��� ������ ���̸� ù ��° �ڽ�[idx]���� ������
        keys[idx-1] = sibling.keys[sibling.num-1];
        child.num += 1;
        sibling.num -= 1;
    }

    // borrowFromPrev�Լ��� ��Ī�� �̷�
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

    // ���� �׸�[idx+1]��(��) ���� �׸�[idx]�� ����
    public void merge(int idx){

    	BTree_Node child = children[idx];
    	BTree_Node sibling = children[idx+1];

        // ���� ����� ������ Ű�� ���� ����� MinDeg-1 ��ġ�� ����
        child.keys[MinDeg-1] = keys[idx];

        // Ű: children[idx]�� [idx+1] copy
        for (int i =0 ; i< sibling.num; ++i)
            child.keys[i+MinDeg] = sibling.keys[i];

        // �ڽ� ���: children[idx]�� [idx+1] copy
        if (!child.isLeaf){
            for (int i = 0;i <= sibling.num; ++i)
                child.children[i+MinDeg] = sibling.children[i];
        }

        // Ű[idx]�� ���� [idx]�� �̵��Ͽ� �߻��ϴ� ������ �ƴ� Ű ������ �̵�
        for (int i = idx+1; i<num; ++i)
            keys[i-1] = keys[i];
        // �ش� ���� ��带 ������ �̵�
        for (int i = idx+2;i<=num;++i)
            children[i-1] = children[i];

        child.num += sibling.num + 1;
        num--;
    }


    public void insertNotFull(int key){

        int i = num -1; // i�� �� ������ �ε����� �ʱ�ȭ

        if (isLeaf){ // ���� ����� ���
            //�� Ű�� ������ ��ġ ã��
            while (i >= 0 && keys[i] > key){
            	//Ű �ڷ�
                keys[i+1] = keys[i]; 
                i--;
            }
            keys[i+1] = key;
            num = num +1;
        }
        else{
            // �����ؾ� �ϴ� ���� ��� ��ġ ã��
            while (i >= 0 && keys[i] > key)
                i--;
            //���� ��尡 �� á�� ��
            if (children[i+1].num == 2*MinDeg - 1){ 
                splitChild(i+1,children[i+1]);
                // ���� �� ���� ��� �߰��� �ִ� Ű�� ���� �̵��ϰ� ���� ��尡 �� ���� ����
                if (keys[i+1] < key)
                    i++;
            }
            children[i+1].insertNotFull(key);
        }
    }


    public void splitChild(int i ,BTree_Node y){

        // ���� Y�� MinDeg-1 Ű�� ������ ��带 ����
    	BTree_Node z = new BTree_Node(y.MinDeg,y.isLeaf);
        z.num = MinDeg - 1;

        // y�� �Ӽ��� z�� �����մϴ�.
        for (int j = 0; j < MinDeg-1; j++)
            z.keys[j] = y.keys[j+MinDeg];
        if (!y.isLeaf){
            for (int j = 0; j < MinDeg; j++)
                z.children[j] = y.children[j+MinDeg];
        }
        y.num = MinDeg-1;

        // ���� �׸� �� ���� �׸� ����
        for (int j = num; j >= i+1; j--)
            children[j+1] = children[j];
        children[i+1] = z;

        // y�� Ű�� �̵�
        for (int j = num-1;j >= i;j--)
            keys[j+1] = keys[j];
        keys[i] = y.keys[MinDeg-1];

        num = num + 1;
    }

    //Ʈ�� ��� travers �Լ�
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

    //Ʈ�� �˻�
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

