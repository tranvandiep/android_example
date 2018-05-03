package student_name.aprotrain.com;

import android.app.Application;

public class EmployeeApplication extends Application{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DBHelper.getInstance(getApplicationContext());
	}
}
