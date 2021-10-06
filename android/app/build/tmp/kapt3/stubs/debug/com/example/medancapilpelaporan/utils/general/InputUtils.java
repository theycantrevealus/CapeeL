package com.example.medancapilpelaporan.utils.general;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/medancapilpelaporan/utils/general/InputUtils;", "", "()V", "FIELD_REQUIRED", "", "WRONG_FORMAT", "checkStringLength", "", "str", "expectedLength", "", "isValidEmail", "email", "app_debug"})
public final class InputUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.medancapilpelaporan.utils.general.InputUtils INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FIELD_REQUIRED = "Harus diisi";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String WRONG_FORMAT = "Format salah";
    
    private InputUtils() {
        super();
    }
    
    public final boolean isValidEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return false;
    }
    
    public final boolean checkStringLength(@org.jetbrains.annotations.NotNull()
    java.lang.String str, int expectedLength) {
        return false;
    }
}