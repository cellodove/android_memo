package com.peter.dovememo.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.peter.dovememo.R
import com.peter.dovememo.databinding.ActivityMainBinding
import com.peter.dovememo.db.table.memo.Memo

class MainActivity : AppCompatActivity() {
    //엑티비티멘인xml전체를 데이터 바인딩한다
    private lateinit var binding: ActivityMainBinding
    //메인뷰모델클래스를 뷰모델로 지정한다.
    private val viewModel by viewModels<MainViewModel>()

    //처음 앱을 실행한다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        //밑에선언한 함수들을 실행한다.
        setListener()
        setObserver()
    }
    //뷰모델로 액션을 보낸다고 생각하자
    private fun setListener() {
        binding.apply {
            add.setOnClickListener {
                //xml타이틀에있는걸 변수 타이틀에 넣는다.
                val title = title.text.toString()
                //타이틀변수를 메모데이터 형식에 넣는다.
                val memo = Memo(0, title)
                //메모데이터형식으로된것을 뷰모델메소드로 보낸다.
                viewModel.insertMemo(memo)
                //전송한다음에 적혀있는 내용을 지운다
                this.title.text.clear()
            }
        }
    }

    //데이터 변화를 감지한다
    private fun setObserver() {
        //전체 데이터를 불러온다.
        viewModel.memoListLiveData.observe(this, Observer {
            var text = ""
            it.forEach {item ->
                text += "${item.title}\n"
            }
            binding.memoContents.text = text
        })
    }
}