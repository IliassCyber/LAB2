package com.example.calcul_impots;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Composants graphiques
    private EditText champSurface, champPieces;
    private CheckBox optionPiscine;
    private TextView zoneResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues via leurs IDs
        champSurface = findViewById(R.id.et_surface_m2);
        champPieces = findViewById(R.id.et_nombre_pieces);
        optionPiscine = findViewById(R.id.cb_option_piscine);
        zoneResultat = findViewById(R.id.tv_affichage_resultat);

        // Configuration de l'action du bouton
        Button btnCalculer = findViewById(R.id.btn_lancer_calcul);
        btnCalculer.setOnClickListener(view -> executerLeCalcul());
    }

    private void executerLeCalcul() {
        try {
            // Récupération des données saisies
            String surfaceTxt = champSurface.getText().toString();
            String piecesTxt = champPieces.getText().toString();

            if (surfaceTxt.isEmpty() || piecesTxt.isEmpty()) {
                zoneResultat.setText("Erreur : Veuillez remplir les champs.");
                return;
            }

            double surfaceValue = Double.parseDouble(surfaceTxt);
            int nombrePieces = Integer.parseInt(piecesTxt);
            boolean aPiscine = optionPiscine.isChecked();

            // Algorithme de calcul
            double baseTaxable = surfaceValue * 2.0;
            double taxePieces = nombrePieces * 50.0;
            double taxePiscine = aPiscine ? 100.0 : 0.0;

            double totalImpot = baseTaxable + taxePieces + taxePiscine;

            // Mise à jour de l'affichage
            zoneResultat.setText("Estimation Impôt : " + totalImpot + " DH");
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Format de saisie incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}