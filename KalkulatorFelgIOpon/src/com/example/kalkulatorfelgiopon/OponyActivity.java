package com.example.kalkulatorfelgiopon;

import java.math.BigDecimal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class OponyActivity extends Activity {

	private Spinner obecnaSzerokoscSpinner;
	private Spinner obecnyProfilSpinner;
	private Spinner obecnaSrednicaSpinner;
	private Spinner nowaSzerokoscSpinner;
	private Spinner nowyProfilSpinner;
	private Spinner nowaSrednicaSpinner;
	private TextView srednicaKolaObecna;
	private TextView srednicaKolaNowa;
	private TextView roznicaWSrednicy;
	private TextView roznicaWPredkosci;
	private TextView predkoscPrzyklad;
	private Button obl;
	private Button menu;
	private Button reset;
	private Button opis;
	private Button uwaga;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opony);

		srednicaKolaObecna = (TextView) findViewById(R.id.srednicaKolaObecna);
		srednicaKolaNowa = (TextView) findViewById(R.id.srednicaKolaNowa);
		roznicaWSrednicy = (TextView) findViewById(R.id.roznicaWSrednicyKola);
		roznicaWPredkosci = (TextView) findViewById(R.id.roznicaWPredkosciWynik);
		predkoscPrzyklad = (TextView) findViewById(R.id.predkoscPrzykladWynik);

		obecnaSzerokoscSpinner = (Spinner) findViewById(R.id.szerokosc);
		obecnyProfilSpinner = (Spinner) findViewById(R.id.profil);
		obecnaSrednicaSpinner = (Spinner) findViewById(R.id.srednica);
		nowaSzerokoscSpinner = (Spinner) findViewById(R.id.szerokosc1);
		nowyProfilSpinner = (Spinner) findViewById(R.id.profil1);
		nowaSrednicaSpinner = (Spinner) findViewById(R.id.srednica1);

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

		uwaga = (Button) findViewById(R.id.pusty2);
		opis = (Button) findViewById(R.id.pusty1);
		obl = (Button) findViewById(R.id.oblicz);
		menu = (Button) findViewById(R.id.powrot);
		reset = (Button) findViewById(R.id.reset);
		initButtonsOnClickListeners();
	}

	private void initButtonsOnClickListeners() {

		uwaga.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showAttention();
			}
		});

		opis.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDescription();
			}
		});

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

	protected void showAttention() {

		final Toast toast = Toast
				.makeText(
						getBaseContext(),
						"Montaż opon większej średnicy od wzorcowej powoduje, "
								+ "że prędkościomierz będzie przekłamywał na minus - licznik"
								+ " pokaże mniejszą prędkość niż rzeczywista. Analogicznie, "
								+ "gdy założymy koła mniejsze od oryginalnych – licznik "
								+ "pokaże większą prędkość niż rzeczywista.",
						Toast.LENGTH_SHORT);
		toast.show();
		new CountDownTimer(10000, 1000) {
			public void onTick(long millisUntilFinished) {
				toast.show();
			}

			public void onFinish() {
				toast.cancel();
			}
		}.start();

	}

	protected void showDescription() {

		final Toast toast = Toast.makeText(getBaseContext(),
				"Kolor czerwony oznacza, że dany rozmiar nie jest właściwym zamiennikiem. "
						+ "Kolor zielony oznacza akceptowalny zamiennik.",
				Toast.LENGTH_SHORT);
		toast.show();
		new CountDownTimer(5000, 1000) {
			public void onTick(long millisUntilFinished) {
				toast.show();
			}

			public void onFinish() {
				toast.cancel();
			}
		}.start();

	}

	protected void runReset() {

		obecnaSzerokoscSpinner.setSelection(0);
		obecnyProfilSpinner.setSelection(0);
		obecnaSrednicaSpinner.setSelection(0);
		nowaSzerokoscSpinner.setSelection(0);
		nowyProfilSpinner.setSelection(0);
		nowaSrednicaSpinner.setSelection(0);

		srednicaKolaObecna.setText("");
		srednicaKolaNowa.setText("");
		roznicaWSrednicy.setText("");
		roznicaWPredkosci.setText("");
		predkoscPrzyklad.setText("");

	}

	protected void runMenuPowrot() {

		Intent intent = new Intent(OponyActivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	protected void runOblicz() {

		if (obecnaSzerokoscSpinner.getSelectedItem().toString().equals("")
				|| obecnyProfilSpinner.getSelectedItem().toString().equals("")
				|| obecnaSrednicaSpinner.getSelectedItem().toString().equals("")
				|| nowaSzerokoscSpinner.getSelectedItem().toString().equals("")
				|| nowyProfilSpinner.getSelectedItem().toString().equals("")
				|| nowaSrednicaSpinner.getSelectedItem().toString().equals("")) {

			Toast.makeText(getApplicationContext(), "Wskaż wszystkie wymiary",
					Toast.LENGTH_LONG).show();

		} else {
			
			double szerokoscObecna = getSelectedSzerokoscObecna();
			double profilObecny = getSelectedProfilObecny();
			double srednicaObecna = getSelectedSrednicaObecna();
			double szerokoscNowa = getSelectedSzerokoscNowa();
			double profilNowy = getSelectedProfilNowy();
			double srednicaNowa = getSelectedSrednicaNowa();

			double srednicaKolaOb = calculateObecnaSrednicaKola(
					szerokoscObecna, profilObecny, srednicaObecna);
			BigDecimal bd = new BigDecimal(srednicaKolaOb);
			srednicaKolaObecna.setText(bd.setScale(1, BigDecimal.ROUND_HALF_UP)
					+ "");

			double srednicaKolaN = calculateNowaSrednicaKola(szerokoscNowa,
					profilNowy, srednicaNowa);
			BigDecimal bd1 = new BigDecimal(srednicaKolaN);
			srednicaKolaNowa.setText(bd1.setScale(1, BigDecimal.ROUND_HALF_UP)
					+ "");

			double roznicaSrednic = calculateRoznicaWSrednicy(srednicaKolaOb,
					srednicaKolaN);
			double gornaGranica = calculateGornaGranica(srednicaKolaOb);
			double dolnaGranica = calculateDolnaGranica(srednicaKolaOb);
			BigDecimal bd2 = new BigDecimal(roznicaSrednic);
			roznicaWSrednicy.setText(bd2.setScale(1, BigDecimal.ROUND_HALF_UP)
					+ "");

			if (srednicaKolaN > dolnaGranica && srednicaKolaN < gornaGranica) {
				roznicaWSrednicy.setTextColor(getResources().getColor(
						R.color.text_color_green));
			} else {
				roznicaWSrednicy.setTextColor(getResources().getColor(
						R.color.text_color_red));
			}

			double roznicaPredkosci = calculateRoznicaWPredkosci(
					roznicaSrednic, srednicaKolaN);
			BigDecimal bd3 = new BigDecimal(roznicaPredkosci);
			roznicaWPredkosci.setText(bd3.setScale(1, BigDecimal.ROUND_HALF_UP)
					+ "");

			double zmianaPredkosci = calculatePredkoscPrzyklad(roznicaPredkosci);
			BigDecimal bd4 = new BigDecimal(zmianaPredkosci);
			predkoscPrzyklad.setText(bd4.setScale(1, BigDecimal.ROUND_HALF_UP)
					+ "");

		}
	}

	private double calculateObecnaSrednicaKola(double szerokoscObecna,
			double profilObecny, double srednicaObecna) {

		double cal = 25.4;

		return (double) ((srednicaObecna * cal) + 2 * ((profilObecny / 100) * szerokoscObecna));
	}

	private double calculateNowaSrednicaKola(double szerokoscNowa,
			double profilNowy, double srednicaNowa) {

		double cal = 25.4;

		return (double) ((srednicaNowa * cal) + 2 * ((profilNowy / 100) * szerokoscNowa));
	}

	public double calculateDolnaGranica(double srednicaKolaOb) {

		double dopuszczalnaDolnaGranica = 0.02;

		return (double) (srednicaKolaOb - (srednicaKolaOb * dopuszczalnaDolnaGranica));
	}

	public double calculateGornaGranica(double srednicaKolaOb) {

		double dopuszczalnaGornaGranica = 0.015;

		return (double) (srednicaKolaOb + (srednicaKolaOb * dopuszczalnaGornaGranica));
	}

	private double calculateRoznicaWSrednicy(double srednicaObecnaKola,
			double srednicaNowaKola) {

		return (double) (srednicaNowaKola - srednicaObecnaKola);
	}

	private double calculateRoznicaWPredkosci(double roznicaSrednic,
			double srednicaNowaKola) {

		return (double) ((roznicaSrednic * 100) / srednicaNowaKola);

	}

	private double calculatePredkoscPrzyklad(double roznicaWPredkosci) {

		double predkosc = 100.00;

		return (double) (predkosc + roznicaWPredkosci);
	}

	private double getSelectedSzerokoscObecna() {
		String selectedObecnaSzerokosc = (String) obecnaSzerokoscSpinner
				.getSelectedItem();
		if (obecnaSzerokoscSpinner.getSelectedItemPosition() == 0)
			Double.parseDouble(selectedObecnaSzerokosc);

		return Double.parseDouble(selectedObecnaSzerokosc);

	}

	private double getSelectedSzerokoscNowa() {
		String selectedNowaSzerokosc = (String) nowaSzerokoscSpinner
				.getSelectedItem();
		if (nowaSzerokoscSpinner.getSelectedItemPosition() == 0)
			Double.parseDouble(selectedNowaSzerokosc);

		return Double.parseDouble(selectedNowaSzerokosc);

	}

	private double getSelectedProfilObecny() {
		String selectedObecnyProfil = (String) obecnyProfilSpinner
				.getSelectedItem();
		if (obecnyProfilSpinner.getSelectedItemPosition() == 0)
			Double.parseDouble(selectedObecnyProfil);

		return Double.parseDouble(selectedObecnyProfil);

	}

	private double getSelectedProfilNowy() {
		String selectedProfilNowy = (String) nowyProfilSpinner
				.getSelectedItem();
		if (nowyProfilSpinner.getSelectedItemPosition() == 0)
			Double.parseDouble(selectedProfilNowy);

		return Double.parseDouble(selectedProfilNowy);

	}

	private double getSelectedSrednicaObecna() {
		String selectedSrednicaObecna = (String) obecnaSrednicaSpinner
				.getSelectedItem();
		if (obecnaSrednicaSpinner.getSelectedItemPosition() == 0)
			Double.parseDouble(selectedSrednicaObecna);

		return Double.parseDouble(selectedSrednicaObecna);

	}

	private double getSelectedSrednicaNowa() {
		String selectedSrednicaNowa = (String) nowaSrednicaSpinner
				.getSelectedItem();
		if (nowaSrednicaSpinner.getSelectedItemPosition() == 0)
			Double.parseDouble(selectedSrednicaNowa);

		return Double.parseDouble(selectedSrednicaNowa);

	}

}
