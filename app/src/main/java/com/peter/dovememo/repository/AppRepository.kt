package com.peter.dovememo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.peter.dovememo.db.AppDatabase
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.db.table.memo.MemoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository private constructor(application: Application) {

    private val memoDao: MemoDao
    //기본 생성자 이클래스가 불리면 자동으로 실행
    //데이터베이스랑 연결
    init {
        val appDatabase = AppDatabase.getInstance(application)
        memoDao = appDatabase.memoDao()
    }

    companion object {
        private lateinit var instance: AppRepository

        fun getInstance(application: Application): AppRepository {
            //만약초기화가 되지않았다면 초기화 시키고 리턴
            if (!::instance.isInitialized) {
                instance = AppRepository(application)
            }
            return instance
        }
    }
    //전체 불러오는 함수
    fun getAllMemoList(): LiveData<List<Memo>> = memoDao.getAll()

    //지연함수 메모저장
    suspend fun insertMemo(memo: Memo) {
        //아이오 스레드사용??
        withContext(Dispatchers.IO) {
            //다오를통해 저장한다. 메모를
            memoDao.insert(memo)
        }
    }
}