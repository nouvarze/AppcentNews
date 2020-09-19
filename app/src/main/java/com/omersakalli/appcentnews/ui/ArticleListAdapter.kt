package com.omersakalli.appcentnews.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.data.model.Articles
import com.omersakalli.appcentnews.databinding.ListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleListAdapter(val onItemListener: OnItemListener) :
    RecyclerView.Adapter<ArticleListAdapter.MyViewHolder>() {

    val articleList = ArrayList<Article>()

    fun setList(articles: List<Article>){
        articleList.clear()

        articleList.addAll(articles)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
        return articleList.size
    }




    class MyViewHolder(
        val binding:ListItemBinding,
        val onItemListener: OnItemListener
    ): RecyclerView.ViewHolder(binding.root), View.OnClickListener{


        fun bind(article: Article){
            binding.apply {
                NewsTitle.text  =article.title
                NewsContent.text=article.description
                Glide.with(listItem)
                    .load(article.urlToImage)
                    .fitCenter()
                    .placeholder(R.drawable.ic_image_placeholder)
                    .into(NewsImage)

                listItem.setOnClickListener{
                    onClick(listItem)
                }
            }
        }

        override fun onClick(p0: View?) {
            CoroutineScope(Dispatchers.Main).launch {
                onItemListener.onItemClick(adapterPosition)
            }
        }



    }

    interface OnItemListener {
        suspend fun onItemClick(position: Int)
    }
}
