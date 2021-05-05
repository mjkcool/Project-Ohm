package kr.hs.emirim.ohm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Chat extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter chatAapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_View);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String[] myDataset = {"test1","test2","test3","test4"};
        chatAapter = new ChatAdapter(myDataset);
        recyclerView.setAdapter(chatAapter);

    }
}