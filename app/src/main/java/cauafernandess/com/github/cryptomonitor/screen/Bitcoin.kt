package cauafernandess.com.github.cryptomonitor.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cauafernandess.com.github.cryptomonitor.ui.theme.HomeViewModel


@Composable
fun Bitcoin(
    modifier: Modifier = Modifier,
    vm: HomeViewModel
) {
    val state = vm.state.value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Cotação - BITCOIN",
                fontSize = 18.sp,
                color = Color.Gray
            )

            Text(
                text = state.priceText,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = state.timeText,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { vm.refresh() },
                enabled = !state.loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B5E20)
                ),
                modifier = Modifier.width(200.dp)
            ) {
                if (state.loading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp),
                        color = Color.White
                    )
                    Spacer(Modifier.width(10.dp))
                }
                Text(
                    text = "ATUALIZAR",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            state.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = Color.Red)
            }
        }
    }
}