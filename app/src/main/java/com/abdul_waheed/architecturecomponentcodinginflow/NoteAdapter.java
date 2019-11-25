package com.abdul_waheed.architecturecomponentcodinginflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> allNotes = new ArrayList<>();
    private OnItemClickListener clickListener;

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvPriority, tvDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPriority = itemView.findViewById(R.id.tv_priority);
            tvDescription = itemView.findViewById(R.id.tv_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        clickListener.onItemClick(allNotes.get(getAdapterPosition()));
                }
            });
        }
    }


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = allNotes.get(position);
        holder.tvPriority.setText(String.valueOf(currentNote.getId()));
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvTitle.setText(currentNote.getTitle());
    }

    public void setAllNotes(List<Note> notes) {
        allNotes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return allNotes.get(position);
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
