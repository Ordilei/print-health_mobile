package com.example.modelotcc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements Runnable {

	private ProgressDialog pd;
	private String valor;
	public EditText txUsuario;
	public EditText txSenha;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		txUsuario = (EditText) findViewById(R.id.txUsuario);
		final EditText txSenha = (EditText) findViewById(R.id.txSenha);
		Button btEnt = (Button) findViewById(R.id.btEnt);
		Button btCad = (Button) findViewById(R.id.btCad);

		btEnt.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), Leitor.class));
				StringBuilder stb = new StringBuilder();
				stb.append("Usuário: ");
				stb.append(txUsuario.getText().toString());
				stb.append("Senha: ");
				stb.append(txSenha.getText().toString());
				Log.d("Retorno", stb.toString());

				pd = ProgressDialog.show(Login.this, "Login",
						"Registrando Login", true, false);
				Thread t = new Thread(Login.this);
				t.start();
			}
		});

		/*
		 * if(usuarioDao.isUsuario(validaLogin, validaSenha)){
		 * 
		 * Toast.makeText(Login.this, "LOGIN E SENHA CORRETOS",
		 * Toast.LENGTH_SHORT).show(); Log.i(CATEGORIA,
		 * "MENSAGEM DE LOG: dados digitados corretamente!");
		 * 
		 * carregaTelaListaEmpresaProcesso(); } else {
		 * Toast.makeText(Login.this, "DADOS INCORRETOS",
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * 
		 * Log.i(CATEGORIA,
		 * "MENSAGEM DE LOG: não existe este usuario no banco!");
		 * 
		 * carregaTelaCadastroUsuario();
		 * 
		 * 
		 * 
		 * }
		 * 
		 * });
		 */

		btCad.setOnClickListener(new View.OnClickListener() {

			public void onClick(View w) {
				startActivity(new Intent(getBaseContext(), Cadastro.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void run() {
		valor = txUsuario.getText().toString();
		h.sendEmptyMessage(0);
	}

	private Handler h = new Handler() {
		public void handleMessenge(Messenger msg) {
			pd.dismiss();
		}
	};

}
