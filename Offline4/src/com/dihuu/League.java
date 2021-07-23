package com.dihuu;

public class League {
    private Match[] matches;
    private int matchCount;
    private int clubCount;
    // add your variables here, if required
    private String name;
    private Club[] clubs;


    public League() {
        // assume a league can have at most 5 clubs, add code for initialization accordingly
        clubCount = 0;
        matchCount = 0;
        matches=new Match[5];
        clubs=new Club[5];
    }

    public void printLeagueInfo(){
        System.out.println("League : " + name);
        printClubs();
    }

    public void printClubs(){
        System.out.println("Clubs:");
        // print the name of the clubs of this league, each one on a line
        for(int i=0;i<clubCount;i++)
        {
            System.out.println(clubs[i].getName());
        }
    }

    public void scheduleMatches(){
        matchCount = (clubCount*(clubCount-1));
        matches = new Match[matchCount];
        int matchNo = 0;
        int c=0;
        for (int i=0; i<clubCount; i++){
            clubs[i].setPoints(0);
            for (int j=0; j<clubCount; j++){
                // check the constructor of the Match class and add your code here
                // note that there will be two matches between club A and club B
                // in the first match, club A will play as the home team and in the second match, as the away team

                if(i!=j)    // A club will play with rest of the clubs in the league
                {
                    matches[c++]=new Match(c,clubs[i],clubs[j]);
                }
            }
        }
    }


    public void simulateMatches(){
        for (int i=0; i<matchCount; i++){
            matches[i].play();
        }
    }

    public void showStandings(){
        // sort the clubs in descending order of points
        // note that, the sequence in which clubs were added to the league, should be unchanged
        // check the given sample output for clarification
        // (carefully observe the output of showStandings() followed by printLeagueInfo() method calls
        // you can use additional arrays if needed
        System.out.println("Sl. - Club - Points");

        // print the clubs in descending order of points

        // Copying array of clubs to an additional array
        Club[] temp=new Club[clubCount];
        for(int i=0;i<clubCount;i++)
        {
            temp[i]=clubs[i];
        }

        // Sort array of clubs in decending order according to points
        for (int j=0;j<clubCount-1;j++)
        {
            for (int i=0;i<clubCount-1-j;i++)
            {
                if (temp[i].getPoints()<temp[i+1].getPoints())
                {
                    Club k=temp[i];
                    temp[i]=temp[i+1];
                    temp[i+1]=k;
                }
            }
        }

        // Printing sorted array
        for(int i=0;i<clubCount;i++)
        {
            System.out.println(i+1+". "+temp[i].getName()+" "+temp[i].getPoints());
        }
    }

    // add your methods here, if required
    public void setName(String name)
    {
        this.name=name;
    }

    public void addClub(Club c)
    {
        clubs[clubCount++]=c;
    }
    public void printMatches()
    {
        System.out.println("Matches:");
        for(int i=0;i<matchCount;i++)
        {
            matches[i].showResult();
        }
    }
    public void removeClub(Club c)
    {
        int i;

        // Searching for the club in the array
        for(i=0;i<clubCount;i++)
        {
            if(clubs[i].getName()==c.getName())
            {
                clubCount--;
                break;
            }
        }

        // Removing the club by shifting remaining clubs to left
        for(;i<clubCount;i++)
        {
            clubs[i]=clubs[i+1];
        }
    }
}

