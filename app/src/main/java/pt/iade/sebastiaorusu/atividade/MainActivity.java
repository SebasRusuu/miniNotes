package pt.iade.sebastiaorusu.atividade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

import pt.iade.sebastiaorusu.atividade.adapters.NoteItemAdapter;
import pt.iade.sebastiaorusu.atividade.models.NoteItem;

public class MainActivity extends AppCompatActivity {
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView note_list;
    protected NoteItemAdapter noteAdapter;

    protected ArrayList<NoteItem> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note) {
            //Action "add" button
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            intent.putExtra("position", -1);
            intent.putExtra("note", new NoteItem());


            startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDITOR_ACTIVITY_RETURN_ID) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                int position = data.getIntExtra("position", -1);
                boolean isDelete = data.getBooleanExtra("delete", false);

                if (isDelete && position != -1) {
                    // Remova a nota da lista
                    noteList.remove(position);
                    noteAdapter.notifyItemRemoved(position);
                } else {
                    NoteItem updateNote = (NoteItem) data.getSerializableExtra("note");

                    if (position == -1) {
                        // Adicionar nova nota
                        noteList.add(updateNote);
                        noteAdapter.notifyItemInserted(noteList.size() - 1);
                    } else {
                        // Atualizar a nota existente
                        if (!noteList.get(position).getTitle().equals(updateNote.getTitle()) || !noteList.get(position).getContent().equals(updateNote.getContent())) {
                            noteList.set(position, updateNote);
                            noteAdapter.notifyItemChanged(position);
                        } else {
                            updateNote.setModificationDate(Calendar.getInstance());
                        }
                    }
                }
            }
        }
    }


    private void setupComponents() {

        setSupportActionBar(findViewById(R.id.toolbar));
        note_list = (RecyclerView)findViewById(R.id.note_list_view);
        note_list.setLayoutManager(new LinearLayoutManager(this));

        NoteItem.List(new NoteItem.ListResponse(){
            @Override
            public void response(ArrayList<NoteItem> notes) {
                noteList = notes;

                noteAdapter = new NoteItemAdapter(MainActivity.this, noteList);
                noteAdapter.setOnClickListener(new NoteItemAdapter.NoteClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("note", noteList.get(position));
                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

                    }

                });
                note_list.setAdapter(noteAdapter);
            }

        });


    }
}