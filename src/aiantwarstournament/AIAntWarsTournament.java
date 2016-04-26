/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.IAntAI;
import java.util.Collection;
import tournament.Tournament;
import tournament.impl.simpleui.TextTournamentUI;
import tournament.player.PlayerFactory;

/**
 *
 * @author Tobias Grundtvig
 */
public class AIAntWarsTournament
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String path = "D:/AIAntWarsTournament";
        Loader loader = new Loader(path);
        
        Collection<PlayerFactory<IAntAI>> ais = loader.loadCategory("A", 10);
        
        System.out.println("\n\n\nFinal tournament: Total war...");
        TextTournamentUI.turnOffIO();
        Tournament.run(new AIAntWarsGameFactory(), ais, false);
        TextTournamentUI.turnOnIO();
        System.out.println("Goodbye...");
    }
    
}
