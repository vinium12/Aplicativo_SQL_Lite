package com.example.appsql_lite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// O construtor primário da classe agora está definido corretamente.
// 'context: Context' e 'factory: SQLiteDatabase.CursorFactory?' são parâmetros do construtor.
// O 'super' chama o construtor da classe pai (SQLiteOpenHelper).
class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) { // 'factory' geralmente é null


    override fun onCreate(db: SQLiteDatabase) {
        // A query SQL para criar a tabela.
        // A interpolação de strings em Kotlin (`$`) é usada para tornar a query mais legível.
        val query = "CREATE TABLE $TABLE_NAME (" +
                "$ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, " + // Adicionado AUTOINCREMENT
                "$NAME_COL TEXT," +
                "$END_COL TEXT," +
                "$BAR_COL TEXT," +
                "$CEP_COL TEXT" +
                ")"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Dropa a tabela existente se ela existir e recria.
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPessoa(name: String, endereco: String, bairro: String, cep: String) {
        // ContentValues é usado para armazenar um conjunto de valores que uma ContentResolver pode processar.
        val values = ContentValues().apply {
            put(NAME_COL, name)
            put(END_COL, endereco)
            put(BAR_COL, bairro)
            put(CEP_COL, cep)
        }

        // Obtemos uma instância do banco de dados em modo de escrita.
        val db = this.writableDatabase

        // Inserimos os valores na tabela.
        db.insert(TABLE_NAME, null, values)

        // É importante fechar o banco de dados após a operação para liberar recursos.
        db.close()
    }

    // O objeto companion é onde definimos constantes estáticas para a classe.
    companion object {
        // Nome do banco de dados
        private const val DATABASE_NAME = "appSQLite"

        // Versão do banco de dados
        private const val DATABASE_VERSION = 1

        // Nome da tabela
        const val TABLE_NAME = "CadastroPessoa"

        // Colunas da tabela
        const val ID_COL = "id"
        const val NAME_COL = "name"
        const val END_COL = "endereco"
        const val BAR_COL = "bairro"
        const val CEP_COL = "cep"
    }
}