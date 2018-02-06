package br.com.delarco.androidmobilefirst.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.worklight.wlclient.api.WLAccessTokenListener;
import com.worklight.wlclient.api.WLAuthorizationManager;
import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResourceRequest;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;
import com.worklight.wlclient.auth.AccessToken;

import java.net.URI;

import br.com.delarco.androidmobilefirst.R;
import br.com.delarco.androidmobilefirst.data.response.BalanceResponse;
import br.com.delarco.androidmobilefirst.data.response.StatementsResponse;

public class MainActivity extends AppCompatActivity {

    private WLClient client;

    private EditText editAccount;
    private Button buttonBalance;
    private Button buttonStatements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMobileFirst();
        bindControls();
        attachEvents();
    }

    private void bindControls() {
        editAccount = findViewById(R.id.edit_account);
        buttonBalance = findViewById(R.id.button_balance);
        buttonStatements = findViewById(R.id.button_statements);
    }

    private void attachEvents() {
        buttonBalance.setOnClickListener(buttonBalanceOnClick);
        buttonStatements.setOnClickListener(buttonStatementsOnClick);
    }

    private void initMobileFirst() {

        // Instantiate client
        client = WLClient.createInstance(this);

        // Get an access token
        WLAuthorizationManager.getInstance().obtainAccessToken(null, new WLAccessTokenListener() {
            @Override
            public void onSuccess(AccessToken accessToken) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        editAccount.setEnabled(true);
                        buttonBalance.setEnabled(true);
                        buttonStatements.setEnabled(true);
                    }
                });
            }

            @Override
            public void onFailure(WLFailResponse wlFailResponse) {
                showAlert("Access Token", "Error: " + wlFailResponse.getErrorMsg());
            }
        });
    }

    private void showAlert(final String title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("Close", null)
                        .create();

                alertDialog.show();
            }
        });
    }

    private void adapterRequest(String url, WLResponseListener wlResponseListener) {
        WLResourceRequest request = new WLResourceRequest(
                URI.create(url),
                WLResourceRequest.GET, null);

        request.setQueryParameter("acc", editAccount.getText().toString());

        request.send(wlResponseListener);
    }

    private View.OnClickListener buttonBalanceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            adapterRequest("/adapters/AccountAdapter/resource/account", new WLResponseListener() {
                @Override
                public void onSuccess(WLResponse wlResponse) {
                    BalanceResponse balanceResponse = new Gson().fromJson(wlResponse.getResponseText(), BalanceResponse.class);
                    showAlert("Account Balance: ", "Costumer: " + balanceResponse.getCostumer() + "\n" + "Balance: " + balanceResponse.getBalance());
                }

                @Override
                public void onFailure(WLFailResponse wlFailResponse) {
                    showAlert("Account Balance", "Error: " + wlFailResponse.getErrorMsg());
                }
            });
        }
    };

    private View.OnClickListener buttonStatementsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            adapterRequest("/adapters/AccountAdapter/resource/account/statements", new WLResponseListener() {
                @Override
                public void onSuccess(WLResponse wlResponse) {
                    StatementsResponse statementsResponse = new Gson().fromJson(wlResponse.getResponseText(), StatementsResponse.class);
                    startActivity(StatementsActivity.getStartIntent(MainActivity.this, statementsResponse));
                }

                @Override
                public void onFailure(WLFailResponse wlFailResponse) {
                    showAlert("Account Statements", "Error: " + wlFailResponse.getErrorMsg());
                }
            });
        }
    };

}
