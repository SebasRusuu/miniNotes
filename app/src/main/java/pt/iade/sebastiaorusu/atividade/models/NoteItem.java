package pt.iade.sebastiaorusu.atividade.models;

import static android.system.Os.remove;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;
import java.util.GregorianCalendar;

import pt.iade.sebastiaorusu.atividade.utilities.WebRequest;


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

    public static void List(ListResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/noteitems"));
                    String resp = request.performGetRequest();
                    //get the array from the webserver response
                    JsonObject json = new Gson().fromJson(resp, JsonObject.class);
                    JsonArray array = json.getAsJsonArray("notes");

                    //convert JSON to items
                    ArrayList<NoteItem> notes = new ArrayList<NoteItem>();
                    for (JsonElement elem : array){
                        notes.add(new Gson().fromJson(elem, NoteItem.class));
                    }

                    response.response(notes);
                } catch (Exception e) {
                    Log.e("NoteItem", e.toString());
                }
            }
        });
        thread.start();
    }

    public static void getById(int id, GetByIdResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/noteitems" + id));
                    String resp = request.performGetRequest();

                    NoteItem note = new Gson().fromJson(resp, NoteItem.class);
                    response.response(note);
                } catch (Exception e) {
                    Log.e("NoteItem", e.toString());
                }
            }
        });
        thread.start();
    }

    public void save() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    if (id == 0) {
                          WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/noteitems"));
                          String response = request.performPostRequest(NoteItem.this);

                          NoteItem responseItem = new Gson().fromJson(response, NoteItem.class);
                            id = responseItem.getId();
                    } else {
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/noteitems" + id));
                        request.performPostRequest(NoteItem.this);
                    }
                } catch (Exception e) {
                    Log.e("NoteItem", e.toString());
                }
            }
        });
        thread.start();
    }

    public void delete() {
        if (id != 0) {
            //delete
        }
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

    public interface ListResponse {
        public void response(ArrayList<NoteItem> notes);
    }

    public interface GetByIdResponse {
        public void response(NoteItem note);
    }
}
