package csg.sai.csgcarpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    private Button btn_pool, btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btn_edit =(Button) findViewById(R.id.btn_re_ed);
        btn_pool = (Button) findViewById(R.id.btn_retreive);
        TextView tv = (TextView) findViewById(R.id.texthappy);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this,MainActivity.class));
            }
        });

        btn_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this,RetrieveCPDetailsActivity.class));
            }
        });
    }
}
