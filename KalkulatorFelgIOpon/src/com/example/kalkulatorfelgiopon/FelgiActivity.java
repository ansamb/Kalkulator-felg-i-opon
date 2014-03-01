package com.example.kalkulatorfelgiopon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FelgiActivity extends Activity {

	private Spinner SzerokoscSpinner;
	private TextView minWynik;
	private TextView maxWynik;
	private TextView optWynik;
	private Button obl;
	private Button menu;
	private Button reset;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_felgi);

		ImageView imageView = (ImageView) findViewById(R.id.imageView2);
		imageView.setImageResource(R.drawable.opona5);

		minWynik = (TextView) findViewById(R.id.minSzerokoscWynik);
		maxWynik = (TextView) findViewById(R.id.maxSzerokoscWynik);
		optWynik = (TextView) findViewById(R.id.optSzerokoscWynik);

		SzerokoscSpinner = (Spinner) findViewById(R.id.szerokosc1);

		ArrayAdapter<CharSequence> adapterSz = ArrayAdapter.createFromResource(
				this, R.array.szerokosc_opony, R.layout.textview);
		adapterSz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SzerokoscSpinner.setAdapter(adapterSz);

		obl = (Button) findViewById(R.id.oblicz1);
		menu = (Button) findViewById(R.id.powrot1);
		reset = (Button) findViewById(R.id.reset1);
		initButtonsOnClickListeners();

	}

	private void initButtonsOnClickListeners() {

		obl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				runOblicz();
			}
		});

		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				runMenuPowrot();
			}
		});

		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				runReset();
			}
		});
	}

	protected void runReset() {

		SzerokoscSpinner.setSelection(0);
		minWynik.setText("");
		maxWynik.setText("");
		optWynik.setText("");

	}

	protected void runMenuPowrot() {

		Intent intent = new Intent(FelgiActivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	protected void runOblicz() {

		if (SzerokoscSpinner.getSelectedItem().toString().equals("")) {

			Toast.makeText(getApplicationContext(),
					"Wskaż szerokość obecnej opony", Toast.LENGTH_LONG).show();

		} else {

			int position = SzerokoscSpinner.getSelectedItemPosition();

			switch (position) {
			case 1: // first item
				maxWynik.setText("4.5");
				minWynik.setText("3.5");
				optWynik.setText("4.0");
				// optWynik.setTextColor(getResources().getColor(R.color.text_color_green));
				break;
			case 2: // second item
				maxWynik.setText("5.0");
				minWynik.setText("4.0");
				optWynik.setText("4.5");
				break;
			case 3: // second item
				maxWynik.setText("5.0");
				minWynik.setText("4.0");
				optWynik.setText("4.5");
				break;
			case 4: // second item
				maxWynik.setText("5.5");
				minWynik.setText("4.0");
				optWynik.setText("5.0");
				break;
			case 5: // second item
				maxWynik.setText("6.0");
				minWynik.setText("4.5");
				optWynik.setText("5.0");
				break;
			case 6: // second item
				maxWynik.setText("6.5");
				minWynik.setText("5.0");
				optWynik.setText("5.5");
				break;
			case 7: // second item
				maxWynik.setText("7.0");
				minWynik.setText("5.5");
				optWynik.setText("6.0");
				break;
			case 8: // second item
				maxWynik.setText("7.5");
				minWynik.setText("5.5");
				optWynik.setText("6.5");
				break;
			case 9: // second item
				maxWynik.setText("8.0");
				minWynik.setText("6.0");
				optWynik.setText("7.0");
				break;
			case 10: // second item
				maxWynik.setText("8.5");
				minWynik.setText("6.0");
				optWynik.setText("7.5");
				break;
			case 11: // second item
				maxWynik.setText("9.0");
				minWynik.setText("7.0");
				optWynik.setText("8.0");
				break;
			case 12: // second item
				maxWynik.setText("10.0");
				minWynik.setText("8.5");
				optWynik.setText("9.0");
				break;
			case 13: // second item
				maxWynik.setText("10.0");
				minWynik.setText("8.5");
				optWynik.setText("9.0");
				break;
			case 14: // second item
				maxWynik.setText("10.5");
				minWynik.setText("9.0");
				optWynik.setText("9.5");
				break;
			case 15: // second item
				maxWynik.setText("11.0");
				minWynik.setText("9.0");
				optWynik.setText("10.0");
				break;
			case 16: // second item
				maxWynik.setText("11.5");
				minWynik.setText("9.5");
				optWynik.setText("10.5");
				break;

			}
		}

	}

}
