package pt.iade.sebastiaorusu.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import pt.iade.sebastiaorusu.atividade.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected NoteItem note;
    protected EditText titleEdit;
    protected EditText contentEdit;
    protected EditText modificationDateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //get the item from the intent
        Intent intent = getIntent();
        note = (NoteItem) intent.getSerializableExtra("note");
        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_note){
            //Action "save"
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents(){

        setSupportActionBar(findViewById(R.id.toolbar));

        titleEdit = (EditText) findViewById(R.id.titulo_txt);
        contentEdit = (EditText) findViewById(R.id.txt_notes);
        modificationDateEdit = (EditText) findViewById(R.id.date_view);
        populateView();

    }

    protected void populateView(){
        titleEdit.setText(note.getTitle());
        contentEdit.setText(note.getContent());
        modificationDateEdit.setText(new SimpleDateFormat("dd-MM-yyyy").format(note.getModificationDate().getTime()));
    }
}