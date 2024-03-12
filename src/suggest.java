//定义基础牌型为ABC,AAB,ABCD,AABC,AABB,ABCDE,AABCD,AABBC,ABCDEF,其策略为基础策略,已穷举//
//suggest类希望把其他牌型化归为基础牌型,从而给出策略//
//基础牌型和其他牌型中都没有顺子和刻子//
//可以证明对同种花色的九种牌,持有的牌的种类大于等于七种牌时,必然会有三张是顺子,从而化归为基础牌型//
//其他牌型内的总牌数大于等于6个时,进行拆分化归，取所有情况的最佳策略//
/*下面给出其他牌型的化归策略:
    AABCDE->VALmin{AAB,CDE;AAC,BDE;AAD,BCE;AAE,BCD;ABC,ADE;ABD,ACE;ABE,ACD}->
    AABBCD->VALmin{AAB,BCD;AAC,BBD;AAD,BBC;ABB,ACD;ABD,ABC}->VALmin{AAB,ABC;AAB,AAB;AAB,AAB;AAB,ABC;ABC,ABC}
    AABBCC->VALmin{AAB,BCC;AAC,BBC;ABB,ACC;ABC,ABC}->VALmin{AAB,AAB;AAB,AAB;AAB,AAB;ABC,ABC}
    AABCDEF->VALmin{}->VALmin{}
    AABBCDE->VALmin{}->VALmin{}
    AABBCCD->VALmin{}->VALmin{}
    AABBCDEF->VALmin{}->VALmin{}
    AABBCCDE->VALmin{}->VALmin{}
    AABBCCDD->VALmin{}->VALmin{}
    AABBCCDEF->VALmin{}->VALmin{}
    AABBCCDDE->VALmin{}->VALmin{}
    AABBCCDDEF->VALmin{}->VALmin{}
 */
public class suggest {

}
