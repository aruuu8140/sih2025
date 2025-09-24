package com.example.studentinnovation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.studentinnovation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var txtUsername: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtPosts: TextView
    private lateinit var txtRewards: TextView

    private lateinit var cardTerms: CardView
    private lateinit var cardFeedback: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        txtUsername = view.findViewById(R.id.txtUsername)
        txtEmail = view.findViewById(R.id.txtEmail)
        txtPosts = view.findViewById(R.id.txtPosts)
        txtRewards = view.findViewById(R.id.txtRewards)
        cardTerms = view.findViewById(R.id.cardTerms)
        cardFeedback = view.findViewById(R.id.cardFeedback)

        loadUserProfile()
        loadPostStats()

        // Handle Terms & Conditions
        cardTerms.setOnClickListener {
            startActivity(Intent(requireContext(), TermsActivity::class.java))
        }

        // Handle Feedback
        cardFeedback.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("support@betterclick.com"))
                putExtra(Intent.EXTRA_SUBJECT, "App Feedback")
                putExtra(Intent.EXTRA_TEXT, "I would like to share my feedback...")
            }
            try {
                startActivity(emailIntent)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "No email app found", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun loadUserProfile() {
        val user = auth.currentUser
        if (user != null) {
            txtUsername.text = user.displayName ?: "User"
            txtEmail.text = user.email ?: "No email"
        }
    }

    private fun loadPostStats() {
        val user = auth.currentUser ?: return
        db.collection("posts")
            .whereEqualTo("userId", user.uid)
            .get()
            .addOnSuccessListener { documents ->
                val count = documents.size()
                txtPosts.text = count.toString()
                txtRewards.text = (count / 10).toString() // 1 reward for every 10 posts
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to load posts", Toast.LENGTH_SHORT).show()
            }
    }
}

