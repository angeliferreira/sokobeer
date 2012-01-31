package br.com.sokobeer;

import main.Grid;
import main.Sokobao2000;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class GameView extends View {
	private Sokobao2000 sokobao;
	 
    public GameView(Context context) {
          super(context);
          this.sokobao = new Sokobao2000(); 
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        
        Bitmap[][] bitmapScreen = getBitmapScreen();
        int i = 0;
		int j = 20;
        
        for (int x = 0; x < 10; x++) {
        	i = 20;
			for (int y = 0; y < 10; y++) {
				canvas.drawBitmap(bitmapScreen[y][x], i, j, null);
				i += 20;
			}
			j += 20;
		}
     }
    
    private Bitmap[][] getBitmapScreen() {
    	
    	Grid grid = sokobao.getGrid();
    	Bitmap[][] bitmapGrid = new Bitmap[10][10];  
    	
    	for (int y = 0; y < 10; y++) {
    		for (int x = 0; x < 10; x++) {
    			bitmapGrid[y][x] = getBitmap(grid.getElement(x, y).getSingleName());
			}
		}

    	return bitmapGrid;
    }
    
	private Bitmap getBitmap(char singleName) {
		if (singleName == 'H') return BitmapFactory.decodeResource(getResources(), R.drawable.beer_girl1);
		if (singleName == 'W') return BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		if (singleName == 'B') return BitmapFactory.decodeResource(getResources(), R.drawable.beer);
		if (singleName == 'X') return BitmapFactory.decodeResource(getResources(), R.drawable.target);
		return BitmapFactory.decodeResource(getResources(), R.drawable.empty);
	}

}
