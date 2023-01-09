# Flexible Hash Map
A hash map implementation that support two different collision handling schemes and some new methods

## Project details
We develop your own flexible hashTable ADT and provide an efficient open addressing implementation for simple bank accounts. The file account.txt stores the account number, the account type (chequing, saving, credit), the first name, the last name, and the account balance.

We design and implement a hash method that would accept the account numbers (keys) and produce hash values. The hash method consists of two parts, the hash code map to integer numbers and the compression map from integer numbers to our array indices. Our code reads the account entries from the file and insert them into the hash table. The hash table's original size is 71 which is extended whenever the load factor reaches a threshold value. For collision handling, we first execute the code with linear probing and a second time with double hashing. For each run, we
1. Display the hash table
2. Display the total number of collisions
3. Display the total number of probes.

### The hashTable ADT additionally implements the following methods:

**setRehashThreshold(loadFactor):** where 0 ≤ loadFactor ≤ 1 is a real number that defines when the table should be extended, i.e., if the hash table’s load factor is greater than or equal to value of loadFactor. We use a load factor of 0.7 but vary this value down to 0.5 in order to find an optimal measure for each collision handling scheme.


**setRehashFactor(factor):** where factor is a positive real number used to extend the hash table. The real value specifies the factor for extending the table size (e.g., a value of 1.5 means the new table size is equal to 1.5 times the old size). Also, we have to make sure that the new size is prime.

**setCollisionHandling(type):** where type is either the character ‘L’ (for linear probing) or ‘D’ (for double hashing). It allows users to select as collision-handling scheme either linear probing or double hashing.

Once the hash table is implemented, we perform the following operations for both linear probing and double hashing. At each step we display the hash table, the total number of collisions, and the total number of probes for each collision handling scheme.
* Insert chequing account 03891 for Jane Walter with a balance of $20.00.
* Insert saving account 53794 for Michel Roy with a balance of $3500.00.
* Delete entries with account numbers 81514, 12421 and 83830.
* Insert credit account 87532 for Xi Liu with a balance of $500.00.
* Search for account numbers 03891, 87532 and 81514 and display the entries.


## Results
a small section of the results are given bellow. The complete results are generated in the "map.txt" file.

```txt
collision handling type: L
Total collisions: 39
Total probes: 121
table capacity: 107
table rehash factor: 1.5
table rehash threshold: 0.7
table load factor: 0.66
map:
account number, first name, last name, account balance
[20922, [CHEQUING, MARIA, GUARDIA, 1740]]
[92318, [SAVING, PIERRE, DOLLARD, 1290]]
[31718, [CHEQUING, PHILLIP, LENNOX, 10]]
[00925, [SAVING, EMILIO, PEREZ, 675]]
[51728, [CREDIT, KIRK, DOUGLAS, 0]]
[58342, [CREDIT, MIKE, HEINZ, 100]]
[61326, [CHEQUING, HARRY, KUHN, 1750]]
[81514, [CREDIT, VICTORIA, HAL, 1300]]
[38421, [CHEQUING, PAUL, MORISSON, 5300]]
[91214, [SAVING, PATRICIA, COOK, 1420]]
[91316, [CHEQUING, LAWRENCE, COOK, 65000]]
[01823, [SAVING, PATRICK, JUSTICE, 560]]
[52853, [CREDIT, MICHAEL, BOONE, 1000]]
[43817, [CHEQUING, DIANE, PARTON, 35]]
[91412, [SAVING, DENNIS, MACLEAN, 95]]
```