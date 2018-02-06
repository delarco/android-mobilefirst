package br.com.delarco.androidmobilefirst.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.delarco.androidmobilefirst.R;
import br.com.delarco.androidmobilefirst.data.response.StatementsResponse;

public class StatementsActivity extends AppCompatActivity {

    private static final String EXTRA_STATEMENTS_RESPONSE = "EXTRA_STATEMENTS_RESPONSE";

    private RecyclerView recyclerViewStatements;

    public static Intent getStartIntent(Context context, StatementsResponse statementsResponse) {
        return new Intent(context, StatementsActivity.class)
                .putExtra(EXTRA_STATEMENTS_RESPONSE, statementsResponse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);

        bindControls();
        setupRecyclerView();
    }

    private void bindControls() {
        recyclerViewStatements = findViewById(R.id.recycler_view_statements);
    }

    private void setupRecyclerView() {
        StatementsResponse statementsResponse = (StatementsResponse) getIntent().getSerializableExtra(EXTRA_STATEMENTS_RESPONSE);

        StatementsAdapter statementsAdapter = new StatementsAdapter(statementsResponse.getStatements());
        recyclerViewStatements.setHasFixedSize(true);
        recyclerViewStatements.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStatements.setAdapter(statementsAdapter);

    }

}
