import java.util.Arrays;

class BubbleSort {
    public int[] bubbleSort(int[] A, int n) {
        // write code here
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (A[j] > A[j + 1]) {
                    int t = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = t;
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BubbleSort().bubbleSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class SelectionSort {
    public int[] selectionSort(int[] A, int n) {
        // write code here
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            int t = A[min];
            A[min] = A[i];
            A[i] = t;
        }
        return A;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SelectionSort().selectionSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class InsertionSort {
    public int[] insertionSort(int[] A, int n) {
        // write code here
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            int t = A[i];
            while (j >= 0 && A[j] >= t) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = t;
//            System.out.println(Arrays.toString(A));
        }
        return A;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new InsertionSort().insertionSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class MergeSort {
    public int[] mergeSort(int[] A, int n) {
        // write code here
        merge(A, 0, n - 1);
        return A;
    }

    private void merge(int[] A, int left, int right) {
        if (right - left <= 0) return;
        int mid = (left + right) >> 1;
        merge(A, left, mid);
        merge(A, mid + 1, right);
        int i = left, j = mid + 1;
        int[] B = new int[right - left + 1];
        int cnt = 0;
        while (i <= mid && j <= right) {
            if (A[i] < A[j]) {
                B[cnt++] = A[i++];
            } else {
                B[cnt++] = A[j++];
            }
        }
        while (i <= mid) {
            B[cnt++] = A[i++];
        }
        while (j <= right) {
            B[cnt++] = A[j++];
        }
        for (i = 0; i < B.length; i++) {
            A[left + i] = B[i];
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MergeSort().mergeSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class QuickSort {
    public int[] quickSort(int[] A, int n) {
        // write code here
        qSort(A, 0, n - 1);
        return A;
    }

    private void qSort(int[] A, int left, int right) {
        if (right - left <= 0) return;
        int pos = partition(A, left, right);
        qSort(A, left, pos - 1);
        qSort(A, pos + 1, right);
    }

    private int partition(int[] A, int left, int right) {
        int i = left;//第一个大于A[right]的数
        for (int j = left; j < right; j++) {
            if (A[j] < A[right]) {
                int t = A[j];
                A[j] = A[i];
                A[i] = t;
                i++;
            }
        }
        int t = A[right];
        A[right] = A[i];
        A[i] = t;
        return i;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new QuickSort().quickSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class HeapSort {
    /*
    堆排序是一种树形选择排序方法，它的特点是：在排序的过程中，将array[0，...，n-1]看成是一颗完全二叉树的顺序存储结构，利用完全二叉树中双亲节点和孩子结点之间的内在关系，在当前无序区中选择关键字最大（最小）的元素。

    1. 若array[0，...，n-1]表示一颗完全二叉树的顺序存储模式，则双亲节点指针和孩子结点指针之间的内在关系如下：

    　　任意一节点指针 i：父节点：i==0 ? null : (i-1)/2

    　　　　　　　　　　  左孩子：2*i + 1

    　　　　　　　　　　  右孩子：2*i + 2

    2. 堆的定义：n个关键字序列array[0，...，n-1]，当且仅当满足下列要求：(0 <= i <= (n-1)/2)

    　　　　　　① array[i] <= array[2*i + 1] 且 array[i] <= array[2*i + 2]； 称为小根堆；

    　　　　　　② array[i] >= array[2*i + 1] 且 array[i] >= array[2*i + 2]； 称为大根堆；

    3. 建立大根堆：

    　　n个节点的完全二叉树array[0，...，n-1]，最后一个节点n-1是第(n-1-1)/2个节点的孩子。对第(n-1-1)/2个节点为根的子树调整，使该子树称为堆。

    　　对于大根堆，调整方法为：若【根节点的关键字】小于【左右子女中关键字较大者】，则交换。

    　　之后向前依次对各节点（(n-2)/2 - 1）~ 0为根的子树进行调整，看该节点值是否大于其左右子节点的值，若不是，将左右子节点中较大值与之交换，交换后可能会破坏下一级堆，于是继续采用上述方法构建下一级的堆，直到以该节点为根的子树构成堆为止。

    　　反复利用上述调整堆的方法建堆，直到根节点。

    4.堆排序：（大根堆）

    　　①将存放在array[0，...，n-1]中的n个元素建成初始堆；

    　　②将堆顶元素与堆底元素进行交换，则序列的最大值即已放到正确的位置；

    　　③但此时堆被破坏，将堆顶元素向下调整使其继续保持大根堆的性质，再重复第②③步，直到堆中仅剩下一个元素为止。

    堆排序算法的性能分析：

    　　空间复杂度:o(1)；

    　　时间复杂度:建堆：o(n)，每次调整o(log n)，故最好、最坏、平均情况下：o(n*logn);

    　　稳定性：不稳定


     */
    public int[] heapSort(int[] A, int n) {
        // write code here
        buildMaxHeap(A);
        for (int i = A.length - 1; i >= 1; i--) {
            int t = A[0];
            A[0] = A[i];
            A[i] = t;
            adjustDownToUp(A, 0, i);
        }
        return A;
    }

    private void buildMaxHeap(int[] A) {
        for (int i = (A.length - 2) / 2; i >= 0; i--) {
            adjustDownToUp(A, i, A.length);
        }
    }

    private void adjustDownToUp(int[] A, int k, int length) {
        int t = A[k];
        for (int i = 2 * k + 1; i < length; i = 2 * i + 1) {
            if (i < length - 1 && A[i] < A[i + 1]) {
                i++;
            }
            if (t >= A[i]) {
                break;
            }
            A[k] = A[i];
            k = i;
        }
        A[k] = t;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HeapSort().heapSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class ShellSort {
    public int[] shellSort(int[] A, int n) {
        // write code here
        for (int i = n / 2; i >= 1; i /= 2) {
            insertSort(A, i);
        }
        return A;
    }

    private void insertSort(int[] A, int step) {
        for (int i = step; i < A.length; i++) {
            int t = A[i];
            int j = i - step;
            while (j >= 0 && A[j] >= t) {
                A[j + step] = A[j];
                j -= step;
            }
            j += step;
            A[j] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ShellSort().shellSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class CountingSort {
    public int[] countingSort(int[] A, int n) {
        // write code here
        int max = A[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, A[i]);
        }
        int[] c = new int[max + 1];
        Arrays.fill(c, 0);
        for (int i = 0; i < n; i++) {
            c[A[i]]++;
        }
        int cnt = 0;
        for (int i = 0; i <= max; i++) {
            while (c[i] > 0) {
                A[cnt++] = i;
                c[i]--;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountingSort().countingSort(new int[]{1, 2, 3, 5, 2, 3}, 6)));
    }
}

class RadixSort {
    public int[] radixSort(int[] A, int n) {
        // write code here
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, Integer.toString(A[i]).length());
        }
        int [][]r = new int[10][A.length+1];
        initArray(r);
        for(int i = 0; i < max; i++){
            sortByBit(r,A, i);
            initArray(r);
        }
        return A;
    }

    private void initArray(int[][] r) {
        for (int i = 0; i < 10; i++){
            r[i][0] = 0;
            for (int j = 1; j < r[i].length; j++){
                r[i][j] = -1;
            }
        }
    }

    private void sortByBit(int [][]r, int []A, int t) {//按照第t位，将数组A进行基数排序放入r中，然后再放回A中
        for(int i = 0; i < A.length; i++){
            int v = getValueAt(A[i], t);
            int cnt = r[v][0];
            r[v][++cnt] = A[i];
            r[v][0] = cnt;
        }
        int k = 0;
        for (int i = 0; i < 10; i++){
            for(int j = 1; j <= r[i][0]; j++){
                A[k++] = r[i][j];
            }
        }
    }

    private int getValueAt(int value, int t) {//得到整数value的第t位（从右数，自0开始）。如356的第1位是5
        for(int i = 0; i < t; i++){
            value /= 10;
        }
        return value % 10;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RadixSort().radixSort(new int[]{54,35,48,36,27,12,44,44,8,14,26,17,28},13)));
    }
}

class ScaleSort {
    /*
    已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序。

给定一个int数组A，同时给定A的大小n和题意中的k，请返回排序后的数组。

测试样例：
[2,1,4,3,6,5,8,7,10,9],10,2
返回：[1,2,3,4,5,6,7,8,9,10]
     */
    public int[] sortElement(int[] A, int n, int k) {
        // write code here
        buildHeap(A, k);
        int []ret = new int[n];
        int cnt = 0;
        for(int i = k; i < n; i++){
            ret[cnt++] = A[0]; A[0] = A[i];
            adjustDownToUp(A, 0, k);
        }
        for (int i = k-1; i > 0; i--){
            ret[cnt++] = A[0];
            A[0] = A[i];
            adjustDownToUp(A, 0, i);
        }
        ret[cnt++] = A[0];
        return ret;
    }

    private void buildHeap(int[] A, int k) {
        for(int i = (k-2)/2; i >= 0; i--){
            adjustDownToUp(A, i, k);
        }
    }

    private void adjustDownToUp(int[] A, int root, int k) {
        int temp = A[root];

        for (int i = 2 * root + 1; i < k; i = 2 * i + 1){
            if(i+1<k && A[i] > A[i+1]){
                i++;
            }
            if(temp < A[i]){
                break;
            }else {
                A[root] = A[i];
                root = i;
            }
        }
        A[root] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ScaleSort().sortElement(new int[]{3,5,4,1,2,8,7,6},8,5)));
    }
}

class Checker {
    public boolean checkDuplicate(int[] a, int n) {
        // write code here
        buildHeap(a);
        for (int i = a.length - 1; i >= 0; i--){
            int t = a[0];
            a[0] = a[i];
            a[i] = t;
            adjustDownToUp(a, 0, i);
        }
        for (int i = 1; i < n; i++){
            if(a[i] == a[i-1]){
                return true;
            }
        }
        return false;
    }

    private void buildHeap(int[] a) {
        for (int i = (a.length-2)/2; i >=0; i--){
            adjustDownToUp(a, i, a.length);
        }
    }

    private void adjustDownToUp(int[] a, int root, int length) {
        int t = a[root];
        for (int i = root * 2 + 1; i < length; i = 2 * i + 1){
            if(i + 1 < length && a[i] < a[i+1]){
                i++;
            }
            if(a[i] <= t){
                break;
            }
            a[root] = a[i];
            root = i;
        }
        a[root] = t;
    }

    public static void main(String[] args) {
        System.out.println((new Checker().checkDuplicate(new int[]{1,2,3,4,5,5,6},7)));
    }
}

class Merge {
    public int[] mergeAB(int[] A, int[] B, int n, int m) {
        // write code here
        int i = n-1, j = m - 1, k = n+m-1;
        while (j >= 0){
            if(i >= 0 && A[i] > B[j]){
                A[k--] = A[i--];
            }else{
                A[k--] = B[j--];
            }
        }
        return A;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Merge().mergeAB(new int[]{2,4,6,0,0,0},new int[]{1,3,5},3,3)));
    }
}

class ThreeColor {
    public int[] sortThreeColor(int[] A, int n) {
        // write code here
        int i = 0, j = 0, k = n-1;
        while (i <= k){
            if(A[i] == 0){
                int t = A[j]; A[j] = A[i]; A[i] = t;
                i++;j++;
            }
            else if(A[i] == 2){
                int t = A[k]; A[k] = A[i]; A[i] = t;
                k--;
            }else{
                i++;
            }
        }
        return A;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ThreeColor().sortThreeColor(new int[]{1,2,0,2},4)));
    }
}

class Finder {
    public boolean findX(int[][] mat, int n, int m, int x) {
        // write code here
        int i = 0, j = m - 1;
        while (i >=0 && j >= 0){
            if(mat[i][j] > x){
                j--;
            }
            else if(mat[i][j] < x){
                i++;
            }
            else return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int [][]a = new int[][]{{109,204,260,270},{452,602,671,917},{1106,1343,1467,1585},{1627,1866,1948,1980},{2108,2164,2295,2577},{2681,2749,2915,3026},{3187,3250,3465,3518},{3562,3773,3966,4101}};
        for (int i = 0; i < a.length; i++){
            System.out.println(Arrays.toString(a[i]));
        }
        System.out.println(new Finder().findX(a, 8,4,2108));
    }
}

class Subsequence {
    public int shortestSubsequence(int[] A, int n) {
        // write code here
        int max= 0, min = n-1, maxRight = 0, minLeft = 0;
        for (int i = 1; i < n; i++){
            if(A[i] > A[max]){
                max = i;
            }
            if(A[i] < A[max]){
                maxRight = i;
            }
        }
        for (int i = n-2; i >= 0; i--){
            if(A[i] < A[min]){
                min = i;
            }
            if(A[i] > A[min]){
                minLeft = i;
            }
        }
        if (maxRight == 0 && minLeft == 0)return 0;
        return maxRight - minLeft + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Subsequence().shortestSubsequence(new int[]{1,4,6,5,9,10}, 6));
    }
}

class Gap {
    static class ZJ{
        int min = -1, max = -1;
        public ZJ(int min, int max){
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return "ZJ{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
    public int maxGap(int[] A, int n) {
        // write code here
        int max = A[0], min = A[0];
        for (int i = 0; i < n; i++){
            max = Math.max(max, A[i]);
            min = Math.min(min, A[i]);
        }
        ZJ[]b = new ZJ[n+1];
        b[n] = new ZJ(max, max);
        double len = (max-min) *1.0/ n;
        for (int i = 0; i < n; i++){
            double t = (A[i] - min)/len;
            int pos=(int)t;
            if(b[pos] == null){
                b[pos] = new ZJ(A[i], A[i]);
            }else{
                b[pos].min = Math.min(b[pos].min, A[i]);
                b[pos].max = Math.max(b[pos].max, A[i]);
            }
        }
        int dif = 0;
        int last = -1;
        for (int i = 0; i <= n; i++){
            if (b[i]!=null){
                if(last!=-1){
                    dif = Math.max(b[i].min - last, dif);
                }
                last = b[i].max;
            }
        }
        return dif;
    }

    public static void main(String[] args) {
        System.out.println(new Gap().maxGap(new int[]{4,2,5,4,6},5));
    }
}