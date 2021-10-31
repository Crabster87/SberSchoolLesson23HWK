package crabster.rudakov.sberschoollesson23hwk.main

import MainAdapter
import MainItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import crabster.rudakov.sberschoollesson23hwk.R
import crabster.rudakov.sberschoollesson23hwk.contacts.ContactsActivity
import crabster.rudakov.sberschoollesson23hwk.databinding.ActivityMainBinding
import crabster.rudakov.sberschoollesson23hwk.file.AllFilesActivity
import crabster.rudakov.sberschoollesson23hwk.file.FileListActivity
import crabster.rudakov.sberschoollesson23hwk.group.PermissionGroupActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = MainAdapter(
            listOf(
                MainItem(R.string.contacts) {
                    startActivity(
                        ContactsActivity.newIntent(this)
                    )
                },
                MainItem(R.string.permission_groups) {
                    startActivity(
                        PermissionGroupActivity.newIntent(this)
                    )
                },
                MainItem(R.string.app_internal_storage) {
                    startActivity(FileListActivity.newIntent(this, filesDir, false))
                },
                MainItem(R.string.cache) {
                    startActivity(FileListActivity.newIntent(this, cacheDir, false))
                }
            ) + getExternalFilesDirs(null).map {
                MainItem(R.string.app_external_storage) {
                    startActivity(FileListActivity.newIntent(this, it, true))
                }
            } + listOf(
                MainItem(R.string.documents) {
                    startActivity(
                        FileListActivity.newIntent(
                            this,
                            getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!,
                            true
                        )
                    )
                },
                MainItem(R.string.external_storage) {
                    startActivity(AllFilesActivity.newIntent(this))
                }
            )
        )
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )
    }

}