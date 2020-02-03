package ru.job4j.simpleplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import ru.job4j.simpleplayer.databinding.ActivityMainBinding
import java.lang.reflect.Field


class MainActivity : AppCompatActivity() {
    var currentTrack: Int = -1
    var newTrack: Int = -1
    private var media: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindig: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        val files: List<Field> = FileManager.getFileNames()
        val adapter: ArrayAdapter<Field> = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_expandable_list_item_1,
            files
        )
        bindig.trackList.adapter = adapter
        currentTrack = files.get(0).getInt(files.get(0))
        newTrack = files.get(0).getInt(files.get(0))
        activateMedia(currentTrack)
        bindig.currentTrack.setText(currentTrack)

        bindig.trackList.setOnItemClickListener { parent, view, position, id ->
            currentTrack = newTrack
            newTrack = files.get(position).getInt(files.get(position))
            bindig.currentTrack.setText(newTrack)
        }
        bindig.play.setOnClickListener {
            try {
                if (newTrack == currentTrack)
                    media?.start() else {
                    activateMedia(newTrack)
                    media?.start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        bindig.stop.setOnClickListener {
            try {
                media?.pause()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        media?.release();
        media = null;
    }

    fun activateMedia(id: Int) {
        media?.release()
        media = MediaPlayer.create(this, id)


    }
}
