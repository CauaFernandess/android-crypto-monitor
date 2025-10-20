package cauafernandess.com.github.cryptomonitor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cauafernandess.com.github.cryptomonitor.screen.Bitcoin
import cauafernandess.com.github.cryptomonitor.ui.theme.HomeViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {

                        TopAppBar(
                            title = {
                                Text(
                                    text = "Monitor de BITCOIN",
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0D47A1)
                            )
                        )
                    }
                ) { innerPadding ->

                    Bitcoin(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        vm = HomeViewModel()
                    )
                }
            }
        }
    }
}