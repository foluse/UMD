# What Stat really makes an NBA Championship Team

Through this tutorial you will apply data wrangling and exploratory data analysis skills to NBA data.
Our main focus is to determine which statistics serve as the most reliable indicators of a team's chances of winning the championship.

## Background
The NBA is a professional basketball league where teams compete to win as many games as possible throughout the season. In the NBA, teams play 82 regular-season games and need to meet specific criteria to qualify for the playoffs. The objective is to outscore opponents and secure victories. Generally, teams with higher caliber players tend to perform better. However, some teams have found success by redefining player performance criteria and identifying undervalued players based on specific metrics. By employing this strategy, teams can build competitive rosters and increase their chances of making the playoffs. The NBA playoffs consist of 16 qualifying teams, with each playoff series following a best-of-seven games format to determine which team advances to the next round.

## Part 1: Data scraping and preparation
Step 1: Scrape data from the past 3 NBA playoff seasons
Use Python to scrape all team statistics from the past 3 NBA playoff seasons (19-20, 20-21, 21-22) gathered from Basketball refernece. Steps to do this are:

1. pip install or conda install the following Python packages: beautifulsoup4, requests, pandas, numpy; these are already in the environment if you are using Docker.
2. Use requests to get (as in, HTTP GET) the URL
3. Extract the text from the page
4. Use BeautifulSoup to read and parse the data, either as html or lxml
5. Use prettify() to view the content and find the appropriate table
6. Use find() to save the aforementioned table as a variable
7. Use pandas to read in the HTML file. HINT make-sure the above data is properly typecast.
8. Set reasonable names for the table columns, e.g., rank, x_classification, date, region, start_time, maximum_time, end_time, movie. Pandas.columns makes this very simple.

