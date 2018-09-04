import java.util.Scanner;

public class Game
{
    private final int SIZE_OF_NUMBER=4;
    Scanner scan=new Scanner(System.in);

    private int[] returnFeedback ()
    {
        int[] ans=new int[2];

        System.out.print("Number of bulls:   ");
        ans[0]=scan.nextInt();

        System.out.print("Number of cows:   ");
        ans[1]=scan.nextInt();

        System.out.println();

        return ans;
    }

    private void printGoodToGo ()
    {
        System.out.println("   Think of a number and press enter to continue");

        scan.nextLine();
    }

    private void printHelp()
    {
        System.out.println("   Bulls and Cows has one goal - guess the right number");
        System.out.println("   Usually, you are the one mindstorming furiously trying to guess it");
        System.out.println("   Don't you get tired?!");
        System.out.println("   Lucky for you, out team developed a way for you to get it back to\n" +
                " the Computer for giving you had numbers");
        System.out.println("\n   This time, he is the one guessing your own number");
        System.out.println("\n   All you need to do is think of a 4-digit number");
        System.out.println("   With different digits");
        System.out.println("   Your number might start with 0");
        System.out.println("\n   After that, you will be asked questions about your number");
        System.out.println("   Computer will give you a guess");
        System.out.println("   Your task is to say how many <Bulls> and <Cows> its number gets");
        System.out.println("\n   The number of bulls is defined by\n" + "the number of digits of the guess that are the same\n" +
                " as in your number and stands on the same position");
        System.out.println("\n   The number of cows is the number of right digits in the guess\n" +
                "that are not right positioned");
        System.out.println("\n   So that's it. Good luck, hope you K.O. this bastard!\n\n");

        printGoodToGo();
    }

    private void startPrint()
    {
        System.out.println("   Press 'H' for Help\n   Press enter to begin the game\n");

        String helpMe=scan.nextLine();

        if (helpMe.equals("H") || helpMe.equals("h"))
        {
            printHelp();
        }

        else
        {
            printGoodToGo();
        }
    }


    public void startGame ()
    {
       startPrint();

        int cntGuess=0;
        Computer comp=new Computer();

        while (true)
        {
            cntGuess++;

            char[] tekGuess=comp.guess();
            System.out.print("Guess number "+cntGuess+":  ");

            for (int i=0; i<4; i++)
            {
                System.out.print(tekGuess[i]);
            }
            System.out.println();

            int[] animals=returnFeedback();
            if (animals[0]==SIZE_OF_NUMBER)
            {
                System.out.println("Computer wins in "+cntGuess+" moves. GG");
                scan.close();
                return;
            }

            comp.cutPoss(tekGuess, animals);

            if (comp.getNumLeft()==0)
            {
                System.out.println("ERROR! Mistake detected. Are you sure you counted the livestock right?");
                scan.close();
                return;
            }
        }
    }
}