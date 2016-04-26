/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.IAntAI;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import tournament.player.PlayerFactory;



/**
 *
 * @author Tobias Grundtvig
 */
public class Loader
{
    private final String rootPath;

    public Loader(String rootPath)
    {
        this.rootPath = rootPath;
    }
    
    public Collection<PlayerFactory<IAntAI>> loadCategory(String prefix, int size)
    {
        ArrayList<PlayerFactory<IAntAI>> res = new ArrayList<>();
        for(int i = 1; i <= size; ++i)
        {
            String jarFile = rootPath + "/" + prefix + i + ".jar";
            String className = prefix.toLowerCase() + i + "." + prefix + i;
            PlayerFactory<IAntAI> player = loadPlayer(jarFile, className);
            if(player != null)
            {
                System.out.println("Loaded: " + prefix + i + " -> " + player.getName());
                res.add(player);
            }
        }
        return res;
    }

    
    public static PlayerFactory<IAntAI> loadPlayer(String jar, String className)
    {
        PlayerFactory<IAntAI> res = null;
        try
        {
            addJar(jar);
            res = (PlayerFactory<IAntAI>) Class.forName(className).newInstance();
        } catch (Exception e)
        {
        }
        return res;
    }

    private static void addJar(String s) throws Exception
    {
        File f = new File(s);
        URI u = f.toURI();
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]
        {
            URL.class
        });
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]
        {
            u.toURL()
        });
    }
}
