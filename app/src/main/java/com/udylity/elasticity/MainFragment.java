package com.udylity.elasticity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MainFragment extends Fragment implements TextWatcher {

    TextView typeElasticity, numberElasticity;
    EditText priceOldET, priceNewET, demandOldET, demandNewET;

    double priceOldNumber, priceNewNumber, demandOldNumber, demandNewNumber;
    double priceChange, demandChange, elasticityNumber;
    String poETString, pnETString, doETString, dnETString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        typeElasticity = (TextView) rootView.findViewById(R.id.typeElasticityTv);
        numberElasticity = (TextView) rootView.findViewById(R.id.numberElasticityTv);

        priceOldET = (EditText) rootView.findViewById(R.id.priceOld);
        priceNewET = (EditText) rootView.findViewById(R.id.priceNew);

        demandOldET = (EditText) rootView.findViewById(R.id.demandOld);
        demandNewET = (EditText) rootView.findViewById(R.id.demandNew);

        priceOldET.addTextChangedListener(this);
        priceNewET.addTextChangedListener(this);
        demandOldET.addTextChangedListener(this);
        demandNewET.addTextChangedListener(this);
        return rootView;
    }

    private void calculateElasticity() {
        priceOldNumber = Double.parseDouble(poETString);
        priceNewNumber = Double.parseDouble(pnETString);
        demandOldNumber = Double.parseDouble(doETString);
        demandNewNumber = Double.parseDouble(dnETString);


        demandChange = ((demandOldNumber - demandNewNumber) / demandOldNumber) * 100;

        priceChange = ((priceOldNumber - priceNewNumber) / priceOldNumber) * 100;

        elasticityNumber = Math.abs(demandChange / priceChange);

        numberElasticity.setText(String.valueOf((double)Math.round(elasticityNumber * 1000) / 1000));
        if (elasticityNumber < 1){
            typeElasticity.setText("inelastic");
        }else if (elasticityNumber == 1){
            typeElasticity.setText("unitary elastic");
        }else if (elasticityNumber > 1){
            typeElasticity.setText("elastic");
        }
    }


    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            poETString = priceOldET.getText().toString();
            pnETString = priceNewET.getText().toString();
            doETString = demandOldET.getText().toString();
            dnETString = demandNewET.getText().toString();
        } catch (NullPointerException n) {
            n.printStackTrace();
        }

        if (!(poETString.contentEquals("") || pnETString.contentEquals("")
                || doETString.contentEquals("") || dnETString.contentEquals(""))) {
            calculateElasticity();
        }else{
            typeElasticity.setText("");
            numberElasticity.setText("");
        }

    }
}
