package com.example.appsql_lite; // Este package está correto para a sua Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
// Corrija o import para apontar para o local correto do seu DBHelper
import com.example.appsql_lite.db.DBHelper // <-- Import corrigido!

class RespostaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val btnConfirmar:Button = findViewById(R.id.btnConfirmar)

        edtNome.setText(intent.getStringExtra("nome"))
        edtEndereco.setText(intent.getStringExtra("endereco"))
        edtBairro.setText(intent.getStringExtra("bairro"))
        edtCep.setText(intent.getStringExtra("cep"))

        // SQLite
        // Instancie o DBHelper passando apenas o contexto (this)
        val db = DBHelper(this) // <-- Construtor corrigido!

        btnConfirmar.setOnClickListener {
            db.addPessoa(edtNome.text.toString(), edtEndereco.text.toString(), edtBairro.text.toString(),
                edtCep.text.toString())
            finish()
        }
    }
}