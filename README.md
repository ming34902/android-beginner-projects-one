# android-beginner-projects-one
Kotlin-study-one

## 根目录及文件

settings.gradle 项目结构配置文件。告诉gradle当前项目有那些模块（Module），及第三方依赖库和下载地址

build.gradle 全局（顶级）构建配置文件。定义整个项目共享的构建配置。配置子模块需要的Gradle插件（Android插件，kotlin插件），通用task构建任务

gradle.properties Gradle构建属性环境配置文件。存放影响Gradle构建性能与环境的全局键值对。例如JVM堆内存大小（org.gradle.jvmargs=-Xmx4g）,开启Androidx插件（android.useAndroidX=true），开启并行编译及配置第三方SDK隐私key

gradlew Mac/linux/Terminal环境下的构建脚本。shell脚本，允许开发人员在本地无需手动i安装gradle的情况下，使项目指定统一gradle版本运行的构建命令（如 ./gradlew assembleRelease）。

gradlew.bat windows环境下的构建脚本。功能与gradlew完全一直，专供windowsCmd和powerShell命令调用

## 主流android项目根目录常见文件夹及文件
MyApplication/
├── .gradle/               # Gradle 构建缓存目录
├── .idea/                 # Android Studio / IntelliJ 项目配置文件
├── app/                   # 默认的主应用模块 (Module)
├── gradle/                # Gradle 包装器 (Wrapper) 的依赖和配置文件
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── build.gradle.kts       # 根构建配置
├── settings.gradle.kts    # 项目模块管理配置
├── gradle.properties      # 构建环境变量配置
├── gradlew                # Shell 构建脚本
├── gradlew.bat            # Windows 构建脚本
├── .gitignore             # Git 版本控制忽略文件
├── local.properties       # 本地 SDK/NDK 路径配置（不提交到 Git）
└── README.md              # 项目说明文档

app/	文件夹	项目的默认主程序模块。包含该模块的业务代码（src/）、局部 build.gradle、资源文件（res/）以及 AndroidManifest.xml。(大型项目还会包含 core/、feature/ 等其他自定义子模块)
gradle/	文件夹	存放 Gradle Wrapper 运行所需文件。核心是 gradle-wrapper.properties，里面指定了项目运行所需的具体 Gradle 运行时版本及下载地址（下载 .zip 压缩包）。
.gradle/	文件夹	Gradle 在本地构建时自动生成的缓存文件夹。保存了增量编译数据、依赖包下载缓存等（通常加入 .gitignore，不提交到代码库）。
.idea/	文件夹	Android Studio 开发工具的配置文件。包含项目的代码风格（Code Style）、运行/调试配置（Run Configuration）、模块依赖索引等（部分文件会提交，部分加入忽略）。
local.properties	文件	本地硬件与 SDK 路径配置文件。主要记录当前机器上的 Android SDK 和 NDK 的绝对路径（如 sdk.dir=/Users/xxx/Library/Android/sdk）。由 IDE 自动生成，严禁提交至公共代码仓库。

## 1.Diceroller计算器
