************************************************************************
### B.Sc. (Hons) in Software Development - Artificial Intelligence
************************************************************************

### Initial setup

Encrypted document contains the cipher-text of Chapter 1 of The Hobbit,
encrypted with the key THEQUICKBROWNFXMPDVLAZYGS. The total number
of characters in the text is 35,713. All instances of the letter 'J'
have been replaced with an 'I' and all whitespace removed after 
converting the text to uppercase. The length of the document should
provide ample levels of repetition to facilitate a frequency-based
cipher attack.

### Requirements

Develop an application using Simulated Annealing Algorithm to perform frequency based cipher attack
on encrypted text. Use 4grams.txt file that contains 389,373  ngrams of size 4 
and their occurrence count in a large sample of documents to calculate heuristic
value of decrypted document as a scoring criteria for the keys tried during
algorithm execution.


### Application Components

1) SimulatedAnnealingAlgorithm.java - a class that executes main flow of the program.
It controls iteration loops, calling necessary methods to perform decryption, decryption scoring and 
key transformation, sets up conditions for key to become parent and eventually best key,
controls cooling of the keys throughout an execution.

2) PlayfairCipherDecoder.java - class that decodes preped cipher text by applying
Playfair Cipher rules against the matrix of size 5x5 containing an encryption key. 
Class uses an instance of a PlayfairCipherMatrix.java, which is the key itself, and 
map coordinates and characters to apply decoding rules.

3) PlayfairCipherMatrix.java - class containing matrix of the key to decode against.
It can be queried for specified character coordinates or vise versa, character at specified 
coordinates within a matrix. Class also contains a method that transform matrix, applying some
changes to the key for next series of decryption. Finally, class has a method to return a copy
of currently set matrix to save "good" keys.

4) MatrixTransformer.java - class that randomly applies changes to the passed matrix.

5) HeuristicsCalculator.java - class that calculates score of the decrypted key by 
summing the log probability of each 4gram contained within decrypted text. Class uses
an instance of QuadGramsRepo.java that contains a map of 4gram.txt file to obtain 
log probability values of 4grams. Additionally, class prep decrypted document sample 
into 4grams set by using NGramBuilder.java and overlap of a 4gram can be set. 