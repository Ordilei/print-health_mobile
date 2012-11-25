/*package com.example.modelotcc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class EditCad extends Activity {

	private ListAdapter adaptador;
	Cadastro cad = new Cadastro();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.editcad);

	}

	public void CarregaDados() {
		try {
			cad.mostraDados = (ListView) findViewById(R.id.listView1);

			if (cad.VerificaRegistro()) {
				String[] Coluna = new String[] { cad.KEY_NOME };

				SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
						R.layout.editcad, cad.cursor, Coluna,
						new int[] { R.id.carregaDados });

				cad.mostraDados.setAdapter(adaptador);

			} else {
				cad.MensagemAlerta("ERRO Banco de Dados",
						"Você não possui cadastro!");
			}
		} catch (Exception erro) {
			cad.MensagemAlerta("erro", "erro aqui***" + erro);

		}
	}
}


 * Simple this, R.layout.mostrabanco, cad.cursor, cad.Coluna, new int[] {
 * R.id.carregaDados }); cad.mostraDados.setAdapter(adapterLista); } else {
 * MensagemAlerta("ERRO Banco de Dados", "Você não possui cadastro!"); } } catch
 * (Exception erro) { MensagemAlerta("erro", "erro aqui***" + erro); }
 
*/