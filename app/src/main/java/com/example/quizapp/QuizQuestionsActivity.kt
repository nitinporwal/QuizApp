package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        var optionOneTv = findViewById<TextView>(R.id.tv_option_one)

        var optionTwoTv = findViewById<TextView>(R.id.tv_option_two)

        var optionThreeTv = findViewById<TextView>(R.id.tv_option_three)

        var optionFourTv = findViewById<TextView>(R.id.tv_option_four)

        optionOneTv.setOnClickListener(this)
        optionTwoTv.setOnClickListener(this)
        optionThreeTv.setOnClickListener(this)
        optionFourTv.setOnClickListener(this)



    }

    private fun setQuestion() {
        mCurrentPosition = 1
        var question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        var progress = findViewById<ProgressBar>(R.id.progressBar)
        progress.progress = mCurrentPosition
        val progressTv = findViewById<TextView>(R.id.tv_progress)
        progressTv.text = "$mCurrentPosition" + "/" + progress.max

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

    private fun defaultOptionsView () {
        var optionOneTv = findViewById<TextView>(R.id.tv_option_one)

        var optionTwoTv = findViewById<TextView>(R.id.tv_option_two)

        var optionThreeTv = findViewById<TextView>(R.id.tv_option_three)

        var optionFourTv = findViewById<TextView>(R.id.tv_option_four)

        val options = ArrayList<TextView>()
        options.add(0, optionOneTv)
        options.add(1, optionTwoTv)
        options.add(2, optionThreeTv)
        options.add(3, optionFourTv)

        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        var optionOneTv = findViewById<TextView>(R.id.tv_option_one)

        var optionTwoTv = findViewById<TextView>(R.id.tv_option_two)

        var optionThreeTv = findViewById<TextView>(R.id.tv_option_three)

        var optionFourTv = findViewById<TextView>(R.id.tv_option_four)

        when(v?.id) {
            R.id.tv_option_one -> selectedOptionView(optionOneTv, 1)
            R.id.tv_option_two -> selectedOptionView(optionTwoTv, 1)
            R.id.tv_option_three -> selectedOptionView(optionThreeTv, 1)
            R.id.tv_option_four -> selectedOptionView(optionFourTv, 1)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg
        )
    }
}