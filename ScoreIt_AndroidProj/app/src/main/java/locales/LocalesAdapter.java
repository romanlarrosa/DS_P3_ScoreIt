package locales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scoreit.R;

import org.json.JSONException;

import java.util.List;

public class LocalesAdapter extends RecyclerView.Adapter<LocalesAdapter.MyViewHolder> {


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView nombre, ubicacion;
        OnLocalListener onLocalListener;

        public MyViewHolder(View itemView, OnLocalListener onLocalListener) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.local_name);
            ubicacion = (TextView) itemView.findViewById(R.id.local_location);
            this.onLocalListener = onLocalListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                onLocalListener.onLocalClick(getAdapterPosition());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnLocalListener{
        void onLocalClick(int position) throws JSONException;
    }

    private List<Local> locales;
    private OnLocalListener mOnLocalListener;

    public LocalesAdapter(List<Local> locales, OnLocalListener onLocalListener){
        this.locales = locales;
        this.mOnLocalListener = onLocalListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View userView = inflater.inflate(R.layout.local_list_layout, parent, false);

        //Return a viewholder instance
        return new MyViewHolder(userView, mOnLocalListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        //Get a data model based on the position
        final Local _local = locales.get(position);

        //Set item views based on your views and data model
        TextView textViewUbicacion = viewHolder.ubicacion;
        textViewUbicacion.setText(_local.getUbicacion());

        TextView textViewNombre = viewHolder.nombre;
        textViewNombre.setText(_local.getNombre());

    }

    @Override
    public int getItemCount() {

        return locales.size();
    }

}
