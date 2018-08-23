package com.tools.strman;

import strman.Strman;

/**
 * Created by zhoushu on 2018/4/11 0011.
 */
public class StrmanTest {


    public static void main(String[] args) {
//        // surround 给定的"前缀”和"后缀”来包裹一个字符串
//        String s46 = Strman.surround("div", "<", ">");
//        System.out.println("surround:" + s46); // result => "
//
//        // safeTruncate 安全的截断字符串，不切一个字的一半,它总是返回最后一个完整的单词
//        String s39 = Strman.safeTruncate("A Javascript string manipulation library.", 19, "…");
//        String s39_1 = Strman.truncate("《宣言》发表后，马克思恩格斯为解决这句话的弊病，进行了长达30年的探索，最终正确地解决了人类社会形态演进序列的起始社会问题，从而补充和完善了唯物史观。.", 19, "…");
//        System.out.println("safeTruncate:" + s39_1); // result => "A Javascript…”
//
//        // inequal 测试两个字符串是否相等
//        boolean s28 = Strman.unequal("a", "b");
//        System.out.println("inequal:" + s28); // result => "true"
        Integer num = -150;
        String numDesc = num != null && num < 0 ? num + "" : "+"+num;
        System.out.println("StrmanTest.main:"+numDesc);
    }
}
