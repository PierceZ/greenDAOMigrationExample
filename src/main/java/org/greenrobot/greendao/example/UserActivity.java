package org.greenrobot.greendao.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Pierce Zaifman on 2017-01-28.
 */

public class UserActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        setUpViews();
    }

    private void setUpViews() {
        final EditText firstNameView = (EditText) findViewById(R.id.user_edittext_firstname);
        final EditText lastNameView = (EditText) findViewById(R.id.user_edittext_lastname);
        final EditText ageView = (EditText) findViewById(R.id.user_edittext_age);

        findViewById(R.id.user_button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaoSession daoSession = ((App) getApplication()).getDaoSession();
                UserDao userDao = daoSession.getUserDao();
                int age = 0;
                try {
                    age = Integer.valueOf(ageView.getText().toString());
                } catch (NumberFormatException e) {
                    // ignore it
                }
                userDao.insert(new User(null, age, firstNameView.getText().toString(), lastNameView.getText().toString()));
                Intent intent = new Intent(UserActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
