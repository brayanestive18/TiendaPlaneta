package com.brayadiaz.tiendaplaneta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;



public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactosViewHolder>{

    Context context;
    private ArrayList<User> userArrayList;

    public ContactosAdapter(Context context,ArrayList<User> userArrayList) {
        super();
        this.context=context;
        this.userArrayList=userArrayList;
    }

    @Override
    public ContactosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem,parent,false);
        ContactosViewHolder contactosViewHolder = new ContactosViewHolder(itemView);

        return contactosViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactosViewHolder holder, int position) {
        User item = userArrayList.get(position);
        holder.bindContactos(item,context);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ContactosViewHolder extends RecyclerView.ViewHolder{

        private TextView tId,tName,tAutor,tPresta, tPhone;

        public ContactosViewHolder (View itemView){
            super(itemView);
            tId=itemView.findViewById(R.id.tID);
            tName=itemView.findViewById(R.id.tName);
            tAutor=itemView.findViewById(R.id.tAutor);
            tPresta=itemView.findViewById(R.id.tPresta);
            tPhone=itemView.findViewById(R.id.tPhone);
        }
        public void bindContactos(User user, Context context){
            tId.setText(user.getUid());
            tName.setText(user.getName());
            tAutor.setText(user.getAutor());
            tPresta.setText(user.getPresta());
            tPhone.setText(user.getPhone());
        }
    }
}