
使用 glide依赖库，在 AndroidManifest.xml 中添加 网络权限  
<application>
..
android::usesCleartextTraffic="true"
..
</application>

// 加载远程图片
Glide.with(this).load(IMAGE_URL).into(imageView);

// 加载本地图片
File file1 = new File("/sdcard/xxx.jpg");
Glide.with(this).load(file1).into(imageView);

// 加载gif
Glide.with(this).load("http://xx.com/xxx.gif").placeholder(R.drawable.placeholder).into(imageView);

// 强制静态显示第一帧
Glide.with(this).asBitmap().load("http://xx.com/xxx.gif").into(imageView);

1.生命周期绑定： Glide.with(context) 传入ActivityFragment 时， Glide会自动跟随其生命周期，避免内存泄露。
2.缓存机制： Glide默认开启第二级缓存，无需手动配置，可通过 diskCacheStrategy()自定义： java运行

.diskCacheStrategy(DiskCacheStrategy.ALL) // 缓存所有（默认）
// DiskCacheStrategy.NONE; 不缓存
// DiskCacheStrategy.RESULT; 只缓存最终加载的图片

3.清除缓存（可选）
// 清除内存缓存，主线程
Glide.get(context).clearMemory();
// 清除磁盘缓存
new Thread(() -> Glide.get(context).clearDiskCache()).start();