import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    static TreeNode getBinaryTreeHeadByLength(int n){
        TreeNode[]nodes = new TreeNode[n];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = new TreeNode(i+1);
        }
        for (int i = 0; i < nodes.length; i++){
            if (2*i+1<nodes.length)
                nodes[i].left = nodes[2*i+1];
            if(2*i+2<nodes.length)
                nodes[i].right = nodes[2*i+2];
        }
        return nodes[0];
    }
}
class TreeToSequence {
    /*
    请用递归方式实现二叉树的先序、中序和后序的遍历打印。

给定一个二叉树的根结点root，请依次返回二叉树的先序，中序和后续遍历(二维数组的形式)。


     */
    Vector<Integer>v0 = new Vector<>(), v1 = new Vector<>(), v2 = new Vector<>();

    public int[][] convert(TreeNode root) {
        int[][]res = new int[3][];
        preOrderRecur(root);
        inOrderRecur(root);
        posOrderRecur(root);
        res[0] = new int[v0.size()];
        for (int i = 0; i < res[0].length; i++){
            res[0][i] = v0.get(i);
        }
        res[1] = new int[v1.size()];
        for (int i = 0; i < res[1].length; i++){
            res[1][i] = v1.get(i);
        }
        res[2] = new int[v2.size()];
        for (int i = 0; i < res[2].length; i++){
            res[2][i] = v2.get(i);
        }
        return res;
    }

    private void posOrderRecur(TreeNode root) {
        if (root==null)return;
        posOrderRecur(root.left);
        posOrderRecur(root.right);
        v2.add(root.val);
    }

    private void inOrderRecur(TreeNode root) {
        if (root==null)return;
        inOrderRecur(root.left);
        v1.add(root.val);
        inOrderRecur(root.right);
    }

    private void preOrderRecur(TreeNode root) {
        if(root==null)return;
        v0.add(root.val);
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }
}

class TreeToSequence2 {
    /*
    请用非递归方式实现二叉树的先序、中序和后序的遍历打印。
给定一个二叉树的根结点root，请依次返回二叉树的先序，中序和后续遍历(二维数组的形式)。
     */
    Vector<Integer>v0 = new Vector<>(), v1 = new Vector<>(), v2 = new Vector<>();
    public int[][] convert(TreeNode root) {
        Stack<TreeNode>stack = new Stack<>();
        TreeNode cur = root;
        stack.push(cur);
        while (stack.size()>0){
            cur = stack.pop();
            v0.add(cur.val);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if (cur.left!=null){
                stack.push(cur.left);
            }
        }
        cur = root;
        while (stack.size()>0|| cur!=null){
            if (cur!=null){
                stack.add(cur);
                cur = cur.left;
            }
            else{
                cur = stack.pop();
                v1.add(cur.val);
                cur = cur.right;
            }
        }
        stack.clear();
        Stack<TreeNode> stack2 = new Stack<>();
        cur = root;
        stack.push(cur);
        while (stack.size()>0){
            cur = stack.pop();
            stack2.push(cur);
            if(cur.left!=null){
                stack.push(cur.left);
            }
            if(cur.right!=null){
                stack.push(cur.right);
            }
        }
        while (stack2.size()>0){
            v2.add(stack2.pop().val);
        }
        int [][]res = new int[3][];
        res[0] = new int[v0.size()];
        for (int i = 0; i < res[0].length; i++){
            res[0][i] = v0.get(i);
        }
        res[1] = new int[v1.size()];
        for (int i = 0; i < res[1].length; i++){
            res[1][i] = v1.get(i);
        }
        res[2] = new int[v2.size()];
        for (int i = 0; i < res[2].length; i++){
            res[2][i] = v2.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][]res = new TreeToSequence2().convert(TreeNode.getBinaryTreeHeadByLength(7));
        for (int i = 0; i < 3; i++){
            System.out.println(Arrays.toString(res[i]));
        }
    }
}

class TreeToString {
    /*
    首先我们介绍二叉树先序序列化的方式，假设序列化的结果字符串为str，初始时str等于空字符串。先序遍历二叉树，如果遇到空节点，就在str的末尾加上“#!”，“#”表示这个节点为空，节点值不存在，当然你也可以用其他的特殊字符，“!”表示一个值的结束。如果遇到不为空的节点，假设节点值为3，就在str的末尾加上“3!”。现在请你实现树的先序序列化。

给定树的根结点root，请返回二叉树序列化后的字符串。
     */
    public String toString(TreeNode root) {
        if(root==null)return "#!";
        return root.val+"!"+toString(root.left)+toString(root.right);
    }
}
class CheckBalance {
    /*
    有一棵二叉树，请设计一个算法判断这棵二叉树是否为平衡二叉树。
给定二叉树的根结点root，请返回一个bool值，代表这棵树是否为平衡二叉树。
     */

    public boolean check(TreeNode root) {
        return checkBalance(root)>=0;
    }

    private int checkBalance(TreeNode root) {
        if (root==null){
            return 0;
        }
        int left = checkBalance(root.left);
        if (left < 0)return -1;
        int right = checkBalance(root.right);
        if(right < 0)return -1;
        if( Math.abs(left-right)<=1){
            return Math.max(left, right) + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode []nodes = new TreeNode[7];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = new TreeNode(i+1);
        }
        nodes[0].left = nodes[1]; nodes[0].right = nodes[2];
        nodes[1].left = nodes[3]; nodes[3].left = nodes[5];
        nodes[2].right = nodes[4]; nodes[4].right = nodes[6];

        System.out.println(new CheckBalance().check(TreeNode.getBinaryTreeHeadByLength(12)));
    }
}

class CheckCompletion {
    /*
    有一棵二叉树,请设计一个算法判断它是否是完全二叉树。

给定二叉树的根结点root，请返回一个bool值代表它是否为完全二叉树。树的结点个数小于等于500。
     */
    public boolean chk(TreeNode root) {
        if (root == null)return true;
        LinkedList<TreeNode>q = new LinkedList<>();
        q.add(root);
        boolean flag = false;
        while (q.size()>0){
            TreeNode cur = q.poll();
            if (flag){
                if (cur.left!=null || cur.right!=null){
                    return false;
                }
            }
            if (cur.left==null&&cur.right!=null)return false;
            if(cur.left!=null && cur.right!=null){
                q.add(cur.left);
                q.add(cur.right);
            }
            else{
                flag = true;
                if (cur.left!=null)q.add(cur.left);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CheckCompletion().chk(TreeNode.getBinaryTreeHeadByLength(7)));
    }
}
class FoldPaper {
    /*
    请把纸条竖着放在桌⼦上，然后从纸条的下边向上⽅对折，压出折痕后再展 开。此时有1条折痕，突起的⽅向指向纸条的背⾯，这条折痕叫做“下”折痕 ；突起的⽅向指向纸条正⾯的折痕叫做“上”折痕。如果每次都从下边向上⽅ 对折，对折N次。请从上到下计算出所有折痕的⽅向。

给定折的次数n,请返回从上到下的折痕的数组，若为下折痕则对应元素为"down",若为上折痕则为"up".

测试样例：
1
返回：["down"]
     */
    Vector<Integer>vector = new Vector<>();
    public String[] foldPaper(int n) {
        //1表示下，0表示上
        TreeNode[]nodes = new TreeNode[(1<<n)-1];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = new TreeNode(1);
        }
        for (int i = 0; i < nodes.length; i++){
            if (2*i+1<nodes.length){
                nodes[i].left = nodes[2*i+1];
                nodes[2*i+1].val = 1;
            }
            if (2*i+2<nodes.length){
                nodes[i].right = nodes[2*i+2];
                nodes[2*i+2].val = 0;
            }
        }
        getInOrderRecur(nodes[0]);
        String[]ret = new String[vector.size()];
        for (int i = 0 ; i < ret.length; i++){
            ret[i] = vector.get(i)==1?"down":"up";
        }
        return ret;
    }

    private void getInOrderRecur(TreeNode root) {
        if (root==null)return;
        getInOrderRecur(root.left);
        vector.add(root.val);
        getInOrderRecur(root.right);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FoldPaper().foldPaper(3)));
    }
}

class FindErrorNode {
    Vector<Integer>vector = new Vector<>();
    public int[] findError(TreeNode root) {
        getInOrderRecur(root);
        System.err.println(vector.toString());
        int[]res = new int[2];
        int cnt = 0;
        for (int i = 0; i < vector.size()-1; i++){
            if (vector.get(i)>vector.get(i+1)){
                cnt++;
                res[0] = vector.get(i+1);
                res[1] = vector.get(i);
            }
        }
        if (cnt>1){
            for (int i = 0; i < vector.size()-1; i++){
                if (vector.get(i)>vector.get(i+1)){
                    res[1] = vector.get(i);
                    break;
                }
            }
        }
        return res;
    }
    private void getInOrderRecur(TreeNode root) {
        if (root==null){
            return;
        }
        getInOrderRecur(root.left);
        vector.add(root.val);
        getInOrderRecur(root.right);
    }

    public static void main(String[] args) {
        TreeNode []nodes = new TreeNode[5];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = new TreeNode(i+1);
        }
        nodes[0].val = 2; nodes[0].left = nodes[1]; nodes[0].right = nodes[2];
        nodes[1].val = 3; nodes[1].left = nodes[3];
        nodes[2].val = 4; nodes[2].right = nodes[4];
        nodes[3].val = 1;
        nodes[4].val = 5;
        System.out.println(Arrays.toString(new FindErrorNode().findError(nodes[0])));
    }

}

class LongestDistance {
    /*
    从二叉树的节点A出发，可以向上或者向下走，但沿途的节点只能经过一次，当到达节点B时，路径上的节点数叫作A到B的距离。对于给定的一棵二叉树，求整棵树上节点间的最大距离。

给定一个二叉树的头结点root，请返回最大距离。保证点数大于等于2小于等于500.

     */
    public int findLongest(TreeNode root) {
        int[]res = longestRoute(root);
        return res[0];
    }
/*
返回为一个数组，第一个元素表示root为头的二叉树最大距离，第二个元素表示离root最远的距离
 */
    private int[] longestRoute(TreeNode root) {
        int[]res = new int[2];
        if (root==null){
            res[0] = 0;
            res[1] = 0;
            return res;
        }
        int[]leftRes = longestRoute(root.left), rightRes = longestRoute(root.right);
        res[1] = Math.max(leftRes[1], rightRes[1])+1;
        res[0] = Math.max(leftRes[0], rightRes[0]);
        res[0] = Math.max(res[0], leftRes[1]+rightRes[1]+1);
        System.err.println(Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestDistance().findLongest(TreeNode.getBinaryTreeHeadByLength(5)));
    }
}

class MaxSubtree {
    /*
    有一棵二叉树，其中所有节点的值都不一样,找到含有节点最多 的搜索二叉子树,并返回这棵子树的头节点.
给定二叉树的头结点root，请返回所求的头结点,若出现多个节点最多的子树，返回头结点权值最大的。
     */
    static class Result{
        TreeNode root;
        int cnt, min,max;

        public Result(TreeNode root, int cnt, int min, int max) {
            this.root = root;
            this.cnt = cnt;
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "root=" + root +
                    ", cnt=" + cnt +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
    public TreeNode getMax(TreeNode root) {
        Result result = getMostBinarySearchTree(root);
        return result.root;
    }

    private Result getMostBinarySearchTree(TreeNode root) {
        if (root.left==null&&root.right==null){
            return new Result(root, 1,root.val,root.val);
        }
        if (root.left != null && root.right==null){
            Result leftResult = getMostBinarySearchTree(root.left);
            if (leftResult.root == root.left && leftResult.max < root.val){
                return new Result(root, leftResult.cnt+1, leftResult.min, root.val);
            }else{
                return leftResult;
            }
        }
        if (root.right != null && root.left==null){
            Result rightResult = getMostBinarySearchTree(root.right);
            if (rightResult.root == root.left && rightResult.min < root.val){
                return new Result(root, rightResult.cnt+1, root.val, rightResult.max);
            }else{
                return rightResult;
            }
        }
        Result leftResult = getMostBinarySearchTree(root.left);
        Result rightResult = getMostBinarySearchTree(root.right);
//        System.err.println("left="+leftResult);
//        System.err.println("right="+rightResult);
        if (leftResult.root==root.left && rightResult.root == root.right && leftResult.max < root.val && root.val < rightResult.min){
            return new Result(root, leftResult.cnt+rightResult.cnt+1, leftResult.min, rightResult.max);
        }
        return leftResult.cnt > rightResult.cnt ? leftResult : rightResult;
    }

    public static void main(String[] args) {
        TreeNode[]nodes = new TreeNode[6];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = new TreeNode(i+1);
        }
        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];
        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];
        nodes[2].left = nodes[5];
        nodes[0].val = 4;
        nodes[1].val = 2;
        nodes[2].val = 6;
        nodes[3].val = 1;
        nodes[4].val = 5;
        nodes[5].val = 3;
        System.out.println(new MaxSubtree().getMax(nodes[0]));
    }
}