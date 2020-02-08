# xRecyclerView，支持下拉刷新和上拉加载更多，以及自定义LoadMoreView

### 1. 概括

`xRecyclerView` 是基于 `SwipeRefreshLayout` 和 `RecyclerView` 的封装和扩展，继承了 `SwipeRefreshLayout` 的下拉刷新功能，独自实现了上拉加载更多功能，集二者于一体。

### 2. 效果图

- 列表处于顶端时，向下滑动触发下拉刷新
- 向上滑动列表，至 LoadMoreItem 完整展示后松手，触发加载更多操作；LoadMoreItem 部分展示时松手，LoadMoreItem 自动滑动至隐藏

<img src="images/sample.gif" style="zoom:50%;" />

### 3. 使用方式一

通过JitPack，作为依赖库导入

a. 在项目根目录下的 build.gradle 中添加：

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

b. 在项目的 build.gradle 中添加：

```groovy
dependencies {
	   implementation 'com.github.oynix:wraprecyclerview:1.0.1'
}
```

c. Sync 一下，即可。

e. xml中

```xml
 <com.oynix.xrecyclerview.xRecyclerView
        android:id="@+id/gank_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

f. 代码中

```java
xRecyclerView rv = findViewById(R.id.gank_recycler_view);
rv.setListener(new xRecyclerView.xAapterListner() {
  @Ovrride
  public void startRefresh() {
    // xRecyclerView下拉刷新被触发时的回调
    // ...
    // 刷新完成时，停止动画
    // ...
    
    // after finish refresh, stop refrsh animation
    rv.stopRefreshing();
  }
  
  @Override
  public void startLoadMore() {
    // xRecyclerView上拉加载被触发时的回调
    // ....
    // do something to load more, such as request more data
    // ...
    
    // 加载完成时，停止动画
    rv.stopLoadingMore();
  }
});


// 首次进入页面，请求数据时，如果需要显示刷新动画，手动调用
rv.startRefreshing();
// 同时，请求完成时，手动停止
rv.stopRefreshing();
```

### 4. 使用方式二

将库里的 `xRecyclerView.java` 文件复制粘贴到自己的项目里，亦可。

**推荐方式一，因非最终版本，使用依赖导入便于及时了解bug修复及新加功能。**

### 5. 实现大致思路

- 用`SwipRefreshLayout`包装一个`RecyclerView`，以便使用下拉刷新功能
- 添加一个`xAdapter`类，并继承自`RecyclerView.Adapter`
- 对于`getItemCount`、`createViewHolder` 和 `onBindViewHolder`，各自添加一一对应的影子方法：`getxItemCount`、`createxViewHolder` 和 `onBindxViewHolder`
- 在`xAdapter`自己处理`LoadMoreView`，其他Item通过影子方法，让使用者实现
- `xAdapter`有默认的`LoadMoreView`，提供一个方法供使用者自定义`LoadMoreItemView`的样式
- `LoadMoreItemView`目前有两种状态：*加载状态*和*非加载状态*。自定义样式时需要针对这两种状态，对`LoadMoreItemView`作出相应的处理
- 默认`LoadMoreItemView`在非加载状态显示文字`上拉加载更多`，加载状态显示一个环形`ProgressBar`
- 监听`RecyclerView`的滑动，当`RecyclerView`状态改变后处于`IDLE`状态时，判断`LoadMoreView`的显示状态以及是否需要滑动隐藏`LoadMoreView`

源代码：[https://github.com/oynix/wraprecyclerview](https://github.com/oynix/wraprecyclerview)

代码中注释较为详细，在此不再赘述。



（完）