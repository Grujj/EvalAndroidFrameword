package com.example.mescompetences.popups

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.R
import com.example.mescompetences.adapters.TagAdapter
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.repositories.CompetenceRepository
import java.time.LocalDateTime

class CompetenceDetailsPopup(context: Context, val competence: CompetenceModel): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_competence_details)

        handleNameView()
        handleDescriptionView()
        handleTagsView()
        handleDeleteButtonView()
        handleLevelView()
        handleButtonsColor()
        handleButtonsView()
    }

    private fun handleNameView(): Unit {
        val nameView = findViewById<TextView>(R.id.competence_title)
        nameView.text = competence.name
    }

    private fun handleDescriptionView(): Unit {
        val descriptionView = findViewById<TextView>(R.id.description_text)
        descriptionView.text = competence.description
    }

    private fun handleTagsView() {
        val tagsView = findViewById<RecyclerView>(R.id.tags_recycler)
        tagsView.adapter = TagAdapter(competence.tags)
    }

    private fun handleDeleteButtonView() {
        val buttonView = findViewById<Button>(R.id.button_delete)
        buttonView.setOnClickListener{
            CompetenceRepository.remove(competence)
            dismiss()
        }
    }

    private fun handleLevelView(): Unit {
        val level = findViewById<TextView>(R.id.level_label)
        level.text = competence.level.toString()
    }

    private fun handleButtonsView(): Unit {

        val increaseButton = findViewById<ImageButton>(R.id.increase_level_button)
        val decreaseButton = findViewById<ImageButton>(R.id.decrease_level_button)

        increaseButton.setOnClickListener{
            competence.level++
            updateCompetence()
            updateDetailsView()
        }

        decreaseButton.setOnClickListener{
            competence.level--
            updateCompetence()
            updateDetailsView()
        }
    }

    private fun updateCompetence(): Unit {
        competence.updateDate = LocalDateTime.now().toString()
        CompetenceRepository.insert(competence)
    }

    private fun updateDetailsView(): Unit {
        handleButtonsColor()
        handleLevelView()
    }

    private fun handleButtonsColor(): Unit {

        val increaseButton = findViewById<ImageButton>(R.id.increase_level_button)
        val decreaseButton = findViewById<ImageButton>(R.id.decrease_level_button)

        when (competence.level) {
            20 -> increaseButton.setBackgroundColor(context.getColor(R.color.grey))
            0 -> decreaseButton.setBackgroundColor(context.getColor(R.color.grey))
            else -> {
                increaseButton.setBackgroundColor(context.getColor(R.color.green))
                decreaseButton.setBackgroundColor(context.getColor(R.color.red))
            }
        }
    }
}