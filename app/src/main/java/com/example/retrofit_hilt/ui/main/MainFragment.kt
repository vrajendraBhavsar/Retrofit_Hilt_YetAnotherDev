package com.example.retrofit_hilt.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_hilt.R
import com.example.retrofit_hilt.adapter.DataAdapter
import com.example.retrofit_hilt.databinding.FragmentMainBinding
import com.example.retrofit_hilt.model.GitHubDataModel
import com.example.retrofit_hilt.model.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint // so Hilt aahi badhi dependency available karavi aapshe..
class MainFragment : Fragment() {
    val dataList: MutableList<GitHubDataModel> = ArrayList()    //List
    //for pagination
    var page = 1
    var isLoading = false
    val limit = 20  //to limit the data per page
    //while scrolling we need to adjust adapter and layoutManager
    lateinit var adapter: DataAdapter
    lateinit var layoutManager: LinearLayoutManager

    lateinit var binding: FragmentMainBinding
    companion object {
        fun newInstance() = MainFragment()
    }

    @DelicateCoroutinesApi
    private val mainFragment by viewModels<MainViewModel>() // provided vm in lazy manned ..and didn't even need to use Factory as vm has useCase as a dependency

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutManager = LinearLayoutManager(requireContext())
        binding.dataListRecyclerView.layoutManager = layoutManager

        //to listen scrolling of recyclerView..onScrollListener
        binding.dataListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy>0){  //means scrollview scrolled vertically ..6:38 - https://www.youtube.com/watch?v=FELCnTNpS_o

                }
            }
        })

        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    //for mocking network communication
    @SuppressLint("NotifyDataSetChanged")
    fun getPage(gitHubDataModel: GitHubDataModel?) {
        //here we will populate list
        binding.progressBar.visibility = View.VISIBLE   //while loading data..its visible
        val start = ((page) * limit) + 1
        val end = (page) * limit

        for (i in start..end){
            gitHubDataModel?.let { dataList.add(it) }
        }
        Handler().postDelayed({
            if (::adapter.isInitialized) {  //checking if adapter is initialized or not
                adapter.notifyDataSetChanged()
            } else {
                adapter = DataAdapter(requireContext())
                binding.dataListRecyclerView.adapter = adapter
            }
            isLoading = false
            binding.progressBar.visibility = View.GONE
        }, 5000)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
    }

    @DelicateCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val repositoriesList = mainFragment.getRepositoriesList() // this var contains lv ..coming from vm
//        repositoriesList.observe(
        mainFragment.getRepositoriesList(page = page)
            mainFragment.githubDataModelLiveData.observe(
            viewLifecycleOwner,
            Observer { resultData ->
                // we will handle sealed class status
                when (resultData) { // checking which type of resultData we recieved ..success or failed
                    is ResultData.Loading -> { // if Loading type no emit thayo hoy
                    }
                    is ResultData.Success -> {
                        val gitHubDataModel: GitHubDataModel? = resultData.data // ArrayList malshe (Check structure of Model)
                        getPage(gitHubDataModel)
//                        binding.tvData.text = gitHubDataModel.toString()
                        Log.d("YETANOTHERDEV", "onActivityCreated: ${gitHubDataModel?.size}")
                        Toast.makeText(requireContext(), gitHubDataModel.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is ResultData.Failed -> {
                    }
                    is ResultData.Exception -> {
                    }
                }
            }
        )
    }
}
