# Java 字符统计工具（Java_WordCount）

## 项目简介
这是一个强大的字符统计工具，支持中英文混合文本的智能统计。能够准确识别并统计汉字、英文单词、数字、标点符号和空格等各类字符。

## 功能特点

### 统计规则
本工具采用智能统计算法，遵循以下规则：

1. **汉字统计**：每个汉字算1个计数
2. **英文单词统计**：连续的英文字母算作1个单词（而不是每个字母单独计数）
3. **数字统计**：每个数字字符算1个计数
4. **标点符号统计**：每个标点符号算1个计数
5. **空格统计**：连续的多个空格只算1个计数
6. **总计数**：以上所有类型的计数之和

### 支持的字符类型
- ✅ 中文汉字（CJK统一汉字）
- ✅ 英文字母（大小写）
- ✅ 阿拉伯数字（0-9）
- ✅ 中英文标点符号
- ✅ 空格和空白字符

## 项目结构

```
/workspace
├── src/main/java/com/wordcount/
│   ├── CharacterCounter.java    # 核心统计类
│   └── Main.java                 # 主程序（包含测试用例）
├── bin/                          # 编译后的class文件目录
├── README.md                     # 项目说明文档
└── LICENSE                       # 许可证文件
```

## 安装和使用

### 环境要求
- Java JDK 8 或更高版本
- 支持UTF-8编码

### 编译项目

在项目根目录下执行以下命令：

```bash
# 编译Java源代码
javac -d bin src/main/java/com/wordcount/*.java
```

### 运行程序

```bash
# 运行主程序（包含多个测试用例）
java -cp bin com.wordcount.Main
```

## 使用示例

### 示例1：在代码中使用

```java
import com.wordcount.CharacterCounter;

public class Example {
    public static void main(String[] args) {
        // 准备要统计的文本
        String text = "Hello世界123，这是一个test！";
        
        // 调用统计方法
        CharacterCounter.CountResult result = CharacterCounter.count(text);
        
        // 获取统计结果
        System.out.println("总计数：" + result.getTotal());
        System.out.println("汉字数量：" + result.getChineseCount());
        System.out.println("英文单词数量：" + result.getEnglishWordCount());
        System.out.println("数字数量：" + result.getNumberCount());
        System.out.println("标点符号数量：" + result.getPunctuationCount());
        System.out.println("空格数量：" + result.getSpaceCount());
        
        // 或者直接打印完整结果
        System.out.println(result);
    }
}
```

### 示例2：测试用例结果展示

**测试文本**：`"Hello世界123，这是一个test！"`

**统计结果**：
```
========== 字符统计结果 ==========
汉字数量：8
英文单词数量：2
数字数量：3
标点符号数量：0
空格（连续空格算1个）：0
--------------------------------
总计数：13
================================
```

**测试文本**：`"The quick brown fox jumps over the lazy dog."`

**统计结果**：
```
========== 字符统计结果 ==========
汉字数量：0
英文单词数量：9
数字数量：0
标点符号数量：1
空格（连续空格算1个）：8
--------------------------------
总计数：18
================================
```

## API 说明

### CharacterCounter 类

#### 主要方法

##### `count(String text)`
统计字符串中的字符数量

**参数**：
- `text` (String) - 需要统计的文本字符串

**返回值**：
- `CountResult` - 统计结果对象

**示例**：
```java
CountResult result = CharacterCounter.count("Hello 世界");
```

### CountResult 类

#### 方法列表

| 方法名 | 返回类型 | 说明 |
|--------|---------|------|
| `getTotal()` | int | 获取总计数 |
| `getChineseCount()` | int | 获取汉字数量 |
| `getEnglishWordCount()` | int | 获取英文单词数量 |
| `getNumberCount()` | int | 获取数字数量 |
| `getPunctuationCount()` | int | 获取标点符号数量 |
| `getSpaceCount()` | int | 获取空格序列数量 |
| `toString()` | String | 获取格式化的统计结果字符串 |

## 技术实现

### 核心算法
程序采用状态机模式进行字符识别和统计：
1. 逐字符遍历输入文本
2. 根据字符的Unicode编码判断字符类型
3. 使用状态标记识别连续的英文单词和空格序列
4. 累计各类字符的计数
5. 返回封装好的统计结果对象

### 字符识别方法
- **汉字识别**：使用 `Character.UnicodeBlock` 判断字符所属的Unicode块
- **英文识别**：使用 `Character.isLetter()` 并排除中文字符
- **数字识别**：使用 `Character.isDigit()`
- **空格识别**：使用 `Character.isWhitespace()`

## 注意事项

1. 程序假设输入文本使用UTF-8编码
2. 连续的英文字母会被识别为一个单词
3. 连续的空格（包括tab、换行等空白字符）只会被计数一次
4. 中文标点符号会被识别为汉字的一部分（因为它们属于CJK Unicode块）
5. 所有不属于以上类别的字符都会被归类为标点符号

## 测试

运行主程序会自动执行7个测试用例：
1. 中英文混合文本
2. 纯中文文本
3. 纯英文文本
4. 包含连续空格的文本
5. 包含数字和标点符号的文本
6. 空字符串
7. 复杂混合文本

每个测试用例都会显示原文和详细的统计结果。

## 版本信息
- 版本：1.0
- 更新日期：2025-10-30

## 许可证
本项目使用 LICENSE 文件中指定的许可证。

## 作者
AI Assistant

---

**提示**：如果您在使用过程中遇到任何问题，请检查：
1. Java版本是否正确（JDK 8+）
2. 文本编码是否为UTF-8
3. 是否正确编译了所有源文件
