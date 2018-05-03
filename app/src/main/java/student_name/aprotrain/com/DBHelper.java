package student_name.aprotrain.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private static String DATABASE_NAME = "employee";
	private static int VERSION_NAME = 1;
	
	private static DBHelper instance;

	private static Context context;

	public static DBHelper getInstance(Context context) {
		if(instance == null) {
			DBHelper.context = context;
			instance = new DBHelper(context);
		}
		return instance;
	}

	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION_NAME);
	}

	public SQLiteDatabase getSQLiteDatabase() {
		return getSQLiteDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		EmployeeModify.getInstance().createTable(sqLiteDatabase);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
