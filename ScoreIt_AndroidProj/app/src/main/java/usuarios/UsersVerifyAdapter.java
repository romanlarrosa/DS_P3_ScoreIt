package usuarios;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scoreit.R;

import java.io.IOException;
import java.util.List;

public class UsersVerifyAdapter extends RecyclerView.Adapter<UsersVerifyAdapter.MyViewHolder> {


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombre;
        Button eliminar, verificar;

        public MyViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.user_list);
            eliminar = (Button) itemView.findViewById(R.id.delete_button);
            verificar = (Button) itemView.findViewById(R.id.verify_button);
        }
    }

    private List<Usuario> usuarios;

    public UsersVerifyAdapter(List<Usuario> usuarios){
        this.usuarios = usuarios;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View userView = inflater.inflate(R.layout.admin_user_layout, parent, false);

        //Return a viewholder instance
        return new MyViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        //Get a data model based on the position
        final Usuario user = usuarios.get(position);

        //Set item views based on your views and data model
        TextView textView = viewHolder.nombre;
        textView.setText(user.getNombre());

        final Button botonEliminar, botonVerificar;
        botonEliminar = viewHolder.eliminar;
        botonVerificar = viewHolder.verificar;
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PRUEBA ELIMINAR", "SE ELIMINA EL USUARIO" + user.getId());
                //ELIMINAR UN USUARIO
                String mensaje = "";
                mensaje = user.eliminar();

                Log.d("PRUEBA ELIMINAR", " " + mensaje);
                botonVerificar.setVisibility(View.INVISIBLE);
                botonEliminar.setText(R.string.deleted);

            }
        });

        botonVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PRUEBA VERIFICAR", "SE VERIFICA EL USUARIO" + user.getId());
                //VERIFICAR UN USUARIO
                String mensaje = "";
                try {
                    mensaje = user.verificar();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("PRUEBA VERIFICAR", " " + mensaje);
                botonVerificar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {

        return usuarios.size();
    }

}
