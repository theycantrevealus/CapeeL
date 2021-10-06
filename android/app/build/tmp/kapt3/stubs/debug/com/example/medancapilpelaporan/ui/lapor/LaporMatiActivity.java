package com.example.medancapilpelaporan.ui.lapor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002Jb\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/medancapilpelaporan/ui/lapor/LaporMatiActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/medancapilpelaporan/databinding/ActivityLaporMatiBinding;", "configuration", "Lcom/example/medancapilpelaporan/Config;", "laporkan", "", "nik", "", "nama", "tempat_lahir", "tanggal_lahir", "tempat_meninggal", "tanggal_meninggal", "jam_meninggal", "nama_keluarga", "kontak_keluarga", "token", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "ApiInterface", "LaporMati", "app_debug"})
public final class LaporMatiActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.medancapilpelaporan.databinding.ActivityLaporMatiBinding binding;
    private final com.example.medancapilpelaporan.Config configuration = null;
    
    public LaporMatiActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void laporkan(java.lang.String nik, java.lang.String nama, java.lang.String tempat_lahir, java.lang.String tanggal_lahir, java.lang.String tempat_meninggal, java.lang.String tanggal_meninggal, java.lang.String jam_meninggal, java.lang.String nama_keluarga, java.lang.String kontak_keluarga, java.lang.String token, android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u0090\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u0006H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/example/medancapilpelaporan/ui/lapor/LaporMatiActivity$ApiInterface;", "", "lapor_mati", "Lretrofit2/Call;", "Lcom/example/medancapilpelaporan/ui/lapor/LaporMatiActivity$LaporMati;", "request", "", "nik", "nama_lengkap", "tempat_lahir", "tanggal_lahir", "tempat_meninggal", "tanggal_meninggal", "jam_meninggal", "nama_keluarga", "kontak_keluarga", "kecamatan", "kelurahan", "jenis", "app_debug"})
    public static abstract interface ApiInterface {
        
        @org.jetbrains.annotations.NotNull()
        @retrofit2.http.FormUrlEncoded()
        @retrofit2.http.POST(value = "Pelaporan")
        @retrofit2.http.Headers(value = {"Accept: application/json"})
        public abstract retrofit2.Call<com.example.medancapilpelaporan.ui.lapor.LaporMatiActivity.LaporMati> lapor_mati(@org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "request")
        java.lang.String request, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "nik")
        java.lang.String nik, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "nama_lengkap")
        java.lang.String nama_lengkap, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "tempat_lahir")
        java.lang.String tempat_lahir, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "tanggal_lahir")
        java.lang.String tanggal_lahir, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "tempat_meninggal")
        java.lang.String tempat_meninggal, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "tanggal_meninggal")
        java.lang.String tanggal_meninggal, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "jam_meninggal")
        java.lang.String jam_meninggal, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "nama_keluarga")
        java.lang.String nama_keluarga, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "no_handphone_keluarga")
        java.lang.String kontak_keluarga, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "kecamatan")
        java.lang.String kecamatan, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "kelurahan")
        java.lang.String kelurahan, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Field(value = "jenis")
        java.lang.String jenis);
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0002X\u0083D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/medancapilpelaporan/ui/lapor/LaporMatiActivity$LaporMati;", "", "()V", "response_message", "", "response_result", "", "getResponseMessage", "getResponseResult", "app_debug"})
    public static final class LaporMati {
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "response_result")
        private final int response_result = 0;
        @com.google.gson.annotations.Expose()
        @com.google.gson.annotations.SerializedName(value = "response_message")
        private final java.lang.String response_message = "";
        
        public LaporMati() {
            super();
        }
        
        public final int getResponseResult() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getResponseMessage() {
            return null;
        }
    }
}