import java.util.Arrays;

class Exchange {
    /*
    有数组penny，penny中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim(小于等于1000)代表要找的钱数，求换钱有多少种方法。

给定数组penny及它的大小(小于等于50)，同时给定一个整数aim，请返回有多少种方法可以凑成aim。

测试样例：
[1,2,4],3,3
返回：2
     */
    public int countWays(int[] penny, int n, int aim) {
        int[][]dp = new int[n][aim+1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= aim; j++){
                dp[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++){
            dp[i][0] = 1;
        }
        for (int i = penny[0]; i <= aim; i+= penny[0]){
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++){
            for (int j = 1; j <= aim; j++){
                if (j >= penny[i])
                    dp[i][j] = dp[i-1][j] + dp[i][j-penny[i]];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n-1][aim];
    }

    public static void main(String[] args) {
        System.out.println(new Exchange().countWays(new int[]{1,2,4},3,3));
    }
}

class GoUpstairs {
    /*
    有n级台阶，一个人每次上一级或者两级，问有多少种走完n级台阶的方法。为了防止溢出，请将结果Mod 1000000007
给定一个正整数int n，请返回一个数，代表上楼的方式数。保证n小于等于100000。
测试样例：
1
返回：1
     */
    public int countWays(int n) {
        if (n<=2)return n;
        int mod = 1000000007;
        int []dp = new int[n+1];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++){
            dp[i] = (dp[i-1]+dp[i-2])%mod;
        }
        return dp[n];
    }
}

class MinimumPath {
    /*
    有一个矩阵map，它每个格子有一个权值。从左上角的格子开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，返回所有的路径中最小的路径和。
给定一个矩阵map及它的行数n和列数m，请返回最小路径和。保证行列数均小于等于100.
测试样例：
[[1,2,3],[1,1,1]],2,3
返回：4
     */
    public int getMin(int[][] map, int n, int m) {
        int [][]dp = new int[n][m];
        dp[0][0] = map[0][0];
        for (int i = 1; i < m; i++){
            dp[0][i] = dp[0][i-1]+map[0][i];
        }
        for (int i = 1; i < n; i++){
            dp[i][0] = dp[i-1][0]+map[i][0];
        }
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+map[i][j];
            }
        }
        return dp[n-1][m-1];
    }
}

class LongestIncreasingSubsequence {
    /*
    这是一个经典的LIS(即最长上升子序列)问题，请设计一个尽量优的解法求出序列的最长上升子序列的长度。

给定一个序列A及它的长度n(长度小于等于500)，请返回LIS的长度。

测试样例：
[1,4,2,5,3],5
返回：3
     */
    public int getLIS(int[] A, int n) {
        int[]dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (A[j] < A[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().getLIS(new int[]{2,1,4,3,5},5));
    }
}

class LCS {
    public int findLCS(String A, int n, String B, int m) {
        int[][]dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = 0;
        }
        for (int j = 0; j <= m; j++){
            dp[0][j] = 0;
        }
        for (int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if (A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(new LCS().findLCS("zynnqufc",8,"ddbauhkw",8));
    }
}

class Backpack {
    /*
    一个背包有一定的承重cap，有N件物品，每件都有自己的价值，记录在数组v中，也都有自己的重量，记录在数组w中，每件物品只能选择要装入背包还是不装入背包，要求在不超过背包承重的前提下，选出物品的总价值最大。
给定物品的重量w价值v及物品数n和承重cap。请返回最大总价值。
测试样例：
[1,2,3],[1,2,3],3,6
返回：6
     */
    public int maxValue(int[] w, int[] v, int n, int cap) {
        int [][]dp = new int[n+1][cap+1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = 0;
        }
        for (int j = 0; j <= cap; j++){
            dp[0][j] = 0;
        }
        for (int i = 1; i <= n; i++){
            for(int j = 1; j <= cap; j++){
                if (j - w[i-1]>=0)
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i-1]]+v[i-1]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][cap];
    }

    public static void main(String[] args) {
        System.out.println(new Backpack().maxValue(new int[]{1,2,3}, new int[]{1,2,3},3,6));
    }
}

class MinCost {
    /*
    对于两个字符串A和B，我们需要进行插入、删除和修改操作将A串变为B串，定义c0，c1，c2分别为三种操作的代价，请设计一个高效算法，求出将A串变为B串所需要的最少代价。
给定两个字符串A和B，及它们的长度和三种操作代价，请返回将A串变为B串所需要的最小代价。保证两串长度均小于等于300，且三种代价值均小于等于100。
测试样例：
"abc",3,"adc",3,5,3,100
返回：8
     */
    public int findMinCost(String A, int n, String B, int m, int c0, int c1, int c2) {
        int[][]dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = c1*i;
        }
        for (int i = 0; i <= m; i++){
            dp[0][i] = c0*i;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = Math.min(dp[i-1][j]+c1, dp[i][j-1]+c0);
                if (A.charAt(i-1) != B.charAt(j-1)){
                    dp[i][j] = Math.min(dp[i-1][j-1]+c2, dp[i][j]);
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(new MinCost().findMinCost("bbca",4,"cabacab",7,1,2,7));
    }
}