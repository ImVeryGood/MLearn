package com.example.mlearn.home

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.mlearn.AidlService
import com.example.mlearn.Book
import com.example.mlearn.IBookManager
import com.example.mlearn.R

class ProcessMainActivity : AppCompatActivity() {
    private val TAG=javaClass.name
    lateinit var mService:IBookManager
    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected: 链接成功")
             mService = IBookManager.Stub.asInterface(service)
            val book1 = Book("三国", "sanguo")
            mService.addbook(book1)
            val bookList = mService.bookList
            bookList?.forEach {
                Log.d("TAG", "onServiceConnected:name== " + it.bookName)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_main)
        bind()

    }

    private fun bind() {
        val intent = Intent(this, AidlService::class.java)
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }

}