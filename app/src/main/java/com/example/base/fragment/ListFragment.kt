package com.example.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.MainViewModel
import com.example.base.R
import com.example.base.adapter.ListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    val mainViewModel: MainViewModel by viewModel()

    lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val name = arguments?.getString("name")
        if (name != null) {
            observLiveData(name)
        }

    }

    private fun observLiveData(name: String) {
        mainViewModel.liveData.observe(this, Observer {
            list.adapter = ListAdapter(it)
            list.layoutManager = LinearLayoutManager(context)
        })
        mainViewModel.getList(name)
    }

    companion object {
        fun newInstance(name: String): ListFragment {
            val args = Bundle()
            args.putString("name", name)
            val fragment = ListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}