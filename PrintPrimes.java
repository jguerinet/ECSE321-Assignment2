public class PrintPrimes {
  int numberOfPrimes;
  int numberOfRows;
  int numberOfColumns;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int numberOfRows, int numberOfColumns, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.numberOfRows  = numberOfRows;
    this.numberOfColumns  = numberOfColumns;
    this.ORDMAX = ORDMAX;
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

  private void calculateOddPrimes() {
      boolean isPrime;
      int nonPrimeNumbers[] = new int[ORDMAX + 1];

      int currentNumber = 1;
      //This will keep the index of the prime number that is the square root of the next square we will encounter.
      int primeNumberIndex = 2;
      int square = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentNumber = currentNumber + 2;
          if (currentNumber == square) {
            primeNumberIndex = primeNumberIndex + 1;
            square = listOfPrimes[primeNumberIndex] * listOfPrimes[primeNumberIndex];
            nonPrimeNumbers[primeNumberIndex - 1] = currentNumber;
          }
          int i = 2;
          isPrime = true;
          while (i < primeNumberIndex && isPrime) {
            while (nonPrimeNumbers[i] < currentNumber)
              nonPrimeNumbers[i] = nonPrimeNumbers[i] + listOfPrimes[i] + listOfPrimes[i];
            if (nonPrimeNumbers[i] == currentNumber){
              isPrime = false;
			}
            i = i + 1;
          }
        } while (!isPrime);
        listOfPrimes[primesFoundSoFar] = currentNumber;
      }
    }

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int rowOffset = pageOffset; rowOffset < pageOffset + numberOfRows; rowOffset++){
            for (int C = 0; C < numberOfColumns;C++)
              if (rowOffset + C * numberOfRows <= numberOfPrimes){
                System.out.format("%10d", listOfPrimes[rowOffset + C * numberOfRows]);
              }
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + numberOfRows * numberOfColumns;
        }
    }
}

					 
