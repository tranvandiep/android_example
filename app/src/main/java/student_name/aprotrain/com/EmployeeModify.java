package student_name.aprotrain.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EmployeeModify {
	private static EmployeeModify instance;
	
	private EmployeeModify() {}
	
	public static EmployeeModify getInstance() {
		if(instance == null) {
			instance = new EmployeeModify();
		}
		return instance;
	}
	
	public void createTable(SQLiteDatabase sqLiteDatabase) {
		String query = "CREATE TABLE IF NOT EXISTS EMPLOYEE (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"name varchar(50)," +
				"age INTEGER," +
				"func varchar(150)," +
				"department varchar(150)" +
				")";
		sqLiteDatabase.execSQL(query);
	}
	
	public void insert(Context context, Employee employee) {
		SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(context).getWritableDatabase();

		ContentValues content = new ContentValues();
		content.put("name", employee.getName());
		content.put("age", employee.getAge());
		content.put("func", employee.getFunc());
		content.put("department", employee.getDepartment());
		sqLiteDatabase.insert("EMPLOYEE", null, content);
	}
	
	public void delelte(Context context, Employee employee) {
		SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(context).getWritableDatabase();

		String sql = "DELETE FROM EMPLOYEE WHERE _id = " + employee.getId();
		sqLiteDatabase.execSQL(sql);
	}
	
	public void update(Context context, Employee employee) {
		SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(context).getWritableDatabase();

		String sql = "UPDATE EMPLOYEE SET name = \'" + employee.getName() + "\', age = " + employee.getAge() + ", func = \'" + employee.getFunc() + "\', department = \'" + employee.getDepartment() + "\' WHERE _id = " + employee.getId();

		Log.e(EmployeeModify.class.getName(), sql);

		sqLiteDatabase.execSQL(sql);
	}
	
	public Employee read(Context context, int id) {
		SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(context).getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM EMPLOYEE WHERE _id = " + id, null);
        if(cursor.moveToFirst()){
            do{
            	
               //assing values
               String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
               int age =  cursor.getInt(cursor.getColumnIndexOrThrow("age"));
               String func = cursor.getString(cursor.getColumnIndexOrThrow("func"));
               String department =  cursor.getString(cursor.getColumnIndexOrThrow("department"));
               
               Employee employee = new Employee(name, age, func, department);
				employee.setId(id);
               
               cursor.close();
               sqLiteDatabase.close();
               
               return employee;
        //Do something Here with values

            }while(cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        
        return null;
	}
}
