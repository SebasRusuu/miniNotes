package pt.iade.sebastiaorusu.atividade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import pt.iade.sebastiaorusu.atividade.R;
import pt.iade.sebastiaorusu.atividade.models.NoteItem;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {
    private ArrayList<NoteItem> notes;
    private LayoutInflater inflater;
    private NoteClickListener clickListener;

    public NoteItemAdapter(Context context, ArrayList<NoteItem> items) {
        inflater = LayoutInflater.from(context);
        notes = items;
        clickListener = null;
    }

    public void setOnClickListener(NoteClickListener listener) {

        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_note_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteItem currentNote = notes.get(position);

        holder.TitleLabel.setText(currentNote.getTitle());
        holder.NotesLabel.setText(currentNote.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView TitleLabel;
        public TextView NotesLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            TitleLabel = itemView.findViewById(R.id.txt_view_row);
            NotesLabel = itemView.findViewById(R.id.notes_view_row);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface NoteClickListener {
        void onItemClick(View view, int position);
    }
}
