package com.egco428.practice.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.egco428.practice.R

/**
 * Created by 6272user on 11/27/2017 AD.
 */
class MessageAdapter(val mContext: Context, val layoutResId: Int, val messageList: List<userInfo>): ArrayAdapter<userInfo>(mContext, layoutResId, messageList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutResId, null)
        val msgTextView = view.findViewById<TextView>(R.id.userList)
        val msg = messageList[position]

        msgTextView.text = msg.username
        return view
    }
}