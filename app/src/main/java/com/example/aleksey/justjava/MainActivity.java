package com.example.aleksey.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }






        /**
     * This method is called when the plus button is clicked.
     *
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1){
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.

    */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocCheckbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();


        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just a Java order" + Name());
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity((getPackageManager())) != null) {
            startActivity(intent);
        }
    }




    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }


        if (addChocolate) {
            basePrice = basePrice + 2;

        }

        return quantity * basePrice;
    }

    /**
     * This method creates a summary of the inputs and returns them
     *as a list of String
     *
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {
        String newPriceMessage = getString(R.string.order_summary_name) + Name();
        newPriceMessage += "\nAdd whipped cream?" + addWhippedCream;
        newPriceMessage += "\nAdd Chocolate? " + addChocolate;
        newPriceMessage += "\nQuantity:" + quantity;
        newPriceMessage += "\n" + getString(R.string.total)+ ":$" + price;
        newPriceMessage += "\n" + getString(R.string.thank_you);
        return newPriceMessage;

    }

    public String Name() {
        EditText editText = (EditText) findViewById(R.id.name_input);
        String Name = editText.getText().toString();
        return Name;
    }
    /**


    }
     */
    private void displayQuantity(int numberOfCups) {
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCups);
    }






}





























