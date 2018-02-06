package br.com.delarco.androidmobilefirst.data.response;

import java.io.Serializable;

import br.com.delarco.androidmobilefirst.data.model.Statement;

public class StatementsResponse implements Serializable {

    private Statement[] statements;

    public StatementsResponse(Statement[] statements) {
        this.statements = statements;
    }

    public Statement[] getStatements() {
        return statements;
    }
}
