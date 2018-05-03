package student_name.aprotrain.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		
		TextView nameTextView = (TextView) findViewById(R.id.name_textview);
		TextView ageTextView = (TextView) findViewById(R.id.age_textview);
		TextView funcTextView = (TextView) findViewById(R.id.func_textview);
		TextView departmentTextView = (TextView) findViewById(R.id.department_textview);
		
		String name = getIntent().getExtras().getString("name");
		int age = getIntent().getExtras().getInt("age", 0);
		String func = getIntent().getExtras().getString("func");
		String department = getIntent().getExtras().getString("department");
		
		nameTextView.setText(name);
		ageTextView.setText("" + age);
		funcTextView.setText(func);
		departmentTextView.setText(department);
	}
}
