package br.com.mariojp.mobile.aula1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Usando o Log para exibir no Logcat o ciclo de vida da Activity
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private FloatingActionButton botaoFormulario;
    private List<Tarefa> tarefas = new ArrayList<>();

    /**
     * onCreate()
     * Sua implementação é obrigatoria
     * É chamado pelo sistema para criar a Activity
     * A Activity é criada atravez dele e para ele é passado o estado (Bundle).
     * No método onCreate(), você executa a lógica básica de inicialização da Activity.
     * Isso deve acontecer somente uma vez durante todo o período que a Activity durar.
     * Boas praticas para sua implementação:
     * - vincular dados a listas
     * - associar a atividade a um ViewModel
     * - instanciar algumas variáveis com escopo de classe
     * Esse método recebe um parâmetro do tipo Bundle que contém o estado anteriormente salvo da Activity.
     * - Se a atividade nunca existiu, o valor do objeto Bundle será nulo.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        preencherLista();

        lista = findViewById(R.id.main_list_tarefas);
        botaoFormulario = findViewById(R.id.main_botao_add);

        checkIntent();
        ListAdapter adapter = new TarefaAdapter(this, tarefas);

        lista.setAdapter(adapter);

        botaoFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formulario();
            }
        });


    }

    private void formulario() {
        Intent intent = new Intent(this, AdicionarTarefa.class);

        intent.putExtra("tarefas", (Serializable) this.tarefas);
        startActivity(intent);
        finish();
    }


    private void checkIntent() {
        Intent intent = getIntent();

        if (intent.hasExtra("tarefas")) {
            List<Tarefa> tarefas = (List<Tarefa>) intent.getSerializableExtra("tarefas");
            this.tarefas = tarefas;
        }
    }

    private void preencherLista(){
        tarefas.add(new Tarefa("DEVER DE CASA","DESCRICAO !", 1));
        tarefas.add(new Tarefa("ASSITIR AULA", "DESCRICAO !", 2));
        tarefas.add(new Tarefa("TOMAR BANHO","DESCRICAO !",1));
        tarefas.add(new Tarefa("LER UM LIVRO", "DESCRICAO !", 3));
    }

}