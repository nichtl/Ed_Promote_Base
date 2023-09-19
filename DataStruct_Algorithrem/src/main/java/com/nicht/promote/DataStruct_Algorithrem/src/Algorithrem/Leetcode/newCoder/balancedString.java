package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.concurrent.Callable;

//有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
//
// 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
//
//
//
// 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
//
// 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
//
// 请返回待替换子串的最小可能长度。
//
// 如果原字符串自身就是一个平衡字符串，则返回 0。
//
//
//
// 示例 1：
//
//
//输入：s = "QWER"
//输出：0
//解释：s 已经是平衡的了。
//
// 示例 2：
//
//
//输入：s = "QQWE"
//输出：1
//解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
//
//
// 示例 3：
//
//
//输入：s = "QQQW"
//输出：2
//解释：我们可以把前面的 "QQ" 替换成 "ER"。
//
//
// 示例 4：
//
//
//输入：s = "QQQQ"
//输出：3
//解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10^5
// s.length 是 4 的倍数
// s 中只含有 'Q', 'W', 'E', 'R' 四种字符
//
//
// Related Topics 字符串 滑动窗口 👍 226 👎 0
// lt 1234
 /* @Description
 * @Date 2023/2/13
 */
public class balancedString {
    public static void main(String[] args) {
     minimumRecolors("WBBWWBBWBW",7);

    }
    //2379
    public static int minimumRecolors(String blocks, int k) {
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
        }
        int ans = cnt;
        for (int i = k; i < blocks.length(); ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
            cnt -= blocks.charAt(i - k) == 'W' ? 1 : 0;
            ans = Math.min(ans, cnt);
        }
        return ans;
    }

//    public int balancedString(String s) {
//      int n  = s.length()/4;
//
//
//
//
//    }



    public  class  Task implements Callable{

        @Override
        public Object call() throws Exception {
            return null;
        }
    }

}
