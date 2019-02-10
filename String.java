import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class IdenticalTree {
    /*
    对于两棵彼此独立的二叉树A和B，请编写一个高效算法，检查A中是否存在一棵子树与B树的拓扑结构完全相同。

给定两棵二叉树的头结点A和B，请返回一个bool值，代表A中是否存在一棵同构于B的子树。
     */
    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean chkIdentical(TreeNode A, TreeNode B) {
        // write code here
        String a = serialByPreOrder(A);
        String b = serialByPreOrder(B);
        return getIndexOf(a, b) != -1;
    }

    private int getIndexOf(String a, String b) {
        if (a == null || b == null || b.length() < 1 || a.length() < b.length())return -1;

        char[]arrA = a.toCharArray();
        char[]arrB = b.toCharArray();
        int[] nextArr = getNextArray(arrB);
        int index = 0;
        int i = 0;
        while (index < arrA.length && i < arrB.length){
            if(arrA[index] == arrB[i]){
                index++;
                i++;
            }else if(nextArr[i] == -1){
                index++;
            }else{
                i = nextArr[i];
            }
        }
        if(i == arrB.length){
            return index - i;
        }else
            return -1;
    }

    private String serialByPreOrder(TreeNode root) {
        if (root == null)return "#!";
        return Integer.toString(root.val)+"!"+ serialByPreOrder(root.left)+ serialByPreOrder(root.right);
    }
    private int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] nextArr = new int[ms.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < nextArr.length) {
            if (ms[pos - 1] == ms[cn]) {
                nextArr[pos++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }
    public static void main(String[] args) {
        TreeNode []nodes = new TreeNode[8];
        for (int i = 0; i < 5; i++){
            nodes[i] = new TreeNode(i+1);
        }
        nodes[5] = new TreeNode(2);
        nodes[6] = new TreeNode(4);
        nodes[7] = new TreeNode(5);

        nodes[0].left = nodes[1]; nodes[0].right = nodes[2]; nodes[1].left = nodes[3]; nodes[1].right = nodes[4];
        nodes[5].left = nodes[6]; nodes[5].right = nodes[7];
        System.out.println(new IdenticalTree().chkIdentical(nodes[0], nodes[5]));
    }
}

class Transform {
    /*
    对于两个字符串A和B，如果A和B中出现的字符种类相同且每种字符出现的次数相同，则A和B互为变形词，请设计一个高效算法，检查两给定串是否互为变形词。

给定两个字符串A和B及他们的长度，请返回一个bool值，代表他们是否互为变形词。

测试样例：
"abc",3,"bca",3
返回：true
     */
    public boolean chkTransform(String A, int lena, String B, int lenb) {
        // write code here
        int []a = new int[65536];
        int []b = new int[65536];
        char[] arrA = A.toCharArray();
        char[] arrB = B.toCharArray();
        for (int i = 0; i < arrA.length; i++){
            a[arrA[i]]++;
        }
        for (int i = 0; i < arrB.length; i++){
            b[arrB[i]]++;
        }
        for (int i = 0; i < 65536; i++){
            if (a[i] != b[i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Transform().chkTransform("abc",3,"bca",3));
    }
}

class Reverse {
    /*
    对于一个字符串，请设计一个算法，只在字符串的单词间做逆序调整，也就是说，字符串由一些由空格分隔的部分组成，你需要将这些部分逆序。

给定一个原字符串A和他的长度，请返回逆序后的字符串。

测试样例：
"dog loves pig",13
返回："pig loves dog"
     */
    public String reverseSentence(String A, int n) {
        // write code here
        char[]arr = A.toCharArray();
        reverseArray(arr, 0, n-1);
        int i = 0, j = 0;
        while (i < arr.length && j< arr.length){
            while (j < arr.length && arr[j]!=' ')j++;
            reverseArray(arr, i,j-1);
            i = ++j;
        }
        return String.valueOf(arr);
    }

    private void reverseArray(char[] arr, int left, int right) {
        for(int k = left; k <= (right+left)/2; k++){
            int r = right+left-k;
            char t = arr[k]; arr[k] = arr[r]; arr[r] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Reverse().reverseSentence("dog loves pig",13));
    }
}

class Translation {
    /*
    对于一个字符串，请设计一个算法，将字符串的长度为len的前缀平移到字符串的最后。

给定一个字符串A和它的长度，同时给定len，请返回平移后的字符串。

测试样例：
"ABCDE",5,3
返回："DEABC"
     */
    public String stringTranslation(String A, int n, int len) {
        // write code here
        char []arr = A.toCharArray();
        reverseArray(arr, 0,n-1);
        reverseArray(arr, 0, n-len-1);
        reverseArray(arr, n-len, n-1);
        return String.valueOf(arr);
    }
    private void reverseArray(char[] arr, int left, int right) {
        for(int k = left; k <= (right+left)/2; k++){
            int r = right+left-k;
            char t = arr[k]; arr[k] = arr[r]; arr[r] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Translation().stringTranslation("ABCDE",5,3));
    }
}

class Prior {
    /*
    对于一个给定的字符串数组，请找到一种拼接顺序，使所有小字符串拼接成的大字符串是所有可能的拼接中字典序最小的。

给定一个字符串数组strs，同时给定它的大小，请返回拼接成的串。

测试样例：
["abc","de"],2
"abcde"
     */
    public String findSmallest(String[] strs, int n) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                String s1 = s+t1, s2=t1+s;
                return s1.compareTo(s2);
            }
        });
        String ret = "";
        for (String i:strs){
            ret += i;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Prior().findSmallest(new String[]{"b","ba"},2));
    }
}

class Replacement {
    /*
    请编写一个方法，将字符串中的空格全部替换为“%20”。假定该字符串有足够的空间存放新增的字符，并且知道字符串的真实长度(小于等于1000)，同时保证字符串由大小写的英文字母组成。

给定一个string iniString 为原始的串，以及串的长度 int len, 返回替换后的string。

测试样例：
"Mr John Smith”,13
返回："Mr%20John%20Smith"
”Hello  World”,12
返回：”Hello%20%20World”
     */
    public String replaceSpace(String iniString, int length) {
        int spaceNumber = 0;
        for (int i = 0; i < iniString.length(); i++){
            if(iniString.charAt(i) == ' '){
                spaceNumber++;
            }
        }
        char []arr = new char[iniString.length() + 2 * spaceNumber];
        int i = iniString.length()-1, j = arr.length - 1;
        while (i >= 0){
            if(iniString.charAt(i)==' '){
                arr[j--] = '0'; arr[j--] = '2'; arr[j--] = '%';
            }else{
                arr[j--] = iniString.charAt(i);
            }
            i--;
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        System.out.println(new Replacement().replaceSpace("Mr John Smith",13));
    }
}

class Parenthesis {
    /*
    对于一个字符串，请设计一个算法，判断其是否为一个合法的括号串。

给定一个字符串A和它的长度n，请返回一个bool值代表它是否为一个合法的括号串。

测试样例：
"(()())",6
返回：true
测试样例：
"()a()()",7
返回：false
测试样例：
"()(()()",7
返回：false
     */
    public boolean chkParenthesis(String A, int n) {
        int num = 0;
        for (int i = 0; i < A.length(); i++){
            if (num < 0)return false;
            if(A.charAt(i) == '(')num++;
            else num--;
        }
        return num == 0;
    }
}

class DistinctSubstring {
    /*
    对于一个字符串,请设计一个高效算法，找到字符串的最长无重复字符的子串长度。

给定一个字符串A及它的长度n，请返回它的最长无重复字符子串长度。保证A中字符全部为小写英文字符，且长度小于等于500。

测试样例：
"aabcb",5
返回：3
     */
    public int longestSubstring(String A, int n) {
        HashMap<Character, Integer>hashMap = new HashMap<>();
        int []s = new int[n];
        s[0] = 1;
        hashMap.put(A.charAt(0), 0);
        for (int i = 1; i < n; i++){
            char c = A.charAt(i);
            if(!hashMap.containsKey(c)){
                hashMap.put(c, i);
                s[i] = s[i-1]+1;
            }else {
                int pos = hashMap.get(c);
                if (i == 1){
                }
                if(i-pos-1 < s[i-1]){
                    s[i] = i - pos;
                }else{
                    s[i] = s[i-1]+1;
                }
                hashMap.put(c, i);
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, s[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubstring().longestSubstring("aabcb",5));
    }
}