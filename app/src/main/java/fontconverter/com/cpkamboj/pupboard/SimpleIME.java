package fontconverter.com.cpkamboj.pupboard;

//ਸਾਡੀ ਪਹਿਲੀ ਸਮੱਸਿਆ
//English+Shift ਤੇ Inscript ਲੇਆਊਟ ਆ ਰਿਹਾ ਹੈ
//ਇਸ ਦੀ ਥਾਂ 'ਤੇ English+Upper Case ਵਾਲਾ ਲੇਆਊਟ ਆਉਣਾ ਚਾਹੀਦਾ ਹੈ ਜੀ
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.ListView;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

public class SimpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;
    private ListView myNames;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    private boolean caps=false;
    //for inscript shift
    private boolean inscript_shift = false;
    //for phonetic shift
    private boolean phonetic_shift = false;
    //for remington shift
    private boolean remington_shift = false;
    //for english shift
   private boolean english_shift=false;


    Charset utf8;
    Charset def;
    String message=null;
    String charToPrint;
    int oldCode=0;
    boolean stag=false;

    @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            InputConnection ic = getCurrentInputConnection();
            playClick(primaryCode);

            if(stag)
            {

                System.err.println("inside if" +stag);


//ਪ੍ਰਿੰਸੀਪਲ principal
            /*    stag=false;
                char code = (char)primaryCode; //ਪ --- 0A2A    --- 2602
                char code1 = (char)primaryCode+1;  //੍ ---  0A4D  ---  2637
                char code2 = (char)primaryCode+2;  //ਰ --- 0A30  ---  2608
                char code3= (char)2623;
                ic.commitText(String.valueOf(code)+String.valueOf(code1),+String.valueOf(code2),+String.valueOf(code3),1);
*/

               stag=false;
                char code = (char)primaryCode;
                char code2= (char)2623;
               ic.commitText(String.valueOf(code)+String.valueOf(code2),1);
                 }
            else {

                System.err.println("else if stag " +stag  +"and primaryCode "+primaryCode);
                oldCode = primaryCode;
                switch (primaryCode) {



                    case 2003801:

                        keyboard = new Keyboard(this, R.xml.inscript);
                        kv.setKeyboard(keyboard);
                        editor = sharedpreferences.edit();
                        editor.putString("keyboard", "inscript");
                        editor.commit();
                        break;

                    case 2003802:
                        keyboard = new Keyboard(this, R.xml.phonetic);
                        kv.setKeyboard(keyboard);
                         editor = sharedpreferences.edit();

                        editor.putString("keyboard", "phonetic");
                        editor.commit();
                        break;

                    case 2003803:
                        keyboard = new Keyboard(this, R.xml.remington);
                        kv.setKeyboard(keyboard);
                        editor = sharedpreferences.edit();

                        editor.putString("keyboard", "remington");
                        editor.commit();
                        break;

                    case 2003804:

                        editor = sharedpreferences.edit();
                        editor.putString("keyboard", "english");
                        editor.commit();
                        keyboard = new Keyboard(this, R.xml.english);
                        kv.setKeyboard(keyboard);
                        break;



                    case 2003806:
                        Intent i = new Intent();
                        i.setClass(this, MyWebView.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        break;

                    case Keyboard.KEYCODE_DELETE:
                        ic.deleteSurroundingText(1, 0);
                        break;

                    case 70002:
                        keyboard = new Keyboard(this, R.xml.phonetic);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                       break;

                    case 70003:
                        keyboard = new Keyboard(this, R.xml.remington);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;
                    case 70004:
                        keyboard = new Keyboard(this, R.xml.english);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;
/////////////
                    case 60002:
                        keyboard = new Keyboard(this, R.xml.phonetic_alt);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;

                    case 60003:
                        keyboard = new Keyboard(this, R.xml.remington_alt);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;

                    case 60004:
                        keyboard = new Keyboard(this, R.xml.english_alt);
                        kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;
                        ////////////////
                    case 50002:
                        System.err.println("inside phonetic shift");
                        phonetic_shift = !phonetic_shift;
                        if (phonetic_shift) {

                            keyboard = new Keyboard(this, R.xml.phonetic_shift);
                            kv.setKeyboard(keyboard);
                        } else {
                            keyboard = new Keyboard(this, R.xml.phonetic);
                            kv.setKeyboard(keyboard);
                        }
                        //kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;

                    case 50003:

                        remington_shift = !remington_shift;
                        if (remington_shift) {
                            System.err.println("inside remington  shift");
                            keyboard = new Keyboard(this, R.xml.remington_shift);
                            kv.setKeyboard(keyboard);
                        } else {
                            System.err.println("inside remington");
                            keyboard = new Keyboard(this, R.xml.remington);
                            kv.setKeyboard(keyboard);
                        }
                        //kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;

                    case 50004:

                        english_shift = !english_shift;
                        if (english_shift) {
                            System.err.println("inside english  shift");
                            keyboard = new Keyboard(this, R.xml.english_shift);
                            kv.setKeyboard(keyboard);
                        } else {
                            System.err.println("inside english");
                            keyboard = new Keyboard(this, R.xml.english);
                            kv.setKeyboard(keyboard);
                        }
                        //kv.setKeyboard(keyboard);
                        kv.invalidateAllKeys();
                        break;



                  case Keyboard.KEYCODE_SHIFT:
                      SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                      String restoredText = prefs.getString("keyboard", null);

                      if (restoredText.equalsIgnoreCase("english")) {

                          keyboard = new Keyboard(this, R.xml.english_shift);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(true);
                          kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "english_shift");
                          editor.commit();

                      }
                      else if(restoredText.equalsIgnoreCase("english_shift"))
                      {
                          keyboard = new Keyboard(this, R.xml.english);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(false);
                          kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "english");
                          editor.commit();
                      }



                     else if (restoredText.equalsIgnoreCase("inscript")) {

                          keyboard = new Keyboard(this, R.xml.inscript_shift);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(true);
                          kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "inscript_shift");
                          editor.commit();
                      }
                      else if(restoredText.equalsIgnoreCase("inscript_shift"))
                      {
                          keyboard = new Keyboard(this, R.xml.inscript);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(false);
                         kv.invalidateAllKeys();
                           editor = sharedpreferences.edit();
                          editor.putString("keyboard", "inscript");
                          editor.commit();
                      }

                      else if(restoredText.equalsIgnoreCase("phonetic"))
                      {
                          keyboard = new Keyboard(this, R.xml.phonetic_shift);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(true);
                          kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "phonetic_shift");
                          editor.commit();
                      }
                      else if(restoredText.equalsIgnoreCase("phonetic_shift"))
                      {
                          keyboard = new Keyboard(this, R.xml.phonetic);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(false);
                          kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "phonetic");
                          editor.commit();
                      }
                      else if(restoredText.equalsIgnoreCase("remington"))
                      {
                          keyboard = new Keyboard(this, R.xml.remington_shift);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(true);
                          kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "remington_shift");
                          editor.commit();
                      }
                      else if(restoredText.equalsIgnoreCase("remington_shift"))
                      {
                          keyboard = new Keyboard(this, R.xml.remington);
                          System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                          kv.setKeyboard(keyboard);
                          keyboard.setShifted(false);
                         kv.invalidateAllKeys();
                          editor = sharedpreferences.edit();
                          editor.putString("keyboard", "remington");
                          editor.commit();
                      }
                       break;

                    case 50001:
                        System.err.println("inside shift");
                        inscript_shift = !inscript_shift;
                        if (inscript_shift) {
                            keyboard = new Keyboard(this, R.xml.inscript_shift);
                            System.err.println("caps pressed"+caps+" keyboard : "+keyboard+" kv : "+kv);
                            kv.setKeyboard(keyboard);
                            keyboard.setShifted(true);
                            kv.invalidateAllKeys();
                        } else {
                            keyboard = new Keyboard(this, R.xml.inscript);
                            kv.setKeyboard(keyboard);
                        }
                        kv.invalidateAllKeys();
                        break;

                    case Keyboard.KEYCODE_DONE:
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                        break;

                     //added for english alt key starts
                    case 550:
                        System.err.println("inside alt  550");
                        keyboard = new Keyboard(this, R.xml.english_alt);
                        kv.setKeyboard(keyboard);
                        break;

                     //added for english alt keys ends

                    case 44:

                        System.err.println("inside shift 456");
                        keyboard = new Keyboard(this, R.xml.inscript_alt);
                        kv.setKeyboard(keyboard);
                        break;
                    case 440:
                        System.err.println("inside shift 456");
                        keyboard = new Keyboard(this, R.xml.inscript);
                        kv.setKeyboard(keyboard);
                        break;


                    case 2637:
                        utf8 = Charset.forName("UTF-8");
                        def = Charset.defaultCharset();
                        message = null;
                        //
                        //charToPrint = "\u0A4D\u0A30";
                        charToPrint = "\u0A4D\u0A30";
                        try {
                            byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());
                            ic.commitText(message, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
//
                    case 2617000:
                        utf8 = Charset.forName("UTF-8");
                        def = Charset.defaultCharset();
                        message = null;
                        charToPrint = "\u0A4D\u0A39";
                        try {
                            byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());
                            ic.commitText(message, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    //
                ////Alt cases
                    case 2602000:
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A2A\u0A40";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;

                        ////
                        case 2608200://.com
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u002E\u0063\u006F\u006D";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    ////
                    case 2608300://.in
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u002E\u0069\u006E";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    ////
                    case 2608400://tab
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "     ";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    ////
                    case 2608500://,
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u002C";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    ////
                    case 2608600://.co
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0063\u006F";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    ////
                    case 2608700://'
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0027";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    ////
                    case 2608701://ਾਂ
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A3E\u0A02";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    case 2608702://੍ਹ
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A4D\u0A39";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    case 2608703://ਪੈਰ ਵਾਵਾ
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A3D\u0A35";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                    ////
                    case 2616000:
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A38\u0A40";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                        ///
                    case 2581000:
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A15\u0A70\u0A2C\u0A4B\u0A1C";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                        ////
                    case 2607000:
                        utf8 = Charset.forName("UTF-8");def = Charset.defaultCharset();message = null;
                        charToPrint = "\u0A2A\u0A70\u0A1C\u0A3E\u0A2C\u0A40\u0020\u0A2F\u0A42\u0A28\u0A40\u0A35\u0A30\u0A38\u0A3F\u0A1F\u0A40\u0020\u0A2A\u0A1F\u0A3F\u0A06\u0A32\u0A3E";
                        try {byte[] bytes = charToPrint.getBytes("UTF-8");
                            message = new String(bytes, def.name());ic.commitText(message, 1);
                        } catch (Exception e) {e.printStackTrace();}break;
                        ////

                    case 2623:
                        System.err.println("2623 if " +stag);
                        stag = true;
                        break;

                    default:


                        System.err.println("inside shift  123");
                        char code = (char) primaryCode;
                        if (Character.isLetter(code) && caps) {
                            code = Character.toUpperCase(code);
                        }

/*
                        Drawable myIcon = getResources().getDrawable( R.drawable.ic_launcher_background);


                        List<Keyboard.Key> keys = keyboard.getKeys();
                        for(Keyboard.Key key: keys) {
                            if(key.label != null)
                                //key.label="123456";
                                 key.icon=myIcon;
                        }
*/

                        ic.commitText(String.valueOf(code), 1);
                        CharSequence charSequence=ic.getTextBeforeCursor(2,0);
                        System.err.println("code is "+charSequence.toString());
                }
            }
        }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.inscript);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);

        //setting defalut keyboard inscript
        sharedpreferences= getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("keyboard", "inscript");
        editor.commit();
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }


    public String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }
        System.out.println("Decimal : " + temp.toString());

        return sb.toString();
    }
}
