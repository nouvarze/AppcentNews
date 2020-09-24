package com.omersakalli.appcentnews.ui.article

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.omersakalli.appcentnews.MainActivity
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.data.db.FavArticleDao
import com.omersakalli.appcentnews.data.db.FavoritesDatabase
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.data.repository.ArticlesRepository
import com.omersakalli.appcentnews.databinding.ArticleFragmentBinding
import com.omersakalli.appcentnews.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleFragment : Fragment() {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var binding: ArticleFragmentBinding


    //    private var itemPosition: Int? = null
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.actionbar_menu, menu)

//        if (FavoritesDatabase.getInstance(requireContext()).favArticleDao()
//                .hasItem(article!!.url)
//        ) {
//            val item = menu.findItem(R.menu.actionbar_menu)
//
//            val drawable: Drawable = menu.getItem(0).icon
//            drawable.mutate()
//            drawable.setColorFilter(resources.getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP)
//        }


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {

//                Toast.makeText(context, "Clicked on fav", Toast.LENGTH_LONG)

//                CoroutineScope(Dispatchers.IO).launch { articleViewModel.toggleFavorite(article!!) }

                articleViewModel.favArticles.observe(this, Observer {
                    if(it.contains(article)){
                        articleViewModel.removeArticle(article!!)
                    }
                    else articleViewModel.addArticle(article!!)

                })

//                lifecycleScope.launch(Dispatchers.IO) {
//
//                    if (articleViewModel.hasItem(article)) {
//                        db.removeArticle(article!!)
//                        lifecycleScope.launch(Dispatchers.Main) {Toast.makeText(context,"Removed from db",Toast.LENGTH_LONG) }
//
//
//                    } else {
//                        articleViewModel.addArticle(article)
//                        lifecycleScope.launch(Dispatchers.Main) { Toast.makeText(context,"Added to db",Toast.LENGTH_LONG) }
//                    }
//                }


                return true
            }

            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hi! Check this article out!\n" + article!!.url)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dao = FavoritesDatabase.getInstance(requireContext()).favArticleDao()
        val repository = ArticlesRepository(dao)

        val factory = ArticleViewModelFactory(repository)

        articleViewModel =
            ViewModelProvider(this, factory).get(ArticleViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.article_fragment, container, false)


//        itemPosition = savedInstanceState?.getString(ITEM_POSITION)?.toInt()
        article = this.arguments?.getParcelable("article")
//
        if (article == null) {
            Toast.makeText(context, "Makale açılırken bir hata oluştu", Toast.LENGTH_SHORT)
        } else {
            initArticle()
        }




        return binding.root
    }


    private fun initArticle() {
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
            if (article == null || article!!.url == null)
                sourceButton.isEnabled = false
            sourceButton.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("article_url", article!!.url)

                findNavController().navigate(R.id.action_articleFragment_to_webviewFragment, bundle)
            }
        }
    }


    companion object {
        fun newInstance() = ArticleFragment()
        private const val ITEM_POSITION = "item_position"
    }
}