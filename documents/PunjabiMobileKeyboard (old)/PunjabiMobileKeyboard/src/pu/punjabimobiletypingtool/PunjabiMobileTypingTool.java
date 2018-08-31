package pu.punjabimobiletypingtool;




import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

public class PunjabiMobileTypingTool extends Activity
{
	ArrayList<WordBean> words=new ArrayList<WordBean>(); 
	SharedPreferences pref;
	int Max_Word=4419;
		@SuppressLint("CommitPrefEdits")
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.typing_tool);
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		pref=getSharedPreferences("mypref", MODE_PRIVATE);
		
		
		
		boolean flag=pref.getBoolean("Check", false);
		//Toast.makeText(this, ""+flag, 1000).show();
		
		if(!flag)
		{
			pref=getSharedPreferences("mypref", MODE_PRIVATE);
			SharedPreferences.Editor edit=pref.edit();
			edit.putBoolean("Check", true);
			edit.commit();
			
			InputStream ins = this.getResources().openRawResource( this.getResources().getIdentifier("raw/wordlist","raw", this.getPackageName()) );
			BufferedReader br = new BufferedReader( new InputStreamReader(ins) );
		
			String strLine;
//
//				
				try
				{
					while( (strLine = br.readLine()) != null)
					{
						 db = new DatabaseHandler(this);
						
						System.out.println("hello"+strLine);
						String[] dic = strLine.split("\t");
						
						//ddData[arr_count].strWord = new String(dic[0]);
						
						
						System.out.println(dic[0].trim());
						WordBean bean=new WordBean();
						bean.setWord(dic[0].trim());
						bean.setFrequency(Integer.parseInt(dic[1].trim()));
						
						db.addWord(bean);
						
					}
				}catch(Exception e){
					e.printStackTrace();
					Toast.makeText(this, "Error"+e.getMessage(), 1000).show();
				}
				

				Intent it = new Intent(PunjabiMobileTypingTool.this, KeyboardActivity.class);
				PunjabiMobileTypingTool.this.finish();
				startActivity(it);
		}
		else
		{
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					
					Intent it = new Intent(PunjabiMobileTypingTool.this, KeyboardActivity.class);
					PunjabiMobileTypingTool.this.finish();
					startActivity(it);
				}
			}, 2000);
			
		}
//		Thread th = new Thread(rb);
//		th.start();
//		startService(new Intent(PunjabiMobileTypingTool.this,
//				MyService.class));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.typing_tool, menu);
		return true;
	}

}
