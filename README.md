# NiuTrans-Cli

![](https://img.shields.io/badge/Java-14-orange?style=for-the-badge&logo=java)  ![](https://img.shields.io/github/v/release/amtoaer/NiuTrans-Cli?style=for-the-badge&logo=github)  ![](https://img.shields.io/github/license/amtoaer/NiuTrans-Cli?style=for-the-badge&logo=litecoin) 

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

`api-key`需替换为你自己的key，可通过在[小牛翻译开放平台](https://niutrans.com/)注册账号获得。

`type`可选`text`、`XML`、`compare`，分别对应文本翻译、XML格式翻译、双语对照翻译。

## 用法

```bash
⟩ java -jar NiuTrans-Cli-1.3.jar help
Usage: <main class> [options] [command] [command options]
  Options:
    help, -h, --help

  Commands:
    translate      Translate content
      Usage: translate [options] srcText
        Options:
          from
            Language of source text
            Default: auto
          to
            Language of target text
            Default: en
          via
            Translation method(accept text/XML/compare)

    customize      customize translation
      Usage: customize [options] Customize method(accept word/sentence)
        Options:
          from
            Source text
            Default: <empty string>
          fromLanguage, fl
            Language of source text
            Default: auto
          to
            Target text
            Default: <empty string>
          toLanguage, tl
            Language of target text
            Default: en

```

子命令有两个：

+ `translate`

  用于翻译文本内容，示例如下：

  ```bash
  # 通过文本翻译将'测试'从zh（中文）翻译到en（英文）
  java -jar NiuTrans-Cli-Version.jar translate '测试' from zh to en via text
  ```

  其中`via`参数默认为配置文件内的`type`项，`from`参数默认为`auto`。[点击此处](https://niutrans.com/documents/develop/develop_text/free#languageList)查看支持语言列表。

+ `customize`

  用于使用术语词典和翻译记忆功能，这两个功能的官方介绍如下：

  + 术语词典

    如果系统对某个术语翻译结果不准确，可以通过“术语词典”重新定义术语译文，系统后续的翻译结果均会采用修改后的定义结果，保证同样的错误不会犯第二次！

  + 翻译记忆

    如果说术语词典是单词/词组级，那么翻译记忆就是从句子级的角度，将人工翻译的正确译文实时添加到小牛翻译系统中，帮助修改翻译结果，避免机器翻译重复犯错。此外，现有的高精度语料也可以添加至翻译记忆中，小牛翻译系统会优先选用记忆中的译文。

  这两个功能依次对应`word`和`sentence`，举例：

  ```bash
  # 使用术语词典，指定在之后的zh（中文）到en（英文）的翻译中，将'测试'一词翻译为'abcd'
  java -jar NiuTrans-Cli-Version.jar customize word from '测试' to 'abcd' fl zh tl en
  ```

  其中`from`参数为原始内容，`to`参数为想要翻译为的内容，`fl`和`tl`分别是`from language`和`to language`的缩写，分别对应`from`和`to`的语言。

  翻译记忆功能与此类似，只需要将例子中的`word`替换为`sentence`即可。

## 简化使用

`linux`可参考[这篇文章](https://allwens.work/makeJarExecutable/)设置，生成无需`java -jar`的可执行文件（假设文件名为`niu`）。将其移动到环境变量中，即可通过如下方式使用：

![image-20200810200425034](https://allwens-work.oss-cn-beijing.aliyuncs.com/bed/image-20200810200425034.png)


## 依赖

+ (Mulan Permissive Software License，Version 1) hutool-core

+ (Mulan Permissive Software License，Version 1) hutool-http

+ (Mulan Permissive Software License，Version 1) hutool-json

+ (Apache License, Version 2.0) jcommander

+ (MIT License) com.diogonunes:JColor

+ (Common Public License Version 1.0) JUnit

