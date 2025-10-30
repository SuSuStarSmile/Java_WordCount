package com.wordcount;

/**
 * 字符统计工具类
 * 
 * 功能说明：
 * 该类用于统计字符串中的字符数量，支持中英文混合文本的统计
 * 
 * 统计规则：
 * 1. 汉字：每个汉字算1个计数
 * 2. 英文单词：连续的英文字母算1个单词（算1个计数）
 * 3. 数字：每个数字字符算1个计数
 * 4. 标点符号：每个标点符号算1个计数
 * 5. 连续空格：多个连续空格只算1个计数
 * 
 * @author AI Assistant
 * @version 1.0
 */
public class CharacterCounter {
    
    /**
     * 统计字符串的总字符数
     * 
     * @param text 需要统计的文本字符串
     * @return 返回统计结果对象 CountResult
     * 
     * 实现原理：
     * 1. 遍历字符串中的每个字符
     * 2. 根据字符类型进行分类统计
     * 3. 对于英文字母，需要识别连续的字母为一个单词
     * 4. 对于空格，需要识别连续空格并只计数一次
     */
    public static CountResult count(String text) {
        // 如果输入为空，返回全0的结果
        if (text == null || text.isEmpty()) {
            return new CountResult(0, 0, 0, 0, 0, 0);
        }
        
        // 初始化各类字符的计数器
        int chineseCount = 0;      // 汉字计数
        int englishWordCount = 0;  // 英文单词计数
        int numberCount = 0;       // 数字计数
        int punctuationCount = 0;  // 标点符号计数
        int spaceCount = 0;        // 空格计数
        
        // 标记是否正在处理英文单词（用于识别连续的英文字母）
        boolean inEnglishWord = false;
        // 标记是否正在处理空格（用于识别连续的空格）
        boolean inSpace = false;
        
        // 将字符串转换为字符数组，逐个字符进行处理
        char[] chars = text.toCharArray();
        
        // 遍历每个字符
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            
            // 判断是否为汉字（中文字符）
            // 汉字的Unicode编码范围主要在 \u4e00-\u9fa5
            if (isChinese(c)) {
                chineseCount++;  // 每个汉字算1个计数
                inEnglishWord = false;  // 遇到汉字，英文单词结束
                inSpace = false;        // 遇到汉字，空格序列结束
            }
            // 判断是否为英文字母
            else if (isEnglishLetter(c)) {
                // 如果之前不在英文单词中，说明这是一个新单词的开始
                if (!inEnglishWord) {
                    englishWordCount++;  // 新单词计数+1
                    inEnglishWord = true;  // 标记进入英文单词状态
                }
                // 如果已经在英文单词中，不增加计数（因为连续字母算一个单词）
                inSpace = false;  // 遇到字母，空格序列结束
            }
            // 判断是否为数字
            else if (Character.isDigit(c)) {
                numberCount++;  // 每个数字算1个计数
                inEnglishWord = false;  // 遇到数字，英文单词结束
                inSpace = false;        // 遇到数字，空格序列结束
            }
            // 判断是否为空格
            else if (Character.isWhitespace(c)) {
                // 如果之前不在空格序列中，说明这是新的空格序列开始
                if (!inSpace) {
                    spaceCount++;  // 空格序列计数+1
                    inSpace = true;  // 标记进入空格状态
                }
                // 如果已经在空格序列中，不增加计数（因为连续空格算一个）
                inEnglishWord = false;  // 遇到空格，英文单词结束
            }
            // 其他情况视为标点符号或特殊字符
            else {
                punctuationCount++;  // 每个标点符号算1个计数
                inEnglishWord = false;  // 遇到标点，英文单词结束
                inSpace = false;        // 遇到标点，空格序列结束
            }
        }
        
        // 计算总数：所有类型的字符数相加
        int total = chineseCount + englishWordCount + numberCount + punctuationCount + spaceCount;
        
        // 返回统计结果对象
        return new CountResult(total, chineseCount, englishWordCount, numberCount, punctuationCount, spaceCount);
    }
    
    /**
     * 判断字符是否为中文汉字
     * 
     * @param c 待判断的字符
     * @return 如果是汉字返回true，否则返回false
     * 
     * 原理：
     * 中文汉字的Unicode编码范围主要在以下区间：
     * - \u4e00-\u9fa5：CJK统一汉字（最常用）
     * - \u3400-\u4dbf：CJK扩展A
     * - \u20000-\u2a6df：CJK扩展B
     * 这里使用最常用的范围进行判断
     */
    private static boolean isChinese(char c) {
        // 使用Character.UnicodeBlock来判断字符所属的Unicode块
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        
        // 判断是否属于中文相关的Unicode块
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  // CJK统一汉字
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  // CJK兼容汉字
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  // CJK扩展A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  // 通用标点（某些中文标点）
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  // CJK符号和标点
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;  // 全角ASCII、全角标点
    }
    
    /**
     * 判断字符是否为英文字母
     * 
     * @param c 待判断的字符
     * @return 如果是英文字母返回true，否则返回false
     * 
     * 原理：
     * 使用Character类的isLetter方法判断是否为字母
     * 并且排除中文字符（因为中文字符也会被识别为letter）
     */
    private static boolean isEnglishLetter(char c) {
        // 判断是否为字母，并且不是中文字符
        return Character.isLetter(c) && !isChinese(c);
    }
    
    /**
     * 统计结果类
     * 
     * 该类用于封装统计结果，包含各类字符的计数和总计数
     */
    public static class CountResult {
        private final int total;            // 总计数
        private final int chineseCount;     // 汉字数量
        private final int englishWordCount; // 英文单词数量
        private final int numberCount;      // 数字数量
        private final int punctuationCount; // 标点符号数量
        private final int spaceCount;       // 空格序列数量
        
        /**
         * 构造函数
         * 
         * @param total 总计数
         * @param chineseCount 汉字数量
         * @param englishWordCount 英文单词数量
         * @param numberCount 数字数量
         * @param punctuationCount 标点符号数量
         * @param spaceCount 空格序列数量
         */
        public CountResult(int total, int chineseCount, int englishWordCount, 
                          int numberCount, int punctuationCount, int spaceCount) {
            this.total = total;
            this.chineseCount = chineseCount;
            this.englishWordCount = englishWordCount;
            this.numberCount = numberCount;
            this.punctuationCount = punctuationCount;
            this.spaceCount = spaceCount;
        }
        
        // Getter方法，用于获取各项统计数据
        public int getTotal() { return total; }
        public int getChineseCount() { return chineseCount; }
        public int getEnglishWordCount() { return englishWordCount; }
        public int getNumberCount() { return numberCount; }
        public int getPunctuationCount() { return punctuationCount; }
        public int getSpaceCount() { return spaceCount; }
        
        /**
         * 重写toString方法，方便打印统计结果
         * 
         * @return 格式化的统计结果字符串
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("========== 字符统计结果 ==========\n");
            sb.append("汉字数量：").append(chineseCount).append("\n");
            sb.append("英文单词数量：").append(englishWordCount).append("\n");
            sb.append("数字数量：").append(numberCount).append("\n");
            sb.append("标点符号数量：").append(punctuationCount).append("\n");
            sb.append("空格（连续空格算1个）：").append(spaceCount).append("\n");
            sb.append("--------------------------------\n");
            sb.append("总计数：").append(total).append("\n");
            sb.append("================================");
            return sb.toString();
        }
    }
}
