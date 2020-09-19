package my.users.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.allusers.view.*
import my.users.models.UserMO

class UserVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun listUser(allusers: UserMO) {
        itemView.tv01.text = allusers.D1
        itemView.tv02.text = allusers.D2
    }
}