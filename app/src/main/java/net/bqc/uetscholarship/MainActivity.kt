package net.bqc.uetscholarship

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.shirwa.simplistic_rss.RssReader

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_URL : String = "EXTRA_URL"
    }

    private lateinit var listView : ListView
    private lateinit var adapter : ArrayAdapter<NewsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup listview
        listView = findViewById<ListView>(R.id.news_list)
        adapter = NewsAdapter(this, R.layout.list_item_layout)
        GetNewsTask().execute("https://uet.vnu.edu.vn/category/tin-tuc/tin-sinh-vien/feed/")

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, BrowserActivity::class.java)
            intent.putExtra(EXTRA_URL, adapter.getItem(position).link)
            startActivity(intent)
        }

        // setup toolbar
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
    }

    inner class GetNewsTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String?): String {
            val rssReader = RssReader(p0[0])
            rssReader.items
                    .map { NewsItem(it.title, it.description, it.link) }
                    .forEach { adapter.add(it) }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            adapter.notifyDataSetChanged()
            listView.adapter = adapter
        }
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
