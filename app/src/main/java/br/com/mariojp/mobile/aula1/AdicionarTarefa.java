package br.com.mariojp.mobile.aula1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AdicionarTarefa extends AppCompatActivity {

    private EditText tarefaTitle;
    private EditText tarefaDescription;
    private TextView priority;
    private SeekBar tarefaPriority;
    private Button tarefaAdd;
    private List<Tarefa> tarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        getElements();
        checkIntent();


        tarefaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa tarefa = getTarefa();
                tarefas.add(tarefa);
                passList();
            }
        });

        tarefaPriority.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                priority.setText(String.valueOf(tarefaPriority.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void passList() {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("tarefas", (Serializable) this.tarefas);
        startActivity(intent);
        finish();
    }

    private Tarefa getTarefa(){
        Tarefa newTarefa = new Tarefa();
        newTarefa.setDescricao(tarefaDescription.getText().toString());
        newTarefa.setTitulo(tarefaTitle.getText().toString());
        newTarefa.setPrioridade(tarefaPriority.getProgress());

        return newTarefa;
    }

    private void checkIntent() {
        Intent intent = getIntent();

        if (intent.hasExtra("tarefas")) {
            List<Tarefa> tarefas = (List<Tarefa>) intent.getSerializableExtra("tarefas");
            this.tarefas = tarefas;
        }
    }

    private void getElements(){
        tarefaTitle = findViewById(R.id.editTextTarefaTitle);
        tarefaDescription = findViewById(R.id.editTextTarefaDescricao);
        tarefaPriority = findViewById(R.id.prioritySlider);
        tarefaAdd = findViewById(R.id.buttonAdicionar);
        priority = findViewById(R.id.textViewPrioridade);
    }

}