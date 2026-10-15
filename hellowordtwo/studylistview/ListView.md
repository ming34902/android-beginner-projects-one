## ListView的焦点
方法1：为抢占了控件的组件设置:android:focusable="false"
如题，只需为抢占了ListView Item焦点的控件设置android:focusable="false"即可解决这个问题 或者在代码中获得控件后调用：setFocusable(false) !!另外，EditText却不行，如果我们设置了android:focusable="false"，这B可以获取焦点但是一下子 又失去了焦点，而且也不会弹出小键盘，暂不知道如何解决，听别人说是ListView的一个bug，如果 有知道解决方法的欢迎告知下，谢谢~


方法2：item根节点设置android:descendantFocusability="blocksDescendants"
如题，在Item布局的根节点添加上述属性，android:descendantFocusability="blocksDescendants" 即可，另外该属性有三个可供选择的值：

beforeDescendants：viewgroup会优先其子类控件而获取到焦点
afterDescendants：viewgroup只有当其子类控件不需要获取焦点时才获取焦点
blocksDescendants：viewgroup会覆盖子类控件而直接获得焦点