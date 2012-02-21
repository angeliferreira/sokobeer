package br.com.sokobeer;

import main.Grid;
import main.Sokobao2000;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
	
	private SurfaceHolder holder;
	private Sokobao2000 sokobao;
	private float oldX;
	private float newX;
	private float oldY;
	private float newY;
	 
    public GameView(Context context) {
          super(context);
          this.sokobao = new Sokobao2000(); 
          this.setOnTouchListener(listener);
          setHolder(getHolder());
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        
        Bitmap[][] bitmapScreen = getBitmapScreen();
		int j = 20;
        
        for (int x = 0; x < 10; x++) {
        	int i = 20;
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
	
	public void setHolder(SurfaceHolder holder) {
		this.holder = holder;
	}

	private void updateOnDrawCanvas(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas(null);
		onDraw(canvas);
		getHolder().unlockCanvasAndPost(canvas);
	}

	private OnTouchListener listener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction()) {
				case (MotionEvent.ACTION_DOWN):
					oldX = event.getX();
					oldY = event.getY();
					break;
				case (MotionEvent.ACTION_UP):
					newX = event.getX();
					newY = event.getY();
					float dx = newX - oldX;
					float dy = newY - oldY;
	
					if (dx == 0 && dy == 0) return false;
					
					if (Math.abs(dx) > Math.abs(dy)) {
						if (dx > 0) {
							//Log.i("lemao", "RIGHT - "+dx);
							sokobao.moveHeroEast();
						} else {
							//Log.i("lemao", "LEFT - "+dx);
							sokobao.moveHeroWest();
						}
					} else {
						if (dy > 0) {
							//Log.i("lemao", "DOWN - "+dy);
							sokobao.moveHeroSouth();						
						} else {
							//Log.i("lemao", "UP - "+dy); 
							sokobao.moveHeroNorth();
						}
					}
					
					updateOnDrawCanvas(holder);
			}
			return true;
		}
	};
	
	
	
}
