import java.util.Arrays;
import java.util.Vector;

class LessIndexBinarySearch {
    /*
    定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，如果arr[0]<arr[1]，那么arr[0]是局部最小；如果arr[N-1]<arr[N-2]，那么arr[N-1]是局部最小；如果0<i<N-1，既有arr[i]<arr[i-1]又有arr[i]<arr[i+1]，那么arr[i]是局部最小。 给定无序数组arr，已知arr中任意两个相邻的数都不相等，写一个函数，只需返回arr中任意一个局部最小出现的位置即可。
     */
    public int getLessIndex(int[] arr) {
        if (arr.length<=0)return -1;
        if(arr.length == 1)return 0;
        if (arr[0] < arr[1])return 0;
        int n = arr.length;
        if (arr[n-1] < arr[n-2])return n-1;
        int left = 0, right = n-1, mid,res=-1;
        while(right-left>1){
            mid = (left+right)>>1;
            if(arr[mid] < arr[mid-1] && arr[mid]<arr[mid+1]){
                res = mid;
                break;
            }
            if(arr[mid] > arr[mid-1]){
                right = mid;
            }else{
                left = mid;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LessIndexBinarySearch().getLessIndex(new int[]{3,5,2,4}));
    }
}

class LeftMostAppearance {
    /*
    对于一个有序数组arr，再给定一个整数num，请在arr中找到num这个数出现的最左边的位置。

给定一个数组arr及它的大小n，同时给定num。请返回所求位置。若该元素在数组中未出现，请返回-1。

测试样例：
[1,2,3,3,4],5,3
返回：2
     */
    public int findPos(int[] arr, int n, int num) {
        int left = 0, right = arr.length-1;
        int mid,res = -1;
        while (right-left>0){
            mid = (left+right)>>1;
            if (arr[mid]<num){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        if (arr[left]==num){
            res = left;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LeftMostAppearance().findPos(new int[]{1,2,4,4,4},5,3));
    }
}

class MinValue {
    /*
    对于一个有序循环数组arr，返回arr中的最小值。有序循环数组是指，有序数组左边任意长度的部分放到右边去，右边的部分拿到左边来。比如数组[1,2,3,3,4]，是有序循环数组，[4,1,2,3,3]也是。

给定数组arr及它的大小n，请返回最小值。

测试样例：
[4,1,2,3,3],5
返回：1
     */
    public int getMin(int[] arr, int n) {
        if (arr.length<=0)return -1;
        if (arr.length<=1)return 0;
        int left = 0, right = arr.length-1, res = -1, mid;
        while (right-left>0){
            mid = (left+right)>>1;
            if(arr[mid]<arr[right]){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return arr[left];
    }

    public static void main(String[] args) {
        System.out.println(new MinValue().getMin(new int[]{5,1,2,3,4},5));
    }
}
class Find {
    /*
    有一个有序数组arr，其中不含有重复元素，请找到满足arr[i]==i条件的最左的位置。如果所有位置上的数都不满足条件，返回-1。

给定有序数组arr及它的大小n，请返回所求值。

测试样例：
[-1,0,2,3],4
返回：2
     */
    public int findPos(int[] arr, int n) {
        if (arr.length<=0)return -1;
        if(arr[0]>n-1||arr[n-1]<0)return -1;
        int left = 0, right = n-1, res = -1, mid;
        while (right-left>=0){
            mid = (left+right)>>1;
            if (arr[mid]==mid){
                res = mid;
            }
            if (arr[mid]>= mid){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Find().findPos(new int[]{-5,-2,0,1,4}, 5));
    }
}

class CountNodes {
    static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
    /*
    给定一棵完全二叉树的根节点root，返回这棵树的节点个数。如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。

给定树的根结点root，请返回树的大小。
     */
    public int count(TreeNode root) {
        if (root==null)return 0;
        if (root.left==null)return 1;
        int leftCount, rightCount, rootCount, sum;
        int height = 1;
        TreeNode cur = root.left;
        while (cur.left!=null){
            cur = cur.left;
            height++;
        }
        int height2 = 1;
        if (root.right==null)return 1 + count(root.left);
        cur = root.right;
        while (cur.left!=null){
            cur = cur.left;
            height2++;
        }
        if(height==height2){
            leftCount = (1 << height)-1;
            rightCount = count(root.right);
        }else{
            leftCount = count(root.left);
            rightCount = (1<<height2)-1;
        }
        rootCount = 1;
        sum = leftCount+rightCount+rootCount;
        return sum;
    }

    public static void main(String[] args) {
        CountNodes c = new CountNodes();
        TreeNode[]nodes = new TreeNode[5];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = new TreeNode(i+1);
        }
        for (int i = 0; i < nodes.length; i++){
            if (2*i+1<nodes.length)
            nodes[i].left = nodes[2*i+1];
            if(2*i+2<nodes.length)
            nodes[i].right = nodes[2*i+2];
        }
        System.out.println(c.count(nodes[0]));
    }
}

class QuickPower {
    /*
    如果更快的求一个整数k的n次方。如果两个整数相乘并得到结果的时间复杂度为O(1)，得到整数k的N次方的过程请实现时间复杂度为O(logN)的方法。

给定k和n，请返回k的n次方，为了防止溢出，请返回结果Mod 1000000007的值。

测试样例：
2,3
返回：8
     */
    public int getPower(int k, int N) {
        int mod = 1000000007;
        Vector<Integer>vector = new Vector<>();
        while (N>0){
            vector.add(N % 2);
            N/=2;
        }
        Vector<Integer>a = new Vector<>();
        a.add(k);
        int l = vector.size();
        for (int i = 1; i <= l; i++){
            int t = (int)(1L*a.lastElement() * a.lastElement() % mod);
            a.add(t);
        }
//        System.err.println("a.size="+a.size());
//        System.err.println("v.size="+vector.size());
//        System.err.println(a.toString());
//        System.err.println(vector.toString());
        int res = 1;
        for (int i = 0; i < vector.size(); i++){
            if (vector.get(i)==1){
                res = (int)(1L*a.get(i) * res % mod);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new QuickPower().getPower(2,14876069));
    }
}