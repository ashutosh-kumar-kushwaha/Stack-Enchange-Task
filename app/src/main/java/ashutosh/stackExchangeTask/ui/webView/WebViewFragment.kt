package ashutosh.stackExchangeTask.ui.webView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ashutosh.stackExchangeTask.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)

        if(arguments?.getString("link")!=null){
            val link = arguments?.getString("link")
            binding.webView.loadUrl(link!!)

            binding.webView.settings.javaScriptEnabled = true

            binding.webView.webViewClient = WebViewClient()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}