package csg.sai.csgcarpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveCPDetailsActivity extends AppCompatActivity {

    private DatabaseReference rdatabase;
    RecyclerView recyclerview;
    Button poolbtn;
    List<CPDetailsList> cpDetailsLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_cpdetails);

        recyclerview = (RecyclerView) findViewById(R.id.cpdview);
        poolbtn = (Button) findViewById(R.id.btn_pool);

        rdatabase = FirebaseDatabase.getInstance().getReference();
    
        cpDetailsLists = new ArrayList<>();
    
        final RetrieveCPDetailsAdapter retrieveCPDetailsAdapter = new RetrieveCPDetailsAdapter(cpDetailsLists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrieveCPDetailsActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(retrieveCPDetailsAdapter);
    
      
        

        rdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    CarPoolDetails carPoolDetails = dataSnapshot1.getValue(CarPoolDetails.class);
                    CPDetailsList cpList = new CPDetailsList();
                    String name = carPoolDetails.getName();
                    String destin = carPoolDetails.getDestination();
                    String seta = carPoolDetails.getSeats();
                    String phnooo = carPoolDetails.getPhoneNumber();
                    cpList.setName(name);
                    cpList.setDestination(destin);
                    cpList.setSeats(seta);
                    cpList.setPhoneNumber(phnooo);
                    retrieveCPDetailsAdapter.updateData(cpList);
                    
                }
                

                
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        poolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( RetrieveCPDetailsActivity.this,AddCPDetailsActivity.class));
            }
        });

    }
}
