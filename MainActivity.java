package com.example.converter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity<fromAdaxpter> extends AppCompatActivity {

    EditText text, fromLangCode, toLangCode;
    private Spinner spinner, spinner2;
    TextView translatedText;
    Button btnTranslate;
    String[] fromLang = {"Arabic=ar", "Bulgarian=bg", "Catalan=ca", "Chinese(Simplified)=zh_cn", "Chinese (Traditional)=zh_tw", "Croatian=hr", "Czech=cs", "Danish=da", "Dutch=nl", "English=en", "English (UK)=en_gb",
            "Farsi=fa", "Filipino=fil", "Finnish=fi", "French=fr", "German=de", "Greek=el", "Hebrew=iw", "Hindi=hi", "Hungarian=hu", "Indonesian=id", "Italian=it", "Japanese=ja", "Korean=ko", "Latvian=lv", "Lithuanian=lt", "Norwegian (Bokmal)=mo",
            "Polish=pl", "Portuguese (Brazil)pt_br", "Portuguese (Portugal)=pt_pt", "Romanian=ro", "Russian=ru", "Serbian=sr", "Slovak=sk", "Slovenian=sl", "Spanish=es", "Spanish (Latin America)=es_419", "Swedish=sv",
            "Thai=th", "Turkish=tr", "Ukrainian=uk", "Vietnamese=vi"};
    String[] toLang = {"Arabic=ar", "Bulgarian=bg", "Catalan=ca", "Chinese(Simplified)=zh_cn", "Chinese (Traditional)=zh_tw", "Croatian=hr", "Czech=cs", "Danish=da", "Dutch=nl", "English=en", "English (UK)=en_gb",
            "Farsi=fa", "Filipino=fil", "Finnish=fi", "French=fr", "German=de", "Greek=el", "Hebrew=iw", "Hindi=hi", "Hungarian=hu", "Indonesian=id", "Italian=it", "Japanese=ja", "Korean=ko", "Latvian=lv", "Lithuanian=lt", "Norwegian (Bokmal)=mo",
            "Polish=pl", "Portuguese (Brazil)pt_br", "Portuguese (Portugal)=pt_pt", "Romanian=ro", "Russian=ru", "Serbian=sr", "Slovak=sk", "Slovenian=sl", "Spanish=es", "Spanish (Latin America)=es_419", "Swedish=sv",
            "Thai=th", "Turkish=tr", "Ukrainian=uk", "Vietnamese=vi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate_api translate = new translate_api();
                translate.setOnTranslationCompleteListener(new translate_api.OnTranslationCompleteListener() {
                    @Override
                    public void onStartTranslation() {
                        // here you can perform initial work before translated the text like displaying progress bar
                    }

                    @Override
                    public void onCompleted(String text) {
                        // "text" variable will give you the translated text
                        translatedText.setText(text);

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                translate.execute(text.getText().toString(), fromLangCode.getText().toString(), toLangCode.getText().toString());
            }
        });
    }

    private void initUi() {
        text = findViewById(R.id.text);
        fromLangCode = findViewById(R.id.from_lang);
        toLangCode = findViewById(R.id.to_lang);
        translatedText = findViewById(R.id.translated_text);
        btnTranslate = findViewById(R.id.btnTranslate);
        spinner = findViewById(R.id.fromSpinner);
        spinner2 = findViewById(R.id.toSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemInSpinner = spinner.getSelectedItem().toString();
                fromLangCode.setText(selectedItemInSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String selectedItemInSpinner = spinner.getSelectedItem().toString();
                toLangCode.setText(selectedItemInSpinner);

            }
        });
        ArrayAdapter toAdapter = new ArrayAdapter(this, R.layout.spinner_item, toLang);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(toAdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemInSpinner=spinner2.getSelectedItem().toString();
                toLangCode.setText(selectedItemInSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String selectedItemInSpinner=spinner2.getSelectedItem().toString();
                toLangCode.setText(selectedItemInSpinner);

            }
        });

        ArrayAdapter fromAdapter = new ArrayAdapter(this, R.layout.spinner_item, fromLang);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(fromAdapter);

    }
}