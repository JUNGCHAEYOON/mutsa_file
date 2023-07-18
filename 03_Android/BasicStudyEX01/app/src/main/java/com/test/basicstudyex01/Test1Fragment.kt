package com.test.basicstudyex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.basicstudyex01.databinding.FragmentTest1Binding


class Test1Fragment : Fragment() {

    lateinit var fragmentTest1Binding: FragmentTest1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTest1Binding = FragmentTest1Binding.inflate(inflater)

        fragmentTest1Binding.run{
            button.setOnClickListener {
                val a1 = editTextNumber.text.toString().toInt()
                val a2 = editTextNumber2.text.toString().toInt()

                textView.text = "${a1 + a2}"
            }
        }

        return fragmentTest1Binding.root
    }

}