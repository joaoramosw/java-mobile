import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private RadioButton radioMasculino;
    private SharedPreferences sharedPreferences;

    // Chaves para SharedPreferences
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_NAME = "nome";
    private static final String KEY_GENDER = "masculino";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular componentes do XML
        etNome = findViewById(R.id.etNome);
        radioMasculino = findViewById(R.id.radioMasculino);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnSair = findViewById(R.id.btnSair);

        // Carregar dados salvos
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        carregarDados();

        // Configurar botão Salvar
        btnSalvar.setOnClickListener(v -> salvarDados());

        // Configurar botão Sair
        btnSair.setOnClickListener(v -> finish());
    }

    // Carrega os dados salvos ou define valores padrão
    private void carregarDados() {
        String nomeSalvo = sharedPreferences.getString(KEY_NAME, "João Ninguém");
        boolean sexoMasculino = sharedPreferences.getBoolean(KEY_GENDER, true);

        etNome.setText(nomeSalvo);
        radioMasculino.setChecked(sexoMasculino);
    }

    // Salva as alterações no SharedPreferences
    private void salvarDados() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, etNome.getText().toString());
        editor.putBoolean(KEY_GENDER, radioMasculino.isChecked());
        editor.apply();
    }
}