package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。
//
// 给你两个整数 tomatoSlices 和 cheeseSlices，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：
//
//
// 巨无霸汉堡：4 片番茄和 1 片奶酪
// 小皇堡：2 片番茄和 1 片奶酪
//
//
// 请你以 [total_jumbo, total_small]（[巨无霸汉堡总数，小皇堡总数]）的格式返回恰当的制作方案，使得剩下的番茄片
//tomatoSlices 和奶酪片 cheeseSlices 的数量都是 0。
//
// 如果无法使剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量为 0，就请返回 []。
//
//
//
// 示例 1：
//
// 输入：tomatoSlices = 16, cheeseSlices = 7
//输出：[1,6]
//解释：制作 1 个巨无霸汉堡和 6 个小皇堡需要 4*1 + 2*6 = 16 片番茄和 1 + 6 = 7 片奶酪。不会剩下原料。
//
//
// 示例 2：
//
// 输入：tomatoSlices = 17, cheeseSlices = 4
//输出：[]
//解释：只制作小皇堡和巨无霸汉堡无法用光全部原料。
//
//
// 示例 3：
//
// 输入：tomatoSlices = 4, cheeseSlices = 17
//输出：[]
//解释：制作 1 个巨无霸汉堡会剩下 16 片奶酪，制作 2 个小皇堡会剩下 15 片奶酪。
//
//
// 示例 4：
//
// 输入：tomatoSlices = 0, cheeseSlices = 0
//输出：[0,0]
//
//
// 示例 5：
//
// 输入：tomatoSlices = 2, cheeseSlices = 1
//输出：[0,1]
//
//
//
//
// 提示：
//
//
// 0 <= tomatoSlices <= 10^7
// 0 <= cheeseSlices <= 10^7
//
//
// Related Topics 数学 👍 59 👎 0

//tomatoSlices 和 cheeseSlices
public class numOfBurgers {

    // x big   y small
    // 4x + 2y = tomatoSlices
    // x + y = cheeseSlices
    //  (4x + 2y  - 2x - 2y = tomatoSlices - 2*cheeseSlices )
    //  =>  2x = tomatoSlices - 2*cheeseSlices  =>  x = 1/2 * tomatoSlices - cheeseSlices

    // 4x + 4y - 4x -2y = 4*cheeseSlices - tomatoSlices
    // => 2y = 4*cheeseSlices - tomatoSlices
    // => y = 2*cheeseSlices -1/2*tomatoSlices

    //x = 1/2 * tomatoSlices - cheeseSlices
    //y = 2*cheeseSlices -1/2*tomatoSlices

    // x >=0 y>=0  &&  x y tomatoSlices %2=0
    //    tomatoSlices  >= cheeseSlices *2
    //    4*cheeseSlices>= tomatoSlices
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
      if(tomatoSlices %2 !=0  || tomatoSlices < cheeseSlices *2 || tomatoSlices> 4*cheeseSlices ) {
       return  new ArrayList<>();
      }
          return Arrays.asList((1/2 * tomatoSlices - cheeseSlices),(2*cheeseSlices -1/2*tomatoSlices));
    }
}
