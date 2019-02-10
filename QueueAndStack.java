import java.util.*;

class Solution {
    Stack<Integer>s1 = new Stack<>();
    Stack<Integer>s2 = new Stack<>();

    public void push(int node) {
        s1.push(node);
        if(s2.empty()){
            s2.push(node);
        }else{
            if(node < s2.peek()){
                s2.push(node);
            }else{
                s2.push(s2.peek());
            }
        }
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.push(3);
        System.out.println(s.min());
        s.push(4);
        System.out.println(s.min());
        s.push(2);
        System.out.println(s.min());
        s.push(3);
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.push(0);
        System.out.println(s.min());
    }
}
class TwoStack {
    /*
    编写一个类,只能用两个栈结构实现队列,支持队列的基本操作(push，pop)。
给定一个操作序列ope及它的长度n，其中元素为正数代表push操作，为0代表pop操作，保证操作序列合法且一定含pop操作，请返回pop的结果序列。
测试样例：
[1,2,3,0,4,0],6
返回：[1,2]
     */
    Stack<Integer>stackPush = new Stack<>();
    Stack<Integer>stackPop = new Stack<>();
    public int[] twoStack(int[] ope, int n) {
        // write code here
        Vector<Integer>vector = new Vector<>();
        for(int i = 0; i < n; i++){
            if(ope[i] == 0){
                vector.add(pop());
            }else{
                push(ope[i]);
            }
        }
        int []ret = new int[vector.size()];
        for (int i = 0; i < ret.length; i++){
            ret[i] = vector.get(i);
        }
        return ret;
    }

    private int pop() {
        while (stackPush.size()>0){
            stackPop.push(stackPush.pop());
        }
        int ret = stackPop.pop();
        while (stackPop.size()>0){
            stackPush.push(stackPop.pop());
        }
        return ret;
    }
    private void push(int x){
        stackPush.push(x);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoStack().twoStack(new int[]{1,2,3,0,4,0},6)));
    }
}

class StackReverse {
    /*
    实现一个栈的逆序，但是只能用递归函数和这个栈本身的pop操作来实现，而不能自己申请另外的数据结构。

给定一个整数数组A即为给定的栈，同时给定它的大小n，请返回逆序后的栈。

测试样例：
[4,3,2,1],4
返回：[1,2,3,4]

     */
    Stack<Integer>stack = new Stack<>();
    public int[] reverseStack(int[] A, int n) {
        // write code here
        for (int i: A){
            stack.push(i);
        }
        reverse(stack);
        int []ret = new int[stack.size()];
        for(int i = 0; i < ret.length; i++){
            ret[n-1-i] = stack.pop();
        }
        return ret;
    }
    private void reverse(Stack<Integer>stack){
        if (stack.size()==1)return;
        int t = removeStackBottom(stack);
        reverse(stack);
        stack.push(t);
    }
    private int removeStackBottom(Stack<Integer>stack){
        int top = stack.pop();
        if (stack.empty()){
            return top;
        }else{
            int ret = removeStackBottom(stack);
            stack.push(top);
            return ret;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new StackReverse().reverseStack(new int[]{4,3,2,1},4)));
    }
}

class TwoStacks {
    /*
    请编写一个程序，按升序对栈进行排序（即最大元素位于栈顶），要求最多只能使用一个额外的栈存放临时数据，但不得将元素复制到别的数据结构中。

给定一个int[] numbers(C++中为vector&ltint>)，其中第一个元素为栈顶，请返回排序后的栈。请注意这是一个栈，意味着排序过程中你只能访问到第一个元素。

测试样例：
[1,2,3,4,5]
返回：[5,4,3,2,1]
     */
    public ArrayList<Integer> twoStacksSort(int[] numbers) {
        Stack<Integer>stack = new Stack<>();
        for (int i = numbers.length-1; i>=0;i--){
            stack.push(numbers[i]);
        }
        Stack<Integer>res = new Stack<>();
        while (stack.size()>0){
            int x = stack.pop();
            int cnt = 0;
            while (res.size()>0&&x > res.peek()){
                cnt++;
                stack.push(res.pop());
            }
            res.push(x);
            while (cnt-->0){
                res.push(stack.pop());
            }
        }
        ArrayList<Integer>list = new ArrayList<>();
        while (res.size()>0){
            stack.push(res.pop());
        }
        while (stack.size()>0){
            list.add(stack.pop());
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer>list = new TwoStacks().twoStacksSort(new int[]{1,2,3,4,5});
        for (int i : list){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}

class SlideWindow {
    /*
    有一个整型数组 arr 和一个大小为 w 的窗口从数组的最左边滑到最右边,窗口每次向右边滑一个位置。 返回一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值。 以数组为[4,3,5,4,3,3,6,7]，w=3为例。因为第一个窗口[4,3,5]的最大值为5，第二个窗口[3,5,4]的最大值为5，第三个窗口[5,4,3]的最大值为5。第四个窗口[4,3,3]的最大值为4。第五个窗口[3,3,6]的最大值为6。第六个窗口[3,6,7]的最大值为7。所以最终返回[5,5,5,4,6,7]。

给定整形数组arr及它的大小n，同时给定w，请返回res数组。保证w小于等于n，同时保证数组大小小于等于500。

测试样例：
[4,3,5,4,3,3,6,7],8,3
返回：[5,5,5,4,6,7]
     */
    public int[] slide(int[] arr, int n, int w) {
        int []ret = new int[n - w + 1];
        int cnt = 0;
        LinkedList<Integer>deque = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if (deque.size() <= 0){
                deque.add(i);
            }else{
                while (deque.size()>0 && arr[deque.getLast()] <= arr[i]){
                    deque.removeLast();
                }
                deque.add(i);
            }
            if (i >= w-1){
                if(i - deque.getFirst() + 1 > w){
                    deque.removeFirst();
                }
                ret[cnt++] = arr[deque.getFirst()];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SlideWindow().slide(new int[]{4,3,5,4,3,3,6,7}, 8, 3)));
    }
}

class MaxTree {
    /*
    对于一个没有重复元素的整数数组，请用其中元素构造一棵MaxTree，MaxTree定义为一棵二叉树，其中的节点与数组元素一一对应，同时对于MaxTree的每棵子树，它的根的元素值为子树的最大值。现有一建树方法，对于数组中的每个元素，其在树中的父亲为数组中它左边比它大的第一个数和右边比它大的第一个数中更小的一个。若两边都不存在比它大的数，那么它就是树根。请设计O(n)的算法实现这个方法。

给定一个无重复元素的数组A和它的大小n，请返回一个数组，其中每个元素为原数组中对应位置元素在树中的父亲节点的编号，若为根则值为-1。

测试样例：
[3,1,4,2],4
返回：[2,0,-1,2]
     */
    public int[] buildMaxTree(int[] A, int n) {
        Stack<Integer>leftStack = new Stack<>();
        int []leftArr = new int[n];
        for (int i = 0; i < n; i++){
            while (leftStack.size() > 0 && A[i] >= A[leftStack.peek()]){
                leftStack.pop();
            }
            if (leftStack.size() > 0){
                    leftArr[i] = leftStack.peek();
            }else{
                leftArr[i] = -1;
            }
            leftStack.push(i);
        }
        Stack<Integer>rightStack = new Stack<>();
        int []rightArr = new int[n];
        for (int i = n-1; i >=0; i--){
            while (rightStack.size() > 0 && A[i] >= A[rightStack.peek()]){
                rightStack.pop();
            }
            if (rightStack.size() > 0){
                rightArr[i] = rightStack.peek();
            }else{
                rightArr[i] = -1;
            }
            rightStack.push(i);
        }
        int []ret = new int[n];
        for (int i = 0; i < n; i++){
            int l = leftArr[i], r = rightArr[i];
            if (l == -1 && r == -1){
                ret[i] = -1;
            }else if(l != -1 && r == -1){
                ret[i] = l;
            }else if(l == -1 && r != -1){
                ret[i] = r;
            }else{
                if(A[l] < A[r]){
                    ret[i] = l;
                }else{
                    ret[i] = r;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxTree().buildMaxTree(new int[]{340,1387,2101,847,1660,733,36,528},8)));
    }
}