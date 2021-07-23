/*
    Mahir Labib Dihan
*/

package com.dihu;

import com.dihu.data.Database;
import com.dihu.IO.Console;
import com.dihu.menu.MainMenu;

public class Main {
    public static void main(String[] args)  throws Exception{
        Database.readFromFile();
        MainMenu.view();    // Loop
        Console.closeScanner();
        Database.writeToFile();
    }
}
