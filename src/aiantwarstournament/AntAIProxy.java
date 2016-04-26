/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.EAction;
import aiantwars.EAntType;
import aiantwars.IAntAI;
import aiantwars.IAntInfo;
import aiantwars.IEgg;
import aiantwars.ILocationInfo;
import java.util.List;

/**
 *
 * @author Tobias Grundtvig
 */
public class AntAIProxy implements IAntAI
{
    private final IAntAI ai;
    private final Timer timer;

    public AntAIProxy(IAntAI ai, Timer timer)
    {
        this.ai = ai;
        this.timer = timer;
    }
    
    
    @Override
    public void onHatch(IAntInfo thisAnt, ILocationInfo thisLocation, int worldSizeX, int worldSizeY)
    {
        if (timer != null)
        {
            timer.entry("onHatch");
        }
        try
        {
            ai.onHatch(thisAnt, thisLocation, worldSizeX, worldSizeY);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onStartTurn(IAntInfo thisAnt, int turn)
    {
        if (timer != null)
        {
            timer.entry("onStartTurn");
        }
        try
        {
            ai.onStartTurn(thisAnt, turn);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations,
                                List<EAction> possibleActions)
    {
        if (timer != null)
        {
            timer.entry("chooseAction");
        }
        try
        {
            return ai.chooseAction(thisAnt, thisLocation, visibleLocations, possibleActions);
        } catch (Exception e)
        {
            return EAction.Pass;
        } finally
        {
            if (timer != null)
            {
                timer.exit();
            }
        }
    }

    @Override
    public void onLayEgg(IAntInfo thisAnt, List<EAntType> types, IEgg egg)
    {
        if (timer != null)
        {
            timer.entry("onLayEgg");
        }
        try
        {
            ai.onLayEgg(thisAnt, types, new EggProxy(egg, timer));
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onAttacked(IAntInfo thisAnt, int dir, IAntInfo attacker, int damage)
    {
        if (timer != null)
        {
            timer.entry("onAttacked");
        }
        try
        {
            ai.onAttacked(thisAnt, dir, attacker, damage);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onDeath(IAntInfo thisAnt)
    {
        if (timer != null)
        {
            timer.entry("onDeath");
        }
        try
        {
            ai.onDeath(thisAnt);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onStartMatch(int worldSizeX, int worldSizeY)
    {
        if (timer != null)
        {
            timer.entry("onStartMatch");
        }
        try
        {
            ai.onStartMatch(worldSizeX, worldSizeY);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onStartRound(int round)
    {
        if (timer != null)
        {
            timer.entry("onStartRound");
        }
        try
        {
            ai.onStartRound(round);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onEndRound(int yourMajor, int yourMinor, int enemyMajor, int enemyMinor)
    {
        if (timer != null)
        {
            timer.entry("onEndRound");
        }
        try
        {
            ai.onEndRound(yourMajor, yourMinor, enemyMajor, enemyMinor);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }

    @Override
    public void onEndMatch(int yourScore, int yourWins, int enemyScore, int enemyWins)
    {
        if (timer != null)
        {
            timer.entry("onEndMatch");
        }
        try
        {
            ai.onEndMatch(yourScore, yourWins, enemyScore, enemyWins);
        } catch (Exception e)
        {

        }
        if(timer != null)
        {
            timer.exit();
        }
    }
    
}
