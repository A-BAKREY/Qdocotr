package com.example.clinicapp.chat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.chat.adapter.ChattingAdapter
import com.example.clinicapp.chat.model.RepliesModel
import com.example.clinicapp.databinding.ActivityChatBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private var read_req2 = 300
    private val camera_req = 400
    private lateinit var adapter: ChattingAdapter // Replace MyAdapter with your actual adapter class

    val items = arrayListOf(RepliesModel(
        "hi","12-12-2023, 12:55:12pm"
    ),RepliesModel(
        "how are you","12-12-2023, 12:55:12pm"
    )
  )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ChattingAdapter(items)
        binding.chatRecyclerView.adapter = adapter
        binding.sendMessage.setOnClickListener {
            val messageText = binding.addReply.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val currentTime = getCurrentTime() // قم بتعريف هذه الدالة لاسترجاع الوقت الحالي
                val newMessage = RepliesModel(messageText, currentTime)
                items.add(newMessage)
                adapter.notifyItemInserted(items.size - 1)
                binding.chatRecyclerView.scrollToPosition(items.size - 1)
                binding.addReply.setText("") // لمسح نص الرسالة بعد الإرسال إذا رغبت
            }
        }

        binding.exandLayout.parentLayout.visibility = View.GONE

        binding.exandLayout.parentLayout.setOnClickListener { binding.exandLayout.toggleLayout() }

        binding.imageFile.setOnClickListener {
            onFileClick()
        }
        binding.imageDeleteAttachment.setOnClickListener { view ->
            binding.cardView.visibility = View.GONE
            binding.imageDeleteAttachment.visibility = View.GONE
        }
        binding.imageSelectImage.setOnClickListener { view ->
            binding.exandLayout.isExpanded
            binding.exandLayout.parentLayout.visibility = View.VISIBLE

            binding.exandLayout.parentLayout.findViewById<ImageView>(R.id.imgGallery)
                .setOnClickListener {
                    onGalleryClick()
                }
            binding.exandLayout.parentLayout.findViewById<ImageView>(R.id.imgCamera)
                .setOnClickListener {
                    onCameraClick()
                }
            binding.exandLayout.parentLayout.findViewById<FrameLayout>(R.id.btnHide)
                .setOnClickListener {
                    binding.exandLayout.parentLayout.visibility = View.GONE
                }
        }
    }
    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy, hh:mm:ss a", Locale.getDefault())
        val currentTime = Calendar.getInstance().time
        return dateFormat.format(currentTime)
    }
    private fun onCameraClick() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is granted
            binding.exandLayout.parentLayout.visibility = View.GONE
            // checkCameraPermission(camera_req) // You can include this if needed
            selectImage(camera_req)
        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                camera_req
            )
        }
    }
    private fun onFileClick() {
        val filePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
//        filePickerIntent.type = "*/*" // Set the MIME type to all files
        filePickerIntent.type = "*/*"
        startActivityForResult(filePickerIntent, 200)
    }
    private fun onGalleryClick() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is granted
            binding.exandLayout.parentLayout.visibility = View.GONE
            selectImage(read_req2)
        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                read_req2
            )
        }
    }
    private fun selectImage(req: Int) {
        val intent = Intent()

        when (req) {
            read_req2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    intent.action = Intent.ACTION_OPEN_DOCUMENT
                    intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                    intent.type = "image/*"
                } else {
                    intent.action = Intent.ACTION_GET_CONTENT
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.type = "image/*"
                }
            }

            camera_req -> {
                intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            }
//            else -> throw IllegalArgumentException("Invalid request code: $req")
        }

        startActivityForResult(intent, req)
    }
}