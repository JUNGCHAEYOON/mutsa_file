package com.test.mini02_boardproject01

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.search.SearchView
import com.google.android.material.snackbar.Snackbar
import com.test.mini02_boardproject01.databinding.FragmentPostListBinding
import com.test.mini02_boardproject01.databinding.RowPostListBinding
import com.test.mini02_boardproject01.vm.PostViewModel

class PostListFragment : Fragment() {
    val sampleData = arrayOf(
        "게시글1", "게시글2", "게시글3", "게시글4", "게시글5", "게시글6", "게시글7", "게시글8", "게시글9", "게시글10",
        "게시글11", "게시글12", "게시글13", "게시글14", "게시글15", "게시글16", "게시글17", "게시글18", "게시글19", "게시글20"
    )

    lateinit var fragmentPostListBinding: FragmentPostListBinding
    lateinit var mainActivity: MainActivity
    lateinit var boardMainFragment: BoardMainFragment

    lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        postViewModel = ViewModelProvider(mainActivity)[PostViewModel::class.java]
        postViewModel.run {
            postDataList.observe(mainActivity) {
                fragmentPostListBinding.recyclerViewPostList.adapter?.notifyDataSetChanged()
                fragmentPostListBinding.recyclerViewPostListResult.adapter?.notifyDataSetChanged()
            }
        }

        fragmentPostListBinding.run {
            searchBarPostList.run {
                hint = "검색어를 입력해주세요"
                inflateMenu(R.menu.menu_post_list)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_post_list_add -> {
                            mainActivity.replaceFragment(MainActivity.POST_WRITE_FRAGMENT, true, null)
                        }
                    }

                    true
                }
            }

            searchViewPostList.run {
                hint = "검색어를 입력해주세요"

                addTransitionListener { searchView, previousState, newState ->
                    // 서치바를 눌러 서치뷰가 보일때
                    if (newState == SearchView.TransitionState.SHOWING) {
                        // Snackbar.make(fragmentPostListBinding.root, "Showing", Snackbar.LENGTH_SHORT).show()
                        postViewModel.resetPostList()
                    }
                    // 서치뷰의 백 버튼을 눌러 서치뷰가 사라지고 서치바가 보일때
                    else if (newState == SearchView.TransitionState.HIDING) {
                        // Snackbar.make(fragmentPostListBinding.root, "Hiding", Snackbar.LENGTH_SHORT).show()
                        postViewModel.getPostAll(arguments?.getLong("postType")!!)
                    }
                }

                editText.setOnEditorActionListener { v, actionId, event ->
                    // Snackbar.make(fragmentPostListBinding.root, text!!, Snackbar.LENGTH_SHORT).show()
                    postViewModel.getSearchPostList(arguments?.getLong("postType")!!, text.toString())
                    true
                }
            }

            recyclerViewPostList.run {
                adapter = PostListRecyclerViewAdpater()
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL))
            }

            recyclerViewPostListResult.run {
                adapter = PostListRecyclerViewAdpater()
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL))
            }

            // 게시판 타입 번호를 전달하여 게시글 정보를 가져온다.
            postViewModel.getPostAll(arguments?.getLong("postType")!!)
        }

        return fragmentPostListBinding.root
    }

    // 모든 게시물을 보여주는 리사이클러뷰의 어댑터
    inner class PostListRecyclerViewAdpater : RecyclerView.Adapter<PostListRecyclerViewAdpater.PostListViewHolder>() {
        inner class PostListViewHolder(rowPostListBinding: RowPostListBinding) :
            RecyclerView.ViewHolder(rowPostListBinding.root) {
            var rowPostListSubject: TextView
            var rowPostListNickName: TextView

            init {
                rowPostListSubject = rowPostListBinding.rowPostListSubject
                rowPostListNickName = rowPostListBinding.rowPostListNickName

                rowPostListBinding.root.setOnClickListener {
                    // 선택한 글의 postIdx를 가져온다.
                    val readPostIdx = postViewModel.postDataList.value?.get(adapterPosition)?.postIdx
                    val newBundle = Bundle()
                    newBundle.putLong("readPostIdx", readPostIdx!!)
                    mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, newBundle)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
            val rowPostListBinding = RowPostListBinding.inflate(layoutInflater)
            val postListViewHolder = PostListViewHolder(rowPostListBinding)

            rowPostListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return postListViewHolder
        }

        override fun getItemCount(): Int {
            return postViewModel.postDataList.value?.size!!
        }

        override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
            holder.rowPostListSubject.text = postViewModel.postDataList.value?.get(position)?.postSubject
            // holder.rowPostListNickName.text = postViewModel.postWriterNicknameList.value?.get(position)
        }
    }

    // 검색 결과 게시글 목록을 보여주는 리사이클러뷰의 어댑터
    inner class PostListSearchRecyclerViewAdpater :
        RecyclerView.Adapter<PostListSearchRecyclerViewAdpater.PostListSearchViewHolder>() {
        inner class PostListSearchViewHolder(rowPostListBinding: RowPostListBinding) :
            RecyclerView.ViewHolder(rowPostListBinding.root) {
            var rowPostListSubject: TextView
            var rowPostListNickName: TextView

            init {
                rowPostListSubject = rowPostListBinding.rowPostListSubject
                rowPostListNickName = rowPostListBinding.rowPostListNickName

                rowPostListBinding.root.setOnClickListener {
                    // 선택한 글의 postIdx를 가져온다.
                    val readPostIdx = postViewModel.postDataList.value?.get(adapterPosition)?.postIdx
                    val newBundle = Bundle()
                    newBundle.putLong("readPostIdx", readPostIdx!!)
                    mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, newBundle)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListSearchViewHolder {
            val rowPostListBinding = RowPostListBinding.inflate(layoutInflater)
            val postListSearchViewHolder = PostListSearchViewHolder(rowPostListBinding)

            rowPostListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return postListSearchViewHolder
        }

        override fun getItemCount(): Int {
            return postViewModel.postDataList.value?.size!!
        }

        override fun onBindViewHolder(holder: PostListSearchViewHolder, position: Int) {
            holder.rowPostListSubject.text = postViewModel.postDataList.value?.get(position)?.postSubject
            // holder.rowPostListNickName.text = postViewModel.postWriterNicknameList.value?.get(position)
        }
    }
}