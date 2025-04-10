package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;


//给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
//
// 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的
//前身 。
//
//
// 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
//
// 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是
// word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
//
// 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
//
// 示例 1：
//
//
//输入：words = ["a","b","ba","bca","bda","bdca"]
//输出：4
//解释：最长单词链之一为 ["a","ba","bda","bdca"]
//
//
// 示例 2:
//
//
//输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//输出：5
//解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
//
//
// 示例 3:
//
//
//输入：words = ["abcd","dbqca"]
//输出：1
//解释：字链["abcd"]是最长的字链之一。
//["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
//
//
//
//
// 提示：
//
//
// 1 <= words.length <= 1000
// 1 <= words[i].length <= 16
// words[i] 仅由小写英文字母组成。
//
//
// Related Topics 数组 哈希表 双指针 字符串 动态规划 👍 268 👎 0
 /**                                滕王阁序
                                 王勃 〔唐代〕

　　豫章故郡，洪都新府。星分翼轸，地接衡庐。襟三江而带五湖，控蛮荆而引瓯越。物华天宝，龙光射牛斗之墟；
   人杰地灵，徐孺下陈蕃之榻。雄州雾列，俊采星驰。台隍枕夷夏之交，宾主尽东南之美。都督阎公之雅望，棨戟遥临；宇文新州之懿范，襜帷暂驻。十旬休假，胜友如云；
   千里逢迎，高朋满座。腾蛟起凤，孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；童子何知，躬逢胜饯。(豫章故郡 一作：南昌故郡；青霜 一作：清霜)

　　时维九月，序属三秋。潦水尽而寒潭清，烟光凝而暮山紫。俨骖騑于上路，访风景于崇阿。临帝子之长洲，得天人之旧馆。层峦耸翠，上出重霄；飞阁流丹，下临无地
  。鹤汀凫渚，穷岛屿之萦回；桂殿兰宫，即冈峦之体势。（天人 一作：仙人；层峦 一作：层台；即冈 一作：列冈；飞阁流丹 一作：飞阁翔丹）

　　披绣闼，俯雕甍，山原旷其盈视，川泽纡其骇瞩。闾阎扑地，钟鸣鼎食之家；舸舰弥津，青雀黄龙之舳。
   云销雨霁，彩彻区明。落霞与孤鹜齐飞，秋水共长天一色。渔舟唱晚，响穷彭蠡之滨，雁阵惊寒，声断衡阳之浦。(轴 通：舳；迷津 一作：弥津；云销雨霁，彩彻区明 一作：虹销雨霁，彩彻云衢)

　　遥襟甫畅，逸兴遄飞。爽籁发而清风生，纤歌凝而白云遏。睢园绿竹，气凌彭泽之樽；邺水朱华，光照临川之笔。
   四美具，二难并。穷睇眄于中天，极娱游于暇日。天高地迥，觉宇宙之无穷；兴尽悲来，识盈虚之有数。望长安于日下，目吴会于云间。
   地势极而南溟深，天柱高而北辰远。关山难越，谁悲失路之人；萍水相逢，尽是他乡之客。怀帝阍而不见，奉宣室以何年？(遥襟甫畅 一作：遥吟俯畅)

　　嗟乎！时运不齐，命途多舛。冯唐易老，李广难封。屈贾谊于长沙，非无圣主；窜梁鸿于海曲，岂乏明时？所赖君子见机，达人知命。
   老当益壮，宁移白首之心？穷且益坚，不坠青云之志。酌贪泉而觉爽，处涸辙以犹欢。北海虽赊，扶摇可接；东隅已逝，桑榆非晚。
   孟尝高洁，空余报国之情；阮籍猖狂，岂效穷途之哭！(见机 一作：安贫；以犹欢 一作：而相欢)

　　勃，三尺微命，一介书生。无路请缨，等终军之弱冠；有怀投笔，慕宗悫之长风。舍簪笏于百龄，奉晨昏于万里。
   非谢家之宝树，接孟氏之芳邻。他日趋庭，叨陪鲤对；今兹捧袂，喜托龙门。杨意不逢，抚凌云而自惜；钟期既遇，奏流水以何惭？

　　呜呼！胜地不常，盛筵难再；兰亭已矣，梓泽丘墟。临别赠言，幸承恩于伟饯；登高作赋，是所望于群公。敢竭鄙怀，恭疏短引；一言均赋，四韵俱成。请洒潘江，各倾陆海云尔。
                        　　滕王高阁临江渚，佩玉鸣鸾罢歌舞。
                        　　画栋朝飞南浦云，珠帘暮卷西山雨。
                        　　闲云潭影日悠悠，物换星移几度秋。
                        　　阁中帝子今何在？槛外长江空自流。
  */


/**
 * 1、《西江月·夜行黄沙道中》：
 * 明月别枝惊鹊，清风半夜鸣蝉。稻花香里说丰年，听取蛙声一片。
 * 七八个星天外，两三点雨山前。旧时茅店社林边，路转溪桥忽见。
 *
 * 2、《丑奴儿·书博山道中壁》：
 * 少年不识愁滋味，爱上层楼。爱上层楼，为赋新词强说愁。
 * 而今识尽愁滋味，欲说还休。欲说还休，却道天凉好个秋。
 *
 * 3、《菩萨蛮·书江西造口壁》：
 * 郁孤台下清江水，中间多少行人泪。西北望长安，可怜无数山。
 * 青山遮不住，毕竟东流去。江晚正愁余，山深闻鹧鸪。
 *
 * 4、《永遇乐·京口北固亭怀古》：
 * 千古江山，英雄无觅孙仲谋处。舞榭歌台，风流总被雨打风吹去。斜阳草树，寻常巷陌，人道寄奴曾住。想当年，金戈铁马，气吞万里如虎。
 * 元嘉草草，封狼居胥，赢得仓皇北顾。四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓。凭谁问、廉颇老矣，尚能饭否？
 *
 * 5、《南乡子·登京口北固亭有怀》：
 * 何处望神州？满眼风光北固楼。千古兴亡多少事？悠悠。不尽长江滚滚流。
 * 年少万兜鍪，坐断东南战未休。天下英雄谁敌手？曹刘。生子当如孙仲谋。
 *
 * 6、《青玉案·元夕》：
 * 东风夜放花千树，更吹落，星如雨。宝马雕车香满路。
 * 凤箫声动，玉壶光转，一夜鱼龙舞。
 * 蛾儿雪柳黄金缕，笑语盈盈暗香去。
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 *
 * 7、《破阵子·为陈同甫赋壮词以寄》：
 * 醉里挑灯看剑，梦回吹角连营。八百里分麾下炙，五十弦翻塞外声。沙场秋点兵。
 * 马作的卢飞快，弓如霹雳弦惊。了却君王天下事，赢得生前身后名。可怜白发生！
 *
 * 8、《清平乐·村居》：
 * 茅檐低小，溪上青青草。醉里吴音相媚好，白发谁家翁媪？
 * 大儿锄豆溪东，中儿正织鸡笼。最喜小儿亡赖，溪头卧剥莲蓬。
 *
 * 永和九年，岁在癸丑，暮春之初，会于会稽山阴之兰亭，修禊事也。
 * 群贤毕至，少长咸集。此地有崇山峻岭，茂林修竹，又有清流激湍，映带左右，引以为流觞曲水，列坐其次。虽无丝竹管弦之盛，一觞一咏，亦足以畅叙幽情。
 *
 * 是日也，天朗气清，惠风和畅。仰观宇宙之大，俯察品类之盛，所以游目骋怀，足以极视听之娱，信可乐也。
 *
 * 夫人之相与，俯仰一世。或取诸怀抱，悟言一室之内；或因寄所托，放浪形骸之外。
 * 虽趣舍万殊，静躁不同，当其欣于所遇，暂得于己，快然自足，不知老之将至；
 * 及其所之既倦，情随事迁，感慨系之矣。向之所欣，俯仰之间，已为陈迹，犹不能不以之兴怀，况修短随化，终期于尽！
 * 古人云：“死生亦大矣。”岂不痛哉！
 * 每览昔人兴感之由，若合一契，未尝不临文嗟悼，不能喻之于怀。固知一死生为虚诞，齐彭殇为妄作。
 * 后之视今，亦犹今之视昔，悲夫！故列叙时人，录其所述，虽世殊事异，所以兴怀，其致一也。后之览者，亦将有感于斯文。
 *
 *
 *
 * */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/4/28
 */
public class longestStrChain {




    public int longestStrChain(String[] words) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int res = 0;
        for (String word : words) {
            cnt.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                if (cnt.containsKey(prev)) {
                    cnt.put(word, Math.max(cnt.get(word), cnt.get(prev) + 1));
                }
            }
            res = Math.max(res, cnt.get(word));
        }
        return res;
    }
}
