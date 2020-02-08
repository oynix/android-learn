## 发布代码到JitPack

将常用的一些代码发布到公共的代码库中，在项目中饮用时，通过在build.gradle中添加一行：

```groovy
implementation 'groupId:artificialId:version'
```

就可以把需要的代码作为依赖库导入当前项目，十分方便。

这里说的JitPack，与Github配合起来，流程精简，发布代码简单快速。JitPack最近更新，也支持Gitee了。

**将过程大致分成了 3 步**

### 1/3 创建代码，并提交到Github

- 打开AndroidStudio，直接创建一个application，养成个好习惯，所有的Library都要写sample，不然久了可能连自己都不知道这个库是创建是为了做什么的
- 在项目中添加moudle，类型选择Android Library。至此，当前项目包含两个moudle，一个是类型为appliaction的sample，另个是类型为library的mylibrary
- 将要发布的代码写到mylibrary中，在sample中写使用示例，保证在sample中通过导入moudle的形式能正确调用mylibrary中的代码
- 在Github中建个仓库，将代码传上去

### 2/3 在Github上打release包

- 按图顺序点击
<img src="/Users/xiaoyu/learn/android-learn/publish/JitPack/images/jitpack_1.png" style="zoom:50%;" />
<img src="/Users/xiaoyu/learn/android-learn/publish/JitPack/images/jitpack_2.png" style="zoom:50%;" />


- 输入相关字段。版本号不能重复，一般从1.0.0开始，我这里发布过1.0.0，所以写了3.0.0。最后点击Publish release
<img src="/Users/xiaoyu/learn/android-learn/publish/JitPack/images/jitpack_3.png" style="zoom:50%;" />

### 3/3 在JitPack上build代码

- 地址：https://jitpack.io/，在搜索栏输入用户名和仓库名，点搜索便能显示打过的release版本，如果没打过会显示最近的提交。如果登陆Github账号，会在左边展示所有仓库，点击刚刚的仓库亦可。然后点击对应版本后面的Get it按钮，等待build完成即可
<img src="/Users/xiaoyu/learn/android-learn/publish/JitPack/images/jitpack_4.png" style="zoom:50%;" />

- 编译完整后，按照下面的说明，便可以在项目中导入自己刚刚发布的代码了
<img src="/Users/xiaoyu/learn/android-learn/publish/JitPack/images/jitpack_5.png" style="zoom:50%;" />



（完）