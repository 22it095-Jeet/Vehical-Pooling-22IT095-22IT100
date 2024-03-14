package com.example.commutride
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import  android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.api.Context

class UserAdapter(val context: android.content.Context, val userlist: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {

        return userlist.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentUser =userlist[position]

        holder.textName.text =currentUser.email
    }
    class UserViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
      val textName = itemView.findViewById<TextView>(R.id.text_name)
    }

}