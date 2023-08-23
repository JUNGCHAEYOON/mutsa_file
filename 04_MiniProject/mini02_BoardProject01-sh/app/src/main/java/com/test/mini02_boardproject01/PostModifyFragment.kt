package com.test.mini02_boardproject01

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.mini02_boardproject01.databinding.FragmentPostModifyBinding
import com.test.mini02_boardproject01.repository.PostRepository
import com.test.mini02_boardproject01.vm.PostViewModel
import java.io.File

class PostModifyFragment : Fragment() {

    lateinit var fragmentPostModifyBinding: FragmentPostModifyBinding
    lateinit var mainActivity: MainActivity

    lateinit var postViewModel: PostViewModel

    // 게시글 번호
    var readPostIdx = 0L

    // 업로드할 이미지의 Uri
    var uploadUri: Uri? = null

    lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    // 새로운 이미지를 설정한 적이 있는지
    var isSelectNewImage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPostModifyBinding = FragmentPostModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        readPostIdx = arguments?.getLong("readPostIdx")!!

        // 카메라 설정
        cameraLauncher = cameraSetting(fragmentPostModifyBinding.imageViewPostModify)
        // 앨범 설정
        albumLauncher = albumSetting(fragmentPostModifyBinding.imageViewPostModify)

        postViewModel = ViewModelProvider(mainActivity)[PostViewModel::class.java]
        postViewModel.run {
            postSubject.observe(mainActivity) {
                fragmentPostModifyBinding.textInputEditTextPostModifySubject.setText(it)
            }
            postText.observe(mainActivity) {
                fragmentPostModifyBinding.textInputEditTextPostModifyText.setText(it)
            }
            postImage.observe(mainActivity) {
                fragmentPostModifyBinding.imageViewPostModify.setImageBitmap(it)
            }
        }

        fragmentPostModifyBinding.run {
            toolbarPostModify.run {
                title = "글 수정"

                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_MODIFY_FRAGMENT)
                }

                inflateMenu(R.menu.menu_post_modify)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_post_modify_camera -> {
                            val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                            // 촬영될 사진이 저장될 파일 이름
                            val fileName = "/temp_upload.jpg"
                            // 경로
                            val filePath = mainActivity.getExternalFilesDir(null).toString()
                            // 경로 + 파일이름
                            val picPath = "${filePath}/${fileName}"

                            // 사진이 저장될 경로를 관리할 Uri객체를 만들어준다.
                            // 업로드할 때 사용할 Uri이다.
                            val file = File(picPath)
                            uploadUri = FileProvider.getUriForFile(
                                mainActivity,
                                "com.test.mini02_boardproject01.file_provider", file
                            )

                            newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uploadUri)
                            cameraLauncher.launch(newIntent)
                        }

                        R.id.item_post_modify_album -> {
                            // 앨범에서 사진을 선택할 수 있는 Activity를 실행한다.
                            val newIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            // 실행할 액티비티의 마임타입 설정(이미지로 설정해준다)
                            newIntent.setType("image/*")
                            // 선택할 파일의 타입을 지정(안드로이드  OS가 이미지에 대한 사전 작업을 할 수 있도록)
                            val mimeType = arrayOf("image/*")
                            newIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                            // 액티비티를 실행한다.
                            albumLauncher.launch(newIntent)
                        }

                        R.id.item_post_modify_done -> {
                            // 입력한 내용을 가져온다.
                            val subject = textInputEditTextPostModifySubject.text.toString()
                            val text = textInputEditTextPostModifyText.text.toString()

                            if (subject.isEmpty()) {
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostModifySubject)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if (text.isEmpty()) {
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용을 입력해주세요")
                                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostModifyText)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            val fileName = if (isSelectNewImage == false) {
                                "None"
                            } else {
                                // 이미지가 없었는데 추가한 경우에는 파일명 수정
                                if (postViewModel.postFileName.value == "None") {
                                    "image/img_${System.currentTimeMillis()}.jpg"
                                } else {
                                    // 기존 이미지가 있었으면 파일명을 유지하여 덮어씌움 (파일명을 수정하면 이미지가 계속 생기기 때문)
                                    postViewModel.postFileName.value
                                }
                            }

                            val postDataClass = PostDataClass(
                                readPostIdx, 0, subject,
                                text, "", fileName!!, mainActivity.loginUserClass.userIdx
                            )

                            PostRepository.modifyPost(postDataClass, isSelectNewImage) {
                                // 새롭게 선택한 이미지가 있다면...
                                if (isSelectNewImage == true) {
                                    PostRepository.uploadImage(uploadUri!!, fileName) {
                                        mainActivity.removeFragment(MainActivity.POST_MODIFY_FRAGMENT)
                                    }
                                } else {
                                    mainActivity.removeFragment(MainActivity.POST_MODIFY_FRAGMENT)
                                }
                            }
                        }
                    }

                    true
                }
            }
            imageViewPostModify.setImageResource(R.mipmap.ic_launcher)
        }

        return fragmentPostModifyBinding.root
    }

    // 카메라 관련 설정
    fun cameraSetting(previewImageView: ImageView): ActivityResultLauncher<Intent> {
        // 사진 촬영을 위한 런처
        val cameraContract = ActivityResultContracts.StartActivityForResult()
        val cameraLauncher = registerForActivityResult(cameraContract) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                // 새로운 이미지 선택 여부값 설정
                isSelectNewImage = true

                // Uri를 이용해 이미지에 접근하여 Bitmap 객체로 생성한다.
                val bitmap = BitmapFactory.decodeFile(uploadUri?.path)

                // 이미지의 크기를 조정한다.
                // 이미지의 축소/ 확대 비율을 구한다.
                val ratio = 1024.0 / bitmap.width
                // 세로 길이를 구한다.
                val targetHeight = (bitmap.height * ratio).toInt()

                // 크기를 조정한 Bitmap을 생성한다.
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)

                // 회전 각도를 가져온다.
                val degree = getDegree(uploadUri!!)

                // 회전 이미지를 생성하기 위한 변환 행렬
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())

                // 회전 행렬을 적용하여 회전왼 이미지를 생성한다.
                // 원본 이미지, 원본 이미지에서의 X좌표, 원본 이미지에서의 Y좌표, 원본 이미지에서의 가로길이,
                // 원본 이미지에서의 세로길이, 변환행렬, 필터정보
                // 원본 이미지에서 지정된 x, y 좌표를 찍고 지정된 가로 세로 길이만큼의 이미지 데이터를 가져와
                // 변환 행렬을 적용하여 이미지를 변환한다.
                val bitmap3 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)

                previewImageView.setImageBitmap(bitmap3)
            }
        }

        return cameraLauncher
    }

    // 앨범 관련 설정
    fun albumSetting(previewImageView: ImageView): ActivityResultLauncher<Intent> {
        val albumContract = ActivityResultContracts.StartActivityForResult()
        val albumLauncher = registerForActivityResult(albumContract) {
            if (it?.resultCode == AppCompatActivity.RESULT_OK) {
                // 선택한 이미지에 접근할 수 있는 Uri 객체를 추출한다.
                if (it.data?.data != null) {
                    // 새로운 이미지 선택 여부값 설정
                    isSelectNewImage = true

                    uploadUri = it.data?.data

                    // 안드로이드 10 (Q) 이상이라면...
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        // 이미지를 생성할 수 있는 디코더를 생성한다.
                        val source = ImageDecoder.createSource(mainActivity.contentResolver, uploadUri!!)
                        // Bitmap객체를 생성한다.
                        val bitmap = ImageDecoder.decodeBitmap(source)

                        previewImageView.setImageBitmap(bitmap)
                    } else {
                        // 컨텐츠 프로바이더를 통해 이미지 데이터 정보를 가져온다.
                        val cursor = mainActivity.contentResolver.query(uploadUri!!, null, null, null, null)
                        if (cursor != null) {
                            cursor.moveToNext()

                            // 이미지의 경로를 가져온다.
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지를 생성하여 보여준다.
                            val bitmap = BitmapFactory.decodeFile(source)
                            previewImageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }

        return albumLauncher
    }

    // 이미지 파일에 기록되어 있는 회전 정보를 가져온다.
    fun getDegree(uri: Uri): Int {
        var exifInterface: ExifInterface? = null

        // 사진 파일로 부터 tag 정보를 관리하는 객체를 추출한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val photoUri = MediaStore.setRequireOriginal(uri)
            // 스트림을 추출한다.
            val inputStream = mainActivity.contentResolver.openInputStream(photoUri)
            // ExifInterface 정보를 읽어온다.
            exifInterface = ExifInterface(inputStream!!)
        } else {
            exifInterface = ExifInterface(uri.path!!)
        }

        var degree = 0
        if (exifInterface != null) {
            // 각도 값을 가지고온다.
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        }
        return degree
    }
}