package id.alpine.coronainformation.ui.kasus_nasional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.R
import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.network.Status
import kotlinx.android.synthetic.main.fragment_kasus.*
import kotlinx.android.synthetic.main.layout_content_kasus.*
import kotlinx.android.synthetic.main.layout_content_laporan_terkini.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class KasusFragment : Fragment() {

    private val viewModel: KasusViewModel by lazy {
        ViewModelProvider(this).get(KasusViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kasus, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val current = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-YYYY HH:mm", Locale("ID"))
        val formatted = formatter.format(current)
        tv_tanggal_sekarang.text = "$formatted WIB "
        initVIewModel()
    }

    private fun initVIewModel() {
        viewModel.getInfoNegara.observe(viewLifecycleOwner, Observer { data ->

            when (data.status) {
                Status.LOADING -> {
                    println("debug loading: Loading")
                }
                Status.SUCCESS -> {
                    showdata(data.data)
                }
                Status.ERROR -> {
                    println("debug error : ${data.msg}")
                    showToast("Koneksi Bermasalah}")
                }
            }
        })
        viewModel.setNegara("indonesia")
    }

    private fun showdata(data: ResponseCountries?) {
        updateTextView(data?.cases!!, tv_kasus_dilaporkan)
        updateTextView(data.active!!, tv_dalam_perawatan)
        updateTextView(data.recovered!!, tv_jumlah_sembuh)
        updateTextView(data.deaths!!, tv_jumlah_meninggal)
        updateTextView(data.todayCases!!, tv_kasus_hari_ini)
        updateTextView(data.todayDeaths!!, tv_meninggal_hari_ini)
    }

    private fun updateTextView(count: Int, view: TextView) {
        CoroutineScope(Default).launch {
            for (i in 0..count) {
                delay(1)
                withContext(Main) {
                    view.text = i.toString()
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }
}
