package student_name.aprotrain.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView employeeListView;
	CustomCursorAdapter cursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DBHelper handler = DBHelper.getInstance(this);

        SharedPreferences sharedPreferences = getSharedPreferences("EXAMINATION", Context.MODE_PRIVATE);
        boolean isInit = sharedPreferences.getBoolean("isInit", false);
        if(!isInit) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isInit", true);
            editor.commit();

            //khoi 1 vai employees
			Employee employee = new Employee("A 1", 20, "func 1", "department 1");
			EmployeeModify.getInstance().insert(DBHelper.context, employee);
			
			employee = new Employee("A 2", 21, "func 2", "department 2");
			EmployeeModify.getInstance().insert(DBHelper.context, employee);
			
			employee = new Employee("A 3", 22, "func 3", "department 3");
			EmployeeModify.getInstance().insert(DBHelper.context, employee);
        }


		SQLiteDatabase db = handler.getWritableDatabase();
		Cursor todoCursor = db.rawQuery("SELECT  * FROM EMPLOYEE", null);

		cursorAdapter = new CustomCursorAdapter(this, todoCursor);

		employeeListView = (ListView) findViewById(R.id.employee_listview);

		employeeListView.setAdapter(cursorAdapter);

		employeeListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int position, long arg3) {
						// TODO Auto-generated method stub
						int id = (Integer) view.getTag();
						Employee employee = EmployeeModify.getInstance().read(MainActivity.this, id);
						
						Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
						intent.putExtra("name", employee.getName());
						intent.putExtra("age", employee.getAge());
						intent.putExtra("func", employee.getFunc());
						intent.putExtra("department", employee.getDepartment());
						
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
					}

				});
		employeeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				int id = (Integer) view.getTag();
				showOption(id);
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);// Menu Resource, Menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_item:
			showCreateEmployeeDialog(null);
			return true;
		case R.id.exit_item:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showOption(final int idEmployee) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Lua chon");

		// set dialog message
		alertDialogBuilder
				.setMessage("Ban lua chon gi!")
				.setCancelable(false)
				.setPositiveButton("DELELTE",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Employee employee = new Employee();
								employee.setId(idEmployee);
								EmployeeModify.getInstance().delelte(MainActivity.this, employee);

								// update listview
								DBHelper handler = DBHelper.getInstance(MainActivity.this);
								SQLiteDatabase db = handler.getWritableDatabase();
								Cursor todoCursor = db
										.rawQuery("SELECT  * FROM EMPLOYEE", null);

								cursorAdapter.changeCursor(todoCursor);
							}
						})
				.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
						Employee employee = EmployeeModify.getInstance().read(MainActivity.this, idEmployee);
						showCreateEmployeeDialog(employee);

						// update listview
						DBHelper handler = DBHelper.getInstance(MainActivity.this);
						SQLiteDatabase db = handler.getWritableDatabase();
						Cursor todoCursor = db
								.rawQuery("SELECT  * FROM EMPLOYEE", null);

						cursorAdapter.changeCursor(todoCursor);
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private void showCreateEmployeeDialog(final Employee currenteEmployee) {
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCancelable(false);
		dialog.setContentView(R.layout.employee_view);

		final EditText name = (EditText) dialog.findViewById(R.id.nameTextView);
		final EditText age = (EditText) dialog.findViewById(R.id.ageTextView);
		final EditText func = (EditText) dialog.findViewById(R.id.funcTextView);
		final EditText department = (EditText) dialog
				.findViewById(R.id.departmentTextView);
		if(currenteEmployee != null) {
			name.setText(currenteEmployee.getName());
			age.setText("" + currenteEmployee.getAge());
			func.setText(currenteEmployee.getFunc());
			department.setText(currenteEmployee.getDepartment());
		}

		Button cancelButton = (Button) dialog.findViewById(R.id.cancel_button);
		Button commitButton = (Button) dialog.findViewById(R.id.commit_button);

		commitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
				if(currenteEmployee != null) {
					//update
					currenteEmployee.setName(name.getText().toString());
					currenteEmployee.setAge(Integer.parseInt(age.getText().toString()));
					currenteEmployee.setFunc(func.getText().toString());
					currenteEmployee.setDepartment(department.getText().toString());
					EmployeeModify.getInstance().update(MainActivity.this, currenteEmployee);
				} else {
					//insert
					Employee employee = new Employee(name.getText().toString(),
							Integer.parseInt(age.getText().toString()), func
									.getText().toString(), department.getText()
									.toString());
					EmployeeModify.getInstance().insert(MainActivity.this, employee);
				}
				

				// update listview
				DBHelper handler = DBHelper.getInstance(MainActivity.this);
				SQLiteDatabase db = handler.getWritableDatabase();
				Cursor todoCursor = db
						.rawQuery("SELECT  * FROM EMPLOYEE", null);

				cursorAdapter.changeCursor(todoCursor);
			}
		});

		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();

	}
}
