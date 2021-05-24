# DataStructure_RadixSort-Cursor
Radix sort was proposed for sorting numbers, but if we consider a number as a string of digits, the
algorithm can be considered as a string sorting algorithm. In this project we
implement a radix sort algorithm for sorting strings in ascending order. The input to our algorithm
should be a (multi)set S = [S1, S2, . . . , Sn] of strings each of which is of length m over the English
alphabet [A…Z, a…z]. The output should be the set sorted in ascending lexicographical order.
We use the cursor implementation of the linked list data structure to store characters in
our buckets

Example: the set {data, structures, and, algorithms, in, C} should be sorted as follows:
a l g o r i t h m
a n d
C
d a t a
i n
s t r u c t u R e s
