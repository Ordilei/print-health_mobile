package com.example.modelotcc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

public class Metodos extends Activity {

	Cadastro cad = new Cadastro();

	// Mensagem de SQLiteConstraintException, quando já existir o usuário
	public void usuarioUnique() {
		try {

		} catch (SQLiteConstraintException usuario) {
			cad.MensagemAlerta("Banco de Dados", "Usuário já existe!");

		}
	}

	// Limpar dados na tela de cadastro
	private void limpaDados() {
		cad.nome.getText().clear();
		cad.email.getText().clear();
		cad.usuario.getText().clear();
		cad.senha.getText().clear();
		cad.senhaConf.getText().clear();

	}

	// Chamar a tela de login
	public void chamaLogin() {

		Intent chamarLogin = new Intent(this, Login.class);
		Metodos.this.startActivity(chamarLogin);

	}

	public void SetarDados(int Posicao) {
		try {
			cad.BancoDeDados = openOrCreateDatabase(cad.Nome_Banco,
					MODE_WORLD_READABLE, null);
			Cursor c = cad.BancoDeDados
					.rawQuery("Select * from usuario where _id = '" + Posicao
							+ "'", null);

			while (c.moveToFirst()) {
				cad.nome.setText(c.getString(c.getColumnIndex("nome")));
				cad.email.setText(c.getString(c.getColumnIndex("email")));
				cad.senha.setText(c.getString(c.getColumnIndex("senha")));
			}

		} catch (Exception erro) {
			cad.MensagemAlerta("Erro", "Não encontrado");
		}
	}
}
