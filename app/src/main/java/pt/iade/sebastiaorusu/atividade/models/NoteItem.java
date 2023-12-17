package pt.iade.sebastiaorusu.atividade.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;
import java.util.GregorianCalendar;


public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    private Calendar modificationDate;
    private Calendar creationDate;

    public NoteItem() {
        this(0, "", "", Calendar.getInstance());
    }
    public NoteItem(int id, String title, String content, Calendar modificationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.modificationDate = modificationDate;

    }

    public static ArrayList<NoteItem> List(){
        ArrayList<NoteItem> note = new ArrayList<>();
        note.add(new NoteItem(1, "Note 1", "Content 1", new GregorianCalendar(2021, 10, 1)));
        note.add(new NoteItem(2, "Note 2", "Content 2", new GregorianCalendar(2022, 9, 2)));
        note.add(new NoteItem(3, "Note 3", "Content 3", new GregorianCalendar(2023, 8, 3)));
        return note;
    }
    public static NoteItem getById(int id){
        return new NoteItem(id, "note 1", "Content 2", new GregorianCalendar(2021, 10, 1));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Calendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }
}
