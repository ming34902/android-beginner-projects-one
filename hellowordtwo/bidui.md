


## Groovy 旧写法 build.gradle
/** 
buildscript{
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
     classpath "com.android.tools.build:gradle:9.5.0"
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
*/

## 现在项目里的对应位置 settings.gradle.kts
/**  
  libs.versions.toml 的 [plugins] + 根   build.gradle.kts 的 plugins { alias(...) apply false }
settings.gradle.kts 的 pluginManagement { repositories { ... } }
*/

旧写法是"命令式地在构建脚本上挂全局副作用"，新写法是"在 settings 里集中声明 + 在模块里按需 apply"。


## 1. Groovy 动态 vs Kotlin DSL 静态
build.gradle 是动态类型脚本，写错方法名要到运行才报；  build.gradle.kts 有真实类型，IDE 能补全、跳转、重构，alias() / apply false 这些都是 Kotlin DSL 独有能力。
## 2. AGP 的身份：构建脚本的依赖 → 插件 ⭐
   •
   旧：把 AGP 塞进 buildscript.classpath。这等于污染整个构建脚本自己的 classpath，所有 build.gradle 立刻共享它。版本冲突只能靠 resolutionStrategy 或调整 classpath 顺序去救，是个隐式全局副作用。
   •
   新：AGP 是插件。根脚本用 apply false 只解析版本、不应用，子模块再自己 alias(libs.plugins.android.application) 应用。这就是为什么你的根   build.gradle.kts 只有一行 —— 它只负责"定版本"。
## 3. 仓库声明：散布子项目 → 集中管控 ⭐
   Kotlin
   dependencyResolutionManagement {
   repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
   ...
   }
   allprojects {} 是"对每个子项目执行一遍配置"，而且模块自己还能再写 repositories {} 加私货，来源不统一。FAIL_ON_PROJECT_REPOS 表示：任何模块私自写 repositories 直接构建失败，强制单一来源。你新增的 studydependencies 模块就因此完全不用配仓库。
## 4. 版本统一：硬编码字符串 → Version Catalog
   旧写法到处 "androidx.appcompat:appcompat:1.7.1"，升级要全局搜索替换。新写法在   libs.versions.toml 一处定义，模块里写 libs.appcompat —— 类型安全、写错立刻标红。你 4 个模块共享同一份版本源。
## 5. 配置缓存兼容性 ⚠️
   你在   gradle.properties 开了 org.gradle.configuration-cache=true。allprojects {} / subprojects {} 属于跨项目配置，和配置缓存的设计目标直接冲突，官方明确建议避免。新结构天然兼容 —— 你这段旧片段直接搬进来会和配置缓存打架。
## 6. 插件仓库专门化
   pluginManagement 只回答"去哪儿找插件"。注意你项目里还做了限定：
   Kotlin
   google {
       content {
           includeGroupByRegex("com\\.android.*")
           includeGroupByRegex("com\\.google.*")
           includeGroupByRegex("androidx.*")
       }
   }
   这样解析 junit 时不会去 google 仓库白跑一趟 —— 旧写法做不到这种精细控制。
