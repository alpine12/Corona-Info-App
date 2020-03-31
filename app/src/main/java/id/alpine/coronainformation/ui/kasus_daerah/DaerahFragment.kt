package id.alpine.coronainformation.ui.kasus_daerah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.R
import id.alpine.coronainformation.helper.Debuger
import id.alpine.coronainformation.model.ResponseDaerah
import id.alpine.coronainformation.repository.RepositoryDaerah
import kotlinx.android.synthetic.main.fragment_daerah.*

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
            _viewState.observe(viewLifecycleOwner, Observer(this@DaerahFragment::handleState))

            getDaerah()
        }
    }

    private fun handleState(state: DaerahViewState) {
        state.let {
            it.data?.let { _data ->
                showData(_data)
            }
            it.error?.let { err ->
                Debuger().debug(" Daerah -> $err")
            }
        }
    }

    private fun showData(data: MutableList<ResponseDaerah>) {
        adapter.updateData(data)
    }
}
