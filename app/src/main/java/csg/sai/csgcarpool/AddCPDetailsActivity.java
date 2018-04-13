package csg.sai.csgcarpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCPDetailsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    TextView FullName , Destination, Seats, Phonenumber;
    Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cpdetails);
        FullName = (TextView) findViewById(R.id.addName);
        Destination = (TextView) findViewById(R.id.destination);
        Seats = (TextView) findViewById(R.id.seats);
        Phonenumber= (TextView) findViewById(R.id.phoneNumber);
        Save = (Button) findViewById(R.id.btn_save);

        mDatabase = FirebaseDatabase.getInstance().getReference();


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
}
