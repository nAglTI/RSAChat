package org.kekus.rsachat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.kekus.rsachat.AppViewModelHolder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    override fun onPause() {
        super.onPause()
        AppViewModelHolder.viewModel.lock()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}