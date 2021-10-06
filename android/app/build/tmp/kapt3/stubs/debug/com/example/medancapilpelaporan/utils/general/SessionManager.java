package com.example.medancapilpelaporan.utils.general;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010&\u001a\u00020\'J\u0018\u0010(\u001a\u00020\'2\b\u0010)\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020*J\u0018\u0010+\u001a\u00020\'2\b\u0010)\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020,J\u001a\u0010-\u001a\u00020\'2\b\u0010)\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR(\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR(\u0010\u0014\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR(\u0010\u001d\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR(\u0010 \u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\rR(\u0010#\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010\r\u00a8\u0006."}, d2 = {"Lcom/example/medancapilpelaporan/utils/general/SessionManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "configuration", "Lcom/example/medancapilpelaporan/Config;", "value", "", "email", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "foto", "getFoto", "setFoto", "kontak", "getKontak", "setKontak", "nama", "getNama", "setNama", "sp", "Landroid/content/SharedPreferences;", "spEditor", "Landroid/content/SharedPreferences$Editor;", "getSpEditor", "()Landroid/content/SharedPreferences$Editor;", "token", "getToken", "setToken", "uID", "getUID", "setUID", "username", "getUsername", "setUsername", "logout", "", "saveSPBoolean", "keySP", "", "saveSPInt", "", "saveSPString", "app_debug"})
public final class SessionManager {
    private final com.example.medancapilpelaporan.Config configuration = null;
    private android.content.SharedPreferences sp;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences.Editor spEditor = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences.Editor getSpEditor() {
        return null;
    }
    
    public SessionManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void saveSPString(@org.jetbrains.annotations.Nullable()
    java.lang.String keySP, @org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final void saveSPInt(@org.jetbrains.annotations.Nullable()
    java.lang.String keySP, int value) {
    }
    
    public final void saveSPBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.String keySP, boolean value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUID() {
        return null;
    }
    
    public final void setUID(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final void setUsername(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getKontak() {
        return null;
    }
    
    public final void setKontak(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNama() {
        return null;
    }
    
    public final void setNama(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEmail() {
        return null;
    }
    
    public final void setEmail(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFoto() {
        return null;
    }
    
    public final void setFoto(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final void setToken(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final void logout() {
    }
}