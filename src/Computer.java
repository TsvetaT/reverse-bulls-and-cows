public class Computer
{
    private final int SIZE=4,LENGTH=10000,NUMBER_DIGITS=10;;
    private boolean[] flagNum=new boolean[LENGTH];
    private int numLeft=LENGTH;

    private boolean isLegitNumber (char[] num)
    {
        for (int i=0; i<SIZE; i++)
        {
            for (int j=i+1; j<SIZE; j++)
            {
                if (num[i]==num[j])
                {
                    return false;
                }
            }
        }
        return true;
   }

    public Computer()
    {
        for (int i=0; i<LENGTH; i++)
        {
            if (!isLegitNumber(makeCharArray(i)))
            {
                flagNum[i]=true;
                numLeft--;
            }
        }

    }

    private int getRandPos (int maxPos)
    {
        return (int)(Math.random()*maxPos);
    }

    private char[] makeCharArray (int num)
    {
        char[] charNumber=new char[SIZE];

        for (int i=SIZE-1; i>=0; i--)
        {
            charNumber[i]=(char)(num%NUMBER_DIGITS+'0');
            num/=NUMBER_DIGITS;
        }

        return charNumber;
    }

    public int getNumLeft()
    {
        return numLeft;
    }

    public char[] guess ()
    {
        int[] freePositions=new int[LENGTH];
        int numFreePos=0;

        for (int i=0; i<LENGTH; i++)
        {
            if (!flagNum[i])
            {
                freePositions[numFreePos]=i;
                numFreePos++;
            }
        }

        int newPosition=getRandPos(numFreePos);
        return makeCharArray(freePositions[newPosition]);
    }

    private int countBulls (char[] s, char[] p)
    {
        int bulls=0;

        for (int i=0; i<SIZE; i++)
        {
            if (s[i]==p[i])
            {
                bulls++;
            }
        }

        return bulls;
    }

    private int countCows (char[] s, char[] p)
    {
        int cows=0;

        for (int i=0; i<SIZE; i++)
        {
            for (int j=0; j<SIZE; j++)
            {
                if (i!=j && s[i]==p[j])
                {
                    cows++;
                }
            }
        }

        return cows;
    }

    private boolean corresponding (char[] s, char[] p, int[] animals)
    {
        if (countBulls(s,p)!=animals[0])
        {
            return false;
        }

        if (countCows(s,p)!=animals[1])
        {
            return false;
        }

        return true;
    }

    public void cutPoss (char[] tekGuess, int[] animals)
    {
        for (int i=0; i<LENGTH; i++)
        {
            if (!flagNum[i] && !corresponding(makeCharArray(i),tekGuess,animals))
            {
                flagNum[i] = true;
                numLeft--;
            }
        }
    }
}
