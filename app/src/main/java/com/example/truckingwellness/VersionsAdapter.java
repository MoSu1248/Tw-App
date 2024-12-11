package com.example.truckingwellness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VersionsAdapter extends RecyclerView.Adapter<VersionsAdapter.VersionVH> {

    List<Versions> versionsList;

    public VersionsAdapter(List<Versions> versionsList) {
        this.versionsList = versionsList;
    }

    @NonNull
    @Override
    public VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
    return new VersionVH(view);


    }

    @Override
    public void onBindViewHolder(@NonNull VersionVH holder, int position) {
    Versions versions = versionsList.get(position);
    holder.codeNameTxt.setText(versions.getCodeName());
    holder.versionTxt.setText(versions.getVersion());
    holder.descriptionTxt.setText(versions.getDescription());

    boolean isExpandable = versionsList.get(position).isExpandable();
    holder.expandableLayout.setVisibility((isExpandable ? View.VISIBLE: View.GONE));
    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        RelativeLayout expandableLayout ;


        TextView codeNameTxt, versionTxt, apiLevelTxt, descriptionTxt;

        public VersionVH(@NonNull View itemView) {
            super(itemView);

            codeNameTxt = itemView.findViewById(R.id.code_name);
            versionTxt =itemView.findViewById(R.id.nurse_notes_heading);

            descriptionTxt=itemView.findViewById(R.id.medication_listed);

            linearLayout =itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);



            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Versions versions = versionsList.get(getAbsoluteAdapterPosition());
                    versions.setExpandable((!versions.isExpandable()));
                    notifyItemChanged(getAbsoluteAdapterPosition());

                }
            });

        }
    }
}
