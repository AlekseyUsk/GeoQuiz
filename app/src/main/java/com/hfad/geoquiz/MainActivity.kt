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

    private lateinit var true_button: Button
    private lateinit var false_button: Button
    private lateinit var next_Button: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var prev_button: ImageButton

    private val questionBank = listOf(
        Question(
            R.string.question_australia, true
        ),
        Question(
            R.string.question_oceans,
            true
        ),
        Question(
            R.string.question_mideast,
            false
        ),
        Question(
            R.string.question_africa,
            false
        ),
        Question(
            R.string.question_americas, true
        ),
        Question(
            R.string.question_asia,
            true
        ),
        Question(R.string.matematic, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")

        setContentView(R.layout.activity_main)
// связал activity с экземпляром QuizViewModel ->
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "получил QuizViewModel : $quizViewModel")

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
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }
        next_Button.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(
            TAG,
            "onStart() called"
        )
    }

    override fun onResume() {
        super.onResume()
        Log.d(
            TAG,
            "onResume() called"
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d(
            TAG,
            "onPause() called"
        )
    }

    override fun onStop() {
        super.onStop()
        Log.d(
            TAG,
            "onStop() called"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(
            TAG,
            "onDestroy() called"
        )
    }


    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer =
            questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
