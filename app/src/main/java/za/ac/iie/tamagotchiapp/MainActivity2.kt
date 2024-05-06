package za.ac.iie.tamagotchiapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    // Array of counters
    private val counters = IntArray(3)

    // Handler to perform counter decrement every 30 seconds
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val petImage = findViewById<ImageView>(R.id.pet_image)
        val statusText = findViewById<TextView>(R.id.status_text)
        val feedButton = findViewById<Button>(R.id.button_feed)
        val cleanButton = findViewById<Button>(R.id.button_clean)
        val playButton = findViewById<Button>(R.id.button_play)
        val feedCounterTextView =findViewById<TextView>(R.id.feedCounter)
        val cleanCounterTextView = findViewById<TextView>(R.id.cleanCounter)
        val playCounterTextView = findViewById<TextView>(R.id.playCounter)

        // set initial counters
        updateCounterTexts()

        // set button click listeners
        feedButton.setOnClickListener{
            changeImageAndIncrementCounter(0,R.drawable.pet_eating)
        }
        cleanButton.setOnClickListener {
            changeImageAndIncrementCounter(1, R.drawable.pet_cleaning)
        }
        playButton.setOnClickListener {
            changeImageAndIncrementCounter(2, R.drawable.pet_playing)
        }

        // start counter decrement every 30 seconds
        startCounterDecrement()
    }

    // Method to change the image and increment the counter
    private fun changeImageAndIncrementCounter(counterIndex: Int, imageResId: Int){
        // change the image
        findViewById<ImageView>(R.id.pet_image).setImageResource(imageResId)

        // Increment the counter by INCREMENT
        counters[counterIndex] += INCREMENT

        // Ensure counters do not exceed the maximum limit
        if (counters[counterIndex] > MAX_COUNTER) {
            counters[counterIndex] = MAX_COUNTER
        }

        // Update the counter text views
        updateCounterTexts()
    }

    // Method to update the counter text views
    private fun updateCounterTexts() {
        findViewById<TextView>(R.id.feedCounter).text = "Feed counter: ${counters[0]}"
        findViewById<TextView>(R.id.cleanCounter).text = "Clean counter: ${counters[1]}"
        findViewById<TextView>(R.id.playCounter).text = "Play counter: ${counters[2]}"
    }

    // Method to start counter decrement every 30 seconds
    private fun startCounterDecrement() {
        handler.postDelayed({
            decrementCounters()
            startCounterDecrement() // Schedule next decrement
        },20000) // 20 seconds
    }

    // Method to decrement all counters by DECREMENT
    private fun decrementCounters() {
        for (i in counters.indices){
            counters[i] = (counters[i] - DECREMENT).coerceAtLeast(0) //Ensure counter
        }
        updateCounterTexts()
    }

    companion object {
        private const val INCREMENT = 10
        private const val DECREMENT =20
        private const val MAX_COUNTER = 500
    }
}



