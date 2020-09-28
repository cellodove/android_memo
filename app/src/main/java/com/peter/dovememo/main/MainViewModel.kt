package com.peter.dovememo.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.repository.AppRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //처리가될때까지 기다려준다.
    private val repository: AppRepository by lazy {
        AppRepository.getInstance(getApplication())
    }
    //전체내용으 불러온다.
    val memoListLiveData: LiveData<List<Memo>> by lazy {
        repository.getAllMemoList()
    }
    //메모를 저장한다.
    fun insertMemo(memo: Memo) {
        //코르틴 사용
        viewModelScope.launch {
            //모델인 레파지토리의 메소드로 보낸다.
            repository.insertMemo(memo)
        }
    }
}