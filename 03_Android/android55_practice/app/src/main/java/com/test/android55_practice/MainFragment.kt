package com.test.android55_practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.android55_practice.databinding.FragmentMainBinding
import com.test.android55_practice.databinding.RowMainBinding
import org.w3c.dom.Text


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding : FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{

        }

        return fragmentMainBinding.root
    }

    // 리싸이클러 어댑터
    inner class MainRecyclerAdapterClass : RecyclerView.Adapter<MainRecyclerAdapterClass.MainViewHolderClass>(){
        inner class MainViewHolderClass(rowMainBinding : RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            var textViewRowMainType : TextView
            var textViewRowMainName : TextView

            init {
                textViewRowMainType = rowMainBinding.textViewRowMainType
                textViewRowMainName = rowMainBinding.textViewRowMainName

                rowMainBinding.root.run{
                    setOnClickListener {

                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val mainViewHolderClass = MainViewHolderClass(rowMainBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowMainBinding.root.layoutParams = params
            return mainViewHolderClass
        }

        override fun getItemCount(): Int {
            return mainActivity.animalList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.textViewRowMainType.text = mainActivity.animalList[position].type
            holder.textViewRowMainName.text = mainActivity.animalList[position].name
        }
    }
}