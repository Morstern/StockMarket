# StockMarket – Java + MySQL + React-Native
Application to track stock changes (<b>First Spring project created from scratch</b>)


Backend works perfectly:
- authentication (httpBasic),
- I used Stream API (Java 8) to map entities to desired view-objects,
- Business Logic was made by using TDD. (class NetProfitMarginCalculator),
- scheduled work (Server initializes class that creates process, which uses program written in python – which scraps website and gathers informations),
- REST functionality (most of endpoints in addition can be paginated).


Database works perfectly. <b>In addition of database creation script</b>:
- created trigger which deletes all data created by an user,
- created view, which displays recent informations about all stocks.

<h2>
<b>
Project is abandoned due to problems with communication on frontend. (I'm not into developing mobile applications,
and further research made me think that there MIGHT be a problem with connecting frontend to locally created backend)
<br/><br/>
I will resume working on the project in future (but I'll make a website instead of mobile application)
</b>
</h2>
