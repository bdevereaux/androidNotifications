package com.blackboardtheory.notifications.notificationList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blackboardtheory.notifications.NotificationsApplication
import com.blackboardtheory.notifications.R
import com.blackboardtheory.notifications.models.SimpleNotification
import com.blackboardtheory.notifications.notificationList.notificationCreation.CreateNotificationActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class NotificationsActivity : AppCompatActivity() {

    companion object {
        const val CREATE_NOTIFICATION_REQUEST = 0xCDEF
    }

    private lateinit var viewModel: NotificationsViewModel

    private lateinit var fab: FloatingActionButton
    private lateinit var notificationsList: RecyclerView
    private lateinit var adapter: NotificationsAdapter

    /**********************************************************************************************/
    /********************************** Lifecycle Overrides ***************************************/
    /**********************************************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        viewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        adapter = NotificationsAdapter(viewModel)
        notificationsList = findViewById(R.id.notification_list)
        notificationsList.layoutManager = LinearLayoutManager(this)
        notificationsList.adapter = adapter
        // todo add a divider to our recycler view
        notificationsList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        observeModel()

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            createNotification()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(Activity.RESULT_OK == resultCode) {
            val title = data?.getStringExtra(CreateNotificationActivity.KEY_TITLE)
            val subtitle = data?.getStringExtra(CreateNotificationActivity.KEY_SUBTITLE)
            val group = data?.getStringExtra(CreateNotificationActivity.KEY_GROUP)
            viewModel.addNotification(SimpleNotification(title!!, subtitle!!, Date().hashCode(), NotificationsApplication.CHANNEL_ID, group!!))
        }
    }

    /**********************************************************************************************/
    /************************************ Private Methods *****************************************/
    /**********************************************************************************************/

    private fun observeModel() {
        viewModel.notifications.observe(this, Observer { items ->
            adapter.items = items
        })
    }

    private fun createNotification() {
        val createNotificationIntent = Intent(this, CreateNotificationActivity::class.java)
        startActivityForResult(createNotificationIntent, CREATE_NOTIFICATION_REQUEST)
    }
}
