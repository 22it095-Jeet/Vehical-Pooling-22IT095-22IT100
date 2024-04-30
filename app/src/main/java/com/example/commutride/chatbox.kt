package com.example.commutride

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class chatbox : AppCompatActivity() {
    private lateinit var chatRecyclerview: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList : ArrayList<Message>
    private lateinit var mDabase : DatabaseReference
    var receiverRoom : String? =null
    var senderRoom : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chatbox)

        val email=intent.getStringExtra("email")
        val receiverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDabase = FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverUid + senderUid
        receiverRoom =  senderUid + receiverUid


        supportActionBar?.title= email

        chatRecyclerview= findViewById(R.id.chatRecyclerview)
        messageBox= findViewById(R.id.massagebox)
        sendButton= findViewById(R.id.sent)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this,messageList)
        chatRecyclerview.layoutManager =LinearLayoutManager(this)
        chatRecyclerview.adapter = messageAdapter
        //logic for adding data to recyclerView
        mDabase.child("Chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for(postSnapshot in snapshot.children)
                    {
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        sendButton.setOnClickListener {
            val message = messageBox.text.toString()
            val messageObject  = Message(message,senderUid)

            mDabase.child("Chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDabase.child("Chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")

        }
    }
}