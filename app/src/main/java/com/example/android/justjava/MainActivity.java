package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
   * gets the checked state for the whipped cream checkbox
   * @return true or false
   */
  private boolean addWhippedCream() {
    CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
    return whippedCreamCheckBox.isChecked();
  }

  /**
   * gets the checked state for the chocolate checkbox
   * @return true or false
   */
  private boolean addChocolate() {
    CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
    return chocolateCheckBox.isChecked();
  }

  /**
   * Gets the text from the EditText input
   * @return String
   */
  private String getName() {
    EditText nameInput = (EditText) findViewById(R.id.name_input);
    return nameInput.getText().toString();
  }

  /**
   * This method is called when the order button is clicked.
   */
  public void submitOrder(View view) {
//    displayMessage(orderSummary());
    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
    emailIntent.setData(Uri.parse("mailto:"));
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Summary");
    emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary());

    if (emailIntent.resolveActivity(getPackageManager()) != null) {
      startActivity(emailIntent);
    }
  }

  /**
   * Formats and gathers the order summary date into an output String
   * @return String
   */
  private String orderSummary() {
    return "Name: " + getName() +
        "\nAdd whipped cream? " + addWhippedCream() +
        "\nAdd chocolate? " + addChocolate() +
        "\nQuantity: " + quantity +
        "\nTotal: $" + (quantity*coffeeCost) +
        "\nThank you!";
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