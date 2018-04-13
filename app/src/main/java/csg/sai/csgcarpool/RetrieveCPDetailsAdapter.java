package csg.sai.csgcarpool;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RetrieveCPDetailsAdapter extends RecyclerView.Adapter<RetrieveCPDetailsAdapter.MyHolder> {
    
    List<CPDetailsList> carPoolDetails;
    
    public RetrieveCPDetailsAdapter(List<CPDetailsList> scarPoolDetails) {
        this.carPoolDetails = scarPoolDetails;
    }
    
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myview, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }
    
    
    public void updateData(CPDetailsList cpDetailsList) {
        carPoolDetails.add(cpDetailsList);
        notifyDataSetChanged();
    }
    
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CPDetailsList carPoolDetails1 = carPoolDetails.get(position);
        holder.vname.setText(carPoolDetails1.getName());
        holder.vdestination.setText(carPoolDetails1.getDestination());
        holder.vseats.setText(carPoolDetails1.getSeats());
        holder.vphoneno.setText(carPoolDetails1.getPhoneNumber());
    }
    
    @Override
    public int getItemCount() {
        return carPoolDetails.size();
    }
    
    class MyHolder extends RecyclerView.ViewHolder {
        TextView vname, vdestination, vseats, vphoneno;
        
        public MyHolder(View view) {
            super(view);
            vname = (TextView) view.findViewById(R.id.vPName);
            vdestination = (TextView) view.findViewById(R.id.vPDestination);
            vseats = (TextView) view.findViewById(R.id.vPSeats);
            vphoneno = (TextView) view.findViewById(R.id.vPPhno);
        }
    }
}
