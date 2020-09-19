package my.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.recyclerview.*
import my.users.models.UserMO
import my.users.viewholder.UserVH

class UserList : Fragment(R.layout.recyclerview) {
    private lateinit var databaseReference: DatabaseReference
    private var fbAdapter: FirebaseRecyclerAdapter<UserMO, UserVH>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        databaseReference = FirebaseDatabase.getInstance().reference
        val userQuery = databaseReference.child("user-list")
        val fbOpt = FirebaseRecyclerOptions.Builder<UserMO>()
            .setQuery(userQuery, UserMO::class.java).build()
        fbAdapter = object : FirebaseRecyclerAdapter<UserMO, UserVH>(fbOpt) {
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserVH {
                val inflater = LayoutInflater.from(viewGroup.context)
                return UserVH(inflater.inflate(R.layout.allusers, viewGroup, false))
            }

            override fun onBindViewHolder(uservh: UserVH, i: Int, allusers: UserMO) {
                uservh.listUser(allusers)
                uservh.itemView.setOnClickListener {
                    findNavController().navigate(R.id.userDetails)
                }
            }
        }
        rView.apply {
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = fbAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        fbAdapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        fbAdapter?.stopListening()
    }
}