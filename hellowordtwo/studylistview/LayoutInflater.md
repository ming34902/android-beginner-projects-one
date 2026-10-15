# LayoutInflater

## 四个概念的关系

1. **XML 布局文件**
   例如 `item_user.xml`，定义一行内容由哪些控件组成、如何排列。
2. **LayoutInflater**
   负责解析布局资源，并创建对应的 View 对象树。
3. **inflate()**
   真正执行布局加载，返回一个 View，通常是根布局及其所有子控件。
4. **getView()**
   ListView 需要显示某一行时调用它，由适配器返回这一行的 View。

LayoutInflater 不只是构造整个页面，也可以只加载一个局部布局，例如列表中的单行 item。

## 获取 LayoutInflater

```java
LayoutInflater inflater = LayoutInflater.from(context);
```

这里的 `from()` 不是加载 XML 的方法，而是获取一个 LayoutInflater 实例的方法。
它接收 `Context`（例如 Activity），就能获得适用于当前应用环境的 LayoutInflater。

接下来再调用 `inflate()`：

```java
View itemView = inflater.inflate(
        R.layout.item_user,
        parent,
        false
);
```

| 参数 | 含义 |
| --- | --- |
| `R.layout.item_user` | 要加载的 XML 布局资源 |
| `parent` | 这个 View 将来所属的父容器，ListView 中通常是传入的父容器 |
| `false` | 现在先不把新 View 添加到父容器中，由 ListView 管理添加 |

### 两步走

```java
// 第一步：拿到布局加载工具
LayoutInflater inflater = LayoutInflater.from(context);

// 第二步：用工具加载 XML，生成 View
View itemView = inflater.inflate(
        R.layout.item_user,
        parent,
        false
);
```

## getView()

`item_user.xml`：

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/tvAge"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

适配器里的实现：

```java
@Override
public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder holder;

    if (convertView == null) {
        // 1. 获取布局加载器
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // 2. 将 XML 转换成 View 对象
        convertView = inflater.inflate(
                R.layout.item_user,
                parent,
                false
        );

        // 3. 第一次创建时，查找子控件
        holder = new ViewHolder();
        holder.tvName = convertView.findViewById(R.id.tvName);
        holder.tvAge = convertView.findViewById(R.id.tvAge);

        // 4. 缓存子控件引用
        convertView.setTag(holder);

    } else {
        // 5. 复用旧 View，不再 inflate
        holder = (ViewHolder) convertView.getTag();
    }

    // 6. 每次都要更新当前行的数据
    User user = users.get(position);
    holder.tvName.setText(user.getName());
    holder.tvAge.setText(String.valueOf(user.getAge()));

    return convertView;
}
```

## convertView 的复用流程

假设列表有 1000 个用户，但手机屏幕一次只能显示 10 行左右。

**第一次显示某一行**（`convertView == null`）：

```
LayoutInflater.inflate()
  ↓ 创建行布局 View
  ↓ findViewById() 查找子控件
  ↓ setTag(holder) 缓存引用
```

**向下滚动，复用旧行**（`convertView != null`）：

```
取出已有的 ViewHolder
  ↓ 更新姓名、年龄等数据
  ↓ 返回复用的 View
```

核心区别是：

- **LayoutInflater**：创建布局 View。
- **convertView**：接收可能已经创建好的旧 View。
- **ViewHolder**：缓存旧 View 中的子控件引用，减少重复查找。
- **getView()**：负责决定创建还是复用，并绑定当前行的数据。

因此，`LayoutInflater.from()` 通常只在需要创建新行时才有必要调用；`inflate()` 更是只在 `convertView == null` 时执行。

还有一点：`getView()` **不是只调用一次**。ListView 在需要显示、滚动或更新列表时，会根据需要反复调用它，因此每次都必须根据 `position` 绑定正确的数据。

## Activity.setContentView() 和 inflate() 的区别

```java
// 加载 Activity 的整体页面：setContentView 用于页面主体
setContentView(R.layout.activity_main);

// 加载一个局部布局：inflate 用于 ListView item、弹窗内容、自定义组件
View view = LayoutInflater.from(this)
        .inflate(R.layout.item_user, parent, false);
```

## 小结

```
LayoutInflater.from(context) 获取加载器
  → inflate(xml, parent, false) 创建 View
  → getView() 返回列表行
  → ListView 显示并在滚动时尽可能复用它
```
