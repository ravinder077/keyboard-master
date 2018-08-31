package pu.punjabimobiletypingtool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		Button back2=(Button)findViewById(R.id.btnback1);
		back2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(Help.this,KeyboardActivity.class);
				startActivity(it);
			}
		});
	}

}
