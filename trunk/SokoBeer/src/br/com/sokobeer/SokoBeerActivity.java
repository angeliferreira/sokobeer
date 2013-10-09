package br.com.sokobeer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SokoBeerActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
    }
}