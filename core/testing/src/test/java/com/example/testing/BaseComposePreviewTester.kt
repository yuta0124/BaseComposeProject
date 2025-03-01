package com.example.testing

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.captureRoboImage
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

@OptIn(ExperimentalRoborazziApi::class)
class BaseComposePreviewTester : ComposePreviewTester<AndroidPreviewInfo> {
    // 利用するJunit4のテストルールを作成
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /**
     * build gradleから渡されたroborazziのオプションをカスタマイズする関数
     * Junit4のルールをインジェクトできる
     */
    override fun options(): ComposePreviewTester.Options {
        val testLifecycleOptions = ComposePreviewTester.Options.JUnit4TestLifecycleOptions(
            testRuleFactory = { composeTestRule }
        )

        return super.options().copy(testLifecycleOptions = testLifecycleOptions)
    }

    /**
     * ComposePreviewScannerで集められたPreviewの一覧を返す関数
     */
    override fun previews(): List<ComposablePreview<AndroidPreviewInfo>> {
        val options = options()

        return AndroidComposablePreviewScanner()
            .scanPackageTrees(*options.scanOptions.packages.toTypedArray())
            .getPreviews()
    }

    /**
     * previewsで集められたPreviewが入る
     */
    override fun test(preview: ComposablePreview<AndroidPreviewInfo>) {
        val fileName = AndroidPreviewScreenshotIdBuilder(preview).build()
        val filePath = "$DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH/$fileName.png"
        composeTestRule.activityRule.scenario.recreate()
        composeTestRule.apply {
            setContent { preview() }
            onRoot().captureRoboImage(filePath = filePath)
        }
    }
}
