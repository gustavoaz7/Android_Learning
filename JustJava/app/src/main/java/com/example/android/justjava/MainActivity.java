/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    This method is called when the order button is clicked.
    public void submitOrder(View view) {
        displayQuantity(quantity);
        String orderSummaryMessage = createOrderSummary(quantity);
        displayMessage(orderSummaryMessage);
    }

    private int calculatePrice(int price) {
        return price * 4;
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        int price = calculatePrice(quantity);
        displayPrice(price);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
        int price = calculatePrice(quantity);
        displayPrice(price);
    }

//    This method displays the given quantity value on the screen.
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

//    This method displays the given price value on the screen.
    private void displayPrice(int price) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        String message = "$" + price;
        orderSummaryTextView.setText(message);
    }

    private String createOrderSummary(int quantity) {
        int price = calculatePrice(quantity);
        String message = "Name: Guss";
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        message += "\nThank you!";
        return message;
    }

}