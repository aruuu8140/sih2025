package com.example.studentinnovation



import com.example.studentinnovation.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

import com.google.android.material.appbar.MaterialToolbar

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // ✅ RecyclerView setup
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerPosts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // TODO: Attach adapter for posts (Firestore later)

        // ✅ Toolbar setup
        val toolbar = view.findViewById<MaterialToolbar>(R.id.homeToolbar)


        // Handle menu clicks
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_sos -> {
                    startActivity(Intent(requireContext(), SosActivity::class.java))
                    true
                }
                R.id.action_towing -> {
                    Toast.makeText(requireContext(), "Towing Service 🚚", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_trusted -> {
                    Toast.makeText(requireContext(), "Add Trusted Contact 👥", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        return view
    }
}

