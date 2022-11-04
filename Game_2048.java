import java.io.*;
class Game_2048//this is a numbers game called 2048
{
    static BufferedReader a=new BufferedReader(new InputStreamReader(System.in));
    static int game[][]=new int[4][4];//this array stores the actual numbers on the 2x2 grid
    static int temp[][]=new int[4][4];//it temporarily stores the value of uno3 array just to return the values if the user made an invalid move
    static int checkgame[][]=new int[4][4];//this array is used to check if the values of game[x][y] changed after the user input ie it checks if the numbers moved
    static int zero[]=new int[16];//this array stores the location of the tiles which are zero in the form   10*row number + coloumn number
    static int undo2[][]=new int[4][4];//stores the state of the grid in previous iteration than that of game[x][y]
    static int undo3[][]=new int[4][4];//stores the state of the grid in previous iteration than that of undo2[x][y]   basically 2 moves prior to current state of game
    static int all0[]=new int[4];//it indicates if a particular coloumn is empty so as to not process it while performing moves
    static String space[][]=new String[4][4];
    static int win=0,i,j,c,empty,b,counter,gameover,exit,undo,score=0,tile0,move,finpos,finrow,addno,fincol,direct;//i is row no  j is coloumn no
    static double pos,ran,x;
    //c is used for confirming whether the user input a valid number, if not, c is default zero and user has to play again
    //empty is used to store the number of tiles that are empty or equal to zero
    //if gameover is 1 then GAMEOVER
    
public static void main(String[] args)throws IOException, InterruptedException
{
    Start();
}

public static void Start()throws IOException, InterruptedException
{
        game[2][1]=4;
        move=1;
        while(win==0)
     {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      //System.out.println('\u000C');    
      //System.out.print("\033[H\033[2J");
      //System.out.flush();
      c=0;
      empty=0;
      pos=0;
      finpos=0;
      tile0=0;
      gameover=1;
      
      
      for(i=1;i<16;i++)
      {
          zero[i]=0;
        }
      
      
      for(i=0;i<4;i++)
      {
          all0[i]=0;
        }
            
            
      for(i=0;i<4;i++)
      {
          for(j=0;j<4;j++)
          {
          if(game[i][j]==0)
          {
            empty++;
            zero[empty]=(i*10)+j;
            }
        }
      }
      
      
        if(move==1)
        {
     
        //have towrite code on 0 number of empty spaces i.e. gameover
        if(empty!=0)
        {
        pos=Math.random();
        if(pos<(1.0/empty)&&1<=empty)
        {
          finpos=zero[1];
        }
        for(b=1;b<13;b++)
        {
            x=b;
          if(pos>(x/empty)&&pos<((x+1)/empty)&&(x+1)<=empty)
          {
              
            finpos=zero[b];
          }
        }
        
        fincol=finpos%10;
        finrow=finpos/10;
        ran=Math.random();
        if(ran>0.8)
        addno=4;
        else
        addno=2;
        game[finrow][fincol]=addno;
       }
      }
      
      for(i=0;i<4;i++)
      {
          for(j=0;j<3;j++)
          {
              if(game[i][j]==game[i][j+1])
              gameover=0;
           }
        }
        
      for(i=0;i<3;i++)
      {
          for(j=0;j<4;j++)
          {
              if(game[i][j]==game[i+1][j])
              gameover=0;
           }
        }
        
        if(empty!=0)
        gameover=0;
        
       System.out.println("\t\t\t                              -:THE 2048 GAME:-\n\n\t\t\t                                  Score:"+score+"\t\t\t\t\n\n\t\t\t                      ||---------------------------||");
            for(i=0;i<4;i++)//this for loop adjusts the size of numbers
            {
                System.out.print("\t\t\t                      ||");
                for(j=0;j<4;j++)
                 {
                     
                     if(game[i][j]<10000&&game[i][j]>999)
                     space[i][j]="";
                     if(game[i][j]<1000&&game[i][j]>99)
                     space[i][j]=" ";
                     if(game[i][j]<100&&game[i][j]>9)
                     space[i][j]="  ";
                     if(game[i][j]<10)
                     space[i][j]="   ";
                     if(game[i][j]!=0)
                     System.out.print(" "+game[i][j]+space[i][j]+" |");
                     else
                     System.out.print("  "+space[i][j]+" |");
                 }
               System.out.println("|\n\t\t\t                      ||---------------------------||");
        }
        if(gameover==0)
        {
        while(c==0)
        {
            
            for(i=0;i<4;i++)
            {
                for(j=0;j<4;j++)
                {
                    if(game[i][j]==2048)
                    {
                        int continu;
                        System.out.println("\n\n\t\t\t                  YOU HAVE WON!!  DO YOU WISH TO CONTINUE?   (1 YES  2 NO)");
                        continu=Integer.parseInt(a.readLine());
                        if(continu==2)
                        {
                            System.out.println("\n\t\t\t                         PRESS ANY KEY TO QUIT THE GAME");
                            direct=Integer.parseInt(a.readLine());
                            System.exit(0);
                        }
                    }
                }
            }
            
         System.out.println("\n\n\t\t\t                 Press:'0' for undo(only 3 'undo' per move)\n\n\t\t\tEnter the direction\t\t\t\t("+(3-undo)+" undo chances are left)");
         direct=Integer.parseInt(a.readLine());         
         switch(direct)
         { 
          case 0:
          if(counter>0)
          {
              undo++;
           if(undo>3)
            undo=3;
          
          if(undo==1)
               {
                   counter--;
                   for(i=0;i<4;i++)
                  {
                   for(j=0;j<4;j++)
                    {
                        game[i][j]=checkgame[i][j];
                    }
                }
            }
            
          if(undo==2)
               {
                   counter--;
                   for(i=0;i<4;i++)
                  {
                   for(j=0;j<4;j++)
                    {
                        game[i][j]=undo2[i][j];
                    }
                }
            }
            
          if(undo==3)
               {
                   counter--;
                   for(i=0;i<4;i++)
                  {
                   for(j=0;j<4;j++)
                    {
                        game[i][j]=undo3[i][j];
                    }
                }
            }
        }
            move=0;
          c=1;
          break;
          
          case 2:down();
          c=1;
          break;
          
          case 4:left();
          c=1;
          break;
          
          case 6:right();
          c=1;
          break;
          
          case 8:up();
          c=1;
          break;
          
          default:c=0;
         }
        }
       }
      else
       {
           System.out.println("\n\t\t\tGAMEOVER\n\t\t\tEnter any number to exit");
           exit=Integer.parseInt(a.readLine()); 
           System.exit(0);
       }
    }
}



private static void up()
{
    move=0;
    
    for(i=0;i<4;i++)
     {
        for(j=0;j<4;j++)
        {
            temp[i][j]=undo3[i][j];
           undo3[i][j]=undo2[i][j];
           undo2[i][j]=checkgame[i][j];
            checkgame[i][j]=game[i][j]; 
        }
     }
    for(j=0;j<4;j++)
    {
        tile0=0;
        for(i=0;i<4;i++)
        {
            if(game[i][j]==0)
            tile0++;
            if(tile0==4)
            all0[j]=1;
        }
        
    }
   
    for(i=0;i<4;i++)
    {
       if(all0[i]==0)
       {
          while(game[0][i]==0)
           {
               game[0][i]=game[1][i];
               game[1][i]=game[2][i];
               game[2][i]=game[3][i];
               game[3][i]=0;
            }
        
            if(game[1][i]==0&&game[2][i]!=0)
                {
                    game[1][i]=game[2][i];
                    game[2][i]=game[3][i];
                    game[3][i]=0;
                }
                
            if(game[1][i]==0&&game[2][i]==0&&game[3][i]!=0)
                {
                    game[1][i]=game[3][i];
                    game[3][i]=0;
                }
            
            if(game[2][i]==0&&game[3][i]!=0)
                {
                game[2][i]=game[3][i];
                game[3][i]=0;
               }
                
            }
        
    }
    
        for(i=0;i<4;i++)
        {
            if(game[0][i]==game[1][i]&&game[0][i]!=0)
            {
                game[0][i]=(2*game[0][i]);
                score=score+game[0][i];
                game[1][i]=game[2][i];
                game[2][i]=game[3][i];
                game[3][i]=0;
            }
            if(game[1][i]==game[2][i]&&game[0][i]!=0)
            {
                game[1][i]=(2*game[1][i]);
                score=score+game[1][i];
                game[2][i]=game[3][i];
                game[3][i]=0;
            }
            if(game[2][i]==game[3][i]&&game[0][i]!=0)
            {
                game[2][i]=(2*game[2][i]);
                score=score+game[2][i];
                game[3][i]=0;
            }
           
            
        }
        
        for(i=0;i<4;i++)
            {
             for(j=0;j<4;j++)
            {
                if(checkgame[i][j]!=game[i][j])
                 {
                     move=1;
                     break;
                    }
                 
            }   
            if(move==1)
            {
                counter++;
                undo=0;
                break;
            }
            }
            if(move==0)
            {
              for(i=0;i<4;i++)
              {
                for(j=0;j<4;j++)
                {
                 checkgame[i][j]=undo2[i][j];
                 undo2[i][j]=undo3[i][j];
                 undo3[i][j]=temp[i][j];
               } 
            }
        }
         }



private static  void down()
{
    move=0;
    for(i=0;i<4;i++)
     {
        for(j=0;j<4;j++)
        {
            temp[i][j]=undo3[i][j];
           undo3[i][j]=undo2[i][j];
           undo2[i][j]=checkgame[i][j];
           checkgame[i][j]=game[i][j]; 
        }
     }
     
    for(j=0;j<4;j++)
    {
        tile0=0;
        for(i=0;i<4;i++)
        {
            if(game[i][j]==0)
            tile0++;
            if(tile0==4)
            all0[j]=1;
        }
        
    }
    
    
    
    for(i=0;i<4;i++)
    {
        if(all0[i]==0)
        {
            while(game[3][i]==0)
            {
               game[3][i]=game[2][i];
               game[2][i]=game[1][i];
               game[1][i]=game[0][i];
               game[0][i]=0;
            }
            
           
            if(game[2][i]==0&&game[1][i]!=0)
                {
                    game[2][i]=game[1][i];
                    game[1][i]=game[0][i];
                    game[0][i]=0;
                }
                    
            if(game[2][i]==0&&game[1][i]==0&&game[0][i]!=0)
                {
                    game[2][i]=game[0][i];
                    game[0][i]=0;
                }
             
            if(game[1][i]==0&&game[0][i]!=0)
                {
                game[1][i]=game[0][i];
                game[0][i]=0;
               }
        }
    }
    
    //following block is for addition
        for(i=0;i<4;i++)
        {
            
            if(game[3][i]==game[2][i]&&game[3][i]!=0)
            {
                game[3][i]=(2*game[3][i]);
                score=score+game[3][i];
                game[2][i]=game[1][i];
                game[1][i]=game[0][i];
                game[0][i]=0;
            }
            if(game[2][i]==game[1][i]&&game[2][i]!=0)
            {
                game[2][i]=(2*game[2][i]);
                score=score+game[2][i];
                game[1][i]=game[0][i];
                game[0][i]=0;
            }
            if(game[1][i]==game[0][i]&&game[1][i]!=0)
            {
                game[1][i]=(2*game[1][i]);
                score=score+game[1][i];
                game[0][i]=0;
            }
        }
        
        for(i=0;i<4;i++)
            {
             for(j=0;j<4;j++)
            {
                if(checkgame[i][j]!=game[i][j])
                 {
                     move=1;
                     break;
                    }
                 
            }   
            if(move==1)
            {
                counter++;
                undo=0;
                break;
        }
            }
            
            if(move==0)
            {
              for(i=0;i<4;i++)
              {
                for(j=0;j<4;j++)
                {
                 checkgame[i][j]=undo2[i][j];
                 undo2[i][j]=undo3[i][j];
                 undo3[i][j]=temp[i][j];
               } 
            }
        }
}



private static void left()

{
     move=0;
    for(i=0;i<4;i++)
     {
        for(j=0;j<4;j++)
        {
            temp[i][j]=undo3[i][j];
           undo3[i][j]=undo2[i][j];
           undo2[i][j]=checkgame[i][j];
           checkgame[i][j]=game[i][j]; 
        }
     }
     

    
    for(i=0;i<4;i++)
    {
        
       
       
           if(game[i][0]==0&&game[i][1]!=0)
           {
               game[i][0]=game[i][1];
               game[i][1]=game[i][2];
               game[i][2]=game[i][3];
               game[i][3]=0;
            }
           
        
           if(game[i][0]==0&&game[i][1]==0&&game[i][2]!=0)
           {
               game[i][0]=game[i][2];
               game[i][2]=game[i][3];
               game[i][3]=0;
            }
            
           if(game[i][0]==0&&game[i][1]==0&&game[i][2]==0&&game[i][3]!=0)
           {
               game[i][0]=game[i][3];
               game[i][3]=0;
            }
      
        
            if(game[i][1]==0&&game[i][2]!=0)
                {
                    game[i][1]=game[i][2];
                    game[i][2]=game[i][3];
                    game[i][3]=0;
                }
                
            if(game[i][1]==0&&game[i][2]==0&&game[i][3]!=0)
                {
                    game[i][1]=game[i][3];
                    game[i][3]=0;
                }
            
            if(game[i][2]==0&&game[i][3]!=0)
                {
                game[i][2]=game[i][3];
                game[i][3]=0;
               }
            
    }
    
    
        for(i=0;i<4;i++)
        {
            if(game[i][0]==game[i][1]&&game[i][0]!=0)
            {
                game[i][0]=(2*game[i][0]);
                score=score+game[i][0];
                game[i][1]=game[i][2];
                game[i][2]=game[i][3];
                game[i][3]=0;
            }
            if(game[i][1]==game[i][2]&&game[i][0]!=0)
            {
                game[i][1]=(2*game[i][1]);
                score=score+game[i][1];
                game[i][2]=game[i][3];
                game[i][3]=0;
            }
            if(game[i][2]==game[i][3]&&game[i][0]!=0)
            {
                game[i][2]=(2*game[i][2]);
                score=score+game[i][2];
                game[i][3]=0;
            }
           
            
        }
    
    
        for(i=0;i<4;i++)
            {
             for(j=0;j<4;j++)
            {
                if(checkgame[i][j]!=game[i][j])
                 {
                     move=1;
                     break;
                    }
                 
            }   
            if(move==1)
            {
                counter++;
                undo=0;
                break;
        }
            }
            
            if(move==0)
            {
              for(i=0;i<4;i++)
              {
                for(j=0;j<4;j++)
                {
                 checkgame[i][j]=undo2[i][j];
                 undo2[i][j]=undo3[i][j];
                 undo3[i][j]=temp[i][j];
               } 
            }
        }
}



private static void right()
{
    
     move=0;
    for(i=0;i<4;i++)
     {
        for(j=0;j<4;j++)
        {
            temp[i][j]=undo3[i][j];
           undo3[i][j]=undo2[i][j];
           undo2[i][j]=checkgame[i][j];
           checkgame[i][j]=game[i][j]; 
        }
     }
    
    for(i=0;i<4;i++)
    {
        
       
       
           if(game[i][3]==0&&game[i][2]!=0)
           {
               game[i][3]=game[i][2];
               game[i][2]=game[i][1];
               game[i][1]=game[i][0];
               game[i][0]=0;
            }
           
        
           if(game[i][3]==0&&game[i][2]==0&&game[i][1]!=0)
           {
               game[i][3]=game[i][1];
               game[i][1]=game[i][0];
               game[i][0]=0;
            }
            
           if(game[i][3]==0&&game[i][2]==0&&game[i][1]==0&&game[i][0]!=0)
           {
               game[i][3]=game[i][0];
               game[i][0]=0;
            }
      
        
            if(game[i][2]==0&&game[i][1]!=0)
                {
                    game[i][2]=game[i][1];
                    game[i][1]=game[i][0];
                    game[i][0]=0;
                }
                
            if(game[i][2]==0&&game[i][1]==0&&game[i][0]!=0)
                {
                    game[i][2]=game[i][0];
                    game[i][0]=0;
                }
            
            if(game[i][1]==0&&game[i][0]!=0)
                {
                game[i][1]=game[i][0];
                game[i][0]=0;
               }
            
    }
    
    
        for(i=0;i<4;i++)
        {
            if(game[i][3]==game[i][2]&&game[i][3]!=0)
            {
                game[i][3]=(2*game[i][3]);
                score=score+game[i][3];
                game[i][2]=game[i][1];
                game[i][1]=game[i][0];
                game[i][0]=0;
            }
            if(game[i][2]==game[i][1]&&game[i][3]!=0)
            {
                game[i][2]=(2*game[i][2]);
                score=score+game[i][2];
                game[i][1]=game[i][0];
                game[i][0]=0;
            }
            if(game[i][1]==game[i][0]&&game[i][3]!=0)
            {
                game[i][1]=(2*game[i][1]);
                score=score+game[i][1];
                game[i][0]=0;
            }
           
            
        }
    
        for(i=0;i<4;i++)
            {
             for(j=0;j<4;j++)
            {
                if(checkgame[i][j]!=game[i][j])
                 {
                     move=1;
                     break;
                    }
                 
            }   
            if(move==1)
            {
                counter++;
                undo=0;
                break;
            }
            }
            
            if(move==0)
            {
              for(i=0;i<4;i++)
              {
                for(j=0;j<4;j++)
                {
                 checkgame[i][j]=undo2[i][j];
                 undo2[i][j]=undo3[i][j];
                 undo3[i][j]=temp[i][j];
               } 
            }
        }
}


}

