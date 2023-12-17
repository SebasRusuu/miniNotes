package pt.iade.sebastiaorusu.atividade.models;

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
