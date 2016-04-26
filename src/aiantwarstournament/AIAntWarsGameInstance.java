/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.EAntType;
import aiantwars.IAntAI;
import aiantwars.IOnGameFinished;
import aiantwars.ITeamInfo;
import aiantwars.impl.AntWarsGameCtrl;
import aiantwars.impl.Board;
import aiantwars.impl.DummyGraphicsAntWarsGUI;
import aiantwars.impl.Score;
import aiantwars.impl.TeamInfo;
import java.util.Map;
import tournament.game.GameInstance;
import tournament.game.GameResult;
import tournament.ui.TournamentUI;

/**
 *
 * @author Tobias Grundtvig
 */
public class AIAntWarsGameInstance implements GameInstance<IAntAI>, IOnGameFinished
{

    private final int xSize;
    private final int ySize;
    private final int soil;
    private final int rock;
    private final int minFood;
    private final int maxFood;
    private final int startFood;
    private final int rounds;

    private int winsA;
    private int winsB;
    private IAntAI pA;
    private IAntAI pB;

    public AIAntWarsGameInstance(int xSize, int ySize, int soil, int rock, int minFood, int maxFood, int startFood,
                                 int rounds)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        this.soil = soil;
        this.rock = rock;
        this.minFood = minFood;
        this.maxFood = maxFood;
        this.startFood = startFood;
        this.rounds = rounds;
    }

    @Override
    public GameResult run(IAntAI playerA, String aID, IAntAI playerB, String bID, TournamentUI ui,
                          boolean useTimer)
    {
        winsA = 0;
        winsB = 0;
        Timer timerA = null;
        Timer timerB = null;
        if(useTimer)
        {
            timerA = new Timer(ui, aID);
            timerB = new Timer(ui, bID);
            timerA.start();
            timerB.start();
        }
        
        pA = new AntAIProxy(playerA, timerA);
        pB = new AntAIProxy(playerB, timerB);
        TeamInfo tA = new TeamInfo(1, aID);
        TeamInfo tB = new TeamInfo(2, bID);
        for(int round = 1; round <= rounds; ++round)
        {
            Board board = BoardBuilder.buildTwoPlayerSymmetric(xSize, ySize, soil, rock, minFood, maxFood, startFood);
            
            int yA = board.getSizeY() / 2;
            int yB = board.getSizeY() % 2 == 0 ? yA - 1 : yA;

            AntWarsGameCtrl ctrl = new AntWarsGameCtrl(new DummyGraphicsAntWarsGUI(false), board, this);
            ctrl.createAnt(tA, EAntType.QUEEN, pA, board.getLocation(0, yA), 1);
            ctrl.createAnt(tB, EAntType.QUEEN, pB, board.getLocation(board.getSizeX() - 1, yB), 3);

            ctrl.run();
        }
        
        //Stop the timer threads
        if(useTimer)
        {
            timerA.stop();
            timerB.stop();
        }

        int majorA = 0;
        int majorB = 0;
        if(winsA > winsB)
        {
            majorA = 1;
            majorB = -1;
        }
        else if(winsB > winsA)
        {
            majorA = -1;
            majorB = 1;
        }
        pA.onEndMatch(majorA, winsA, majorB, winsB);
        pB.onEndMatch(majorB, winsB, majorA, winsA);
        return new GameResult(majorA, winsA, majorB, winsB);
    }

    @Override
    public void onGameFinished(Map<ITeamInfo, Score> scores)
    {
        int majorA = 0;
        int minorA = 0;
        int majorB = 0;
        int minorB = 0;

        for(Map.Entry<ITeamInfo, Score> entry : scores.entrySet())
        {
            if(entry.getKey().getTeamID() == 1)
            {
                majorA = entry.getValue().getMajor();
                minorA = entry.getValue().getMinor();
            }
            else if(entry.getKey().getTeamID() == 2)
            {
                majorB = entry.getValue().getMajor();
                minorB = entry.getValue().getMinor();
            }
        }
        
        if(majorA > majorB)
        {
            ++winsA;
        }
        else if(majorB > majorA)
        {
            ++winsB;
        }
        else if(minorA > minorB)
        {
            ++winsA;
        }
        else if(minorB > minorA)
        {
            ++winsB;
        }
        
        //report
        pA.onEndRound(majorA, minorA, majorB, minorB);
        pB.onEndRound(majorB, minorB, majorA, minorA);
    }

}
