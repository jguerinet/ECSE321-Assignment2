/*
 * Julien Guerinet
 * ECSE 321 - Introduction to Software Engineering
 * Assignment 2
 */
public class PrintPrimes {
	//Number of Primes we are looking for
	int numberOfPrimes;
	//Number of rows per page 
	int numberOfRows;
	//Number of columns per page
	int numberOfColumns;
	//Array size where we store the non-prime numbers to compare with each number
	int nonPrimeNumbersArraySize;
	//The Array containing the final list of primes. 
	int listOfPrimes[];

	//Constructor method
	public PrintPrimes(int numberOfPrimes, int numberOfRows, int numberOfColumns, int nonPrimeNumbersArraySize) {
		this.numberOfPrimes   = numberOfPrimes;
		this.numberOfRows  = numberOfRows;
		this.numberOfColumns  = numberOfColumns;
		this.nonPrimeNumbersArraySize = nonPrimeNumbersArraySize;
		this.listOfPrimes = new int[numberOfPrimes + 1];
	}


	public static void main(String[] args) {
		PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
		printPrimes.calculatePrimes();
		printPrimes.printPrimes();
	}

	public void calculatePrimes() {
		/* Two is the only even prime. All other prime numbers are odd.
		 * To simplify the code, we simply add 2 as a prime number, and
		 * delegate the task of finding all odd prime numbers to a helper
		 * function.
		 */
		listOfPrimes[1] = 2;
		calculateOddPrimes();
	}

	//This method will calculate all of the odd primes up until we have found the number of primes specified by the PrintPrimes object
	private void calculateOddPrimes() {
		boolean isPrime;
		int nonPrimeNumbers[] = new int[nonPrimeNumbersArraySize + 1];

		//Number to analyze
		int currentNumber = 1;
		//This will keep the index of the prime number that is the square root of the next square we will encounter.
		int primeNumberIndex = 2;
		//The square of the next prime number. 
		int square = 9;

		for (int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
			do {
				//Increment by 2 because we are only looking for odd numbers
				currentNumber = currentNumber + 2;
				/*If the current number is the stored square: 
				 * 	- Increment primeNumberIndex
				 * 	- Calculate the next square
				 * 	- Store the current number in the nonPrimeNumbers arraylist
				*/
				if (currentNumber == square) {
					primeNumberIndex = primeNumberIndex + 1;
					square = listOfPrimes[primeNumberIndex] * listOfPrimes[primeNumberIndex];
					nonPrimeNumbers[primeNumberIndex - 1] = currentNumber;
				}
				int i = 2;
				isPrime = true;
				while (i < primeNumberIndex && isPrime) {
					while (nonPrimeNumbers[i] < currentNumber){
						nonPrimeNumbers[i] = nonPrimeNumbers[i] + listOfPrimes[i] + listOfPrimes[i];
					}
					//If the current number equals to a number in the nonPrimeNumbers Arraylist, it cannot be prime.
					if (nonPrimeNumbers[i] == currentNumber){
						isPrime = false;
					}
					//Increment i
					i = i + 1;
				}
			} while (!isPrime);
			//Store the current number (that is prime) at the primesFoundSoFar index of listOfPrimes
			listOfPrimes[primesFoundSoFar] = currentNumber;
		}
	}

	//This method will print the primes that are stored in the listOfPrimes[] arraylist of the PrintPrimes object
	public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
        	//Print this at the top of each page
        	System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
        	//Blank line to separate header from numbers
        	System.out.println("");
        	for (int rowOffset = pageOffset; rowOffset < pageOffset + numberOfRows; rowOffset++){
        		//Print column by column
        		for (int currentColumn = 0; currentColumn < numberOfColumns; currentColumn++)
        			//Check that there are still numbers to print
        			if (rowOffset + currentColumn * numberOfRows <= numberOfPrimes){
        				System.out.format("%10d", listOfPrimes[rowOffset + currentColumn * numberOfRows]);
        			}
        		//Go to the next line
        		System.out.println("");
        	}
        	//Go to next 'page'
        	System.out.println("\f");
        	//Increment page number
        	pageNumber = pageNumber + 1;
        	//Increment page offset
        	pageOffset = pageOffset + numberOfRows * numberOfColumns;
        }
    }
}

					 
