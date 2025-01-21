# spring-boot-rest-api-request-limit

What is Rate Limiting?
[Rate limiting](https://www.cloudflare.com/en-gb/learning/bots/what-is-rate-limiting/) is a strategy
to [limit access to APIs](https://cloud.google.com/solutions/rate-limiting-strategies-techniques). It restricts the
number of API calls that a client can make within a certain time frame. This helps defend the API against overuse, both
unintentional and malicious.
It puts a cap on how often someone can repeat an action within a certain timeframe — for instance, trying to log in to
an account. Rate limiting can help stop certain kinds of
malicious [bot activity](https://www.cloudflare.com/learning/bots/what-is-a-bot/). Rate limits are often applied to an
API by tracking the IP address, or in a more business-specific way, such as API keys or access tokens.

### What is Bucket4j?

[Bucket4j](https://bucket4j.com/8.7.0/toc.html) is a Java rate-limiting library that is mainly based on the token-bucket
algorithm, which is by the de-facto standard for rate-limiting in the IT industry.

What is Token Bucket Algorithm?
The Token Bucket algorithm is a popular and simple method used in computer networking and telecommunications for traffic
shaping and rate limiting. It is designed to control the amount of data that a system can send or receive in some sort
of period, ensuring that the traffic conforms to a specified rate.

The Apache benchmark tool is very basic, and while it will give you a solid idea of some performance, it is a bad idea
to only depend on it if you plan to have your site exposed to serious stress in production.

Having said that, here's the most common and simplest parameters:

-c: ("Concurrency"). Indicates how many clients (people/users) will be hitting the site at the same time. While ab runs,
there will be -c clients hitting the site. This is what actually decides the amount of stress your site will suffer
during the benchmark.

-n: Indicates how many requests are going to be made. This just decides the length of the benchmark. A high -n value
with a -c value that your server can support is a good idea to ensure that things don't break under sustained stress:
it's not the same to support stress for 5 seconds than for 5 hours.

-k: This does the "KeepAlive" functionality browsers do by nature. You don't need to pass a value for -k as it "
boolean" (meaning: it indicates that you desire for your test to use the Keep Alive header from HTTP and sustain the
connection). Since browsers do this and you're likely to want to simulate the stress and flow that your site will have
from browsers, it is recommended you do a benchmark with this.

The final argument is simply the host. By default, it will hit http:// protocol if you don't specify it.

```shell
 ab -k -c 350 -n 20000 http://localhost:8080/api/v1/names
```
