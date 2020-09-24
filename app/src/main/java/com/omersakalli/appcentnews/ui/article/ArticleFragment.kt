package com.omersakalli.appcentnews.ui.article

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.databinding.ArticleFragmentBinding
import com.omersakalli.appcentnews.ui.home.HomeViewModel

class ArticleFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ArticleFragmentBinding
//    private var itemPosition: Int? = null
    private var article:Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.actionbar_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_favorite){
            Toast.makeText(context,"Share button",Toast.LENGTH_SHORT)

            //TODO
        }
        if (item.itemId == R.id.action_share){
            Toast.makeText(context,"Share button",Toast.LENGTH_SHORT)
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,"Hi! Check this article out!\n"+article!!.url)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent,null)
            startActivity(shareIntent)
        }
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.article_fragment,container,false)


//        itemPosition = savedInstanceState?.getString(ITEM_POSITION)?.toInt()
        article = this.arguments?.getParcelable("article")
//
        if(article==null){
            Toast.makeText(context,"Makale açılırken bir hata oluştu",Toast.LENGTH_SHORT)
        }
        else{
            initArticle()
        }






        return binding.root
    }

    private fun initArticle(){
//        val article = homeViewModel.getArticleToView()

        binding.apply {
            author.text = article?.author
            date.text = article?.publishedAt
            title.text = article?.title
            content.text = article?.content
            Glide.with(articleFrame)
                .load(article?.urlToImage)
                .fitCenter()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(image)
            sourceButton.setOnClickListener {
                //TODO
//                article.url
            }
        }
    }




    companion object {
        fun newInstance() = ArticleFragment()
        private const val ITEM_POSITION = "item_position"
    }
}