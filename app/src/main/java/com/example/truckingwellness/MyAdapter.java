package com.example.truckingwellness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;

    ArrayList<User> userArrayList;

    LinearLayout expand ;



    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {




        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.Nurse_Name.setText(user.Nurse_Name);
        holder.Nurse_Id.setText(user.Nurse_Id);
        holder.Appointment_ID.setText(user.Appointment_ID);
        holder.Appointment_Date.setText(user.Appointment_Date);
        holder.Appointment_Location.setText(user.Appointment_Location);
        holder.Medication_Administered.setText(user.Medication_Administered);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Nurse_Name , Surname , Appointment_Date , Appointment_ID , Appointment_Location , Nurse_Id , Medication_Administered;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Nurse_Name = itemView.findViewById(R.id.tvnurseName2);
//            Surname = itemView.findViewById(R.id.tvappointment_date);
            Appointment_Date = itemView.findViewById(R.id.tvappointment_date);
            Appointment_ID = itemView.findViewById(R.id.tvappointment_ID);
            Appointment_Location = itemView.findViewById(R.id.tvappointment_location);
            Nurse_Id = itemView.findViewById(R.id.tvnurseID);
            Medication_Administered = itemView.findViewById(R.id.tvmed);



        }
    }

}
