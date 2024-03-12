//定义基础牌型为ABC,AAB,ABCD,AABC,AABB,ABCDE,AABBC,ABCDEF,其策略为基础策略,已穷举//
//suggest类希望把其他牌型化归为基础牌型,从而给出策略//
//基础牌型和其他牌型中都没有顺子和刻子//
//可以证明对同种花色的九种牌,持有的牌的种类大于等于七种牌时,必然会有三张是顺子,从而化归为基础牌型//
//根据胡牌规则,人为规定对于其他牌型,当有且仅有一个pair时,选择保留pair,把余下的牌归为基础牌型//
//有两个及以上pair时，根据牌型中牌的数量
/*下面给出其他牌型的化归策略:
    AABCD->BCD->ABC
    AABCDE->BCDE->ABCD
    AABBCD->VALmin{AAB,BCD;AAC,BBD;AAD,BBC;ABB,ACD;ABD,ABC}->VALmin{AAB,ABC;AAB,AAB;AAB,AAB;AAB,ABC;ABC,ABC}
    AABBCC->
    AABCDEF->BCDEF->ABCDE
    AABBCDE->
    AABBCCD->
    AABBCDEF->
    AABBCCDE->
    AABBCCDD->
    AABBCCDEF->
    AABBCCDDE->
    AABBCCDDEF->
 */
public class suggest {

}
