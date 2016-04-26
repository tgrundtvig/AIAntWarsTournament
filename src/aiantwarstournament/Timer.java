/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import tournament.ui.TournamentUI;

/**
 *
 * @author Tobias Grundtvig
 */
public class Timer implements Runnable
{
    private final TournamentUI ui;
    private final long timeout = 5000;
    private final Thread thread;
    private final String ID;
    private String method;
    private long entryTime;
    private boolean stop;
    

    public Timer(TournamentUI ui, String ID)
    {
        this.ui = ui;
        this.ID = ID;
        stop = false;
        method = null;
        entryTime = 0;
        thread = new Thread(this);
    }
    
    public void start()
    {
        thread.start();
    }
    
    public synchronized void stop()
    {
        this.stop = true;
        thread.interrupt();
    }
    
    @Override
    public void run()
    {
        while(!stop)
        {
            try
            {
                Thread.sleep(timeout);
                check();
            } catch (InterruptedException ex)
            {
                
            }
        }
    }
    
    public synchronized void entry(String method)
    {
        this.method = method;
        entryTime = (System.nanoTime() / 1000000);
    }
    
    public synchronized void exit()
    {
        method = null;
        entryTime = 0;
    }
    
    public synchronized void check()
    {
        if(entryTime != 0)
        {
            long elapsed = (System.nanoTime() / 1000000) - entryTime;
            if(elapsed > timeout)
            {
                ui.timeoutOccurred(ID, method);
            }
        }
    }
    
}
