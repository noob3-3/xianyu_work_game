package com.example.msi_pc.demo_619.Tools.SQLiteOperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// 排行榜存储
public class RankingListSqlite {

    public static DatabaseHelper databaseHelper;

    public static String TableName = "RankingListTable";      // 表名称
    public static String _id       = "_id";                   // 数据库自动生成 数据的id
    public static String _score    = "score";                 // 分数
    public static String _name     = "name";                  // 名字

    public String id;
    public String name;
    public String score;


    // 初始化数据库得到数据库操作对象
    public static void initDataBase(Context context,String DataBaseName)
    {
        databaseHelper = new DatabaseHelper(context,DataBaseName) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                super.onCreate(db);
                db.execSQL(ChangeToSqlite());
            }
        };
    }

    public RankingListSqlite(){}

    public RankingListSqlite(String name,String score)
    {
        this.name  = name;
        this.score = score;
    }


    private static String ChangeToSqlite() {
        String sql = "CREATE TABLE "
                + TableName
                + " (  "
                + _id    + " integer primary key autoincrement, "
                + _name  + " TEXT, "
                + _score + " TEXT "
                + ");";
        return sql;
    }

    // 将对象转化为可以存储进数据库的形式
    public ContentValues ChangeToContentValues()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_name,this.name);
        return contentValues;
    }

    // 将本条数据插入数据库
    public static long InsertToSqlData(RankingListSqlite rankingListSqlite)
    {
        // 获取数据库操作对象
        SQLiteDatabase db =  databaseHelper.getWritableDatabase();
        long number = db.insert(TableName,null,rankingListSqlite.ChangeToContentValues());
        return number;
    }

    // 删除本条数据
    public static void DeleteToSqlData(RankingListSqlite rankingListSqlite)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TableName,_id + "=?",new String[]{rankingListSqlite.id});
        db.close();
    }

    // 更改本条数据
    public static void UpdateToSqlData(RankingListSqlite rankingListSqlite)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.update(TableName,rankingListSqlite.ChangeToContentValues(),_id+"=?",new String[]{rankingListSqlite.id});
        db.close();
    }

    // 查询所有信息
    public static List<RankingListSqlite> getTableAllInformation()
    {
        List<RankingListSqlite> rankingListSqlites = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TableName, null, null, null, null, null, null);
        cursor.moveToFirst();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            RankingListSqlite rankingListSqlite = new RankingListSqlite();
            rankingListSqlite.name  = cursor.getString(cursor.getColumnIndex(_name));
            rankingListSqlite.score = cursor.getString(cursor.getColumnIndex(_score));
            rankingListSqlite.id    = cursor.getString(cursor.getColumnIndex(_id));
            rankingListSqlites.add(rankingListSqlite);
        }
        return rankingListSqlites;
    }

}
