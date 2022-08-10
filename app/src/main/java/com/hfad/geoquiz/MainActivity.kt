package com.hfad.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import viewModel.QuizViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    // добавим лениво инициализированное свойство,
//чтобы хранить экземпляры QuizViewModel, связанные с
//activity -->
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    private lateinit var true_button: Button
    private lateinit var false_button: Button
    private lateinit var next_Button: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var prev_button: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")

        setContentView(R.layout.activity_main)

        true_button = findViewById(R.id.true_button)
        false_button = findViewById(R.id.false_button)
        questionTextView = findViewById(R.id.question_text_view)
        prev_button = findViewById(R.id.prev_button)
        next_Button = findViewById(R.id.next_button)

        true_button.setOnClickListener {
            checkAnswer(true)
        }
        false_button.setOnClickListener {
            checkAnswer(false)
        }
        prev_button.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }
        next_Button.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer =
            quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
