# 免责声明

本软件和技术仅供学习和研究使用，禁止将其用于商业用途。如果您使用本软件和技术用于商业化而产生任何损失、风险或法律纠纷，作者将不承担任何责任。

作者不保证本软件和技术的完整性、准确性、可靠性、适用性或及时性。使用本软件和技术产生的任何后果，作者将不承担任何责任。

本软件和技术中的任何意见、建议或信息，均不构成作者或作者所在组织的任何形式的保证或担保。

任何人不得对本软件和技术进行反向工程、破解或其他非法行为。任何未经作者或作者所在组织授权的行为，均可能构成侵权或违法行为，一经发现作者将保留追究其法律责任的权利。

使用本软件和技术将被视为您已经完全接受本免责声明的所有条款和条件。如果您不同意本免责声明的任何部分，请勿使用本软件和技术。

# 使用方法

下载 https://github.com/libin9iOak/ja-netfilter-all 或 https://gitee.com/ja-netfilter/ja-netfilter/releases/tag/2022.2.0
并解压到目录

修改 IDEA 的 `idea.vmoptions` 文件

```text
--add-opens=java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED
--add-opens=java.base/jdk.internal.org.objectweb.asm.tree=ALL-UNNAMED

-javaagent:C:\ja-netfilter-all\ja-netfilter.jar=jetbrains

上面的地址替换成刚才解压地址
```

下载 https://github.com/starxg/MyBatisCodeHelperProCrack/releases 对应的版本放到 `plugins-jetbrains` 目录

启动 IDEA 即可

<img width="495" src="https://user-images.githubusercontent.com/34997494/255570584-13bb94f9-d322-4329-b584-584269f1709c.png" alt="">

# 注意⚠️

使用的过程中，可将 `plugins-jetbrains`、`config-jetbrains` 目录下的其他插件删除（不是必须的，也可以不删除）。

如果您觉得本程序好用，建议您立即删除本程序并购买正版：[https://brucege.com/pay/view](https://brucege.com/pay/view)