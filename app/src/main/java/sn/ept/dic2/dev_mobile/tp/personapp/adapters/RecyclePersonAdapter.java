package sn.ept.dic2.dev_mobile.tp.personapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.List;

import sn.ept.dic2.dev_mobile.tp.personapp.R;
import sn.ept.dic2.dev_mobile.tp.personapp.models.Person;

public class RecyclePersonAdapter extends RecyclerView.Adapter<RecyclePersonAdapter.MyViewHolder> {

    List<Person> persons;

    private OnItemClickListener listener;

    public RecyclePersonAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_card, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fullName.setText(new StringBuilder().append(persons.get(position).getFirstName()).append(" ").append(persons.get(position).getLastName()).toString());
        try {
            holder.dateModification.setText(persons.get(position).getDateInSimpleFormat());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        return this.persons.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fullName;
        TextView dateModification;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.full_name_person);
            dateModification = itemView.findViewById(R.id.date_modification_person);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(persons.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Person model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener  = listener;
    }

}
