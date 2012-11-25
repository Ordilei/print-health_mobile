package com.example.modelotcc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Cadastro extends Activity {

	String Nome_Banco = "print";
	SQLiteDatabase BancoDeDados = null;
	EditText usuario;
	EditText email;
	EditText nome;
	EditText senha;
	EditText senhaConf;
	Button salvar;
	Cursor cursor;
	ListView mostraDados;
	SimpleCursorAdapter adapterLista;

	Login l = new Login();
	Metodos m = new Metodos();

	public static final String KEY_NOME = "nome";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro);

		mostraDados.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int Posicao, long arg3) {
				m.SetarDados(Posicao);

			}

		});

		Inicializar();

	}

	// Cria o BD
	public void CriaBanco() {
		try {
			BancoDeDados = openOrCreateDatabase(Nome_Banco,
					MODE_WORLD_READABLE, null);
			String SQL = "CREATE TABLE IF NOT EXISTS usuario (_id INTEGER PRIMARY KEY autoincrement, usuario TEXT not null unique,email TEXT not null,nome TEXT not null, senha  TEXT not null,senhaConf TEXT not null)";
			BancoDeDados.execSQL(SQL);
		} catch (Exception erro) {
			MensagemAlerta("ERRO Banco de Dados",
					"Não foi possivel criar o banco!" + erro);

		} finally {
			BancoDeDados.close();
		}
	}

	// Insere os dados no BD
	public void GravaBanco() {
		try {
			BancoDeDados = openOrCreateDatabase(Nome_Banco,
					MODE_WORLD_READABLE, null);
			String SQL = "INSERT INTO usuario (usuario,email,nome,senha, senhaConf) VALUES ('"
					+ usuario.getText().toString()
					+ "','"
					+ email.getText().toString()
					+ "','"
					+ nome.getText().toString()
					+ "','"
					+ senha.getText().toString()
					+ "','"
					+ senhaConf.getText().toString() + "')";
			BancoDeDados.execSQL(SQL);

			MensagemAlerta("Banco de Dados", "Registro gravado com sucesso!");

		} catch (SQLiteConstraintException usuario) {
			MensagemAlerta("Banco de Dados", "Usuário já existe!");

		} catch (Exception erro) {
			MensagemAlerta("ERRO Banco de Dados",
					"Não foi possivel gravar o registro!");

		}

		finally {
			BancoDeDados.close();
		}

	}

	// Verifica se já existe o registro no BD
	boolean VerificaRegistro() {
		try {
			BancoDeDados = openOrCreateDatabase(Nome_Banco,
					MODE_WORLD_READABLE, null);
			cursor = BancoDeDados.rawQuery("select * from usuario", null);

			if (cursor.getCount() != 0) {
				cursor.moveToFirst();
				return true;
			} else
				return false;
		} catch (Exception erro) {
			MensagemAlerta("ERRO Banco de Dados",
					"Não foi possivel verificar dados!" + erro);
			return false;
		} finally {
			BancoDeDados.close();
		}
	}

	// Insere os dados no BD quando clicar no botão CADASTRAR
	public void btnSalvarDados() {

		salvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {

				if (nome.getText().toString().equals("")
						|| email.getText().toString().equals("")
						|| usuario.getText().toString().equals("")
						|| senha.getText().toString().equals("")
						|| senhaConf.getText().toString().equals("")) {
					MensagemAlerta("Erro", "Algum campo está vazio!");
					return;
				}

				GravaBanco();
				chamaLogin();
			}

		});

	}

	// Chama a tela de login
	public void chamaLogin() {

		Intent chamarLogin = new Intent(Cadastro.this, Login.class);
		Cadastro.this.startActivity(chamarLogin);

	}

	// Mensagem de alerta
	public void MensagemAlerta(String TituloAlerta, String MensagemAlerta) {
		AlertDialog.Builder Mensagem = new AlertDialog.Builder(Cadastro.this);
		Mensagem.setTitle(TituloAlerta);
		Mensagem.setMessage(MensagemAlerta);
		Mensagem.setNeutralButton("OK", null);
		Mensagem.show();
	}

	// Inicializa os objetos da classe Cadastro
	public void Inicializar() {
		usuario = (EditText) findViewById(R.id.campoUsuario);
		email = (EditText) findViewById(R.id.campoEmail);
		nome = (EditText) findViewById(R.id.campoNome);
		senha = (EditText) findViewById(R.id.campoSenhaCad);
		senhaConf = (EditText) findViewById(R.id.campoSenhaConf);
		salvar = (Button) findViewById(R.id.btCadastrar);

		CriaBanco();
		btnSalvarDados();
	}

}
