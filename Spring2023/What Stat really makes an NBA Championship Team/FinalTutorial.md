# What Stat really makes an NBA Championship Team
### Group Members: Fiyin Oluseye, Amara Ihediohanma, Roger Thibaudeau, Sean Sweeney

Through this tutorial you will apply data wrangling and exploratory data analysis skills to NBA data.
Our main focus is to determine which statistics serve as the most reliable indicators of a playoff team's chances of winning the championship.

## Background
The NBA is a professional basketball league where teams compete to win as many games as possible throughout the season. In the NBA, teams play 82 regular-season games and need to meet specific criteria to qualify for the playoffs. The objective is to outscore opponents and secure victories. Generally, teams with higher caliber players tend to perform better. However, some teams have found success by redefining player performance criteria and identifying undervalued players based on specific metrics. By employing this strategy, teams can build competitive rosters and increase their chances of making the playoffs. The NBA playoffs consist of 16 qualifying teams, with each playoff series following a best-of-seven games format to determine which team advances to the next round.

We will be analyzing data from playoff teams only. Our data will be gathered from the [Basketball Reference]([url](https://www.basketball-reference.com/leagues/)). Specifically, we will be analyzing data from the 2020, 2021, and 2022 NBA playoff seasons. We have compiled the necessary data provided from basketball reference into easily extractable CSV files found below:


Download the following playoff data:
[2020 Playoff Stats.csv](https://github.com/foluse/UMD/files/11448474/2020.Playoff.Stats.csv)
[2021 Playoff Stats.csv](https://github.com/foluse/UMD/files/11448476/2021.Playoff.Stats.csv)
[2022 Playoff Stats.csv](https://github.com/foluse/UMD/files/11448478/2022.Playoff.Stats.csv)

Place all data files into the same folder as your python notebook.

## Part 1: Data scraping and preparation
Step 1: Scrape data from each of the past 3 NBA playoff seasons
Use Python to scrape all team statistics from the past 3 NBA playoff.

```
import pandas as pd
data = pd.read_csv("sportsref_download.csv", sep='\t')
data.head()
```

```
		Rk	Team	G	MP	FG	FGA	FG%	3P	3PA	3P%	...	FT%	ORB	DRB	TRB	AST	STL	BLK	TOV	PF	PTS
0	1.0	Boston Celtics	24	5760	881	1962	0.449	328	879	0.373	...	0.797	216	814	1030	588	154	150	353	497	2533
1	2.0	Golden State Warriors	22	5280	910	1895	0.480	308	821	0.375	...	0.766	216	750	966	594	170	109	320	474	2461
2	3.0	Dallas Mavericks	18	4320	653	1455	0.449	284	747	0.380	...	0.771	117	540	657	345	129	50	184	380	1914
3	4.0	Miami Heat	18	4320	684	1536	0.445	196	626	0.313	...	0.804	177	562	739	394	150	66	233	386	1876
4	5.0	Phoenix Suns	13	3120	535	1076	0.497	128	353	0.363	...	0.817	123	399	522	334	86	49	173	292	1399
5	6.0	Memphis Grizzlies	12	2880	477	1096	0.435	157	430	0.365	...	0.735	149	401	550	302	110	73	168	249	1350
6	7.0	Philadelphia 76ers	12	2905	437	939	0.465	149	400	0.373	...	0.849	92	376	468	261	73	52	177	247	1254
7	8.0	Milwaukee Bucks	12	2880	462	1056	0.438	127	388	0.327	...	0.731	117	488	605	250	76	54	165	230	1233
8	9.0	New Orleans Pelicans	6	1440	234	506	0.462	56	158	0.354	...	0.780	91	183	274	128	38	21	87	127	659
9	10.0	Minnesota Timberwolves	6	1440	218	492	0.443	83	214	0.388	...	0.810	42	198	240	137	49	47	106	161	655
10	11.0	Toronto Raptors	6	1465	230	516	0.446	59	197	0.299	...	0.794	60	165	225	124	40	29	60	133	619
11	12.0	Utah Jazz	6	1440	210	474	0.443	49	179	0.274	...	0.786	56	213	269	103	24	22	71	132	594
12	13.0	Denver Nuggets	5	1200	197	414	0.476	56	157	0.357	...	0.794	56	153	209	125	35	17	82	125	550
13	14.0	Atlanta Hawks	5	1200	172	391	0.440	57	175	0.326	...	0.782	43	154	197	93	29	12	82	108	487
14	15.0	Chicago Bulls	5	1200	182	451	0.404	52	184	0.283	...	0.833	41	179	220	115	39	16	65	93	476
15	16.0	Brooklyn Nets	4	960	157	312	0.503	46	109	0.422	...	0.738	34	102	136	89	32	26	61	99	436
16	NaN	League Average	11	2613	415	911	0.456	133	376	0.355	...	0.785	102	355	457	249	77	50	149	233	1156
```
Here is a sample output from the 2020 Playoffs:



