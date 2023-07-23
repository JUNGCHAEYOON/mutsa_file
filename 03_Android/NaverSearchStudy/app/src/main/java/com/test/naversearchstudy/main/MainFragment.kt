package com.test.naversearchstudy.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.naversearchstudy.MainActivity
import com.test.naversearchstudy.R
import com.test.naversearchstudy.databinding.FragmentMainBinding
import com.test.naversearchstudy.databinding.RowMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity


    val newsTitleList = mutableListOf<String>()
    val newContentList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{
            tbMain.run{
                title = "네이버 뉴스 검색"
                inflateMenu(R.menu.menu_main)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 리셋 버튼 누르면 리싸이클러 비움
                        R.id.item_main_reset->{
                            newsTitleList.clear()
                            rvMain.adapter?.notifyDataSetChanged()
                        }
                    }
                    false
                }
            }

            etMainSearch.run{
                setOnEditorActionListener { v, actionId, event ->

                    // 검색 실행
                    thread{
                        // 검색어 url 주소 생성
                        val searchString = text.toString()
                        val searchText = URLEncoder.encode(searchString,"UTF-8")
                        val apiURL = MainActivity.URL + searchText + "&display=10"

                        // connection 객체 생성
                        val conURL = URL(apiURL)
                        val con : HttpURLConnection = conURL.openConnection() as HttpURLConnection

                        // Authentication
                        con.requestMethod = "GET"
                        con.setRequestProperty("X-Naver-Client-Id", MainActivity.CLIENT_ID)
                        con.setRequestProperty("X-Naver-Client-Secret", MainActivity.CLIENT_SECRET)

                        // response
                        val responseCode = con.responseCode
                        val br : BufferedReader

                        // 호출
                        if(responseCode == 200){
                            // 정상실행
                            br = BufferedReader(InputStreamReader(con.inputStream, "UTF-8"))
                        }else{
                            // error
                            br = BufferedReader(InputStreamReader(con.errorStream))
                        }

                        // 호출하여 불러온 데이타로 JSONObject 인 root 생성
                        val response = StringBuffer()
                        var inputLine : String?
                        while(br.readLine().also{inputLine = it} != null){
                            response.append(inputLine)
                            response.append("\n")
                        }
                        br.close()
                        var data = response.toString()
                        val root = JSONObject(data)


                        // items 분류
                        val items = root.getJSONArray("items")
                        for(idx in 0 until items.length()){
                            val obj = items.getJSONObject(idx)
                            val title = obj.getString("title")
                            val description = obj.getString("description")
                            newsTitleList.add(title)
                            newContentList.add(description)
                        }

                        // 리싸이클러뷰로 실행
                        mainActivity.runOnUiThread{
                            rvMain.adapter?.notifyDataSetChanged()
                        }

                    }

                    false
                }
            }

            rvMain.run{
                adapter = MainRecyclerAdapterClass()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }

        return fragmentMainBinding.root
    }

    inner class MainRecyclerAdapterClass : RecyclerView.Adapter<MainRecyclerAdapterClass.ViewHolderClass>() {
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root), View.OnClickListener{
            val tv : TextView

            init{
                tv = rowMainBinding.tvRowMain
            }

            // 개별 항목 클릭시 showFragment로 전환
            override fun onClick(v: View?) {
                MainActivity.newsTitle = newsTitleList[adapterPosition]
                MainActivity.newsContent = newContentList[adapterPosition]
                mainActivity.replaceFragment(MainActivity.SHOW,true,true)
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass((rowMainBinding))

            // 클릭 이벤트를 설정해준다.
            rowMainBinding.root.setOnClickListener(viewHolderClass)

            // 항목 View의 가로세로길이를 설정해준다(터치때문에...)
            val params = RecyclerView.LayoutParams(
                // 가로길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                // 세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowMainBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return newsTitleList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tv.text = newsTitleList[position]
        }
    }
}