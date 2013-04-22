package star.pnrstatus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public WebView wv;
	public Button b1;
	public EditText et1;
	public TextView tv1,tv2;
	public String value;
	public String hell;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//820-8601940
		b1=(Button)findViewById(R.id.b1);
		et1=(EditText)findViewById(R.id.et1);
		tv1=(TextView)findViewById(R.id.tv1);
		wv=(WebView)findViewById(R.id.wv);
		
		
	//	tv2=(TextView)findViewById(R.id.hello);
		
	//	new hello().execute();
		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wv.clearView();
				hell = et1.getText().toString();
				if(hell.length()==10)
				grabURL(hell);
				else 
					Toast.makeText(getApplicationContext(), "Please enter 10 digit PNR no.", Toast.LENGTH_SHORT).show();
				
				
			}
		});
	}

	public class hello extends AsyncTask<String, String, String >{

		
		private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
		InputStream is = null;
		StringBuilder sb=null;
		String ls=null;
		
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			Dialog.setMessage("Checking PNR Status..");
			list.add(new BasicNameValuePair("lccp_pnrno1",hell));
			Dialog.show();
//			super.onPreExecute();
		}
		
		




		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Dialog.cancel();
	
			
			Scanner sc = new Scanner(ls);
			sc.useDelimiter("Charting");
			sc.findInLine("center_table");
					
			String asd = sc.next();
			asd=asd.substring(3, asd.length());
			String mw = "<TABLE>";
			String n = mw+asd;
			String m = "</TD></TR></TABLE>";
			String p =n+m;
	
		if(ls.contains("FLUSHED")){
			
			wv.loadData("Please enter correct number", "text/html", "UTF-8");  
			wv.setVisibility(WebView.VISIBLE);
			Toast.makeText(getApplicationContext(), "Flush found", Toast.LENGTH_SHORT).show();
			}
		else {
			wv.loadData(p, "text/html", "UTF-8");  
			wv.setVisibility(WebView.VISIBLE);
			Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();}
	
		}



		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
		String line=null;
			
			HttpClient hp = new DefaultHttpClient();
			HttpPost http = new HttpPost("http://www.indianrail.gov.in/cgi_bin/inet_pnrstat_cgi.cgi");
			
			try {
				http.setEntity(new UrlEncodedFormEntity(list));
				
				HttpResponse resp=hp.execute(http);
		
			HttpEntity he = resp.getEntity();
			is=he.getContent();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			sb = new StringBuilder();
			
			while((line = in.readLine()) != null)
				sb.append(line);
		
			line=sb.toString();

			ls = line;
			runOnUiThread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "length "+ls.length(), Toast.LENGTH_SHORT).show();		
				}});
			
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("asdf", "eRRRRRRROR");

			}
			
			return null;
		}
		
	
	}
	
	
	
	
	public void grabURL(String url){

		new hello().execute();

		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	
}




