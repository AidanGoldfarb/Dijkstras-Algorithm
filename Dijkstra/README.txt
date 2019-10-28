Aidan Goldfarb - agoldfa7
agoldfa7@u.rochester.edu
No partner 
Lab- MW 2:00PM - 3:15PM


Project 3. All files contained in readme are authored by me. Map is the JFrame that things are drawn on. 
Graph is the object used to store all intersections and roads.
Commandline should resemble: 
"java StreetMap (file).txt --show* --directions* (intersection) (intersection)"
*conditionally optional
The main challege was implementing the shortest path algorithm, but trial and error prevailed.
Larger mappes were done by making sure no duplicate nodes were added into the priority queue, 
as the shortest path algo often looks at nodes more than once
without visiting them. 
3 PNG's of runtime analysis is included in the submission file, titled "ur_RUNTIME",
"monroe_RUNTIME", and "nys_RUNTIME"
For runtime, the random first and last nodes were selected in order to simulate worst case scenario 
where the maximum number of nodes are processed. As number of a node had no meaning concerning its
distance from another, i.e. node_1 and node_2 could be further away than node_1 and node_2333
(very helpful...)
I just picked random numbers to evaluate runtime. 

I chose soothing colors for my map in order to relax you guys for finals 
and I believe it warrents some extra credit.
 


All global variables have getters and setters.
Any void method is a mutator 
Any <T> method returns a/an <T> based on <P> input parameter



Outside sources: https://en.wikipedia.org/wiki/Haversine_formula