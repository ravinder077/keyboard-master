package pu.punjabimobiletypingtool;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "WordList";

	// Contacts table name
	private static final String TABLE_CONTACTS = "Dictionary";

	private static final String TABLE_TEMP = "TempWord";
	
	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String Word = "word";
	private static final String Frequency = "frequency";

	Context mcontext;
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	
		
		mcontext=context;
		
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF  EXISTS " + TABLE_CONTACTS);
		
		
		try
		{
		
		String create_table="create table Dictionary(id INTEGER PRIMARY KEY,word text,frequency integer)";
		db.execSQL(create_table);
	//	Toast.makeText(mcontext, "created", 1000).show();
		System.out.println(" Inserted data");
		
		
		
		String create_temp_table="create table TempWord(id INTEGER PRIMARY KEY,word text,frequency integer)";
		db.execSQL(create_temp_table);
		
		}
		catch(Exception e){
			System.out.println(" Error "+e.getMessage());
		//	Toast.makeText(mcontext, "created not", 1000).show();
		}
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		//onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addWord(WordBean contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		
		ContentValues values = new ContentValues();
		
		
		
		values.put(Word, contact.getWord()); // Contact Name
		values.put(Frequency, contact.getFrequency()); // Contact Phone

		
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}
	int updateWord(WordBean contact,int frequency) {
		SQLiteDatabase db = this.getWritableDatabase();

		
		ContentValues values = new ContentValues();
		
		
		
		values.put(Word, contact.getWord()); // Contact Name
		 // Contact Phone

		System.out.println("Update method ");
		// Inserting Row
		int a=db.update(TABLE_CONTACTS, values, Frequency + " = ?",
				new String[] { String.valueOf(frequency) });

		db.close(); // Closing database connection
		return a;
	}
	int updateWordFrequency(int id,int frequency) {
		SQLiteDatabase db = this.getWritableDatabase();

		
		ContentValues values = new ContentValues();
		
		
		
		values.put(Frequency, frequency); // Contact Name
		 // Contact Phone

		System.out.println("Update method ");
		// Inserting Row
		int a=db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		db.close(); // Closing database connection
		return a;
	}
	
	void addTempWord(WordBean word) {
		SQLiteDatabase db = this.getWritableDatabase();

		
		ContentValues values = new ContentValues();
		
		
		
		values.put(Word, word.getWord()); // Contact Name
		values.put(Frequency, word.getFrequency()); // Contact Phone

		
		// Inserting Row
		db.insert(TABLE_TEMP, null, values);
		db.close(); // Closing database connection
	}
	
	
	
	
	// Getting single contact
	WordBean getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				Word, Frequency }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		WordBean world=new WordBean();
		world.set_id(Integer.parseInt(cursor.getString(0)));
		world.setWord(cursor.getString(1));
		world.setFrequency(Integer.parseInt(cursor.getString(2)));
		
		cursor.close();
		
		return world;
	}
	WordBean getTempWord(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_TEMP, new String[] { KEY_ID,
				Word, Frequency }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		WordBean world=new WordBean();
		world.set_id(Integer.parseInt(cursor.getString(0)));
		world.setWord(cursor.getString(1));
		world.setFrequency(Integer.parseInt(cursor.getString(2)));
		// return contact
		cursor.close();
		return world;
	}
	
	
	
	
	// Getting All Contacts
	public List<WordBean> getAllContacts() {
		List<WordBean> contactList = new ArrayList<WordBean>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				WordBean contact = new WordBean();
				contact.set_id(Integer.parseInt(cursor.getString(0)));
				contact.setWord(cursor.getString(1));
				contact.setFrequency(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		// return contact list
		return contactList;
		//12552191000369
	}
	public List<WordBean> getAllTempWords() {
		List<WordBean> contactList = new ArrayList<WordBean>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TEMP;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				WordBean contact = new WordBean();
				contact.set_id(Integer.parseInt(cursor.getString(0)));
				contact.setWord(cursor.getString(1));
				contact.setFrequency(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		// return contact list
		return contactList;
		//12552191000369
	}

	
	
	public ArrayList<WordBean> getContacts(String slw) {
		ArrayList<WordBean> contactList = new ArrayList<WordBean>();
		// Select All Query
		//String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+" ";

		String selectQuery="Select * from Dictionary where word like '"+ slw + "%' order by frequency desc LIMIT 0,5";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				WordBean contact = new WordBean();
				contact.set_id(Integer.parseInt(cursor.getString(0)));
				contact.setWord(cursor.getString(1));
				contact.setFrequency(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		
		// return contact list
		return contactList;
	}
	public ArrayList<WordBean> checkWordExists(String slw) {
		ArrayList<WordBean> contactList = new ArrayList<WordBean>();
		// Select All Query
		//String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+" ";

		String selectQuery="Select * from Dictionary where word like '"+ slw + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if(cursor.getCount()==0)
			return null;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				WordBean contact = new WordBean();
				contact.set_id(Integer.parseInt(cursor.getString(0)));
				contact.setWord(cursor.getString(1));
				contact.setFrequency(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		
		// return contact list
		return contactList;
	}
	
	
	

	// Updating single contact
//	public int updateContact(Contact contact) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_NAME, contact.getName());
//		values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//		// updating row
//		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
//				new String[] { String.valueOf(contact.getID()) });
//	}

	// Deleting single contact
//	public void deleteContact(Contact contact) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//				new String[] { String.valueOf(contact.getID()) });
//		db.close();
//	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	@SuppressWarnings("finally")
	public boolean checkTableExists()
	{
		Cursor cursor = null;
		SQLiteDatabase db = null;
		
		String query="SELECT count(*) FROM sqlite_master WHERE type='table' AND name='Dictionary'";
		 db = this.getReadableDatabase();
		 cursor=db.rawQuery(query, null);
		 System.out.println("check table exists "+cursor.getColumnCount()+" "+cursor.getCount()+" ");
		 
		if (cursor.getCount()>0)
	    {
			cursor.close();
			
	        return true;
	    }
		else
		{
			cursor.close();
			return false;
		}
			
		
		
	}
	public ArrayList<WordBean> checkTempRowExsits(String str)
	{
		ArrayList<WordBean> contactList = new ArrayList<WordBean>();
		String selectQuery="Select * from TempWord where word like '"+str+ "' ";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				WordBean contact = new WordBean();
				contact.set_id(Integer.parseInt(cursor.getString(0)));
				contact.setWord(cursor.getString(1));
				contact.setFrequency(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		
		// return contact list
		return contactList;

	}
	public int updateTempWord(WordBean bean)
	{
		int id = 0,frequency = 0 ;
		String selectQuery="Select *  from TempWord where word like '"+bean.getWord()+ "'";
		SQLiteDatabase database=this.getWritableDatabase();
		
		Cursor cursor=database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst())
		{
			id=cursor.getInt(0);
			frequency=cursor.getInt(2);
			System.out.println("frequency "+frequency);
		}
		
		frequency++;
				ContentValues values = new ContentValues();
				values.put(Word, bean.getWord()); // Contact Name
				values.put(Frequency, frequency); // Contact Phone

				cursor.close();
				int a=database.update(TABLE_TEMP, values, KEY_ID + " = ?",
						new String[] { String.valueOf(id) });
//				// updating row
				database.close();
				return a;
		//
		
		
		
	}
	public ArrayList<WordBean> getTempWords()
	{
		
		ArrayList<WordBean> words=new ArrayList<WordBean>();
		String selectQuery="Select *  from TempWord where frequency >=3";
		SQLiteDatabase database=this.getWritableDatabase();
		
		Cursor cursor=database.rawQuery(selectQuery, null);
		System.out.println("Rows "+cursor.getCount());
		if(cursor.getCount()==0)
		{
			cursor.close();
			database.close();
			return null;
		}
		if(cursor!=null)
			cursor.moveToFirst();
		
		do
		{
			WordBean bean=new WordBean();
			bean.set_id(cursor.getInt(0));
			bean.setWord(cursor.getString(1));
			bean.setFrequency(cursor.getInt(2));
			words.add(bean);
		}while(cursor.moveToNext());
		System.out.println("Rows words "+words.size());
		cursor.close();
		database.close();
		return words;		
	}
	public int deleteAllTempRows()
	{
		String selectQuery="DELETE FROM TempWord";
		SQLiteDatabase database=this.getWritableDatabase();
		
		int a=database.delete("TempWord", null, null);
		database.close();
		return a;
	}
}
