package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.checkingsystem.entity.Assistant;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 那年.盛夏 on 2017/3/14.
 */

public class DBHelperUtil extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;

    /**
     * 用来存放Dao的地图
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();


    private static DBHelperUtil instance;

    /**
     * 构造方法
     * @param context
     */
    public DBHelperUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 获取单例
     * @param context
     * @return
     */
    public static synchronized DBHelperUtil getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DBHelperUtil.class) {
                if (instance == null) {
                    instance = new DBHelperUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 这里创建表
     */
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        // 创建表
        try {
            TableUtils.createTable(connectionSource, Student.class);
            TableUtils.createTable(connectionSource,Teacher.class);
            TableUtils.createTable(connectionSource, Assistant.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里进行更新表操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        try
        {
            TableUtils.dropTable(connectionSource, Student.class, true);
            TableUtils.dropTable(connectionSource,Teacher.class,true);
            TableUtils.dropTable(connectionSource,Assistant.class,true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过类来获得指定的Dao
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (!daos.containsKey(className)) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }else {
            dao = super.getDao(clazz);
        }
        return dao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
