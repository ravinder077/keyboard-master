package pu.punjabimobiletypingtool;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aboutus extends Activity{
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.aboutus);
//	Typeface font = Typeface.createFromAsset(getAssets(),
//			"AnmolUn.ttf");

//aboutus.setTypeface(font);
Button back1=(Button)findViewById(R.id.btnback);
back1.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent it=new Intent(Aboutus.this,KeyboardActivity.class);
		startActivity(it);
	}
	
	
});
}
}
