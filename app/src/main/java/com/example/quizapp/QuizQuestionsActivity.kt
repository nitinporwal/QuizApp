package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        setQuestion()

        var optionOneTv = findViewById<TextView>(R.id.tv_option_one)

        var optionTwoTv = findViewById<TextView>(R.id.tv_option_two)

        var optionThreeTv = findViewById<TextView>(R.id.tv_option_three)

        var optionFourTv = findViewById<TextView>(R.id.tv_option_four)

        optionOneTv.setOnClickListener(this)
        optionTwoTv.setOnClickListener(this)
        optionThreeTv.setOnClickListener(this)
        optionFourTv.setOnClickListener(this)

        var submitBtn = findViewById<Button>(R.id.btn_submit)
        submitBtn.setOnClickListener(this)

    }

    private fun setQuestion() {
        var question = mQuestionsList!![mCurrentPosition-1]

        var submitBtn = findViewById<Button>(R.id.btn_submit)

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size) {
            submitBtn.text = "FINISH"
        }
        else {
            submitBtn.text = "SUBMIT"
        }

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

        var submitBtn = findViewById<Button>(R.id.btn_submit)

        when(v?.id) {
            R.id.tv_option_one -> selectedOptionView(optionOneTv, 1)
            R.id.tv_option_two -> selectedOptionView(optionTwoTv, 2)
            R.id.tv_option_three -> selectedOptionView(optionThreeTv, 3)
            R.id.tv_option_four -> selectedOptionView(optionFourTv, 4)
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else {
                    val question = mQuestionsList!!.get(mCurrentPosition-1)
                    if(question!!.correctAns != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAns, R.drawable.correct_option_border_bg)
                    if(mCurrentPosition == mQuestionsList!!.size) {
                        submitBtn.text = "FINISH"
                    }
                    else {
                        submitBtn.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {

        var optionOneTv = findViewById<TextView>(R.id.tv_option_one)

        var optionTwoTv = findViewById<TextView>(R.id.tv_option_two)

        var optionThreeTv = findViewById<TextView>(R.id.tv_option_three)

        var optionFourTv = findViewById<TextView>(R.id.tv_option_four)

        when(answer) {
            1-> {
                optionOneTv.background = ContextCompat.getDrawable(this, drawableView)
            }
            2-> {
                optionTwoTv.background = ContextCompat.getDrawable(this, drawableView)
            }
            3-> {
                optionThreeTv.background = ContextCompat.getDrawable(this, drawableView)
            }
            4-> {
                optionFourTv.background = ContextCompat.getDrawable(this, drawableView)
            }


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