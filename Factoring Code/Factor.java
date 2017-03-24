package com.company;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

/*
    Author: Adam Austin
    Date: 3/23/17
    Description: This file holds the solution to HW8. It uses the Pollards Rho algorithm to find two factors of a large integer.
        The program can be run either with command line arguments -f <file.txt> to run the program with a file filled with integers
        or -n <number> to run the program with an individual integer. The program uses the BigInteger library of java to do calculations
        with arbitrarily large integers.
 */


public class Factor {
    // Main will look first for command line arguments to run with and if none are found redirect to userInteract
    public static void main(String[] args) throws IOException {
        if (args.length > 2) {
            String runType = args[0];
            String input = args[1];

            switch (runType) {
                case "-f":
                    runWithFile(input);
                    break;
                case "-n":
                    runWithNumber(input);
                    break;
                default:
                    System.out.println("Your first command line argument was not understood: please enter \"-f\" or \"-n\" for your first argument.");
                    userInteract();
                    break;
            }
        } else {
            System.out.println("You ran the program without two command line arguments. ");
            userInteract();
        }
    }

    /*
     This function handles user interaction if the program is run without command line arguments
      */
    public static void userInteract() {
        Scanner reader = new Scanner(System.in);
        String input;
        System.out.println();
        System.out.println("Enter \"q\" at any time to quit.");
        System.out.println();
        System.out.println("Would you like to run the Pollards Rho algorithm next to Adam's Brute Force algorithm? For yes enter \"y\", otherwise click any other key.");
        input = reader.nextLine();
        switch (input) {
            case "y":
            case "Y":
                userInteractTwoFunc();
                return;
            default:
                break;
        }

        System.out.println("Would you like to run an entire file or a single number?");
        String runType = "0";

        while ((runType.compareTo("f") != 0) && (runType.compareTo("r") != 0) && (runType.compareTo("F") != 0) && (runType.compareTo("R") != 0)) {
            System.out.println();
            System.out.println("Enter \"f\" or \"n\":");
            runType = reader.nextLine();
            switch (runType) {
                case "f":
                case "F":
                    System.out.println("Enter your filename (EX: \"file.txt\"):");
                    input = reader.nextLine();
                    try {
                        runWithFile(input);
                    } catch (FileNotFoundException exc) {
                        System.out.println("The file you entered was not found");
                        runType = "0";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "n":
                case "N":
                    System.out.println("Enter your number (EX: \"45678743457\"):");
                    input = reader.nextLine();
                    runWithNumber(input);
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Please enter one of the specified input characters; " + runType + " is not recognized.");
                    break;
            }
        }
    }

    public static void userInteractTwoFunc() {
        System.out.println("Welcome to run two functions!");

        Scanner reader = new Scanner(System.in);
        String input;
        System.out.println();
        System.out.println("Enter \"q\" at any time to quit.");
        System.out.println();
        System.out.println("Would you like to run an entire file or a single number?");
        String runType = "0";
        while ((runType.compareTo("f") != 0) && (runType.compareTo("r") != 0) && (runType.compareTo("F") != 0) && (runType.compareTo("R") != 0)) {
            System.out.println();
            System.out.println("Enter \"f\" or \"n\":");
            runType = reader.nextLine();
            switch (runType) {
                case "f":
                case "F":
                    System.out.println("Enter your filename (EX: \"file.txt\"):");
                    input = reader.nextLine();
                    try {
                        runWithFile2Func(input);
                    } catch (FileNotFoundException exc) {
                        System.out.println("The file you entered was not found");
                        runType = "0";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "n":
                case "N":
                    System.out.println("Enter your number (EX: \"45678743457\"):");
                    input = reader.nextLine();
                    runWithNumber2Func(input);
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Please enter one of the specified input characters; " + runType + " is not recognized.");
                    break;
            }
        }
    }

    /*
        This function runs an individual number from user input through the Pollars Rho algorithm
    */
    public static void runWithNumber(String input) {
        BigInteger userInt;
        try {
            userInt = new BigInteger(input);
            if (userInt.compareTo(BigInteger.ZERO) > 0) {
                BigInteger val2a;
                BigInteger val2b;
                long startTime;
                long endTime;
                long elapsedTime;
                startTime = System.currentTimeMillis();
                val2a = pollardsRoh(userInt);
                endTime = System.currentTimeMillis();
                val2b = userInt.divide(val2a);
                System.out.println("pollardRho Factor of " + userInt + " is " + val2a + " and " + val2b);
                System.out.println();
                elapsedTime = endTime - startTime;
                if (elapsedTime > 60000) {
                    System.out.print("Factoring took " + elapsedTime / 60000 + " minutes, ");
                    elapsedTime %= 60000;
                }
                if (elapsedTime > 1000) {
                    System.out.print(elapsedTime / 1000 + " seconds, and ");
                    elapsedTime %= 1000;
                }
            } else {
                System.out.println("Please enter a number that is non-zero and positive.");
            }
            System.out.println("------------------END------------------");
        } catch (java.lang.NumberFormatException ex) {
            System.out.println("The number you entered was not valid. Please enter an integer that contains only numeric digits.");
        }
    }

    /*
        This function runs a file through the Pollards Rho algorithm
    */
    public static void runWithFile(String input) throws IOException {
        FileInputStream fstream = new FileInputStream(input);
        BufferedReader buff = new BufferedReader(new InputStreamReader(fstream));
        String line;
        BigInteger val2a;
        BigInteger val2b;
        BigInteger disInt;
        long startTime;
        long endTime;
        long elapsedTime;
        int check = 1;
        while ((line = buff.readLine()) != null) {
            disInt = new BigInteger(line);
            startTime = System.currentTimeMillis();
            val2a = pollardsRoh(disInt);
            endTime = System.currentTimeMillis();
            val2b = disInt.divide(val2a);
            System.out.println("pollardRho Check: " + check + ", Factor of " + disInt + " is " + val2a + " and " + val2b);
            check++;
            System.out.println();
            elapsedTime = endTime - startTime;
            if (elapsedTime > 60000) {
                System.out.print("Factoring took " + elapsedTime / 60000 + " minutes, ");
                elapsedTime %= 60000;
            }
            if (elapsedTime > 1000) {
                System.out.print(elapsedTime / 1000 + " seconds, and ");
                elapsedTime %= 1000;
            }
            System.out.println(elapsedTime + " milliseconds");
        }

        System.out.println("------------------END------------------");
    }

    /*
        This function runs an individual number from user input through the Pollars Rho algorithm
    */
    public static void runWithNumber2Func(String input) {
        BigInteger userInt;
        try {
            userInt = new BigInteger(input);
            if (userInt.compareTo(BigInteger.ZERO) > 0) {
                BigInteger val1;
                BigInteger val2a;
                BigInteger val2b;
                long startTime;
                long endTime;
                long elapsedTime;
                startTime = System.currentTimeMillis();
                val2a = pollardsRoh(userInt);
                endTime = System.currentTimeMillis();
                val2b = userInt.divide(val2a);
                System.out.println("pollardRho Factor of " + userInt + " is " + val2a + " and " + val2b);
                System.out.println();
                elapsedTime = endTime - startTime;
                if (elapsedTime > 60000) {
                    System.out.print("Factoring took " + elapsedTime / 60000 + " minutes, ");
                    elapsedTime %= 60000;
                }
                if (elapsedTime > 1000) {
                    System.out.print(elapsedTime / 1000 + " seconds, and ");
                    elapsedTime %= 1000;
                }
                System.out.println();

                startTime = System.currentTimeMillis();
                val1 = autoVaryingIntervalFactor(userInt);
                endTime = System.currentTimeMillis();
                System.out.println("autoVaryingIntervalFactor Factor of " + userInt + " is " + val1);
                System.out.println();
                elapsedTime = endTime - startTime;
                if (elapsedTime > 60000) {
                    System.out.print("Factoring took " + elapsedTime / 60000 + " minutes, ");
                    elapsedTime %= 60000;
                }
                if (elapsedTime > 1000) {
                    System.out.print(elapsedTime / 1000 + " seconds, and ");
                    elapsedTime %= 1000;
                }
            } else {
                System.out.println("Please enter a number that is non-zero and positive.");
            }
            System.out.println("------------------END------------------");
        } catch (java.lang.NumberFormatException ex) {
            System.out.println("The number you entered was not valid. Please enter an integer that contains only numeric digits.");
        }
    }

    /*
        This function runs a file through the Pollards Rho algorithm
    */
    public static void runWithFile2Func(String input) throws IOException {
        FileInputStream fstream = new FileInputStream(input);
        BufferedReader buff = new BufferedReader(new InputStreamReader(fstream));
        String line;
        BigInteger val1;
        BigInteger val2a;
        BigInteger val2b;
        BigInteger disInt;
        long startTime;
        long endTime;
        long elapsedTime;
        int check = 1;
        while ((line = buff.readLine()) != null) {
            disInt = new BigInteger(line);
            startTime = System.currentTimeMillis();
            val2a = pollardsRoh(disInt);
            endTime = System.currentTimeMillis();
            val2b = disInt.divide(val2a);
            System.out.println("pollardRho Check: " + check + ", Factor of " + disInt + " is " + val2a + " and " + val2b);
            elapsedTime = endTime - startTime;
            if (elapsedTime > 60000) {
                System.out.print("Factoring took " + elapsedTime / 60000 + " minutes, ");
                elapsedTime %= 60000;
            }
            if (elapsedTime > 1000) {
                System.out.print(elapsedTime / 1000 + " seconds, and ");
                elapsedTime %= 1000;
            }
            System.out.println(elapsedTime + " milliseconds");
            System.out.println();

            startTime = System.currentTimeMillis();
            val1 = autoVaryingIntervalFactor(disInt);
            endTime = System.currentTimeMillis();
            System.out.println("autoVaryingIntervalFactor Check: " + check + ", Factor of " + disInt + " is " + val1);
            elapsedTime = endTime - startTime;
            if (elapsedTime > 60000) {
                System.out.print("Factoring took " + elapsedTime / 60000 + " minutes, ");
                elapsedTime %= 60000;
            }
            if (elapsedTime > 1000) {
                System.out.print(elapsedTime / 1000 + " seconds, and ");
                elapsedTime %= 1000;
            }
            System.out.println(elapsedTime + " milliseconds");
            System.out.println("---------------------------------------");
            check++;
        }

        System.out.println("------------------END------------------");
    }

    /*
        This function is an implementation of the PollardsRho algorithm
    */
    public static BigInteger pollardsRoh(BigInteger num) {
        BigInteger a = new BigInteger("2");                     // a will be our first number used to produce a somewhat random number to test as a factor of num
        BigInteger b = a;                                       // b will be our second, start with the two equal.
        BigInteger gcd = BigInteger.ZERO;                       // Define a variable to be used for the greatest common divisor later on
        BigInteger pFactor;                                     // Define a variable to hold a possible factor of num


        do {                                                    // Execute at least once:

            b = disperseNum(b, num);                             // Send b through function that will disperse its value between 0 and n
            pFactor = a.subtract(b).abs();                      // Use positive difference of a and b to find a possible factor
            if (pFactor.compareTo(BigInteger.ZERO) != 0) {      // Check pFactor does not equal zero
                gcd = findGreatestCommonDivisor(pFactor, num);  // Set gcd to the greatest common divisor of pFactor and num
            }

            if (gcd.compareTo(BigInteger.ONE) != 0) {           // Check if the gcd is not 1, then gcd is one of the factors.
                return gcd;
            }

            // Repeat steps above after running b through the function a second time, this will gurantee every value in the set (a) will be checked against every other value (b)
            b = disperseNum(b, num);                             pFactor = a.subtract(b).abs();
            if (pFactor.compareTo(BigInteger.ZERO) != 0) {
                gcd = findGreatestCommonDivisor(pFactor, num);
            }

            if (gcd.compareTo(BigInteger.ONE) != 0) {
                return gcd;
            }

            a = disperseNum(a, num);                             // Send value a through value dispersion function once for every 2 times b goes through.
        }
        while (a != b);                                         // Repeat as long as a and b are not equal (b going 2x faster than a has iterated over every number)
        return BigInteger.ONE;                                  // If no factor was found, the number is prime and the only factor is 1
    }

    public static BigInteger disperseNum(BigInteger num, BigInteger n) {
        return num.pow(2).subtract(BigInteger.ONE).mod(n);      // As a pseudo random. return ((num^2 - 1) mod n)
    }

    /*
        use the Euclidian algorithm to find a GCD
     */
    public static BigInteger findGreatestCommonDivisor(BigInteger a, BigInteger b) {
        BigInteger remainder = BigInteger.ONE;                  // Var for remainder
        BigInteger greater;                                     // Var for the greater of the two nums being used to find the GCD
        BigInteger lesser;                                      // Var for the lesser of the two nums being used to find the GCD
        if (a.compareTo(b) > 0) {
            greater = a;
            lesser = b;
        } else {
            greater = b;
            lesser = a;
        }

        while (remainder.compareTo(BigInteger.ZERO) != 0) {     // While the lesser number does not divide into the greater number, run one cycle of the euclidian algorithm
            remainder = greater.mod(lesser);
            greater = lesser;
            lesser = remainder;
        }
        return greater;                                         // Return the greater number which at the point of breaking the loop will contain the GCD
    }

    /*
        A driver for the varyingIntervalFactor function that calculates the number of intervals to be used.
        Determines whether to use function with or without list
     */
    public static BigInteger autoVaryingIntervalFactor(BigInteger num) {
        try {
            int arrSize = 1000000;
            BigInteger[] primesArr = new BigInteger[arrSize];
            readPrimes("primes1million.txt",primesArr, arrSize);

            BigInteger root = rootAproximation(num);
            BigInteger target1 = new BigInteger("10000000");
            if (root.compareTo(target1) > 0) {
                int numIntervals = 10000;
                for (; numIntervals < 2000000000 && root.subtract(target1).compareTo(BigInteger.ZERO) >= 0; numIntervals *= 10) {
                    root = root.divide(BigInteger.TEN);
                }
                return varyingIntervalFactorWList(num, primesArr, numIntervals);
            }
            else {
                return varyingIntervalFactorWList(num, primesArr, 100000);
            }
        } catch (IOException e) {
            BigInteger root = rootAproximation(num);
            BigInteger target1 = new BigInteger("1000000000");
            BigInteger target2 = new BigInteger("100");
            if (root.compareTo(target1) > 0) {                  // Determine base to use for calculating the num intervals (will be 10000 vs 1)
                //System.out.println("Looking");
                int numIntervals = 1000;
                for (; numIntervals < 2000000000 && root.subtract(target1).compareTo(BigInteger.ZERO) >= 0; numIntervals *= 10) {
                    root = root.divide(BigInteger.TEN);                             // For every digit above the 8th place of the root multiply the interval 10000 by 10
                }
                return varyingIntervalFactor(num, numIntervals);
            }
            else {
                int numIntervals = 1;
                for (; numIntervals < 2000000000 && root.subtract(target2).compareTo(BigInteger.ZERO) >= 0; numIntervals *= 10) {
                    root = root.divide(BigInteger.TEN);                             // For every digit above the 3rd place of the root multiply the interval 1 by 10
                }
                return varyingIntervalFactor(num, numIntervals);
            }
        }

    }

    /*
        Find Factor using intervals spread out between 3 and the square root of the number being factored.
     */
    public static BigInteger varyingIntervalFactor(BigInteger num, int numIntervals) {
        System.out.println("No prime list was found: running without list.");

        BigInteger TWO = new BigInteger("2");
        BigInteger root = rootAproximation(num);
        BigInteger[] intervalArr = new BigInteger[numIntervals];                                // Declare an array for the intervals
        BigInteger bigNumIntervals = new BigInteger(String.valueOf(numIntervals));
        BigInteger lastNum = TWO;
        BigInteger difference = root.subtract(lastNum);
        BigInteger intervalSize = difference.divide(bigNumIntervals);                           // Calculate the interval size from difference/numIntervals

        for (int i = 0; i < numIntervals; i++) {                                                // Loop to place a value at every interval
            intervalArr[i] = lastNum.add(intervalSize.multiply(BigInteger.valueOf((long) i)));
            if (intervalArr[i].mod(TWO).compareTo(BigInteger.ZERO) == 0) {
                intervalArr[i] = intervalArr[i].add(BigInteger.ONE);                            // If the value is even add one
            }
        }

        difference = BigInteger.ZERO;
        BigInteger temp = BigInteger.ZERO;
        while (difference.compareTo(intervalSize) < 0) {                                           // Loop while the difference (being used as an offset) is less than the interval size
            for (int i = 0; i < numIntervals; i++) {
                temp = intervalArr[i];
                temp = temp.add(difference);
                if (num.mod(temp).compareTo(BigInteger.ZERO) == 0) {                               // Check if the interval value plus the offset is a factor for every interval value
                    return temp;
                }
            }
            difference = difference.add(TWO);
        }
        return BigInteger.ZERO;
    }

    /*
          Find Factor using intervals using list
    */
    public static BigInteger varyingIntervalFactorWList(BigInteger num, BigInteger[] primesArr, int numIntervals) {
        System.out.println("List found: running with list.");


        BigInteger TWO = new BigInteger("2");

        BigInteger largestPrime = primesArr[primesArr.length - 1];

        BigInteger root = rootAproximation(num);

        int index = 0;
        int check = 1;
        while (primesArr[index].compareTo(largestPrime) < 0 && primesArr[index].compareTo(root) < 0)
        {
            check++;
            if (BigInteger.ZERO == num.mod(primesArr[index]))
            {
                return primesArr[index];
            }
            index++;
        }

        BigInteger[] intervalArr = new BigInteger[numIntervals];
        BigInteger bigNumIntervals = new BigInteger(String.valueOf(numIntervals));
        BigInteger lastNum = largestPrime;
        BigInteger difference = root.subtract(lastNum);
        BigInteger intervalSize = difference.divide(bigNumIntervals);

        for (int i = 0; i < numIntervals; i++) {
            intervalArr[i] = largestPrime.add(intervalSize.multiply(BigInteger.valueOf((long) i)));
            if (intervalArr[i].mod(TWO).compareTo(BigInteger.ZERO) == 0) {
                intervalArr[i] = intervalArr[i].subtract(BigInteger.ONE);
            }
        }

        difference = BigInteger.ZERO;
        BigInteger temp = BigInteger.ZERO;
        while (difference.compareTo(intervalSize) < 0)
        {
            for (int i = 0; i < numIntervals; i++) {
                temp = intervalArr[i];
                temp = temp.add(difference);
                if (num.mod(temp).compareTo(BigInteger.ZERO) == 0) {
                    System.out.println("Answer found after " + difference.divide(TWO) + " comparisons accross all intervals where root = " + root + ".");
                    return temp;
                }
            }
            difference = difference.add(TWO);
        }
        System.out.println("No Factor Found");
        return BigInteger.ZERO;
    }

    /*
        A function that aproximates the root of a number
     */
    public static BigInteger rootAproximation (BigInteger num) {
        int TWO_TO_THE_SIXTEENTH = 65536;
        BigInteger divisor = new BigInteger("2");
        BigInteger solution = num;
        for (int i = TWO_TO_THE_SIXTEENTH; i >= 2; i /= 2) {        // Divid by powers of 2 untill a number is found that when squared is slightly larger than the given num
            BigInteger bigI = new BigInteger(String.valueOf(i));
            BigInteger temp = solution;
            while (temp.multiply(temp).compareTo(num) >= 0) {
                solution = temp;
                temp = temp.divide(bigI);
            }
        }
        return solution;
    }

    /*
        A function to read a list of primes from a file into an array
     */
    public static void readPrimes(String fileName, BigInteger[] arr, int length) throws IOException {
        FileInputStream fstream = new FileInputStream(fileName);
        BufferedReader buff = new BufferedReader(new InputStreamReader(fstream));
        String line;
        int index = 0;
        while ((line = buff.readLine()) != null && index < length) {
            BigInteger disInt = new BigInteger(line);
            arr[index] = disInt;
            index++;
        }
    }


    /*------------------------------------------------------------------------------------------
    All of the bellow functions have to do with making a list of primes n long
    ------------------------------------------------------------------------------------------*/

    /*
    Function to write a list of primes to a file, will write listSize number primes to file
     */
    public static void writePrimeListToFile(String fileName, int listSize) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("------- in writePrimeListToFile -------");

        BigInteger[] primeList = new BigInteger[listSize];
        fillListWithInts(primeList, listSize);
        overWritePrimeList(primeList, listSize, 0);

        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        for (int i = 0; i < listSize; i++) {
            writer.println(primeList[i]);
        }

        writer.close();
    }

    /*
        Fills an array with integers starting at 2
     */
    public static void fillListWithInts(BigInteger[] arr, int arrSize){
        System.out.println("------- in fillListWithInts -------");
        for (int i = 0; i < arrSize; i++) {
            BigInteger temp = new BigInteger(String.valueOf(i+2));
            arr[i] = temp;
        }
    }

    /*
        Recursive function to fill with primes
     */
    public static void overWritePrimeList(BigInteger[] arr, int arrSize, int index) {
        System.out.println("------- in overWritePrimeList -------");

        int root = arr[arr.length - 1].intValue();
        root = ((int) Math.sqrt(root)) + 1;
        for (int i = index; i < arrSize && arr[i].intValue() < root; i++)	// loop up to the square root of n
        {
            BigInteger currNum = arr[i];		// set the current num in the array
            if (currNum != BigInteger.ZERO)	{		// if the current num was not already determined to be prime...
                for (int j = i + 1; j < arrSize; j++) {		// Loop through every other num and eliminate all the nums that are multiples of currNum
                    if(arr[j] != BigInteger.ZERO && (arr[j].mod(currNum) == BigInteger.ZERO)) {
                        //System.out.println("Compare " + arr[j] + " % " + currNum);
                        arr[j] = BigInteger.ZERO;
                    }
                }
            }
        }

        int lastPrime = removeZerosFromArray(arr, arrSize, index);

        reFillArray(arr, arrSize, lastPrime);

        if (lastPrime < arrSize - 1) {
            overWritePrimeList(arr, arrSize, lastPrime);
        }
    }

    /*
        re-fills array with integers not divisible by ints already existing in list
     */
    public static void reFillArray(BigInteger[] arr, int arrSize, int index) {      // Array will have primes and zeros, condense primes and add new values
        System.out.println("------- in reFillArray -------");
        int offSet = 2;
        boolean good;
        for (int i = index; i < arrSize;) {
            if (arr[i].compareTo(BigInteger.ZERO) == 0) {
                BigInteger bigOffSet = new BigInteger(String.valueOf(offSet));
                BigInteger newNum = arr[i - 1].add(bigOffSet);
                good = true;
                for (int j = 0; j < index && good; j++) {
                    if (newNum.mod(arr[j]) == BigInteger.ZERO) {
                        good = false;
                        break;
                    }
                }
                if(good) {
                    arr[i] = newNum;
                    i++;
                    offSet = 2;
                }
                else {
                    offSet += 2;
                }
            }
            else {
                i++;
            }
        }
    }

    /*
        remove all zeros betwee prime ellements and put zeros all at end of list
     */
    public static int removeZerosFromArray(BigInteger[] arr, int arrSize, int index) {      // Return index of last prime number
        System.out.println("------- in removeZerosFromArray -------");
        boolean found = true;
        int i = index;
        for (; i < arrSize && found; i++) {
            found = false;
            if (arr[i].compareTo(BigInteger.ZERO) == 0) {
                for (int j = i + 1; j < arrSize && !found; j++) {
                    if (arr[j].compareTo(BigInteger.ZERO) != 0) {
                        arr[i] = arr[j];
                        arr[j] = BigInteger.ZERO;
                        found = true;
                    }
                }
            }
            else {
                found = true;
            }
        }
        return i - 1;       // The previous value holds the last prime
    }
}
