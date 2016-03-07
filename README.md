# Android Development Assignment
Please submit this assignment as a public git repository
and provide me a link to it. Please also send me a link to
an apk file hosted on some cloud storage.

Your assignment is to write an executable app which uses
Foursquare APIs https://foursquare.com/

Please display pizza restaurants close to the user position
(+ add a possibility to emulate user's location in the
center of New York). The resulting list should at least
show the restaurant name and distance to the user. The
list should be sorted in an ascending order using distance.

Lazy loading should be implemented. App should fetch
and show only first 10 restaurants. The bottommost list
item should contain a loading indicator. When loading
indicator becomes visible app should request 10 more
restaurant from the API.

You are free to use any method you want in order to
achieve this, i.e. treat the assignment as you would any
other project.

Create another screen with details of a single PizzaPlace.
Please make a transition to the newly created screen
when user clicks on some list item. Display there some
detailed information passed from the previous screen.

Downloaded data must be persisted. App must display
latest downloaded data if there is no Internet connection.
All disk writes must be performed asynchronously.
You are free to choose any libraries or tools. Explain your
choice.