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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    boolean whipCream = false;
    boolean chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    This method is called when the order button is clicked.
    public void submitOrder(View view) {
        displayQuantity(quantity);

        int price = calculatePrice(quantity);
        EditText nameField = (EditText) findViewById(R.id.name);
        String name = nameField.getText().toString();
        String orderSummaryMessage = createOrderSummary(quantity, price, name);
        displayMessage(orderSummaryMessage);
    }

    private int calculatePrice(int quantity) {
        int unitPrice = 4;
        if (whipCream) {
            unitPrice += 1;
        }
        if (chocolate) {
            unitPrice += 2;
        }
        return unitPrice * quantity;
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Limit of 100 units per order.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
        int price = calculatePrice(quantity);
        displayPrice(price);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee. ", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
        int price = calculatePrice(quantity);
        displayPrice(price);
    }

//    This method displays the given quantity value on the screen.
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
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

    public void toggleWhipCream(View view) {
        whipCream = !whipCream;
        int price = calculatePrice(quantity);
        displayPrice(price);
    }

    public void toggleChocolate(View view) {
        chocolate = !chocolate;
        int price = calculatePrice(quantity);
        displayPrice(price);
    }

    private String createOrderSummary(int quantity, int price, String name) {
        String message = "Name: " + name;
        if (whipCream) {
            message += "\nAdd Whipped cream";
        };
        if (chocolate) {
            message += "\nAdd Chocolate";
        }
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        message += "\nThank you!";
        return message;
    }

}