package cauafernandess.com.github.cryptomonitor.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cauafernandess.com.github.cryptomonitor.service.MercadoBitcoinServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.State
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



data class UiState(
    val loading: Boolean = false,
    val priceText: String = "R$ 0,00",
    val timeText: String = "dd/MM/yyyy HH:mm:ss",
    val error: String? = null
)

class HomeViewModel : ViewModel() {

    private val service = MercadoBitcoinServiceFactory().create()

    private val _state = mutableStateOf(UiState())
    val state: State<UiState> get() = _state

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            try {
                val resp = withContext(Dispatchers.IO) { service.getTicker() }
                if (resp.isSuccessful) {
                    val dto = resp.body()

                    val last = dto?.ticker?.last?.toDoubleOrNull()
                    val price = last?.let {
                        NumberFormat.getCurrencyInstance(Locale("pt","BR")).format(it)
                    } ?: "—"

                    val millis = dto?.ticker?.date?.let { it * 1000L }
                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("pt", "BR")).apply {
                        timeZone = java.util.TimeZone.getTimeZone("America/Sao_Paulo")
                    }
                    val time = millis?.let { sdf.format(Date(it)) } ?: "—"

                    _state.value = UiState(
                        loading = false,
                        priceText = price,
                        timeText = time,
                        error = null
                    )
                } else {
                    _state.value = _state.value.copy(
                        loading = false,
                        error = "Erro ${resp.code()}"
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    loading = false,
                    error = e.message ?: "Falha na chamada"
                )
            }
        }
    }
}

