package com.spaikdeveloper.firebasebitm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.setTitle("User Details");

       recyclerViewRV = findViewById(R.id.recycleviewRV);
       LinearLayoutManager llm = new LinearLayoutManager(this);
       recyclerViewRV.setLayoutManager(llm);

       MyAdapter adapter = new MyAdapter(this,MainActivity.userList);
       adapter.notifyDataSetChanged();
       recyclerViewRV.setAdapter(adapter);


    }
}
