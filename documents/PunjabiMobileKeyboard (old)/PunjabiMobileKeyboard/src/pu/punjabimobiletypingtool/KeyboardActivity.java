package pu.punjabimobiletypingtool;

import java.util.ArrayList;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 import com.example.filepro.R;*/
@SuppressWarnings("deprecation")
public class KeyboardActivity extends Activity  {

	Button btn24,btn27,btn27a,btn32b,btn36,btn36a,btn37,btn37a,btn38,btn38a,btn40,btn40a,btn41,btn41a,btn42,btn42a,btn43a;
	EditText tb;
	int f=2;
	int si, li, diffi;
	String[] word = new String[8000];
	ArrayList<WordBean> words=new ArrayList<WordBean>(); 
	Button helpbtn1,helpbtn2,helpbtn3,helpbtn4,helpbtn5;
	int Max_Word=4419;
	AlertDialog alertdialog;
	RelativeLayout aboutLayout;
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	// "[a-zA-Z]"+
			"[a-zA-Z0-9+._%-+]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}"
					+ "(" + "." + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");

	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btn10 ;
	Button btn1a,btn2a,btn3a,btn4a,btn5a,btn6a,btn7a,btn8a,btn9a, btn10a ;
	ArrayList<String> numbers=new ArrayList<String>();
	ArrayList<String> names=new ArrayList<String>();
	 Typeface font, font1;
	 Button about;
	AutoCompleteTextView mobno;
	
	Button btncopy;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 font = Typeface.createFromAsset(getAssets(),
				"ANMOLUNI_0.TTF");
		 /*font1= Typeface.createFromAsset(getAssets(),
					"ARIAL_0.TTF");*/
		setContentView(R.layout.main);
		tb = (EditText) findViewById(R.id.txtbox1);
		tb.setText(" ");
		String subject;
		
		btncopy=(Button)findViewById(R.id.btncopy);
		
		btncopy.setOnClickListener(new OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int currentapiVersion = android.os.Build.VERSION.SDK_INT;
				if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
				     android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
				     clipboard.setText(tb.getText().toString());
				} else{
				    android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
				    clipboard.setText(tb.getText().toString());
				}
			}
		});
		
		// ////
        helpbtn1=(Button)findViewById(R.id.btn1);
        helpbtn2=(Button)findViewById(R.id.btn2);
		helpbtn3=(Button)findViewById(R.id.btn3);
		helpbtn4=(Button)findViewById(R.id.btn4);
		helpbtn5=(Button)findViewById(R.id.btn5);
	//////
		
		final RelativeLayout smslayout = (RelativeLayout) findViewById(R.id.smslay);
		final RelativeLayout emaillayout = (RelativeLayout) findViewById(R.id.emailay);
		 aboutLayout=(RelativeLayout)findViewById(R.id.aboutlay);
		 mobno = (AutoCompleteTextView) findViewById(R.id.smst);
		 ContentResolver cr=getContentResolver();
			getNumber(cr);
			mobno.setThreshold(0);
			String[] array = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
		            "Eight", "Nine", "Ten" };
			
		CustomAdapter adapter=new CustomAdapter(KeyboardActivity.this,android.R.layout.simple_list_item_1,numbers,names);
//			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//		                android.R.layout.select_dialog_item, numbers);
			mobno.setAdapter(adapter);
		
		helpbtn1.setTypeface(font);
		helpbtn2.setTypeface(font);
		helpbtn3.setTypeface(font);
		helpbtn4.setTypeface(font);
		helpbtn5.setTypeface(font);
		helpbtn1.setOnClickListener(listener);
		helpbtn2.setOnClickListener(listener);
		helpbtn3.setOnClickListener(listener);
		helpbtn4.setOnClickListener(listener);
		helpbtn5.setOnClickListener(listener);
		 tb.addTextChangedListener(watcher);
		 aboutLayout=(RelativeLayout)findViewById(R.id.aboutlay);
		final EditText mobno = (EditText) findViewById(R.id.smst);

//		 final EditText smsto = (EditText) findViewById(R.id.smsString);
		final TextView smstv = (TextView) findViewById(R.id.sms);
		final Button email = (Button) findViewById(R.id.tb1);
		Button smssendbtn = (Button) findViewById(R.id.bye);
		Button emailsendbtn = (Button) findViewById(R.id.bye1);
		Button smsgo = (Button) findViewById(R.id.sendsms);

		final EditText tomail = (EditText) findViewById(R.id.tomail);
		final EditText emailsubject = (EditText) findViewById(R.id.submail);

		final Button sendemailbtn = (Button) findViewById(R.id.send);
		final Button sms = (Button) findViewById(R.id.tb2);
		Button help = (Button) findViewById(R.id.tb4);
		 about = (Button) findViewById(R.id.tb5);
		help.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent it=new Intent(KeyboardActivity.this,Help.class);
		startActivity(it);
		
	}
});
		sms.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				smslayout.setVisibility(View.VISIBLE);
				emaillayout.setVisibility(View.INVISIBLE);
				smstv.setTypeface(font);
				//mobno.setTypeface(font);
				}
		});
		smssendbtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				smslayout.setVisibility(View.INVISIBLE);
			}
		});
		email.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				emaillayout.setVisibility(View.VISIBLE);
				smslayout.setVisibility(View.INVISIBLE);
				}
		});
		emailsendbtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				emaillayout.setVisibility(View.INVISIBLE);
			}
		});
		sendemailbtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				String to = tomail.getText().toString();
				String subject = emailsubject.getText().toString();

				String message = tb.getText().toString();
tomail.setTypeface(font);
emailsubject.setTypeface(font);
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
				email.putExtra(Intent.EXTRA_SUBJECT, subject);
				email.putExtra(Intent.EXTRA_TEXT, message);
				email.setType("message/rfc822");

				startActivity(Intent.createChooser(email,
						"Choose an Email client :"));
			}
		});

		// ------------------------Sending
		// Sms----------------------------------------//

		smsgo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				
				String phoneNo = mobno.getText().toString();
				String message = tb.getText().toString().trim();
				if (phoneNo.length() > 0 && message.length() > 0)
				{
					sendSMS(phoneNo, message);
					Log.e("Sms ", "Sms no");
				}
				else
					Toast.makeText(getBaseContext(),
							"Please enter both phone number and message.",
							Toast.LENGTH_SHORT).show();
			}
		});

		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent(KeyboardActivity.this,Aboutus.class);
				startActivity(in);
			}
		});
		
		
		// First Row// gurmukhi number
		final Button  btn1 = (Button) findViewById(R.id.b1);
		final Button btn2 = (Button) findViewById(R.id.b2);
		final Button btn3 = (Button) findViewById(R.id.b3);
		final Button btn4 = (Button) findViewById(R.id.b4);
		final Button  btn5 = (Button) findViewById(R.id.b5);
		final Button btn6 = (Button) findViewById(R.id.b6);
		final Button  btn7 = (Button) findViewById(R.id.b7);
		final Button  btn8 = (Button) findViewById(R.id.b8);
		final Button  btn9 = (Button) findViewById(R.id.b9);
		final Button btn10 = (Button) findViewById(R.id.b10);
		// First Row// romon number
		final Button btn1a = (Button) findViewById(R.id.b1a);
		final Button btn2a = (Button) findViewById(R.id.b2a);
		final Button btn3a = (Button) findViewById(R.id.b3a);
		final Button btn4a = (Button) findViewById(R.id.b4a);
		final Button btn5a = (Button) findViewById(R.id.b5a);
		final Button btn6a = (Button) findViewById(R.id.b6a);
		final Button btn7a = (Button) findViewById(R.id.b7a);
		final Button btn8a = (Button) findViewById(R.id.b8a);
		final Button btn9a = (Button) findViewById(R.id.b9a);
		final Button btn10a = (Button) findViewById(R.id.b10a);		
		// Second Row
		final Button btn11 = (Button) findViewById(R.id.b11);
		final Button btn12 = (Button) findViewById(R.id.b12);
		final Button btn13 = (Button) findViewById(R.id.b13);
		final Button btn14 = (Button) findViewById(R.id.b14);
		final Button btn15 = (Button) findViewById(R.id.b15);
		final Button btn16 = (Button) findViewById(R.id.b16);
		final Button btn17 = (Button) findViewById(R.id.b17);
		final Button btn18 = (Button) findViewById(R.id.b18);
		final Button btn19 = (Button) findViewById(R.id.b19);
		// third Row
		final Button btn20 = (Button) findViewById(R.id.b20);
		final Button btn21 = (Button) findViewById(R.id.b21);
		final Button btn22 = (Button) findViewById(R.id.b22);
		final Button btn23 = (Button) findViewById(R.id.b23);
		btn24 = (Button) findViewById(R.id.b24);
		final Button btn25 = (Button) findViewById(R.id.b25);
		final Button btn26 = (Button) findViewById(R.id.b26);
		btn27 = (Button) findViewById(R.id.b27);
		btn27a = (Button) findViewById(R.id.b27a);
		final Button btn28 = (Button) findViewById(R.id.b28);
		// fourth Row
		final Button btn29 = (Button) findViewById(R.id.b29);
		final Button btn30 = (Button) findViewById(R.id.b30);
		final Button btn31 = (Button) findViewById(R.id.b31);
		final Button btn32 = (Button) findViewById(R.id.b32);
		final Button btn32b = (Button) findViewById(R.id.b32b);
		final Button btn33 = (Button) findViewById(R.id.b33);
		final Button btn34 = (Button) findViewById(R.id.b34);
		final Button btn35 = (Button) findViewById(R.id.b35);
		// fifth Row
		btn36 = (Button) findViewById(R.id.b36);
		btn36a = (Button) findViewById(R.id.b36a);
		btn37 = (Button) findViewById(R.id.b37);
		btn37a = (Button) findViewById(R.id.b37a);
		btn38 = (Button) findViewById(R.id.b38);
		btn38a = (Button) findViewById(R.id.b38a);
		final Button btn39 = (Button) findViewById(R.id.b39);
		btn40 = (Button) findViewById(R.id.b40);
		btn40a = (Button) findViewById(R.id.b40a);
		btn41 = (Button) findViewById(R.id.b41);
		btn41a = (Button) findViewById(R.id.b41a);
		btn42 = (Button) findViewById(R.id.b42);
		btn42a = (Button) findViewById(R.id.b42a);
		//btn43 = (Button) findViewById(R.id.b43);
		btn43a = (Button) findViewById(R.id.toggleButton2);
		
		
		btn43a.setTypeface(font);
		btn43a.setText("");
	
		
		// ////textBox/////////
		tb.setTypeface(font);
		/////
		// ////Message/////////
		smstv.setTypeface(font);
		///
		//GURMUKHI
		btn1.setTypeface(font);
		btn2.setTypeface(font); 
		btn3.setTypeface(font);
		btn4.setTypeface(font);
		btn5.setTypeface(font);
		btn6.setTypeface(font);
		btn7.setTypeface(font);
		btn8.setTypeface(font);
		btn9.setTypeface(font);
		btn10.setTypeface(font);
		//romon
		btn1a.setTypeface(font);
		btn2a.setTypeface(font); 
		btn3a.setTypeface(font);
		btn4a.setTypeface(font);
		btn5a.setTypeface(font);
		btn6a.setTypeface(font);
		btn7a.setTypeface(font);
		btn8a.setTypeface(font);
		btn9a.setTypeface(font);
		btn10a.setTypeface(font);
		//
		btn11.setTypeface(font);
		btn12.setTypeface(font);
		btn13.setTypeface(font);
		btn14.setTypeface(font);
		btn15.setTypeface(font);
		btn16.setTypeface(font);
		btn17.setTypeface(font);
		btn18.setTypeface(font);
		btn19.setTypeface(font);
		btn20.setTypeface(font);
		btn21.setTypeface(font);
		btn22.setTypeface(font);
		btn23.setTypeface(font);
		btn24.setTypeface(font);
		btn25.setTypeface(font);
		btn26.setTypeface(font);
		btn27.setTypeface(font);
		btn28.setTypeface(font);
		btn29.setTypeface(font);
		btn30.setTypeface(font);
		btn31.setTypeface(font);
		btn32.setTypeface(font);
		btn32b.setTypeface(font);
		btn33.setTypeface(font);
		btn34.setTypeface(font);
		btn35.setTypeface(font);
		
		btn36.setTypeface(font);
		btn37.setTypeface(font);
		btn38.setTypeface(font);
		btn39.setTypeface(font);
		btn40.setTypeface(font);
		btn41.setTypeface(font);
		smssendbtn.setTypeface(font);
		//////////////////////////////////////// Start First Row Roman Number Coding
										 				btn1a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st1 = tb.getText().toString();
																tb.setText(st1 + "1");
																//tb.setTypeface(font1);
																tb.setSelection(tb.getText().length());
																hide();hide1();
															}
														});
														btn1a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st1 = tb.getText().toString();
																tb.setText(st1 + "ੴ");
																tb.setSelection(tb.getText().length());
																hide();hide1();
																return true;
															}
														});
														 // 
														btn2a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st2 = tb.getText().toString();
																tb.setText(st2 + "2");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn2a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st2 = tb.getText().toString();
																tb.setText(st2 + ",");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn3a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st3 = tb.getText().toString();
																tb.setText(st3 + "3");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn3a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st3 = tb.getText().toString();
																tb.setText(st3 + "'");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn4a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st4 = tb.getText().toString();
																tb.setText(st4 + "4");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn4a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st4 = tb.getText().toString();
																tb.setText(st4 + "/");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn5a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st5 = tb.getText().toString();
																tb.setText(st5 + "5");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn5a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st5 = tb.getText().toString();
																tb.setText(st5 + "?");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn6a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st6 = tb.getText().toString();
																tb.setText(st6 + "6");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn6a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st6 = tb.getText().toString();
																tb.setText(st6 + "-");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn7a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st7 = tb.getText().toString();
																tb.setText(st7 + "7");
																
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn7a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st7 = tb.getText().toString();
																tb.append(""+'!');
																
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn8a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st8 = tb.getText().toString();
																tb.setText(st8 + "8");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn8a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st8 = tb.getText().toString();
																tb.setText(st8 + "*");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn9a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st9 = tb.getText().toString();
																tb.setText(st9 + "9");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn9a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st9 = tb.getText().toString();
																tb.setText(st9 + "(");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn10a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st10 = tb.getText().toString();
																tb.setText(st10 + "0");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn10a.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st10 = tb.getText().toString();
																tb.setText(st10 + ")");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
				//////////////////////////////////////// End First Row Roman Number Coding
				//////////////////////////////////////// Start First Row GurmuKhi Number Coding
														btn1.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st1 = tb.getText().toString();
																tb.setText(st1 + "੧");
																//tb.setTypeface(font);
																tb.setSelection(tb.getText().length());
																hide();hide1();
															}
														});
														btn1.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st1 = tb.getText().toString();
																tb.setText(st1 + "ੴ");
																tb.setSelection(tb.getText().length());
																hide();hide1();
																return true;
															}
														});
														 // 
														btn2.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st2 = tb.getText().toString();
																tb.setText(st2 + "੨");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn2.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st2 = tb.getText().toString();
																tb.setText(st2 + ",");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn3.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st3 = tb.getText().toString();
																tb.setText(st3 + "੩");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn3.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st3 = tb.getText().toString();
																tb.setText(st3 + "'");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn4.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st4 = tb.getText().toString();
																tb.setText(st4 + "੪");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn4.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st4 = tb.getText().toString();
																tb.setText(st4 + "/");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn5.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st5 = tb.getText().toString();
																tb.setText(st5 + "੫");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn5.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st5 = tb.getText().toString();
																tb.setText(st5 + "?");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn6.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st6 = tb.getText().toString();
																tb.setText(st6 + "੬");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn6.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st6 = tb.getText().toString();
																tb.setText(st6 + "-");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn7.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st7 = tb.getText().toString();
																tb.setText(st7 + "੭");
																
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn7.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st7 = tb.getText().toString();
																tb.append(""+'!');
																
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn8.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st8 = tb.getText().toString();
																tb.setText(st8 + "੮");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn8.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st8 = tb.getText().toString();
																tb.setText(st8 + "*");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn9.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st9 = tb.getText().toString();
																tb.setText(st9 + "੯");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn9.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st9 = tb.getText().toString();
																tb.setText(st9 + "(");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn10.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st10 = tb.getText().toString();
																tb.setText(st10 + "੦");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn10.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st10 = tb.getText().toString();
																tb.setText(st10 + ")");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});		
				//////////////////////////////////////// End First Row GurmuKhi Number Coding
														///// second row 
														// //////////////
														btn11.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st11 = tb.getText().toString();
																tb.setText(st11 + "ੳ");
															tb.setSelection(tb.getText().length());
																show();hide1();hide2();;}});
														btn11.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st11 = tb.getText().toString();
																tb.setText(st11 + "ਅ");
																tb.setSelection(tb.getText().length());
																hide();hide2();
																show1();return true;}});
														 // 
														btn12.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st12 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st12 = st12.substring(0, st12.length() - 1);
																	tb.setText(st12 + "ਇ");}
																else {tb.setText(st12 + "ੲ");}
																//end sihari first
																tb.setSelection(tb.getText().length());
																hide();hide1();
																show2();}});
														btn12.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st12 = tb.getText().toString();
																tb.setText(st12 + "ਓ");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();
																return true;}});
														 // 
														btn13.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st13 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st13 = st13.substring(0, st13.length() - 1);
																	tb.setText(st13 + "ਸਿ");}
																else {tb.setText(st13 + "ਸ");}
																//end sihari first
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn13.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st13 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st13 = st13.substring(0, st13.length() - 1);
																	tb.setText(st13 + "ਸ਼ਿ");}
																else {tb.setText(st13 + "ਸ਼");}
																//end sihari first
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn14.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st14 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st14 = st14.substring(0, st14.length() - 1);
																	tb.setText(st14 + "ਹਿ");}
																else {tb.setText(st14 + "ਹ");}
																//end sihari first
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn14.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st14 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ੜ')
																{tb.setText(st14 + "੍ਹ");}
																else if (c=='ਗ')
																{tb.setText(st14 + "੍ਹ");}
																else if (c=='ਮ')
																{tb.setText(st14 + "੍ਹ");}
																else if (c=='ਰ')
																{tb.setText(st14 + "੍ਹ");}
																else if (c=='ਬ')
																{tb.setText(st14 + "੍ਹ");}
																else if (c=='ਨ')
																{tb.setText(st14 + "੍ਹ");}
																else if (c=='ਲ')
																{tb.setText(st14 + "੍ਹ");}
																else
																{tb.setText(st14 + "");}
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn15.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st15 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st15 = st15.substring(0, st15.length() - 1);
																	tb.setText(st15 + "ਕਿ");}
																else {tb.setText(st15 + "ਕ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn15.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st15 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st15 = st15.substring(0, st15.length() - 1);
																	tb.setText(st15 + "ਖਿ");}
																else {tb.setText(st15 + "ਖ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn16.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st16 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st16 = st16.substring(0, st16.length() - 1);
																	tb.setText(st16 + "ਗਿ");}
																else {tb.setText(st16 + "ਗ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn16.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st16 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st16 = st16.substring(0, st16.length() - 1);
																	tb.setText(st16 + "ਘਿ");}
																else {tb.setText(st16 + "ਘ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn17.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st17 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st17 = st17.substring(0, st17.length() - 1);
																	tb.setText(st17 + "ਚਿ");}
																else {tb.setText(st17 + "ਚ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn17.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st17 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st17 = st17.substring(0, st17.length() - 1);
																	tb.setText(st17 + "ਛਿ");}
																else {tb.setText(st17 + "ਛ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn18.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st18 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st18 = st18.substring(0, st18.length() - 1);
																	tb.setText(st18 + "ਜਿ");}
																else {tb.setText(st18 + "ਜ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn18.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st18 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st18 = st18.substring(0, st18.length() - 1);
																	tb.setText(st18 + "ਝਿ");}
																else {tb.setText(st18 + "ਝ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn19.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st19 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st19 = st19.substring(0, st19.length() - 1);
																	tb.setText(st19 + "ਟਿ");}
																else {tb.setText(st19 + "ਟ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn19.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st19 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st19 = st19.substring(0, st19.length() - 1);
																	tb.setText(st19 + "ਠਿ");}
																else {tb.setText(st19 + "ਠ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn20.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st20 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st20 = st20.substring(0, st20.length() - 1);
																	tb.setText(st20 + "ਡਿ");}
																else {tb.setText(st20 + "ਡ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn20.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st20 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st20 = st20.substring(0, st20.length() - 1);
																	tb.setText(st20 + "ਢਿ");}
																else {tb.setText(st20 + "ਢ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn21.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st21 = tb.getText().toString();
																tb.setText(st21 + "ਣ");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn21.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st21 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st21 = st21.substring(0, st21.length() - 1);
																	tb.setText(st21 + "ਯਿ");}
																else {tb.setText(st21 + "ਯ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn22.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st22 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st22 = st22.substring(0, st22.length() - 1);
																	tb.setText(st22 + "ਤਿ");}
																else {tb.setText(st22 + "ਤ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn22.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st22 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st22 = st22.substring(0, st22.length() - 1);
																	tb.setText(st22 + "ਥਿ");}
																else {tb.setText(st22 + "ਥ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn23.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st23 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st23 = st23.substring(0, st23.length() - 1);
																	tb.setText(st23 + "ਦਿ");}
																else {tb.setText(st23 + "ਦ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn23.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st23 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st23 = st23.substring(0, st23.length() - 1);
																	tb.setText(st23 + "ਧਿ");}
																else {tb.setText(st23 + "ਧ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn24.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st24 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st24 = st24.substring(0, st24.length() - 1);
																	tb.setText(st24 + "ਨਿ");}
																else {tb.setText(st24 + "ਨ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());}});
														btn24.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st24 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ਿ'){tb.setText(st24 + "");}
																else if (c==' '){tb.setText(st24 + "");}
																else if (c=='ਂ'){tb.setText(st24 + "");}
																else if (c=='ੰ'){tb.setText(st24 + "");}
																else if (c=='ੱ'){tb.setText(st24 + "");}
															////auto correct/////////////
																/*else if (c=='ਿ') {tb.setText(st24 + "ਿੰ");}*/
																////////
																//else if (c=='ਂ'){tb.setText(st24 + "");}
																
																else  {tb.setText(st24 + "ਂ");}}
																else  {tb.setText(st24 + "ਂ");}
																tb.setSelection(tb.getText().length());
																hide();	return true;}});
														 // 
														btn25.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st25 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st25 = st25.substring(0, st25.length() - 1);
																	
																	tb.setText(st25 + "ਪਿ");}
																else {tb.setText(st25 + "ਪ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn25.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st25 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st25 = st25.substring(0, st25.length() - 1);
																	tb.setText(st25 + "ਫਿ");}
																else {tb.setText(st25 + "ਫ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn26.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st26 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st26 = st26.substring(0, st26.length() - 1);
																	tb.setText(st26 + "ਬਿ");}
																else {tb.setText(st26 + "ਬ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn26.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st26 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st26 = st26.substring(0, st26.length() - 1);
																	tb.setText(st26 + "ਭਿ");}
																else {tb.setText(st26 + "ਭ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();return true;}});
														 // 
														btn27.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st27 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st27 = st27.substring(0, st27.length() - 1);
																	tb.setText(st27 + "ਮਿ");}
																else {tb.setText(st27 + "ਮ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();}});
														
														btn27.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st27 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ੇ'){tb.setText(st27 + "");}
																else if (c=='ੀ'){tb.setText(st27 + "");}
																else if (c=='ੈ'){tb.setText(st27 + "");}
																else if (c=='ੋ'){tb.setText(st27 + "");}
																else if (c=='ੌ'){tb.setText(st27 + "");}
																else if (c=='ੰ'){tb.setText(st27 + "");}
																else if (c==' '){tb.setText(st27 + "");}
																else if (c=='ਂ'){tb.setText(st27 + "");}
																else if (c=='ੱ'){tb.setText(st27 + "");}
																else if ((c=='ਿ') && (c-1=='ਂ')){tb.setText(st27 + "ਿੰ");}
																/*////auto correct/////////////
																else if ((c=='ਿ') && (c-1=='ਂ')){tb.setText(st27 + "ਿੰ");}
																////////
																 
*/																else  {tb.setText(st27 + "ੰ");}}
																else  {tb.setText(st27 + "ੰ");}
																tb.setSelection(tb.getText().length());
																hide();	return true;}});
														 // 
														btn28.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st28 = tb.getText().toString();
																if (st28 == null || st28.length() == 0) {
																} else {
																	st28 = st28.substring(0, st28.length() - 1);
																}
																tb.setText(st28);
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();		}});
														 // 
														btn29.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st29 = tb.getText().toString();
																tb.setText(" ");
																helpbtn1.setText("");
																helpbtn2.setText("");
																helpbtn3.setText("");
																helpbtn4.setText("");
																helpbtn5.setText("");
																
																
																
															//	tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														 // 
														btn30.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st30 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st30 = st30.substring(0, st30.length() - 1);
																	tb.setText(st30 + "ਰਿ");}
																else {tb.setText(st30 + "ਰ");}
																//end sihari first;
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();}});
														btn30.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st30 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if ((c=='ਸ')||(c=='ਕ')||(c=='ਗ')||(c=='ਟ')||(c=='ਡ')||(c=='ਤ')||(c=='ਥ')||(c=='ਦ')||(c=='ਧ')||(c=='ਨ')||(c=='ਪ')||(c=='ਫ')||(c=='ਬ')||(c=='ਭ')||(c=='ਮ')||(c=='ਵ')||(c=='ਸ਼')||(c=='ਫ਼'))
																{tb.setText(st30 + "੍ਰ");}
																else
																{tb.setText(st30 + "");}
																
																return true;}});
														 // 
														btn31.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st31 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st31 = st31.substring(0, st31.length() - 1);
																	tb.setText(st31 + "ਲਿ");}
																else {tb.setText(st31 + "ਲ");}
																//end sihari first;
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());}});
														/////
														btn32.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st32 = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																	st32 = st32.substring(0, st32.length() - 1);
																	tb.setText(st32 + "ਵਿ");}
																else {tb.setText(st32 + "ਵ");}
																//end sihari first;
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());}});
														btn32.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st32 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if ((c=='ਸ')||(c=='ਖ')||(c=='ਜ')||(c=='ਟ')||(c=='ਤ')||(c=='ਥ')||(c=='ਦ')||(c=='ਖ'))
																{tb.setText(st32 + "੍ਵ");}
																else
																{tb.setText(st32 + "");}
																
																return true;}});
														 //
													/////
														btn32b.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st32b = tb.getText().toString();
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਿ'){
																st32b = st32b.substring(0, st32b.length() - 1);
																tb.setText(st32b + "ੜਿ");}
																else {tb.setText(st32b + "ੜ");}
																//end sihari first;
																hide();hide1();hide2();

																tb.setSelection(tb.getText().length());}});
														 // 
														btn33.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st33 = tb.getText().toString();
																tb.setText(st33 + "ਙ");
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());}});
														btn33.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st33 = tb.getText().toString();
																tb.setText(st33 + "ਞ");
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn34.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st34 = tb.getText().toString();
																tb.setText(st34 + ".");
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());}});
														btn34.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st34 = tb.getText().toString();
																//tb.setText(st34 + "਼");
																//start sihari first
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (c=='ਖ'){
																	st34 = st34.substring(0, st34.length() - 1);
																	tb.setText(st34 + "ਖ਼");}
																else if (c=='ਸ'){
																	st34 = st34.substring(0, st34.length() - 1);
																	tb.setText(st34 + "ਸ਼");}
																else if (c=='ਜ'){
																	st34 = st34.substring(0, st34.length() - 1);
																	tb.setText(st34 + "ਜ਼");}
																else if (c=='ਗ'){
																	st34 = st34.substring(0, st34.length() - 1);
																	tb.setText(st34 + "ਗ਼");}
																else if (c=='ਲ'){
																	st34 = st34.substring(0, st34.length() - 1);
																	tb.setText(st34 + "ਲ਼");}
																else {tb.setText(st34+ "਼");}
																//end sihari first;
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn35.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st35 = tb.getText().toString();
																tb.setText(st35 + "\n");
																hide();hide1();hide2();
																tb.setSelection(tb.getText().length());}});
														 // 
														
														btn36.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st36 = tb.getText().toString();
																/*tb.setText(st36 + "ਾ");*/
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ਅ'){tb.setText(st36 + "ਆ");}
																else if (c=='ੳ'){tb.setText(st36 + "");}
																//rule 14-15
																else if (c==' '){
																	st36 = st36.substring(0, st36.length() - 0);
																	tb.setText(st36 + "ਆ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st36 = st36.substring(0, st36.length() - 0);
																	tb.setText(st36 + "");}
																//
																else if (c=='ੲ'){tb.setText(st36 + "");}
																else if (c=='ਿ'){tb.setText(st36 + "");}
																
																else if (c=='ੁ'){tb.setText(st36 + "");}
																else if (c=='ੂ'){tb.setText(st36 + "");}
																else if (c=='ੇ'){tb.setText(st36 + "");}
																else if (c=='ੈ'){tb.setText(st36 + "");}
																else if (c=='ੋ'){tb.setText(st36 + "");}
																else if (c=='ੌ'){tb.setText(st36 + "");}
																
																else{tb.setText(st36 + "ਾ");}}
																else{tb.setText(st36 + "ਾ");}
																tb.setSelection(tb.getText().length());
																hide();}	});
														btn36.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st36 = tb.getText().toString();
																//tb.setText(st37 + "ੀ");
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																	if (c=='ੲ'){tb.setText(st36 + "");}
																	else if (c=='ਿ'){tb.setText(st36 + "");}
																	
																	else if (c=='ੁ'){tb.setText(st36 + "");}
																	else if (c=='ੂ'){tb.setText(st36 + "");}
																	else if (c=='ੇ'){tb.setText(st36 + "");}
																	else if (c=='ੈ'){tb.setText(st36 + "");}
																	else if (c=='ੋ'){tb.setText(st36 + "");}
																	else if (c=='ੌ'){tb.setText(st36 + "");}
																//rule 14-15
																else if (c==' '){
																	st36 = st36.substring(0, st36.length() - 0);
																	tb.setText(st36 + "ਆਂ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st36 = st36.substring(0, st36.length() - 0);
																	tb.setText(st36 + "ਆਂ");}
																//
																else{tb.setText(st36 + "ਾਂ");}}
																else{tb.setText(st36 + "ਾਂ");}
																hide();hide1();
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn37.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st37 = tb.getText().toString();
																/*tb.setText(st36 + "ਾ");*/
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ੳ'){tb.setText(st37 + "");}
																else if (c=='ਅ'){tb.setText(st37 + "");}
																else if (c=='ੁ'){tb.setText(st37 + "");}
																else if (c=='ੂ'){tb.setText(st37 + "");}
																else if (c=='ੇ'){tb.setText(st37 + "");}
																else if (c=='ੈ'){tb.setText(st37 + "");}
																else if (c=='ੋ'){tb.setText(st37 + "");}
																else if (c=='ੌ'){tb.setText(st37 + "");}
																
																//
																else{tb.setText(st37 + "ਿ");}}
																else{tb.setText(st37 + "ਿ");}
																tb.setSelection(tb.getText().length());
																hide();}	});
														btn37.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st37 = tb.getText().toString();
																//tb.setText(st37 + "ੀ");
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ੳ'){tb.setText(st37 + "");}
																else if (c=='ਅ'){tb.setText(st37 + "");}
																else if (c=='ੁ'){tb.setText(st37 + "");}
																else if (c=='ੂ'){tb.setText(st37 + "");}
																else if (c=='ੇ'){tb.setText(st37 + "");}
																else if (c=='ੈ'){tb.setText(st37 + "");}
																else if (c=='ੋ'){tb.setText(st37 + "");}
																else if (c=='ੌ'){tb.setText(st37 + "");}
																//rule 14-15
																else if (c==' '){
																	st37 = st37.substring(0, st37.length() - 0);
																	tb.setText(st37 + "ਈ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st37 = st37.substring(0, st37.length() - 0);
																	tb.setText(st37 + "ਈ");}
																//
																else{tb.setText(st37 + "ੀ");}}
																else{tb.setText(st37 + "ੀ");}
																hide();hide1();
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn38.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st38 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{if (c=='ਅ'){tb.setText(st38 + "");}
																else if (c=='ੲ'){tb.setText(st38 + "");}
																//rule 14-15
																else if (c==' ') {
																	st38 = st38.substring(0, st38.length() - 0);
																	tb.setText(st38 + "ਉ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st38 = st38.substring(0, st38.length() - 0);
																	tb.setText(st38 + "ਉ");}
																//
																else{tb.setText(st38 + "ੁ");}}
																else{tb.setText(st38 + "ੁ");}
																tb.setSelection(tb.getText().length());}});
														btn38.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st38 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ਅ'){tb.setText(st38 + "");}
																else if (c=='ੲ'){tb.setText(st38 + "");}
																//rule 14-15
																else if (c==' '){
																	st38 = st38.substring(0, st38.length() - 0);
																	tb.setText(st38 + "ਊ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st38 = st38.substring(0, st38.length() - 0);
																	tb.setText(st38 + "ਊ");}
																//
																else{tb.setText(st38 + "ੂ");}}
																else{tb.setText(st38 + "ੂ");}
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn39.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st38 = tb.getText().toString();
																if (st38 == null || st38.length() == 0) {
																} else {
																	st38 = st38.substring(0, st38.length() - 0);
																}
																tb.setText(st38 + " ");
																tb.setSelection(tb.getText().length());
																hide();hide1();hide2();		}});
														////////////////////////////
														btn40.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st40 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ੳ'){tb.setText(st40 + "");}
																else if (c=='ਅ'){tb.setText(st40 + "");}
																//rule 14-15
																else if (c==' '){
																	st40 = st40.substring(0, st40.length() - 0);
																	tb.setText(st40 + "ਏ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st40 = st40.substring(0, st40.length() - 0);
																	tb.setText(st40 + "ਏ");}
																//
																else{tb.setText(st40 + "ੇ");}}
																else{tb.setText(st40 + "ੇ");}
																hide();
																tb.setSelection(tb.getText().length());}});
														btn40.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st40 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if (f==2)
																{
																if (c=='ੳ'){tb.setText(st40 + "");}
																else if (c=='ੲ'){tb.setText(st40 + "");}
																//rule 14-15
																else if (c==' '){
																	st40 = st40.substring(0, st40.length() - 0);
																	tb.setText(st40 + "ਐ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st40 = st40.substring(0, st40.length() - 0);
																	tb.setText(st40 + "ਐ");}
																//
																else{tb.setText(st40 + "ੈ");}}
																else{tb.setText(st40 + "ੈ");}
																hide();
																tb.setSelection(tb.getText().length());
																return true;}});
														 // 
														btn41.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
																String st41 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if(f==2)
																{
																if (c=='ਅ'){tb.setText(st41 + "");}
																else if (c=='ੲ'){tb.setText(st41 + "");}
																//rule 14-15
																else if (c==' ') {
																	st41 = st41.substring(0, st41.length() - 0);
																	tb.setText(st41 + "ਓ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st41 = st41.substring(0, st41.length() - 0);
																	tb.setText(st41 + "ਓ");}
																//
																else{tb.setText(st41 + "ੋ");}}
																else{tb.setText(st41 + "ੋ");}
																hide();
																tb.setSelection(tb.getText().length());}});
														btn41.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st41 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if(f==2)
																{
																if (c=='ੳ'){
																	tb.setText(st41 + "");}
																else if (c=='ੲ'){
																	tb.setText(st41 + "");}
																//rule 14-15
																else if (c==' ') {
																	st41 = st41.substring(0, st41.length() - 0);
																	tb.setText(st41 + "ਔ");}
																else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
																	st41 = st41.substring(0, st41.length() - 0);
																	tb.setText(st41 + "ਔ");}
																//
																else{
																	tb.setText(st41 + "ੌ");}}
																else{
																	tb.setText(st41 + "ੌ");}
																hide();
																tb.setSelection(tb.getText().length());
																return true;}});
														 // /////////////////////////////////////
														
														 // 
														btn42.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
													
																String st42 = tb.getText().toString();
																int aa=tb.length()-1;
																char c=tb.getText().charAt(aa);
																if(f==2)
																{
																if (c=='ੀ'){tb.setText(st42 + "");}
																else if (c=='ੂ'){tb.setText(st42 + "");}
																else if (c=='ੇ'){tb.setText(st42 + "");}
																else if (c=='ਾ'){tb.setText(st42 + "");}
																else if (c=='ੋ'){tb.setText(st42 + "");}
																else if (c=='ੌ'){tb.setText(st42 + "");}
																else if (c=='ੱ'){tb.setText(st42 + "");}
																else if (c=='ਂ'){tb.setText(st42 + "");}
																else if (c=='ੰ'){tb.setText(st42 + "");}
																
																else  {tb.setText(st42 + "ੱ");}
																}
																else  {tb.setText(st42 + "ੱ");}
																tb.setSelection(tb.getText().length());
																hide();	}});
														btn42.setOnLongClickListener(new OnLongClickListener() {
															public boolean onLongClick(View v) {
																String st42 = tb.getText().toString();
																tb.setText(st42 + "।");
																tb.setSelection(tb.getText().length());
																return true;}});
														
														btn43a.setOnClickListener(new OnClickListener() {
															public void onClick(View v) {
//																f=2;
//																hide3();
																 alertdialog=new AlertDialog.Builder(KeyboardActivity.this).create();
													    		System.out.println("check connection");
													    		
													    		alertdialog.setTitle(getResources().getString(R.string.app_name));
													    		
													    		
													    		View view=KeyboardActivity.this.getLayoutInflater().inflate(R.layout.dialoglayout, null);
													    		alertdialog.setView(view);
													    		
													    		
													    		Button firstbtn=(Button)view.findViewById(R.id.firstbutton);
													    		firstbtn.setTypeface(font);
													    		
													    		firstbtn.setOnClickListener(new OnClickListener() {
																	
																	public void onClick(View v) {
																		// TODO Auto-generated method stub
																		f=2;
																		Toast.makeText(getApplicationContext(),
																				"VALID SPELL FACILITY IS ACTIVATED", 0).show();
																		alertdialog.dismiss();
																	}
																});
													    		Button secondbtn=(Button)view.findViewById(R.id.secondbutton);
													    		secondbtn.setTypeface(font);
													    		secondbtn.setOnClickListener(new OnClickListener() {
																	
																	public void onClick(View v) {
																		// TODO Auto-generated method stub
																		f=4;
																		Toast.makeText(getApplicationContext(),
																				"VALID SPELL FACILITY IS DEACTIVATED", 0).show();
																		alertdialog.dismiss();
																	}
																});
													    		////////////////////////////////////////GURMUKHI NUMBERLS ACTIVATED	
													    		Button thirdbtn=(Button)view.findViewById(R.id.thirdbutton);
													    		thirdbtn.setTypeface(font);
													    		thirdbtn.setOnClickListener(new OnClickListener() {
																	
																	public void onClick(View v) {
																		// TODO Auto-generated method stub
																		btn1.setVisibility(View.VISIBLE);
																		btn2.setVisibility(View.VISIBLE);
																		btn3.setVisibility(View.VISIBLE);
																		btn4.setVisibility(View.VISIBLE);
																		btn5.setVisibility(View.VISIBLE);
																		btn6.setVisibility(View.VISIBLE);
																		btn7.setVisibility(View.VISIBLE);
																		btn8.setVisibility(View.VISIBLE);
																		btn9.setVisibility(View.VISIBLE);
																		
																		
																		btn1a.setVisibility(View.GONE);
																		btn2a.setVisibility(View.GONE);
																		btn3a.setVisibility(View.GONE);
																		btn4a.setVisibility(View.GONE);
																		btn5a.setVisibility(View.GONE);
																		btn6a.setVisibility(View.GONE);
																		btn7a.setVisibility(View.GONE);
																		btn8a.setVisibility(View.GONE);
																		btn9a.setVisibility(View.GONE);
																		////////
																		/*btn1.setTypeface(font);
																		btn2.setTypeface(font);
																		btn3.setTypeface(font);
																		btn4.setTypeface(font);
																		btn5.setTypeface(font);
																		btn6.setTypeface(font);
																		btn7.setTypeface(font);
																		btn8.setTypeface(font);
																		btn9.setTypeface(font);
																		btn10.setTypeface(font);
																		*/
																		Toast.makeText(getApplicationContext(),
																				"GURMUKHI NUMBERLS ACTIVATED", 0).show();
																		alertdialog.dismiss();
																	}
																});
													    		////////////////////////////////////////ROMAN NUMBERLS ACTIVATED
													    		Button fourthbtn=(Button)view.findViewById(R.id.fourthbutton);
													    		fourthbtn.setTypeface(font);
													    		fourthbtn.setOnClickListener(new OnClickListener() {
																	
																	public void onClick(View v) {
																		// TODO Auto-generated method stub
																		btn1.setVisibility(View.GONE);
																		btn2.setVisibility(View.GONE);
																		btn3.setVisibility(View.GONE);
																		btn4.setVisibility(View.GONE);
																		btn5.setVisibility(View.GONE);
																		btn6.setVisibility(View.GONE);
																		btn7.setVisibility(View.GONE);
																		btn8.setVisibility(View.GONE);
																		btn9.setVisibility(View.GONE);
																		
																		
																		btn1a.setVisibility(View.VISIBLE);
																		btn2a.setVisibility(View.VISIBLE);
																		btn3a.setVisibility(View.VISIBLE);
																		btn4a.setVisibility(View.VISIBLE);
																		btn5a.setVisibility(View.VISIBLE);
																		btn6a.setVisibility(View.VISIBLE);
																		btn7a.setVisibility(View.VISIBLE);
																		btn8a.setVisibility(View.VISIBLE);
																		btn9a.setVisibility(View.VISIBLE);
																		/////
																		/*btn1a.setTypeface(font1);
																		btn2a.setTypeface(font1);
																		btn3a.setTypeface(font1);
																		btn4a.setTypeface(font1);
																		btn5a.setTypeface(font1);
																		btn6a.setTypeface(font1);
																		btn7a.setTypeface(font1);
																		btn8a.setTypeface(font1);
																		btn9a.setTypeface(font1);
																		btn10.setTypeface(font1);
																		*/
																		
																		Toast.makeText(getApplicationContext(),
																				"ROMAN NUMBERLS ACTIVATED", 0).show();
																		alertdialog.dismiss();
																	}
																});
													    		
													    		
													    		
													    		alertdialog.show();
																}});

/* // duplicate highlight buttons 24a,27a,36a,37a,38a,40a,41a,42a////////////*/		
		
		btn27a.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				String st27a = tb.getText().toString();
				tb.setText(st27a + "ੰ");
				
				tb.setSelection(tb.getText().length());
				return true;
			}
		});
		 // 
		btn36a.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String st36 = tb.getText().toString();
				/*tb.setText(st36 + "ਾ");*/
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				if (f==2)
				{
				if (c=='ਅ'){
					st36 = st36.substring(0, st36.length() - 1);
					tb.setText(st36 + "ਆ");}
				else if (c=='ੳ'){tb.setText(st36 + "");}
				//rule 14-15
				else if (c==' '){
					st36 = st36.substring(0, st36.length() - 0);
					tb.setText(st36 + "ਆ");}
				else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
					st36 = st36.substring(0, st36.length() - 0);
					tb.setText(st36 + "ਆ");}
				//
				else if (c=='ੲ'){tb.setText(st36 + "");}
				else if (c=='ਿ'){tb.setText(st36 + "");}
				
				else if (c=='ੁ'){tb.setText(st36 + "");}
				else if (c=='ੂ'){tb.setText(st36 + "");}
				else if (c=='ੇ'){tb.setText(st36 + "");}
				else if (c=='ੈ'){tb.setText(st36 + "");}
				else if (c=='ੋ'){tb.setText(st36 + "");}
				else if (c=='ੌ'){tb.setText(st36 + "");}
				
				else{tb.setText(st36 + "ਾ");}}
				else{tb.setText(st36 + "ਾ");}
				tb.setSelection(tb.getText().length());
				hide();}	});
		btn36a.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				String st36 = tb.getText().toString();
				//tb.setText(st37 + "ੀ");
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				if (f==2)
				{
					if (c=='ਅ'){
						st36 = st36.substring(0, st36.length() - 1);
						tb.setText(st36 + "ਆਂ");}
					else if (c=='ੲ'){tb.setText(st36 + "");}
					else if (c=='ਿ'){tb.setText(st36 + "");}
					
					else if (c=='ੁ'){tb.setText(st36 + "");}
					else if (c=='ੂ'){tb.setText(st36 + "");}
					else if (c=='ੇ'){tb.setText(st36 + "");}
					else if (c=='ੈ'){tb.setText(st36 + "");}
					else if (c=='ੋ'){tb.setText(st36 + "");}
					else if (c=='ੌ'){tb.setText(st36 + "");}
				//rule 14-15
				else if (c==' '){
					st36 = st36.substring(0, st36.length() - 0);
					tb.setText(st36 + "ਆਂ");}
				else if ((c=='ਾ')||(c=='ਿ')||(c=='ੀ')||(c=='ੁ')||(c=='ੂ')||(c=='ੇ')||(c=='ੈ')||(c=='ੋ')||(c=='ੌ')||(c=='ਂ')||(c=='ੰ')||(c=='ੱ')){
					st36 = st36.substring(0, st36.length() - 0);
					tb.setText(st36 + "ਆਂ");}
				//
				else{tb.setText(st36 + "ਾਂ");}}
				else{tb.setText(st36 + "ਾਂ");}
				hide();hide1();
				tb.setSelection(tb.getText().length());
				return true;}});
		 // 
		btn37a.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String st37a = tb.getText().toString();
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				
				if (c=='ੲ')
				{
					st37a = st37a.substring(0, st37a.length() - 1);
					tb.setText(st37a + "ਇ");
					}
				else 
					tb.setText(st37a + "ਿ");
				tb.setSelection(tb.getText().length());
			}
		});
		btn37a.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				String st37a = tb.getText().toString();
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				
				if (c=='ੲ')
				{
					st37a = st37a.substring(0, st37a.length() - 1);
					tb.setText(st37a + "ਈ");
					}
				else 
					tb.setText(st37a + "ੀ");
				tb.setSelection(tb.getText().length());
				return true;
			}
		});
		 // 
		btn38a.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String st38a = tb.getText().toString();
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				tb.setText(st38a + "ੁ");
				if (c=='ੳ')
				{
					st38a = st38a.substring(0, st38a.length() - 1);
					tb.setText(st38a + "ਉ");
					}
				tb.setSelection(tb.getText().length());
			}
		});
		btn38a.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				String st38a = tb.getText().toString();
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				
				if (c=='ੳ')
				{
					st38a = st38a.substring(0, st38a.length() - 1);
					tb.setText(st38a + "ਊ");
					}
				else
					tb.setText(st38a + "ੂ");
				tb.setSelection(tb.getText().length());
				
				return true;
			}
		});
		// 
				btn40a.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						String st40a = tb.getText().toString();
						int aa=tb.length()-1;
						char c=tb.getText().charAt(aa);
						 if (c=='ਅ'){
							tb.setText(st40a + "");}
						 else if (c=='ੲ')
						{
							st40a = st40a.substring(0, st40a.length() - 1);
							tb.setText(st40a + "ਏ");
							}
						else 
							tb.setText(st40a + "ੇ");
						tb.setSelection(tb.getText().length());
					}
				});
				btn40a.setOnLongClickListener(new OnLongClickListener() {
					public boolean onLongClick(View v) {
						String st40a = tb.getText().toString();
						int aa=tb.length()-1;
						char c=tb.getText().charAt(aa);
						
						if (c=='ੲ')
						{
							st40a = st40a.substring(0, st40a.length() - 1);
							tb.setText(st40a + "ੲੈ");
							}
						else if (c=='ਅ'){
							st40a = st40a.substring(0, st40a.length() - 1);
							tb.setText(st40a + "ਐ");}
						else 
							tb.setText(st40a + "ੈ");
						tb.setSelection(tb.getText().length());
						return true;
					}
				});
				 // 
		
		btn41a.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				String st41a = tb.getText().toString();
				int aa=tb.length()-1;
				char c=tb.getText().charAt(aa);
				
				if (c=='ਅ')
				{
					st41a = st41a.substring(0, st41a.length() - 1);
					tb.setText(st41a + "ਔ");
					}
				else 
					tb.setText(st41a + "ੌ");
				tb.setSelection(tb.getText().length());
				return true;
			}
		});
		 // /////////////////////////////////////
		 // 
		btn42a.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String st42a = tb.getText().toString();
				tb.setText(st42a + "ੱ");
				
				tb.setSelection(tb.getText().length());
			}
		});
		}
	public void show(){
		btn38.setVisibility(View.GONE);
		btn38a.setVisibility(View.VISIBLE);
		}
	public void hide(){
		btn38.setVisibility(View.VISIBLE);
		btn38a.setVisibility(View.GONE);
		}
	public void show1(){
		btn27.setVisibility(View.GONE);
		btn36.setVisibility(View.GONE);
		btn40.setVisibility(View.GONE);
		btn41.setVisibility(View.GONE);
		btn42.setVisibility(View.GONE);
		btn27a.setVisibility(View.VISIBLE);
		btn36a.setVisibility(View.VISIBLE);
		btn40a.setVisibility(View.VISIBLE);
		btn41a.setVisibility(View.VISIBLE);
		btn42a.setVisibility(View.VISIBLE);
		}
	public void hide1(){
		btn27.setVisibility(View.VISIBLE);
		btn36.setVisibility(View.VISIBLE);
		btn40.setVisibility(View.VISIBLE);
		btn41.setVisibility(View.VISIBLE);
		btn42.setVisibility(View.VISIBLE);
		btn27a.setVisibility(View.GONE);
		btn36a.setVisibility(View.GONE);
		btn40a.setVisibility(View.GONE);
		btn41a.setVisibility(View.GONE);
		btn42a.setVisibility(View.GONE);
		}
	public void show2(){
		btn37.setVisibility(View.GONE);
		btn40.setVisibility(View.GONE);
		btn37a.setVisibility(View.VISIBLE);
		btn40a.setVisibility(View.VISIBLE);
		}
	public void hide2(){
		btn37.setVisibility(View.VISIBLE);
		btn40.setVisibility(View.VISIBLE);
		btn37a.setVisibility(View.GONE);
		btn40a.setVisibility(View.GONE);
		}
	
	private boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	private void sendSMS(String phoneNumber, String message) {
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
		
		SmsManager sms = SmsManager.getDefault();
		 sms.sendTextMessage(phoneNumber, null, message, null, null);
	
	}

	class clicker implements Button.OnClickListener {
		public void onClick(View v) {
			String a;
			a = tb.getText().toString();
			tb.setText(a.toString());
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		aboutLayout.setVisibility(View.VISIBLE);
		
	}
	TextWatcher watcher=new TextWatcher() {
		
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
			
			if(s.toString().trim().length()==0)
				return;
			try
			{
			helpbtn1.setVisibility(View.INVISIBLE);
			helpbtn2.setVisibility(View.INVISIBLE);
			helpbtn3.setVisibility(View.INVISIBLE);
			helpbtn4.setVisibility(View.INVISIBLE);
			helpbtn5.setVisibility(View.INVISIBLE);
			
			String string=s.toString();
			char[] chr=string.toCharArray();
			

			String str=s.toString();
			String tempstr;
			
//			if(s.toString().charAt(s.toString().length()-1)==' ')
//				return;
			if(str.length()>0)
			{
				String st[]=str.split(" ");
				tempstr=st[st.length-1];
				System.out.println(str.length());
				if(st.length>1 && (str.charAt(str.length()-1)==' '))
				{
					System.out.println("Character at "+str.charAt(str.length()-2));
					DatabaseHandler database=new DatabaseHandler(KeyboardActivity.this);
					WordBean  contact=new WordBean();
					contact.setWord(tempstr);
					contact.setFrequency(1);
					ArrayList<WordBean> wordlist=database.checkTempRowExsits(tempstr);
					System.out.println("worldlist "+wordlist.size());
					System.out.println("tempstr "+tempstr);
					database=new DatabaseHandler(KeyboardActivity.this);
					if(wordlist.size()>0)
						database.updateTempWord(contact);
					else
						database.addTempWord(contact);
				
				}
				
				System.out.println("Str "+tempstr);
			DatabaseHandler handler=new DatabaseHandler(KeyboardActivity.this);
			ArrayList< WordBean>  list= handler.getContacts(tempstr);
		if(list.size()>0)
		{
			
			for(int i=0;i<list.size();i++)
				System.out.println(list.get(i).word);
		
		for(int i=0;i<list.size();i++)
		{
			
			if(i==0)
			{
				helpbtn1.setText(list.get(i).word);
				helpbtn1.setVisibility(View.VISIBLE);
							
			}
			else if(i==1)
			{
					helpbtn2.setText(list.get(i).word);
					helpbtn2.setVisibility(View.VISIBLE);
			}
			else if(i==2)	
			{
				helpbtn3.setText(list.get(i).word);
				helpbtn3.setVisibility(View.VISIBLE);
			}
			else if(i==3)	
			{
				helpbtn4.setText(list.get(i).word);
				helpbtn4.setVisibility(View.VISIBLE);
			}
			else if(i==4)	
			{
				helpbtn5.setText(list.get(i).word);
				helpbtn5.setVisibility(View.VISIBLE);
			}
			}
		}
			else
			{}
		
			}
				
		}
			
			catch (Exception e) {
				// TODO: handle exception
			}}
		
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			}};
	OnClickListener listener=new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button b=(Button)v;
			String temp="";
			String str=tb.getText().toString();
			String st[]=str.split(" ");
			st[st.length-1]=b.getText().toString();
			for(int i=0;i<st.length;i++)
				temp+=st[i]+" ";
			tb.setText(temp.trim());
			tb.setSelection(tb.getText().length());
			helpbtn1.setVisibility(View.INVISIBLE);
			helpbtn2.setVisibility(View.INVISIBLE);
			helpbtn3.setVisibility(View.INVISIBLE);
			helpbtn4.setVisibility(View.INVISIBLE);
			helpbtn5.setVisibility(View.INVISIBLE);
			}};
	public void showToast(String str)
	{
		Toast.makeText(KeyboardActivity.this, str, 1000).show();
	}
	public void onDestroy()
	{
		super.onDestroy();
		
		DatabaseHandler handler=new DatabaseHandler(KeyboardActivity.this);
		
		words=handler.getTempWords();
		if(words==null)
			return;
		System.out.println("size of change words"+words.size());
		
		for(int i=0;i<words.size();i++)
		{
			 handler = new DatabaseHandler(this);
			 String str=words.get(i).word;
			ArrayList<WordBean> word= handler.checkWordExists(str);
			if(word==null)
				 Log.e("Update word", ""+handler.updateWord(words.get(i), words.get(i).frequency));
			else
			{	
				int frequency=word.get(0).frequency;
				int frequencytemp=words.get(i).frequency;
					 frequency=frequency+frequencytemp;
					 //update frequency
					Log.e("Update word frequency", ""+handler.updateWordFrequency(word.get(0).get_id(),frequency)); 
			}
			System.out.println(handler.updateWord(words.get(i), words.get(i).frequency));
		}
		System.out.println("rows deleted"+handler.deleteAllTempRows());
		}
	public void getNumber(ContentResolver cr)
	{
		String phoneNumber;
	    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
	            // use the cursor to access the contacts    
	    while (phones.moveToNext())
	    {
	      String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
	             // get display name
	      phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	              // get phone number
	     
	      Contact contact=new Contact();
	    names.add(name);
	      numbers.add(phoneNumber);
	      System.out.println(name+".................."+phoneNumber); 
	    }

	}
	
	class CustomAdapter extends ArrayAdapter<String>
	{

		ArrayList<String> name,pnumber;
		Context mcontext;
		public CustomAdapter(Context context, int resource, ArrayList<String> objects,ArrayList<String> name1) {
			super(context, resource, objects);
			// TODO Auto-generated constructor stub
			mcontext=context;
			name=name1;
			pnumber=objects;
//			for(int i=0;i<name.size();i++)
//				System.out.println(name.get(i).toString()+" "+pnumber.get(i).toString());
		}
		public View getView(int position,View convertview,ViewGroup root)
		{
			if(convertview==null)
				convertview=((Activity)mcontext).getLayoutInflater().inflate(R.layout.customlayout, null);
		
			TextView txtname=(TextView)convertview.findViewById(R.id.name);
			TextView txtnumber=(TextView)convertview.findViewById(R.id.number);
			txtname.setText(name.get(position).toString());
			txtnumber.setText(pnumber.get(position));
			return convertview;
			
		}
	}
}
