package com.test.mini02_boardproject01

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.test.mini02_boardproject01.databinding.FragmentPostReadBinding
import com.test.mini02_boardproject01.repository.PostRepository
import com.test.mini02_boardproject01.vm.PostViewModel

class PostReadFragment : Fragment() {
    lateinit var fragmentPostReadBinding: FragmentPostReadBinding
    lateinit var mainActivity: MainActivity

    lateinit var postViewModel: PostViewModel

    // 게시글 인덱스 번호를 받는다.
    var readPostIdx = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPostReadBinding = FragmentPostReadBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        postViewModel = ViewModelProvider(mainActivity)[PostViewModel::class.java]
        postViewModel.run {
            postSubject.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadSubject.setText(it)
            }
            postText.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadText.setText(it)
            }
            postNickname.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadNickname.setText(it)
            }
            postWriteDate.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadDate.setText(it)
            }
            postFileName.observe(mainActivity) {
                if (it == "None") {
                    fragmentPostReadBinding.imageViewPostRead.visibility = View.GONE
                }
            }
            postImage.observe(mainActivity) {
                fragmentPostReadBinding.imageViewPostRead.visibility = View.VISIBLE
                fragmentPostReadBinding.imageViewPostRead.setImageBitmap(it)
            }
        }

        fragmentPostReadBinding.run {
            toolbarPostRead.run {
                title = "게시글"

                inflateMenu(R.menu.menu_post_read)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_post_read_modify -> {
//                            if(textInputEditTextPostReadSubject.isEnabled == false) {
//                                textInputEditTextPostReadSubject.isEnabled = true
//                                textInputEditTextPostReadText.isEnabled = true
//                            } else {
//                                textInputEditTextPostReadSubject.isEnabled = false
//                                textInputEditTextPostReadText.isEnabled = false
//                            }
                            val newBundle = Bundle()
                            newBundle.putLong("readPostIdx", readPostIdx)
                            mainActivity.replaceFragment(MainActivity.POST_MODIFY_FRAGMENT, true, newBundle)
                        }

                        R.id.item_post_read_delete -> {
                            // 글을 삭제한다.
                            PostRepository.removePost(readPostIdx) {
                                // 이미지가 있을때와 없을때로 분기
                                if (postViewModel.postFileName.value != "None") {
                                    PostRepository.removeImage(postViewModel.postFileName.value!!) {
                                        mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                                        mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                                    }
                                } else {
                                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                                    mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                                }
                            }
                        }
                    }

                    true
                }

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                    mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                }
            }

            textInputEditTextPostReadSubject.run {
                setTextColor(Color.BLACK)
            }

            textInputEditTextPostReadText.run {
                setTextColor(Color.BLACK)
            }

            textInputEditTextPostReadNickname.run {
                setTextColor(Color.BLACK)
            }

            textInputEditTextPostReadDate.run {
                setTextColor(Color.BLACK)
            }

            // 이미지를 받아오기 전까지 보여줄 이미지를 설정한다.
            imageViewPostRead.setImageResource(R.mipmap.ic_launcher)
        }

        // 게시글 인덱스 번호를 받는다.
        readPostIdx = arguments?.getLong("readPostIdx")!!
        // 게시글 정보를 가져온다.
        postViewModel.setPostReadData(readPostIdx.toDouble())

        return fragmentPostReadBinding.root
    }
}