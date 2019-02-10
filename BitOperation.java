import java.util.Arrays;

class Swap {
    /*
    请编写一个算法，不用任何额外变量交换两个整数的值。

给定一个数组num，其中包含两个值，请不用任何额外变量交换这两个值，并将交换后的数组返回。

测试样例：
[1,2]
返回：[2,1]
     */
    public int[] getSwap(int[] num) {
        num[0] = num[0]^num[1];
        num[1] = num[0]^num[1];
        num[0] = num[0]^num[1];
        return num;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Swap().getSwap(new int[]{1,2})));
    }
}

class Compare {
    /*
    对于两个32位整数a和b，请设计一个算法返回a和b中较大的。但是不能用任何比较判断。若两数相同，返回任意一个。

给定两个整数a和b，请返回较大的数。

测试样例：
1,2
返回：2

     */
    public int getMax(int a, int b) {
        int c = a - b;
        int ra = flip(getSign(c));
        int rb = flip(ra);
        return a*ra + b*rb;
    }
    private int flip(int x){
        return x^1;
    }
    private int getSign(int x) {
        return (x>>31)&1;
    }

    public static void main(String[] args) {
        System.out.println(new Compare().getMax(1,2));
    }
}

class OddAppearance {
    /*
    有一个整型数组A，其中只有一个数出现了奇数次，其他的数都出现了偶数次，请打印这个数。要求时间复杂度为O(N)，额外空间复杂度为O(1)。

给定整形数组A及它的大小n，请返回题目所求数字。

测试样例：
[1,2,3,2,1],5
返回：3
     */
    public int findOdd(int[] A, int n) {
        int t = 0;
        for (int i = 0; i < n; i++){
            t ^= A[i];
        }
        return t;
    }
}

class OddAppearance2 {
    /*
    给定一个整型数组arr，其中有两个数出现了奇数次，其他的数都出现了偶数次，找到这两个数。要求时间复杂度为O(N)，额外空间复杂度为O(1)。

给定一个整形数组arr及它的大小n，请返回一个数组，其中两个元素为两个出现了奇数次的元素,请将他们按从小到大排列。

测试样例：
[1,2,4,4,2,1,3,5],8
返回：[3,5]
     */
    public int[] findOdds(int[] arr, int n) {
        int t = 0;
        for (int i = 0; i < n; i++){
            t ^= arr[i];
        }
        int lowbit = t&(-t);
        int a = 0;
        for (int i = 0; i < n; i++){
            if ((arr[i] & lowbit)!=0){
                a ^= arr[i];
            }
        }
        int b = t ^ a;
        if (a < b){
            return new int[]{a,b};
        }else{
            return new int[]{b, a};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new OddAppearance2().findOdds(new int[]{3,5,4,3,4,8},6)));
    }
}