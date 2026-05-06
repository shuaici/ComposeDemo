# ComposeDemo

本仓库是 **Jetpack Compose 从入门到进阶** 的配套代码与文章：`@Composable` 与重组、`StateFlow`/`ViewModel`、`Modifier`、副作用 API、`LazyColumn`/`key`、Navigation Compose、Material3、重组稳定性、单测与 UI Test 入口等；另有 `:core:ui` 设计系统示例与单 Activity 导航骨架。正文见 [docs/articles/README.md](docs/articles/README.md)（**00～12**，含基础控件与 Layout/动画/Focus 精通段），适合 CSDN 专栏与自学对照截图。

## 版本基线（文首请同步更新）

| 组件 | 版本 |
|------|------|
| Kotlin | 2.0.21 |
| Android Gradle Plugin | 8.11.2 |
| Compose Compiler | 与 Kotlin 对齐（`org.jetbrains.kotlin.plugin.compose`） |
| Compose BOM | `2026.03.00`（见 `gradle/libs.versions.toml`） |
| minSdk / compileSdk | 24 / 36 |

会随时间过期的数字只放在上表与 `libs.versions.toml`；文章论述尽量写原则、取舍与验收标准。

## 模块与职责（起点）

| 模块 | 职责 |
|------|------|
| `:app` | Application、导航图 `AppNavHost`、feature 组合入口 |
| `:core:ui` | 设计系统（Theme / Token），业务模块只依赖其公开 API，不反向依赖 feature |

当前代码量刻意保持小：**可运行 + 可扩展**。

## 本地构建

```bash
./gradlew :app:assembleDebug
```

## 许可

若计划公开发布，请自行补充 `LICENSE` 并在此 README 链接。
