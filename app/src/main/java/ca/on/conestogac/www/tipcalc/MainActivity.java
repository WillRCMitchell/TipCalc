package ca.on.conestogac.www.tipcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.NumberFormat;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener,OnClickListener {

    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;

    private float tipPercent=.15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billAmountEditText=(EditText)
                findViewById(R.id.billAmountEditText);
        percentTextView=(TextView)
                findViewById(R.id.percentTextView);
        percentUpButton=(Button)
                findViewById(R.id.percentUpButton);
        percentDownButton=(Button)
                findViewById(R.id.percentDownButton);
        tipTextView=(TextView)
                findViewById(R.id.tipTextView);
        totalTextView=(TextView)
                findViewById(R.id.totalTextView);

        billAmountEditText.setOnEditorActionListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);

        calculateAndDisplay();
    }
    public void calculateAndDisplay(){
        String billAmountString =
                billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountString.equals("")) {
            billAmount=0;
        }
        else {
            billAmount=Float.parseFloat(billAmountString);
        }
        float tipAmount = billAmount*tipPercent;
        float totalAmount = billAmount+tipAmount;

        NumberFormat currency =NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        calculateAndDisplay();
        return false;
    }
    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.percentDownButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;

            case R.id.percentUpButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;
        }
    }
}
