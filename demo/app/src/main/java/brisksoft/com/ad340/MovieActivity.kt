package brisksoft.com.ad340

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*


class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val b = this.intent.extras
        val movieData = b!!.getStringArray(RecyclerActivity.EXTRA_MESSAGE)

        movieTitle.text = movieData[0]
        movieYear.text = movieData[1]
        movieDesc.text = movieData[4]

    }

}