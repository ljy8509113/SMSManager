package com.sms.send.smsmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private static DBManager _instance;
    Context myContext = null;

    public static DBManager getInstance(Context context){
        if(_instance == null){
            _instance = new DBManager(context);
        }
        _instance.myContext = context;
        return _instance;
    }

    static final String DB_CONTACT = "ContactList.db";   //DB이름
    static final String TABLE_CONTACT = "Contact"; //Table 이름
    static final int DB_VERSION = 1;			//DB 버전

    private SQLiteDatabase mydatabase = null;

    private DBManager(Context context) {
        myContext = context;

        //DB Open
        mydatabase = context.openOrCreateDatabase(DB_CONTACT, context.MODE_PRIVATE,null);

        //Table 생성
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_CONTACT +
                "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "phone TEXT," +
                "group_name TEXT," +
                "last_send_date DATE,"+
                "msg_type int);");
    }

    

}
