package com.example.lina.hhk_girls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler
        extends SQLiteOpenHelper
{
    public static final String COLUMN_HOME1 = "home1";
    public static final String COLUMN_HOME2 = "home2";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOBILE1 = "mobile1";
    public static final String COLUMN_MOBILE2 = "mobile2";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";
    private static final String DATABASE_NAME = "girlsDB.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_E3DADY = "girlsE3dady";
    public static final String TABLE_EBTDA2Y = "girlsEbtda2y";
    public static final String TABLE_THANAWY = "girlsThanawy";

    public MyDBHandler(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
    {
        super(paramContext, "girlsDB.db", paramCursorFactory, 3);
    }

    public void addRecord(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13)
    {
        ContentValues localContentValues = new ContentValues();
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localContentValues.put(paramString7, paramString1);
        localContentValues.put(paramString8, paramString2);
        localContentValues.put(paramString9, paramString3);
        localContentValues.put(paramString10, paramString4);
        localContentValues.put(paramString11, paramString5);
        localContentValues.put(paramString12, paramString6);
        localSQLiteDatabase.insert(paramString13, null, localContentValues);
        localSQLiteDatabase.close();
    }

    public boolean checkForTables(String table){
        SQLiteDatabase db = getWritableDatabase();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + table, null);

        if(cursor != null){

            cursor.moveToFirst();

            int count = cursor.getInt(0);

            if(count > 0){
                return true;
            }

            cursor.close();
        }

        return false;
    }

    public String databaseToString(String paramString1, String paramString2)
    {
        String str = "";
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM " + paramString2 + " WHERE 1", null);
        localCursor.moveToFirst();
        for (paramString2 = str; !localCursor.isAfterLast(); paramString2 = str)
        {
            str = paramString2;
            if (localCursor.getString(localCursor.getColumnIndex(paramString1)) != null)
            {
                paramString2 = paramString2 + localCursor.getString(localCursor.getColumnIndex(paramString1));
                str = paramString2 + "\n";
            }
            localCursor.moveToNext();
        }
        localSQLiteDatabase.close();
        return paramString2;
    }

    public void deleteGirl(String table, int length, String[] id)
    {
        SQLiteDatabase db = getWritableDatabase();
        Integer[] arrayOfInteger = new Integer[id.length];
       /* if (length > 1)
        {
            if (id[0] > id[1])
            {
               int i = 0;
                while (i < length)
                {
                    if (id[i] == null)
                    {
                        while (id[i] == null) {
                            id[i] = Integer.valueOf(id[i].intValue() + 1);
                        }
                        id[i] = (id[i]+ length);
                    }
                   db.delete(table, "_id=" + id[i], null);
                    i += 1;
                }
            }
            int i = length - 1;
            while (i >= 0)
            {
                if (id[i] == null)
                {
                    while (id[i] == null) {
                        id[i] = Integer.valueOf(id[i].intValue() + 1);
                    }
                    id[i] = (id[i]+ length);
                }
                db.delete(table, "_id=" + id[i], null);
                i -= 1;
            }
        }
        if (id[0] == null) {
            while (id[0] == null) {
                id[0] =(id[0] + 1);
            }
        }*/
        for(int i=0;i<length;i++)
        db.delete(table, "_id=" + id[i], null);
    }

    public int getCountt(String table)
    {
       String query = "SELECT  * FROM " + table;
        Cursor c  = getReadableDatabase().rawQuery(query, null);
        int i = c.getCount();
        c.close();
        return i;
    }

    public String getItem(String paramString1, String paramString2, String paramString3)
    {
        String str = "";
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        new StringBuilder().append("SELECT * FROM ").append(paramString3).append(" WHERE 1").toString();
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT " + paramString2 + " FROM " + paramString3 + " WHERE " + "_id" + "='" + paramString1 + "'", null);
        localCursor.moveToFirst();
        for (paramString1 = str; !localCursor.isAfterLast(); paramString1 = paramString3)
        {
            paramString3 = paramString1;
            if (localCursor.getString(localCursor.getColumnIndex(paramString2)) != null)
            {
                paramString1 = paramString1 + localCursor.getString(localCursor.getColumnIndex(paramString2));
                paramString3 = paramString1 + "\n";
            }
            localCursor.moveToNext();
        }
        localSQLiteDatabase.close();
        return paramString1;
    }

    public String getItemName(String paramString1, String paramString2, String paramString3)
    {
        String str = "";
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        new StringBuilder().append("SELECT * FROM ").append(paramString3).append(" WHERE 1").toString();
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT " + paramString2 + " FROM " + paramString3 + " WHERE " + "name" + " LIKE '"+ paramString1+"%'" , null);
        localCursor.moveToFirst();
        for (paramString1 = str; !localCursor.isAfterLast(); paramString1 = paramString3)
        {
            paramString3 = paramString1;
            if (localCursor.getString(localCursor.getColumnIndex(paramString2)) != null)
            {
                paramString1 = paramString1 + localCursor.getString(localCursor.getColumnIndex(paramString2));
                paramString3 = paramString1 + "\n";
            }
            localCursor.moveToNext();
        }
        localSQLiteDatabase.close();
        return paramString1;
    }

    public String getItemByColumn(String paramString1, String column1, String column2,String table)
    {
        String str = "";
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        new StringBuilder().append("SELECT * FROM ").append(table).append(" WHERE 1").toString();
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT " + column1 + " FROM " + table + " WHERE " + column2 + "='" + paramString1 + "'", null);
        localCursor.moveToFirst();
        for (paramString1 = str; !localCursor.isAfterLast(); paramString1 = table)
        {
            table = paramString1;
            if (localCursor.getString(localCursor.getColumnIndex(column1)) != null)
            {
                paramString1 = paramString1 + localCursor.getString(localCursor.getColumnIndex(column1));
                table = paramString1 + "\n";
            }
            localCursor.moveToNext();
        }
        localSQLiteDatabase.close();
        return paramString1;
    }


    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE girlsEbtda2y(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT ,mobile1 TEXT, mobile2 TEXT, home1 TEXT ,home2 TEXT );");
        paramSQLiteDatabase.execSQL("CREATE TABLE girlsE3dady(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT ,mobile1 TEXT ,mobile2 TEXT ,home1 TEXT ,home2 TEXT );");
        paramSQLiteDatabase.execSQL("CREATE TABLE girlsThanawy(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,year TEXT ,mobile1 TEXT, mobile2 TEXT ,home1 TEXT ,home2 TEXT );");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS girlsEbtda2y");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS girlsE3dady");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS girlsThanawy");
        onCreate(paramSQLiteDatabase);
    }

    public void update(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13)
    {
        ContentValues localContentValues = new ContentValues();
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localContentValues.put(paramString7, paramString1);
        localContentValues.put(paramString8, paramString2);
        localContentValues.put(paramString9, paramString3);
        localContentValues.put(paramString10, paramString4);
        localContentValues.put(paramString11, paramString5);
        localContentValues.put(paramString12, paramString6);
        localSQLiteDatabase.update(paramString13, localContentValues, "_id=" + paramInt, null);
    }
}
