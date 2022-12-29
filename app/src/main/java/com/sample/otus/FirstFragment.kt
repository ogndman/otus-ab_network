package com.sample.otus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sample.otus.databinding.FragmentFirstBinding
import com.sample.otus.network.NetworkServiceOkHttpAsync
import com.sample.otus.network.NetworkServiceOkHttpAsyncWithInterceptor
import com.sample.otus.network.NetworkServiceOkHttpBlocking
import com.sample.otus.network.NetworkServiceRetrofitCall
import com.sample.otus.network.NetworkServiceRetrofitCallMoshi
import com.sample.otus.network.NetworkServiceRetrofitCallMoshiConverter
import com.sample.otus.network.NetworkServiceRetrofitCoroutinesMoshiAdapter
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val okHttpBlocking = NetworkServiceOkHttpBlocking()
    private val okHttpAsync = NetworkServiceOkHttpAsync()
    private val okHttpAsyncWithInterceptor = NetworkServiceOkHttpAsyncWithInterceptor()
    private val retrofitCall = NetworkServiceRetrofitCall()
    private val retrofitCallMoshi = NetworkServiceRetrofitCallMoshi()
    private val retrofitCallMoshiConverter = NetworkServiceRetrofitCallMoshiConverter()
    private val retrofitCoroutinesMoshiConverter = NetworkServiceRetrofitCoroutinesMoshiAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOkhttpBlocking.setOnClickListener {
            okHttpBlocking.request { response ->
                binding.textviewResponseData.text = response
            }
        }
        binding.buttonOkhttpAsync.setOnClickListener {
            okHttpAsync.request { response ->
                requireActivity().runOnUiThread {
                    binding.textviewResponseData.text = response
                }
            }
        }
        binding.buttonOkhttpAsyncWithInterceptor.setOnClickListener {
            okHttpAsyncWithInterceptor.request { response ->
                requireActivity().runOnUiThread {
                    binding.textviewResponseData.text = response
                }
            }
        }
        binding.buttonRetrofitCall.setOnClickListener {
            retrofitCall.request { response ->
                binding.textviewResponseData.text = response
            }
        }
        binding.buttonRetrofitCallMoshi.setOnClickListener {
            retrofitCallMoshi.request { response ->
                binding.textviewResponseData.text = response.toString()
            }
        }
        binding.buttonRetrofitCallMoshiConverter.setOnClickListener {
            retrofitCallMoshiConverter.request { response ->
                binding.textviewResponseData.text = response.toString()
            }
        }
        binding.buttonRetrofitCoroutines.setOnClickListener {
            lifecycleScope.launch {
                val currencyData = retrofitCoroutinesMoshiConverter.request()
                binding.textviewResponseData.text = currencyData.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}