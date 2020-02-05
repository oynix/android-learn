# apk 反编译 -- jadx

### 1. Github 地址

https://github.com/skylot/jadx

里面有较为详细的介绍与说明，这里挑出其中部分

### 2. 主要功能

- 支持 `apk`、`dex`、`aar` 和 `zip` 格式的文件
- 解码 `AndroidManifest.xml` 文件和 `resources.arsc` 中的资源文件
- 反混淆

### 3. `jadx-gui` 功能

- 高亮显示
- 可在声明、引用之间跳转
- 全文搜索

### 4. macOS 安装

```shell
$ brew install jadx
```

成功后，会安装两个可执行命令：`jadx`、`jadx-gui`，一般使用后者即可。

### 5. 使用

```shell
$ jadx-gui
```

在 GUI 中打开要反编译的文件即可。



（完）