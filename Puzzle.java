import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class HorseRace {
    /*
    作为一个马场的主人，你要安排你的n匹赛马和另一个马场的n匹马比赛。你已经知道了对方马场的出战表，即参加每一场的马的强壮程度。当然你也知道你自己的所有马的强壮程度。我们假定比赛的结果直接由马的强壮程度决定，即更壮的马获胜(若相同则双方均不算获胜)，请你设计一个策略，使你能获得尽量多的场次的胜利。
给定对方每场比赛的马的强壮程度oppo及你的所有马的强壮程度horses(强壮程度为整数，且数字越大越强壮)同时给定n，请返回最多能获胜的场次。
测试样例：
[1,2,3],[1,2,3],3
返回：2
     */
    public int winMost(int[] oppo, int[] horses, int n) {
        int cnt = 0;
        int i = 0, j = 0;
        Arrays.sort(oppo);
        Arrays.sort(horses);
        while (i < n && j < n){
            if (oppo[i] < horses[j]){
                i++;j++;
                cnt++;
            }else{
                j++;
            }
        }
        return cnt;
    }
}

class Jump {
    /*
    你和你的朋友正在玩棋子跳格子的游戏，而棋盘是一个由n个格子组成的长条，你们两人轮流移动一颗棋子，每次可以选择让棋子跳1-3格，先将棋子移出棋盘的人获得胜利。我们知道你们两人都会采取最优策略，现在已知格子数目，并且初始时棋子在第一格由你操作。请你计算你是否能获胜。

给定格子的数目n(n为不超过300的正整数)。返回一个整数，1代表能获胜，0代表不能获胜。

测试样例：
3
返回：1
     */
    public int checkWin(int n) {
        n--;
        return n % 4 ==0 ? 0 : 1;
    }
}

class Game {
    /*
    A与B做游戏。 在一个n*m的矩阵中的出发点是（1，m），终点是（n,1），
    规则是只能向左移动一格，向下一格或向左下移动一格，先走到终点的为winner。 A先走。
    给定两个整数n和m，请返回最后的获胜者的名字(A或B)。
    测试样例：
    5 3
    返回：B
     */
    public char getWinner(int n, int m) {
        return (n&1)==1&&(m&1)==1?'B':'A';

    }
}

class Clear {
    /*
    现在有一个整数数组，其元素值均为1-n范围内的某个整数，现在你和你的朋友在玩一个游戏，游戏的目的是把数组清空，你们轮流操作，你是先手，每次操作你可以删除数组中值为某个数的元素任意多个(当然数组中值为这个数的元素个数应大于等于你删除的个数,且你至少要删除一个数)。最先把数组清空的人获得胜利。假设你们都采取最优策略，请你计算你能否获得胜利。

给定一个整数数组A和元素个数n。请返回一个整数，1代表你能获胜，0代表你不能获胜。

测试样例：
[1,1,1]
返回：1
     */
    public int getWinner(int[] A, int n) {
        HashMap<Integer,Integer>hashMap = new HashMap<>();
        for (int i = 0; i < A.length; i++){
            if (hashMap.containsKey(A[i])){
                hashMap.put(A[i], hashMap.get(A[i])+1);
            }else{
                hashMap.put(A[i],1);
            }
        }
        int res = 0;
        for (Integer i : hashMap.keySet()){
            res ^= hashMap.get(i);
        }
        return res>0?1:0;
    }

    public static void main(String[] args) {
        System.out.println(new Clear().getWinner(new int[]{1,1,1},3));
    }
}