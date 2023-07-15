package com.test.android75_contentprovider_practice1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {

    lateinit var sqLiteDatabase: SQLiteDatabase

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val cnt = sqLiteDatabase.delete("TestTable",selection,selectionArgs)
        return cnt
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        sqLiteDatabase.insert("TestTable", null, values)

        return uri
    }


    override fun onCreate(): Boolean {
        val dh = DBHelper(context!!)
        sqLiteDatabase = dh.writableDatabase

        if(sqLiteDatabase == null){
            return false
        }

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val cursor = sqLiteDatabase.query("TestTable",projection, selection,selectionArgs, null,null, sortOrder)
        return cursor
    }



    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val cnt = sqLiteDatabase.update("TestTable",values,selection,selectionArgs)
        return cnt
    }
}