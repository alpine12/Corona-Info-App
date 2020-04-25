package id.alpine.coronainformation.ui.kasus_nasional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.R
import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.repository.RepositoryNegara
import kotlinx.android.synthetic.main.fragment_negara.*
import kotlinx.android.synthetic.main.layout_content_kasus.*
import kotlinx.android.synthetic.main.layout_content_laporan_terkini.*
import kotlinx.android.synthetic.main.layout_no_connection.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class NasionalFragment : Fragment() {

    private lateinit var viewModel: NasionalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_negara, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTanggal()
        initVIewModel()
    }

    private fun initVIewModel() {
        val factory = NaisonalViewModelFactory(RepositoryNegara.instance)
        viewModel = ViewModelProvider(this, factory).get(NasionalViewModel::class.java).apply {
            viewState.observe(
                viewLifecycleOwner, androidx.lifecycle.Observer(this@NasionalFragment::handleState)
            )
            sr_negara.setOnRefreshListener {
                showTanggal()
                refresh("indonesia")
            }
        }
    }

    private fun handleState(state: NasionalViewState?) {
        state?.let {
            handleLoading(state.loading)
            state.data?.let { data -> showdata(data) }
            state.error?.let { handleError(it.message) }
            state.message?.let { showToast(it) }
        }
    }

    private fun showdata(data: ResponseCountries) {

        container_negara.visibility = View.VISIBLE
        container_error.visibility = View.GONE

        updateTextView(data.cases!!, tv_kasus_dilaporkan)
        updateTextView(data.active!!, tv_dalam_perawatan)
        updateTextView(data.recovered!!, tv_jumlah_sembuh)
        updateTextView(data.deaths!!, tv_jumlah_meninggal)
        updateTextView(data.todayCases!!, tv_kasus_hari_ini)
        updateTextView(data.todayDeaths!!, tv_meninggal_hari_ini)
    }

    private fun updateTextView(count: Int, view: TextView) {
        view.text = count.toString()
    }

    private fun handleLoading(state: Boolean) {
        sr_negara.isRefreshing = state
    }

    private fun handleError(text: String?) {
        container_negara.visibility = View.GONE
        container_error.visibility = View.VISIBLE
        tv_no_connection.text = text
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    private fun showTanggal() {
        val current = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-YYYY HH:mm", Locale("ID"))
        val formatted = formatter.format(current)
        tv_tanggal_sekarang.text = "$formatted WIB "
    }
}
