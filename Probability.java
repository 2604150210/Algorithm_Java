import java.util.Arrays;
import java.util.Random;

class Championship {
    /*
    有2k只球队，有k-1个强队，其余都是弱队，随机把它们分成k组比赛，每组两个队，问两强相遇的概率是多大？

给定一个数k，请返回一个数组，其中有两个元素，分别为最终结果的分子和分母，请化成最简分数

测试样例：
4
返回：[3,7]
     */
    public int[] calc(int k) {
        int []ret = new int[2];
        ret[1] = 1;
        for (int i = 2 * k; i > k; i--){
            ret[1] *= i;
        }
        for (int i = 1; i <= k; i++){
            ret[1] /= 2;
        }
        int t = fact(k+1)/2;
        ret[0] = ret[1] - t;
        int r = gcd(ret[0], ret[1]);
        ret[0]/=r;
        ret[1]/=r;
        return ret;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    private int fact(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++){
            ret *= i;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Championship().calc(4)));
    }
}

class Ants {
    public int[] collision(int n) {
        int []ret = new int[2];
        ret[1] = 1;
        for (int i = 1; i <= n; i++){
            ret[1] *= 2;
        }
        ret[0] = ret[1] - 2;
        int r = gcd(ret[0], ret[1]);
        ret[0]/=r;
        ret[1]/=r;
        return ret;
    }
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Ants().collision(3)));
    }
}

class Random7 {
    /*
    给定一个等概率随机产生1~5的随机函数，除此之外，不能使用任何额外的随机机制，请实现等概率随机产生1~7的随机函数。(给定一个可调用的Random5::random()方法,可以等概率地随机产生1～5的随机函数)
     */
    private static Random rand = new Random(123456);
    // 随机产生[1,5]
    private int rand5() {
        return 1 + rand.nextInt(5);
    }

    // 通过rand5实现rand7
    public int randomNumber() {
        return rand21()%7+1;
    }
    private int rand25() {
        //返回从0到24
        return rand5()*5-rand5();
    }
    private int rand21() {
        //返回从0到20
        int t;
        do {
            t = rand25();
        }while (t>=21);
        return t;
    }

    public static void main(String[] args) {
        Random7 r = new Random7();
        for (int i = 1; i <= 21; i++){
            System.out.println(r.randomNumber());
        }
    }
}

class Random01 {
    /*
    给定一个以p概率产生0，以1-p概率产生1的随机函数RandomP::f()，p是固定的值，但你并不知道是多少。除此之外也不能使用任何额外的随机机制，请用RandomP::f()实现等概率随机产生0和1的随机函数。
     */
    private static double p = new Random().nextFloat();
    // 随机概率p
    public static int f() {
        return new Random().nextFloat() < p ? 0 : 1;
    }

    public int random01() {
        int a, b;
        do {
            a = f();
            b = f();
        }while (a == b);
        return a==1?1:0;
    }
}

class RandomSeg {
    /*
    假设函数f()等概率随机返回一个在[0,1)范围上的浮点数，那么我们知道，在[0,x)区间上的数出现的概率为x(0<x≤1)。给定一个大于0的整数k，并且可以使用f()函数，请实现一个函数依然返回在[0,1)范围上的数，但是在[0,x)区间上的数出现的概率为x的k次方。
     */
    private Random rand = new Random(12345);
    public double f() {
        return rand.nextFloat();
    }
    // 请调用f()函数实现
    public double random(int k, double x) {
        double max = f();
        for (int i = 2; i <= k; i++){
            max = Math.max(max, f());
        }
        return max;
    }
}

class RandomPrint {
    /*
    给定一个长度为N且没有重复元素的数组arr和一个整数M，实现函数等概率随机打印arr中的M个数。
     */
    public int[] print(int[] arr, int N, int M) {
        Random random = new Random(12345);
        int []ret = new int[M];
        for (int i = 0; i < M; i++){
            int r = random.nextInt(N-i);
            ret[i] = arr[r];
            int t = arr[N-i-1]; arr[N-i-1] = arr[r]; arr[r] = t;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RandomPrint().print(new int[]{29,24,17,1,3,11,8,19,12,15,10,28,20,18,2,26,14,7,22,27,23,5,6,9,21,16,25,4,13},29,10)));
    }
}

class Bag {
    /*
    有一个机器按自然数序列的方式吐出球，1号球，2号球，3号球等等。你有一个袋子，袋子里最多只能装下K个球，并且除袋子以外，你没有更多的空间，一个球一旦扔掉，就再也不可拿回。设计一种选择方式，使得当机器吐出第N号球的时候，你袋子中的球数是K个，同时可以保证从1号球到N号球中的每一个，被选进袋子的概率都是K/N。举一个更具体的例子，有一个只能装下10个球的袋子，当吐出100个球时，袋子里有10 球，并且1~100号中的每一个球被选中的概率都是10/100。然后继续吐球，当吐出1000个球时，袋子里有 10 个球，并且1~1000号中的每一个球被选中的概率都是10/1000。继续吐球，当吐出i个球时，袋子里有10个球，并且1~i号中的每一个球被选中的概率都是10/i。也就是随着N的变化，1~N号球被选中的概率动态变化成k/N。请将吐出第N个球时袋子中的球的编号返回。
     */
    private int [] selected = null;
    private static Random rand = new Random(12345);
    // 每次拿一个球都会调用这个函数，N表示第i次调用
    public int[] carryBalls(int N, int k) {
        if (selected == null)selected = new int[k];
        if (N <= k){
            selected[N-1] = N;
        }else{
            if (rand.nextInt(N) < k){
                int r = rand.nextInt(k);
                selected[r] = N;
            }
        }
        return selected;
    }
}
