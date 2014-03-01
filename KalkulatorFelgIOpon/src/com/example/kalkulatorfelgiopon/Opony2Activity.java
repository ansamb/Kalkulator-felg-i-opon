package com.example.kalkulatorfelgiopon;

import java.io.IOException;
//import java.math.BigDecimal;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Opony2Activity extends Activity {

	private Spinner obecnaSzerokoscSpinner;
	private Spinner obecnyProfilSpinner;
	private Spinner obecnaSrednicaSpinner;
	private Spinner nowaSzerokoscSpinner;
	private Spinner nowyProfilSpinner;
	private Spinner nowaSrednicaSpinner;
	private Button obl;
	private Button menu;
	private Button reset;
	private CheckBox option1;
	private CheckBox option2;
	private CheckBox option3;
	private Cursor cursor;
	private TextView listdata;
	private DataBaseHelper myDbHelper;
	private OnClickListener checkBoxListener;
	public final String sredKolaText = "Średnica koła (mm):";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opony2);

		obecnaSzerokoscSpinner = (Spinner) findViewById(R.id.szerokoscO2);
		obecnyProfilSpinner = (Spinner) findViewById(R.id.profilO2);
		obecnaSrednicaSpinner = (Spinner) findViewById(R.id.srednicaO2);
		nowaSzerokoscSpinner = (Spinner) findViewById(R.id.szerokosc1O2);
		nowyProfilSpinner = (Spinner) findViewById(R.id.profil1O2);
		nowaSrednicaSpinner = (Spinner) findViewById(R.id.srednica1O2);
		option1 = (CheckBox) findViewById(R.id.option1);
		option2 = (CheckBox) findViewById(R.id.option2);
		option3 = (CheckBox) findViewById(R.id.option3);

		ArrayAdapter<CharSequence> adapterSz = ArrayAdapter.createFromResource(
				this, R.array.szerokosc_opony, R.layout.textview);
		adapterSz
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		obecnaSzerokoscSpinner.setAdapter(adapterSz);

		ArrayAdapter<CharSequence> adapterP = ArrayAdapter.createFromResource(
				this, R.array.profil_opony, R.layout.textview);
		adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		obecnyProfilSpinner.setAdapter(adapterP);

		ArrayAdapter<CharSequence> adapterSr = ArrayAdapter.createFromResource(
				this, R.array.srednica_opony, R.layout.textview);
		adapterSr
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		obecnaSrednicaSpinner.setAdapter(adapterSr);

		ArrayAdapter<CharSequence> adapterSz1 = ArrayAdapter
				.createFromResource(this, R.array.szerokosc_opony,
						R.layout.textview);
		adapterSz1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		nowaSzerokoscSpinner.setAdapter(adapterSz1);

		ArrayAdapter<CharSequence> adapterP1 = ArrayAdapter.createFromResource(
				this, R.array.profil_opony, R.layout.textview);
		adapterP1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		nowyProfilSpinner.setAdapter(adapterP1);

		ArrayAdapter<CharSequence> adapterSr1 = ArrayAdapter
				.createFromResource(this, R.array.srednica_opony,
						R.layout.textview);
		adapterSr1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		nowaSrednicaSpinner.setAdapter(adapterSr1);

		nowaSzerokoscSpinner.setEnabled(false);
		nowyProfilSpinner.setEnabled(false);
		nowaSrednicaSpinner.setEnabled(false);
		myDbHelper = new DataBaseHelper(this);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		getCheckBoxResult();

		obl = (Button) findViewById(R.id.obliczO2);
		menu = (Button) findViewById(R.id.powrotO2);
		reset = (Button) findViewById(R.id.resetO2);
		initButtonsOnClickListeners();

	}

	private void getCheckBoxResult() {

		checkBoxListener = new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (option1.isChecked()) {

					nowaSzerokoscSpinner.setEnabled(true);
					nowyProfilSpinner.setSelection(0);
					nowyProfilSpinner.setEnabled(false);
					nowaSrednicaSpinner.setSelection(0);
					nowaSrednicaSpinner.setEnabled(false);
				}

				if (option2.isChecked()) {

					nowyProfilSpinner.setEnabled(true);
					nowaSzerokoscSpinner.setSelection(0);
					nowaSzerokoscSpinner.setEnabled(false);
					nowaSrednicaSpinner.setSelection(0);
					nowaSrednicaSpinner.setEnabled(false);

				}
				if (option3.isChecked()) {

					nowaSrednicaSpinner.setEnabled(true);
					nowaSzerokoscSpinner.setSelection(0);
					nowaSzerokoscSpinner.setEnabled(false);
					nowyProfilSpinner.setSelection(0);
					nowyProfilSpinner.setEnabled(false);

				}
				if (option1.isChecked() && option2.isChecked()) {

					nowaSzerokoscSpinner.setEnabled(true);
					nowyProfilSpinner.setEnabled(true);
					option3.setChecked(false);
					nowaSrednicaSpinner.setSelection(0);
					nowaSrednicaSpinner.setEnabled(false);

				}

				if (option1.isChecked() && option3.isChecked()) {

					nowaSzerokoscSpinner.setEnabled(true);
					nowaSrednicaSpinner.setEnabled(true);
					option2.setChecked(false);
					nowyProfilSpinner.setSelection(0);
					nowyProfilSpinner.setEnabled(false);

				}

				if (option2.isChecked() && option3.isChecked()) {

					nowaSrednicaSpinner.setEnabled(true);
					nowyProfilSpinner.setEnabled(true);
					option1.setChecked(false);
					nowaSzerokoscSpinner.setSelection(0);
					nowaSzerokoscSpinner.setEnabled(false);
				}

			}
		};

		option1.setOnClickListener(checkBoxListener);
		option2.setOnClickListener(checkBoxListener);
		option3.setOnClickListener(checkBoxListener);

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

	protected void runOblicz() {

		int szerokoscObecna;
		int profilObecny;
		int srednicaObecna;
		double srednicaKolaOb;

		if (obecnaSzerokoscSpinner.getSelectedItem().toString().equals("")
				|| obecnyProfilSpinner.getSelectedItem().toString().equals("")
				|| obecnaSrednicaSpinner.getSelectedItem().toString()
						.equals("")) {

			Toast.makeText(getApplicationContext(),
					"Wybierz wymiary obecnej opony", Toast.LENGTH_LONG).show();

		} else {

			szerokoscObecna = getSelectedSzerokoscObecna();
			profilObecny = getSelectedProfilObecny();
			srednicaObecna = getSelectedSrednicaObecna();
			srednicaKolaOb = calculateObecnaSrednicaKola(szerokoscObecna,profilObecny, srednicaObecna);
			int gornaGranica = calculateGornaGranica((int) srednicaKolaOb);
			int dolnaGranica = calculateDolnaGranica((int) srednicaKolaOb);
			
			if (option1.isChecked()) {
				if (nowaSzerokoscSpinner.getSelectedItem().toString()
						.equals("")) {

					Toast.makeText(getApplicationContext(),
							"Wybierz wartość do zmiany", Toast.LENGTH_LONG)
							.show();
				} else {

					int szerokoscNowa = getSelectedSzerokoscNowa();
					listdata = (TextView) findViewById(R.id.textView2O2);
					cursor = myDbHelper.getWymiar1(dolnaGranica, gornaGranica,
							szerokoscNowa);
					String result = "";
					if (cursor.getCount() == 0) {

						listdata.setText("Nie zamienników o takiej szerokości");
					} else {

						for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
								.moveToNext()) {
							result = result + cursor.getString(0) + "\n";
						}
						cursor.close();
						listdata.setText(result);
					}
				}

			}

			if (option2.isChecked()) {
				if (nowyProfilSpinner.getSelectedItem().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Wybierz wartość do zmiany", Toast.LENGTH_LONG)
							.show();
				} else {

					int profilNowy = getSelectedProfilNowy();
					listdata = (TextView) findViewById(R.id.textView2O2);
					cursor = myDbHelper.getWymiar2(dolnaGranica, gornaGranica,
							profilNowy);
					String result = "";
					if (cursor.getCount() == 0) {
						listdata.setText("Nie zamienników o takiej szerokości");
					} else {
						for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
								.moveToNext()) {
							result = result + cursor.getString(0) + "\n";
						}
						cursor.close();
						listdata.setText(result);
					}
				}
			}
			if (option3.isChecked()) {
				if (nowaSrednicaSpinner.getSelectedItem().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Wybierz wartość do zmiany", Toast.LENGTH_LONG)
							.show();
				} else {

					int srednicaNowa = getSelectedSrednicaNowa();
					listdata = (TextView) findViewById(R.id.textView2O2);
					cursor = myDbHelper.getWymiar3(dolnaGranica, gornaGranica,
							srednicaNowa);
					String result = "";
					if (cursor.getCount() == 0) {
						listdata.setText("Nie zamienników o takiej szerokości");
					} else {
						for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
								.moveToNext()) {
							result = result + cursor.getString(0) + "\n";
						}
						cursor.close();
						listdata.setText(result);
					}
				}
			}

			if (option1.isChecked() && option2.isChecked()) {

				if (nowaSzerokoscSpinner.getSelectedItem().toString()
						.equals("")
						|| nowyProfilSpinner.getSelectedItem().toString()
								.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Wybierz wartość do zmiany", Toast.LENGTH_LONG)
							.show();
				} else {

					int szerokoscNowa = getSelectedSzerokoscNowa();
					int profilNowy = getSelectedProfilNowy();
					listdata = (TextView) findViewById(R.id.textView2O2);
					cursor = myDbHelper.getWymiar12(dolnaGranica, gornaGranica,
							szerokoscNowa, profilNowy);
					String result = "";

					if (cursor.getCount() == 0) {
						listdata.setText("Nie zamienników o takiej szerokości");
					} else {
						for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
								.moveToNext()) {
							result = result + cursor.getString(0) + "\n";
						}
						cursor.close();
						listdata.setText(result);

					}
				}
			}
			if (option1.isChecked() && option3.isChecked()) {

				if (nowaSzerokoscSpinner.getSelectedItem().toString()
						.equals("")
						|| nowaSrednicaSpinner.getSelectedItem().toString()
								.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Wybierz wartość do zmiany", Toast.LENGTH_LONG)
							.show();
				} else {

					int szerokoscNowa = getSelectedSzerokoscNowa();
					int srednicaNowa = getSelectedSrednicaNowa();
					listdata = (TextView) findViewById(R.id.textView2O2);
					cursor = myDbHelper.getWymiar13(dolnaGranica, gornaGranica,
							szerokoscNowa, srednicaNowa);
					String result = "";

					if (cursor.getCount() == 0) {
						listdata.setText("Nie zamienników o takiej szerokości");
					} else {
						for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
								.moveToNext()) {
							result = result + cursor.getString(0) + "\n";
						}
						cursor.close();
						listdata.setText(result);

					}
				}
			}

			if (option2.isChecked() && option3.isChecked()) {

				if (nowaSrednicaSpinner.getSelectedItem().toString().equals("")
						|| nowyProfilSpinner.getSelectedItem().toString()
								.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Wybierz wartość do zmiany", Toast.LENGTH_LONG)
							.show();
				} else {

					int srednicaNowa = getSelectedSrednicaNowa();
					int profilNowy = getSelectedProfilNowy();
					listdata = (TextView) findViewById(R.id.textView2O2);
					cursor = myDbHelper.getWymiar23(dolnaGranica, gornaGranica,
							profilNowy, srednicaNowa);
					String result = "";

					if (cursor.getCount() == 0) {
						listdata.setText("Nie zamienników o takiej szerokości");
					} else {
						for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
								.moveToNext()) {
							result = result + cursor.getString(0) + "\n";
						}
						cursor.close();
						listdata.setText(result);

					}
				}
			}
		}

	}

	public int calculateDolnaGranica(int srednicaKolaOb) {

		double dopuszczalnaDolnaGranica = 0.02;

		return (int) (srednicaKolaOb - (srednicaKolaOb * dopuszczalnaDolnaGranica));
	}

	public int calculateGornaGranica(int srednicaKolaOb) {

		double dopuszczalnaGornaGranica = 0.015;

		return (int) (srednicaKolaOb + (srednicaKolaOb * dopuszczalnaGornaGranica));
	}

	public double calculateObecnaSrednicaKola(double szerokoscObecna,
			double profilObecny, double srednicaObecna) {

		double cal = 25.4;

		return (double) ((srednicaObecna * cal) + 2 * ((profilObecny / 100) * szerokoscObecna));
	}

	private int getSelectedSzerokoscObecna() {
		String selectedObecnaSzerokosc = (String) obecnaSzerokoscSpinner
				.getSelectedItem();

		if (obecnaSzerokoscSpinner.getSelectedItemPosition() == 0)
			Integer.parseInt(selectedObecnaSzerokosc);

		return Integer.parseInt(selectedObecnaSzerokosc);

	}

	private int getSelectedSzerokoscNowa() {
		String selectedNowaSzerokosc = (String) nowaSzerokoscSpinner
				.getSelectedItem();

		if (nowaSzerokoscSpinner.getSelectedItemPosition() == 0)
			Integer.parseInt(selectedNowaSzerokosc);

		return Integer.parseInt(selectedNowaSzerokosc);

	}

	private int getSelectedProfilObecny() {

		String selectedObecnyProfil = (String) obecnyProfilSpinner
				.getSelectedItem();

		if (obecnyProfilSpinner.getSelectedItemPosition() == 0)
			Integer.parseInt(selectedObecnyProfil);

		return Integer.parseInt(selectedObecnyProfil);

	}

	private int getSelectedProfilNowy() {
		String selectedProfilNowy = (String) nowyProfilSpinner
				.getSelectedItem();
		if (nowyProfilSpinner.getSelectedItemPosition() == 0)
			Integer.parseInt(selectedProfilNowy);

		return Integer.parseInt(selectedProfilNowy);

	}

	private int getSelectedSrednicaObecna() {
		String selectedSrednicaObecna = (String) obecnaSrednicaSpinner
				.getSelectedItem();

		if (obecnaSrednicaSpinner.getSelectedItemPosition() == 0)
			Integer.parseInt(selectedSrednicaObecna);

		return Integer.parseInt(selectedSrednicaObecna);

	}

	private int getSelectedSrednicaNowa() {
		String selectedSrednicaNowa = (String) nowaSrednicaSpinner
				.getSelectedItem();
		if (nowaSrednicaSpinner.getSelectedItemPosition() == 0)
			Integer.parseInt(selectedSrednicaNowa);

		return Integer.parseInt(selectedSrednicaNowa);

	}

	protected void runReset() {

		obecnaSzerokoscSpinner.setSelection(0);
		obecnyProfilSpinner.setSelection(0);
		obecnaSrednicaSpinner.setSelection(0);
		nowaSzerokoscSpinner.setSelection(0);
		nowyProfilSpinner.setSelection(0);
		nowaSrednicaSpinner.setSelection(0);
		nowaSzerokoscSpinner.setEnabled(false);
		nowyProfilSpinner.setEnabled(false);
		nowaSrednicaSpinner.setEnabled(false);
		listdata.setText("");
//		srednicaKolaObecna.setText("");
		option1.setChecked(false);
		option2.setChecked(false);
		option3.setChecked(false);
	}

	protected void runMenuPowrot() {

		myDbHelper.close();

		Intent intent = new Intent(Opony2Activity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

}
