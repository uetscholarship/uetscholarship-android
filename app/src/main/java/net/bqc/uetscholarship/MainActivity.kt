package net.bqc.uetscholarship

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.nispok.snackbar.Snackbar
import com.nispok.snackbar.SnackbarManager
import com.shirwa.simplistic_rss.RssReader

class MainActivity : AppCompatActivity(), ConnectionReceiver.ConnectionReceiverListener {

    companion object {
        const val EXTRA_URL: String = "EXTRA_URL"
    }

    private lateinit var progressDialog: ProgressDialog
    private lateinit var listView: ListView
    private var adapter: ArrayAdapter<NewsItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check connection
        checkConnection()

        // setup toolbar
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
    }

    inner class GetNewsTask : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            progressDialog = ProgressDialog.show(this@MainActivity,
                    "Loading", "Loading data from server...", true)
        }

        override fun doInBackground(vararg p0: String?): String {
            val rssReader = RssReader(p0[0])
            rssReader.items
                    .map { NewsItem(it.title, it.description, it.link) }
                    .forEach { adapter!!.add(it) }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            adapter!!.notifyDataSetChanged()
            listView.adapter = adapter
            progressDialog.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        UETScholarShipApp.getInstance().setConnectionListener(this@MainActivity)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            showConnectionError()
        }
        else if (adapter == null){
            doJob()
        }
    }

    private fun checkConnection() {
        val isConnected = ConnectionReceiver.isConnected()
        if (!isConnected) {
            showConnectionError()
        }
        else {
            doJob()
        }
    }

    private fun doJob() {
        // setup list view
        listView = findViewById<ListView>(R.id.news_list)
        adapter = NewsAdapter(this, R.layout.list_item_layout)
        GetNewsTask().execute("https://uet.vnu.edu.vn/category/tin-tuc/tin-sinh-vien/feed/")

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, BrowserActivity::class.java)
            intent.putExtra(EXTRA_URL, adapter!!.getItem(position).link)
            startActivity(intent)
        }
    }

    private fun showConnectionError() {
        SnackbarManager.show(
                Snackbar.with(applicationContext)
                        .text("I need Internet connection :(")
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE),
                this@MainActivity)
    }


//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.itemId) {
//            R.id.action_favorite -> {
//                FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.scholarship_topic))
//                Toast.makeText(this, getString(R.string.msg_subscribed), Toast.LENGTH_LONG).show()
//                return true
//            }
//        }
//        return true
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
}
