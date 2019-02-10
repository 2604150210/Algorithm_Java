import jiaozuo.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
    static void print(ListNode head){
        ListNode cur = head;
        while (cur != null){
            System.err.print(cur.val+" -> ");
            cur = cur.next;
        }
        System.err.println("null");
    }

    public String printThis() {
        return "ListNode{" + this+
                " val=" + val +
                ", next=" + next +
                '}';
    }

    static ListNode arrayToListNode(int[]a){
        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        for (int i = 1; i < a.length; i++){
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return head;
    }
}
class InsertValue {

    public ListNode insert(int[] A, int[] nxt, int val) {
        if (A.length <= 0) {
            return new ListNode(val);
        }
        ListNode head = new ListNode(A[0]);
        ListNode cur = head, t, pre;
        int pos = nxt[0];
        while (pos != 0) {
            t = new ListNode(A[pos]);
            cur.next = t;
            pos = nxt[pos];
            cur = t;
        }
//        cur.next = head;
        pre = head;
        while (pre.next != null) {
            cur = pre.next;
            if (!(pre.val <= val && cur.val >= val)) {
                pre = pre.next;
            } else {
                t = new ListNode(val);
                pre.next = t;
                t.next = cur;
                break;
            }
        }
        if (pre.next == null) {
            t = new ListNode(val);
            pre.next = t;
//            t.next = head;
            if (t.val < head.val) {
                head = t;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new InsertValue().insert(new int[]{1, 3, 4, 5, 7}, new int[]{1, 2, 3, 4, 0}, 9);
        ListNode.print(head);
    }
}

class Remove {
/*
实现一个算法，删除单向链表中间的某个结点，假定你只能访问该结点。

给定带删除的头节点和要删除的数字，请执行删除操作，返回删除后的头结点。链表中没有重复数字
 */
    public ListNode removeNode(ListNode pNode, int delVal) {
        if (pNode.val == delVal){
            return pNode.next;
        }
        // write code here
        ListNode pre = pNode;
        while (pre.next != null){
            if (pre.next.val == delVal){
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }
        return pNode;
    }
}

class Divide {
/*
对于一个链表，我们需要用一个特定阈值完成对它的分化，使得小于等于这个值的结点移到前面，大于该值的结点在后面，同时保证两类结点内部的位置关系不变。

给定一个链表的头结点head，同时给定阈值val，请返回一个链表，使小于等于它的结点在前，大于等于它的在后，保证结点值不重复。

测试样例：
{1,4,2,5},3
{1,2,4,5}
 */
    public ListNode listDivide(ListNode head, int val) {
        ListNode head1 = null, head2 = null,t, cur = head;
        ListNode cur1 = head1, cur2 = head2;
        while (cur!=null){
//            System.err.println("cur.val="+cur.val);
            t = new ListNode(cur.val);
            if(cur.val <= val){
                if (head1==null){
                    head1 = t;
                    cur1 = t;
                }else{
                    cur1.next = t;
                    cur1 = cur1.next;
                }
            }else{
                if (head2==null){
                    head2 = t;
                    cur2 = t;
                }else{
                    cur2.next = t;
                    cur2 = cur2.next;
                }
            }
            cur = cur.next;
//            System.err.print("head1=");
//            ListNode.print(head1);
//            System.err.print("head2=");
//            ListNode.print(head2);
//            System.err.print("head3=");
//            ListNode.print(head3);
//            System.err.println();
        }
        if(cur1!=null){
            cur1.next = head2;
            return head1;
        }else{
            return head2;
        }
    }

    public static void main(String[] args) {
        int []a = new int[]{1620,1134,861,563,1};
        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        for(int i = 1; i < a.length; i++){
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        ListNode.print(head);
        Divide d = new Divide();
        ListNode newHead = d.listDivide(head, 1134);
        ListNode.print(newHead);
    }
}
class Common {
    /*
    现有两个升序链表，且链表中均无重复元素。请设计一个高效的算法，打印两个链表的公共值部分。

给定两个链表的头指针headA和headB，请返回一个vector，元素为两个链表的公共部分。请保证返回数组的升序。两个链表的元素个数均小于等于500。保证一定有公共值

测试样例：
{1,2,3,4,5,6,7},{2,4,6,8,10}
返回：[2.4.6]

     */


    public int[] findCommonParts(ListNode headA, ListNode headB) {
        // write code here
        ArrayList<Integer>list = new ArrayList<>();
        ListNode i = headA, j = headB;
        while (i != null && j != null){
            if(i.val < j.val){
                i = i.next;
            }
            else if (i.val > j.val){
                j = j.next;
            }
            else{
                list.add(i.val);
                i = i.next;j=j.next;
            }
        }
        int []ret = new int[list.size()];
        int cnt = 0;
        for (int f : list){
            ret[cnt++] = f;
        }
        return ret;
    }

    public static void main(String[] args) {
        Common common = new Common();
        int []a = new int[]{1,2,3,4,5,6,7};
        int []b = new int[]{2,4,6,8,10};
        ListNode headA = new ListNode(a[0]);
        ListNode cur = headA;
        for (int i = 1; i < a.length; i++){
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        ListNode headB = new ListNode(b[0]);
        cur = headB;
        for (int i = 1; i < b.length; i++){
            cur.next = new ListNode(b[i]);
            cur = cur.next;
        }
        ListNode.print(headA);
        ListNode.print(headB);
        int[]res = common.findCommonParts(headA, headB);
        System.out.println(Arrays.toString(res));
    }
}

class KInverse {
/*
有一个单链表，请设计一个算法，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。例如链表1->2->3->4->5->6->7->8->null，K=3这个例子。调整后为，3->2->1->6->5->4->7->8->null。因为K==3，所以每三个节点之间逆序，但其中的7，8不调整，因为只有两个节点不够一组。

给定一个单链表的头指针head,同时给定K值，返回逆序后的链表的头指针。
 */
    public ListNode inverse(ListNode head, int k) {
        // write code here
        ListNode pre = new ListNode(-1),ret=pre;
        pre.next = head;
        ListNode last = pre;
        int cnt = 0;
        while (last.next != null){
            last = last.next;
            cnt++;
            if(cnt == k){
                last = inverseList(pre, last);
                cnt = 0;
                pre = last;
            }
        }
        return ret.next;
    }
    private ListNode inverseList(ListNode pre, ListNode last) {
        ListNode first = pre.next,p=pre,q = pre.next, r = q.next,cur = pre,nx = last.next,ret=pre.next;
        while (q != nx){
            r=q.next;
            q.next=p;
            p=q;
            q=r;
        }
        first.next = nx;
        pre.next = last;
        return ret;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.arrayToListNode(new int[]{1,2,3,4,5,6,7,8});
        ListNode.print(head);
        ListNode cur = head;
        while (cur != null){
            System.err.println("cur="+cur.printThis());
            cur = cur.next;
        }
        KInverse k = new KInverse();
//        k.inverseList(head,head.next.next.next);
        ListNode h = k.inverse(head,3);
        ListNode.print(h);
    }
}

class ClearValue {
    /*
    删除链表中指定的元素
     */
    public ListNode clear(ListNode head, int val) {
        ListNode ret = new ListNode(-1);
        ListNode i = head, j = ret;
        while (i != null){
            if(i.val != val){
                j.next = new ListNode(i.val);
                j = j.next;
            }
            i = i.next;
        }
        return ret.next;
    }
}

class Palindrome {
    public boolean isPalindrome(ListNode pHead) {
        int cnt = 0;
        ListNode cur = pHead;
        while (cur != null){
            cnt++;
            cur = cur.next;
        }
        cur = pHead;
        int i = 1;
        while (cur!=null){
            if (i > (cnt-1)/2)break;
            cur = cur.next;
            i++;
        }
        ListNode pre = cur;
        ListNode q = pre.next,r = null,head2,cur2;
        pre.next = null;
        while (q != null){
            r = q.next;
            q.next = pre;
            pre = q;
            q = r;
        }
        head2 = pre;
        cur = pHead; cur2 = head2;
        while (cur!=null && cur2 != null){
            if(cur.val != cur2.val)return false;
            cur = cur.next;
            cur2 = cur2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        ListNode head = ListNode.arrayToListNode(new int[]{1,2,3,5,1});
        System.out.println(p.isPalindrome(head));
    }
}

class Solution2 {
    /*
    复制链表
     */
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

        void printSelf(){
            System.err.println("lable="+label+"   this="+this + "   next="+next+"   random="+random);
        }
    }
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null)return null;
        RandomListNode cur = pHead;
        while (cur!=null){
            RandomListNode t = new RandomListNode(cur.label);
            t.next = cur.next;
            cur.next = t;
            cur = t.next;
        }
        cur = pHead;
        while (cur != null){
            if(cur.random==null){
                cur.next.random = null;
            }else{
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        RandomListNode head = pHead.next;
        cur = pHead;
        while (cur.next!=null){
            RandomListNode t = cur.next;
            cur.next = cur.next.next;
            cur = t;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();

        RandomListNode []nodes = new RandomListNode[3];
        for (int i = 0; i < 3; i++){
            nodes[i] = new RandomListNode(i+1);
        }
        nodes[0].next = nodes[1];
        nodes[1].next = nodes[2];
        nodes[0].random = nodes[1];
        nodes[1].random = nodes[2];
        nodes[2].random = nodes[0];
        RandomListNode h = s.Clone(nodes[0]);
        RandomListNode cur = nodes[0];
        while (cur != null){
            cur.printSelf();
            cur = cur.next;
        }
        cur = h;
        while (cur != null){
            cur.printSelf();
            cur = cur.next;
        }
    }
}

class ChkLoop {
    /*
    如何判断一个单链表是否有环？有环的话返回进入环的第一个节点的值，无环的话返回-1。如果链表的长度为N，请做到时间复杂度O(N)，额外空间复杂度O(1)。

给定一个单链表的头结点head（注意另一个参数adjust为加密后的数据调整参数，方便数据设置，与本题求解无关)，请返回所求值。
     */
    public int chkLoop(ListNode head, int adjust) {
        ListNode fast = head, slow = head;
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast==null)return -1;
            fast = fast.next;
            if (fast == null)return -1;
            if(fast == slow){
                break;
            }
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast.val;
    }
}

class CheckIntersect {
    /*
    现在有两个无环单链表，若两个链表的长度分别为m和n，请设计一个时间复杂度为O(n + m)，额外空间复杂度为O(1)的算法，判断这两个链表是否相交。

给定两个链表的头结点headA和headB，请返回一个bool值，代表这两个链表是否相交。保证两个链表长度小于等于500。
     */
    public boolean chkIntersect(ListNode headA, ListNode headB) {
        int n = 0, m = 0;
        ListNode cur = headA;
        while (cur != null){
            cur = cur.next;
            n++;
        }
        cur = headB;
        while (cur != null){
            cur = cur.next;
            m++;
        }
        ListNode i = headA, j = headB;
        while (n-->m){
            i = i.next;
        }
        while (i!=null && j != null && i!=j){
            i = i.next; j = j.next;
        }
        return i != null;
    }
}

class ChkIntersection {
    /*
    如何判断两个有环单链表是否相交？相交的话返回第一个相交的节点，不想交的话返回空。如果两个链表长度分别为N和M，请做到时间复杂度O(N+M)，额外空间复杂度O(1)。

给定两个链表的头结点head1和head2(注意，另外两个参数adjust0和adjust1用于调整数据,与本题求解无关)。请返回一个bool值代表它们是否相交。
     */
    public boolean chkInter(ListNode head1, ListNode head2, int adjust0, int adjust1) {
        ListNode loop1 = chkLoop(head1);
        ListNode loop2 = chkLoop(head2);
        ListNode cur = loop1.next;
        if (loop1 == loop2)return true;
        while (cur != loop1){
            if (cur == loop2){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    public ListNode chkLoop(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if(fast == slow){
                break;
            }
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

class ChkIntersection2 {
    /*
    给定两个单链表的头节点head1和head2，如何判断两个链表是否相交？相交的话返回true，不想交的话返回false。
给定两个链表的头结点head1和head2(注意，另外两个参数adjust0和adjust1用于调整数据,与本题求解无关)。请返回一个bool值代表它们是否相交。
     */
    public boolean chkInter(ListNode head1, ListNode head2, int adjust0, int adjust1) {
        ListNode loop1 = chkLoop(head1), loop2 = chkLoop(head2);
        if (loop1 == null && loop2 == null){
            return chkIntersect(head1, head2);
        }else if(loop1!= null && loop2 != null){
            ListNode cur = loop1.next;
            if (loop1 == loop2)return true;
            while (cur != loop1){
                if (cur == loop2){
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }
        return false;
    }
    public boolean chkIntersect(ListNode headA, ListNode headB) {
        int n = 0, m = 0;
        ListNode cur = headA;
        while (cur != null){
            cur = cur.next;
            n++;
        }
        cur = headB;
        while (cur != null){
            cur = cur.next;
            m++;
        }
        ListNode i = headA, j = headB;
        while (n-->m){
            i = i.next;
        }
        while (i!=null && j != null && i!=j){
            i = i.next; j = j.next;
        }
        return i != null;
    }

    public ListNode chkLoop(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast==null)return null;
            fast = fast.next;
            if (fast == null)return null;
            if(fast == slow){
                break;
            }
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

}