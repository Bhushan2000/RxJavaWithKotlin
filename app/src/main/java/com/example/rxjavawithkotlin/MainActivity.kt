package com.example.rxjavawithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavawithkotlin.adapter.BookListAdapter
import com.example.rxjavawithkotlin.network.BookListModel
import com.example.rxjavawithkotlin.viewmodel.MainActivityViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var etSearch: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var bookListAdapter: BookListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerview()
        initSearchBox()
    }

    private fun initSearchBox() {
        etSearch = findViewById(R.id.etQuery)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadDataFromApi(etSearch.text.toString().trim())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun loadDataFromApi(input: String) {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.makeApiCall(input)

        viewModel.getBookListObserver().observe(this, Observer<BookListModel> {
            if (it != null) {
                // update adapter
                bookListAdapter.bookListData = it.items
                bookListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error While Fetching data", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initRecyclerview() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)
            bookListAdapter = BookListAdapter()

            adapter = bookListAdapter


        }
    }
}