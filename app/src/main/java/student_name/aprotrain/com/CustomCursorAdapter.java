package student_name.aprotrain.com;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class CustomCursorAdapter extends CursorAdapter{
	public CustomCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// Find fields to populate in inflated template
		Random random = new Random();
		int rad = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		
	      TextView letter = (TextView) view.findViewById(R.id.first_letter_textview);
	      
	      letter.setBackgroundColor(rad);
	      
	      TextView title = (TextView) view.findViewById(R.id.title_textview);
	      TextView description = (TextView) view.findViewById(R.id.description_textview);
	      // Extract properties from cursor
	      String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
	      String func = cursor.getString(cursor.getColumnIndexOrThrow("func"));
	      String department = cursor.getString(cursor.getColumnIndexOrThrow("department"));
	      // Populate fields with extracted properties
	      letter.setText(name.substring(0, 1));
	      title.setText(name);
	      description.setText(func + " - " + department);
	      
	      view.setTag(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
	}
}
