package vcmsa.ci.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Companion object holds the four parallel arrays and a counter
    companion object {
        var songs = arrayOf("Stay", "Sunrise", "Falling", "Shine")
        var artists = arrayOf("Zedd", "Norah Jones", "Harry Styles", "Kygo")
        var ratings = arrayOf(5, 4, 3, 5)
        var comments = arrayOf("Great vibe", "Smooth and calm", "Nice lyrics", "Upbeat and fun")
        var songCount = 4  // Start at 4 since initial data already filled
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Linking layout views
        val etSong = findViewById<EditText>(R.id.etSong)
        val etArtist = findViewById<EditText>(R.id.etArtist)
        val etRating = findViewById<EditText>(R.id.etRating)
        val etComment = findViewById<EditText>(R.id.etComment)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnAdd.setOnClickListener {
            val songInput = etSong.text.toString().trim()
            val artistInput = etArtist.text.toString().trim()
            val ratingText = etRating.text.toString().trim()
            val commentInput = etComment.text.toString().trim()

            // Input validation
            if (songInput.isEmpty() || artistInput.isEmpty() || ratingText.isEmpty() || commentInput.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ratingValue = ratingText.toIntOrNull()
            if (ratingValue == null || ratingValue !in 1..5) {
                Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if playlist has room for more songs
            if (songCount < 4) {
                songs[songCount] = songInput
                artists[songCount] = artistInput
                ratings[songCount] = ratingValue
                comments[songCount] = commentInput
                songCount++

                Toast.makeText(this, "Song added to playlist!", Toast.LENGTH_SHORT).show()

                // Clear input fields
                etSong.text.clear()
                etArtist.text.clear()
                etRating.text.clear()
                etComment.text.clear()
            } else {
                Toast.makeText(this, "Playlist is full!", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to DetailActivity
        btnView.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("songCount", songCount)
            intent.putExtra("songs", songs)
            intent.putExtra("artists", artists)
            intent.putExtra("ratings", ratings.toIntArray())  // Convert to primitive array
            intent.putExtra("comments", comments)
            startActivity(intent)
        }

        // Exit the app
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}
