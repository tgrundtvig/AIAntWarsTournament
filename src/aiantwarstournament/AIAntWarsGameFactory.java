/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.IAntAI;
import tournament.game.GameFactory;
import tournament.game.GameInstance;

/**
 *
 * @author Tobias Grundtvig
 */
public class AIAntWarsGameFactory implements GameFactory<IAntAI>
{

    @Override
    public String getGameName()
    {
        return "AI Ant Wars";
    }

    @Override
    public GameInstance<IAntAI> getNewGameInstance()
    {
        return new AIAntWarsGameInstance(32, 17, 16, 4, 0, 4, 5, 100);
    }
    
}
