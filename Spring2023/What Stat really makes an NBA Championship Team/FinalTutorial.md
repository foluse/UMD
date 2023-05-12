# What Stat really makes an NBA Championship Team
### Group Members: Fiyin Oluseye, Amara Ihediohanma, Roger Thibaudeau, Sean Sweeney

Through this tutorial you will apply data wrangling and exploratory data analysis skills to NBA data.
Our main focus is to determine which statistics serve as the most reliable indicators of a playoff team's chances of winning the championship.

## Background
The NBA is a professional basketball league where teams compete to win as many games as possible throughout the season. In the NBA, teams play 82 regular-season games and need to meet specific criteria to qualify for the playoffs. The objective is to outscore opponents and secure victories. Generally, teams with higher caliber players tend to perform better. However, some teams have found success by redefining player performance criteria and identifying undervalued players based on specific metrics. By employing this strategy, teams can build competitive rosters and increase their chances of making the playoffs. The NBA playoffs consist of 16 qualifying teams, with each playoff series following a best-of-seven games format to determine which team advances to the next round.

We will be analyzing data from playoff teams only. Our data will be gathered from the [Basketball Reference]([url](https://www.basketball-reference.com/leagues/)). Specifically, we will be analyzing data from the 2020, 2021, and 2022 NBA playoff seasons. We have compiled the necessary data provided from basketball reference into easily extractable CSV files found below:


Download the following playoff data:
[2020_Playoff_Stats.csv](https://github.com/foluse/UMD/files/11448895/2020_Playoff_Stats.csv)
[2021_Playoff_Stats.csv](https://github.com/foluse/UMD/files/11448896/2021_Playoff_Stats.csv)
[2022_Playoff_Stats.csv](https://github.com/foluse/UMD/files/11448897/2022_Playoff_Stats.csv)

Place all data files into the same folder as your python notebook.

## Part 1: Data scraping and preparation
### Step 1: Scrape data from each of the past 3 NBA playoff seasons
Use Python to scrape all team statistics from the past 3 NBA playoff.

```
import pandas as pd
data = pd.read_csv("sportsref_download.csv", sep='\t')
data.head()
```
Here is a sample output from the 2020 Playoffs:
```
	Rk	Team	G	MP	FG	FGA	FG%	3P	3PA	3P%	...	FT%	ORB	DRB	TRB	AST	STL	BLK	TOV	PF	PTS
0	1.0	Boston Celtics	24	5760	881	1962	0.449	328	879	0.373	...	0.797	216	814	1030	588	154	150	353	497	2533
1	2.0	Golden State Warriors	22	5280	910	1895	0.480	308	821	0.375	...	0.766	216	750	966	594	170	109	320	474	2461
2	3.0	Dallas Mavericks	18	4320	653	1455	0.449	284	747	0.380	...	0.771	117	540	657	345	129	50	184	380	1914
3	4.0	Miami Heat	18	4320	684	1536	0.445	196	626	0.313	...	0.804	177	562	739	394	150	66	233	386	1876
4	5.0	Phoenix Suns	13	3120	535	1076	0.497	128	353	0.363	...	0.817	123	399	522	334	86	49	173	292	1399
```

Repeat for the 2021 and 2022 playoff seasons.

### Step 2: Data Visualization
Create a bar graph using three point percentage of each of the 16 playoff teams for each year we're observing. Observe the champion of that year by creating some type of destinction in its bar on the graph. 

Do the same thing with ORB (Offensive Rebounds), FG (Field Goal %), TOV (Turnovers), Points, and PF (Personal Fouls)

This image is prior to finding the linear regression line, that should be plotted after displaying the data in the image. 
![2020 3pt percentage](https://github.com/foluse/UMD/assets/76791730/7c9c4dd4-fff0-4d5c-90d0-2e58f80fb47d)

### Question 1: What is a general trend you notice about the data?
### Question 2: Looking at the data, create an interpretation for the pattern you noted for the previous question. For example, turnovers in each year decrease as we supposedly go down in ranking (meaning the supposed "best team" has the most turnovers)?

## Part 2: Regression and Analysis
Now let's look at the data with a fitted regression line for each scatter plot to see trends in various statistics for each of the last three playoff years.

### Analysis Question 1:
How does the championship team compare to the rest of the playoff teams? Create a linear regression line as well.


