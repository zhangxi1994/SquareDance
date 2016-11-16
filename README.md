# SquareDance
This repository contains the project called Squredance for the course COMS 4444 -- Programming and Problem Solving.

Known members of this fabulous team are (in no particular order):

- Xi Zhang ([zhang.xi@columbia.edu](mailto:zhang.xi@columbia.edu))
- Chengyu Lin ([cl3529@columbia.edu](mailto:cl3529@columbia.edu))
- Shardendu Gautam ([sg3391@columbia.edu](mailto:sg3391@columbia.edu))

# Project Discription
The Future of Square Dancing

In the year 2273, square dancing has become a highly refined and popular activity. Dance masters control the movements of the dancers in a much more fine-grained manner than in the distant past. The original square dances that date back to the 17th century have all participants listening to a single caller. (Amusing example https://www.youtube.com/watch?v=QuaojjCV1Tk)

Nowadays, each participant is wearing a personal square-dance assisting hat fondly known as the square-cap. The square-cap relays indvidual instructions to each dancer about where to go next. What's more, the square-cap senses the dancer's brain-waves and sends the dance caller a number that corresponds to the dancer's level of enjoyment.

Despite the advanced technology, square dancing is still a social event, and dancers like to dance with some people more than others. Two people will dance if they are (a) mutually the closest to one another, and (b) between 0.5m and 2m from each other (they need space to avoid injury). While dancing the dancers execute twirls and jumps, but basically stay in the same place. (Ironic, isn't it.) When the dance caller tells them to move, they relocate to the new position and begin dancing right away if they can. The dance floor is a 20m by 20m square (it's a square dance, after all).

**The dance caller (which is actually a program written by you) can send instructions to move every 6 seconds, which is the simulation granularity. The caller sends some subset of the participants simultaneous instructions to move to specific locations up to 2m from their current positions. The remaining dancers stay put.(Am I overusing parenthesized comments?)**

There are d total participants at the event. Each participant has f friends among the group, and friendship is mutual, but unknown to the caller, who doesn't even know f. Each participant has a single soulmate in the room whose identity is also unknown to the caller, and the soulmate relationship is also mutual. The remaining group members are strangers. These relationships are important for determining the enjoyment of the dancers:

A dancer will get 60 units of enjoyment per minute (i.e., 6 units per 6-second simulation interval) when dancing with a soulmate. Dancers never get bored of dancing with a soulmate.
A dancer will get 40 units of enjoyment per minute (i.e., 4 units per 6-second simulation interval) when dancing with a friend. However, after 5 minutes they get bored and no longer enjoy dancing with that friend.
A dancer will get 30 units of enjoyment per minute (i.e., 3 units per 6-second simulation interval) when dancing with a stranger. However, after 2 minutes they get bored and no longer enjoy dancing with that stranger.
An individual who is not able to dance gets no enjoyment for that simulation unit. However, if the dance caller places a person less than 0.1m from somebody else, that person feels claustrophobic and loses 5 units of enjoyment per 6-second simulation interval.
Note that the boredom threshold is cumulative, meaning that if a dancer dances with a friend for 3 minutes, disengages, and then dances with that same friend later for 2 more minutes, the dancer still gets bored.
The dance caller gets moment-to-moment measures of the dancers' enjoyment, from which she can determine (a) friendship/soulmate/stranger relationships and (b) when a dancer is going to get bored. 

**The goal of the dance caller is to dynamically choreograph a 3-hour dance to maximize the minimum enjoyment level of the dancers. In other words, you are scored by how well the least happy dancer fared at the dance. The choreography includes both the initial locations of all participants, as well as the movements of the participants over time.**


# Collaborating on GitHub

Each of us can push our own code to our own branches (e.g. Xi would push to a 'xi' branch) and then create pull requests to merge into master.
