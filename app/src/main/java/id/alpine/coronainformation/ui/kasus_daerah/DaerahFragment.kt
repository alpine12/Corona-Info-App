package id.alpine.coronainformation.ui.kasus_daerah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.R
import id.alpine.coronainformation.model.ResponseDaerah
import id.alpine.coronainformation.repository.RepositoryDaerah
import kotlinx.android.synthetic.main.fragment_daerah.*
import kotlinx.android.synthetic.main.layout_no_connection.*

/**
 * A simple [Fragment] subclass.
 */
class DaerahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daerah, container, false)
    }

    private lateinit var adapter: DaerahAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DaerahAdapter()
        rv_daerah.adapter = adapter

        val factory = DaerahViewModelFactory(RepositoryDaerah.instance)
        val vm = ViewModelProvider(this, factory).get(DaerahVIewModel::class.java).apply {
            viewState.observe(viewLifecycleOwner, Observer(this@DaerahFragment::handleState))
            srl_daerah.setOnRefreshListener { refresh() }
        }
    }

    private fun handleState(state: DaerahViewState) {
        state.let {
            handleLoading(it.loading)
            it.data?.let { _data ->
                showData(_data)
            }
            it.error?.let { err ->
                handleError(err.toString())
            }
            it.message?.let {
                showToast(it)
            }
        }
    }

    private fun showData(data: MutableList<ResponseDaerah>) {
        adapter.updateData(data)
        container_daerah.visibility = View.VISIBLE
        container_error.visibility = View.GONE
    }

    private fun handleLoading(state: Boolean) {
        srl_daerah.isRefreshing = state
    }

    private fun handleError(text: String?) {
        container_daerah.visibility = View.GONE
        container_error.visibility = View.VISIBLE
        tv_no_connection.text = text
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}
