package com.example.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //Criando um banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criando uma tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, sexo VARCHAR, idade INT(3))");

            //Apagando uma tabela
            //bancoDados.execSQL("DROP TABLE pessoas");

            //Inserindo os registros
            bancoDados.execSQL("INSERT INTO pessoas (nome, sexo, idade) VALUES ('Evanio', 'Masculino', 45)");
            bancoDados.execSQL("INSERT INTO pessoas (nome, sexo, idade) VALUES ('Mariana', 'Feminino', 19)");

            //Atualizando uma tabela
            bancoDados.execSQL("UPDATE pessoas SET idade = 20 WHERE id = 2 ");

            //Deletando um registro do banco de dados
            bancoDados.execSQL("DELETE FROM pessoas WHERE id = 1");

            //Recuperar os registros em pessoas
            String consulta =
                    //Pode ser usado SELECET *, que exibirá todos os campos da consulta
                    "SELECT id, nome, sexo, idade FROM pessoas" +
                            " WHERE 1=1";

            // IN() Consulta dentro dos valores inseridos
            //BETWEEN Consulta valores como idade BETWEEN 30 AND 35
            //Like Exemplo nome LIKE 'Luc%', procuraria todos os nomes com a inicial Luc
            //ORDER BY idade ASC 3 , organizaram os dados por idade em ordem ascendente e limitaria em 3


            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //Indices da tabelas

            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceSexo = cursor.getColumnIndex("sexo");
            int indiceIdade = cursor.getColumnIndex("idade");
            cursor.moveToFirst();

            while(cursor != null){

                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String sexo = cursor.getString(indiceSexo);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO - id", id + " Nome: " + nome + " Sexo: " + sexo + " Idade: " + idade);

                cursor.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}