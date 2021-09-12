package com.example.retrofit_hilt.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.retrofit_hilt.R
import com.example.retrofit_hilt.model.ResultData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //so Hilt aahi badhi dependency available karavi aapshe..
class MainFragment : Fragment() {
    companion object{
        fun newInstance() = MainFragment()
    }

    private val mainFragment by viewModels<MainViewModel>() //provided vm in lazy manned ..and didn't even need to use Factory as vm has useCase as a dependency

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val repositoriesList = mainFragment.getRepositoriesList()   //this var contains lv ..coming from vm
        repositoriesList.observe(viewLifecycleOwner, Observer { resultData ->
            //we will handle sealed class status
            when(resultData){ //checking which type of resultData we recieved ..success or failed
                is ResultData.Loading -> {  //if Loading type no emit thayo hoy

                }
                is ResultData.Success -> {
                    val gitHubDataModel = resultData.data   //ArrayList malshe (Check structure of Model)
                    Log.d("YETANOTHERDEV", "onActivityCreated: $gitHubDataModel")
                    Toast.makeText(requireContext(), gitHubDataModel.toString(), Toast.LENGTH_SHORT).show()
                }
                is ResultData.Failed -> {

                }
                is ResultData.Exception -> {

                }
            }
        })
    }
}