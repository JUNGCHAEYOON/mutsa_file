package com.test.mini02_boardproject02

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.mini02_boardproject02.databinding.FragmentPostWriteBinding
import java.io.File
import kotlin.math.max


class PostWriteFragment : Fragment() {

    lateinit var fragmentPostWriteBinding: FragmentPostWriteBinding
    lateinit var mainActivity: MainActivity

    // 게시판 종류
    var boardType = 0

    // 업로드할 이미지의 Uri
    var uploadUri:Uri? = null

    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 카메라 설정
        cameraLauncher = cameraSetting(fragmentPostWriteBinding.imageViewPostWrite)

        fragmentPostWriteBinding.run{

            toolbarPostWrite.run{
                title = "글 작성"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                }
                inflateMenu(R.menu.menu_post_write)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_post_write_album -> {

                        }
                        R.id.item_post_write_camera -> {
                            val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                            // 사진이 저장될 파일 이름
                            val fileName = "/temp_upload.jpg"
                            // 경로
                            val filePath = mainActivity.getExternalFilesDir(null).toString()
                            // 경로 + 파일이름
                            val picPath = "${filePath}/${fileName}"

                            // 사진이 저장될 경로를 관리할 Uri객체를 만들어준다.
                            // 업로드할 때 사용할 Uri이다.
                            val file = File(picPath)
                            uploadUri = FileProvider.getUriForFile(mainActivity,
                                "com.test.mini02_boardproject02.file_provider", file)

                            newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uploadUri)
                            cameraLauncher.launch(newIntent)
                        }
                        R.id.item_post_write_done -> {

                            // 입력한 내용을 가져온다.
                            val subject = textInputEditTextPostWriteSubject.text.toString()
                            val text = textInputEditTextPostWriteText.text.toString()

                            if(subject.isEmpty()){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteSubject)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if(text.isEmpty()){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteText)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if(boardType == 0){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("게시판 종류 선택 오류")
                                builder.setMessage("게시판 종류를 선택해주세요")
                                builder.setPositiveButton("확인", null)
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }


                            mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, null)
                        }
                    }
                    true
                }
            }

            // 게시판 종류 버튼
            buttonPostWriteType.run{
                setOnClickListener {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("게시판 종류")
                    builder.setItems(mainActivity.boardTypeList){ dialogInterface: DialogInterface, i: Int ->
                        boardType = i + 1
                        text = mainActivity.boardTypeList[i]
                    }
                    builder.setNegativeButton("취소", null)
                    builder.show()
                }
            }
        }

        return fragmentPostWriteBinding.root
    }

    // 카메라 관련 설정
    fun cameraSetting(previewImageView:ImageView) : ActivityResultLauncher<Intent>{
        // 사진 촬영을 위한 런처
        val cameraContract = ActivityResultContracts.StartActivityForResult()
        val cameraLauncher = registerForActivityResult(cameraContract){
            if(it?.resultCode == AppCompatActivity.RESULT_OK){
                // Uri를 이용해 이미지에 접근하여 Bitmap 객체로 생성한다.
                val bitmap = BitmapFactory.decodeFile(uploadUri?.path)

                // 이미지 크기 조정
                val ratio = 1024.0 / bitmap.width
                val targetHeight = (bitmap.height * ratio).toInt()
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)

                // 회전 각도값을 가져온다.
                val degree = getDegree(uploadUri!!)

                // 회전 이미지를 생성한다.
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())
                val bitmap3 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)
                previewImageView.setImageBitmap(bitmap3)
            }
        }

        return cameraLauncher
    }

    // 이미지 파일에 기록되어 있는 회전 정보를 가져온다.
    fun getDegree(uri:Uri) : Int{

        var exifInterface: ExifInterface? = null

        // 사진 파일로 부터 tag 정보를 관리하는 객체를 추출한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val photoUri = MediaStore.setRequireOriginal(uri)
            // 스트림을 추출한다.
            val inputStream = mainActivity.contentResolver.openInputStream(photoUri)
            // ExifInterface 정보를 읽엉돈다.
            exifInterface = ExifInterface(inputStream!!)
        } else {
            exifInterface = ExifInterface(uri.path!!)
        }

        var degree = 0
        if(exifInterface != null){
            // 각도 값을 가지고온다.
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

            when(orientation){
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        }
        return degree
    }

    // 앨범 관련 설정
}