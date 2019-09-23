package ru.diitcenter.optovik.presentation.screens.search.ui


import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_search.*
import ru.diitcenter.optovik.data.global.models.Product
import ru.diitcenter.optovik.presentation.global.utils.hideKeyboard
import ru.diitcenter.optovik.presentation.screens.search.mvp.SearchPresenter
import ru.example.optovik.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class SearchFragment : ru.diitcenter.optovik.presentation.global.BaseFragment(),
    ru.diitcenter.optovik.presentation.screens.search.mvp.SearchView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var basket: ru.diitcenter.optovik.data.basketholder.BasketHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    private lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        ru.diitcenter.optovik.App.appComponent.searchComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_arrow_search.setOnClickListener { presenter.back()
        hideKeyboard()}
        initViews()
        updateBasketButtonSearch()
        update_search.setOnClickListener { presenter.search("") }
        clear.setOnClickListener { input_search.setText("") }

        input_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.search(input_search.text.toString())
            }

        })
        emptyBasketCheck()
    }

    override fun emptyBasketCheck() {
        if(basket.items.isEmpty())
            button_basket_search.visibility = View.GONE
        else
            button_basket_search.visibility = View.VISIBLE
    }


    private fun initViews() {
        search_recycler.run {
            layoutManager = LinearLayoutManager(search_recycler.context)
            addItemDecoration(
                DividerItemDecoration(search_recycler.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun showProgress(progress: Boolean) {
        progressBar_Search.visibility = if (progress) View.VISIBLE else View.GONE
    }

    override fun showFoundProducts(products: List<Product>) {
        val adapter = SearchAdapter(
            products = products,
            clickListenerPlus = {
        //        basket.addProduct(it)
//                updateBasketButton()
            },
            clickListenerMinus = {
      //          basket.deleteProduct(it)
//                updateBasketButton()
            },
            basket = basket,
            searchWord = input_search.text.toString()

        )
        search_recycler.adapter = adapter
        adapter.setOnCatalogClickListener {
            presenter.goToProductCard(it)
        }
        not_found_container.visibility = View.GONE
    }

    override fun updateBasketButtonSearch() {
        var priceAll: Int = 0
        basket.items.forEach { item ->
            priceAll += (item.product.price * item.quantity)
        }
        val haveItem = basket.items.filter {
            it.product.isEstimatedPrice == true
        }.firstOrNull()
        if (haveItem == null)
            isEstimatedPrise_search.visibility = View.GONE
        else
            isEstimatedPrise_search.visibility = View.VISIBLE

        price_on_button_search.setText("$priceAll")
        count_on_button_search.setText("${basket.items.size}")
    }

    override fun showError() {
        not_found_container.visibility = View.VISIBLE
    }

    override fun visiblSearchList() {
        search_container.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        updateBasketButtonSearch()
       // adapterUpdate()
        emptyBasketCheck()
    }

    override fun onBackPressed() { presenter.back()
        hideKeyboard()}

}
