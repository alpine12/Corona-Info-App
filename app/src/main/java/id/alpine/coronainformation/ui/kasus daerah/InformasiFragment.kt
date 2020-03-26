package id.alpine.coronainformation.ui.`kasus daerah`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.alpine.coronainformation.R

/**
 * A simple [Fragment] subclass.
 */
class InformasiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informasi, container, false)
    }

}
