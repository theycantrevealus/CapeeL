// Generated by view binder compiler. Do not edit!
package com.example.medancapilpelaporan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.medancapilpelaporan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLaporLahirBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout btnBack;

  @NonNull
  public final Button btnLaporkanMati;

  @NonNull
  public final EditText laporLahirNikOrtu;

  @NonNull
  public final EditText laporMatiNama;

  @NonNull
  public final EditText laporMatiNik;

  @NonNull
  public final EditText laporMatiTanggalLahir;

  @NonNull
  public final EditText laporMatiTanggalMati;

  @NonNull
  public final EditText laporMatiTempatLahir;

  @NonNull
  public final EditText laporMatiTempatMati;

  private ActivityLaporLahirBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout btnBack, @NonNull Button btnLaporkanMati,
      @NonNull EditText laporLahirNikOrtu, @NonNull EditText laporMatiNama,
      @NonNull EditText laporMatiNik, @NonNull EditText laporMatiTanggalLahir,
      @NonNull EditText laporMatiTanggalMati, @NonNull EditText laporMatiTempatLahir,
      @NonNull EditText laporMatiTempatMati) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnLaporkanMati = btnLaporkanMati;
    this.laporLahirNikOrtu = laporLahirNikOrtu;
    this.laporMatiNama = laporMatiNama;
    this.laporMatiNik = laporMatiNik;
    this.laporMatiTanggalLahir = laporMatiTanggalLahir;
    this.laporMatiTanggalMati = laporMatiTanggalMati;
    this.laporMatiTempatLahir = laporMatiTempatLahir;
    this.laporMatiTempatMati = laporMatiTempatMati;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLaporLahirBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLaporLahirBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_lapor_lahir, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLaporLahirBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_back;
      LinearLayout btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btn_laporkan_mati;
      Button btnLaporkanMati = ViewBindings.findChildViewById(rootView, id);
      if (btnLaporkanMati == null) {
        break missingId;
      }

      id = R.id.lapor_lahir_nik_ortu;
      EditText laporLahirNikOrtu = ViewBindings.findChildViewById(rootView, id);
      if (laporLahirNikOrtu == null) {
        break missingId;
      }

      id = R.id.lapor_mati_nama;
      EditText laporMatiNama = ViewBindings.findChildViewById(rootView, id);
      if (laporMatiNama == null) {
        break missingId;
      }

      id = R.id.lapor_mati_nik;
      EditText laporMatiNik = ViewBindings.findChildViewById(rootView, id);
      if (laporMatiNik == null) {
        break missingId;
      }

      id = R.id.lapor_mati_tanggal_lahir;
      EditText laporMatiTanggalLahir = ViewBindings.findChildViewById(rootView, id);
      if (laporMatiTanggalLahir == null) {
        break missingId;
      }

      id = R.id.lapor_mati_tanggal_mati;
      EditText laporMatiTanggalMati = ViewBindings.findChildViewById(rootView, id);
      if (laporMatiTanggalMati == null) {
        break missingId;
      }

      id = R.id.lapor_mati_tempat_lahir;
      EditText laporMatiTempatLahir = ViewBindings.findChildViewById(rootView, id);
      if (laporMatiTempatLahir == null) {
        break missingId;
      }

      id = R.id.lapor_mati_tempat_mati;
      EditText laporMatiTempatMati = ViewBindings.findChildViewById(rootView, id);
      if (laporMatiTempatMati == null) {
        break missingId;
      }

      return new ActivityLaporLahirBinding((ConstraintLayout) rootView, btnBack, btnLaporkanMati,
          laporLahirNikOrtu, laporMatiNama, laporMatiNik, laporMatiTanggalLahir,
          laporMatiTanggalMati, laporMatiTempatLahir, laporMatiTempatMati);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
