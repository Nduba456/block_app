package com.myapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.model.Employee
import com.myapplication.model.EmployeeX

class EmployeeAdapter() : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.full_name)
        var employeeEmail: TextView = itemView.findViewById(R.id.email_address)
        val employeeTeam: TextView = itemView.findViewById(R.id.text_team)
        val employeeUuid: TextView = itemView.findViewById(R.id.text_uuid)
        val employeeType: TextView = itemView.findViewById(R.id.employee_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val employeeData = LayoutInflater.from(parent.context).inflate(R.layout.employee_list_item, parent, false)
        return EmployeeViewHolder(employeeData)
    }


    private val differCallBackStack = object : DiffUtil.ItemCallback<EmployeeX>() {
        override fun areItemsTheSame(oldItem: EmployeeX, newItem: EmployeeX): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: EmployeeX, newItem: EmployeeX): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBackStack)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employeeDetails = differ.currentList[position]

        Log.d("Adapter", "Binding data for: ${employeeDetails.full_name}")

        holder.employeeName.text = employeeDetails.full_name
            holder.employeeEmail.text = employeeDetails.email_address
            holder.employeeTeam.text = employeeDetails.team
            holder.employeeUuid.text = employeeDetails.uuid
            holder.employeeType.text = employeeDetails.employee_type


    }
}