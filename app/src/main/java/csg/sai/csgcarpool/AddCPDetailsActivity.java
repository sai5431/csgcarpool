package csg.sai.csgcarpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.xml.transform.Source;

public class AddCPDetailsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    TextView FullName , Destination, Seats, Phonenumber,Source;
    Button Save;
    
    boolean sORR;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cpdetails);
        FullName = (TextView) findViewById(R.id.addName);
        Source = (TextView) findViewById(R.id.source);
        Destination = (TextView) findViewById(R.id.destination);
        Seats = (TextView) findViewById(R.id.seats);
        Phonenumber= (TextView) findViewById(R.id.phoneNumber);
        Save = (Button) findViewById(R.id.btn_save);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Destination.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sORR = false;
                boolean status= findPlace(v);
                return status;
            }
        });

        Source.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sORR = true;
                boolean status= findPlace(v);
                return status;
            }

        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = FullName.getText().toString();
                String destin = Destination.getText().toString();
                String noSeats = Seats.getText().toString();
                String phno = Phonenumber.getText().toString();

                String key = mDatabase.push().getKey();

                CarPoolDetails carPoolDetails = new CarPoolDetails();
                carPoolDetails.setName(name);
                carPoolDetails.setDestination(destin);
                carPoolDetails.setSeats(noSeats);
                carPoolDetails.setPhoneNumber(phno);
                if(!TextUtils.isEmpty(name)) {
                    mDatabase.child(key).setValue(carPoolDetails);
                    FullName.setText("");
                    Destination.setText("");
                    Seats.setText("");
                    Phonenumber.setText("");
                    Toast.makeText(AddCPDetailsActivity.this,"Car added to pool successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddCPDetailsActivity.this,"Please enter ur name",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean findPlace(View view){
        try {
            AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(Place.TYPE_COUNTRY).setCountry("IN")
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS).build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).setFilter(autocompleteFilter).build(this);
            startActivityForResult(intent,1);
        }catch (GooglePlayServicesRepairableException e){
            Toast.makeText(AddCPDetailsActivity.this,"Not Available",Toast.LENGTH_SHORT).show();
        }catch (GooglePlayServicesNotAvailableException e){
            Toast.makeText(AddCPDetailsActivity.this,"Not Available",Toast.LENGTH_SHORT).show();
        }

        return  true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1){
            if (resultCode == RESULT_OK){

                Place place = PlaceAutocomplete.getPlace(this, data);
                if(sORR == true) {
                    ((TextView) findViewById(R.id.source)).setText(place.getName());
                }
                else if(sORR == false){
                    ((TextView) findViewById(R.id.destination)).setText(place.getName());
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    
}
