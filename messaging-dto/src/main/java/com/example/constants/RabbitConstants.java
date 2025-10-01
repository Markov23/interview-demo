package com.example.constants;

public class RabbitConstants {
    //------------------------------------------------------
    //                     EXCHANGES
    //------------------------------------------------------
    public static final String CLIENT_VALIDATION_EXCHANGE = "client.validation.exchange";
    public static final String ACCOUNT_STATEMENT_EXCHANGE = "account.statement.exchange";

    //------------------------------------------------------
    //                      QUEUES
    //------------------------------------------------------
    public static final String CLIENT_VALIDATION_QUEUE = "client.validation.request";
    public static final String ACCOUNT_STATEMENT_QUEUE = "account.statement.request";

    //------------------------------------------------------
    //                        BINDS
    //------------------------------------------------------
    public static final String CLIENT_VALIDATION_BIND = "client.validation";
    public static final String ACCOUNT_STATEMENT_BIND = "account.statement";
}
