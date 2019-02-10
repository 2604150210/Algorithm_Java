import java.util.Arrays;

class Robot {
    /*
    在XxY的方格中，以左上角格子为起点，右下角格子为终点，每次只能向下走或者向右走，请问一共有多少种不同的走法

给定两个正整数int x,int y，请返回走法数目。保证x＋y小于等于12。

测试样例：
2,2
返回：2
     */
    public int countWays(int x, int y) {
        x--;y--;
        return C(x+y,x);
    }

    private int C(int m, int n) {
        return fact(m)/fact(m-n)/fact(n);
    }

    private int fact(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++){
            ret *= i;
        }
        return ret;
    }
}

class StandInLine {
    /*
    n个人站队，他们的编号依次从1到n，要求编号为a的人必须在编号为b的人的左边，但不要求一定相邻，请问共有多少种排法？第二问如果要求a必须在b的左边，并且一定要相邻，请问一共有多少种排法？

给定人数n及两个人的编号a和b，请返回一个两个元素的数组，其中两个元素依次为两个问题的答案。保证人数小于等于10。

测试样例：
7,1,2
返回：[2520,720]
     */
    public int[] getWays(int n, int a, int b) {
        int[]ret = new int[2];
        ret[0] = fact(n)/2;
        ret[1] = fact(n-1);
        return ret;
    }
    private int fact(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++){
            ret *= i;
        }
        return ret;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 2520; i++){
            if (2520 % i == 0) System.out.print(i+ " ");
        }
        int x = 2520;
        while (x>0){
            if (x % 2 == 0){
                System.out.print(2+ " ");
                x/=2;
            }else{
                System.out.println();
                break;
            }
        }
        System.out.println();
        System.out.println(Arrays.toString(new StandInLine().getWays(7,1,2)));
    }
}

class LonelyA {
    /*
    A(A也是他的编号)是一个孤傲的人，在一个n个人(其中编号依次为1到n)的队列中，他于其中的标号为b和标号c的人都有矛盾，所以他不会和他们站在相邻的位置。现在问你满足A的要求的对列有多少种？

给定人数n和三个人的标号A,b和c，请返回所求答案，保证人数小于等于11且大于等于3。

测试样例：
6,1,2,3
288
     */
    public int getWays(int n, int A, int b, int c) {
        int t = n - 3;
        int sum = 2 * t * fact(n-2);//A 站在头尾的情况
        sum += (n-2) * C(t,2) * fact(2) * fact(t);
        return sum;
    }
    private int fact(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++){
            ret *= i;
        }
        return ret;
    }
    private int C(int m, int n) {
        return fact(m)/fact(m-n)/fact(n);
    }

    public static void main(String[] args) {
        System.out.println(new LonelyA().getWays(6,1,2,3));
    }
}

class Distribution {
    /*
    n颗相同的糖果，分给m个人，每人至少一颗，问有多少种分法。

给定n和m，请返回方案数，保证n小于等于12，且m小于等于n。

测试样例：
10,3
返回：36
     */
    public int getWays(int n, int m) {
        return C(n-1, m-1);
    }
    private int fact(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++){
            ret *= i;
        }
        return ret;
    }
    private int C(int m, int n) {
        return fact(m)/fact(m-n)/fact(n);
    }

    public static void main(String[] args) {
        System.out.println(new Distribution().getWays(10,3));
    }
}

class Parenthesis2 {
    /*
    假设有n对左右括号，请求出合法的排列有多少个？合法是指每一个括号都可以找到与之配对的括号，比如n=1时，()是合法的，但是)(为不合法。

给定一个整数n，请返回所求的合法排列数。保证结果在int范围内。

测试样例：
1
返回：1

本题考察的是卡特兰数，公式为C(2*n,n)/(n+1)
     */
    public int countLegalWays(int n) {
        return C(2*n, n)/(n+1);
    }

    private int C(int m, int n) {
        int ret = 1;
        for (int i = m; i > n; i--){
            ret *= i;
        }
        for (int i = 1; i <= n; i++){
            ret /= i;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Parenthesis2().countLegalWays(7));
    }
}

class Stack {
    /*
    n个数进出栈的顺序有多少种？假设栈的容量无限大。

给定一个整数n，请返回所求的进出栈顺序个数。保证结果在int范围内。

测试样例：
1
返回：1
     */
    public int countWays(int n) {
        return C(2*n, n)/(n+1);
    }

    private int C(int m, int n) {
        int ret = 1;
        for (int i = m; i > n; i--){
            ret *= i;
        }
        for (int i = 1; i <= n; i++){
            ret /= i;
        }
        return ret;
    }
}

class BuyTickets {
    /*
    2n个人排队买票，n个人拿5块钱，n个人拿10块钱，票价是5块钱1张，每个人买一张票，售票员手里没有零钱，问有多少种排队方法让售票员可以顺利卖票。

给定一个整数n，请返回所求的排队方案个数。保证结果在int范围内。

测试样例：
1
返回：1
     */
    public int countWays(int n) {
        return C(2*n, n)/(n+1);
    }

    private int C(int m, int n) {
        int ret = 1;
        for (int i = m; i > n; i--){
            ret *= i;
        }
        for (int i = 1; i <= n; i++){
            ret /= i;
        }
        return ret;
    }
}

class TreeCount {
    /*
    求n个无差别的节点构成的二叉树有多少种不同的结构？

给定一个整数n，请返回不同结构的二叉树的个数。保证结果在int范围内。

测试样例：
1
返回：1
     */
    public int countWays(int n) {
        return C(2*n, n)/(n+1);
    }

    private int C(int m, int n) {
        int ret = 1;
        for (int i = m; i > n; i--){
            ret *= i;
        }
        for (int i = 1; i <= n; i++){
            ret /= i;
        }
        return ret;
    }
}

class HighAndShort {
    /*
    12个高矮不同的人，排成两排，每排必须是从矮到高排列，而且第二排比对应的第一排的人高，问排列方式有多少种？

给定一个偶数n，请返回所求的排列方式个数。保证结果在int范围内。

测试样例：
1
返回：1
     */
    public int countWays(int n) {
        n/=2;
        return C(2*n, n)/(n+1);
    }

    private int C(int m, int n) {
        int ret = 1;
        for (int i = m; i > n; i--){
            ret *= i;
        }
        for (int i = 1; i <= n; i++){
            ret /= i;
        }
        return ret;
    }
}

class CombineByMistake {
    /*
    有n个信封，包含n封信，现在把信拿出来，再装回去，要求每封信不能装回它原来的信封，问有多少种装法?

给定一个整数n，请返回装发个数，为了防止溢出，请返回结果Mod 1000000007的值。保证n的大小小于等于300。

测试样例：
2
返回：1
     */
    public int countWays(int n) {
        if (n==1)return 0;
        int mod = 1000000007;
        int []f = new int[n+1];
        f[1] = 0; f[2] = 1;
        for (int i = 3; i <= n; i++){
            f[i] = (int)(1L*(i-1)*(f[i-1]+f[i-2])%mod);
        }
        return f[n];
    }


    public static void main(String[] args) {
        System.out.println(new CombineByMistake().countWays(20));
    }
}