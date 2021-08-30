# Problem
Social media Influencers have been basing the price of their social media post on a single post basis. So If a brand required 10 posts (for example spread over a period) then they would be charged 10x the cost of a single post. One company has decided to allow social media influencers to sell posts in bundles and charge the brand on a per bundle basis. So if the Influencer sold image based posts in bundles of 5 and 10 and brand ordered 15 they would get a bundle of 10 and a bundle of 5.

The company currently allows the influencer to monitize the following submission formats:

| Submission format | Format code | Bundles                           |
| ----------------- | ----------- | --------------------------------- |
| Image             | IMG         | 5 @ $450 10 @ $800                |
| Audio             | Flac        | 3 @ $427.50 6 @ $810 9 @ $1147.50 |
| Video             | VID         | 3 @ $570 5 @ $900 9 @ $1530       |

### Task
Given a brands order, you are required to determine the cost and bundle breakdown for each submission format. For simplicity, each order should contain the minimal number of bundles.

# Example
### Input:
Each order has a series of lines with each line containing the number of items followed by the submission format code
An example input:
```
10 IMG
15 FLAC
13 VID
```
### Output:

A successfully passing test(s) that demonstrates the following output: (The format of the output is not important)
```
10 IMG $800
1 x 10 $800
15 FLAC $1957.50
1 x 9 $1147.50
1 x 6 $810
13 VID $2370
2 x 5 $1800
1 x 3 $570
```

# Solution
1. I construct two objects - pers.grace.calculator.Media and pers.grace.calculator.ThreeMediaCalculator. <br>
pers.grace.calculator.Media can represent each type of media, including the media name and a table which contains the bundle information. 
   `calSingleType()` returns the table of result and also log the result in console. <br>
pers.grace.calculator.ThreeMediaCalculator includes three types of media, a table of the bundle information of these media, and a table of result 
   after calculation.`calTotal()` returns the result after calculation. `printRes()` uses log to print the result.
2. For a single media, I divide the required input amount into three cases.`Method: calSingleType()` <br>
   (1) If the input amount is smaller than the cheapest bundle. It simply returns a single bundle. <br>
   (2) If the input amount is larger than the sum of all bundles, which means the larger part is repeated. I record the number it has repeated and cut them, then passed the remained part to (3). After it finds the cheapest solution by (3), I add the repeated bundles back.<br>
   (3) For the rest cases, I use recursion to calculate the cheapest solution.`Method: calBasicCase()` I calculate all possible solutions
and find the minimum cost solution. The base case is when the size of map is smaller than two. For the rest cases,
I use a for loop listed all possible solutions, delete the first element, and the rest part to the next recursion.

