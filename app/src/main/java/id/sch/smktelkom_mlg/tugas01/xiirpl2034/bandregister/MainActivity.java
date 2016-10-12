package id.sch.smktelkom_mlg.tugas01.xiirpl2034.bandregister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText namaband;
    EditText tahunband;
    Spinner genre;
    RadioGroup statusband;
    //    RadioButton single, engaged, halfstat;
    EditText status;
    CheckBox vocal, guitar, drum, bass, keyboard;
    TextView namatext, tahuntext, genretext, statustext, perantext;
    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaband = (EditText) findViewById(R.id.editTextNama);
        tahunband = (EditText) findViewById(R.id.editTextTahun);
        genre = (Spinner) findViewById(R.id.spinnerMainGenre);
/*      single = (RadioButton) findViewById(R.id.radioButtonSingle);
        engaged = (RadioButton) findViewById(R.id.radioButtonEngaged);
        halfstat = (RadioButton) findViewById(R.id.radioButtonHalfStatus);*/
        statusband = (RadioGroup) findViewById(R.id.RadioGroupBandStatus);
        status = (EditText) findViewById(R.id.editTextStatus);
        vocal = (CheckBox) findViewById(R.id.checkBoxVocal);
        guitar = (CheckBox) findViewById(R.id.checkBoxGitar);
        drum = (CheckBox) findViewById(R.id.checkBoxDrum);
        bass = (CheckBox) findViewById(R.id.checkBoxBass);
        keyboard = (CheckBox) findViewById(R.id.checkBoxKeyboard);
        namatext = (TextView) findViewById(R.id.textViewNamaBand);
        tahuntext = (TextView) findViewById(R.id.textViewTahunBand);
        genretext = (TextView) findViewById(R.id.textViewGenreBand);
        statustext = (TextView) findViewById(R.id.textViewStatusBand);
        perantext = (TextView) findViewById(R.id.textViewPeranBand);
        daftar = (Button) findViewById(R.id.buttonDaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();
            }
        });
        findViewById(R.id.buttonDaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();
            }
        });
        statusband.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonHalfStatus) {
                    findViewById(R.id.editTextStatus).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.editTextStatus).setVisibility(View.GONE);
                }
            }
        });


    }


    private void doProcess() {
        if (isValid()) {
            String nama = namaband.getText().toString();
            namatext.setText("Nama Band: " + nama + "\n");
            String tahun = tahunband.getText().toString();
            tahuntext.setText("Tahun berdiri: " + tahun + "\n");
            genretext.setText("Genre Band: " + genre.getSelectedItem().toString() + "\n");

        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = namaband.getText().toString();
        String tahun = tahunband.getText().toString();
        String status = null;
        String peran = "Peran yang kosong:\n";
        int startlen = peran.length();

        if (nama.isEmpty()) {
            namaband.setError("Nama Band belum diisi.");
            namatext.setText("Mohon isi nama Band Anda\n");
            genretext.setText("");
            valid = false;
        } else {
            namaband.setError(null);
        }
        if (tahun.isEmpty()) {
            tahunband.setError("Tahun berdiri Band belum diisi.");
            tahuntext.setText("Mohon isi tahun berdiri Band Anda");
            valid = false;
        } else if (tahun.length() != 4) {
            tahunband.setError("Format tahun Band adalah yyyy");
            valid = false;
        } else {
            tahunband.setError(null);
        }
        if (statusband.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(statusband.getCheckedRadioButtonId());
            status = rb.getText().toString();

            if (statusband.getCheckedRadioButtonId() == R.id.radioButtonHalfStatus) {
                EditText jumlahEngaged = (EditText) findViewById(R.id.editTextStatus);
                status += " dengan " + jumlahEngaged.getText() + " anggota sudah menikah\n";
            }
        }
        if (status == null) {
            statustext.setText("Mohon pilih status anggota Band Anda\n");
        } else {
            statustext.setText("Status Band: " + status);
        }
        if (vocal.isChecked()) peran += vocal.getText() + "\n";
        if (guitar.isChecked()) peran += guitar.getText() + "\n";
        if (drum.isChecked()) peran += drum.getText() + "\n";
        if (bass.isChecked()) peran += bass.getText() + "\n";
        if (keyboard.isChecked()) peran += keyboard.getText() + "\n";

        if (peran.length() == startlen) peran += "Tidak ada";
        perantext.setText(peran);

        return valid;
    }
}
