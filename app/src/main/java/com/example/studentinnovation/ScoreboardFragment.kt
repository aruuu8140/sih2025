package com.example.studentinnovation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentinnovation.R

class ScoreboardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scoreboard, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerScoreboard)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val fakeList = listOf("Aryan - 120", "Rahul - 100", "Sneha - 95", "Priya - 90", "Ravi - 85")
        recyclerView.adapter = SimpleAdapter(fakeList)

        return view
    }
}

class SimpleAdapter(private val list: List<String>) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text = view.findViewById<android.widget.TextView>(R.id.itemText)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = list[position]
    }
}
