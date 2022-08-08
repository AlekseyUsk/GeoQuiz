package viewModel

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    init {
        Log.d(TAG, "Созданный экземпляр ViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(
            TAG, "Экземпляр ViewModel вот-вот\n" +
                    "будет уничтожен"
        )
    }
}


