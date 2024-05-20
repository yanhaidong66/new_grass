package top.haidong556.filter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class VerificationFilterTest {
    static VerificationFilter verificationFilter;
    static String jwt;
    @BeforeAll
    static void init(){
        verificationFilter =new VerificationFilter();
        jwt="eyJ0eXAiOiJKV1QiLCJ0eXBlIjoiSldUIiwiYWxnIjoiUlMyNTYiLCJhbGdvcml0aG0iOiJSU0EyNTYifQ.eyJhdWRpZW5jZSI6ImV4YW1wbGVSZXF1ZXN0ZXIiLCJhdXRob3JpdHkiOiJhbGwiLCJpc3N1ZWRBdCI6IjE3MTQ0NDE4NzI1NTIiLCJ1c2VyTmFtZSI6ImhhaWRvbmciLCJ1c2VySWQiOiIyIiwiZW1haWwiOiIxMjMiLCJpc3N1ZXIiOiJncmFzc19vYXV0aCIsImV4cGlyZXNBdCI6IjE3MTQ0NDE5NzI1NTIifQ.b2j_yx8OoyZfY9qnuiqy8vyyC6K7uMYNdtZqgzblgY7G2rALGqjcvWOSjdGtO7YsHFKBQVIuyZWA-LSpQm2RP2n-n0iZYtdVc6-9Z3aMsFYfUmwE4qLH0KPI6_h3oZ0Jc3hukmyGE8ws5mGssJICLHbq_mYM14iukgq5SuLRf7BodSznEkeWdVXF2kz1rt1oTV7fD-ZEKBqL97ENSdeNlaiRvbE5dssni7SK8_mib2j7Z0obYApG5tcveiQz4Eg0baIfJWMEw8MmTWEpGLbkXtYgArfoBpp6SbHPytPTut6iMpFYUiSzUTvEUbnTlsUHwKKDVc30qpZTiCZaL_EPLA";
    }

    @Test
    void filter() {
    }

    @Test
    void getOrder() {
    }

    @Test
    void verify() {


    }

    @Test
    void verifyAndGetMessage() {
        verificationFilter.verifyAndGetMessage(jwt);
    }
}