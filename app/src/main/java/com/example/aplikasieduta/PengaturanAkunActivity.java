package com.example.aplikasieduta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class PengaturanAkunActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.PG_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke AkunActivity
                Intent intent = new Intent(PengaturanAkunActivity.this, AkunActivity.class);
                startActivity(intent);
            }
        });

        // Inisialisasi sessionManager
        sessionManager = new SessionManager(this);

        // Mengaitkan OnClickListener ke tombol PG_actionlogout
        ImageButton logoutButton = findViewById(R.id.PG_actionlogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutMenu(v);
            }
        });

        // Mengaitkan OnClickListener ke TextView PG_txt_5
        TextView pgText5 = findViewById(R.id.PG_txt_5);
        pgText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutMenu(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionlogout:
                sessionManager.logoutSession();
                moveToLogin();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private void showLogoutMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.logout_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.actionlogout) {
                    sessionManager.logoutSession();
                    moveToLogin();
                }
                return true;
            }
        });

        popupMenu.show();
    }
}

