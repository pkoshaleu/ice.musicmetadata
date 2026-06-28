# Artist of the Day

As a user, you should be able to see a different "Artist of the Day" in a cyclical
manner on the homepage each day, ensuring a fair rotation through the entire catalogue of artists.
This means if there are n artists, after n days, the cycle restarts with the first artist, 
ensuring an equal
chance for each artist to be the "Artist of the Day".

## Analysis & Implementation

Under some specific conditions (a fixed list of artists, fixed in size and equal to 
some power of two) one can come up with a recurrence formula which will do it,
but unfortunately the case described above is not exactly that.

Truly random selection will not help either. It is in the nature of random generators to have
a chance of generating the same number in sequence. 

A trial with a boolean memory of presented artists will not help either.

A better strategy, in my opinion, would be:

- select some reasonable number of artists as a batch, 
- shuffle it, 
- present them one by one until the batch is exhausted.

As soon as the last artist from the batch is presented, select a new batch, covering freshly 
added artists.

Taking into account that in my test dataset I have only a few artists to present, I can
keep everything in memory and reshuffle it on exhaustion.