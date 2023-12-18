package pt.iade.sebastiaorusu.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import pt.iade.sebastiaorusu.atividade.adapters.NoteItemAdapter;
import pt.iade.sebastiaorusu.atividade.models.NoteItem;

public class MainActivity extends AppCompatActivity {
    protected RecyclerView note_list;
    protected NoteItemAdapter noteAdapter;

    protected ArrayList<NoteItem> noteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteList = NoteItem.List();
        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_note){
            //Action "add" button
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            intent.putExtra("note", new NoteItem());
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents(){

        setSupportActionBar(findViewById(R.id.toolbar));
        note_list = findViewById(R.id.note_list_view);
        note_list.setLayoutManager(new LinearLayoutManager(this));


        noteAdapter = new NoteItemAdapter(this, noteList);
        noteAdapter.setOnClickListener(new NoteItemAdapter.NoteClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("note", noteList.get(position));
                startActivity(intent);

            }

        });
        note_list.setAdapter(noteAdapter);

    }
}