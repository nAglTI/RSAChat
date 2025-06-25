package org.kekus.rsachat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.kekus.rsachat.RootComponentHolder
import org.kekus.rsachat.di.initKoin
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        if (GlobalContext.getOrNull() == null) initKoin()

        onBackPressedDispatcher.addCallback(this) {
            if (!RootComponentHolder.backDispatcher.back()) {
                finish()
            }
        }

        setContent {
            App()
        }
    }

    override fun onPause() {
        super.onPause()
        RootComponentHolder.component.lock()
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}