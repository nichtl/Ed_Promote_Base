package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */

public class DIGui {
    public static void main(String[] args) {


        System.out.println(ysfdg(10,4,3));

    }

    // 约瑟夫问题
    //n个人围成圈，依次编号为1,2,..,n，现在从1号开始依次报数，当报到m时，报m的人退出，下一个人重新从1报起，循环下去，问最后剩下那个人的编号是多少？
    /**
     *
     * @param sum     总人数
     * @param value   淘汰
     * @param n       循环几次
     * @return
     */
    static int ysfdg(int sum, int value, int n) {    //如果把每次剔除的后的人数重新组成一个环，那么就等于求每次新环的第一次剔除的人的编号，再反推还原 原始环的该人的编号
        // 该方法的思路为
        // 将人数看成 对应的环数 sum=10 即10人环
        // 这里把约瑟夫环看作一个逐渐递增的环，由 1 人环 至 二人环 。。三人环
        // 求得 环数 n 相对的逻辑第一环 ，即可由第一环导出 指定环淘汰人编号
        if( n == 1 )
        { return ( sum + value - 1 ) %sum;}  //返回 一次环时淘汰人的编号
        else{
            return ( ysfdg ( sum-1, value,n-1 ) + value ) %sum;}
        // 第n次淘汰人的编号 = 前n-1 +value） %sum
        // ((ysfdg(9,4,2)+4)%9)+4)%10
        // ((ysfdg(8,4,1))+4)%9)+4)%10
        // ysfdg(8,4,1) = (8+4-1)%8 = 3
    }
    //下标从0开始
    static int ysfdg2(int sum, int value){
        int x=0; //第一环剔除的结果
        for(int i=2; i< sum;i++){
            x=(x+value)%i;  //第 i 环剔除的结果
        }
        return x;
    }




}
/*
8queue
* */
class Queen8 {
    // 定义皇后数量
     static  int count =0;

    int max = 8;
    int[] arr = new int[max]; //arr[8] 利用下标表示 第几行，值存放每一行的列数

    public static void main(String[] args) {
        Queen8 Queen8 = new Queen8();
        Queen8.check(0);//从 0 开始
        System.out.printf("解法总数%d",count);
    }
    //八皇后
    private  void queen(int n) {
        // n为行数
        //给定条件跳出
        if(n == 8){
            print();
            return;
        }
        // 代表列数
        for( int i=0; i<8;i++){
            // 循环 一行 的 8 列
            // 如果 碰到 那一列可行了 继续推下一行的 可行 列,否则 列数继续循环 直到结束
            arr[n] = i;  //  n 行i 列
            if(judge(n)){
                check(n+1);       //碰到某一行列不满足等待for完成 回退到它的上一级 ，，上一级继续，列数加一，判断，如此一直回退
            }

        }
        return ;

    }


    // 模拟放置第n个棋子
    // arr【n】 利用小标表示第n个皇后
    // 从 第 n个皇后开始到第8皇后的摆法
    private void check(int n) { // n=0
        if (n == max) {
            print();
            // n =max 说明已经放完8个皇后结束
            return;  //此时返回后的 arr[n] 已经保存了遍历好的 位置
        }
        //依次放入皇后并判断
        for (int i = 0; i < max; i++) { // 01234567
            arr[n] = i;  // 代表列数            // 当 第一次得到了正确的解  check（6）--》 check（7）
                                               // check(7) 直接返回了一个正确的 解 回到了check（6）
                                              // check（6） 的列加+1 发现不通过 for循环完成回到check（5） 同样check（5） 不同过 check（4）一直回到check（0）才通过
            if (judge(n)) {                   // 此时 于是又重新从check（0） 开始继续推进 0 行 n 列的解法计算
                //不冲突开始 递归
                check(n + 1); // n+1
            }
            //冲突的话 继续执行 i++ 自动换到 本行的下一列
        }
    }

    // 关于递归的回溯的问题
    //当 check（7）完成后


    //在放置第n个皇后时与前面放过的n-1 个 皇后比较 是否冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // i == n 判断第 n 个皇后是否和n - 1个皇后再同一列 若 n行 和 前面 i 行存放的列值是一样的就是同一列
            //对角线的判断 其实可以知道 对角线的规律为（1，1） (2，2) （3，3）  ；行号 = 列号
            // 所以判断不同的行的点是否在 一条斜线上可 让  （3，3）-（1，1） 其取绝对值一样即为在一条斜线上
            // n代表着当前是第几个皇后，i= n 代表 n 与 n之前的某一个 i 重复 即为同一列
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }





    private void print(){
        count++;
        for (int i =0; i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}