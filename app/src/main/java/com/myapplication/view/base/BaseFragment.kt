package com.myapplication.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.myapplication.database.AppDao
import com.myapplication.database.Database
import com.myapplication.service.RetrofitInstance
import com.myapplication.service.repository.BaseRepository
import com.myapplication.viewModel.ViewModelFactory

abstract class BaseFragment<VM: ViewModel, Binding: ViewBinding, Repo: BaseRepository>: Fragment() {

    protected lateinit var binding: Binding
    protected lateinit var viewModel: VM
    protected lateinit var appDao: AppDao
    protected val retrofitInstance = RetrofitInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        appDao = Database.invoke(requireContext()).appDao()
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
        return binding.root

    }

    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
    abstract fun getFragmentRepository(): Repo
}