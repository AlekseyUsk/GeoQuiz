package com.hfad.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast


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
        )
    )
    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }
        next_Button.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        updateQuestion()
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
