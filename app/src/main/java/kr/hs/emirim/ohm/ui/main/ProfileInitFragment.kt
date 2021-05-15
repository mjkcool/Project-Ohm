//package kr.hs.emirim.ohm.ui.main
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kr.hs.emirim.ohm.R
//
//class ProfileInitFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = ProfileInitFragment()
//    }
//
//    private lateinit var viewModel: MainViewModel
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View {
//        return inflater.inflate(R.layout.first_nickname_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}