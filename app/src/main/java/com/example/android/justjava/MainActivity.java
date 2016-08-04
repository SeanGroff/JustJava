package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

  int quantity = 1;
  int coffeeCost = 3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * This method is called when the order button is clicked.
   */
  public void submitOrder(View view) {
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:"));
    intent.putExtra(Intent.EXTRA_SUBJECT, "Do you love Java?");
    intent.putExtra(Intent.EXTRA_TEXT, "This is the body of the email, order Java!!");
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
//    String priceMessage = "Total: $" + (quantity*coffeeCost) + "\nThank you!";
//    displayMessage(priceMessage);
  }

  public void increment(View view) {
    quantity++;
    display(quantity);
  }

  public void decrement(View view) {
    if (quantity > 0) {
      quantity--;
    }
    display(quantity);
  }

  /**
   * This method displays the given quantity value on the screen.
   */
  private void display(int number) {
    TextView quantityTextView = (TextView) findViewById(
        R.id.quantity_text_view);
    quantityTextView.setText("" + number);
  }

  /**
   * This method displays the given price on the screen.
   */
  private void displayPrice(int number) {
    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
    priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
  }

  private void displayMessage(String message) {
    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
    priceTextView.setText(message);
  }
}