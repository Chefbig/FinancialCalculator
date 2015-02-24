package com.thuangster.financialcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity {
    private double total = 0;
    private String operation;
    private int placeValue = 0;
    private static final String CLEAR_BUTTON_TEXT = "CE | C";
    private static final String POS_NEG_BUTTON_TEXT = "+ | -";
    public static final String EXTRA_MESSAGE = "This is a help message.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String bText = button.getText().toString();
        if (isInteger(bText, 10)) {
            int value = Integer.parseInt(bText);
            if (placeValue == 0) {
                total = total + value;
            } else {
                total = 10 * total + value;
            }

            placeValue++;
        } else if (bText.equals(CLEAR_BUTTON_TEXT)) {
            total = 0;
            placeValue = 0;
        } else if (bText.equals(POS_NEG_BUTTON_TEXT)) {
            total = total * -1;
        }
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText(Double.toString(total));

    }

    public void helpMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, EXTRA_MESSAGE);
        startActivity(intent);
    }

    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
