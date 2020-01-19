## JMeter
We used JMeter to make performance tests on our api and to make sure it handles the heavy load that we throw at it.

Since our main model entities are more or less alike in size, we chose the do the tests with the UserEntiy, specifically the creation of users.

We created different test scenarios that exert more or less heavy load on the server. They can be found [here](https://github.com/Crulllo/Teaching-HEIGVD-AMT-2019-Project-Two/tree/master/jmeter) in the JMeter folder in the root of our project.

For this we implemented a special endpoint that received POST http requests  to `api/testUsers`with an empty body, and creates a user with a random username in our db. This is implemented as an admin operation so you have to have an admin token in the header of the request to be able to run the tests.

