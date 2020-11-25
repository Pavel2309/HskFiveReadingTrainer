package com.pavel2309.hskfivereadingtrainer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pavel2309.hskfivereadingtrainer.R
import com.pavel2309.hskfivereadingtrainer.data.model.QuestionWithAnswers


class QuestionAdapter internal constructor(context: Context):
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var questions = emptyList<QuestionWithAnswers>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.findViewById(R.id.question_string)
        val firstOption: TextView = itemView.findViewById(R.id.option_one)
        val secondOption: TextView = itemView.findViewById(R.id.option_two)
        val thirdOption: TextView = itemView.findViewById(R.id.option_three)
        val fourthOption: TextView = itemView.findViewById(R.id.option_four)


        val choiceA: TextView = itemView.findViewById(R.id.textViewA)
        val choiceB: TextView = itemView.findViewById(R.id.textViewB)
        val choiceC: TextView = itemView.findViewById(R.id.textViewC)
        val choiceD: TextView = itemView.findViewById(R.id.textViewD)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {

        val itemView = inflater.inflate(R.layout.question_item, parent,false)

        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        val currentQuestion = questions[position]

        //currentQuestion.answers.size
        //holder.question.text = currentQuestion.answers.size.toString()


        holder.firstOption.visibility = View.GONE
        holder.secondOption.visibility = View.GONE
        holder.thirdOption.visibility = View.GONE
        holder.fourthOption.visibility = View.GONE

        holder.choiceA.visibility = View.GONE
        holder.choiceB.visibility = View.GONE
        holder.choiceC.visibility = View.GONE
        holder.choiceD.visibility = View.GONE


        holder.firstOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColor))
        holder.secondOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColor))
        holder.thirdOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColor))
        holder.fourthOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColor))

        holder.question.text = currentQuestion.question.text
        //holder.question.text = currentQuestion.answers.size.toString()

        for(i in 1..currentQuestion.answers.size) {
            when (i){
                1 -> {holder.firstOption.text = currentQuestion.answers[0].text
                    holder.firstOption.visibility = View.VISIBLE
                    holder.choiceA.visibility = View.VISIBLE}

                2 -> {holder.secondOption.text = currentQuestion.answers[1].text
                    holder.secondOption.visibility = View.VISIBLE
                    holder.choiceB.visibility = View.VISIBLE}

                3 -> {holder.thirdOption.text = currentQuestion.answers[2].text
                    holder.thirdOption.visibility = View.VISIBLE
                    holder.choiceC.visibility = View.VISIBLE}

                4 -> {holder.fourthOption.text = currentQuestion.answers[3].text
                    holder.fourthOption.visibility = View.VISIBLE
                    holder.choiceD.visibility = View.VISIBLE}
            }
        }

//        holder.question.text = currentQuestion.question.text
//        holder.firstOption.text = currentQuestion.answers[0].text
//        holder.secondOption.text = currentQuestion.answers[1].text
//        holder.thirdOption.text = currentQuestion.answers[2].text
//        holder.fourthOption.text = currentQuestion.answers[3].text

        holder.firstOption.setOnClickListener {
            if(currentQuestion.answers[0].isCorrect) {
                holder.firstOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.correctAnswer))
            } else {
                holder.firstOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.wrongAnswer))
            }
        }

        holder.secondOption.setOnClickListener {
            if(currentQuestion.answers[1].isCorrect) {
                holder.secondOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.correctAnswer))
            } else {
                holder.secondOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.wrongAnswer))
            }
        }

        holder.thirdOption.setOnClickListener {
            if(currentQuestion.answers[2].isCorrect) {
                holder.thirdOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.correctAnswer))
            } else {
                holder.thirdOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.wrongAnswer))
            }
        }

        holder.fourthOption.setOnClickListener {
            if(currentQuestion.answers[3].isCorrect) {
                holder.fourthOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.correctAnswer))
            } else {
                holder.fourthOption.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.wrongAnswer))
            }
        }


    }

    override fun getItemCount() = questions.size

    internal fun setQuestions(questions: List<QuestionWithAnswers>) {
        this.questions = questions
        notifyDataSetChanged()
    }



}