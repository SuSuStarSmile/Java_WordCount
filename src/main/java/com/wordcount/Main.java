package com.wordcount;

/**
 * 主程序类
 * 
 * 功能说明：
 * 该类是程序的入口，用于测试和演示 CharacterCounter 的功能
 * 
 * @author AI Assistant
 * @version 1.0
 */
public class Main {
    
    /**
     * 程序入口方法
     * 
     * @param args 命令行参数（本程序暂不使用）
     * 
     * 该方法通过多个测试用例来展示字符统计功能
     */
    public static void main(String[] args) {
        System.out.println("=== 字符统计程序测试 ===\n");
        
        // 测试用例1：中英文混合文本
        System.out.println("【测试用例1】中英文混合文本：");
        String test1 = "Hello世界123，这是一个test！";
        System.out.println("原文：" + test1);
        CharacterCounter.CountResult result1 = CharacterCounter.count(test1);
        System.out.println(result1);
        System.out.println("\n");
        
        // 测试用例2：纯中文文本
        System.out.println("【测试用例2】纯中文文本：");
        String test2 = "今天天气真好，阳光明媚！";
        System.out.println("原文：" + test2);
        CharacterCounter.CountResult result2 = CharacterCounter.count(test2);
        System.out.println(result2);
        System.out.println("\n");
        
        // 测试用例3：纯英文文本
        System.out.println("【测试用例3】纯英文文本：");
        String test3 = "The quick brown fox jumps over the lazy dog.";
        System.out.println("原文：" + test3);
        CharacterCounter.CountResult result3 = CharacterCounter.count(test3);
        System.out.println(result3);
        System.out.println("\n");
        
        // 测试用例4：包含多个连续空格
        System.out.println("【测试用例4】包含连续空格：");
        String test4 = "Hello   World    测试     多个空格";
        System.out.println("原文：" + test4);
        CharacterCounter.CountResult result4 = CharacterCounter.count(test4);
        System.out.println(result4);
        System.out.println("\n");
        
        // 测试用例5：包含数字和标点符号
        System.out.println("【测试用例5】包含数字和各种标点：");
        String test5 = "价格：99元！折扣：50% off！电话：123-456-7890。";
        System.out.println("原文：" + test5);
        CharacterCounter.CountResult result5 = CharacterCounter.count(test5);
        System.out.println(result5);
        System.out.println("\n");
        
        // 测试用例6：空字符串
        System.out.println("【测试用例6】空字符串：");
        String test6 = "";
        System.out.println("原文：" + test6);
        CharacterCounter.CountResult result6 = CharacterCounter.count(test6);
        System.out.println(result6);
        System.out.println("\n");
        
        // 测试用例7：复杂混合文本
        System.out.println("【测试用例7】复杂混合文本：");
        String test7 = "2024年，OpenAI发布了GPT-4模型，性能提升了100%以上！amazing!!!";
        System.out.println("原文：" + test7);
        CharacterCounter.CountResult result7 = CharacterCounter.count(test7);
        System.out.println(result7);
        System.out.println("\n");
        
        System.out.println("=== 测试完成 ===");
    }
}
