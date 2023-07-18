package com.test.basicstudyex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.basicstudyex01.databinding.FragmentTest1Binding
import com.test.basicstudyex01.databinding.FragmentTest2Binding


class Test2Fragment : Fragment() {

    lateinit var fragmentTest2Binding: FragmentTest2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentTest2Binding = FragmentTest2Binding.inflate(inflater)

        fragmentTest2Binding.run{
            button2.setOnClickListener {
                val a3 = editTextNumber3.text.toString().toInt()
                val a4 = editTextNumber4.text.toString().toInt()

                textView2.text = "${a3 * a4}"
            }
        }

        return fragmentTest2Binding.root
    }

}