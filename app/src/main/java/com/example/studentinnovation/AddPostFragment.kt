package com.example.studentinnovation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.studentinnovation.R
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddPostFragment : Fragment() {
    private var imageUri: Uri? = null
    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        imageView = view.findViewById(R.id.imagePreview)
        val selectBtn = view.findViewById<Button>(R.id.btnSelect)
        val uploadBtn = view.findViewById<Button>(R.id.btnUpload)

        selectBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        uploadBtn.setOnClickListener {
            if (imageUri != null) {
                val filename = UUID.randomUUID().toString()
                val ref = FirebaseStorage.getInstance().reference.child("posts/$filename.jpg")
                ref.putFile(imageUri!!)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Uploaded!", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}

