package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

/**
 * 功能描述：
 *
 * @author xujian8
 */
public class NumPrimeArrangements {

    //请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
//
// 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
//
// 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
//
//
//
// 示例 1：
//
// 输入：n = 5
//输出：12
//解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
//
//
// 示例 2：
//
// 输入：n = 100
//输出：682289015
//
//
//
//
// 提示：
//
//
// 1 <= n <= 100
//
// Related Topics 数学 👍 70 👎 0


//    public int numPrimeArrangements(int n) {
//    return  1;
//    }

//847865&32269&18980812862,1908017&15455&18653180369,2430614&31683&18961178308,2510408&32269&18912966556,2603714&16680&18674058188,2669962&16176&18986152062,5469776&32269&18655059158,6446098&32269&18655202012,6575669&15939&18690895648,7525941&16680&18636647656,7707059&16680&18660156303,7712268&14144&18817379589,9279208&16680&18810476587,9385607&32269&18992830476,9779958&18451&18678970678,12344883&14144&18810286406,12411553&15455&18659206911,12558443&16680&18746073581,14508117&27976&18648647658,14704258&15455&18930253156,14782655&16176&18946717262,16625764&32448&18942166011,16647595&15455&18842581368,15258574&15936&18955595451,18738221&31683&18676166969

//18561306&15455&15961169887,802812&31683&18211141674,959552&16176&18601679347,667066&24730&18621682583,1098537&8763&15905198118,1579335&16176&15950576439,1527227&13387&18600327153,1760491&14144&18049750359,3106469&15138&18611351989,3199697&15455&18118805006,3847671&31683&18001834880,4575577&31683&18601650810,4806830&15455&15961192188,7427844&15455&18017623948,7526564&14144&15914409999,8008818&16680&18010650227,8328650&31683&15922298388,8722942&15455&18116446800,10273747&14144&18217189369,11595120&15455&18600973541,11606076&16176&15934351515,11050624&31683&18008590043,12578037&16176&18060163687,12762492&14144&18615540987,17491463&16176&18627163777

//927711&31683&15307155151,1038376&16680&15801789828,1319148&16680&15295318359,1460678&32269&15545241111,2238268&13040&15295765740,2485064&15455&13991292912,2495896&31757&13995681088,3000140&32269&15320362926,3004687&31683&15828651265,3147259&16680&15884493103,5469610&15455&15161098214,7757866&16680&15305163061,10053706&15455&15864536972,10667008&14144&15226969986,12777745&16176&15298658365,14001385&16680&15327334447,14347732&16680&15855629997,14372218&7953&15202125506,15915641&16176&15101037252,16605792&15455&15057952003,15431008&31683&13993961658,15451655&15455&15136229529,16889711&16176&15307106001,17000379&32269&13999610620,17297539&16680&15161197818

//206322&31683&13867118979,107745&15455&13901784446,700909&27976&13909681040,731133&32269&13901512719,784280&16680&13983968548,1222941&32269&13889782222,1086696&15455&13952058983,1104429&14144&13951650773,2190186&14144&13901089109,2756117&15455&13951675384,3153089&32269&13951740248,5138189&15455&13871388344,5971945&15455&13952003133,6549969&14144&13952315630,7251007&16680&13889317158,7604723&31683&13905419439,7982695&31683&13858055530,9210137&24256&13911462211,8909585&16176&13862550688,9996873&31683&13984065315,10720538&14144&13904718736,13606773&16176&13912982481,16951350&32269&13901366904,17305703&16176&13957189882,18684821&31683&13836694980

//230896&16680&13811723570,241038&14144&13806502611,241238&15455&13770586798,323657&22496&13818159206,445020&32269&13761915955,462566&24730&13764001890,928033&16176&13805740003,1454665&2677&13770708406,1977075&16176&13812460888,2743043&1707&13816007368,2999932&16680&13817896615,3235701&14144&13808192032,3986160&17344&13817602953,5771409&14144&13801686867,6024696&14144&13702556581,6260103&16680&13817707481,6286650&14144&13833537610,6617172&17950&13810392027,6849616&16176&13823267805,8768283&16176&13802096171,10665908&3853&13813801655,12512225&21517&13709900604,16856298&31683&13815824022,15284639&3485&13820871386,18635021&15455&13807823070

//87371&1166&13601327537,143399&21517&13581772655,796942&15455&13683175695,907719&16176&13671100663,1486632&16176&13606189311,1559522&32269&13671089273,1822726&16680&13681728027,2265002&24730&13564820336,2149830&15455&13641999629,2195626&24730&13609322088,2665363&31994&13661137168,2801397&16176&13608545722,2865453&14144&13701123152,3073181&32269&13601166692,3144066&15455&13678599699,3225032&27976&13681793063,3416858&16176&13600823902,5455168&31994&13519966006,8812676&16176&13536994995,10423429&15455&13608331971,10426026&32269&13651098405,15386366&32269&13611532468,15498660&31683&13651810486,17161299&16680&13681090437,18583354&17580&13573205797

//167107&16176&13011836601,482542&16680&13402088755,838810&3137&13386109187,850949&32269&13311725599,1535829&23596&13301603760,2675763&16680&13062540552,3308012&16176&13151012889,3386072&15939&13330946810,4733617&11773&13021032008,5596918&21517&13382021739,5248084&16176&13338000836,6679149&31683&13382802888,6985649&14144&13011065037,7328472&21390&13391064266,7580234&25164&13186096206,7673360&16176&13306400922,9013799&32269&13346216125,9527636&15455&13170601771,9579501&16176&13331997605,9606964&16176&13395600588,10411935&15455&13002181003,10280642&31683&13001162410,13362861&15455&13211718869,13537068&31808&13167327618,14443672&16176&13196611126,18555225&16176&13502002858,18131268&14144&13041017589

//
//String a = "{\"answerFlag\":1,\"answerTime\":\"2022-09-07 09:24:02\",\"batchName\":\"机器人意向目的地-内蒙古-0906-A\",\"callId\":\"41071167546844940\",\"callTime\":\"00:00:42\",\"company\":\"\",\"decryptPhone\":\"15692168367\",\"dialogs\":[{\"botSay\":\"诶（空0.5秒），您好（空1.5秒）我是途牛旅游网的福利官，最近我们内蒙古草原、胡杨林等产品在做福利爆款活动，长线、周边跟团等都是高满意度产品，感兴趣的话发您看下？\",\"botSayTime\":\"2022-09-07 09:24:02\",\"callBackId\":5299213,\"custSay\":\"现在那个呼伦贝尔那那那边宴请怎么样\",\"custSayTime\":\"2022-09-07 09:24:23\",\"detailId\":22539628,\"domainName\":\"开场白\",\"id\":0,\"setBotSay\":true,\"setBotSayTime\":true,\"setBranchId\":false,\"setBranchName\":false,\"setCallBackId\":true,\"setCustSay\":true,\"setCustSayTime\":true,\"setDetailId\":true,\"setDomainId\":false,\"setDomainName\":true,\"setId\":false},{\"botSay\":\"我是那个“要旅游找途牛”的途牛旅游网的，最近内蒙古方向在做福利爆款活动，稍后我加您的微信可以了解下，您的微信号是手机号吧？\",\"botSayTime\":\"2022-09-07 09:24:23\",\"callBackId\":5299213,\"custSay\":\"不是那个现在内蒙古很近的吗\",\"custSayTime\":\"2022-09-07 09:24:25\",\"detailId\":22539689,\"domainName\":\"解释开场白\",\"id\":0,\"setBotSay\":true,\"setBotSayTime\":true,\"setBranchId\":false,\"setBranchName\":false,\"setCallBackId\":true,\"setCustSay\":true,\"setCustSayTime\":true,\"setDetailId\":true,\"setDomainId\":false,\"setDomainName\":true,\"setId\":false},{\"botSay\":\"那您微信号是多少，您说我记一下（空5秒）\",\"botSayTime\":\"2022-09-07 09:24:25\",\"callBackId\":5299213,\"custSay\":\"我们,我微信号我不是不清楚哎\",\"custSayTime\":\"2022-09-07 09:24:35\",\"detailId\":22539696,\"domainName\":\"流程6.2\",\"id\":0,\"setBotSay\":true,\"setBotSayTime\":true,\"setBranchId\":false,\"setBranchName\":false,\"setCallBackId\":true,\"setCustSay\":true,\"setCustSayTime\":true,\"setDetailId\":true,\"setDomainId\":false,\"setDomainName\":true,\"setId\":false},{\"botSay\":\"没关系，那我发个短信给您看下吧，您稍后留意下短信通知，不打扰您了，祝您生活愉快！再见！\",\"botSayTime\":\"2022-09-07 09:24:35\",\"callBackId\":5299213,\"detailId\":22539736,\"domainName\":\"结束-不记得微信\",\"id\":0,\"setBotSay\":true,\"setBotSayTime\":true,\"setBranchId\":false,\"setBranchName\":false,\"setCallBackId\":true,\"setCustSay\":false,\"setCustSayTime\":false,\"setDetailId\":true,\"setDomainId\":false,\"setDomainName\":true,\"setId\":false}],\"dialogsIterator\":[{\"$ref\":\"$.dialogs[0]\"},{\"$ref\":\"$.dialogs[1]\"},{\"$ref\":\"$.dialogs[2]\"},{\"$ref\":\"$.dialogs[3]\"}],\"dialogsSize\":4,\"duration\":57,\"hangupTime\":\"2022-09-07 09:24:44\",\"id\":5299213,\"label\":\"C\",\"labelReason\":\"需发短信\",\"mainVoice\":\"https://crm3.guiji.ai/group2/M00/21/FE/rBACLWMX8t2AIYnMAAGhYE17bWY220.wav\",\"myend\":\"客户挂断\",\"name\":\"\",\"partVoices\":[\"https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8seAeXpOAAEC7PvM8nM089.wav\",\"https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8sqAK9kFAACbLPv7AEw676.wav\",\"https://crm3.guiji.ai/group2/M00/21/FE/rBACLWMX8s-ACKsYAAAzbL68bbQ802.wav\",\"https://crm3.guiji.ai/group1/M00/23/D3/rBACEWMX8tOAXTtEAAC5LBIuLJc256.wav\"],\"partVoicesIterator\":[\"https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8seAeXpOAAEC7PvM8nM089.wav\",\"https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8sqAK9kFAACbLPv7AEw676.wav\",\"https://crm3.guiji.ai/group2/M00/21/FE/rBACLWMX8s-ACKsYAAAzbL68bbQ802.wav\",\"https://crm3.guiji.ai/group1/M00/23/D3/rBACEWMX8tOAXTtEAAC5LBIuLJc256.wav\"],\"partVoicesSize\":4,\"phone\":\"815680823799\",\"remarks\":\"-1\",\"scanTaskId\":264793,\"setAnswerFlag\":true,\"setAnswerTime\":true,\"setBatchName\":true,\"setCallId\":true,\"setCallTime\":true,\"setCompany\":true,\"setDecryptPhone\":true,\"setDialogs\":true,\"setDuration\":true,\"setHangupTime\":true,\"setId\":true,\"setLabel\":true,\"setLabelReason\":true,\"setMainVoice\":true,\"setMyend\":true,\"setName\":true,\"setPartVoices\":true,\"setPhone\":true,\"setRemarks\":true,\"setScanTaskId\":true,\"setStartTime\":true,\"setTemplateId\":true,\"setTemplateName\":true,\"startTime\":\"2022-09-07 09:23:47\",\"templateId\":\"nmg_yxmddqr_A_39391_en\",\"templateName\":\"内蒙古_意向目的地确认_A\"} "
//    {"answerFlag":1,"answerTime":"2022-09-07 09:24:02","batchName":"机器人意向目的地-内蒙古-0906-A","callId":"41071167546844940","callTime":"00:00:42","company":"","decryptPhone":"15692168367","dialogs":[{"botSay":"诶（空0.5秒），您好（空1.5秒）我是途牛旅游网的福利官，最近我们内蒙古草原、胡杨林等产品在做福利爆款活动，长线、周边跟团等都是高满意度产品，感兴趣的话发您看下？","botSayTime":"2022-09-07 09:24:02","callBackId":5299213,"custSay":"现在那个呼伦贝尔那那那边宴请怎么样","custSayTime":"2022-09-07 09:24:23","detailId":22539628,"domainName":"开场白","id":0,"setBotSay":true,"setBotSayTime":true,"setBranchId":false,"setBranchName":false,"setCallBackId":true,"setCustSay":true,"setCustSayTime":true,"setDetailId":true,"setDomainId":false,"setDomainName":true,"setId":false},{"botSay":"我是那个“要旅游找途牛”的途牛旅游网的，最近内蒙古方向在做福利爆款活动，稍后我加您的微信可以了解下，您的微信号是手机号吧？","botSayTime":"2022-09-07 09:24:23","callBackId":5299213,"custSay":"不是那个现在内蒙古很近的吗","custSayTime":"2022-09-07 09:24:25","detailId":22539689,"domainName":"解释开场白","id":0,"setBotSay":true,"setBotSayTime":true,"setBranchId":false,"setBranchName":false,"setCallBackId":true,"setCustSay":true,"setCustSayTime":true,"setDetailId":true,"setDomainId":false,"setDomainName":true,"setId":false},{"botSay":"那您微信号是多少，您说我记一下（空5秒）","botSayTime":"2022-09-07 09:24:25","callBackId":5299213,"custSay":"我们,我微信号我不是不清楚哎","custSayTime":"2022-09-07 09:24:35","detailId":22539696,"domainName":"流程6.2","id":0,"setBotSay":true,"setBotSayTime":true,"setBranchId":false,"setBranchName":false,"setCallBackId":true,"setCustSay":true,"setCustSayTime":true,"setDetailId":true,"setDomainId":false,"setDomainName":true,"setId":false},{"botSay":"没关系，那我发个短信给您看下吧，您稍后留意下短信通知，不打扰您了，祝您生活愉快！再见！","botSayTime":"2022-09-07 09:24:35","callBackId":5299213,"detailId":22539736,"domainName":"结束-不记得微信","id":0,"setBotSay":true,"setBotSayTime":true,"setBranchId":false,"setBranchName":false,"setCallBackId":true,"setCustSay":false,"setCustSayTime":false,"setDetailId":true,"setDomainId":false,"setDomainName":true,"setId":false}],"dialogsIterator":[{"$ref":"$.dialogs[0]"},{"$ref":"$.dialogs[1]"},{"$ref":"$.dialogs[2]"},{"$ref":"$.dialogs[3]"}],"dialogsSize":4,"duration":57,"hangupTime":"2022-09-07 09:24:44","id":5299213,"label":"C","labelReason":"需发短信","mainVoice":"https://crm3.guiji.ai/group2/M00/21/FE/rBACLWMX8t2AIYnMAAGhYE17bWY220.wav","myend":"客户挂断","name":"","partVoices":["https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8seAeXpOAAEC7PvM8nM089.wav","https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8sqAK9kFAACbLPv7AEw676.wav","https://crm3.guiji.ai/group2/M00/21/FE/rBACLWMX8s-ACKsYAAAzbL68bbQ802.wav","https://crm3.guiji.ai/group1/M00/23/D3/rBACEWMX8tOAXTtEAAC5LBIuLJc256.wav"],"partVoicesIterator":["https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8seAeXpOAAEC7PvM8nM089.wav","https://crm3.guiji.ai/group1/M00/23/D1/rBACEGMX8sqAK9kFAACbLPv7AEw676.wav","https://crm3.guiji.ai/group2/M00/21/FE/rBACLWMX8s-ACKsYAAAzbL68bbQ802.wav","https://crm3.guiji.ai/group1/M00/23/D3/rBACEWMX8tOAXTtEAAC5LBIuLJc256.wav"],"partVoicesSize":4,"phone":"815680823799","remarks":"-1","scanTaskId":264793,"setAnswerFlag":true,"setAnswerTime":true,"setBatchName":true,"setCallId":true,"setCallTime":true,"setCompany":true,"setDecryptPhone":true,"setDialogs":true,"setDuration":true,"setHangupTime":true,"setId":true,"setLabel":true,"setLabelReason":true,"setMainVoice":true,"setMyend":true,"setName":true,"setPartVoices":true,"setPhone":true,"setRemarks":true,"setScanTaskId":true,"setStartTime":true,"setTemplateId":true,"setTemplateName":true,"startTime":"2022-09-07 09:23:47","templateId":"nmg_yxmddqr_A_39391_en","templateName":"内蒙古_意向目的地确认_A"}
//
//    INSERT INTO taskflow_task_assgin
//            (
//                    task_sub_id,
//                    saler_id,
//                    assign_status,
//                    add_saler_id,
//                    add_time,
//                    update_time,
//                    update_saler_id)
//    values(
//
//    )
//
//
//        124671,124672,124673,124674,124676,124677,124678,124680,124682,124684,124686,124688,124693,124694,124695,124697,124699,124700,124704,124708,124709,124710,124711,124712,124714,124717,124720,124723,124725,124726,124729,124732,124733,124734,124737,124739,124741,124743,124744,124748,124749,124750,124752,124763
}
