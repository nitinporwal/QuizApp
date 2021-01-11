package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questionList = Constants.getQuestions()
        var currentPositon = 1
        var question: Question? = questionList[currentPositon-1]

        var progress = findViewById<ProgressBar>(R.id.progressBar)
        progress.progress = currentPositon
        val progressTv = findViewById<TextView>(R.id.tv_progress)
        progressTv.text = "$currentPositon" + "/" + progress.max

        var questionTv = findViewById<TextView>(R.id.tv_question)
        questionTv.text = question!!.question

        var imgIv = findViewById<ImageView>(R.id.iv_image)
        imgIv.setImageResource(question.image)

        var optionOneTv = findViewById<TextView>(R.id.tv_option_one)
        optionOneTv.text = question.optionOne

        var optionTwoTv = findViewById<TextView>(R.id.tv_option_two)
        optionTwoTv.text = question.optionTwo

        var optionThreeTv = findViewById<TextView>(R.id.tv_option_three)
        optionThreeTv.text = question.optionThree

        var optionFourTv = findViewById<TextView>(R.id.tv_option_four)
        optionFourTv.text = question.optionFour
    }
}