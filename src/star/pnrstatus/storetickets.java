package star.pnrstatus;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class storetickets extends Activity {
	public Button savet;
	public EditText pnrnum,stat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticket);
		final SQLiteDatabase db = openOrCreateDatabase("pnr", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		String query = "create table if not exists pnr (pnr_no integer,status integer );";
		db.execSQL(query);
		
		savet = (Button)findViewById(R.id.saveticket);
		pnrnum = (EditText)findViewById(R.id.pnrnum);
		stat = (EditText)findViewById(R.id.pnrstat);
		
		savet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				int ticket = (int)Float.parseFloat(pnrnum.getText().toString());
				ContentValues values =new ContentValues();
				values.put("pnr_no", ticket);
				values.put("status",1);
				
				db.insert("pnr", null, values);
				
			}
		});
		
	}

	
	
	
	
}
