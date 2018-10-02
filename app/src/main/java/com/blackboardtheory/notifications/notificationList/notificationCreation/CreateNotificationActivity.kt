package com.blackboardtheory.notifications.notificationList.notificationCreation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.blackboardtheory.notifications.R

class CreateNotificationActivity: AppCompatActivity() {

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_SUBTITLE = "subtitle"
        const val KEY_GROUP = "group"
    }

    private lateinit var titleEdit: EditText
    private lateinit var subtitleEdit: EditText
    private lateinit var groupEdit: EditText

    private lateinit var viewModel: CreateNotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notification)
        title = "New Notification"
        viewModel = ViewModelProviders.of(this).get(CreateNotificationViewModel::class.java)
        titleEdit = findViewById(R.id.title_edit)
        subtitleEdit = findViewById(R.id.subtitle_edit)
        groupEdit = findViewById(R.id.group_edit)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_create_notification, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(R.id.save_notification == item.itemId) {
            // validate our fields have stuff in them
            viewModel.title.value = titleEdit.text.toString()
            viewModel.subtitle.value = subtitleEdit.text.toString()
            viewModel.group.value = groupEdit.text.toString()

            if(viewModel.isValid()) {
                val returnIntent = Intent()
                returnIntent.putExtra(KEY_TITLE, viewModel.title.value)
                returnIntent.putExtra(KEY_SUBTITLE, viewModel.subtitle.value)
                returnIntent.putExtra(KEY_GROUP, viewModel.group.value)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            } else {
                // display a Toast that the data is invalid
            }
            return true
        }
        return false
    }

}