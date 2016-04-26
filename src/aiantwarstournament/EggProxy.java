/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.EAntType;
import aiantwars.IAntAI;
import aiantwars.IEgg;

/**
 *
 * @author Tobias Grundtvig
 */
public class EggProxy implements IEgg
{
    private final IEgg egg;
    private final Timer timer;

    public EggProxy(IEgg egg, Timer timer)
    {
        this.egg = egg;
        this.timer = timer;
    }
    
    @Override
    public void set(EAntType type, IAntAI ai)
    {
        egg.set(type, new AntAIProxy(ai, timer));
    }
    
}
