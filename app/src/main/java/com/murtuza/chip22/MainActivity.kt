package com.murtuza.chip22

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.murtuza.chip22.databinding.ActivityMainBinding

//help of
//https://code.luasoftware.com/tutorials/android/android-tag-input/

class MainActivity : AppCompatActivity() {
    val gropu1Tags = mutableListOf<String>()
    val upazilaTags = mutableListOf<String>()
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)


        //actvSelectGroup1
        val group1 = resources.getStringArray(R.array.group1)
        val adapter = ArrayAdapter(this, R.layout.dropdown_row, group1)
        mainBinding.actvSelectGroup1.setAdapter(adapter)
        mainBinding.actvSelectGroup1.setText("Select Group1", false)

        mainBinding.actvSelectGroup1.setOnItemClickListener { adapterView, _, position, _ ->
            mainBinding.actvSelectGroup1.setText("Select Group1", false)
            val name = adapterView.getItemAtPosition(position) as String
            addTag(name, mainBinding.mainTagChipGroup,gropu1Tags)
        }

        //group2
        val group2 = resources.getStringArray(R.array.group2)
        val group2Adapter = ArrayAdapter(this, R.layout.dropdown_row, group2)
        mainBinding.actvSelectGroup2.setAdapter(group2Adapter)
        mainBinding.actvSelectGroup2.setText("Select Group2", false)

        mainBinding.actvSelectGroup2.setOnItemClickListener { adapterView, _, position, _ ->
            mainBinding.actvSelectGroup2.setText("Select Group2", false)
            val name = adapterView.getItemAtPosition(position) as String
            addTag(name, mainBinding.chipGroup2,upazilaTags)
        }
    }

    private fun addTag(name: String, chipGroup: ChipGroup, tags: MutableList<String>) {
        if (!name.isEmpty() && !tags.contains(name)) {
            addChipToGroup(name, chipGroup, tags)
            tags.add(name)
        }
    }

    private fun addChipToGroup(name: String, chipGroup: ChipGroup, items: MutableList<String>) {
        val chip = Chip(this)
        chip.text = name

        chip.isClickable = true
        chip.isCheckable = false
        chip.isCloseIconVisible = true

        chipGroup.addView(chip)

        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip)
            items.remove(name)
        }
    }

}