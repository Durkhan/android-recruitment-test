package com.tasks.androidrecruitmenttesttask.data.db.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tasks.androidrecruitmenttesttask.data.db.datasource.DataDaoImpl
import com.tasks.androidrecruitmenttesttask.common.Constants.DATABASE_NAME
import com.tasks.androidrecruitmenttesttask.common.Constants.TABLE_NAME
import com.tasks.androidrecruitmenttesttask.data.db.datasource.DataDao

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (" +
                "id INTEGER PRIMARY KEY, " +
                "up_down TEXT, " +
                "brand Text,"+
                "openingPrice TEXT,"+
                "currentPrice TEXT,"+
                "lowPrice TEXT,"+
                "highPrice TEXT,"+
                "valueOfSharesInDay Integer)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun dao(): DataDao {
        return DataDaoImpl(writableDatabase)
    }
}
