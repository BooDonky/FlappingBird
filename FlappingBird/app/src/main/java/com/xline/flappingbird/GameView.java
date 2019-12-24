package com.xline.flappingbird;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    GameThread gameThread;
    public GameView(Context context) {
        super(context);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!gameThread.isRunning())
        {
            gameThread = new GameThread(holder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (gameThread.isRunning())
        {
            gameThread.setIsRunningl(false);
            boolean retry = true;
            while(retry)
            {
                try {
                    gameThread.join();
                    retry = false;
                }catch (InterruptedException e)
                {}
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    void initView()
    {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }
}
