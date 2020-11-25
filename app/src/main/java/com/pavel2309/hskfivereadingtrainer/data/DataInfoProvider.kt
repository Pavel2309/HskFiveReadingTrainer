package com.pavel2309.hskfivereadingtrainer.data

import com.pavel2309.hskfivereadingtrainer.data.model.*

object DataInfoProvider {

    private fun initAllList(): MutableList<CategoryWithQuestionsAndAnswers> {

        val all = mutableListOf<CategoryWithQuestionsAndAnswers>()

        all.add(
            CategoryWithQuestionsAndAnswers(
                Category(1, "FIRST CATEGORY", false), mutableListOf(
                    QuestionWithAnswers(
                        Question(1, 1, "1"), mutableListOf(
                            Answer(1, 1, "test", true)
                        )
                    )
                )
            )
        )

        return all
    }


    private fun initCategoryListNew(): MutableList<Category> {
        val categories = mutableListOf<Category>()

        categories.add(
            Category(0, "one", false)
        )

        categories.add(
            Category(0, "two", false)
        )

        return categories
    }


    var categoryList = initCategoryList()
    var questionList = initQuestionList()
    var answerList = initAnswerList()

    private fun initCategoryList(): MutableList<Category> {

        val categories = mutableListOf<Category>()


        categories.add(
            Category(
                1,
                "曾经有 7 个人住在一起,每天分一大桶食物。不\n" +
                        "幸的是,食物每天都是不够的。\n" +
                        "一开始,他们抽签决定由谁来负责分食物,谁抽\n" +
                        "到谁分,每天抽一次,已经抽中的一周之内不能再抽。\n" +
                        "于是,每个星期下来,他们只有一天是饱的,就是自\n" +
                        "己分食物的那一天。\n" +
                        "后来他们共同推选出一个品德最好的人来分食物。然而,为了能分到更多的\n" +
                        "食物,其余的人开始想办法去讨好他,纷纷赞美他,谁说的好话多谁分到的食物\n" +
                        "就多,这还是不能解决问题。\n" +
                        "再后来,大家开始组成三人或 4 人的评选委员会,每次分食物都要相互争论\n" +
                        "半天,结果到最后吃到嘴里的食物全是凉的。\n" +
                        "最后,他们想出来一个方法:轮流分食物,但负责分的人要等其他人都挑完\n" +
                        "后,才能拿剩下的最后一碗。为了不让自己吃到最少的,每人都尽量分得平均;\n" +
                        "就算不平均,也只能自己吃亏了。就这样,大家的日子变得快乐了许多。",
                false
            )
        )

        categories.add(
            Category(
                2,
                "SECOND CATEGORY",
                false
            )
        )

        categories.add(
            Category(
                3,
                "THIRD CATEGORY",
                false
            )
        )

        categories.add(
            Category(
                4,
                "FOURTH CATEGORY",
                false
            )
        )

        categories.add(
            Category(
                5,
                "FIFTH CATEGORY",
                false
            )
        )


        return categories
    }

    private fun initQuestionList(): MutableList<Question> {

        val questions = mutableListOf<Question>()


        //CATEGORY 1
        questions.add(
            Question(
                1,
                1,
                "他们为什么要讨好别人?"
            )
        )
        questions.add(
            Question(
                2,
                1,
                "哪种办法最合理?"
            )
        )
        questions.add(
            Question(
                3,
                1,
                "根据上文,下列哪项正确?"
            )
        )
        questions.add(
            Question(
                4,
                1,
                "上文主要想告诉我们什么?"
            )
        )


        // CATEGORY 2
        questions.add(
            Question(
                5,
                2,
                "Second category questions 1 (originally only has one question)"
            )
        )

        return questions
    }


    private fun initAnswerList(): MutableList<Answer> {

        val answers = mutableListOf<Answer>()


        // QUESTION 1
        answers.add(
            Answer(
                1,
                1,
                "想逃避责任",
                false
            )
        )

        answers.add(
            Answer(
                2,
                1,
                "想获得尊重",
                false
            )
        )
        answers.add(
            Answer(
                3,
                1,
                "想负责分食物",
                false
            )
        )
        answers.add(
            Answer(
                4,
                1,
                "想分到更多食物",
                true
            )
        )


        //QUESTION 2
        answers.add(
            Answer(
                5,
                2,
                "抽签",
                false
            )
        )
        answers.add(
            Answer(
                6,
                2,
                "集体决定怎么分",
                false
            )
        )
        answers.add(
            Answer(
                7,
                2,
                "负责分的人最后选",
                true
            )
        )
        answers.add(
            Answer(
                8,
                2,
                "选品德好的人来分",
                false
            )
        )


        //QUESTION 3
        answers.add(
            Answer(
                9,
                3,
                "没人愿意做家务",
                false
            )
        )
        answers.add(
            Answer(
                10,
                3,
                "大家对厨师很满意",
                false
            )
        )
        answers.add(
            Answer(
                11,
                3,
                "每天都有多余的食物",
                false
            )
        )
        answers.add(
            Answer(
                12,
                3,
                "他们找到了公平的办法",
                true
            )
        )

        //QUESTION 4
        answers.add(
            Answer(
                13,
                4,
                "人多力量大",
                false
            )
        )
        answers.add(
            Answer(
                14,
                4,
                "不能浪费粮食",
                false
            )
        )
        answers.add(
            Answer(
                15,
                4,
                "没有绝对的公平",
                false
            )
        )
        answers.add(
            Answer(
                16,
                4,
                "好的制度可以避免矛盾",
                true
            )
        )


        //CATEGORY 2, QUESTION 1
        answers.add(
            Answer(
                17,
                5,
                "answer one",
                true
            )
        )
        answers.add(
            Answer(
                18,
                5,
                "answer two",
                false
            )
        )
        answers.add(
            Answer(
                19,
                5,
                "answer three",
                false
            )
        )
        answers.add(
            Answer(
                20,
                5,
                "answer four",
                false
            )
        )

        return answers
    }

}