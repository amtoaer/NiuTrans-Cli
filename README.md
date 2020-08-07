# NiuTrans-Cli

![](https://img.shields.io/badge/Java-14-orange?style=for-the-badge&logo=java)![](https://img.shields.io/github/v/release/amtoaer/NiuTrans-Cli?style=for-the-badge&logo=github)

## 介绍

这是命令行版本的小牛翻译，使用小牛翻译开放平台的[免费接口](https://niutrans.com/documents/develop/develop_text/free#accessMode)。

## 下载

下载[release界面](https://github.com/amtoaer/NiuTrans-Cli/releases)的`.jar`包，使用

```bash
java -jar NiuTrans-Cli-Version.jar ...arguments
```

运行。

## 自定义

在第一次翻译过后，用户路径下会生成`.NiuTrans`配置文件，内容如下：

```properties
# config file

api-key=531cb65d16b3ed2df2b4c6a6e44171ec

# available type: text,XML,compare
type=compare
```

`api-key`可选替换为你自己的key，可通过在[小牛翻译开放平台](https://niutrans.com/)注册账号获得。

`type`可选`text`、`XML`、`compare`，分别对应文本翻译、XML格式翻译、双语对照翻译。

> 1. api-key不替换也没关系，毕竟用的是免费接口。
> 2. 由于尚不清楚的原因，`XML`翻译的返回结果与文本翻译完全相同。

## 参数

共提供四个参数：

+ `src`：需要翻译的内容，无需前缀
+ `from`： 源语言，前缀为`from`，默认为`auto`
+ `to`： 目标语言，前缀为`to`
+ `type`： 翻译类型，前缀为`via`，默认为`null`，可覆盖配置文件的`type`

一般的使用方法：

```bash
java -jar NiuTrans-Cli-Version.jar 需要翻译的内容 from zh to en via text
```

对于`linux`，参考[这篇文章](https://allwens.work/makeJarExecutable/)设置，可生成无需`java -jar`的可执行文件（假设为`translate`）。将该可执行文件放入`PATH`中，即可像这样使用：

<p align="center">
    <img src="https://allwens-work.oss-cn-beijing.aliyuncs.com/bed/image-20200807233705800.png" width="700">
</p>

## 依赖

+ (Mulan Permissive Software License，Version 1) hutool-all

+ (Apache License, Version 2.0) jcommander

+ (MIT License) com.diogonunes:JColor

+ (Common Public License Version 1.0) JUnit

