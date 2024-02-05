package com.example.rxjavawithkotlin.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavawithkotlin.R
import com.example.rxjavawithkotlin.network.BookListModel
import com.example.rxjavawithkotlin.network.VolumeInfo


class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    var bookListData = ArrayList<VolumeInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false )
        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: BookListAdapter.MyViewHolder, position: Int) {
        holder.bind(bookListData[position])
    }

    override fun getItemCount(): Int {
        return bookListData.size
    }

    class   MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvPublisher = view.findViewById<TextView>(R.id.tvPublisher)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val thumbImageView = view.findViewById<ImageView>(R.id.thumbImageView)

        fun bind(data : VolumeInfo){
            tvTitle.text = data.volumeInfo.title
            tvPublisher.text = data.volumeInfo.publisher
            tvDescription.text = data.volumeInfo.description

//            val url  = data .volumeInfo?.imageLinks.smallThumbnail
//            if (url == null){
//
//            }else{
//                Glide.with(thumbImageView)
//                    .load(url)
//                    .circleCrop()
//                    .into(thumbImageView)
//            }
            Glide.with(thumbImageView)
                .load("https://cdn.pixabay.com/photo/2021/10/13/15/09/water-6706894_960_720.jpg")
                .circleCrop()
                .into(thumbImageView)
        }
    }
}