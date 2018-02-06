package br.com.delarco.androidmobilefirst.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.delarco.androidmobilefirst.R;
import br.com.delarco.androidmobilefirst.data.model.Statement;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.StatementsViewHolder> {

    private Statement[] statements;

    public StatementsAdapter(Statement[] statements) {
        this.statements = statements;
    }

    @Override
    public StatementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_statement, parent, false);

        return new StatementsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatementsViewHolder holder, int position) {
        holder.bindItem(statements[position]);
    }

    @Override
    public int getItemCount() {
        return statements.length;
    }

    class StatementsViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDescription;
        private TextView textViewValue;
        private TextView textViewBalance;

        public StatementsViewHolder(View itemView) {
            super(itemView);

            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewValue = itemView.findViewById(R.id.text_view_value);
            textViewBalance = itemView.findViewById(R.id.text_view_balance);
        }

        public void bindItem(Statement statement) {
            textViewDescription.setText(statement.getDescription());
            textViewValue.setText("" + statement.getValue() + " " + statement.getType());
            textViewBalance.setText("" + statement.getBalance());

            if(statement.getType() == 'D') {
                textViewValue.setTextColor(Color.RED);
            } else {
                textViewValue.setTextColor(Color.BLUE);
            }
        }
    }
}
