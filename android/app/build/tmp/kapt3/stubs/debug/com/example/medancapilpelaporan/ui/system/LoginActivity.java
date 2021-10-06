package com.example.medancapilpelaporan.ui.system;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003\u0018\u0019\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lcom/example/medancapilpelaporan/ui/system/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/medancapilpelaporan/databinding/ActivityLoginBinding;", "configuration", "Lcom/example/medancapilpelaporan/Config;", "sessionManager", "Lcom/example/medancapilpelaporan/utils/general/SessionManager;", "getSessionManager", "()Lcom/example/medancapilpelaporan/utils/general/SessionManager;", "setSessionManager", "(Lcom/example/medancapilpelaporan/utils/general/SessionManager;)V", "login", "", "email", "", "password", "token", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "ApiInterface", "Login", "UserData", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.medancapilpelaporan.databinding.ActivityLoginBinding binding;
    public com.example.medancapilpelaporan.utils.general.SessionManager sessionManager;
    private final com.example.medancapilpelaporan.Config configuration = null;
    
    public LoginActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.medancapilpelaporan.utils.general.SessionManager getSessionManager() {
        return null;
    }
    
    public final void setSessionManager(@org.jetbrains.annotations.NotNull()
    com.example.medancapilpelaporan.utils.general.SessionManager p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void login(java.lang.String email, java.lang.String password, java.lang.String token, android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u0006H\'\u00a8\u0006\t"}, d2 = {"Lcom/example/medancapilpelaporan/ui/system/LoginActivity$ApiInterface;", "", "signin", "Lretrofit2/Call;", "Lcom/example/medancapilpelaporan/ui/system/LoginActivity$Login;", "request", "", "email", "password", "app_debug"})
    public static abstract interface ApiInterface {
        
        @org.jetbrains.annotations.NotNull()
        @retrofit2.http.FormUrlEncoded()
        @retrofit2.http.POST(value = "Pegawai")
        @retrofit2.http.Headers(value = {"Accept: application/json"})
        public abstract retrofit2.Call<com.example.medancapilpelaporan.ui.system.LoginActivity.Login> signin(@org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "request")
        java.lang.String request, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "username")
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "password")
        java.lang.String password);
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\bJ\b\u0010\r\u001a\u0004\u0018\u00010\u0006R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\u00068\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/medancapilpelaporan/ui/system/LoginActivity$Login;", "", "()V", "response_data", "Lcom/example/medancapilpelaporan/ui/system/LoginActivity$UserData;", "response_message", "", "response_result", "", "response_token", "getResponseData", "getResponseMessage", "getResponseResult", "getResponseToken", "app_debug"})
    public static final class Login {
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "response_data")
        private final com.example.medancapilpelaporan.ui.system.LoginActivity.UserData response_data = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "response_token")
        private final java.lang.String response_token = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "response_result")
        private final int response_result = 0;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "response_message")
        private final java.lang.String response_message = "";
        
        public Login() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.medancapilpelaporan.ui.system.LoginActivity.UserData getResponseData() {
            return null;
        }
        
        public final int getResponseResult() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getResponseMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getResponseToken() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\b\u0010\f\u001a\u0004\u0018\u00010\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/medancapilpelaporan/ui/system/LoginActivity$UserData;", "", "()V", "contact", "", "email", "nama", "password", "uid", "username", "getContact", "getEmail", "getNama", "getPassword", "getUID", "getUserName", "app_debug"})
    public static final class UserData {
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "uid")
        private final java.lang.String uid = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "username")
        private final java.lang.String username = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "nama")
        private final java.lang.String nama = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "no_handphone")
        private final java.lang.String contact = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "email")
        private final java.lang.String email = null;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "password")
        private final java.lang.String password = null;
        
        public UserData() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUID() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getEmail() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUserName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getContact() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getNama() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPassword() {
            return null;
        }
    }
}