package br.com.sokobeer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import br.com.sokobao2000.GameElement.StringRepresentation;
import br.com.sokobao2000.Grid;
import br.com.sokobao2000.Sokobao2000;

public class GameView extends SurfaceView {
	
	private SurfaceHolder holder;
	private Sokobao2000 sokobao;
	private float oldX;
	private float newX;
	private float oldY;
	private float newY;
	private Bitmap heroBitmap;
	private Bitmap targetBitmap;
	private Bitmap blockBitmap;
	private Bitmap wallBitmap;
	private Bitmap targetHeroBitmap;
	private Bitmap targetBlockBitmap;
	private Bitmap emptyBitmap;
	private int screenHeight;
	private int screenWidth;
	private int increment;
	 
    public GameView(Context context) {
          super(context);
          setSokobao(new Sokobao2000()); 
          setOnTouchListener(listener);
          setHolder(getHolder());
          addCallback();
    }

	private int getCanvasWidth() {
		Canvas canvas = holder.lockCanvas();
		int width = canvas.getWidth();
		holder.unlockCanvasAndPost(canvas);
		return width;
	}

	private int getCanvasHeight() {
		Canvas canvas = holder.lockCanvas();
		int height = canvas.getHeight();
		holder.unlockCanvasAndPost(canvas);
		return height;
	}

	private void addCallback() {
		holder.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				setScreenHeight(getCanvasHeight());
				setScreenWidth(getCanvasWidth());
				updateOnDrawCanvas(holder);
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) { }
			
			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) { }
		});
	}
    
    public void setHolder(SurfaceHolder holder) {
    	this.holder = holder;
    }
    
	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public Sokobao2000 getSokobao() {
		return sokobao;
	}

	public void setSokobao(Sokobao2000 sokobao) {
		this.sokobao = sokobao;
	}
	
	public int getIncrement() {
		if (increment == 0) {
			increment = getScreenWidth() / 10; 
		}
		return increment;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		Bitmap[][] bitmapScreen = getBitmapScreen();

		int j = 0;
		for (int x = 0; x < 10; x++) {
			int i = 0;
			for (int y = 0; y < 10; y++) {
				canvas.drawBitmap(bitmapScreen[y][x], i, j, null);
				i += getIncrement();
			}
			j += getIncrement();
		}
	}
    
    private Bitmap[][] getBitmapScreen() {
    	
    	Grid grid = getSokobao().getGrid();
    	Bitmap[][] bitmapGrid = new Bitmap[10][10];  
    	
    	for (int y = 0; y < 10; y++) {
    		for (int x = 0; x < 10; x++) {
    			bitmapGrid[y][x] = getBitmap(grid.getCell(y, x).toString());
			}
		}

    	return bitmapGrid;
    }
    
	private Bitmap getBitmap(String singleName) {
		if (singleName.equals(StringRepresentation.HERO.represent())) return getHeroBitmap();
		if (singleName.equals(StringRepresentation.WALL.represent())) return getWallBitmap();
		if (singleName.equals(StringRepresentation.BLOCK.represent())) return getBlockBitmap();
		if (singleName.equals(StringRepresentation.TARGET.represent())) return getTargetBitmap();
		if (singleName.equals(StringRepresentation.TARGET_WITH_HERO.represent())) return getTargetHeroBitmap();
		if (singleName.equals(StringRepresentation.TARGET_WITH_BLOCK.represent())) return getTargetBlockBitmap();
		
		return getEmptyBitmap();
	}

	private Bitmap getEmptyBitmap() {
		if (emptyBitmap == null) {
			emptyBitmap = resizeBitmap(getBitmap(R.drawable.empty));
		}
		return emptyBitmap;
	}

	private Bitmap getTargetBitmap() {
		if (targetBitmap == null) {
			targetBitmap = resizeBitmap(getBitmap(R.drawable.barrel));
		}
		return targetBitmap;
	}

	private Bitmap getBlockBitmap() {
		if (blockBitmap == null) {
			blockBitmap = resizeBitmap(getBitmap(R.drawable.beer));
		}
		return blockBitmap;
	}

	private Bitmap getWallBitmap() {
		if (wallBitmap == null) {
			wallBitmap = resizeBitmap(getBitmap(R.drawable.wall));
		}
		return wallBitmap;
	}

	private Bitmap getHeroBitmap() {
		if (heroBitmap == null) {
			heroBitmap = resizeBitmap(getBitmap(R.drawable.hero));
		}
		return heroBitmap;
	}
	
	private Bitmap getTargetHeroBitmap() {
		if (targetHeroBitmap == null) {
			targetHeroBitmap = resizeBitmap(getBitmap(R.drawable.barrel_hero));
		}
		return targetHeroBitmap;
	}
	
	private Bitmap getTargetBlockBitmap() {
		if (targetBlockBitmap == null) {
			targetBlockBitmap = resizeBitmap(getBitmap(R.drawable.barrel_beer));
		}
		return targetBlockBitmap;
	}
	
	private Bitmap getBitmap(int id) {
		return BitmapFactory.decodeResource(getResources(), id);
	}

	private Bitmap resizeBitmap(Bitmap bitmap) {
		return Bitmap.createScaledBitmap(bitmap, getIncrement(), getIncrement(), true);
	}
	
	private void updateOnDrawCanvas(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas();
		draw(canvas);
		holder.unlockCanvasAndPost(canvas);
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
							getSokobao().moveHeroEast();
						} else {
							getSokobao().moveHeroWest();
						}
					} else {
						if (dy > 0) {
							getSokobao().moveHeroSouth();						
						} else {
							getSokobao().moveHeroNorth();
						}
					}
					
					updateOnDrawCanvas(holder);
			}
			return true;
		}
	};
		
}
