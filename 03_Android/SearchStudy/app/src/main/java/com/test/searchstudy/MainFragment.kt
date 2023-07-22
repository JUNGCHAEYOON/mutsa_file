package com.test.searchstudy

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.searchstudy.databinding.FragmentMainBinding
import com.test.searchstudy.databinding.RowMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
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

    var searchList = mutableListOf<String>()

    // naver 관련 data
    val clientID: String = "VROtkNTiWApas68axObh"
    val clientSecret: String = "GB78c6OLD4"

    // apiurl = "https://openapi.naver.com/v1/search/news?query=" + text +"&display=20"
    var apiURL = "https://openapi.naver.com/v1/search/news.json?query="

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            btnMainSearch.setOnClickListener {
                val searchString = etMain.text.toString()

                thread {
                    // 검색어를 이용해 url 주소 완성
                    val text: String = URLEncoder.encode(searchString, "UTF-8")
                    apiURL = apiURL + text + "&display=10"

                    // connection 객체 생성
                    val url = URL(apiURL)
                    val con: HttpURLConnection = url.openConnection() as HttpURLConnection

                    // get메서드 설정, id secret 코드 설정
                    con.requestMethod = "GET"
                    con.setRequestProperty("X-Naver-Client-Id", clientID)
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret)

                    val responseCode = con.responseCode
                    val br: BufferedReader

                    if (responseCode == 200) {
                        // 정상호출
                        br = BufferedReader(InputStreamReader(con.inputStream, "UTF-8"))
                    } else {
                        // error
                        br = BufferedReader(InputStreamReader(con.getErrorStream()));
                    }

                    val response = StringBuffer()
                    var inputLine: String?

                    while (br.readLine().also { inputLine = it } != null) {
                        response.append(inputLine)
                        response.append("\n")
                    }

                    br.close()

                    var data = response.toString()

                    val root = JSONObject(data)

                    val items = root.getJSONArray("items")

                    for (idx in 0 until items.length()) {
                        val obj = items.getJSONObject(idx)
                        val title = obj.getString("title")
                        searchList.add(title)
                    }

                    mainActivity.runOnUiThread {
                        fragmentMainBinding.rvMain.adapter?.notifyDataSetChanged()
                    }

                    Log.d("mainFragment", searchList.toString())

                }

            }

            rvMain.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        mainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

            return fragmentMainBinding.root
        }
    }
    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        // RecyclerView의 Row 하나가 가지고 있는 View들의 객체를 가지고 있는 Holder Class
        // 주 생성자로 ViewBinding 객체를 받는다.
        // 부모의 생성자에게 행 하나로 사용할 View를 전달한다.
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) :
            RecyclerView.ViewHolder(rowMainBinding.root),
            View.OnClickListener {

            var tvRowMain: TextView

            init {
                // 사용하고자 하는 View를 변수에 담아준다.
                tvRowMain = rowMainBinding.tvRowMain

            }

            override fun onClick(v: View?) {
                // ViewHolder를 통해 항목의 순서값을 가지고온다.
            }
        }

        // ViewHolder의 객체를 생성해서 반환한다.
        // 전체 행의 개수가 아닌 필요한 만큼만 행으로 사용할 View를 만들고 ViewHolder도 생성한다.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // ViewBinding
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            // ViewHolder
            val viewHolderClass = ViewHolderClass(rowMainBinding)

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

        // 전체 행의 개수를 반환한다.
        override fun getItemCount(): Int {
            return searchList.size
        }

        // viewHolder를 통해 View에 접근하여 View에 값을 설정한다.
        // 첫 번째 : ViewHolder 객체
        // 두 번째 : 특정 행의 순서값
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tvRowMain.text = searchList[position]
        }
    }
}
