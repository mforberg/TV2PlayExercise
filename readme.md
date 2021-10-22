# Part 1
The API was created in IntelliJ IDEA, with the use of Maven for build automation, Spring for the creation of the API, and JUnit for testing

#
The HTTP API has been written in Java, the solution can be found here:
- exercise\src\main\java\dk\tv2play\exercise

Some tests have been created for the API, these can be found here:
- exercise\src\test\java\dk\tv2play\exercise

A _VERY_ simple Python client has been created to send requests to the API endpoint, it can be seen here:
- exercise\simple_client.py
 
#

  
__Sample Output can be seen here:__

[From IntelliJ Console](https://i.imgur.com/fUK46cd.png)

[From Python Terminal](https://i.imgur.com/8zJ2IR4.png)
#

# Part 2

> "Tell us what you think about the input data model. Is the current
JSON format a good fit for this kind of data or can you come up
with a better version? There are no right answers here. Please
write your thoughts in the readme.md"

I do not think the current JSON formatting is very good. I will first outline what I dislike, then subsequently explain why I dislike it and propose changes.

1. The current formatting cannot handle more than 7 weekdays worth of programming information "efficiently"
1. The current formatting has separated each program into two "states" (begin/end)

1. The date/timestamp provided is not "accurate", and hard to use without conversion first
1. "Arguably", including days with no airing programs is unnecessary

Each point will now be elaborated on briefly:
1. Since there is no distinction between actual dates, having multiple "mondays" (as an example) listed can end up being parsed incorrectly, if the EPG is more than 7 days long. A simple addition of including the date can alleviate this.
1. Having multiple entries in a nested node for the same program is counter-intuitive, and makes parsing the data more troublesome. Simply adding "begin" and "end" as keys in each node would alleviate this. e.g.: _[...]_ "friday": [ {"title": "ESLPro","begin": 82800,"end": 3600 } ] _[...]_

1. The timestamp does not include the actual date, and this makes accuracy very hard, using full timestamps would alleviate this.
1. Sending additional "data" without having a need for it is not the most efficient, it can help for clarity, but it could potentially be left out with much larger input.