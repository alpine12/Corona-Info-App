package id.alpine.coronainformation.ui.banyuwangi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.R
import id.alpine.coronainformation.model.Data
import id.alpine.coronainformation.repository.RepositoryBanyuwangi
import kotlinx.android.synthetic.main.fragment_banyuwangi.*
import kotlinx.android.synthetic.main.layout_content_odp.*
import kotlinx.android.synthetic.main.layout_content_pdp.*
import kotlinx.android.synthetic.main.layout_content_positive.*
import kotlinx.android.synthetic.main.layout_no_connection.*

/**
 * A simple [Fragment] subclass.
 */
class BanyuwangiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banyuwangi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = BanyuwangiVIewModelFactory(RepositoryBanyuwangi.instance)
        val vm = ViewModelProvider(this, factory).get(BanyuwangiViewModel::class.java).apply {
            viewState.observe(viewLifecycleOwner, Observer(this@BanyuwangiFragment::handleState))
            srl_banyuwangi.setOnRefreshListener { refresh() }
        }
    }

    private fun handleState(state: BanyuwangiViewState) {
        state.let {
            handleLoading(it.loading)
            it.data?.let { _data ->
                showData(_data)
            }
            it.error?.let {
                handleError(it.message.toString())
            }
            it.message?.let {
                showToast(it)
            }
        }
    }

    private fun showData(data: Data) {
        tv_total_odp.text = data.totalOdp
        tv_proses_pemantauan.text = data.odpProses
        tv_selesai_pantau.text = data.odpSelesai
        tv_total_pdp.text = data.totalPdp
        tv_masih_dirawat.text = data.pdpRawat
        tv_sudah_sembuh.text = data.pdpSembuh
        tv_positif.text = data.totalCovid
        tv_dirawat.text = data.covidRawat
        tv_sembuh.text = data.covidSembuh
        tv_meninggal.text = data.covidMeninggal
        tv_last_update.text = " Terakhir update data ${data.lastUpdated}"

        container_banyuwangi.visibility = View.VISIBLE
        container_error.visibility = View.GONE
    }

    private fun handleLoading(state: Boolean) {
        srl_banyuwangi.isRefreshing = state
    }

    private fun handleError(text: String?) {
        container_banyuwangi.visibility = View.GONE
        container_error.visibility = View.VISIBLE
        tv_no_connection.text = text
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }
}
