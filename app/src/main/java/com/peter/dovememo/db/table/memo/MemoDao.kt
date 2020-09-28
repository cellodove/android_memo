package com.peter.dovememo.db.table.memo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.peter.dovememo.db.table.BaseDao

@Dao
interface MemoDao : BaseDao<Memo> {
    //전체불러오는 쿼리 데이스 다오를 기반으로하는 인터페이스
    @Query("SELECT * FROM memo ORDER BY date DESC, id DESC")
    fun getAll(): LiveData<List<Memo>>
}