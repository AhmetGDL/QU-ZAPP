package com.example.bilbakalim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.bilbakalim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUİZ_COUNT = 5

    private  val quizData = mutableListOf(
        //Sorular

        mutableListOf("Cin","Pekin","Cakarta","Manila","Stokholm"),
        mutableListOf("Hindistan","Yeni delhi","Pekin","Bangkok","Seoul"),
        mutableListOf("Endenozya","Cakarta","Manila","Yeni delhi","Kuala lumpur"),
        mutableListOf("japonya","tokyo","Bangkok","Ankara","Cakarta"),
        mutableListOf("Tayland","Bangkok","Berlin","Havana","Kingston"),
        mutableListOf("Brazilya","Brazilya","Havana","Bangkok","Kopenhag"),
        mutableListOf("Kanada","Ottawa","bern","copenhagen","jakarta"),
        mutableListOf("Küba","Havana","Ankara","Londra","Meksika"),
        mutableListOf("Türkiye","Ankara","Ottowa","Kopenhag","Santiago"),
        mutableListOf("ABD","Washington D.C.","San jose","Ankara","kuala lumpur"),
        mutableListOf("Fransa","Paris","Ottowa","Kopenhag","Tokyo"),
        mutableListOf("Almanya","Berlin","Kopenhag","Bangkok","Santiago"),
        mutableListOf("İtalya","Roma","Londra","Paris","Athena"),
        mutableListOf("Azerbaycan","Bakü","Madrid","Cakarta","Havana"),
        mutableListOf("İngiltere","Londra","Roma","Paris","Singapur")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        quizData.shuffle()
        showNextQuiz()
    }

  fun showNextQuiz(){
      // countLabel güncellenir
      binding.countlabel.text = getString(R.string.count_label, quizCount)

      //Bir quiz seti seçilir
      val quiz = quizData[0]

      binding.questionLabel.text = quiz[0]
      rightAnswer=quiz[1]

      quiz.removeAt(0)

      quiz.shuffle()

      binding.answerBtn1.text = quiz[0]
      binding.answerBtn2.text = quiz[1]
      binding.answerBtn3.text = quiz[2]
      binding.answerBtn4.text = quiz[3]


      quizData.removeAt(0)

  }

   fun checkAnswer(view: View) {

       val answerBtn: Button = findViewById(view.id)
       val btnText =answerBtn.text.toString()

       val alertTitle: String
       if (btnText == rightAnswer){
           //Doğru cevap verince gerçekleşir
           alertTitle= "Doğru!"
           rightAnswerCount++
       }
       else{
        //Yanlış cevep verince gerçekleşir
           alertTitle = "Yanlış"
       }

       AlertDialog.Builder(this)
           .setTitle(alertTitle)
           .setMessage("Cevap: $rightAnswer")
           .setPositiveButton("ok"){dialogInterface, i ->
               checkQuizCount()
           }
           .setCancelable(false)
           .show()

   }

    fun checkQuizCount(){

        if(quizCount == QUİZ_COUNT){

            //Sonuç
            val intent = Intent(this@MainActivity,ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
            startActivity(intent)

        }
        else{
            quizCount++
            showNextQuiz()
        }

    }

}