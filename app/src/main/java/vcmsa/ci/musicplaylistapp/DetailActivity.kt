package vcmsa.ci.musicplaylistapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // FIXED: use correct layout

        // Link UI elements to their IDs in XML
        val btnShow = findViewById<Button>(R.id.btnShow)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val tvDetails = findViewById<TextView>(R.id.tvDetails)

        // Show all added song details
        btnShow.setOnClickListener {
            val builder = StringBuilder()

            // Use only the number of songs actually added
            for (i in 0 until MainActivity.songCount) {
                builder.append("Song: ${MainActivity.songs[i]}\n")
                builder.append("Artist: ${MainActivity.artists[i]}\n")
                builder.append("Rating: ${MainActivity.ratings[i]}\n")
                builder.append("Comment: ${MainActivity.comments[i]}\n\n")
            }

            // Set text to TextView
            tvDetails.text = builder.toString()
        }

        // Calculate average rating of added songs
        btnAverage.setOnClickListener {
            var total = 0

            // Sum up only the ratings of the songs added
            for (i in 0 until MainActivity.songCount) {
                total += MainActivity.ratings[i]
            }

            // Avoid division by zero
            if (MainActivity.songCount > 0) {
                val avg = total.toDouble() / MainActivity.songCount
                tvDetails.text = "Average Rating: %.2f".format(avg)
            } else {
                tvDetails.text = "No songs in playlist to calculate average."
            }
        }

        // Go back to MainActivity
        btnBack.setOnClickListener {
            finish()
        }
    }
}
