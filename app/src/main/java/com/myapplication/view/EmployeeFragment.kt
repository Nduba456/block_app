package com.myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.databinding.FragmentEmployeeBinding
import com.myapplication.service.Api
import com.myapplication.service.repository.Repository
import com.myapplication.utils.Resource
import com.myapplication.view.adapter.EmployeeAdapter
import com.myapplication.view.base.BaseFragment
import com.myapplication.viewModel.EmployeeViewModel
import com.myapplication.viewModel.ViewModelFactory
import javax.inject.Inject


class EmployeeFragment : BaseFragment<EmployeeViewModel, FragmentEmployeeBinding, Repository>() {

    private lateinit var employeeBinding: FragmentEmployeeBinding
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun getViewModel(): Class<EmployeeViewModel> {
       return EmployeeViewModel::class.java
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?
    ): FragmentEmployeeBinding {
        return FragmentEmployeeBinding.inflate(inflater, container, false)
    }

    override fun getFragmentRepository(): Repository {
        return Repository(retrofitInstance.buildApi(Api::class.java), appDao, requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        employeeBinding = getFragmentBinding(layoutInflater, view as ViewGroup?)

        setUpRecyclerView()
        viewModel.employeeData.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resource.Success -> {
                    val employeeList = it.value.employees
                    if (employeeList.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "No employees available", Toast.LENGTH_SHORT).show()
                    } else {
                        employeeAdapter.differ.submitList(employeeList)
                        Log.d("Employee Fragment", "$employeeList")
                        Toast.makeText(requireContext(), "Employees loaded", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading employees", Toast.LENGTH_SHORT).show()


                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()

                }

            }
        })

        viewModel.getEmployee()
    }
    private fun setUpRecyclerView() {
        employeeAdapter = EmployeeAdapter()
        employeeBinding.employeeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = employeeAdapter
        }
        Log.d("RecyclerView", "RecyclerView is set up")
    }

}