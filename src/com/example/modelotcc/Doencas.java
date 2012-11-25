package com.example.modelotcc;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Doencas extends Activity {

	private static final String LOGS = "logs";
	private Button btProx;
	String Nome_Banco = "print";
	SQLiteDatabase BancoDeDados = null;
	EditText nome_doenca;

	Cadastro cad = new Cadastro();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doencas);

		btProx = (Button) findViewById(R.id.btProx);

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Itens, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		btProx.setOnClickListener(new View.OnClickListener() {

			public void onClick(View w) {
				startActivity(new Intent(getBaseContext(), ItensLista.class));
			}
		});

	}

	public void CriaBanco() {
		try {
			BancoDeDados = openOrCreateDatabase(Nome_Banco,
					MODE_WORLD_READABLE, null);
			String SQL = "CREATE TABLE IF NOT EXISTS doencas (_id_doenca INTEGER PRIMARY KEY autoincrement, nome_doenca TEXT)";
			BancoDeDados.execSQL(SQL);
		} catch (Exception erro) {
			cad.MensagemAlerta("ERRO Banco de Dados",
					"Não foi possivel criar o banco!" + erro);

		} finally {
			BancoDeDados.close();
		}

	}

	public void GravaBanco() {
		try {
			BancoDeDados = openOrCreateDatabase(Nome_Banco,
					MODE_WORLD_READABLE, null);
			String SQL = "INSERT INTO usuario (nome_doenca) VALUES ('"
					+ nome_doenca.getText().toString() + "')";
			BancoDeDados.execSQL(SQL);
		} finally {
			BancoDeDados.close();
		}
	}
}
